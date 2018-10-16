FROM openjdk:8-jdk-alpine
RUN apk add --update \
        curl \
    && rm -rf /var/cache/apk/*
MAINTAINER HMPPS Digital Studio <info@digital.justice.gov.uk>

WORKDIR /app

COPY build/libs/custody-api*.jar /app/app.jar
COPY run.sh /app

ENTRYPOINT ["/bin/sh", "/app/run.sh"]
