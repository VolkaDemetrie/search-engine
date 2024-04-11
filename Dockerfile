FROM azul/zulu-openjdk-alpine:17.0.10-jdk AS builder
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew clean
RUN ./gradlew bootJar

FROM azul/zulu-openjdk-alpine:17.0.10-jdk
COPY --from=builder build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
VOLUME /tmp
