FROM openjdk:8-jdk-alpine

WORKDIR /app
ADD event-streams-kafka*.jar event-streams-kafka.jar


COPY config.properties config.properties
##ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.config.additional-location=config.properties","-jar","event-streams-kafka.jar"]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","event-streams-kafka.jar"]

## docker build . -t event-streams-kafka:v1