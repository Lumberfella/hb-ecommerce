# HB - Backend Coding Challenge

## Running locally

Gradle version: `7.6.1`

Build project:
```sh
./gradlew clean build
```

Docker build:
```sh
docker build --build-arg JAR_FILE=build/libs/hb-ecommerce-0.0.1-SNAPSHOT.jar -t hbecommerce .
```

Docker run:
```sh
docker run -p 8080:8080 hbecommerce
```
