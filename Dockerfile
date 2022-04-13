FROM openjdk:8-jre-slim as build

EXPOSE 8080

RUN mkdir /app

COPY --from=build /app/target/spring-petclinic-*.jar /spring-petclinic.jar

CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/spring-petclinic.jar"]
