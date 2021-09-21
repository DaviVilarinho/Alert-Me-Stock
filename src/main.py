import handler.dbhandler as db
from repository.stocks import Stock
import logging
import datetime
import time

TICK_POS = 1

def main():
    logging.basicConfig(filename='/tmp/Alert-Me-Stock.log', level=logging.DEBUG)
    logging.info("Today is " + datetime.date.today().isoformat())
    dbcon = db.DbHandler()

    for st in dbcon.getStocks():
        time.sleep(30)
        logging.info("Try warning for: {}".format(st))
        stockObj = Stock(st[TICK_POS])
        if stockObj.isWarnable():
            print(stockObj)


    dbcon.closeConnection()

if __name__ == '__main__':
    main()
