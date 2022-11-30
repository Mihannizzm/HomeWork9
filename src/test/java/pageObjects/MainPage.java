package pageObjects;

import com.codeborne.selenide.Selenide;

public class MainPage {

    private final static String BASE_URL = "http://the-internet.herokuapp.com/";

    public static void openMainPage() {
        Selenide.open(BASE_URL);
    }
}
