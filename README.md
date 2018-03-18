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

### Style check

#### IntelliJ

- You can download "CheckStyle plugin". Then open Settings > Other Settings > Checkstyle. Add path to check-style.xml file 
  and provide link to check-style folder in "${configDir}". This will display errors in editor and it wont affect build/run. 

#### Gradle

- As default behaviour it is configured to call "checkstyleMain pmdMain" before "build" task is executed, which does not prevent
 development (application start up and bootrun)

## Develop

1. Create `.env` file 

Create top level `.env` file and add required [key-values](https://docs.oracle.com/cd/E23095_01/Platform.93/ATGProgGuide/html/s0204propertiesfileformat01.html)

e.g.:
```
APP_ICNDB_URL=https://api.icndb.com
LOGGING_LEVEL_feign=DEBUG
LOGGING_LEVEL_graphql=DEBUG
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

- Gradle: `$ gradlew buld`
- Docker: `$ docker build -t spring-boot-graphql-template .`

## Run productive

Assuming that the Docker image is already built on the previous step

- Docker (add `-d` to run in daemon mode): `$ docker run -e 'APP_ICNDB_URL=https://api.icndb.com' -p 8080:8080 spring-boot-graphql-template`

## Tech Stack
- [Spring Boot](https://projects.spring.io/spring-boot/) : Application framework
- [Feign](https://github.com/OpenFeign/feign) : HTTP Client library
  - [Feign-Hystrix](https://github.com/OpenFeign/feign/tree/master/hystrix) : wraps Feign's http requests in Hystrix, which enables the [Circuit Breaker](https://en.wikipedia.org/wiki/Circuit_breaker_design_pattern) pattern. 
- [Lombok](https://projectlombok.org/features/index.html) : Utility library for Java language
- [GraphQL](http://graphql.org/learn/) : API query runtime
  - [GraphQL and GraphiQL Spring Framework Boot Starters](https://github.com/graphql-java/graphql-spring-boot)
  - [GraphQL Java Tools](https://github.com/graphql-java/graphql-java-tools)
- [GraphQL Voyager](https://github.com/APIs-guru/graphql-voyager) : Represent any GraphQL API as an interactive graph. See [Voyager Sample](https://github.com/ctco/spring-boot-graphql-template/blob/master/VOYAGER.md) 
- Docker
  - [Home Page](https://www.docker.com)
  
## Packaging
- Use the [Models and Connectors](https://dev-blog.apollodata.com/how-to-build-graphql-servers-87587591ded5) pattern to structure our GraphQL server code, since it is a well established pattern in GraphQL world. Such approach allows to refer to the existing approach instead of coming up with our own set of guidelines.
  
## Cloud Deployment  
- [Azure](https://github.com/ctco-dev/spring-boot-graphql-template/blob/master/cloud/azure/README.md)

