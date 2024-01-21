# Stage 1: Build the Java microservice
FROM khipu/openjdk17-alpine AS builder

WORKDIR /app

COPY target/user-info-service-0.0.1-SNAPSHOT.jar /app/

CMD ["java", "-jar", "/app/user-info-service-0.0.1-SNAPSHOT.jar"]



