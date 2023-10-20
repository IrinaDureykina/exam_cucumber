package reqres_steps;

import common_steps.RequestSpecificationAndResponseGet;
import io.cucumber.java.en.When;
import io.restassured.specification.RequestSpecification;

import static reqres_steps.UpdateJsonFile.bodyCreateUserReqres;
import static util.Config.getProperties;

public class CreateUsersRequest extends RequestSpecificationAndResponseGet {

    @When("Отправляем запрос на сайт {string},  c endpoint = {string},  method = {string} для создания пользователя")
    public static void createUsersRequest(String keyUrl, String endpoint, String method ) {
        RequestSpecification request = requestSpecificationTests(getProperties(keyUrl));
        responseGet(request, bodyCreateUserReqres, endpoint, method);
    }
}
