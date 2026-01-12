FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN chmod +x ./gradlew
RUN ./gradlew clean bootJar

CMD ["java", "-jar", "build/libs/task-backend-0.0.1-SNAPSHOT.jar"]

