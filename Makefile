init-fe: ; yarn --cwd frontend install

init-be: ; cd backend && sbt clean compile

init: init-fe init-be

build-fe: ; yarn --cwd frontend build

build-be: ; cd backend && sbt clean assembly

build: build-fe build-be

test-fe: ; yarn --cwd frontend test

test-be: ; cd backend && sbt test

test: test-fe test-be

lint-fe: ; yarn --cwd frontend lint

lint-be: ; cd backend && sbt scalafmtCheckAll

lint: lint-fe lint-be

format-fe: ; yarn --cwd frontend lint:fix

format-be: ; cd backend && sbt scalafmt

format: format-fe format-be

codegen-fe: ; yarn --cwd frontend codegen

codegen-be: ; cd backend && sbt clean compile

codegen: codegen-fe codegen-be
