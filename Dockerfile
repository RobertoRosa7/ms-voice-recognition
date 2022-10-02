FROM openjdk:11
COPY . /app
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -jar cloud-message.jar"]