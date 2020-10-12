# ========== Build frontend ==========
FROM node:14 as build-frontend

RUN apt-get update && \
    apt-get install -y --no-install-recommends protobuf-compiler

COPY frontend/yarn.lock app/yarn.lock
COPY frontend/package.json app/package.json
RUN yarn --cwd app install

COPY frontend app
COPY proto proto

RUN yarn --cwd app codegen
RUN yarn --cwd app build

# ========== Build backend ==========
FROM mozilla/sbt:8u232_1.3.13 as build-backend

COPY backend app
COPY proto proto
WORKDIR app

RUN sbt clean assembly

# ========== Build final app ==========
FROM openjdk:11

COPY --from=build-backend app/target/scala-2.13/server.jar .
COPY --from=build-frontend /app/build/ static

CMD ["java", "-jar", "server.jar"]
