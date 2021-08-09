package com.vibhor.cucumberserenity.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.walmartlabs.context.Context;
import com.walmartlabs.readers.FileReaderManager;
import com.walmartlabs.restclient.IOperationInvoker;
import com.walmartlabs.restclient.PostOperation;
import com.walmartlabs.restclient.RestService;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.Map;

public class UserApiSteps {

    private RestService restService;
    private IOperationInvoker iOperationInvoker = new IOperationInvoker();
    private Map<String,Object> headerMap = FileReaderManager.getInstance().getConfigReader().getHeaderMap();

    public UserApiSteps () throws JsonProcessingException {

    }

    @Step
    public Response registerNewUser(String userName,String passWord) {
        Map<String,Object> bodyMap = new HashMap<>();
        bodyMap.put("email",userName);
        bodyMap.put("password",passWord);
        System.out.println(headerMap);
        this.restService = new RestService(FileReaderManager.getInstance().getConfigReader().getBaseEndpoint() + FileReaderManager.getInstance().getConfigReader().getBasePath(),"/register",headerMap,null);
        iOperationInvoker.setRestOperation(new PostOperation(restService,"",bodyMap));
        Response response = iOperationInvoker.execute();
        //Response response = SerenityRest.given().baseUri("https://reqres.in/api/").basePath("/register").contentType("application/json").body(bodyMap).when().post();
        return response;
    }
}
