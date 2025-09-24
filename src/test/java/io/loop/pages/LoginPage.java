package io.loop.pages;

import io.loop.utilities.BrowserUtils;
import io.loop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy (xpath = "//input[@type='text']")
    public WebElement userNameInput;


    @FindBy (xpath = "//input[@type='password']")
    public WebElement passwordInput;

    @FindBy (xpath = "//button[@type='submit']")
    public WebElement loginButton;

    /**
     * login to docuport
     * @param username
     * @param password
     */

    public void login(String username, String password) throws InterruptedException {
        BrowserUtils.waitForClickable(loginButton, 10);
        userNameInput.clear();
        userNameInput.sendKeys(username);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        loginButton.click();
        Thread.sleep(3000);

       // if (BrowserUtils.waitForVisibility(loginButton,10).isDisplayed()) {
           // loginButton.click();
        }
    // --- helpers to avoid using sendKeys in steps and to avoid throws ---

    /** Type only (no waits beyond whatever your BrowserUtils already does) */
    public void typeUsername(String username) {
        userNameInput.clear();
        userNameInput.sendKeys(username);
    }

    public void typePassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    /** Click Sign in with your existing click helper (or plain click) */
    public void clickSignIn() {
        try {
            // If you have this helper:
            BrowserUtils.click(loginButton);
        } catch (Throwable t) {
            // Fallback if you don't have BrowserUtils.click:
            loginButton.click();
        }
    }

    /** Optional: a safe overload that does NOT throw InterruptedException */
    public void loginSafe(String username, String password) {
        typeUsername(username);
        typePassword(password);
        clickSignIn();
    }



}




