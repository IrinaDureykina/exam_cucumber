package rick_and_morty_api_steps;

import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import request_specification_and_response.RequestSpecificationAndResponseTests;

import static request_specification_and_response.AssertionsStatusCodeBody.checkStatusCode;
import static util.Config.getProperties;

public class GetInEpisodelastCharactersNumber extends RequestSpecificationAndResponseTests {

    @Step("Получение Id последнего персонажа в эпизоде Id: \"{idEpisode}\"")
    public static String getInEpisodeLastCharactersNumber(String keyUrl, String endpoint, String method, String idEpisode) {
        RequestSpecification request = requestSpecificationTests(getProperties(keyUrl));
        endpoint = endpoint + idEpisode;
        responseGet(request, null, endpoint, method);
        checkStatusCode("200");
        JSONObject responseBody = new JSONObject(response.body().asString());
        String lastCharactersUrl = responseBody.getJSONArray("characters").getString(responseBody.getJSONArray("characters").length() - 1);
        return lastCharactersUrl.substring(lastCharactersUrl.lastIndexOf("/") + 1);

    }
}