FROM maven:3-jdk-11 as builder
EXPOSE 8080

USER root
RUN apt-get update && mkdir /home/project
WORKDIR /home/project

# Keep maven dependencies in separate image layer
COPY pom.xml /home/project
RUN mvn dependency:resolve dependency:resolve-plugins

# Copy in the src and build
COPY . /home/project
ARG SONAR_LOGIN
RUN mvn clean package -Dsonar.login=${SONAR_LOGIN}

FROM java:jre-alpine
WORKDIR /home/project
COPY --from=builder /home/project/target/knowledge-share-app.jar .
ENTRYPOINT java -jar /home/project/knowledge-share-app.jar
