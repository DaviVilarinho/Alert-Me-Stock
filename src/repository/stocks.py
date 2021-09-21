import handler.credential as crd
import requests
import datetime
import logging

def getAlphaVantageURLBollinger(symbol: str):
    return "https://www.alphavantage.co/query?function=BBANDS&symbol={}&interval=weekly&time_period=22&matype=1&series_type=close&nbdevup=1.3&nbdevdn=1.3&apikey={}".format(symbol, crd.ALPHA_VANTAGE_KEY) # 1.3 at stddev because i need WARNINGS, not exactly when


def getAlphaVantageURLPrice(symbol: str):
    return "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol={}&outputsize=compact&apikey={}".format(symbol, crd.ALPHA_VANTAGE_KEY) 


class Stock:
    def __init__(self, tick: str):
        self.tick = tick

        # getting Bollinger Data
        req = requests.get(getAlphaVantageURLBollinger(self.tick))
        self.bollingers = req.json()
        self.bollingers = self.bollingers["Technical Analysis: BBANDS"] # encontrar & assignar json
        req.close()
        logging.info("got bollinger bands for " + self.tick)

        # getting price data
        req = requests.get(getAlphaVantageURLPrice(self.tick))

        self.prices = req.json()
        self.prices = self.prices["Time Series (Daily)"]
        req.close()
        logging.info("got prices for " + self.tick)

        self.lastDaySync = self.findLastSyncDate() # find the correct date
        self.refprice = -1
        self.bblow    = -1
        self.bbhi     = -1


    def __str__(self):
        return '{"TICK": {}, "DaySync": {}, "Price": {}, "UpperBand": {}, "LowerBand": {}, "inWarning": {}}'.format(self.tick, self.lastDaySync.isoformat(), self.price, self.bbhi, self.bblow, self.isWarnable())

    def isWarnable(self):
        try: 
            self.refprice  = float(self.prices[self.lastDaySync.isoformat()]["4. close"])
            self.bblow     = float(self.bollingers[self.lastDaySync.isoformat()]["Real Lower Band"])
            self.bbhi      = float(self.bollingers[self.lastDaySync.isoformat()]["Real Upper Band"])

            logging.info("Prices ({}, {}, {}) are found by the day {}".format(self.refprice, self.bblow, self.bbhi, self.lastDaySync.isoformat()))
            if self.refprice >= self.bbhi :
                return 1
            elif self.refprice <= self.bblow:
                return -1
            else:
                return 0
        except KeyError: # if there's no date
            logging.warning("KEY ERROR: {} is not avaiable!".format(self.lastDaySync.isoformat()))
            return False # there's nothing to see
        
        
        
    def findLastSyncDate(self):
        today = datetime.date.today()
        for i in range(10): # try 10 different days
            try:
                if self.bollingers[today.isoformat()] and self.prices[today.isoformat()]: # if today is the fetch day
                    logging.info("Find day synced and it is: " + today.isoformat())
                    return today
            except KeyError: # if not, subtract one day
                today = today - datetime.timedelta(days=1)
