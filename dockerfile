# ---- Stage 1: Build frontend ----
FROM node:20 AS build-frontend
WORKDIR /app/frontend
COPY frontend/package*.json ./
RUN npm install
COPY frontend/ ./
RUN npm run build

# ---- Stage 2: Build backend ----
FROM maven:3.9.3-eclipse-temurin-20 AS build-backend
WORKDIR /app/backend
COPY backend/pom.xml ./
RUN mvn dependency:go-offline
COPY backend/ ./
# Copy built frontend assets into backend static folder
COPY --from=build-frontend /app/frontend/dist/assets src/main/resources/static/assets
RUN mvn clean package -DskipTests

# ---- Stage 3: Run ----
FROM eclipse-temurin:20-jdk-jammy
WORKDIR /app
COPY --from=build-backend /app/backend/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
