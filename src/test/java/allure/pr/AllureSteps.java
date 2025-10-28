package allure.pr;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

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
        $(".Text__StyledText-sc-1klmep6-0").shouldHave(Condition.text(bug));
    }
}
