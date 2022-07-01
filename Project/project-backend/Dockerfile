FROM maven:3-amazoncorretto-17 as builder

COPY src /usr/src/app/src
COPY pom.xml /usr/src/app

RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests

FROM amazoncorretto:17

COPY --from=builder /usr/src/app/target/project-backend-*.jar /usr/app/project-backend.jar

ARG PORT=8080
ENV PORT=${PORT}

ARG PICTURE_CACHE=/tmp
ENV PICTURE_CACHE=${PICTURE_CACHE}

ENTRYPOINT ["java", "-cp", "/usr/app/project-backend.jar", "com.hunludvig.Application"]