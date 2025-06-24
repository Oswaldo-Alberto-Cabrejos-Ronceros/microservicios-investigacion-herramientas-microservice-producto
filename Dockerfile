#traemos la imagen de jdk
FROM openjdk:17-jdk-alpine
#creamos directorio en el contenedor
WORKDIR /app
#copeamos el jar del target
COPY target/microservice-producto-0.0.1-SNAPSHOT.jar producto-service-app.jar
#exponemos port informativo
EXPOSE 8080
#comando para ejecutar el proyecto
ENTRYPOINT ["java","-jar","producto-service-app.jar"]
