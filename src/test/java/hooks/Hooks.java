package hooks;


import io.qameta.allure.Attachment;


public class Hooks {

    @Attachment(value = "{nameMessage}", type = "text/plain")
    public static String saveMessage(String nameMessage ,String message) {
        return message;
    }


}
