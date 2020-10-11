#!/usr/bin/env bash

set -x
set -e

SCRIPT_DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
ROOT_DIR="$SCRIPT_DIR/.."
SOURCE_DIR="$ROOT_DIR/../proto"
OUT_DIR="$ROOT_DIR/src/generated"

rm -rf "$OUT_DIR"

mkdir -p "$OUT_DIR"

protoc \
    -I "$SOURCE_DIR" \
    --plugin=protoc-gen-ts="$ROOT_DIR/node_modules/.bin/protoc-gen-ts" \
    --js_out="import_style=commonjs,binary:$OUT_DIR" \
    --ts_out="service=grpc-web:$OUT_DIR" \
    "$SOURCE_DIR"/*.proto

# https://github.com/improbable-eng/grpc-web/issues/96#issuecomment-347871452
for f in "${OUT_DIR}"/*.js
do
    echo '/* eslint-disable */' | cat - "${f}" > temp && mv temp "${f}"
done    