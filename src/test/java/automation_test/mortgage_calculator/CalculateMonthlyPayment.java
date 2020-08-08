package automation_test.mortgage_calculator;

import command_providers.ActOn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import page_objects.MenuNavigation;
import utilities.DateUtils;

import java.util.concurrent.TimeUnit;

public class CalculateMonthlyPayment {
    WebDriver driver;

    @BeforeMethod
    public void openBrowser() {
        String url = "https://www.mortgagecalculator.org/";
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ActOn.browser(driver).openBrowser(url);
    }

    @Test
    public void calculateMonthlyPayment() {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        String date = DateUtils.returnNextMonth();
        String[] dates = date.split("-");
        String month = dates[0];
        String year = dates[1];

        new MenuNavigation(driver)
                .navigateToHome()
                .validateTitle()
                .typeHomePrice("300000")
                .typeDownPayment("60000")
                .clickDownPaymentInDollar()
                .typeLoanAmount("240000")
                .typeInterestRate("3")
                .typeLoanTermYears("30")
                .selectMonth(month)
                .selectYear(year)
                .typePropertyTax("5000")
                .typePmi("0.5")
                .typeHomeOwnerInsurance("1000")
                .typeMonthlyHoa("100")
                .selectLoanType("FHA")
                .selectBuyOrRefi("Buy")
                .selectCreditRating("Excellent (720+)")
                .clickOnCalculateButton()
                .validateTotalMonthlyPayment();
    }

    @AfterMethod
    public void closeBrowser() {
        ActOn.browser(driver).closeBrowser();
    }
}
