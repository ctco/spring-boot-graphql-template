# Building the image
FROM gradle:4.1-jdk8-alpine as build

# Gradle image creates a 'gradle' user, so fallback to 'root' to do initial setup
USER root

RUN mkdir -p /opt/app
WORKDIR /opt/app

COPY settings.gradle .
COPY api ./api

# Assemble artifact
RUN gradle clean build


# Run the application in a small container
FROM openjdk:8-jre-alpine as runtime

RUN mkdir -p /opt/app

WORKDIR /opt/app

# Take artifact from previous step
COPY --from=build /opt/app/api/build/libs/api.jar .

# Create new user to run application on behalf of
RUN addgroup -S -g 1001 app \
    && adduser -D -S -G app -u 1001 -s /bin/ash app \
    && chown -R app:app /opt/app

USER app

EXPOSE 8080

# Run as plain jar file
CMD ["java", "-jar", "api.jar"]
