package reqres_steps;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.cucumber.java.en.Then;

import java.io.FileReader;
import java.io.IOException;

import static web_hooks.WebHooks.saveMessage;

public class UpdateJsonFile {

    protected static String bodyCreateUserReqres;

    @Then("Изменяем в файле {string} name на: {string} и добавляем поле job с значением {string}")
    public void updateJsonFile(String filePath, String name, String job) throws IOException {
        JsonParser jsonParser = new JsonParser();
        FileReader fileReader = new FileReader(filePath);
        JsonObject newJsonFiles = jsonParser.parse(fileReader).getAsJsonObject();
        fileReader.close();
        newJsonFiles.addProperty("name", name);
        newJsonFiles.addProperty("job", job);
        bodyCreateUserReqres = newJsonFiles.toString();
        String massage = "Создан объект с body: " + bodyCreateUserReqres.toString();
        saveMessage("Body для отправки на сайт", massage);
    }
}
