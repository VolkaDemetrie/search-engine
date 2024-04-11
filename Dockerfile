FROM azul/zulu-openjdk-alpine:17.0.10-jdk
ARG JAR_PATH=./build/libs
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew clean build
COPY config/application-dev.yaml ./application-dev.yaml
COPY ${JAR_PATH}/*.jar ./app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]