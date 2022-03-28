FROM openjdk:11
MAINTAINER restonov
COPY build/libs/employee-api-1.0.0.jar employee-api-1.0.0.jar
ENTRYPOINT ["java","-jar","/employee-api-1.0.0.jar"]