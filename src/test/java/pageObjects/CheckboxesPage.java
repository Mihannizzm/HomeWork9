package pageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;

public class CheckboxesPage {

    private final static ElementsCollection checkboxes = $$x(".//*[text()='Checkboxes']/..//input");
    public final static SelenideElement checkbox1 = checkboxes.first();
    public static void clickCheckbox1() {
        checkbox1.click();
    }
}
