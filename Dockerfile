FROM openjdk:8-slim

COPY target /target

COPY config.docker.yml /config.docker.yml

COPY bin/entryPoint.sh /entryPoint.sh

ENTRYPOINT ["/entryPoint.sh"]
