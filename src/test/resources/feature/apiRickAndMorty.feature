@Api
@Сайт_https://rickandmortyapi.com
Feature: : Rick And Morty Tests Api

  @ПроверкаДоступностиСайта
  Scenario: : Тест Проверки доступности сайта
    Then Отправляем запрос на сайт "rickandmortyapi.com", c endpoint = "",  method = "GET"
    When Сверяем statusCode ответа с ожидаемым "200"
    When Сверяем body ответа с ожидаемым "rickandmortyapi.com"

  @СравнениеХарактеристикПерсонажей
  Scenario: Тест Получение и сравнение характеристик персонажей
    Then Отправляем запрос на сайт "rickandmortyapi.com" c endpoint = "/character/",  method = "GET" для получения характеристики персонажа с ID: "2"
    When Получаем номер последнего эпизода
    When Отправляем запрос на сайт "rickandmortyapi.com" c endpoint = "/episode/",  method = "GET" для получения номера последнего персонажа в эпизоде
    When Отправляем запрос на сайт "rickandmortyapi.com" c endpoint = "/character/",  method = "GET" для получения характеристики последнего персонажа в эпизоде
    And Сравниваем Характеристики персонажей