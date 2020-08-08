package page_objects;

import command_providers.ActOn;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MenuNavigation {
    private final By MortgageCalculatorLogo = By.xpath("//a/img[@alt='Logo']");
    private final By RatesLink = By.linkText("Rates");
    private final By RealAprLink = By.linkText("Real APR");
    public WebDriver driver;

    public MenuNavigation(WebDriver driver) {
        this.driver = driver;
    }

    //Navigate to the Home Page
    public Home navigateToHome() {
        ActOn.element(driver, MortgageCalculatorLogo).click();
        return new Home(driver);
    }

    //Mouse Hover to the Rates Link
    public MenuNavigation mouseHoverToRates() {
        ActOn.element(driver, RatesLink).mouseHover();
        return this;
    }

    //Navigate to the Real APR page
    public RealApr navigateToRealApr() {
        ActOn.element(driver, RealAprLink).click();
        return new RealApr(driver);
    }
}
