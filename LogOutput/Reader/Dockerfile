FROM maven:3-amazoncorretto-17 as builder

COPY src /usr/src/app/src
COPY pom.xml /usr/src/app

RUN mvn -f /usr/src/app/pom.xml clean package

FROM amazoncorretto:17

COPY --from=builder /usr/src/app/target/Reader-*.jar /usr/app/Reader.jar

ARG PORT=8080
ENV PORT=${PORT}

ARG TS_PATH=/timestamp.data
ENV TS_PATH=${TS_PATH}

ENTRYPOINT ["java", "-cp", "/usr/app/Reader.jar", "com.hunludvig.Application"]