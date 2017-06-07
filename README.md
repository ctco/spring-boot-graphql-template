[![Build Status](https://travis-ci.org/ctco-dev/spring-boot-graphql-template.svg?branch=master)](https://travis-ci.org/ctco-dev/spring-boot-graphql-template)
# spring-boot-graphql-template

Spring Boot, GraphQL template project with batteries included.

## Features


## Required Software
- JDK 1.8

## Lombok configuration

### IntelliJ 

- Download and install Lombok [plugin](https://plugins.jetbrains.com/plugin/6317-lombok-plugin)
- Enable Annotation Processors
  -  Go to Setting->Build, Execution, Deployment-> Compiler->Annotation Processors
  -  Check _Enable annotation processing_
  
## GraphQL Tools
  
### IntelliJ

- Download and install GraphQLJs [plugin](https://plugins.jetbrains.com/plugin/8097-js-graphql)

## Development Mode

`$ gradlew bootRun`

## Run unit tests

`$ gradlew test`

## Build project artifacts

`$ gradlew buld`

# Tech Stack
- [Spring Boot](https://projects.spring.io/spring-boot/) : Application framework
- [Feign](https://github.com/OpenFeign/feign) : HTTP Client library
- [Lombok](https://projectlombok.org/features/index.html) : Utility library for Java language
- [GraphQL](http://graphql.org/learn/) : API query runtime
  - [GraphQL and GraphiQL Spring Framework Boot Starters](https://github.com/graphql-java/graphql-spring-boot)
  - [GraphQL Java Tools](https://github.com/graphql-java/graphql-java-tools)
