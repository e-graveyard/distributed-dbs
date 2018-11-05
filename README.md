# APS: Sistemas Distribuídos


## Dependências

- `java`
- `maven`

### Variáveis de ambiente

- `export JAVA_HOME="/usr/lib/jvm/java-1.8.0-openjdk-amd64"`
- `export MAVEN_HOME="/etc/maven"`
- `source .env`


## Uso

Targets possíveis:

- `client`
- `server`
- `controller`

### Build

```sh
$ make build target=x
```

### Execução

```sh
$ make run target=x [args, ...]
```

### Banco de dados

```sh
$ make db
```
