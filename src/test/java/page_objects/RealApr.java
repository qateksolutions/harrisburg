package page_objects;

import command_providers.ActOn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class RealApr extends NavigationBar {
    private final By RealAprHeader = By.xpath("//*[@id='CalcForm']/h1[text()='What is the Real APR?']");
    private final By HomeValueInputField = By.name("HomeValue");
    private final By DownPaymentInputField = By.name("DownPayment");
    private final By DownPaymentInDollarRadioButton = By.id("DownPaymentSel0");
    private final By InterestRateInputField = By.name("Interest");
    private final By CalculateButton = By.name("calculate");
    private final By ActualApr = By.xpath("//*[@id='analysisDiv']/table/tbody/tr/td[contains(text(),'Actual APR')]/../td[2]");

    private static final Logger LOGGER = LogManager.getLogger(RealApr.class);

    public RealApr(WebDriver driver) {
        super(driver);
    }

    public RealApr waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(RealAprHeader));
        LOGGER.info("Wait until the locator is visible in the page");
        return this;
    }

    public RealApr typeHomePrice(String value) {
        ActOn.element(driver, HomeValueInputField).setValue(value);
        LOGGER.info("Entered Home Price is: " + value);
        return this;
    }

    public RealApr typeDownPayment(String value) {
        ActOn.element(driver, DownPaymentInputField).setValue(value);
        LOGGER.info("Entered DownPayment is: " + value);
        return this;
    }

    public RealApr selectDownPaymentInDollar() {
        ActOn.element(driver, DownPaymentInDollarRadioButton).click();
        LOGGER.info("Clicking on the Radio Button $");
        return this;
    }

    public RealApr typeInterestRate(String value) {
        ActOn.element(driver, InterestRateInputField).setValue(value);
        LOGGER.info("Entered Interest Rate is: " + value);
        return this;
    }

    public RealApr clickOnCalculateButton() {
        ActOn.element(driver, CalculateButton).click();
        LOGGER.info("Clicking on the Calculate Button");
        return this;
    }

    public RealApr validateAprRate(String expectedValue) {
        String aprRate = ActOn.element(driver, ActualApr).getText();
        LOGGER.info("Validating APR rate is: " + expectedValue);
        Assert.assertEquals(aprRate, expectedValue);
        return this;
    }
}
