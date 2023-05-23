# HB - Backend Coding Challenge

## Running locally

Gradle version: `7.6.1`

Build project:
```sh
./gradlew clean build
```

Docker build:
```sh
docker build --build-arg JAR_FILE=build/libs/hb-ecommerce-0.1.0.jar -t hbecommerce .
```

Docker run:
```sh
docker run -p 8080:8080 hbecommerce
```

## Thoughts

**Approach:**
 * Using Spring Initializr for simplicity. Same as for the auto-generated .gitignore.
 * Java does not have octal literals as far as I know, so I will keep the Id of Watch as a String. 
 * Will keep the format and types of the catalogue as-is and simple, even though it should be more atomic and split up.
 * Prices as integers, double and decimal types should not be used for currency or monetary values generally, but ok for now.
 * Keep project structure as split up by feature/domain.
 * We will get a list of Ids which can be duplicated based on amount, and JPA will not return duplicates. Need to keep count by Ids.
 * Can then loop the distinct watches returned from db and check count to determine if discount is applicable or not.

**Improvements**
 * Split discounts to its own table and package. Simpler to manage and can avoid string splitting. 
 * Some class/type/library to properly handle money/price.
