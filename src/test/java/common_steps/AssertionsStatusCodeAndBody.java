package common_steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

import java.util.Objects;

import static org.hamcrest.Matchers.*;
import static hooks.Hooks.saveMessage;

public class AssertionsStatusCodeAndBody extends RequestSpecificationAndResponseGet {

    @When("Сверяем statusCode ответа с ожидаемым {string}")
    public static void checkStatusCode(String statusCode) {
        int expectedStatusCode = Integer.parseInt(statusCode);
        int actualStatusCode = response.getStatusCode();
        String message = "Проверка StatusCode: " + expectedStatusCode + " Actual StatusCode: " + actualStatusCode;
        saveMessage("Сверяем полученный статус код с ожидаемым" ,message);
        response
                .then()
                .statusCode(expectedStatusCode);
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
        String massage = "Проверка Body сайта: "+ keyUrl + ", прошла успешно body:"+response.getBody().asString();
        saveMessage("Проверка Body сайта: "+ keyUrl, massage);
    }

    @And("Сверяем ответ с ожидаемым значением полей name : {string} и job: {string}")
    public static void checkBody(String expectedName, String expectedJob) {
        String actualName = response.path("name");
        String actualjob = response.path("job");
        String message = "Проверяем Поле 'name' на соответствие. Ожидаемое значение: " + expectedName + ", актуальное значение: " + actualName;
        saveMessage("Проверяем поле name", message);
        response.then()
                .body("name", equalTo(expectedName));
        message = "Проверяем Поле 'job' на соответствие. Ожидаемое значение: " + expectedJob + ", актуальное значение: " + actualjob;
        saveMessage("Проверяем поле job ", message);
        response.then()
                .body("job", equalTo(expectedJob));
    }
}
