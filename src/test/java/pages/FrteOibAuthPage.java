package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FrteOibAuthPage extends BasePage {
    final static String BASE_URL = "https://sdo-oib-test.it2g.ru/admin/";

    @Step("Открыть страницу входа в систему")
    public static FrteOibAuthPage openPage() {
        open(BASE_URL);
        return new FrteOibAuthPage();
    }

    @Step("Открытая страница содержит поле 'Логин'")
    public void checkLoginInputExists() {
        $("#identifier").shouldBe(visible);
    }

    @Step("Заполнить поле 'Логин'")
    public void setLoginValue(String loginValue) {
        SelenideElement login = $("#identifier");
        clearInputValue(login);
        login.setValue(loginValue);
    }

    @Step("Заполнить поле 'Пароль'")
    public void setPasswordValue(String passwordValue) {
        SelenideElement password = $("#password");
        clearInputValue(password);
        password.setValue(passwordValue);
    }

    @Step("Нажать кнопку 'Войти'")
    public void clickEnterButton() {
        $(".loginBtn").click();
    }

    @Step("Нажать на ссылку 'Восстановить пароль'")
    public FrteOibRecoveryPasswordPage clickRecoveryPasswordLink() {
        $(".recovery").click();
        return new FrteOibRecoveryPasswordPage();
    }

    private void clearInputValue(SelenideElement element) {
        element.sendKeys(Keys.CONTROL + "A");
        element.sendKeys(Keys.BACK_SPACE);
    }
}
