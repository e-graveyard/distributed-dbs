#!/usr/bin/env python3

import sys
import json
import socket

HOST = '127.0.0.1'
PORT = int(sys.argv[1])

msg = {
    'kind': 'ReadRecord',
    'envelope': {
        'from': 'Caian'
    },
    'data': {
        'isbn': '1234567890990'
    }
}

tcp = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

tcp.connect((HOST, PORT))
tcp.send(bytes(json.dumps(msg), 'utf8'))
tcp.send(bytes('\n', 'utf-8'))

data = tcp.recv(4096).decode('UTF-8')
data = json.loads(data)

print(json.dumps(data, indent=4))

tcp.close()
