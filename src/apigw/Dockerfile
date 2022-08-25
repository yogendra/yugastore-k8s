ARG JAVA_VERSION=17

ARG JDK_IMAGE=eclipse-temurin
ARG JDK_IMAGE_TAG=${JAVA_VERSION}-jdk

ARG JRE_IMAGE=eclipse-temurin
ARG JRE_IMAGE_TAG=${JAVA_VERSION}-jre
ARG JAR_FILE=apigw-0.0.1-SNAPSHOT.jar
ARG APP_NAME=yugastore-api-gateway

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

ENV SERVER_PORT=8080
ENV APP_SERVICES_PRODUCTS_URI=http://products:8080
ENV APP_SERVICES_CART_URI=http://cart:8080
ENV APP_SERVICES_CHECKOUT_URI=http://cart:8080
ENV APP_SERVICES_LOGIN_URI=http://login:8080

EXPOSE 8080
ENTRYPOINT ["/usr/bin/java", "-jar", "/opt/yugabyte/${JAR_FILE}"]
