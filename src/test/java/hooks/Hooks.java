package hooks;


import io.qameta.allure.Attachment;


public class Hooks {
//    @Before
//    public static void allureSelenideListener() {
//        String listenerName = "Allureselenide";
//        if(SelenideLogger.hasListener(listenerName)) {
//            SelenideLogger.addListener(listenerName,(new AllureSelenide()));
//        }
//    }

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

//    @After
//    public void afterTest() {
//        saveMessage("afterTest","Конец теста");
//        SelenideLogger.removeListener("Allureselenide");
//    }
}
