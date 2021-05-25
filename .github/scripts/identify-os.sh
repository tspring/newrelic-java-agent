#!/bin/bash
set -xv
lsb_release -a
uname -a
## Our private IP
PIP=$(hostname -I | awk '{print $1}')
echo "The private IP is ${PIP}"
## Test setting env variable from script:
echo "GHA_ENV_VAR=test_value" >> $GITHUB_ENV

## Check env variables
printenv