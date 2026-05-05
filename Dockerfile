# Stage 1: Build stage
FROM openjdk:17-jdk-slim AS builder

WORKDIR /app

# Create necessary directories
RUN mkdir -p /app/lib /app/classes

# Copy existing libraries from the project
COPY src/main/webapp/WEB-INF/lib/*.jar /app/lib/

# Download Jakarta Servlet API (needed for compilation on Tomcat 10)
ADD https://repo1.maven.org/maven2/jakarta/servlet/jakarta.servlet-api/5.0.0/jakarta.servlet-api-5.0.0.jar /app/lib/

# Copy source code
COPY src/main/java /app/src

# Compile all Java files
RUN find /app/src -name "*.java" > sources.txt && \
    javac -cp "/app/lib/*" -d /app/classes @sources.txt

# Stage 2: Runtime stage
FROM tomcat:10.1-jdk17-slim

# Remove default Tomcat webapps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy webapp content (JSPs, CSS, etc.) to ROOT
COPY src/main/webapp /usr/local/tomcat/webapps/ROOT

# Copy compiled classes from the builder stage
COPY --from=builder /app/classes /usr/local/tomcat/webapps/ROOT/WEB-INF/classes

# Ensure the lib directory is complete (it was copied with src/main/webapp, but we can be explicit if needed)
# All jars in src/main/webapp/WEB-INF/lib are already in the container's webapps/ROOT/WEB-INF/lib

EXPOSE 8080

CMD ["catalina.sh", "run"]
