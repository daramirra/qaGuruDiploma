# Проект по автоматизации тестирования форм

### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:heavy_check_mark: [входа в Систему](https://sdo-oib-test.it2g.ru/admin/)

### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:heavy_check_mark: [восстановления пароля пользователя](https://sdo-oib-test.it2g.ru/auth-sdo/recovery-password?service=https://sdo-oib-test.it2g.ru/admin/shiro-cas)

 > *Данные формы являются частью разработки **[Системы обеспечения информационной безопасности "ВИЗОР"](http://visor.frte.ru/solutions/)**, реализованной в одном из вндерений компанией **[ФРЕШТЕХ](http://visor.frte.ru/solutions/)**, сотрудником которой я являюсь на данный момент.*

## :rocket: Технологии и инструменты
<p  align="center"
   
  <code><img width="5%" title="IntelliJ IDEA" src="images/IDEA-logo.svg"></code>
  <code><img width="5%" title="Java" src="images/java-logo.svg"></code>
  <code><img width="5%" title="Selenide" src="images/selenide-logo.svg"></code>
  <code><img width="5%" title="REST-Assured" src="images/rest-assured-logo.svg"></code>
  <code><img width="5%" title="Selenoid" src="images/selenoid-logo.svg"></code>
  <code><img width="5%" title="Gradle" src="images/gradle-logo.svg "></code>
  <code><img width="5%" title="JUnit5" src="images/junit5-logo.svg"></code>
  <code><img width="5%" title="Allure Report" src="images/allure-Report-logo.svg"></code>
  <code><img width="5%" title="Allure TestOps" src="images/allure-ee-logo.svg"></code>
  <code><img width="5%" title="Github" src="images/git-logo.svg"></code>
  <code><img width="5%" title="Jenkins" src="images/jenkins-logo.svg"></code>
  <code><img width="5%" title="Jira" src="images/jira-logo.svg"></code>
  <code><img width="5%" title="Telegram" src="images/Telegram.svg"></code>
</p>

>*В данном проекте автотесты написаны на <code><strong>*Java*</strong></code> с использованием фреймворка <code><strong>*Selenide*</strong></code> для UI-тестов и <code><strong>*REST-Assured*</strong></code> для API-тестов.*
>
>*Для сборки проекта используется <code><strong>*Gradle*</strong></code>.*
> 
>*<code><strong>*JUnit 5*</strong></code> используется как фреймворк для модульного тестирования.*
>   
>*Запуск тестов выполняется из <code><strong>*Jenkins*</strong></code>.*
>    
>*<code><strong>*Selenoid*</strong></code> используется для запуска браузеров в контейнерах  <code><strong>*Docker*</strong></code>.*
> 
>*<code><strong>*Allure Report, Allure TestOps, Jira, Telegram Bot*</strong></code> используются для визуализации результатов тестирования.*

## :spiral_notepad: Реализованы проверки

### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; UI
>- [x] *Проверка заголовка страницы входа в Систему*
>- [x] *Лог консоли браузера на странице входа в Систему не содержит ошибок*
>- [x] *Успешный вход в Систему*
>- [x] *Попытка входа в Систему вне рабочего расписания доступа*
>- [x] *Попытка входа в Систему с неразрешенного IP-адреса*
>- [x] *Попытка входа в Систему под пользователем с истекшим сроком доступа*
>- [x] *Попытка входа в Систему под пользователем с отключенной учетной записью*
>- [x] *Попытка входа в Систему с невалидным значением логина*
>- [x] *Попытка входа в Систему без указания пароля*
>- [x] *Переход на страницу 'Восстановление пароля'*

### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; API
>- [x] *Успешное начало процедуры восстановления пароля пользователя*
>- [x] *Ошибка начала процедуры восстановления пароля пользователя без указания логина*
>- [x] *Ошибка начала процедуры восстановления пароля пользователя по неизвестному логину*
>- [x] *Ошибка начала процедуры восстановления пароля пользователя с не подтвержденным адресом электронной почты*
>- [x] *Ошибка начала процедуры восстановления пароля пользователя с не указанным адресом электронной почты*
>- [x] *Ошибка начала процедуры восстановления пароля пользователя с отключенной учетной записью*

## :computer: Запуск тестов из терминала

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:green_circle:&nbsp;&nbsp;*Запуск тестов с заполненным remote.properties:*
```bash
gradle clean test
```
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:green_circle:&nbsp;&nbsp;*Запуск тестов без заполненного remote.properties:*
```bash
gradle clean test -DremoteDriverUrl=https://[selenoidUser]:[selenoidPwd]@selenoid.autotests.cloud/wd/hub/ 
-DvideoStorage=https://selenoid.autotests.cloud/video/ -Dthreads=5 -DaccountPassword=[passwordValue]
```
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:green_circle:&nbsp;&nbsp;*Запуск тестов в несколько потоков:*
```bash
gradle clean -Dthreads=[threadsValue]
```
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:green_circle:&nbsp;&nbsp;*Сформировать allure отчет:*
```bash
allure serve build/allure-results
```

## <img width="4%" title="Jenkins" src="images/jenkins-logo.svg"> Запуск тестов в [Jenkins](https://jenkins.autotests.cloud/job/C07-daramirra-diploma/)
### :pushpin: Параметры сборки

    BROWSER (по умолчанию chrome)
    BROWSER_VERSION (по умолчанию 91.0)
    BROWSER_SIZE (по умолчанию 1920x1080)
    REMOTE_DRIVER_URL (url-адрес selenoid. по умолчанию selenoid.autotests.cloud)
    TREADS (по умолчанию 5)
    ALLURE_NOTIFICATIONS_VERSION (по умолчанию 3.1.1)
    ACCOUNT_PASSWORD (пароль учетной записи пользователя для тестов)
    
<p align="center">
  <img src="images/job_param.png" alt="job" width="800">
</p>

## <img width="4%" title="Allure Report" src="images/allure-Report-logo.svg"> Отчет о результатах тестирования в [Allure Report](https://jenkins.autotests.cloud/job/C07-daramirra-diploma/8/allure/)
### :pushpin: Общая информация
<p align="center">
  <img src="images/Allure Report.png" alt="Allure Report" width="900">
</p>

### :pushpin: Список тестов c описанием шагов и визуализацией результатов
<p align="center">
  <img src="images/Allure Report steps.png" alt="Allure Report" width="900">
</p>

## <img width="4%" title="Allure TestOPS" src="images/allure-ee-logo.svg"> Интеграция с [Allure TestOps](https://allure.autotests.cloud/project/651/dashboards) 
### :pushpin: Основной дашборд
<p align="center">
  <img src="images/allureTestOPS dashboards.png" alt="dashboards" width="900">
</p>

### :pushpin: Дашборд по разным типам тестов
<p align="center">
  <img src="images/allureTestOPS dashboards test types.png" alt="dashboards test types" width="900">
</p>

### :pushpin: Дашборд по команде
<p align="center">
  <img src="images/allureTestOPS dashboards team.png" alt="dashboards team" width="900">
</p>

### :pushpin: Запуски
<p align="center">
  <img src="images/allureTestOPS launches.png" alt="launches" width="900">
</p>

### :pushpin: Результат запуска
<p align="center">
  <img src="images/allureTestOPS launch.png" alt="launch" width="900">
</p>

### :pushpin: Тест-кейсы
<p align="center">
  <img src="images/Test cases.png" alt="test cases" width="900">
</p>

## <img width="4%" title="Jira" src="images/jira-logo.svg"> Интеграция с [Jira](https://jira.autotests.cloud/browse/HOMEWORK-269)
<p align="center">
  <img src="images/allureTestOPS jira.png" alt="jira" width="1000">
</p>

## <img width="4%" title="Selenoid" src="images/selenoid-logo.svg"> Пример запуска теста в Selenoid 
<p align="center">
  <img src="images/video.gif" alt="video" width="900">
</p>

## <img width="4%" title="Telegram" src="images/Telegram.svg"> Уведомления в Telegram 
<p align="center">
  <img src="images/tlgrm.png" alt="Telegram" width="900">
</p>
<br><br>

