FROM openjdk:11
COPY . /app
WORKDIR /app
EXPOSE 8081
ENTRYPOINT ["sh", "-c", "java -Dserver.port=8081 -jar target/cloud-message.jar"]