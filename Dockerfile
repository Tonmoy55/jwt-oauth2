FROM openjdk:17-oracle AS Build

WORKDIR /app
COPY target/jwt-oauth2.jar /app/jwt-oauth2.jar
EXPOSE 8181

# Set environment variables for MySQL connection
ENV MYSQL_DATABASE=jwt-oauth2
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root
ENV MYSQL_URL=jdbc:mysql://localhost:3306/jwt-oauth2?useSSL=false&serverTimezone=UTC

ENTRYPOINT ["java","-jar","/app/jwt-oauth2.jar"]
