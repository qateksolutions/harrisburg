package automation_test.mortgage_calculator;

import command_providers.ActOn;
import command_providers.AssertThat;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.DateUtils;

import java.util.concurrent.TimeUnit;

public class CalculateMonthlyPayment {
    WebDriver driver;
    private final By HomeValueInputField = By.id("homeval");
    private final By DownPaymentInputField = By.id("downpayment");
    private final By DownPaymentInDollarRadioButton = By.xpath("//*[@id='calc']//input[@name='param[downpayment_type]'][@value='money']");
    private final By LoanAmountInputField = By.id("loanamt");
    private final By InterestRateInputField = By.id("intrstsrate");
    private final By LoanTermInputField = By.id("loanterm");
    private final By StartDateMonthDropDown = By.name("param[start_month]");
    private final By StartDateYearInputField = By.id("start_year");
    private final By PropertyTaxInputField = By.id("pptytax");
    private final By PmiInputField = By.id("pmi");
    private final By HomeInsInputField = By.id("hoi");
    private final By MonthlyHoaInputField = By.id("hoa");
    private final By LoanTypeDropDown = By.name("param[milserve]");
    private final By BuyOrRefiDropDown = By.name("param[refiorbuy]");
    private final By CreditRatingDropDown = By.name("param[credit_rating]");
    private final By CalculateButton = By.name("cal");
    private final By TotalMonthlyPayment = By.xpath("//*[@id='calc']//h3[contains(text(),'$1,611.85')]");


    @BeforeTest
    public void openBrowser() {
        String url = "https://www.mortgagecalculator.org/";
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ActOn.browser(driver).openBrowser(url);
    }

    //Enter data for mortgage calculations
    public void enterData() {
        //Enter Home Value "300000"
        ActOn.element(driver, HomeValueInputField).setValue("300000");

        //Enter Down payment "60000"
        ActOn.element(driver, DownPaymentInputField).setValue("60000");

        //Clicking on the radio button $
        ActOn.element(driver, DownPaymentInDollarRadioButton).click();

        //Enter Loan Amount "240000.00"
        ActOn.element(driver, LoanAmountInputField).setValue("240000");

        //Enter Interest Rate 3.0 %
        ActOn.element(driver, InterestRateInputField).setValue("3");

        //Enter Loan Term 30 years
        ActOn.element(driver, LoanTermInputField).setValue("30");

        //Enter Start Date will be always next month
        String date = DateUtils.returnNextMonth();
        String[] dates = date.split("-");
        String month = dates[0];
        String year = dates[1];

        //Selecting value from the dropdown
        ActOn.element(driver, StartDateMonthDropDown).selectOption(month);

        //Enter Year
        ActOn.element(driver, StartDateYearInputField).setValue(year);

        //Enter Property Tax "5000"
        ActOn.element(driver, PropertyTaxInputField).setValue("5000");

        //Enter PMI "0.5"
        ActOn.element(driver, PmiInputField).setValue("0.5");

        //Enter Home Ins "1000"
        ActOn.element(driver, HomeInsInputField).setValue("1000");

        //Enter Monthly HOA "100"
        ActOn.element(driver, MonthlyHoaInputField).setValue("100");

        //Select Loan Type "FHA"
        ActOn.element(driver, LoanTypeDropDown).selectOption("FHA");

        //Select Buy or Refi: "Buy"
        ActOn.element(driver, BuyOrRefiDropDown).selectOption("Buy");

        //Select Credit Rating: "Excellent"
        ActOn.element(driver, CreditRatingDropDown).selectOption("Excellent (720+)");
    }

    @Test
    public void validateTitle() {
        String actualTitle = ActOn.browser(driver).getTitle();
        String expectedTitle = "Mortgage Calculator";
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void calculateRate() {
        //Implicit wait
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        //Entering data into the desired field
        enterData();

        //Click on Calculate button
        ActOn.element(driver, CalculateButton).click();

        //Validating the total monthly payment is generated as per the calculation
        AssertThat.elementAssertions(driver, TotalMonthlyPayment).elementExist();
    }

    @AfterTest
    public void closeBrowser() {
        ActOn.browser(driver).closeBrowser();
    }
}
