package allure.pr;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;


public class TestIssueInGitHub {

    private static final String REPOSITORY = "taron19/Allure";
    private static final String BUG_TYPE = "bug";


    @BeforeAll
    public static void init() {
        Configuration.timeout = 10000;
        Configuration.pageLoadStrategy = "eager";
        step("открываем нужный репозиторий GitHub",
                () -> open("https://github.com/" + REPOSITORY));
        SelenideLogger.addListener("allureListener", new AllureSelenide());
    }

    @Test
    @Owner("Taron")
    @DisplayName("Проверка Issue в репозитории github")
    @Link(value = "GitHub Issue", url = "https://github.com/taron19/Allure/issues")
    @Severity(SeverityLevel.CRITICAL)           // насколько важен тест
    @Feature("issue в репозитории 1")               // крупная функциональность
    @Story("Создание issue 1")
        // конкретный сценарий/история внутри фичи
    void testSelenide() {
        $("#issues-tab [data-content='Issues']").click();
        $(withText("#1")).shouldBe(Condition.exist);
        $("a[href='/taron19/Allure/issues/1']").shouldHave(Condition.exactText("FirstIssue"));
        $$(".prc-Text-Text-0ima0").findBy(Condition.text(BUG_TYPE)).shouldBe(Condition.visible);

    }

    @Test
    @Owner("Taron")
    @DisplayName("Проверка Issue в репозитории github")
    @Link(value = "GitHub Issue", url = "https://github.com/taron19/Allure/issues")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("issue в репозитории 2")
    @Story("Создание issue 2")
    void lambdaTestAllure() {

        attachment("source", webdriver().driver().source());

        step("кликаем по кнопке Issues ", () ->
                $("#issues-tab [data-content='Issues']").click());
        step("проверяем название Issue", () ->
                $("a[href='/taron19/Allure/issues/1']").shouldHave(Condition.exactText("FirstIssue")));
        step("проверяем что это тип ошибки" + BUG_TYPE,
                () -> $$(".prc-Text-Text-0ima0").findBy(Condition.text(BUG_TYPE)).shouldBe(Condition.visible));
    }


    @Test
    @Owner("Taron")
    @DisplayName("Проверка Issue в репозитории github")
    @Link(value = "GitHub Issue", url = "https://github.com/taron19/Allure/issues")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("issue в репозитории 3")
    @Story("Создание issue 3")
    void annotationTestAllure() {


        AllureSteps allureSteps = new AllureSteps();
        allureSteps.takeScreenshot("после открытия страницы");
        //Фото можно также делать
        //attachment("Screenshot", new ByteArrayInputStream(allureSteps.takeScreenshot("после открытия страницы")));
        allureSteps.clickIssueButton();
        allureSteps.checkNameIssue();
        allureSteps.checkBugType(BUG_TYPE);
        allureSteps.takeScreenshot("после проверки типа ошибки");

        //в отчете будет 2 скриншота

    }
}
