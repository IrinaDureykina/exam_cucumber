package request_specification_and_response;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.Objects;

import static org.hamcrest.Matchers.*;
import static request_specification_and_response.RequestSpecificationAndResponseTests.response;
import static web_hooks.WebHooks.saveMessage;

public class AssertionsStatusCodeBody {

    @When("Сверяем statusCode ответа с ожидаемым {string}")
    public static void checkStatusCode(String statusCode) {
        int expectedStatusCode = Integer.parseInt(statusCode);
        int actualStatusCode = response.getStatusCode();
        String message = "Проверка StatusCode: " + expectedStatusCode + " Actual StatusCode: " + actualStatusCode;
        saveMessage("Сверяем полученный статус код с ожидаемым" ,message);
        Assertions.assertEquals(expectedStatusCode, actualStatusCode, "StatusCode не соответствует ожидаемому значению");
    }

    @When("Сверяем body ответа с ожидаемым {string}")
    public static void checkBody(String keyUrl) {

        if(Objects.equals(keyUrl, "rickandmortyapi.com")){
            response
                    .then()
                    .body("characters", equalTo("https://rickandmortyapi.com/api/character"))
                    .body("locations", equalTo("https://rickandmortyapi.com/api/location"))
                    .body("episodes", equalTo("https://rickandmortyapi.com/api/episode"));

        }
        if(Objects.equals(keyUrl, "reqres.in")){
            response.then()
                    .body("per_page", equalTo(6))
                    .body("total", equalTo(12))
                    .body("page", equalTo(1))
                    .body("total_pages", equalTo(2))
                    .body("support.text", containsString("To keep ReqRes free, contributions towards server costs are appreciated!"))
                    .body("support.url", equalTo("https://reqres.in/#support-heading"))
                    .body("data", hasSize(6))
                    .body("data[0].color", equalTo("#98B2D1"))
                    .body("data[0].year", equalTo(2000))
                    .body("data[0].name", equalTo("cerulean"))
                    .body("data[0].id", equalTo(1))
                    .body("data[0].pantone_value", equalTo("15-4020"));
        }
        String massage = "Проверка Body сайта: "+ keyUrl + "прошла успешно body:"+response.getBody().asString();
        saveMessage("Проверка Body сайта: "+ keyUrl, massage);
    }

    @And("Сверяем ответ с ожидаемым значением полей name : {string} и job: {string}")
    public static void checkBody(String expectedName, String expectedJob) {
        String message = "Проверяем Поле 'name' и Поле 'job' на соответствие ожидаемым значениям- 'name': " + expectedName + " 'job': " + expectedJob;
        saveMessage("Проверяем поля ответа ", message);
        Assertions.assertEquals(expectedName, response.path("name"), "Поле 'name' не соответствует ожидаемому значению");
        Assertions.assertEquals(expectedJob, response.path("job"), "Поле 'job' не соответствует ожидаемому значению");
    }
}
