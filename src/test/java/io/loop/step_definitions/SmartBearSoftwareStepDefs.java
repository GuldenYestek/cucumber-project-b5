package io.loop.step_definitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.loop.pages.POM;

import static org.junit.Assert.assertEquals;

public class SmartBearSoftwareStepDefs {

    POM pages = new POM();

    @Given("user is already logged in and navigated to order page")
    public void user_is_already_logged_in_and_navigated_to_order_page() {
        pages.getSmartBearSoftwarePage().openLoginPage();
        pages.getSmartBearSoftwarePage().loginAs("Tester", "test");
        pages.getSmartBearSoftwarePage().goToOrder();
    }

    @When("user selects product type {string}")
    public void user_selects_product_type(String product) {
        pages.getSmartBearSoftwarePage().selectProduct(product);
    }

    @When("user enters quantity {int}")
    public void user_enters_quantity(Integer qty) {
        pages.getSmartBearSoftwarePage().enterQuantity(qty);
    }

    @When("user enters customer name {string}")
    public void user_enters_customer_name(String name) {
        pages.getSmartBearSoftwarePage().enterCustomerName(name);
    }

    @When("user enters street {string}")
    public void user_enters_street(String street) {
        pages.getSmartBearSoftwarePage().enterStreet(street);
    }

    @When("user enters city {string}")
    public void user_enters_city(String city) {
        pages.getSmartBearSoftwarePage().enterCity(city);
    }

    @When("user enters state {string}")
    public void user_enters_state (String state) {
        pages.getSmartBearSoftwarePage().enterState(state);
    }

    @When("user enters zip {string}")
    public void user_enters_zip (String zip){
        pages.getSmartBearSoftwarePage().enterZip(zip);
    }

    @When("user selects credit card type {string}")
    public void user_selects_credit_card_type (String type){
        pages.getSmartBearSoftwarePage().selectCreditCardType(type);
    }

    @When("user enters credit car number {string}")
    public void user_enters_credit_car_number (String cardNumber){
        pages.getSmartBearSoftwarePage().enterCreditCardNumber(cardNumber);
    }

    @When("user enters expiration date {string}")
    public void user_enters_expiration_date (String expDate){
        pages.getSmartBearSoftwarePage().enterExpirationDate(expDate);
    }

    @When("user enters process order button")
    public void user_enters_process_order_button () throws InterruptedException {
        pages.getSmartBearSoftwarePage().clickProcessOrder();
    }

    @Then("user should see {string} in the first row of the table")
    public void user_should_see_in_the_first_row_of_the_table (String expectedName) throws InterruptedException {
        String actualName = pages.getSmartBearSoftwarePage().getFirstRowName();
        assertEquals("First row name mismatch", expectedName, actualName);

    }

}


