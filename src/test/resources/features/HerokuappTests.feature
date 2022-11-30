@all
Feature: Тесты ресурса: "http://the-internet.herokuapp.com/"

  Scenario: Проверка чекбокса
    Given Открываю главную страницу сайта 'herokuapp.com'
    And Нажимаю на 'Checkboxes'
    When Выбираю чекбокс №1
    Then Проверяю, что чекбокс был нажат

  Scenario: Проверка страницы авторизации с валидными и не валидными кредами
    Given Открываю главную страницу сайта 'herokuapp.com'
    And Нажимаю на 'Form Authentication'
    When Ввожу данные для авторизации: Логин - 'tomsmith', пароль - 'SuperSecretPassword!'
    And Нажимаю Login
    Then Проверяю, что текст 'You logged into a secure area!' отображается на странице
    And Нажимаю Logout
    When Ввожу данные для авторизации: Логин - 'admin', пароль - 'admin'
    And Нажимаю Login
    Then Проверяю, что текст 'Your username is invalid!' отображается на странице

  Scenario Outline: Проверка отображения информации о пользователе при наведении курсора
    Given Открываю главную страницу сайта 'herokuapp.com'
    And Нажимаю на 'Hovers'
    When Навожу курсор на аватар пользователя <userNumber>
    Then Проверяю, что отображается информация о пользователе

    Examples:
      | userNumber |
      | 1          |
      | 2          |
      | 3          |

  Scenario: Проверка отображения текста после загрузки
    Given Открываю главную страницу сайта 'herokuapp.com'
    And Нажимаю на 'Dynamic Loading'
    And Нажимаю на 'Example 2: Element rendered after the fact'
    When Нажимаю на 'Start'
    And Дожидаюсь окончания загрузки
    Then Проверяю, что текст 'Hello World!' отображается на странице

  Scenario: Проверка отображения информации о нажатых клавишах
    Given Открываю главную страницу сайта 'herokuapp.com'
    And Нажимаю на 'Key Presses'
    When Ввожу символ 'a'
    Then Проверяю, что отображается информация о нажатой клавише
    When Нажимаю клавишу 'TAB'
    Then Проверяю, что отображается информация о нажатой клавише

  Scenario: Проверка отображения добавленных элементов
    Given Открываю главную страницу сайта 'herokuapp.com'
    And Нажимаю на 'Add/Remove Elements'
    When Добавляю элементы: 5
    Then Проверяю, что добавленные элементы отображаются на странице

