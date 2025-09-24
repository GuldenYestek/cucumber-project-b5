package io.loop.pages;

import io.loop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage {

    public HomePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//img[@alt='Docuport']")
    public WebElement logo;


    @FindBy(xpath = "//button[@type='submit']")
    public WebElement continueButton;

    // Restrict to <aside> so you only get left-nav items
    @FindBy(xpath = "//*[contains(@class,'v-list-item__title')]")
    public List<WebElement> navItems;   // div or span — we don't care


    // optional: hamburger icon for collapsed menu
    @FindBy(xpath = "//*[@class='v-app-bar__nav-icon' or @aria-label='Menu']")
    public WebElement menuButton;

    public List<String> getNavTexts() {
        return navItems.stream()
                .map(e -> e.getText().trim())
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }


}

