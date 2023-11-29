package pages;

import org.apache.logging.log4j.util.EnglishEnums;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import utils.BasePage;

public class WikipediaPage extends BasePage {

    public WikipediaPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "searchInput")
    private WebElement searchBox;

    @FindBy(className = "svg-search-icon")
    private WebElement searchButton;

    @FindBy(id = "mw-panel-toc")
    private WebElement sideBar;

    @FindBy(id = "right-navigation")
    private WebElement navigationBar;
    
    @FindBy(css = "#searchLanguage")
    private WebElement languageButton;

    public void loadPage(){
        navigateTo("https://www.wikipedia.org/");
    }

    public void writeOnSearchBox(String text){
        waitElementToBeWriteable(searchBox,text);
    }

    public WikipediaResultPage clickOnSearchButton(){
        waitElementToBeClickable(searchButton);
        return new WikipediaResultPage(getDriver());
    }

    public boolean sideBarIsPresent(){
        return sideBar.isDisplayed();
    }

    public boolean navigationBarIsPresent(){
        return navigationBar.isDisplayed();
    }

    public void changeLanguageToEnglish(){
        Select selectLanguage = new Select(languageButton);
        selectLanguage.selectByValue("en");
    }


}
