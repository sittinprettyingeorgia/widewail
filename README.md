# Widewail Java Interview Service

Write a Spring Boot app which can be used to import and store reviews from multiple review sites.
Imported reviews should be searchable via a RESTful api. Your service should
be runnable using the supplied `docker-compose.yml`. Feel free to modify
it as you see fit but it is expected that your service will run by simply
running `docker-compose up`.

The supplied docker compose file includes a MySQL database and
a java container which can be used to build your project.

**Required Software**

- Docker
- Docker compose

# Review API

Your service will integrate with a single review site which provides mock
review data. All new reviews should be imported upon app start up.

Endpoint: `https://qttwgovlgb.execute-api.us-east-1.amazonaws.com/production/reviews`

Required headers: `x-api-key: <api key>`

# Interview Service API Requirements

Your service should expose a RESTful api which can be used to search the database
of reviews that have been imported. Expose the following endpoints.

`GET /reviews` - search all reviews

`GET /reviews/{id}` - load a single review by id

`DELETE /reviews/{id}` - delete a review by id

# Troubleshooting

* The database container is failing to start

  Clear out the existing container and database volume then try again.

```
$ docker-compose rm
$ docker volume widewail-java-interview-app_db-data rm
```

* "Missing Authentication Message" when accessing the API

  Requesting the root of the API will return this error. Check that you are calling the `/reviews` endpoint. 