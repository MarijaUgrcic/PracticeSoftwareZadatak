package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final Logger log = LogManager.getLogger(BasePage.class.getName());

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    protected WebElement getElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        //return driver.findElement(locator);
    }

    protected  void clickOnElement(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        //getElement(locator).click();
    }

    protected void typeIn(By locator, String text) {
        getElement(locator).sendKeys(text);
    }

    protected String getTextFromElement(By locator) {
        return getElement(locator).getText();
    }

    protected void clickOnRandomElement(By locator) {
        Random random = new Random();
        List<WebElement> list = driver.findElements(locator);
        int randomElement = random.nextInt(list.size());
        list.get(randomElement).click();

    }

    protected void hover (By locator) {
        Actions actions = new Actions(driver);
        WebElement element = getElement(locator);
        actions.moveToElement(element).build().perform();
    }
    protected void hoverAndClick (By locator) {
        Actions actions = new Actions(driver);
        WebElement element = getElement(locator);
        actions.moveToElement(element).click().build().perform();
    }
    protected boolean matchesExpectedText(By locator, String expectedText){
        WebElement element = getElement(locator);
        if (element.getText().equals(expectedText)){
            log.info("PASSED - Text found in element: " + element.getText() + " MATCHES expected text: " + expectedText);
            return true;
        }else {
            log.error("FAILED - Text found in element: " + element.getText() + " DOES NOT MATCH expected text: " + expectedText);
        }
        return false;
    }
    protected boolean errorFieldNotDisplayed(By locator){
        if (driver.findElements(locator).isEmpty()){
            log.info("PASSED - Used format in test is valid.");
            return true;
        }else {
            log.error("FAILED - Used format in test is mot valid.");
        }
        return false;
    }

}
