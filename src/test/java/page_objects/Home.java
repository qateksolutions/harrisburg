package page_objects;

import command_providers.ActOn;
import command_providers.AssertThat;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Home extends MenuNavigation {
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

    public Home(WebDriver driver) {
        super(driver);
    }

    public Home validateTitle() {
        String actualTitle = ActOn.browser(driver).getTitle();
        String expectedTitle = "Mortgage Calculator";
        Assert.assertEquals(actualTitle, expectedTitle);
        return this;
    }

    public Home typeHomePrice(String value) {
        ActOn.element(driver, HomeValueInputField).setValue(value);
        return this;
    }

    public Home typeDownPayment(String value) {
        ActOn.element(driver, DownPaymentInputField).setValue(value);
        return this;
    }

    public Home clickDownPaymentInDollar() {
        ActOn.element(driver, DownPaymentInDollarRadioButton).click();
        return this;
    }

    public Home typeLoanAmount(String value) {
        ActOn.element(driver, LoanAmountInputField).setValue(value);
        return this;
    }

    public Home typeInterestRate(String value) {
        ActOn.element(driver, InterestRateInputField).setValue(value);
        return this;
    }

    public Home typeLoanTermYears(String value) {
        ActOn.element(driver, LoanTermInputField).setValue(value);
        return this;
    }

    public Home selectMonth(String month) {
        ActOn.element(driver, StartDateMonthDropDown).selectOption(month);
        return this;
    }

    public Home selectYear(String year) {
        ActOn.element(driver, StartDateYearInputField).setValue(year);
        return this;
    }

    public Home typePropertyTax(String value) {
        ActOn.element(driver, PropertyTaxInputField).setValue(value);
        return this;
    }

    public Home typePmi(String value) {
        ActOn.element(driver, PmiInputField).setValue(value);
        return this;
    }

    public Home typeHomeOwnerInsurance(String value) {
        ActOn.element(driver, HomeInsInputField).setValue(value);
        return this;
    }

    public Home typeMonthlyHoa(String value) {
        ActOn.element(driver, MonthlyHoaInputField).setValue(value);
        return this;
    }

    public Home selectLoanType(String value) {
        ActOn.element(driver, LoanTypeDropDown).selectOption(value);
        return this;
    }

    public Home selectBuyOrRefi(String value) {
        ActOn.element(driver, BuyOrRefiDropDown).selectOption(value);
        return this;
    }

    public Home selectCreditRating(String value) {
        ActOn.element(driver, CreditRatingDropDown).selectOption(value);
        return this;
    }

    public Home clickOnCalculateButton() {
        ActOn.element(driver, CalculateButton).click();
        return this;
    }

    public Home validateTotalMonthlyPayment() {
        //Validating the total monthly payment is generated as per the calculation
        AssertThat.elementAssertions(driver, TotalMonthlyPayment).elementExist();
        return this;
    }
}
