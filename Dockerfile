FROM openjdk:8-alpine
EXPOSE 8080
COPY /target/microservicios-0.0.1-SNAPSHOT.jar app-alberto-perez.jar
ENTRYPOINT ["java", "-jar", "app-alberto-perez.jar"]

