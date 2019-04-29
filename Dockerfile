FROM maven:3-jdk-8 as builder
EXPOSE 8080

USER root
RUN apt-get update && mkdir /app
WORKDIR /app

# Keep maven dependencies in separate image layer
COPY pom.xml .
RUN mvn dependency:resolve dependency:resolve-plugins -B

# Copy in the src and build
COPY src ./src
RUN mvn clean package -B

FROM openjdk:8-jre-stretch
WORKDIR /app

COPY --from=builder /app/target/knowledge-share-app.jar .
ENTRYPOINT ["java","-jar","knowledge-share-app.jar"]
EXPOSE 8080