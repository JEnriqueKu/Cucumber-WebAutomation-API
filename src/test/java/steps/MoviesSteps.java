package steps;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.WikipediaEditPage;
import pages.WikipediaPage;
import pages.WikipediaResultPage;
import pojos.Film;

import java.util.Random;

import static org.testng.AssertJUnit.assertEquals;

public class MoviesSteps {

    private final WebDriver driver = new ChromeDriver();
    Film film;
    String headingExpected;
    WikipediaPage wikipediaPage = new WikipediaPage(driver);
    WikipediaResultPage wikipediaResultPage;
    WikipediaEditPage wikipediaEditPage;

    @After
    public void tearDown(){
        driver.close();
    }


    @Given("I am an user at the Wikipedia WebPage requesting a random SW movie")
    public void i_am_an_user_at_the_wikipedia_web_page_requesting_sw_movie() {
        wikipediaPage.loadPage();
        Random random = new Random();
        int number = random.nextInt(6)+1;
        String movieAPI = "https://swapi.dev/api/films/" + number;
        film = (Film) RestAssured.get(movieAPI).then().extract().as(Film.class);
    }
    @When("I search the requested movie name on Wikipedia search page")
    public void i_search_the_requested_movie_name_on_wikipedia_search_page() {
        wikipediaPage.changeLanguageToEnglish();
        wikipediaPage.writeOnSearchBox(film.getTitle());
        wikipediaResultPage = wikipediaPage.clickOnSearchButton();
    }
    @When("I click on the edit button")
    public void i_click_on_the_edit_button() {
        headingExpected = "Editing ".concat( wikipediaResultPage.getHeadingText());
        wikipediaEditPage = wikipediaResultPage.clickOnEditButton();
    }

    @Then("I should be able to see the edit page correctly displayed for the requested movie")
    public void i_should_be_able_to_see_the_edit_page_correctly_displayed_for_the_requested_movie() {
        assertEquals(headingExpected,wikipediaEditPage.getHeading());
    }
}
