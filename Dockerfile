FROM openjdk:25-ea-4-jdk-oraclelinux9

WORKDIR /app

COPY target/ target/

RUN mkdir -p /app/src/main/java/com/example/data

VOLUME ["/app/src/main/java/com/example/data"]

EXPOSE 8080

CMD ["java","-jar","target/mini1.jar"]