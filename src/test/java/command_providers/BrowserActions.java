package command_providers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class BrowserActions {
    public WebDriver driver;
    private static final Logger LOGGER = LogManager.getLogger(BrowserActions.class);

    public BrowserActions(WebDriver driver) {
        this.driver = driver;
    }

    public BrowserActions openBrowser(String url) {
        driver.manage().deleteAllCookies();
        driver.get(url);
        LOGGER.info("Browser is opened with the url: " + url);
        driver.manage().window().maximize();
        return this;
    }

    public BrowserActions closeBrowser() {
        driver.quit();
        LOGGER.info("Browser is closed");
        return this;
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
