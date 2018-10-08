#!/bin/sh

java -jar target/levelsbeyond-notes-1.0-SNAPSHOT.jar db migrate config.docker.yml
java -jar target/levelsbeyond-notes-1.0-SNAPSHOT.jar server config.docker.yml
