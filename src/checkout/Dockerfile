FROM eclipse-temurin:17-jre
COPY ./target/checkout-microservice-0.0.1-SNAPSHOT.jar /
EXPOSE 8086
CMD java -jar /checkout-microservice-0.0.1-SNAPSHOT.jar --server.port=8086 --eureka.instance.preferIpAddress=true --eureka.instance.hostname=checkout-microservice --cronos.yugabyte.hostname=$DB_HOSTNAME



ARG JAVA_VERSION=17

ARG JDK_IMAGE=eclipse-temurin
ARG JDK_IMAGE_TAG=${JAVA_VERSION}-jdk

ARG JRE_IMAGE=eclipse-temurin
ARG JRE_IMAGE_TAG=${JAVA_VERSION}-jre
ARG JAR_FILE=checkout-microservice-0.0.1-SNAPSHOT.jar
ARG APP_NAME=yugastore-checkout
ARG APP_PORT=8085

FROM ${JDK_IMAGE}:${JDK_IMAGE_TAG} as build
RUN mkdir -p /opt/workspace
WORKDIR /opt/workspace
COPY pom.xml ./mvnw ./
COPY .mvn/ .mvn/
RUN ./mvnw -B clean -DskipDockerBuild -DskipTests dependency:resolve-plugins dependency:resolve
ADD . .
RUN ./mvnw -B clean package -DskipDockerBuild -DskipTests

FROM ${JRE_IMAGE}:${JRE_IMAGE_TAG} as main
MAINTAINER YugaByte
ENV container=${APP_NAME}

WORKDIR /opt/yugabyte
COPY --from=build /opt/workspace/target/${JAR_FILE} /opt/yugabyte/
USER nobody

EXPOSE ${APP_PORT}
CMD java -jar /opt/yugabyte/${JAR_FILE}




