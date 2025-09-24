package io.loop.pages;

import io.loop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderBar {
    private final WebDriver driver = Driver.getDriver();
    // Works for "Received docs" / "My uploads" shown as H1/H2
    private final By header = By.xpath("//h1[normalize-space()] | //h2[normalize-space()]");

    public By headerLocator() { return header; }
    public String text() { return driver.findElement(header).getText().trim(); }
}
