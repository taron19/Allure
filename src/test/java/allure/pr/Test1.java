package allure.pr;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class Test1 {


    @BeforeAll
    public static void init() {
        Configuration.timeout = 6000;
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://github.com/taron19";
    }

    @Test
    void testSelenide() {
        //для создания детальных и красивых отчетов о тестировании
        SelenideLogger.addListener("allureListener", new AllureSelenide());
        $(".header-search-input").click();
        $(".header-search-input").sendKeys("");
        $(".header-search-input").submit();
        $(withText("#2")).shouldBe(Condition.exist);
    }

    @Test
    void lambdaTestAllure(){

    }
}
