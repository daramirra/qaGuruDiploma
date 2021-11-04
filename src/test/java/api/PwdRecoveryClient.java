package api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;

import static io.restassured.RestAssured.given;

public class PwdRecoveryClient {

    @Step("Инициировать начало процесса восстановления пароля пользователя")
    public StartPwdRecoveryResponse startPwdRecoveryRecovery(StartPwdRecoveryRequest request) {
        StartPwdRecoveryResponse response = given()
                .baseUri("https://sdo-oib-test.it2g.ru")
                .basePath("/auth-sdo/api/startRecovery")
                .contentType(ContentType.JSON)
                .body(request, ObjectMapperType.GSON)
                .post()
                .then()
                .assertThat().statusCode(200)
                .extract().body().as(StartPwdRecoveryResponse.class, ObjectMapperType.GSON);
        return response;
    }
}
