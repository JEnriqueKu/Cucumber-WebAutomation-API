package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.BasePage;

public class WikipediaEditPage extends BasePage {
    public WikipediaEditPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "oo-ui-iconElement-icon")
    private WebElement startEditingButton;

    @FindBy(id = "firstHeading")
    WebElement heading;

    public void clickOnStartEditingButton(){
        waitElementToBeClickable(startEditingButton);
    }

    public String getHeading(){
        waitElementToBeVisible(heading);
        return heading.getText();
    }

}
