Feature: verify login fucntionality

@CH5341 @Regression @Smoke
Scenario: verify SF login with valid credentails
Given user is on application login page
When user enters username and password
#Then user clicks on login button
#And verify appliaction home page


@118Batch
Scenario: verify login to SF application with valid credentials
Given user is on application home page
When user enters credentails and clicks on login button


Scenario: create an user to SF application
Given user is on application home page
When user enters credentails and clicks on login button
Then user clicks on create user in landing page
And user fills the form and click on save button