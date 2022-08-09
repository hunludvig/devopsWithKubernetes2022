FROM maven:3-amazoncorretto-17 as builder

COPY pom.xml /usr/src/app/pom.xml
RUN mvn -f /usr/src/app/pom.xml dependency:go-offline --batch-mode

COPY src /usr/src/app/src
RUN mvn -f /usr/src/app/pom.xml package -DskipTests

FROM ghcr.io/graalvm/native-image:22.1.0 as graalvm

COPY --from=builder /usr/src/app/target/PingPong-*.jar /app/PingPong.jar

RUN native-image --no-fallback -jar /app/PingPong.jar

FROM alpine:latest
RUN apk add gcompat

COPY --from=graalvm /app/PingPong /app/PingPong

ENTRYPOINT ["/app/PingPong"]