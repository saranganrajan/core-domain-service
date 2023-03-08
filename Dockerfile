FROM adoptopenjdk/openjdk11:alpine-jre
MAINTAINER apps.saranganrajan.com
COPY target/core-domain-service-0.0.1-SNAPSHOT.jar core-domain-service.jar
ENTRYPOINT ["java","-jar","/core-domain-service.jar"]