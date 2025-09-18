package io.loop.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.loop.pages.POM;
import io.loop.utilities.ConfigurationReader;
import io.loop.utilities.Driver;
import org.apache.logging.log4j.*;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ProductStepDefs {


    POM pages = new POM();
    private static final Logger LOG = LogManager.getLogger();

    @Given("User is on the HomePage")
    public void user_is_on_the_home_page() {
        Driver.getDriver().get(ConfigurationReader.getProperties("product.url"));
        LOG.info("User is on Homepage");
    }
    @Then("User should be able to see expected prices in the following products")
    public void user_should_be_able_to_see_expected_prices_in_the_following_products(List<Map<String, String>> productDetails) {

        for (Map<String, String> productDetail : productDetails) {
//            System.out.println("============Product Details===============");
//            System.out.println("productDetail.get(\"Category\") = " + productDetail.get("Category"));
//            System.out.println("productDetail.get(\"Product\") = " + productDetail.get("Product"));
//            System.out.println("productDetail.get(\"expectedPrice\") = " + productDetail.get("expectedPrice"));


            // click the category
            pages.getProductPage().clickCategory(productDetail.get("Category"));

            // get actual price
            String actualPrice = pages.getProductPage().getProductPrice(productDetail.get("Product"));

            //get product expected price from table
            String expectedPrice = productDetail.get("expectedPrice");

            // do assertion
            assertEquals("Expected does not match the actual", expectedPrice, actualPrice);
            LOG.info("Validation of the price for: " + productDetail.get("Category") + ", for Product: " + productDetail.get("Product") + " expected: " + expectedPrice + " - actual: " + actualPrice);
        }
    }
    @Then("User should be able to see expected prices in the following products with listOfLists")
    public void user_should_be_able_to_see_expected_prices_in_the_following_products_with_list_of_lists(List<List<String>> productDetails) {

       for (List<String> productDetail : productDetails) {

           //category
           pages.getProductPage().clickCategory(productDetail.get(0));

           //get actual price for each product
           String actualPrice = pages.getProductPage().getProductPrice(productDetail.get(1));
           String expectedPrice = productDetail.get(2);

           assertEquals("Expected does not match the actual", expectedPrice, actualPrice);
           LOG.info("Validation of the price for: " + productDetail.get(0) + ", for product: " + productDetail.get(1) + " expected price: {}, - actualPrice {}", expectedPrice, actualPrice);

       }
    }

    @Then("user should be able to see the names")
    public void user_should_be_able_to_see_the_names(Map<String, List<String>> student) {
        List<String> group2 = student.get("Group 2");

        System.out.println("group2 = " + group2);

    }

    @Then("User should be able to see expected prices in the following products with map")
    public void user_should_be_able_to_see_expected_prices_in_the_following_products_with_map(Map<String, List<String>> productDetails) {

        for(Map.Entry<String, List<String>> entry : productDetails.entrySet()) {
           String category = entry.getKey();
            List<String> products = entry.getValue();

            for(String productPrice : products) {

                try {

                    String[] arr1 = productPrice.split("-");
                    String product = arr1[0].trim();
                    String expectedPrice = arr1[1].trim();

                    System.out.println("category = " + category);
                    //category

                    pages.getProductPage().clickCategory(category);

                    // get actual price
                    String actualPrice = pages.getProductPage().getProductPrice(product);

                    // do assertion
                    assertEquals("Expected does not match the actual", expectedPrice, actualPrice);
                    LOG.info("Expected price {} - actual price {}", expectedPrice, actualPrice);
                }catch (Exception e) {

                }
            }
        }
    }
}


