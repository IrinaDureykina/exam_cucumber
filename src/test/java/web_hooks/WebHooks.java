package web_hooks;


import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;


public class WebHooks {
    @Before(order = 1)
    public static void allureSelenideListener() {
        String listenerName = "Allureselenide";
        if(SelenideLogger.hasListener(listenerName)) {
            SelenideLogger.addListener(listenerName,(new AllureSelenide()));
        }
    }

//    @AfterStep
//    public void messageIsFailedStep(Scenario scenario) {
//        if (scenario.isFailed()){
//            saveMessage("Failed Step","Ошибка в сценарии ");
//        }
//    }
    @Attachment(value = "{nameMessage}", type = "text/plain")
    public static String saveMessage(String nameMessage ,String message) {
        return message;
    }

    @After
    public void afterTest() {
        saveMessage("afterTest","Конец теста");
        SelenideLogger.removeListener("Allureselenide");
    }
}
