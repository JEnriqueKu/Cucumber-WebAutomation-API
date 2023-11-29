package steps;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import pages.WikipediaPage;
import pages.WikipediaResultPage;
import pojos.Person;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CharactersSteps {
    private final WebDriver driver = new ChromeDriver();
    private WikipediaPage wikipediaPage = new WikipediaPage(driver);
    private WikipediaResultPage wikipediaResultPage;
    private Person person;

    @After
    public void tearDown(){
        driver.close();
    }

    @Given("I am an user at the Wikipedia WebPage requesting SW character {int}")
    public void i_am_an_user_at_the_wikipedia_web_page_requesting_sw_character(Integer number) {
        wikipediaPage.loadPage();
        String characterAPI = "https://swapi.dev/api/people/" + number;
        Response response = RestAssured.get(characterAPI);
        person = (Person) response.then().extract().as(Person.class);


    }

    @When("I search the requested character name on Wikipedia search page")
    public void i_search_the_requested_character_name_on_wikipedia_search_page() {
        wikipediaPage.writeOnSearchBox(person.getName());
        wikipediaResultPage = wikipediaPage.clickOnSearchButton();
    }

    @Then("I should be able to see the article page correctly displayed for the requested character")
    public void i_should_be_able_to_see_the_article_page_correctly_displayed_for_the_requested_character() {
        assertTrue(wikipediaResultPage.headingIsDisplayed());
        assertEquals(wikipediaResultPage.getHeadingText(),person.getName());
    }

    @Then("the side bar should be present on the page")
    public void the_side_bar_should_be_present_on_the_page() {
        AssertJUnit.assertTrue(wikipediaPage.sideBarIsPresent());

    }
    @Then("the navigation bar should be present on the page")
    public void the_navigation_bar_should_be_present_on_the_page() {
        AssertJUnit.assertTrue(wikipediaPage.navigationBarIsPresent());

    }
}
