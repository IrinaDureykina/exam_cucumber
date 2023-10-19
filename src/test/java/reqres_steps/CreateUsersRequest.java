package reqres_steps;

import io.cucumber.java.en.When;
import io.restassured.specification.RequestSpecification;
import request_specification_and_response.RequestSpecificationAndResponseTests;

import static reqres_steps.UpdateJsonFile.bodyCreateUserReqres;
import static util.Config.getProperties;

public class CreateUsersRequest extends RequestSpecificationAndResponseTests {

    @When("Отправляем запрос на сайт {string},  c endpoint = {string},  method = {string} для создание пользователя")
    public static void createUsersRequest(String keyUrl, String endpoint, String method ) {
        RequestSpecification request = requestSpecificationTests(getProperties(keyUrl));
        responseGet(request, bodyCreateUserReqres, endpoint, method);
    }
}
