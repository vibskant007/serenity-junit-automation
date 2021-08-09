package com.vibhor.cucumberserenity.tests;
import com.vibhor.cucumberserenity.steps.UserApiSteps;
import com.vibhor.cucumberserenity.steps.UserStepsResponseAssertions;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.Qualifier;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Properties;


@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value="src/test/resources/username.csv")
public class UserRegisterTest {

    private String username;
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Qualifier
    public String qualifier() {
        return username + "=>" + password;
    }


    @Steps
    UserApiSteps userApiSteps;

    @Steps
    UserStepsResponseAssertions userStepsResponseAssertions;


    @Test
    public void verifyUserRegistration() {
        //GIVEN

        //WHEN
        Response response = userApiSteps.registerNewUser(username,password);

        //THEN
        userStepsResponseAssertions.validateStatusCode(response,200);
        userStepsResponseAssertions.validateJsonKeyValueExists(response);
        userStepsResponseAssertions.matchesJsonSchema(response,"userSchema.json");
    }
}
