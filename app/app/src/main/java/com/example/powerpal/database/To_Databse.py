import pandas as pd
import sqlite3 as sql
conn = sql.connect('powerpal.db')
df = pd.read_csv('appliance_consumption.csv')
df.to_sql('appliance',conn,index=False)