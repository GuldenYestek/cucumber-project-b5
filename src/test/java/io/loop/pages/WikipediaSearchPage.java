package io.loop.pages;

import io.loop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WikipediaSearchPage {
    public WikipediaSearchPage() {
        PageFactory.initElements(Driver.getDriver(), this);}

    @FindBy(xpath = "//span[contains(@class,'Wikipedia')]")
    public WebElement wikipediaLogoTitle;

    @FindBy(name = "search")
    public WebElement searchBox;


    @FindBy(css = "[type='submit']")
    public WebElement searchButton;


    @FindBy(css = "[id='firstHeading']")
    public WebElement mainHeader;

    @FindBy(css = ".fn")
    public WebElement imageHeader;

}

