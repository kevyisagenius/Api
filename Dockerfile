        # Stage 1: Build the application
        FROM maven:3.8.5-openjdk-17 AS build
        WORKDIR /app
        COPY pom.xml .
        # Optional: If you have a multi-module project and backend is in a subfolder
        # COPY backend/pom.xml ./backend/
        # COPY backend/src ./backend/src
        # RUN mvn -f ./backend/pom.xml clean package -DskipTests
        # --- OR if backend is at the root of the 'Api' repo ----
        COPY src ./src
        RUN mvn clean package -DskipTests

        # Stage 2: Create the runtime image
        FROM eclipse-temurin:17-jre-alpine # Or your preferred JRE, e.g., eclipse-temurin:21-jre
        WORKDIR /app
        # Adjust the path to the JAR based on your Maven build output and module structure
        # If backend is a module, it might be backend/target/*.jar
        COPY --from=build /app/target/*.jar app.jar
        EXPOSE 8080
        ENTRYPOINT ["java","-jar","app.jar"]
