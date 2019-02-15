#!/usr/bin/env bash
BD_BIN="${BASH_SOURCE-$0}"
echo $BD_BIN
BD_BIN="$(dirname "${BD_BIN}")"
echo $BD_BIN
BD_BIN="$(cd "${BD_BIN}"; pwd)"
echo $BD_BIN
BD_DIR="${BD_BIN}"
echo $BD_DIR
TG_DIR="$BD_DIR/target"
echo $BD_DIR
#go in build dir
cd $BD_DIR
#build
echo "Starting building"

mvn clean package -DskipTests -U;

if [ ! "$(ls -v  $TG_DIR)"  ] ; then
  echo "Building error"
  exit 1
fi
echo "Building finished"
