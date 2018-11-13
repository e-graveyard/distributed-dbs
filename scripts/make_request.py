#!/usr/bin/env python3

import sys
import json
import socket

HOST = '127.0.0.1'
PORT = int(sys.argv[1])

msg = {
    'kind': 'CreateRecord',
    'envelope': {
        'from': 'Caian'
    },
    'data': {
        'title': '1984',
        'author': 'George Orwell',
        'publication': '25-12-1997',
        'isbn': '1234567890980',
        'pages': 300
    }
}

tcp = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

tcp.connect((HOST, PORT))
tcp.send(bytes(json.dumps(msg), 'utf8'))
tcp.close()
