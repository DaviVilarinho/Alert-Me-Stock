import psycopg2
import handler.credential as crd
import logging

GET_ALL_STOCKS = "SELECT id, tick, isUs FROM stocks"

class DbHandler:
    def __init__(self):
        try:
            self.con = psycopg2.connect("dbname='money' user='dv' password='" + crd.POSTGRES_CREDENTIAL + "'")
            logging.info("Succesfully connected to postgresql")
        except:
            logging.error("Can't connect to postgresql")
        
    def getStocks(self):
        cur = self.con.cursor()
        cur.execute(GET_ALL_STOCKS)
        
        rows = cur.fetchall()
        logging.info("Fetched Stocks: " + rows)

        cur.close()
        return rows

    def closeConnection(self):
        self.con.close()
        logging.info("Closed connection to db")
