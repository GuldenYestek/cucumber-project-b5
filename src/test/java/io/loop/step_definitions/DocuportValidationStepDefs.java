package io.loop.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.loop.pages.POM;
import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DocuportValidationStepDefs {

    private final POM pages = new POM();
    private final WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));

    private static final By NAV_ITEMS = By.xpath("//*[contains(@class,'v-list-item__title')]");

    @When("user enters username for {string}")
    public void user_enters_username_for(String role) {
        switch (role.toLowerCase()) {
            case "client"     -> pages.getLoginPage().userNameInput.sendKeys(DocuportConstants.USERNAME_CLIENT);
            case "advisor"    -> pages.getLoginPage().userNameInput.sendKeys(DocuportConstants.USERNAME_ADVISOR);
            case "supervisor" -> pages.getLoginPage().userNameInput.sendKeys(DocuportConstants.USERNAME_SUPERVISOR);
            case "employee"   -> pages.getLoginPage().userNameInput.sendKeys(DocuportConstants.USERNAME_EMPLOYEE);
            default -> throw new IllegalArgumentException("Unknown role: " + role);
        }
    }
    @When("user enters password for {string}")
    public void user_enters_password_for(String role) {
        pages.getLoginPage().passwordInput.clear();
        pages.getLoginPage().passwordInput.sendKeys(DocuportConstants.PASSWORD);
    }

    @Then("user should be able to see the home page for {string}")
    public void user_should_be_able_to_see_the_home_page_for(String role) {
        clickIfPresent(By.xpath("//button[normalize-space()='Continue' or normalize-space()='CONTINUE']"));
        wait.until(ExpectedConditions.visibilityOf(pages.getHomePage().logo));
        assertTrue("Home page is not loaded", pages.getHomePage().logo.isDisplayed());

        if (Driver.getDriver().findElements(NAV_ITEMS).isEmpty()) {
            clickIfPresent(By.xpath("//*[@class='v-app-bar__nav-icon' or @aria-label='Menu']"));
        }
    }

    @Then("user should see the following {string}")
    public void user_should_see_the_following(String itemsCsv) {
        List<String> expected = Arrays.stream(itemsCsv.split(","))
                .map(String::trim)
                .collect(Collectors.toList());

        ensureNavLoaded();

        List<String> actual = pages.getHomePage().getNavTexts();
        assertEquals("Left navigation items mismatch", expected, actual);
    }

    private void ensureNavLoaded() {
        try {
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(NAV_ITEMS, 0));
            return;
        } catch (Exception ignored) {}

        clickIfPresent(By.xpath("//*[@class='v-app-bar__nav-icon' or @aria-label='Menu']"));
        try {
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(NAV_ITEMS, 0));
            return;
        } catch (Exception ignored) {}

        List<WebElement> frames = Driver.getDriver().findElements(By.tagName("iframe"));
        for (WebElement frame : frames) {
            try {
                Driver.getDriver().switchTo().frame(frame);
                if (!Driver.getDriver().findElements(NAV_ITEMS).isEmpty()) {
                    return;
                }
            } finally {
                Driver.getDriver().switchTo().defaultContent();
            }
        }
        throw new AssertionError("Left nav items not found after opening menu and checking iframes.");
    }
    private void clickIfPresent(By locator) {
        List<WebElement> els = Driver.getDriver().findElements(locator);
        if (!els.isEmpty()) {
            try { els.get(0).click(); } catch (Exception ignored) { }
        }
    }
}
