# Rubrica

## Set-up database

### Obtain the Docker image by executing the following command:

```sh
$ docker pull mysql
```

### Proceed to create a Docker container with the specified parameters

```sh
$ docker run --name rubrica -p 3306:3306 -e MYSQL_ROOT_PASSWORD=rubrica2024 -d mysql:latest
```

### Access the Bash shell within the Docker container

```sh
$ docker exec -it rubrica bash
```

### Finally, log in to the MySQL database with the following command:

```sh
$ mysql -h localhost -u root -p
```

### Create a database with the schema specified in the file schema_database.sql.


## Please amend the database credentials within the file named "credenziali_database.properties".
