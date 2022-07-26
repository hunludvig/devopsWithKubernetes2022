FROM maven:3-amazoncorretto-17 as builder

COPY pom.xml /usr/src/app/pom.xml
RUN mvn -f /usr/src/app/pom.xml dependency:go-offline --batch-mode

COPY src /usr/src/app/src
RUN mvn -f /usr/src/app/pom.xml package -DskipTests

FROM amazoncorretto:17

COPY --from=builder /usr/src/app/target/project-backend-*.jar /usr/app/project-backend.jar

ENTRYPOINT ["java", "-cp", "/usr/app/project-backend.jar", "com.hunludvig.Application"]
