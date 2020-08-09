package page_objects;

import command_providers.ActOn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationBar {
    public WebDriver driver;
    private final By MortgageCalculatorLogo = By.xpath("//a/img[@alt='Logo']");
    private final By RatesLink = By.linkText("Rates");
    private final By RealAprLink = By.linkText("Real APR");
    private static final Logger LOGGER = LogManager.getLogger(NavigationBar.class);

    public NavigationBar(WebDriver driver) {
        this.driver = driver;
    }

    public Home navigateToHome() {
        ActOn.element(driver, MortgageCalculatorLogo).click();
        LOGGER.info("Navigate to the Home Page");
        return new Home(driver);
    }

    public NavigationBar mouseHoverToRates() {
        ActOn.element(driver, RatesLink).mouseHover();
        LOGGER.info("Mouse Hover to the Rates Link");
        return this;
    }

    public RealApr navigateToRealApr() {
        ActOn.element(driver, RealAprLink).click();
        LOGGER.info("Navigate to the Real APR page");
        return new RealApr(driver);
    }
}
