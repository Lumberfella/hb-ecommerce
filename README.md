# HB - Backend Coding Challenge

Simplified e-commerce API with a single endpoint that performs a checkout action.

## Setup

Java version: `17` or higher
Gradle version: `7.6.1`

Install:

```sh
./gradlew install
```

if using Windows:

```sh
gradlew.bat build
```

## Running locally

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

- Using Spring Initializr for simplicity. Same as for the auto-generated .gitignore.
- Java does not have octal literals as far as I know, so I will keep the Id of Watch as a String.
- Will keep the format and types of the catalogue as-is and simple, even though it should be more atomic and split up.
- Prices as integers, double and decimal types should not be used for currency or monetary values generally,
  but ok for now.
- Keep project structure as split up by feature/domain.
- We will get a list of Ids which can be duplicated based on amount, and JPA will not return duplicates.
  Need to keep count by Ids.
- Can then loop the distinct watches returned from db and check count to determine if discount is applicable or not.

**Improvements**

- Split discounts to its own table and package. Simpler to manage and can avoid string splitting.
  - DiscountInfo would come from db and related to watchId.
  - Watch(watchId, name, unitPrice) - Discount(discountId, watchId, quantity, discountPrice)
- Some class/type/library to properly handle money/price.
- Properly handle error cases. The string split can for example throw an Exception if the format is not correct.
- Use a proper database (PostgreSQL)
- Dockerfile can have stages for building and starting the container. No need to build with gradle before.
- The response of the endpoint doesn't say much. At least add what has been checked
  out, how many of each type, unitPrices and what kind of discounts have been applied.

