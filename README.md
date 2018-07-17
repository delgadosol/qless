# Qless challenge

Produce a working microservice that adheres to the Swagger definition and source data attached.

## Solution

Spring boots application using [SpringFox](http://springfox.io), a module for enabling Swagger on Spring MVC projects.


TECH NOTE: This is a initial version of the microservice, there are some filter that are not implemented yet. 
## Requirements

- [Java 8](https://java.com/en/download/)
- [Maven](https://maven.apache.org/)

## Compilation

To compile the code run:

```bash
$ mvn package 
```

## Run

Once compiled it should have a target directory. Go to that directory an execute the following command:

```bash
$ java -jar challenge-0.0.1-SNAPSHOT.jar
```

Alternatively you can use the Spring Boot Maven plugin:

```bash
$ mvn spring-boot:run
```

To view the generated Swagger UI documentation go to: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)