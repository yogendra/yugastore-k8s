ARG JAVA_VERSION=17

ARG JDK_IMAGE=eclipse-temurin
ARG JDK_IMAGE_TAG=${JAVA_VERSION}-jdk

ARG JRE_IMAGE=eclipse-temurin
ARG JRE_IMAGE_TAG=${JAVA_VERSION}-jre
ARG JAR_FILE=api-gateway-0.0.1-SNAPSHOT.jar
ARG APP_NAME=yugastore-api-gateway
ARG APP_PORT=8081

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

ENV APP_SERVICES_PRODUCTS_URI=http://products:8082
ENV APP_SERVICES_CART_URI=http://cart:8083
ENV APP_SERVICES_CHECKOUT_URI=http://checkout:8086
ENV APP_SERVICES_LOGIN_URI=http://login:8085

EXPOSE ${APP_PORT}
ENTRYPOINT ["/usr/bin/java", "-jar", "/opt/yugabyte/${JAR_FILE}"]
