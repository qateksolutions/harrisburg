package automation_test.mortgage_calculator;

import command_providers.ActOn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import page_objects.MenuNavigation;

import java.util.concurrent.TimeUnit;

public class CalculateApr {
    WebDriver driver;

    @BeforeMethod
    public void openBrowser() {
        String url = "https://www.mortgagecalculator.org/";
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ActOn.browser(driver).openBrowser(url);
    }

    @Test
    public void calculateRealApr() {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        new MenuNavigation(driver)
                .mouseHoverToRates()
                .navigateToRealApr()
                .waitForPageToLoad()
                .typeHomePrice("200000")
                .typeDownPayment("15000")
                .selectDownPaymentInDollar()
                .typeInterestRate("3")
                .clickOnCalculateButton()
                .validateAprRate("3.130%");
    }

    @AfterMethod
    public void closeBrowser() {
        ActOn.browser(driver).closeBrowser();
    }
}
