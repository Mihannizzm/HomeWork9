package pageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;

public class HoversPage {

    private final static ElementsCollection usersAvatars = $$x(".//img[@alt='User Avatar']");


    public static SelenideElement hoverAvatar(int userNumber) {
        int userIndex = userNumber - 1;
        usersAvatars.get(userIndex).hover();
        return $(byText("name: user" + userNumber));
    }

}
