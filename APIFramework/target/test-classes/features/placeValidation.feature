Feature: Validatng place APIs

Scenario: Verify if place is being successfuly added using AddPlace API

Given Add place payload
When User calls "AddplaceAPI" with post http method
Then API call got success with status code 200
And "status" of the response is "OK"
And "scope" of the response bidy is "App"
