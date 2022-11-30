package pageObjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class AuthenticationPage {
    private final static SelenideElement usernameInput = $x(".//input[@id='username']");
    private final static SelenideElement passwordInput = $x(".//input[@id='password']");
    private final static SelenideElement loginButton = $x(".//button[@type='submit']");
    private final static SelenideElement logoutButton = $x(".//a[@href='/logout']");

    public static void setLoginAndPassword(String login, String password) {
        usernameInput.val(login);
        passwordInput.val(password);
    }

    public static void clickLogin() {
        loginButton.click();
    }

    public static void clickLogout() {
        logoutButton.click();
    }
}
