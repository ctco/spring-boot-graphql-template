[![Build Status](https://travis-ci.org/ctco-dev/spring-boot-graphql-template.svg?branch=master)](https://travis-ci.org/ctco-dev/spring-boot-graphql-template)

# spring-boot-graphql-template

Spring Boot, GraphQL template project with batteries included.

## Features
​
- Type safe Configuration with [dotenv](https://12factor.net/config)
  - Logging level, e.g. `LOGGING_LEVEL_feign=DEBUG`
  - App properties, e.g. `APP_DEPENDENCY_API_HOST=example.com`
  - See [Spring Boot documentation on relaxed property binding](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html#boot-features-external-config-relaxed-binding)
- Docker :whale: configuration for production deployment, development and test
  - Remote debugging for development mode

## Required Software
- JDK 1.8
- Or docker + docker-compose

### Lombok

#### IntelliJ 

- Download and install Lombok [plugin](https://plugins.jetbrains.com/plugin/6317-lombok-plugin)
- Enable Annotation Processors
  -  Go to Setting->Build, Execution, Deployment-> Compiler->Annotation Processors
  -  Check _Enable annotation processing_
  
### GraphQL Tools
  
#### IntelliJ

- Download and install GraphQLJs [plugin](https://plugins.jetbrains.com/plugin/8097-js-graphql)

## Develop

1. Create `.env` file 

Create top level `.env` file and add required [key-values](https://docs.oracle.com/cd/E23095_01/Platform.93/ATGProgGuide/html/s0204propertiesfileformat01.html)

e.g.:
```
APP_ICNDB_URL=https://api.icndb.com
LOGGING_LEVEL_feign=DEBUG
```

2.1. Run

- Gradle: `$ gradlew bootRun`
- Docker: `$ docker-compose up` (or `$ docker-compose up --build` if image should be rebuilt)

2.2. Debug

Run remote debugger from IDE. Debug port is 5005

## Test

- Gradle: `$ gradlew test`
- Docker: `$ docker-compose -f docker-compose.test.yml up --build`

## Build

`$ gradlew buld`

## Tech Stack
- [Spring Boot](https://projects.spring.io/spring-boot/) : Application framework
- [Feign](https://github.com/OpenFeign/feign) : HTTP Client library
- [Lombok](https://projectlombok.org/features/index.html) : Utility library for Java language
- [GraphQL](http://graphql.org/learn/) : API query runtime
  - [GraphQL and GraphiQL Spring Framework Boot Starters](https://github.com/graphql-java/graphql-spring-boot)
  - [GraphQL Java Tools](https://github.com/graphql-java/graphql-java-tools)
- Docker
  - [Home Page](https://www.docker.com)
  
## Cloud Deployment  

- [Azure](https://github.com/ctco-dev/spring-boot-graphql-template/blob/master/cloud/azure/README.md)

