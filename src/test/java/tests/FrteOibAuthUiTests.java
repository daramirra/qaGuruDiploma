package tests;

import annotations.JiraIssue;
import annotations.JiraIssues;
import annotations.Layer;
import com.github.javafaker.Faker;
import config.Project;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import pages.FrteOibAdminPage;
import pages.FrteOibAuthPage;
import pages.FrteOibRecoveryPasswordPage;

import static com.codeborne.selenide.Condition.text;
import static helpers.DriverUtils.getConsoleLogs;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FrteOibAuthUiTests extends TestBase {

    String passwordValue = Project.config.accountPassword();

    @Test
    @Owner("dlapshinova")
    @Layer("web")
    @Story("Успешный вход в Систему")
    @Tags({@Tag("web"), @Tag("smoke")})
    @Feature("Аутентификация пользователей")
    @JiraIssues({@JiraIssue("HOMEWORK-269")})
    @DisplayName("Проверка заголовка страницы входа в Систему")
    void checkTitleTest() {
        FrteOibAuthPage frteOibAuthPage = FrteOibAuthPage.openPage();
        String expectedTitle = "СЛУЖБА АУТЕНТИФИКАЦИИ ПОЛЬЗОВАТЕЛЕЙ";

        step("Заголовок страницы входа в Систему содержит '" + expectedTitle + "'", () -> {
            assertThat(frteOibAuthPage.getTitle()).isEqualTo(expectedTitle);
        });
    }

    @Test
    @Owner("dlapshinova")
    @Layer("web")
    @Story("Успешный вход в Систему")
    @Tags({@Tag("web"), @Tag("smoke")})
    @Feature("Аутентификация пользователей")
    @JiraIssues({@JiraIssue("HOMEWORK-269")})
    @DisplayName("Лог консоли браузера на странице входа в Систему не содержит ошибок")
    void consoleLogShouldNotHaveErrors() {
        FrteOibAuthPage.openPage();

        step("Страница входа в Систему не содержит ошибок в логах консоли браузера", () -> {
            String consoleLogs = getConsoleLogs();
            assertThat(consoleLogs).doesNotContain("SERVE");
        });
    }

    @Test
    @Owner("dlapshinova")
    @Layer("web")
    @Story("Успешный вход в Систему")
    @Tags({@Tag("web"), @Tag("smoke")})
    @Feature("Аутентификация пользователей")
    @JiraIssues({@JiraIssue("HOMEWORK-269")})
    @DisplayName("Успешный вход в Систему")
    void loginSuccessful() {
        FrteOibAuthPage frteOibAuthPage = FrteOibAuthPage.openPage();
        frteOibAuthPage.setLoginValue("Iapolzovatel");
        frteOibAuthPage.setPasswordValue(passwordValue);
        frteOibAuthPage.clickEnterButton();

        String expectedUserName = "Япользователь для автотестов";
        step("Профиль пользователя содержит '" + expectedUserName + "'", () -> {
            FrteOibAdminPage frteOibAdminPage = new FrteOibAdminPage();
            frteOibAdminPage.userProfileName().shouldHave(text(expectedUserName));
        });
    }

    @Test
    @Owner("dlapshinova")
    @Layer("web")
    @Story("Ограничения доступа")
    @Tags({@Tag("web"), @Tag("smoke")})
    @Feature("Аутентификация пользователей")
    @JiraIssues({@JiraIssue("HOMEWORK-269")})
    @DisplayName("Попытка входа в Систему вне рабочего расписания доступа")
    void loginOutsideWorkSchedule() {
        FrteOibAuthPage frteOibAuthPage = FrteOibAuthPage.openPage();
        frteOibAuthPage.setLoginValue("Iapolzovatel.3");
        frteOibAuthPage.setPasswordValue(passwordValue);
        frteOibAuthPage.clickEnterButton();
        frteOibAuthPage.checkAlertContainsMessage("В данный момент вход в систему указанному пользователю недоступен");
        resetLoginAttemptsBeforeCapcha(frteOibAuthPage);
    }

    @Test
    @Owner("dlapshinova")
    @Layer("web")
    @Story("Ограничения доступа")
    @Tags({@Tag("web"), @Tag("smoke")})
    @Feature("Аутентификация пользователей")
    @JiraIssues({@JiraIssue("HOMEWORK-269")})
    @DisplayName("Попытка входа в Систему с неразрешенного IP-адреса")
    void loginWithUnresolvedIPAddress() {
        FrteOibAuthPage frteOibAuthPage = FrteOibAuthPage.openPage();
        frteOibAuthPage.setLoginValue("Iapolzovatel.1");
        frteOibAuthPage.setPasswordValue(passwordValue);
        frteOibAuthPage.clickEnterButton();
        frteOibAuthPage.checkAlertContainsMessage("Вход в систему с этой рабочей станции недоступен");
        resetLoginAttemptsBeforeCapcha(frteOibAuthPage);
    }

    @Test
    @Owner("dlapshinova")
    @Layer("web")
    @Story("Ограничения доступа")
    @Tags({@Tag("web"), @Tag("smoke")})
    @Feature("Аутентификация пользователей")
    @JiraIssues({@JiraIssue("HOMEWORK-269")})
    @DisplayName("Попытка входа в Систему под пользователем с истекшим сроком доступа")
    void loginWithExpiredUser() {
        FrteOibAuthPage frteOibAuthPage = FrteOibAuthPage.openPage();
        frteOibAuthPage.setLoginValue("Iapolzovatel.2");
        frteOibAuthPage.setPasswordValue(passwordValue);
        frteOibAuthPage.clickEnterButton();
        frteOibAuthPage.checkAlertContainsMessage("Срок действия учетной записи истек");
        resetLoginAttemptsBeforeCapcha(frteOibAuthPage);
    }

    @Test
    @Owner("dlapshinova")
    @Layer("web")
    @Story("Ограничения доступа")
    @Tags({@Tag("web"), @Tag("smoke")})
    @Feature("Аутентификация пользователей")
    @JiraIssues({@JiraIssue("HOMEWORK-269")})
    @DisplayName("Попытка входа в Систему под пользователем с отключенной учетной записью")
    void loginWithDisabledAccount() {
        FrteOibAuthPage frteOibAuthPage = FrteOibAuthPage.openPage();
        frteOibAuthPage.setLoginValue("Iapolzovatel.4");
        frteOibAuthPage.setPasswordValue(passwordValue);
        frteOibAuthPage.clickEnterButton();
        frteOibAuthPage.checkAlertContainsMessage("Учетная запись отключена");
        resetLoginAttemptsBeforeCapcha(frteOibAuthPage);
    }

    @Test
    @Owner("dlapshinova")
    @Layer("web")
    @Story("Попытка входа в Систему с невалидными данными")
    @Tags({@Tag("web"), @Tag("smoke")})
    @Feature("Аутентификация пользователей")
    @JiraIssues({@JiraIssue("HOMEWORK-269")})
    @DisplayName("Попытка входа в Систему с невалидным значением логина")
    void inputInvalidLoginShowErrorMessage() {
        Faker faker = new Faker();

        FrteOibAuthPage frteOibAuthPage = FrteOibAuthPage.openPage();
        frteOibAuthPage.checkLoginInputExists();
        frteOibAuthPage.setLoginValue(faker.number().digits(3));
        frteOibAuthPage.setPasswordValue(faker.number().digits(3));
        frteOibAuthPage.clickEnterButton();
        frteOibAuthPage.checkAlertContainsMessage("Введены неверные логин или пароль");
        resetLoginAttemptsBeforeCapcha(frteOibAuthPage);
    }

    @Test
    @Owner("darina")
    @Layer("web")
    @Story("Попытка входа в Систему с невалидными данными")
    @Tags({@Tag("web"), @Tag("smoke")})
    @Feature("Вход в личный кабинет")
    @JiraIssues({@JiraIssue("HOMEWORK-269")})
    @DisplayName("Попытка входа в Систему без указания пароля")
    void passwordCouldNotBeEmpty() {
        Faker faker = new Faker();

        FrteOibAuthPage frteOibAuthPage = FrteOibAuthPage.openPage();
        frteOibAuthPage.checkLoginInputExists();
        frteOibAuthPage.setLoginValue(faker.number().digits(3));
        frteOibAuthPage.clickEnterButton();
        frteOibAuthPage.checkAlertContainsMessage("Требуется заполнить поле: Пароль");
        resetLoginAttemptsBeforeCapcha(frteOibAuthPage);
    }

    @Test
    @Owner("darina")
    @Layer("web")
    @Story("Открытие страниц")
    @Tags({@Tag("web"), @Tag("smoke")})
    @Feature("Восстановление пароля")
    @JiraIssues({@JiraIssue("HOMEWORK-269")})
    @DisplayName("Переход на страницу 'Восстановление пароля'")
    void checkOpenRecoveryPasswordPage() {
        FrteOibAuthPage frteOibAuthPage = FrteOibAuthPage.openPage();
        String expectedAuthTitle = "СЛУЖБА АУТЕНТИФИКАЦИИ ПОЛЬЗОВАТЕЛЕЙ";
        step("Заголовок страницы входа содержит '" + expectedAuthTitle + "'", () -> {
            assertThat(frteOibAuthPage.getTitle()).isEqualTo(expectedAuthTitle);
        });

        FrteOibRecoveryPasswordPage frteOibRecoveryPasswordPage = frteOibAuthPage.clickRecoveryPasswordLink();
        String expectedRecoveryPasswordTitle = "СЛУЖБА ВОССТАНОВЛЕНИЯ ПАРОЛЯ ПОЛЬЗОВАТЕЛЯ";
        step("Заголовок страницы содержит '" + expectedRecoveryPasswordTitle + "'", () -> {
            assertThat(frteOibRecoveryPasswordPage.getTitle()).isEqualTo(expectedRecoveryPasswordTitle);
        });
    }

    @Step("Сбросить количество попыток входа в Систему до вызова капчи")
    private void resetLoginAttemptsBeforeCapcha(FrteOibAuthPage frteOibAuthPage) {
        frteOibAuthPage.setLoginValue("Iapolzovatel");
        frteOibAuthPage.setPasswordValue(passwordValue);
        frteOibAuthPage.clickEnterButton();
    }
}