# spec

## Ping

```json
{
    "kind": "Ping",
    "envelope": {
        "from": "client-123"
    }
}
```

### Resposta

```json
{
    "kind": "Response",
    "envelope": {
        "from": "server-777",
        "to": "client-123"
    },
    "data": {
        "message": "pong"
    },
    "success": true
}
```

## Cadastrar livro

```json
{
    "kind": "CreateRecord",
    "envelope": {
        "from": "client-123"
    },
    "data": {
        "title": "Nineteen Eighty Four",
        "publication": "8-6-1949",
        "author": "George Orwell",
        "pages": "326",
        "isbn": "9788535914849"
    }
}
```

### Resposta

```json
{
    "kind": "Response",
    "envelope": {
        "from": "server-777",
        "to": "client-123"
    },
    "data": {
        "message": "Book registered"
    },
    "success": true
}
```

### Resposta de erro

```json
{
    "kind": "Response",
    "envelope": {
        "from": "server-777",
        "to": "client-123"
    },
    "data": {
        "message": "Unable to register the book"
    },
    "success": false
}
```


## Ler dados de um livro a partir do ISBN

```json
{
    "kind": "ReadRecord",
    "envelope": {
        "from": "client-123"
    },
    "data": {
        "isbn": "123456"
    }
}
```

### Resposta

```json
{
    "kind": "Response",
    "envelope": {
        "from": "server-777",
        "to": "client-123"
    },
    "data": {
        "title": "Nineteen Eighty Four",
        "publication": "8-6-1949",
        "author": "George Orwell",
        "pages": "326",
        "isbn": "9788535914849",
        "message": "Book information readed"
    },
    "success": true
}
```

### Resposta de erro

```json
{
    "kind": "Response",
    "envelope": {
        "from": "server-777",
        "to": "client-123"
    },
    "data": {
        "message": "Unable to read the register of the book"
    },
    "success": false
}
```


## Atualizar registro do livro

```json
{
    "kind": "UpdateRecord",
    "envelope": {
        "from": "client-123"
    },
    "data": {
        "title": "Nineteen Eighty Four",
        "publication": "8-6-1949",
        "author": "George Orwell",
        "pages": "326",
        "isbn": "9788535914849"
    }
}
```

### Resposta

```json
{
    "kind": "Response",
    "envelope": {
        "from": "server-777",
        "to": "client-123"
    },
    "data": {
        "message": "Book information updated"
    },
    "success": true
}
```

### Resposta de erro

```json
{
    "kind": "Response",
    "envelope": {
        "from": "server-777",
        "to": "client-123"
    },
    "data": {
        "message": "Unable to update the register of the book"
    },
    "success": false
}
```


## Remover registro do livro a partir do ISBN

```json
{
    "kind": "DeleteRecord",
    "envelope": {
        "from": "client-123"
    },
    "data": {
        "isbn": "123456"
    }
}
```

### Resposta

```json
{
    "kind": "Response",
    "envelope": {
        "from": "server-777",
        "to": "client-123"
    },
    "data": {
        "message": "Book information deleted"
    },
    "success": true
}
```

### Resposta de erro

```json
{
    "kind": "Response",
    "envelope": {
        "from": "server-777",
        "to": "client-123"
    },
    "data": {
        "message": "Unable to delete the register of the book"
    },
    "success": false
}
```
