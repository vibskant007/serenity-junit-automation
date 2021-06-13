package com.vibhor.cucumberserenity.steps;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.Map;

public class UserApiSteps {

    @Step
    public Response registerNewUser(String userName,String passWord) {
        Map<String,Object> bodyMap = new HashMap<>();
        bodyMap.put("email",userName);
        bodyMap.put("password",passWord);
        Response response = SerenityRest.given().baseUri("https://reqres.in/api/").basePath("/register").contentType("application/json").body(bodyMap).when().post();
        return response;
    }
}
