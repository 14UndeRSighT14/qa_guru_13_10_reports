package qa.guru.allure;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;

public class AttachmentsTest {

    private final String REPOSITORY = "eroshenkoam/allure-example";
    private final Integer ISSUE_NUMBER = 1;

    @Test
    public void testLambdaSteps() {
       SelenideLogger.addListener("allure", new AllureSelenide());
        step ("Открываем главную страницу", () -> {
            open("https://github.com");
            attachment("Source", webdriver().driver().source());
            Allure.getLifecycle().addAttachment(
                    "Screenshot", "image/png", "png",
                    ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES));
        });
    }

    @Test
    public void testAnnotatedSteps() {
        WebSteps steps = new WebSteps();
        SelenideLogger.addListener("allure", new AllureSelenide());
        steps.openMaiPage();
        steps.takeScreenshot();
    }
}
