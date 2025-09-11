package io.loop.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.loop.pages.WikipediaSearchPage;
import io.loop.utilities.BrowserUtils;
import io.loop.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class WikipediaStepDefs {

    WikipediaSearchPage wikipediaSearchPage = new WikipediaSearchPage();

    @Given("user is on Wikipedia home page")
    public void user_is_on_wikipedia_home_page() {
        Driver.getDriver().get("https://wikipedia.org");
        BrowserUtils.takeScreenshot();
    }

    @When("user types {string} in the wiki search box")
    public void user_types_in_the_wiki_search_box(String name) {
        wikipediaSearchPage.searchBox.sendKeys(name);
    }

    @Then("user clicks wiki search button")
    public void user_clicks_wiki_search_button() {
        wikipediaSearchPage.searchButton.click();
    }

    @Then("user sees {string} is in the {string}")
    public void user_sees_is_in_the_wiki_title(String name, String where) {
        WebDriver driver = Driver.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        switch (where.toLowerCase()) {
            case "wiki title": {
                wait.until(ExpectedConditions.titleContains(name));
                Assert.assertTrue("Title didn't contain '" + name + "'. Actual: " + driver.getTitle(),
                        driver.getTitle().contains(name));
                break;
            }
            case "main header": {
                wait.until(ExpectedConditions.visibilityOf(wikipediaSearchPage.mainHeader));
                assertEquals("Expected name: " + name + " does not match with actual one: " + wikipediaSearchPage.mainHeader.getText(), name, wikipediaSearchPage.mainHeader.getText());
                break;
            }
            case "image header": {
                wait.until(ExpectedConditions.visibilityOf(wikipediaSearchPage.imageHeader));
                assertEquals("Expected name: " + name + " does not match with actual one: " + wikipediaSearchPage.imageHeader.getText(), name, wikipediaSearchPage.imageHeader.getText());
                break;
            }
            default:
                throw new IllegalArgumentException("Unknown title target: " + where);
        }
    }
}
