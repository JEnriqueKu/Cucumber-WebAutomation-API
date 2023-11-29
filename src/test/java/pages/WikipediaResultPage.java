package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.BasePage;

public class WikipediaResultPage extends BasePage {
    public WikipediaResultPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "firstHeading")
    private WebElement heading;

    @FindBy(id = "ca-edit")
    private WebElement editButton;

    @FindBy(id = "ca-history")
    private WebElement historyButton;

    public boolean headingIsDisplayed(){
        waitElementToBeVisible(heading);
        return heading.isDisplayed();
    }

    public String getHeadingText(){
        waitElementToBeVisible(heading);
        return heading.getText();
    }

    public WikipediaEditPage clickOnEditButton(){
        waitElementToBeClickable(editButton);
        return new WikipediaEditPage(getDriver());
    }

    public WikipediaHistoryPage clickOnHistoryButton(){
        waitElementToBeClickable(historyButton);
        return new WikipediaHistoryPage(getDriver());
    }

}
