# spring-boot-graphql-template

Spring Boot, GraphQL template project with batteries included.

## Features


## Required Software
- JDK 1.8
- IntelliJ IDEA

## Lombok configuration

### IntelliJ 

- Download and install Lombok [plugin](https://plugins.jetbrains.com/plugin/6317-lombok-plugin)
- Enable Annotation Processors
  -  Go to Setting->Build, Execution, Deployment-> Compiler->Annotation Processors
  -  Check _Enable annotation processing_

## Development Mode

`$ gradlew bootRun`

## Run unit tests

`$ gradlew test`

## Build project artifacts

`$ gradlew buld`

## Start devmode

`$ gradlew build`

# Tech Stack
- [Spring Boot](https://projects.spring.io/spring-boot/) : Application framework
- [Feign](https://github.com/OpenFeign/feign) : HTTP Client library
- [Lombok](https://projectlombok.org/features/index.html) : Utility library for Java language
- [Logback](http://logback.qos.ch/) : Logging library
