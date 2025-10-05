#!/bin/sh
docker rm -f web_nexus_seven
docker build -t web_nexus_seven .
docker run --name=web_nexus_seven --rm -p1337:1337 -it web_nexus_seven