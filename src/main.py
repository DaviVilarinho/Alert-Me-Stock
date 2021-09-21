import handler.dbhandler as db
from repository.stocks import Stock
import logging
import json
import datetime
import time

TICK_POS = 1
STOCK_ID_POS = 0
PATH_TO_FILE = "/home/dv/Documents/reports/"

def main():
    logging.basicConfig(filename='/tmp/Alert-Me-Stock.log', level=logging.DEBUG)
    logging.info("Today is " + datetime.date.today().isoformat())
    dbcon = db.DbHandler()

#   with open(PATH_TO_FILE + datetime.date.today().isoformat() + "-report.json", "w") as f: # if we want file
#       logging.info("Succesfully Opened File")

    # start each list empty
    oversold   = []
    overbought = []
    inPrice    = []

    for st in dbcon.getStocks():
        time.sleep(30)
        logging.info("Try warning for: {}".format(st))
        stockObj = Stock(st[STOCK_ID_POS], st[TICK_POS])

        status = stockObj.isWarnable()

        if   status == 1:
            overbought.append(stockObj)
        elif status == -1:
            oversold.append(stockObj)
        else:
            inPrice.append(stockObj)

        dbcon.insertStockStatuses(stockObj)

     
     ## important part if we want to store it afterwards
#   logging.info("Merging all of'em")
#   everything = oversold + overbought + inPrice

#   for st in everything:
#       dbcon.insertStockStatuses(st)

    dbcon.closeConnection()

if __name__ == '__main__':
    main()
