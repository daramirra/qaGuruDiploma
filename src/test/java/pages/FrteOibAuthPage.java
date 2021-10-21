package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FrteOibAuthPage extends BasePage {
    final static String BASE_URL = "https://mmc.mos.ru/client-office/security/auth-rvg/login?27&service=http://mmc.mos.ru/client-office/auth/signin-cas";

    @Step("Открыть страницу 'Вход в личный кабинет иностранного гражданина'")
    public static FrteOibAuthPage openPage() {
        open(BASE_URL);
        return new FrteOibAuthPage();
    }

    @Step("Перейти на вкладку 'Почта'")
    public void openEmailTab() {
        $(By.linkText("Почта")).click();
    }

    @Step("Открытая вкладка 'Почта' содержит поле ввода идентификатора учетной записи 'Почта'")
    public void emailInputExists() {
        $(By.name("email")).shouldBe(visible);
    }

    @Step("Открытая страница содержит поле ввода идентификатора учетной записи 'Телефон'")
    public void checkPhoneInputExists(){
        $(By.name("phone")).shouldBe(visible);
    }

    @Step("Заполнить поле ввода идентификатора учетной записи 'Телефон'")
    public void setPhoneValue(String phoneValue){
        $(By.name("phone")).setValue(phoneValue);
    }

    @Step("Заполнить поле 'Пароль'")
    public void setPasswordValue(String passwordValue){
        $(By.name("password")).setValue(passwordValue);
    }

    @Step("Нажать кнопку 'Войти'")
    public void clickEnterButton(){
        $(By.tagName("button")).click();
    }

    @Step("Нажать на ссылку 'Регистрация'")
    public FrteOibRegistrationPage clickRegistrationLink(){
        $(By.linkText("Регистрация")).click();
        return new FrteOibRegistrationPage();
    }
}
