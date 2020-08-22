package automation_test;

import api_test.SimpleDeleteTest;
import command_providers.ActOn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import page_objects.Home;

public class CalculateApr {
    private static final Logger LOGGER = LogManager.getLogger(CalculateApr.class);
    WebDriver driver;

    @BeforeMethod
    public void openBrowser() {
        String url = "https://www.mortgagecalculator.org/";
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        LOGGER.info("---------Calculate Real Apr-------");
        ActOn.browser(driver).openBrowser(url);
    }

    @Test
    public void calculateRealApr() {
        new Home(driver)
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
        LOGGER.info("---------End of Calculate Real Apr-------");
    }
}
