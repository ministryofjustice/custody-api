FROM java
MAINTAINER Mike Jackson <michael.jackson@digital.justice.gov.uk>

COPY build/libs/new-nomis-api*.jar /root/new-nomis-api.jar

ENTRYPOINT ["/usr/bin/java", "-jar", "/root/new-nomis-api.jar"]
