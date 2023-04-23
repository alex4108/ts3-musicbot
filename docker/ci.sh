#!/usr/bin/env bash

echo "${DOCKER_PASSWORD}" | docker login -u ${DOCKER_USER}
export TARGET_TAG="${DOCKER_USER}/ts3musicbot:${COMMIT_SHA}" # TODO Work out the tag
bash build.sh
docker push ${TARGET_TAG}