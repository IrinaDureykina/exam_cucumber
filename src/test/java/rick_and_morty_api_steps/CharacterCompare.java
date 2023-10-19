package rick_and_morty_api_steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONObject;

import static rick_and_morty_api_steps.GetCharacter.getCharacter;
import static rick_and_morty_api_steps.GetInEpisodelastCharactersNumber.getInEpisodeLastCharactersNumber;
import static web_hooks.WebHooks.saveMessage;

public class CharacterCompare {

    private static JSONObject characterJson1;
    private static JSONObject characterJson2;
    private static String lastEpisodeNumber;
    private static String characterId2;
    @Then("Отправляем запрос на сайт {string} c endpoint = {string},  method = {string} для получения характеристики персонажа с ID: {string}")
    public static void characterJson1(String keyUrl, String endpoint, String method, String characterId1) {
        characterJson1 = getCharacter(keyUrl, endpoint, characterId1, method);
        String name = characterJson1.getString("name");
        String species = characterJson1.getString("species");
        String location = characterJson1.getString("location");
        String message = "Имя: " + name + ", Раса персонажа: " + species+", Локация персонажа: " + location;
        saveMessage("Характеристики Персонажа с ID: " + characterId1 ,message);
    }

    @When("Получаем номер последнего эпизода")
    public static void getLastEpisodeNumber() {
        lastEpisodeNumber = characterJson1.getString("lastEpisodeNumber");
        String message = "Персонаж: " + characterJson1.getString("name") + ",последний раз появлялся в эпизоде с ID: " + lastEpisodeNumber;
        saveMessage("ID эпизода где последний раз появлялся: " + characterJson1.getString("name") ,message);
    }

    @When("Отправляем запрос на сайт {string} c endpoint = {string},  method = {string} для получения номера последнего персонажа в эпизоде")
    public static void getCharacterId2(String keyUrl, String endpoint, String method) {
        characterId2 = getInEpisodeLastCharactersNumber(keyUrl, endpoint, method, lastEpisodeNumber);
        String message = "В эпизоде: " + lastEpisodeNumber + ",последний персонаж с ID: " + characterId2;
        saveMessage("ID последнего прсонажа в эпизоде: " + lastEpisodeNumber ,message);
    }

    @When("Отправляем запрос на сайт {string} c endpoint = {string},  method = {string} для получения характеристики последнего персонажа в эпизоде")
    public static void characterJson2(String keyUrl, String endpoint, String method) {
        characterJson2 = getCharacter(keyUrl, endpoint, characterId2, method);
        String name = characterJson2.getString("name");
        String species = characterJson2.getString("species");
        String location = characterJson2.getString("location");
        String message = "Имя: " + name + ", Раса персонажа: " + species +", Локация персонажа: " + location;
        saveMessage("Характеристики Персонажа с ID: " + characterId2 ,message);
    }


    @When("Сравниваем Характеристики персонажей")
    public static void characterCompare() {

        String name1 = characterJson1.getString("name");
        String species1 = characterJson1.getString("species");
        String location1 = characterJson1.getString("location");

        String name2 = characterJson2.getString("name");
        String species2 = characterJson2.getString("species");
        String location2 = characterJson2.getString("location");

        String message;

        if (species1.equals(species2)) {
            message = "Принадлежат к одной расе: " + name1 + " и " + name2 + ": " + species1;
        } else {
            message = "Расы разные: " + name1 + "- " + species1 + ", " + name2 + "- " + species2;
        }
        saveMessage("Принадлежность к расам" ,message);
        if (location1.equals(location2)) {
            message = "Находятся в одном месте: " + name1 + " и " + name2 + ": " + location1;
        } else {
            message = "Находятся в разных местах: " + name1 + "- " + location1 + ", " + name2 + "- " + location2;
        }
        saveMessage("Локации персонажей" ,message);
    }
}