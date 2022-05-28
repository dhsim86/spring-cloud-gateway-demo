FROM eclipse-temurin:17-jre
MAINTAINER dhsim86@gmail.com

ENV BINARY_NAME="spring-cloud-gateway-demo"

RUN mkdir /opt/app

COPY target/${BINARY_NAME}-*.jar /opt/app/app.jar
CMD ["java", "-jar", "/opt/app/app.jar"]

EXPOSE 80