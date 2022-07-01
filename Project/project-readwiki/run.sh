#!/bin/sh
WIKI=https://en.wikipedia.org/wiki/Special:Random
curl $WIKI --head | grep location | sed "s/location:/content=Read/" | curl -XPOST $TODOS --data @-