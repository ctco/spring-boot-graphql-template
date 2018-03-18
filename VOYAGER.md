# Intro

Voyager sample contains:
* `voyager.html` the home page of [GraphQL Voyager](https://github.com/APIs-guru/graphql-voyager) which represent any GraphQL API as an interactive graph.
* `lv.ctco.tpl.bff.voyager` configures and serves voyager static content using Web Controller

# Requirements and Downloads

Requirements:
  * Java 1.8
  * Spring Framework Boot > 1.5.7 (web)
  * runtime voyager dependencies are loaded from `//cdn.jsdelivr.net`

# Enable GraphQL Voyager

GraphQL Voyager becomes accessible at the root `/voyager`.

Note that GraphQL server must be available at `/graphql/*` context to be discovered by GraphQL Voyager.

Available Spring Boot configuration parameters (either `application.yml` or `application.properties`):
```yaml
voyager:
    mapping: /voyager
    enabled: true
    pageTitle: Voyager
graphql:
    endpoint: /graphql
```
