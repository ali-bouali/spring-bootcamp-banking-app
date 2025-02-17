# Use the official OpenJDK image
FROM openjdk:17
WORKDIR /app

# Copy the built JAR file into the container
COPY target/banking-app-0.0.1-SNAPSHOT.jar banking-app-0.0.1-SNAPSHOT.jar

# Run the Java application
CMD ["java", "-jar", "banking-app-0.0.1-SNAPSHOT.jar"]

# Expose port 8080 (or your app's port)
EXPOSE 8080