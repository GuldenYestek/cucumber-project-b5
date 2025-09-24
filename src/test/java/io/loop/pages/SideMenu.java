package io.loop.pages;

import io.loop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SideMenu {
    private final WebDriver driver = Driver.getDriver();

    public void open(String label) {
        By locator = By.xpath(
                "//*[@role='navigation']//span[normalize-space()='" + label + "']" +
                        "/ancestor::*[(self::a or self::li or self::button)][1]" +
                        " | //nav//a[normalize-space()='" + label + "']"
        );
        driver.findElement(locator).click();
    }
}
