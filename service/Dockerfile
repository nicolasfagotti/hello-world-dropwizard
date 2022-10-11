FROM openjdk:17-alpine
MAINTAINER nfagotti

WORKDIR /var/hello-world

ADD target/service-1.0-SNAPSHOT.jar /var/hello-world/service-1.0-SNAPSHOT.jar
ADD src/main/resources/local-dev.yaml /var/hello-world/local-dev.yaml

EXPOSE 8882 8883

ENTRYPOINT ["java", "-jar", "service-1.0-SNAPSHOT.jar", "server", "local-dev.yaml"]
