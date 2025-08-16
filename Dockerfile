# Imagen del jdk 
FROM openjdk:17-jdk-slim as build
WORKDIR /workspace
COPY . .

# Corre este comando para generar el .jar necesario
RUN ./mvnw clean package


FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/to-dolist-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app_todoList.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app_todoList.jar"]