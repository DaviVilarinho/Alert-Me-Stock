import handler.credential as crd
import requests
import datetime

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

        # getting price data
        req = requests.get(getAlphaVantageURLPrice(self.tick))

        self.prices = req.json()
        self.prices = self.prices["Time Series (Daily)"]
        req.close()

    def isWarnable(self):
        lastDaySync = self.findLastSyncDate()

        try: 
            refprice  = float(self.prices[lastDaySync.isoformat()]["4. close"])
            bblow     = float(self.bollingers[lastDaySync.isoformat()]["Real Lower Band"])
            bbhi      = float(self.bollingers[lastDaySync.isoformat()]["Real Upper Band"])

            if refprice >= bbhi or refprice <= bblow:
                return True
            else:
                return False
        except KeyError: # if there's no date
            return False # there's nothing to see
        
        
        
    def findLastSyncDate(self):
        today = datetime.date.today()
        for i in range(10): # try 10 different days
            try:
                if self.bollingers[today.isoformat()] and self.prices[today.isoformat()]: # if today is the fetch day
                    return today
            except KeyError: # if not, subtract one day
                today = today - datetime.timedelta(days=1)
