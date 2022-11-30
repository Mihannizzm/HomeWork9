package pageObjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;

public class KeyPressesPage {

    private final static SelenideElement inputText = $x(".//input[@type='text']");

    public static void setSymbolOnInput(String symbol) {
        inputText.sendKeys(symbol);
    }

    public static void clickKey(String key) {
        inputText.sendKeys(Keys.valueOf(key));
    }
}
