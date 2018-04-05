# Build
FROM gradle:4.1-jdk8-alpine as build

USER root

WORKDIR /opt/app

COPY settings.gradle .
COPY api ./api

RUN gradle --no-daemon clean build

# Run
FROM openjdk:8-jre-alpine as runtime

WORKDIR /opt/app

COPY --from=build /opt/app/api/build/libs/api.jar .

RUN addgroup -S -g 1001 app \
    && adduser -D -S -G app -u 1001 -s /bin/ash app \
    && chown -R app:app /opt/app

USER app

EXPOSE 8080

CMD ["java", "-jar", "api.jar"]
