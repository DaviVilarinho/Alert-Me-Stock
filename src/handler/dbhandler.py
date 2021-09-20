import psycopg2
import handler.credential as crd

GET_ALL_STOCKS = "SELECT id, tick, isUs FROM stocks"

class DbHandler:
    def __init__(self):
        self.con = psycopg2.connect("dbname='money' user='dv' password='" + crd.POSTGRES_CREDENTIAL + "'")
        
    def getStocks(self):
        cur = self.con.cursor()
        cur.execute(GET_ALL_STOCKS)
        
        rows = cur.fetchall()

        cur.close()
        return rows

    def closeConnection(self):
        self.con.close()
