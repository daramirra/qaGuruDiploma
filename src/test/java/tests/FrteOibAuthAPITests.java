package tests;

import annotations.JiraIssue;
import annotations.JiraIssues;
import annotations.Layer;
import api.PwdRecoveryClient;
import api.StartPwdRecoveryRequest;
import api.StartPwdRecoveryResponse;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class FrteOibAuthAPITests {

    @Test
    @Owner("dlapshinova")
    @Layer("api")
    @Tags({@Tag("api"), @Tag("smoke")})
    @Feature("Восстановление пароля пользователя")
    @JiraIssues({@JiraIssue("HOMEWORK-269")})
    @DisplayName("Успешное начало процедуры восстановления пароля пользователя")
    public void successfulStartPwdRecovery() {
        PwdRecoveryClient pwdRecoveryClient = new PwdRecoveryClient();
        StartPwdRecoveryRequest request = new StartPwdRecoveryRequest("Iapolzovatel");
        StartPwdRecoveryResponse startPwdRecoveryResponse = pwdRecoveryClient.startPwdRecoveryRecovery(request);

        step("В качестве ответа возвращается замаскированный адрес электронной почты 't***1@frte.ru'", () -> {
            assertThat(startPwdRecoveryResponse.getMaskedEmail()).isEqualTo("t***1@frte.ru");
        });
    }

    @Test
    @Owner("dlapshinova")
    @Layer("api")
    @Tags({@Tag("api"), @Tag("smoke")})
    @Feature("Восстановление пароля пользователя")
    @JiraIssues({@JiraIssue("HOMEWORK-269")})
    @DisplayName("Ошибка начала процедуры восстановления пароля пользователя без указания логина")
    public void errorStartPwdRecoveryWithEmptyLogin() {
        PwdRecoveryClient pwdRecoveryClient = new PwdRecoveryClient();
        StartPwdRecoveryRequest request = new StartPwdRecoveryRequest(null);
        StartPwdRecoveryResponse startPwdRecoveryResponse = pwdRecoveryClient.startPwdRecoveryRecovery(request);

        step("Статус ответа возвращает ошибку 'VALIDATION_ERROR'", () -> {
            assertThat(startPwdRecoveryResponse.getErrorCode()).isEqualTo("VALIDATION_ERROR");
        });
    }

    @Test
    @Owner("dlapshinova")
    @Layer("api")
    @Tags({@Tag("api"), @Tag("smoke")})
    @Feature("Восстановление пароля пользователя")
    @JiraIssues({@JiraIssue("HOMEWORK-269")})
    @DisplayName("Ошибка начала процедуры восстановления пароля пользователя по неизвестному логину")
    public void errorStartPwdRecoveryWithUnknownLogin() {
        PwdRecoveryClient pwdRecoveryClient = new PwdRecoveryClient();
        StartPwdRecoveryRequest request = new StartPwdRecoveryRequest("ghgfhg");
        StartPwdRecoveryResponse startPwdRecoveryResponse = pwdRecoveryClient.startPwdRecoveryRecovery(request);

        step("Статус ответа возвращает ошибку 'ACCOUNT_NOT_FOUND'", () -> {
            assertThat(startPwdRecoveryResponse.getErrorCode()).isEqualTo("ACCOUNT_NOT_FOUND");
        });
    }

    @Test
    @Owner("darina")
    @Layer("api")
    @Tags({@Tag("api"), @Tag("smoke")})
    @Feature("Восстановление пароля пользователя")
    @JiraIssues({@JiraIssue("HOMEWORK-269")})
    @DisplayName("Ошибка начала процедуры восстановления пароля пользователя с не указанным адресом электронной почты")
    public void errorStartPwdRecoveryWithEmptyEmail() {
        PwdRecoveryClient pwdRecoveryClient = new PwdRecoveryClient();
        StartPwdRecoveryRequest request = new StartPwdRecoveryRequest("Iapolzovatel.3");
        StartPwdRecoveryResponse startPwdRecoveryResponse = pwdRecoveryClient.startPwdRecoveryRecovery(request);

        step("Статус ответа возвращает ошибку 'USER_NOT_VALID'", () -> {
            assertThat(startPwdRecoveryResponse.getErrorCode()).isEqualTo("USER_NOT_VALID");
        });
    }

    @Test
    @Owner("darina")
    @Layer("api")
    @Tags({@Tag("api"), @Tag("smoke")})
    @Feature("Восстановление пароля пользователя")
    @JiraIssues({@JiraIssue("HOMEWORK-269")})
    @DisplayName("Ошибка начала процедуры восстановления пароля пользователя с не подтвержденным адресом электронной почты")
    public void errorStartPwdRecoveryWithNotСonfirmedEmail() {
        PwdRecoveryClient pwdRecoveryClient = new PwdRecoveryClient();
        StartPwdRecoveryRequest request = new StartPwdRecoveryRequest(" Iapolzovatel.1");
        StartPwdRecoveryResponse startPwdRecoveryResponse = pwdRecoveryClient.startPwdRecoveryRecovery(request);

        step("Статус ответа возвращает ошибку 'USER_NOT_VALID'", () -> {
            assertThat(startPwdRecoveryResponse.getErrorCode()).isEqualTo("USER_NOT_VALID");
        });
    }

    @Test
    @Owner("darina")
    @Layer("api")
    @Tags({@Tag("api"), @Tag("smoke")})
    @Feature("Восстановление пароля пользователя")
    @JiraIssues({@JiraIssue("HOMEWORK-269")})
    @DisplayName("Ошибка начала процедуры восстановления пароля пользователя с отключенной учетной записью")
    public void errorStartPwdRecoveryWithDisableLogin() {
        PwdRecoveryClient pwdRecoveryClient = new PwdRecoveryClient();
        StartPwdRecoveryRequest request = new StartPwdRecoveryRequest("Iapolzovatel.4");
        StartPwdRecoveryResponse startPwdRecoveryResponse = pwdRecoveryClient.startPwdRecoveryRecovery(request);

        step("Статус ответа возвращает ошибку 'ACCOUNT_NOT_VALID'", () -> {
            assertThat(startPwdRecoveryResponse.getErrorCode()).isEqualTo("ACCOUNT_NOT_VALID");
        });
    }
}