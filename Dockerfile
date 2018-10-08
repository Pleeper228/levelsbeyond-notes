FROM openjdk:8-slim

COPY target /target

COPY config.docker.yml /config.docker.yml

COPY bin/entrypoint.sh /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]