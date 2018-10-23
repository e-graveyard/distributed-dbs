###Dependencias:

-  `java -version`
- openjdk version "1.8.0_181" OpenJDK Runtime Environment (build 1.8.0_181-8u181-b13-1~deb9u1-b13) OpenJDK 64-Bit Server VM (build 25.181-b13, mixed mode)

- `mvn -v`
- Apache Maven 3.5.4 (1edded0938998edf8bf061f1ceb3cfdeccf443fe; 2018-06-17T15:33:14-03:00) Maven home: /opt/maven/apache-maven-3.5.4 Java version: 1.8.0_181, vendor: Oracle Corporation, runtime: /usr/lib/jvm/java-8-openjdk-amd64/jre Default locale: pt_BR, platform encoding: UTF-8 OS name: "linux", version: "4.9.0-8-amd64", arch: "amd64", family: "unix"

###necessario configurar variaveis de ambiente:

- - `export JAVA_HOME="/usr/lib/jvm/java-1.8.0-openjdk-amd64"`
- - `export MAVEN_HOME="/etc/maven"`

#Rodando o projeto

##Para rodar o projeto:
- - `cd ${Pasta-raiz-do-projeto}/${pasta-da-aplicação}`
- - `mvn compile exec:java -Dexec.mainClass="com.${client|controllerSistem|server}.App" -Dexec.args="helloArgs HelloArgs"`
  
