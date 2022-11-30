package pageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class AddRemoveElementsPage {

    private final static SelenideElement addElementButton = $(byText("Add Element"));
    public final static ElementsCollection deleteButton = $$(byText("Delete"));

    public static void addElement(int countElements) {
        for (int i = 1; i <= countElements; i++) {
            addElementButton.click();
        }
    }
}