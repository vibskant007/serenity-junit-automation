package com.vibhor.cucumberserenity.tests;
import com.vibhor.cucumberserenity.steps.UserApiSteps;
import com.vibhor.cucumberserenity.steps.UserStepsResponseAssertions;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class UserRegisterTest {

    @Steps
    UserApiSteps userApiSteps;

    @Steps
    UserStepsResponseAssertions userStepsResponseAssertions;


    @Test
    public void verifyUserRegistration() {

      Response response = userApiSteps.registerNewUser("eve.holt@reqres.in","pistol");
        userStepsResponseAssertions.validateStatusCode(response,200);
        userStepsResponseAssertions.validateJsonKeyValueExists(response);
        userStepsResponseAssertions.matchesJsonSchema(response,"userSchema.json");
    }
}
