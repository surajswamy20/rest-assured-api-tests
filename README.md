# REST Assured API Automation Practice

This is a personal API automation project built with Java, REST Assured, TestNG, and Maven. I use it to practise writing readable API tests, validating responses, and working with Java objects as request and response payloads.

The tests use [JSONPlaceholder](https://jsonplaceholder.typicode.com/), a public fake REST API. Its POST, PUT, and DELETE responses are simulated and are not permanently stored.

## Scenarios covered

- Get an existing post and validate important response fields
- Verify the response for a post that does not exist
- Create a post using a Java object as the request body
- Update a post and validate the deserialized response
- Delete a post and validate the status code
- Find a user with a query parameter and extract nested values using JSONPath

## Tools

- Java 17
- REST Assured
- TestNG
- Maven
- Jackson

## Run the tests

Prerequisites: Java 17 or later and Maven.

```bash
mvn clean test
```

The base URL is stored in `src/test/resources/config.properties`. It can also be overridden from the command line:

```bash
mvn test -Dbase.url=https://jsonplaceholder.typicode.com
```

## Project structure

```text
src/test/java/com/surajswamy/api/
|-- config/   # Loads the test configuration
|-- model/    # Java object used for JSON serialization
`-- tests/    # Shared request setup and TestNG tests
```

## Current limitations

- The project uses a public fake API, so write operations are not persisted.
- Authentication and JSON schema validation are not covered yet.
- The suite runs locally through Maven; CI execution can be added later.

These are possible next steps rather than features already implemented.
