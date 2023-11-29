package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BasePage;

import java.util.List;

public class WikipediaHistoryPage extends BasePage {

    public WikipediaHistoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "firstHeading")
    WebElement heading;

    public String getHeading(){
        waitElementToBeVisible(heading);
        return heading.getText();
    }

}