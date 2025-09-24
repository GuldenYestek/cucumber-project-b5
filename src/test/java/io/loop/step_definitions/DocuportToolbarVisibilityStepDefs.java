package io.loop.step_definitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import io.loop.pages.*;
import io.loop.utilities.ConfigurationReader;
import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DocuportToolbarVisibilityStepDefs {

    private final POM pages = new POM();
    private final HeaderBar header = new HeaderBar();
    private final SideMenu side = new SideMenu();
    private final Toolbar toolbar = new Toolbar();
    private final HomePage homePage = new HomePage();
    private final WebDriverWait wait =
            new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

    private static String usernameForRole(String role) {
        switch (role.toLowerCase().trim()) {
            case "advisor":    return DocuportConstants.USERNAME_ADVISOR;
            case "client":     return DocuportConstants.USERNAME_CLIENT;
            case "employee":   return DocuportConstants.USERNAME_EMPLOYEE;
            case "supervisor": return DocuportConstants.USERNAME_SUPERVISOR;
            default: throw new IllegalArgumentException("Unknown role: " + role);
        }
    }
    private static String passwordForRole(String role) {
        return role.equalsIgnoreCase("client")
                ? DocuportConstants.PASSWORD_CLIENT
                : DocuportConstants.PASSWORD;
    }
    @Given("user is on the Docuport login page")
    public void user_is_on_the_docuport_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperties("docuportBETA"));
    }
    @When("the user logs in as {string}")
    public void the_user_logs_in_as(String role) {
        pages.getLoginPage().typeUsername(usernameForRole(role));
        pages.getLoginPage().typePassword(passwordForRole(role));
        pages.getLoginPage().clickSignIn();
        // wait for landing page header to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(header.headerLocator()));
    }
    @Then("the page header reads {string}")
    public void the_page_header_reads(String expected) {
        Assert.assertEquals(expected, header.text());
    }
    @Then("{string} button is visible")
    public void button_is_visible(String label) {
        Assert.assertTrue(
                "Expected button '" + label + "' to be visible",
                toolbar.button(label).isDisplayed()
        );
    }
    @And("the {string} opens {string} from the side menu")
    public void theOpensFromTheSideMenu(String user, String section) throws InterruptedException {
        side.open(section);
        // wait until header text matches the section we opened
        if ("client".equalsIgnoreCase(user)) {
            Thread.sleep(3000);
            homePage.continueButton.click();
        }
        wait.until(ExpectedConditions.textToBe(header.headerLocator(), section));
    }
}
