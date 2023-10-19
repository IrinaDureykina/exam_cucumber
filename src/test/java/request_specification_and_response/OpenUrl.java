package request_specification_and_response;

import io.cucumber.java.en.Then;
import io.restassured.specification.RequestSpecification;

import static util.Config.getProperties;

public class OpenUrl extends RequestSpecificationAndResponseTests {
    @Then("Отправляем запрос на сайт {string}, c endpoint = {string},  method = {string}")
    public static void openUrl(String keyUrl, String endpoint, String method) {
        RequestSpecification request = requestSpecificationTests(getProperties(keyUrl));
        responseGet(request, null, endpoint, method);
    }
}