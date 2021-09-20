import handler.dbhandler as db

def main():
    dbcon = db.DbHandler()

    print(dbcon.getStocks())
    dbcon.closeConnection()

if __name__ == '__main__':
    main()
