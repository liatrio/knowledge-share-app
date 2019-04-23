FROM maven:3-jdk-11 as builder
EXPOSE 8080

USER root
RUN apt-get update && mkdir /home/project
WORKDIR /home/project
COPY . /home/project
RUN mvn clean package

FROM java:jre-alpine
WORKDIR /home/project
COPY --from=builder /home/project/target/knowledge-share-app .
ENTRYPOINT java -jar /home/project/knowledge-share-app.jar
