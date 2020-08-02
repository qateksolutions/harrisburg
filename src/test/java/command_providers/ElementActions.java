package command_providers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ElementActions {
    private By locator;
    private WebDriver driver;

    public ElementActions(WebDriver driver, By locator) {
        this.driver = driver;
        this.locator = locator;
    }

    public WebElement getElement() {
        WebElement element = null;
        try {
            element = driver.findElement(locator);
        } catch (NoSuchElementException e) {
            System.out.println("Element Not found for the locator: " + locator + " and exception is: " + e);
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
