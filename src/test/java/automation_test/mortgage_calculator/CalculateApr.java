package automation_test.mortgage_calculator;

import command_providers.ActOn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CalculateApr {
    WebDriver driver;
    private final By RatesLink = By.linkText("Rates");
    private final By RealAprLink = By.linkText("Real APR");
    private final By RealAprHeader = By.xpath("//*[@id='CalcForm']/h1[text()='What is the Real APR?']");
    private final By HomeValueInputField = By.name("HomeValue");
    private final By DownPaymentInputField = By.name("DownPayment");
    private final By DownPaymentInDollarRadioButton = By.id("DownPaymentSel0");
    private final By InterestRateInputField = By.name("Interest");
    private final By CalculateButton = By.name("calculate");
    private final By ActualApr = By.xpath("//*[@id='analysisDiv']/table/tbody/tr/td[contains(text(),'Actual APR')]/../td[2]");

    @BeforeTest
    public void openBrowser() {
        String url = "https://www.mortgagecalculator.org/";
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ActOn.browser(driver).openBrowser(url);
    }

    public void navigateToRatesPage() {
        //Mouse Hover to the Rates Link
        Actions actions = new Actions(driver);
        ActOn.element(driver, RatesLink).mouseHover();

        //Click on Real APR link
        ActOn.element(driver, RealAprLink).click();
    }

    @Test
    public void calculateRealApr() {
        navigateToRatesPage();

        //Wait until the locator is visible in the page
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(RealAprHeader));

        //Set timeout 15 seconds on every single test steps to be completed
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        ActOn.element(driver, HomeValueInputField).setValue("200000");

        ActOn.element(driver, DownPaymentInputField).setValue("15000");
        ActOn.element(driver, DownPaymentInDollarRadioButton).click();

        ActOn.element(driver, InterestRateInputField).setValue("3");

        ActOn.element(driver, CalculateButton).click();

        String aprRate = ActOn.element(driver, ActualApr).getText();
        Assert.assertEquals(aprRate, "3.111%");
    }

    @AfterTest
    public void closeBrowser() {
        ActOn.browser(driver).closeBrowser();
    }
}
