# Credit Card App

## How to run

This is a Spring Boot app, so it's just a matter of: `mvn spring-boot:run`. Or, if you don't have maven installed, use the maven wrapper: `./mvnw spring-boot:run`.

The homepage is `http://localhost:8080`

## The API

- GET http://localhost:8080/api/creditcard => list all cards
- POST http://localhost:8080/api/creditcard => adds a new credit card

If you'd like to use cURL, use this one to list all credit cards:

```
curl "http://localhost:8080/api/creditcard"
```

Use this one to create a new credit card, but keep in mind to use a *valid* and *different* credit card number, every time:

```
curl -X "POST" "http://localhost:8080/api/creditcard" \
     -H 'Content-Type: application/json' \
     -d $'{
  "number": "5491886232752714",
  "name": "Someone 2",
  "limit": 100021.33
}'
```

**This is still under construction, only the list page is available. To add credit cards use the api.** 
