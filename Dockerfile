FROM openjdk:11
COPY . /app
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -jar target/cloud-message.jar"]