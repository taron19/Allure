package allure.pr;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;

public class AllureSteps {

    private final SelenideElement issuesTab = $("#issues-tab [data-content='Issues']");
    private final SelenideElement checkName = $("a[href='/taron19/Allure/issues/1']");
    private final String text = "FirstIssue";
    private final String openURL = "https://github.com/";
    private final ElementsCollection bugCollection = elements(".prc-Text-Text-0ima0");


   /* @Step("открываем нужный репозиторий GitHub")
    public void openPage(String repo) {
        open(openURL + repo);
    }*/

    @Step(" кликаем по кнопке Issues")
    public void clickIssueButton() {
        $(issuesTab).click();
    }

    @Step("проверяем название Issue")
    public void checkNameIssue() {
        $(checkName).shouldHave(Condition.exactText(text));
    }

    @Step("проверяем что это тип ошибки")
    public void checkBugType(String bug) {
        $$(bugCollection).findBy(Condition.text(bug)).shouldBe(Condition.visible);
    }

    /**
     * сериализация нашего скриншота
     *
     * @param name
     * @return
     */
    @Attachment(value = "{name}", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot(String name) {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
