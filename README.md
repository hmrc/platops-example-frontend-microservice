# platops-example-frontend-microservice

This is an exemplar of a frontend microservice.

## Examples in this project

### Create controller with dependency injection

Check uk.gov.hmrc.example.controllers.HelloWorldController

### Unit tests

An example of how to write a unit test for a controller can be found in uk.gov.hmrc.example.controllers.HelloWorldControllerSpec in the test folder

To run unit tests:

```
sbt test
```

### Integration tests using smserver

Check out how to use the new service-integration-test library in uk.gov.hmrc.example.ExampleIntegrationTest in the it folder

To run integration tests (smserver has to be running):

```
sbt it:test
```

### License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html")

test

