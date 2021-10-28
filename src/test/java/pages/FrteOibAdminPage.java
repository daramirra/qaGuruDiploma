package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class FrteOibAdminPage extends BasePage{
    public SelenideElement userProfileName(){
        return $(".header-username");
    }
}
