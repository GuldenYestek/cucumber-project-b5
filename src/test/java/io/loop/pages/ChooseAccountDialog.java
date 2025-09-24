package io.loop.pages;

import io.loop.utilities.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ChooseAccountDialog {

    private WebDriver driver() { return Driver.getDriver(); }
    private WebDriverWait waitFor() { return new WebDriverWait(driver(), Duration.ofSeconds(15)); }

    // Scoped selectors
    private static final By DIALOG = By.cssSelector(".v-dialog.v-dialog--active");
    private static final By COMBO  = By.cssSelector(".v-dialog.v-dialog--active [role='combobox']");
    private static final By INPUT  = By.cssSelector(".v-dialog.v-dialog--active [role='combobox'] input[type='text']");
    private static final By CONTINUE = By.cssSelector(".v-dialog.v-dialog--active button[type='submit']");

    // Vuetify teleports the menu to <body>
    private static final By MENU = By.cssSelector(".v-menu__content.menuable__content__active");

    private By option(String text) {
        return By.xpath(
                "//div[contains(@class,'v-list-item')][.//div[contains(@class,'v-list-item__title') and normalize-space()='"+text+"']]" +
                        " | //div[@role='option' and normalize-space()='"+text+"']"
        );
    }

    /** Select the account by visible text (or type + Enter) and click Continue. */
    public void selectAccountAndContinue(String accountName) {
        waitFor().until(ExpectedConditions.visibilityOfElementLocated(DIALOG));

        if (accountName != null && !accountName.isBlank()) {
            // open dropdown
            WebElement combo = driver().findElement(COMBO);
            combo.click();
            waitFor().until(ExpectedConditions.attributeToBe(combo, "aria-expanded", "true"));

            // try selecting from the teleported menu
            try {
                waitFor().until(ExpectedConditions.visibilityOfElementLocated(MENU));
                waitFor().until(ExpectedConditions.elementToBeClickable(option(accountName))).click();
            } catch (TimeoutException ignored) {
                // fallback: type and hit Enter (autocomplete pattern)
                WebElement input = driver().findElement(INPUT);
                input.clear();
                input.sendKeys(accountName, Keys.ENTER);
            }
        }

        // click Continue and wait for dialog to close
        waitFor().until(ExpectedConditions.elementToBeClickable(CONTINUE)).click();
        waitFor().until(ExpectedConditions.invisibilityOfElementLocated(DIALOG));
    }

    /** If the default value is fine, just continue. */
    public void continueWithPrefilledAccount() {
        waitFor().until(ExpectedConditions.visibilityOfElementLocated(DIALOG));
        waitFor().until(ExpectedConditions.elementToBeClickable(CONTINUE)).click();
        waitFor().until(ExpectedConditions.invisibilityOfElementLocated(DIALOG));
    }
}
