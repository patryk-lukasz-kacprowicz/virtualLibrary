FROM openjdk:21
ARG JAR_FILE=target/*.jar
COPY ./target/virtualLibrary-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]