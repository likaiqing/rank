#!/usr/bin/env bash

if [ $# != 1 ]; then
    echo "version must"
    exit 1
fi

project=rank

rm -rf /data/htdocs/$project

git clone -b $1 http://likaiqing@work1v.bigdata.bjtb.pdtv.it:8082/bigdata/rank.git

cd $project

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
