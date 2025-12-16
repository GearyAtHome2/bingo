# Build stage
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]


# Build frontend
WORKDIR /app/frontend
RUN npm install
RUN npm run build

# Copy frontend assets into Spring Boot static folder
COPY --from=build-frontend /app/frontend/dist/assets /app/backend/src/main/resources/static/assets