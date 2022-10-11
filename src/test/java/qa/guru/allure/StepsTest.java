package qa.guru.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class StepsTest {

    private final String REPOSITORY = "eroshenkoam/allure-example";
    private final Integer ISSUE_NUMBER = 1;

    @Test
    public void testLambdaSteps() {
       SelenideLogger.addListener("allure", new AllureSelenide());
        step ("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step ("Ищем репозиторий по имени " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step ("В результатах поиска переходим по ссылке репозитория " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step ("Открываем таб Issues", () -> {
            $(partialLinkText("Issues")).click();
        });
        step ("Проверяем, что существует Issue с номером " + ISSUE_NUMBER, () -> {
            $(withText("#" +ISSUE_NUMBER)).should(Condition.exist);
        });

    }

    @Test
    public void testAnnotatedSteps() {
        WebSteps steps = new WebSteps();
        SelenideLogger.addListener("allure", new AllureSelenide());
        steps.openMaiPage();
        steps.searchForRepository(REPOSITORY);
        steps.openRepositoryLink (REPOSITORY);
        steps.openIssueTab();
        steps.shouldSeeIssueWithNumber (ISSUE_NUMBER);
    }
}
