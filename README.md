# Notes API

## Requires Java 8

How to start the Notes application with Docker
---

1. Run `mvn clean install` to build and run tests
1. Start application with `docker-compose up`
1. To check that your application is running enter url `http://localhost:8080/api/notes`


How to start the Notes application locally
---

1. Run `mvn clean install` to build and run tests
1. Update database section in config.yml with your postgres information
1. Start application with `java -jar target/levelsbeyond-notes-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080/api/notes`
