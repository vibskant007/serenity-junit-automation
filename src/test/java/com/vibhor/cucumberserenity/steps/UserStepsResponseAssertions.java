package com.vibhor.cucumberserenity.steps;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class UserStepsResponseAssertions {

    @Step
    public void validateStatusCode(Response userResponse,int statusCode) {
        userResponse.then().statusCode(statusCode);
    }

    @Step
    public void validateJsonKeyValueExists(Response userResponse) {
        userResponse.then().body(containsString("id"));
        userResponse.then().body(containsString("token"));
    }

    @Step
    public void matchesJsonSchema(Response userResponse,String path) {
        userResponse.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(path));
    }

    @Step
    public void saveToken(String value) {
        Serenity.setSessionVariable("token").to(value);
    }
}
