FROM adoptopenjdk/openjdk11:alpine as jdk
WORKDIR /project

COPY .mvn/ /project/.mvn
COPY mvnw pom.xml /project/
COPY src/ /project/src

RUN ./mvnw package --show-version --batch-mode --quiet -DskipTests

ENTRYPOINT ["./mvnw"]
EXPOSE 8100 8101

FROM adoptopenjdk/openjdk11:alpine-jre as jre
LABEL maintainer=thijs \
      project-name="project service" \
      project=bouwhulp

WORKDIR /project
COPY --from=jdk /project/target/*.jar /project/app.jar

RUN addgroup --system --gid 1000 appuser && adduser --system --uid 1000 --ingroup appuser appuser
USER 1000:1000

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
EXPOSE 8080 8081