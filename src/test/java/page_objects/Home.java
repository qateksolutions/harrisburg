package page_objects;

import command_providers.ActOn;
import command_providers.AssertThat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Home extends NavigationBar {
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

    // Define a static logger variable so that it references the
    // Logger instance named "Home".
    private static final Logger LOGGER = LogManager.getLogger(Home.class);

    public Home(WebDriver driver) {
        super(driver);
    }

    public Home validateTitle() {
        String actualTitle = ActOn.browser(driver).getTitle();
        String expectedTitle = "Mortgage Calculator";
        LOGGER.info("Asserting the Title of the WebPage");
        Assert.assertEquals(actualTitle, expectedTitle);
        return this;
    }

    public Home typeHomePrice(String value) {
        ActOn.element(driver, HomeValueInputField).setValue(value);
        LOGGER.info("Entered Home Price: " + value);
        return this;
    }

    public Home typeDownPayment(String value) {
        ActOn.element(driver, DownPaymentInputField).setValue(value);
        LOGGER.info("Entered Down Payment: " + value);
        return this;
    }

    public Home clickDownPaymentInDollar() {
        ActOn.element(driver, DownPaymentInDollarRadioButton).click();
        return this;
    }

    public Home typeLoanAmount(String value) {
        ActOn.element(driver, LoanAmountInputField).setValue(value);
        LOGGER.info("Entered Loan Amount: " + value);
        return this;
    }

    public Home typeInterestRate(String value) {
        ActOn.element(driver, InterestRateInputField).setValue(value);
        LOGGER.info("Entered Interest Rate: " + value);
        return this;
    }

    public Home typeLoanTermYears(String value) {
        ActOn.element(driver, LoanTermInputField).setValue(value);
        LOGGER.info("Entered Loan Term Years: " + value);
        return this;
    }

    public Home selectMonth(String month) {
        ActOn.element(driver, StartDateMonthDropDown).selectOption(month);
        LOGGER.info("Selected Month is: " + month);
        return this;
    }

    public Home selectYear(String year) {
        ActOn.element(driver, StartDateYearInputField).setValue(year);
        LOGGER.info("Selected Year is: " + year);
        return this;
    }

    public Home typePropertyTax(String value) {
        ActOn.element(driver, PropertyTaxInputField).setValue(value);
        LOGGER.info("Entered Property Tax: " + value);
        return this;
    }

    public Home typePmi(String value) {
        ActOn.element(driver, PmiInputField).setValue(value);
        LOGGER.info("Entered PMI: " + value);
        return this;
    }

    public Home typeHomeOwnerInsurance(String value) {
        ActOn.element(driver, HomeInsInputField).setValue(value);
        LOGGER.info("Entered Home Owner Insurance: " + value);
        return this;
    }

    public Home typeMonthlyHoa(String value) {
        ActOn.element(driver, MonthlyHoaInputField).setValue(value);
        LOGGER.info("Entered Monthly HOA: " + value);
        return this;
    }

    public Home selectLoanType(String value) {
        ActOn.element(driver, LoanTypeDropDown).selectOption(value);
        LOGGER.info("Selected Loan Type: " + value);
        return this;
    }

    public Home selectBuyOrRefi(String value) {
        ActOn.element(driver, BuyOrRefiDropDown).selectOption(value);
        LOGGER.info("Selected Buy or Refi option: " + value);
        return this;
    }

    public Home selectCreditRating(String value) {
        ActOn.element(driver, CreditRatingDropDown).selectOption(value);
        LOGGER.info("Selected Credit Rating: " + value);
        return this;
    }

    public Home clickOnCalculateButton() {
        ActOn.element(driver, CalculateButton).click();
        return this;
    }

    public Home validateTotalMonthlyPayment() {
        LOGGER.info("Validating Total Monthly Payment");
        AssertThat.elementAssertions(driver, TotalMonthlyPayment).elementExist();
        return this;
    }
}
