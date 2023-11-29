package utils;

import lombok.Getter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class BasePage {

    @Getter
    WebDriver driver;
    Wait<WebDriver> wait;

    public BasePage(WebDriver driver){
        this.driver = driver;
        wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(5)).
                pollingEvery(Duration.ofMillis(500)).
                ignoring(NoSuchElementException.class);
        PageFactory.initElements(driver,this);
    }

    protected void navigateTo(String link){
        driver.get(link);
        driver.manage().window().maximize();
    }

    protected void waitElementToBeClickable(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    protected void waitElementToBeVisible(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitElementToBeWriteable(WebElement element,String text){
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(text);
    }

}
