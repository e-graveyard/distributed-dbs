#!/usr/bin/env python3

# ALTER USER sa SET PASSWORD 'sa';

import sys
from os import environ

import psycopg2

dbname, user, password, host, port = \
    None, None, None, None, None

try:
    dbname   = environ['H2_DBNAME']
    user     = environ['H2_USERNAME']
    password = environ['H2_PASSWORD']
    host     = environ['H2_HOSTNAME']
    port     = int(environ['H2_PG_PORT'])

except (ValueError, KeyError):
    print('Impossível definir dados a partir do env')
    sys.exit(1)


conn = psycopg2.connect('dbname={} user={} password={} host={} port={}'.format(
    dbname, user, password, host, port
))

cur = conn.cursor()

create_table_statement = ('CREATE TABLE books ('
                          '    isbn CHAR(13) PRIMARY KEY,'
                          '    title VARCHAR(255) NOT NULL,'
                          '    author VARCHAR(50) NOT NULL,'
                          '    publication CHAR(10) NOT NULL,'
                          '    pages SMALLINT NOT NULL'
                          ');')

cur.execute(create_table_statement)

cur.close()
conn.close()
