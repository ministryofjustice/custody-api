FROM openjdk:8-jdk-alpine
RUN apk add --update \
        curl \
    && rm -rf /var/cache/apk/*
MAINTAINER Mike Jackson <michael.jackson@digital.justice.gov.uk>

COPY build/libs/new-nomis-api*.jar /root/new-nomis-api.jar

ENTRYPOINT ["/usr/bin/java", "-jar", "/root/new-nomis-api.jar"]
