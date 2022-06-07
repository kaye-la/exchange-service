# Exchange-service
## Description

The project is test task for Junior Java developer/Intern.
A service that calls the exchange rate service and displays a gif.
If the rate against USD for today has become higher than yesterday, then service give a random gif from https://giphy.com/search/rich. If the rate against USD for today has become below than yesterday, then service give a random gif from https://giphy.com/search/broke.

- Type some Markdown on the left
- See HTML in the right
- ✨Magic ✨

## Must have

- Service on Spring Boot 2 + Java / Kotlin
- Requests come to the HTTP endpoint 
- Feign is used to interact with external services
- All parameters (currency in relation to which the rate is viewed, addresses of external services, etc.) are moved to the settings
- Tests written for the service
- Gradle for building

## Usage

Run command: ```java -jar build/libs/exchange-service-0.0.1-SNAPSHOT.jar```
Service responses to the following endpoint:
```
http://localhost:8080/gifs/get-gif/{currency}
```
Where {currency} - path variable, currency to compare with USD

Example: ```http://localhost:8080/gifs/get-gif/hkd```

## Build using wrapper
To build locally, you will need JDK, Git and Gradle installed.

In command line:
```
# Clone repository
$ git clone 

# Go to repository
$ cd exchange-service

# Build
$ ./gradlew build
```