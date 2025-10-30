package allure.pr;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.management.loading.ClassLoaderRepository;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;


public class Test1 {

    private static final String REPOSITORY = "taron19/Allure";
    private static final String BUG_TYPE = "bug";


    @BeforeAll
    public static void init() {
        Configuration.timeout = 10000;
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void testSelenide() {
        //для создания детальных и красивых отчетов о тестировании
        SelenideLogger.addListener("allureListener", new AllureSelenide());
        open("https://github.com/" + REPOSITORY);
        $("#issues-tab [data-content='Issues']").click();
        $(withText("#1")).shouldBe(Condition.exist);
        $("a[href='/taron19/Allure/issues/1']").shouldHave(Condition.exactText("FirstIssue"));
        $$(".prc-Text-Text-0ima0").findBy(Condition.text(BUG_TYPE)).shouldBe(Condition.visible);


    }

    @Test
    void lambdaTestAllure() {
        SelenideLogger.addListener("allureListener", new AllureSelenide());
        step("открываем нужный репозиторий GitHub",
                () -> open("https://github.com/" + REPOSITORY));
        attachment("source", webdriver().driver().source());
        step("кликаем по кнопке Issues ", () ->
                $("#issues-tab [data-content='Issues']").click());
        attachment("source", webdriver().driver().source());
        step("проверяем название Issue", () ->
                $("a[href='/taron19/Allure/issues/1']").shouldHave(Condition.exactText("FirstIssue")));
        step("проверяем что это тип ошибки" + BUG_TYPE,
                () -> $$(".prc-Text-Text-0ima0").findBy(Condition.text(BUG_TYPE)).shouldBe(Condition.visible));
    }


    @Test
    void annotationTestAllure() {
        SelenideLogger.addListener("allureListener", new AllureSelenide());

        AllureSteps allureSteps = new AllureSteps();
        allureSteps.openPage(REPOSITORY);
        allureSteps.clickIssueButton();
        allureSteps.checkNameIssue();
        allureSteps.checkBugType(BUG_TYPE);

    }
}
