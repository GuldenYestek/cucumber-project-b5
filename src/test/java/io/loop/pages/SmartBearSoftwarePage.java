package io.loop.pages;

import io.loop.utilities.ConfigurationReader;
import io.loop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SmartBearSoftwarePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

   public SmartBearSoftwarePage() {
       this.driver = Driver.getDriver();
       this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
       PageFactory.initElements(driver, this);
   }

    @FindBy(name = "ctl00$MainContent$username")
    private WebElement usernameInput;

    @FindBy(name = "ctl00$MainContent$password")
    private WebElement passwordInput;

    @FindBy(name = "ctl00$MainContent$login_button")
    private WebElement loginButton;

    @FindBy(name = "ctl00$MainContent$fmwOrder$ddlProduct")
    private WebElement productDropdown;

    @FindBy(name = "ctl00$MainContent$fmwOrder$txtQuantity")
    private WebElement quantityInput;

    @FindBy(name = "ctl00$MainContent$fmwOrder$txtName")
    private WebElement customerNameInput;

    @FindBy(name = "ctl00$MainContent$fmwOrder$TextBox2")
    private WebElement streetInput;

    @FindBy(name = "ctl00$MainContent$fmwOrder$TextBox3")
    private WebElement cityInput;

    @FindBy(name = "ctl00$MainContent$fmwOrder$TextBox4")
    private WebElement stateInput;

    @FindBy(name = "ctl00$MainContent$fmwOrder$TextBox5")
    private WebElement zipInput;

    @FindBy(name = "ctl00$MainContent$fmwOrder$TextBox6")
    private WebElement creditCardNumberInput;

    @FindBy(name = "ctl00$MainContent$fmwOrder$TextBox1")
    private WebElement expirationDateInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_InsertButton")
    private WebElement processOrderButton;

    @FindBy(linkText = "View all orders")
    private WebElement viewAllOrdersLink;

    @FindBy(id = "ctl00_MainContent_orderGrid")
    private WebElement ordersTable;

    public void openLoginPage() {
        String url = ConfigurationReader.getProperties("smartbearsoftware.url").trim();
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOf(loginButton));
    }

    public void loginAs(String username, String password) {
        wait.until(ExpectedConditions.visibilityOf(usernameInput)).clear();
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Order")));
    }

    public void goToOrder() {
        driver.findElement(By.linkText("Order")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name("ctl00$MainContent$fmwOrder$ddlProduct")));
    }

    public void selectProduct(String product) {
        wait.until(ExpectedConditions.visibilityOf(productDropdown));
        new Select(productDropdown).selectByVisibleText(product);
    }

    public void enterQuantity(int qty) {
        wait.until(ExpectedConditions.visibilityOf(quantityInput));
        quantityInput.sendKeys(String.valueOf(qty));
    }

    public void enterCustomerName(String name)  {
        wait.until(ExpectedConditions.visibilityOf(customerNameInput));
        customerNameInput.sendKeys(name);
    }

    public void enterStreet(String street)  {
        wait.until(ExpectedConditions.visibilityOf(streetInput));
        streetInput.sendKeys(street);
    }

    public void enterCity(String city) {
        wait.until(ExpectedConditions.visibilityOf(cityInput));
        cityInput.sendKeys(city);
    }

    public void enterState(String state) {
        wait.until(ExpectedConditions.visibilityOf(stateInput));
        stateInput.sendKeys(state);
    }

    public void enterZip(String zip) {
        wait.until(ExpectedConditions.visibilityOf(zipInput));
        zipInput.sendKeys(zip);
    }

    public void selectCreditCardType(String type) {
        By labelBy = By.xpath("//label[normalize-space()='" + type + "']");
        WebElement label = wait.until(ExpectedConditions.elementToBeClickable(labelBy));
        label.click();
    }

    public void enterCreditCardNumber(String number) {
        wait.until(ExpectedConditions.elementToBeClickable(creditCardNumberInput));
        creditCardNumberInput.click();
        creditCardNumberInput.sendKeys(number);
    }

    public void enterExpirationDate(String expDate) {
        wait.until(ExpectedConditions.elementToBeClickable(expirationDateInput));
        expirationDateInput.click();
        expirationDateInput.sendKeys(expDate);
    }

    public void clickProcessOrder() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(processOrderButton)).click();
        Thread.sleep(1000);
    }

    public String getFirstRowName() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(viewAllOrdersLink)).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(ordersTable));

        WebElement nameCell = ordersTable.findElement(By.xpath(".//tr[2]/td[2]"));
        return nameCell.getText().trim();
    }
}



