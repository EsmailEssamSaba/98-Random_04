FROM openjdk:25-ea-4-jdk-oraclelinux9

WORKDIR /app

COPY target/mini1.jar app.jar

RUN mkdir -p src/main/java/com/example/data/

COPY src/main/java/com/example/data/*.json src/main/java/com/example/data/

RUN chmod -R 777 src/main/java/com/example/data/

EXPOSE 8080

CMD ["java","-jar","app.jar"]