FROM maven:3-jdk-11 as builder
EXPOSE 8080

USER root
RUN apt-get update && mkdir /home/project
WORKDIR /home/project
COPY . /home/project
RUN mvn clean package
ENTRYPOINT java -jar /home/project/target/sample-app-api.jar
