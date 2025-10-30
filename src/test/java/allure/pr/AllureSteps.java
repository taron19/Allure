package allure.pr;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;

public class AllureSteps {

    @Step("открываем нужный репозиторий GitHub")
    public void openPage(String repo) {
        open("https://github.com/" + repo);
    }

    @Step(" кликаем по кнопке Issues")
    public void clickIssueButton() {
        $("#issues-tab [data-content='Issues']").click();
    }

    @Step("проверяем название Issue")
    public void checkNameIssue() {
        $("a[href='/taron19/Allure/issues/1']").shouldHave(Condition.exactText("FirstIssue"));
    }

    @Step("проверяем что это тип ошибки")
    public void checkBugType(String bug) {
        $$(".prc-Text-Text-0ima0").findBy(Condition.text(bug)).shouldBe(Condition.visible);
    }

    /**
     * сериализация нашего скриншота
     * @param name
     * @return
     */
    @Attachment(value = "{name}", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot(String name) {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
