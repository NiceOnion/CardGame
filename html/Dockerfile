FROM openjdk:8-jre-slim as production

EXPOSE 8080

RUN mkdir /app

COPY --chown=NiceOnion --from=production /app/target/spring-petclinic-*.jar /spring-petclinic.jar

CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/spring-petclinic.jar"]
