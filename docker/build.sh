#!/usr/bin/env bash
set -exuo pipefail

SCRIPTPATH="$( cd -- "$(dirname "$0")" >/dev/null 2>&1 ; pwd -P )"

set +u
if [[ "${TARGET_TAG}" == "" ]]; then
    tag="ts3musicbot"
else
    tag="${TARGET_TAG}"
fi
set -u

docker build -t ${tag} -f Dockerfile ${SCRIPTPATH}/../
