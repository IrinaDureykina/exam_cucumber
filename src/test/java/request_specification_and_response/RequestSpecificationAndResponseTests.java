package request_specification_and_response;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;

public class RequestSpecificationAndResponseTests {
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
        Assertions.assertNotNull(response, "Ответ (response) равен null");
        Allure.addAttachment("Response", "application/json", response.asString());
    }
}