FROM maven:3-amazoncorretto-17 as builder

COPY src /usr/src/app/src
COPY pom.xml /usr/src/app

RUN mvn -f /usr/src/app/pom.xml clean package

FROM amazoncorretto:17

COPY --from=builder /usr/src/app/target/Forklift-*.jar /usr/app/Forklift.jar

ENTRYPOINT ["java", "-jar", "/usr/app/Forklift.jar"]