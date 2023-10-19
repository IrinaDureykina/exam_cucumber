@Api
@reqresTestsApi
Feature: : Reqres Tests Api

  @ПроверкаДоступностиСайта
  Scenario:Тест Проверки доступности сайта
    Then Отправляем запрос на сайт "reqres.in", c endpoint = "/api/unknown",  method = "GET"
    When Сверяем statusCode ответа с ожидаемым "200"
    When Сверяем body ответа с ожидаемым "reqres.in"

  @СозданиеПользователя
  Scenario:Тест Создания пользователя: изменяем Json файл: изменяя имя и добавляя поле job и отправляем запрос
    Then Изменяем в файле "src/test/resources/reqres/reqres.json" name на: "Tomato" и добавляем поле job с значением "Eat maket"
    When Отправляем запрос на сайт "reqres.in",  c endpoint = "/api/users",  method = "POST" для создание пользователя
    When Сверяем statusCode ответа с ожидаемым "201"
    And Сверяем ответ с ожидаемым значением полей name : "Tomato" и job: "Eat maket"
