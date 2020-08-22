package automation_test;

import command_providers.ActOn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import page_objects.Home;
import utilities.DateUtils;

public class CalculateMonthlyPayment {
    private static final Logger LOGGER = LogManager.getLogger(CalculateMonthlyPayment.class);
    WebDriver driver;

    @BeforeMethod
    public void openBrowser() {
        String url = "https://www.mortgagecalculator.org/";
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        LOGGER.info("---------Calculate monthly Payment-------");
        ActOn.browser(driver).openBrowser(url);
    }

    @Test
    public void calculateMonthlyPayment() {
        String date = DateUtils.returnNextMonthWithYear();
        String[] dates = date.split("-");
        String month = dates[0];
        String year = dates[1];

        new Home(driver)
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
        LOGGER.info("---------End of Calculate monthly Payment-------");
    }
}
