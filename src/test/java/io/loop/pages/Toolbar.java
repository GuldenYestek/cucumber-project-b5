package io.loop.pages;

import io.loop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Toolbar {
    private final WebDriver driver = Driver.getDriver();

    private By byButton(String label) {
        String t = label.replace("'", "\\'");
        return By.xpath(
                "//button[normalize-space()='" + t + "']" +
                        " | //button[.//span[normalize-space()='" + t + "']]" +
                        " | //*[@role='button' and normalize-space()='" + t + "']"
        );
    }

    public WebElement button(String label) {
        return driver.findElement(byButton(label));
    }
}
