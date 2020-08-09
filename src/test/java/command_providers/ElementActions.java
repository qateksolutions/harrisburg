package command_providers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class ElementActions {
    private By locator;
    private WebDriver driver;

    private static final Logger LOGGER = LogManager.getLogger(ElementActions.class);

    public ElementActions(WebDriver driver, By locator) {
        this.driver = driver;
        this.locator = locator;
    }

    public WebElement getElement() {
        WebElement element = null;
        try {
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            element = driver.findElement(locator);
        } catch (NoSuchElementException e) {
            LOGGER.error("Element Not found for the locator: " + locator + " and exception is: " + e);
        }
        return element;
    }

    public ElementActions click() {
        getElement().click();
        return this;
    }

    public ElementActions setValue(String value) {
        getElement().clear();
        getElement().sendKeys(value);
        return this;
    }

    public String getText() {
        return getElement().getText();
    }

    public ElementActions selectOption(String value) {
        Select select = new Select(getElement());
        select.selectByVisibleText(value);
        return this;
    }

    public ElementActions mouseHover() {
        Actions actions = new Actions(driver);
        actions.moveToElement(getElement()).perform();
        return this;
    }
}
