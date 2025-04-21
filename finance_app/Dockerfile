FROM maven:3.9.5-eclipse-temurin-17 AS build

RUN apt-get update && apt-get install -y \
    libxext6 \
    libxrender1 \
    libxtst6 \
    libxi6 \
    libxrandr2 \
    libxinerama1 \
    libfreetype6 \
    libx11-6 \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY target/classes/db/finance.db finance.db

COPY target/finance_app-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
