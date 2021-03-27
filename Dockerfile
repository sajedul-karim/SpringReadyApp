FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} springreadyapp.jar

ENTRYPOINT ["java","-jar","/springreadyapp.jar"]