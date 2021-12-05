# Cities API

## API documentation:

https://wkcitiesapi.herokuapp.com/swagger-ui.html



## Requirements

* Linux
* Git
* Java 8
* Docker
* IntelliJ Community
* Heroku CLI

## DataBase

### `Postgres`

* [Postgres Docker Hub](https://hub.docker.com/_/postgres)

```shell script
docker run --name cities-db -d -p 5432:5432 -e POSTGRES_USER=postgres_user_city -e POSTGRES_PASSWORD=super_password -e POSTGRES_DB=cities postgres
```

### Populate data

* [Github com dados para popular banco de dados](https://github.com/chinnonsantos/sql-paises-estados-cidades/tree/master/PostgreSQL) 



#### Comando docker para executar shell do container Postgres:

```shell script
cd ~/workspace/sql-paises-estados-cidades/PostgreSQL

docker run -it --rm --net=host -v $PWD:/tmp postgres /bin/bash

```

* To run an image inside of a container, we use the `docker run` command. 

* For interactive processes (like a shell), you must use `-i -t` together in order to allocate a tty (terminal) for the container process. `-i -t` is often written `-it` as you’ll see in later examples.
* The `--net=host` option is used to make the programs inside the Docker container look like they are running on the host itself, from the perspective of the network. It allows the container greater network access than it can normally get.
* PWD is an environment variable that your shell will expand to your current working directory. So in this example, it would mount the current working directory, from where you are executing this command, to /tmp inside your container.
* `postgres` é o nome da imagem que será executada no container
* `/bin/bash` serve para especificar que você quer um shell ( interpretador de comandos do linux)



#### Comandos para serem executados dentro do container docker com o fim de criar as tabelas:

```
psql -h localhost -U postgres_user_city cities -f /tmp/pais.sql
psql -h localhost -U postgres_user_city cities -f /tmp/estado.sql
psql -h localhost -U postgres_user_city cities -f /tmp/cidade.sql
```

* `-h *hostname*`  Specifies the host name of the machine on which the server is running.
* `-U *username*`Connect to the database as the user *`username`* instead of the default. (You must have permission to do so, of course.)
* `cities` É o nome do banco de dados
* `-f *filename*` Read commands from the file *`filename`*, rather than standard input.



```
psql -h localhost -U postgres_user_city cities
```

* O comando acima faz com que você entre dentro do banco de dados `cities`
* Experimente escrever o comando: `select * from pais;`



#### Extensões do Postgres para cálculo de distância entre duas localizações 

```
CREATE EXTENSION cube; 
CREATE EXTENSION earthdistance;
```



* [Postgres Earth distance](https://www.postgresql.org/docs/current/earthdistance.html)
* [earthdistance--1.0--1.1.sql](https://github.com/postgres/postgres/blob/master/contrib/earthdistance/earthdistance--1.0--1.1.sql)
* [OPERATOR <@>](https://github.com/postgres/postgres/blob/master/contrib/earthdistance/earthdistance--1.1.sql)
* [postgrescheatsheet](https://postgrescheatsheet.com/#/tables)
* [datatype-geometric](https://www.postgresql.org/docs/current/datatype-geometric.html)

### Access

```shell script
docker exec -it cities-db /bin/bash

psql -U postgres_user_city cities
```

### Query Earth Distance

Point

```roomsql
select ((select lat_lon from cidade where id = 4929) <@> (select lat_lon from cidade where id=5254)) as distance;
```

Cube

```roomsql
select earth_distance(
    ll_to_earth(-21.95840072631836,-47.98820114135742), 
    ll_to_earth(-22.01740074157715,-47.88600158691406)
) as distance;
```

## Spring Boot

* [https://start.spring.io/](https://start.spring.io/)

+ Java 8
+ Gradle Project
+ Jar
+ Spring Web
+ Spring Data JPA
+ PostgreSQL Driver

### Spring Data

* [jpa.query-methods](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods)

### Properties

* [appendix-application-properties](https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html)
* [jdbc-database-connectio](https://www.codejava.net/java-se/jdbc/jdbc-database-connection-url-for-common-databases)

### Types

* [JsonTypes](https://github.com/vladmihalcea/hibernate-types)
* [UserType](https://docs.jboss.org/hibernate/orm/3.5/api/org/hibernate/usertype/UserType.html)

## Heroku

* [DevCenter](https://devcenter.heroku.com/articles/getting-started-with-gradle-on-heroku)

## Code Quality

### PMD

+ https://pmd.github.io/pmd-6.8.0/index.html

### Checkstyle

+ https://checkstyle.org/

+ https://checkstyle.org/google_style.html

+ http://google.github.io/styleguide/javaguide.html

```shell script
wget https://raw.githubusercontent.com/checkstyle/checkstyle/master/src/main/resources/google_checks.xml
```
