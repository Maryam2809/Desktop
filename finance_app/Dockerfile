FROM maven:3.9.5-eclipse-temurin-17 AS build

WORKDIR /app

COPY target/classes/db/finance.db finance.db

COPY target/finance_app-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
