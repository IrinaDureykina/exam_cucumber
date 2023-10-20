package common_steps;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static hooks.Hooks.saveMessage;
import static io.restassured.RestAssured.given;

public class RequestSpecificationAndResponseGet {
   public static Response response;
    @Step("Составление RequestSpecification по: {url}")
    public static RequestSpecification requestSpecificationTests(String url) {
        return given()
                .baseUri(url)
                .header("Language", "en")
                .header("Content-Type", "application/json")
                .log()
                .uri()
                .filter(new AllureRestAssured());
    }

    @Step("Отправка запроса с параметрами: body: \"{bodyValue}\", method: \"{method}\" Получение response")
    public static void responseGet(RequestSpecification request, String bodyValue, String endpoint, String method) {
        RequestSpecification updatedRequest = request;
        if (bodyValue != null) {
            updatedRequest = updatedRequest.body(bodyValue);
        }
        if (endpoint != null) {
            updatedRequest = updatedRequest.basePath(endpoint);
        }
        response=null;
        if ("GET".equalsIgnoreCase(method)) {
            response = updatedRequest.get();
        } else if ("POST".equalsIgnoreCase(method)) {
            response = updatedRequest.post();
        } else if ("PUT".equalsIgnoreCase(method)) {
            response = updatedRequest.put();
        } else if ("DELETE".equalsIgnoreCase(method)) {
            response = updatedRequest.delete();
        }
        if (response.then() == null) {
            String message = "Ответ не получен response = null";
            saveMessage("Ответ не получен", message);
        }else {
            Allure.addAttachment("Response", "application/json", response.asString());
        }
    }
}