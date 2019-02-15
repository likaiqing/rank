#!/bin/bash

if [[ $1 == '' ]]; then
    echo "version must"
    exit 1
fi

git clone -b $1 http://likaiqing@work1v.bigdata.bjtb.pdtv.it:8082/bigdata/rank.git /data/htdocs/rank