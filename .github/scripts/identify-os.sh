#!/bin/bash
set -xv
lsb_release -a
uname -a
## Our private IP
PIP=$(hostname -I | awk '{print $1}')
echo "The private IP is ${PIP}"

## Check env variables
printenv