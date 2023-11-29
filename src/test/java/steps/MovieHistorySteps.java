package steps;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.WikipediaHistoryPage;
import pages.WikipediaPage;
import pages.WikipediaResultPage;
import pojos.Film;

import java.util.Random;

import static org.testng.AssertJUnit.assertEquals;

public class MovieHistorySteps {
    private final WebDriver driver = new ChromeDriver();
    WikipediaPage wikipediaPage = new WikipediaPage(driver);
    WikipediaResultPage wikipediaResultPage;
    WikipediaHistoryPage wikipediaHistoryPage;
    Film film;
    String headingExpected;

    @After
    public void tearDown(){
        driver.close();
    }

    @Given("I am an user at the Wikipedia WebPage requesting a random Star Wars movie")
    public void iAmAnUserAtTheWikipediaWebPageRequestingARandomStarWarsMovie() {
        wikipediaPage.loadPage();
        Random random = new Random();
        int number = random.nextInt(6)+1;
        String movieApi = "https://swapi.dev/api/films/"+number;
        film = (Film) RestAssured.get(movieApi).then().extract().as(Film.class);
    }


    @When("I search the requested movie name on Wikipedia")
    public void iSearchTheRequestedMovieNameOnWikipedia() {
        wikipediaPage.changeLanguageToEnglish();
        wikipediaPage.writeOnSearchBox(film.getTitle());
        wikipediaResultPage = wikipediaPage.clickOnSearchButton();
    }

    @And("I click on the History button")
    public void iClickOnTheHistoryButton() {
        headingExpected = wikipediaResultPage.getHeadingText();
        wikipediaHistoryPage = wikipediaResultPage.clickOnHistoryButton();
    }

    @Then("I should be able to see the history page correctly displayed for the requested movie")
    public void iShouldBeAbleToSeeTheHistoryPageCorrectlyDisplayedForTheRequestedMovie() {
        headingExpected = headingExpected.concat(": Revision history");
        assertEquals(headingExpected,wikipediaHistoryPage.getHeading());
    }
}
