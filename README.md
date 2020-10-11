# Fullstack Template

This repo contains a starting point for fullstack applications.
It has a backend server written in Scala, a React frontend and communication through grpc-web.

This repo comes with some repetitive tasks associated with setting up a new project preset and should help you get
up and running quickly.

This repo gives you the following:

## Backend

* An akka-grpc server to handle grpc-web requests.
* Logging.
* Config parsing.
* Grpc code generation.
* Building an assembly jar.
* Code linting and formatting (`scalafmt`).
* Testing (TODO).

## Frontend

* A React app in Typescript (based on `create-react-app`).
* Logging to backend.
* Grpc code generation.
* Code linting and formatting (`eslint` + `prettier`).
* Building a production bundle.
* Testing (TODO).

## Shared

* A directory where protobuf definitions can be placed.
* Makefile with common commands.
* A Docker build to serve the whole app form one container (TODO).
* A preconfigured groc service to send logs from the frontend to the backend. This serves both as an example and can be of real-world use.

## Requirements

* `sbt`
* `yarn`
* `protoc`

# TODO

* Add initial tests to front and backend
* Add Docker build setup

