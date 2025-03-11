FROM openjdk:25-ea-4-jdk-oraclelinux9

WORKDIR /app

COPY target/ target/

EXPOSE 8080

CMD ["java","-jar","target/mini1.jar"]