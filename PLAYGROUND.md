# Intro

Voyager sample contains:
* `playground.html` the home page of [GraphQL Playground](https://github.com/graphcool/graphql-playground) - GraphQL IDE for better development workflows (GraphQL Subscriptions, interactive docs & collaboration).
* `lv.ctco.tpl.bff.playground` configures and serves playground static content using Web Controller

# Requirements and Downloads

Requirements:
  * Java 1.8
  * Spring Framework Boot > 1.5.7 (web)
  * runtime playground dependencies are loaded from `//cdn.jsdelivr.net`

# Enable GraphQL Playground

By default, GraphQL Playground becomes accessible at the root `/playground`.

By default, GraphQL server must be available at `/graphql/*` context to be discovered by GraphQL Playground.

GraphQL Playground root and GraphQL server context could be overwritten.  
Available Spring Boot configuration parameters (either `application.yml` or `application.properties`):
```yaml
playground:
    mapping: /playground
    enabled: true
    pageTitle: Playground
    endpoint: /graphql
```
