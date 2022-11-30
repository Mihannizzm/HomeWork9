package pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class DynamicallyLoadedPage {
    private final static SelenideElement loader = $x(".//div[@id='loading']");

    public static void waitDisappearLoader() {
        loader.shouldBe(Condition.disappear, Duration.ofSeconds(30));
    }
}
