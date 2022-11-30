package testSteps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pageObjects.*;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;


public class HerokuappTestSteps {

    private static SelenideElement userInfo;
    private static String keyForCheck;
    private static int countElementsForCheck;

    @Given("^Открываю главную страницу сайта 'herokuapp.com'$")
    public void openMainPage() {
        MainPage.openMainPage();
        System.out.println("Открываю страницу: \"http://the-internet.herokuapp.com/\"");
    }

    @And("^Нажимаю на '(.+)'$")
    public void clickOn(String text) {
        $(byText(text)).click();
        System.out.println("Нажимаю на: " + text);
    }

    @When("^Выбираю чекбокс №1$")
    public void selectCheckbox() {
        CheckboxesPage.clickCheckbox1();
        System.out.println("Нажимаю чекбокс №1");
    }

    @Then("^Проверяю, что чекбокс был нажат$")
    public void checkCheckboxSelected() {
        CheckboxesPage.checkbox1.shouldBe(Condition.selected);
        System.out.println("Проверяю что нажат");
    }

    @When("^Ввожу данные для авторизации: Логин - '(.+)', пароль - '(.+)'$")
    public void setLoginAndPassword(String login, String password) {
        AuthenticationPage.setLoginAndPassword(login, password);
        System.out.println("\nВвожу логин: " + login);
        System.out.println("Ввожу пароль: " + password);
    }

    @And("^Нажимаю Login$")
    public void clickLogin() {
        AuthenticationPage.clickLogin();
        System.out.println("Нажимаю кнопку \"Login\"");
    }

    @And("^Нажимаю Logout$")
    public void clickLogout() {
        AuthenticationPage.clickLogout();
        System.out.println("Нажимаю кнопку \"Logout\"");
    }

    @When("^Навожу курсор на аватар пользователя (\\d+)$")
    public void hoverUserAvatar(int userNumber) {
        userInfo = HoversPage.hoverAvatar(userNumber);
        System.out.println("Навожу курсор аватар пользователя " + userNumber);
    }

    @Then("^Проверяю, что отображается информация о пользователе$")
    public void checkUserInfo() {
        userInfo.shouldBe(Condition.visible);
        System.out.println("Всплывающая информация о пользователе отображается на странице");
    }

    @And("^Дожидаюсь окончания загрузки$")
    public void waitDisappearLoader() {
        DynamicallyLoadedPage.waitDisappearLoader();
        System.out.println("Ожидаю окончания загрузки");
    }

    @Then("^Проверяю, что текст '(.+)' отображается на странице$")
    public void checkVisibleText(String text) {
        $(byText(text)).shouldBe(Condition.visible);
        System.out.println("Текст \"" + text + "\" отображается на странице");
    }

    @When("^Ввожу символ '(.+)'$")
    public void setSymbolOnInput(String symbol) {
        KeyPressesPage.setSymbolOnInput(symbol);
        System.out.println("Ввожу символ: " + symbol);
        keyForCheck = symbol.toUpperCase();
    }

    @Then("^Проверяю, что отображается информация о нажатой клавише$")
    public void checkVisibleMessage() {
        $(byText("You entered: " + keyForCheck)).shouldBe(Condition.visible);
        System.out.println("Отображается текст: \"You entered: " + keyForCheck + "\"");
    }

    @When("^Нажимаю клавишу '(.+)'$")
    public void clickKey(String key) {
        KeyPressesPage.clickKey(key);
        System.out.println("Нажимаю клавишу: " + key);
        keyForCheck = key;
    }

    @Then("^Проверяю, что добавленные элементы отображаются на странице$")
    public void checkElementsVisible() {
        Assert.assertEquals(countElementsForCheck, AddRemoveElementsPage.deleteButton.size());
        for (int i = 0; i < AddRemoveElementsPage.deleteButton.size(); i++) {
            AddRemoveElementsPage.deleteButton.get(i).shouldBe(Condition.visible);
        }
        System.out.println("Добавленные " + countElementsForCheck + " элементов отображаются на странице");
    }

    @When("^Добавляю элементы: (\\d+)$")
    public void addElements(int countElements) {
        countElementsForCheck = countElements;
        AddRemoveElementsPage.addElement(countElements);
        System.out.println("Добавляю " + countElements + " элементов");
    }
}
