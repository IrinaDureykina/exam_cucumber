package reqres_steps;

import io.cucumber.java.en.When;
import io.restassured.specification.RequestSpecification;
import common_steps.RequestSpecificationAndResponseGet;

import static reqres_steps.UpdateJsonFile.bodyCreateUserReqres;
import static util.Config.getProperties;

public class CreateUsersRequest extends RequestSpecificationAndResponseGet {

    @When("Отправляем запрос на сайт {string},  c endpoint = {string},  method = {string} для создание пользователя")
    public static void createUsersRequest(String keyUrl, String endpoint, String method ) {
        RequestSpecification request = requestSpecificationTests(getProperties(keyUrl));
        responseGet(request, bodyCreateUserReqres, endpoint, method);
    }
}
