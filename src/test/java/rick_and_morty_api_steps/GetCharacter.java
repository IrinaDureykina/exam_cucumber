package rick_and_morty_api_steps;

import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import request_specification_and_response.RequestSpecificationAndResponseTests;

import static request_specification_and_response.AssertionsStatusCodeAndBody.checkStatusCode;
import static util.Config.getProperties;

public class GetCharacter extends RequestSpecificationAndResponseTests {

    @Step("Получение характеристик персонажа по его Id: \"{id}\"")
    public static JSONObject getCharacter(String keyUrl, String endpoint, String id, String method) {
        RequestSpecification request = requestSpecificationTests(getProperties(keyUrl));
        endpoint = endpoint + id;
        responseGet(request, null, endpoint, method);
        checkStatusCode("200");
        JSONObject characterJson = new JSONObject();
        JSONObject responseBody = new JSONObject(response.body().asString());
        characterJson.put("name", responseBody.getString("name"));
        characterJson.put("species", responseBody.getString("species"));
        characterJson.put("location", responseBody.getJSONObject("location").getString("name"));
        String lastEpisodeUrl = responseBody.getJSONArray("episode").getString(responseBody.getJSONArray("episode").length() - 1);
        characterJson.put("lastEpisodeNumber", lastEpisodeUrl.substring(lastEpisodeUrl.lastIndexOf("/") + 1));
        return characterJson;
    }
}