package io.loop.pages;

import io.loop.utilities.BrowserUtils;
import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeftNavigatePage {
    public LeftNavigatePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//span[contains(text(),'Upload')]")
    public WebElement uploadButton;

    @FindBy(xpath = "//span[contains(text(),'Home')]")
    public WebElement homeButton;

    @FindBy(xpath = "//span[contains(text(),'Received')]")
    public WebElement receivedDocsButton;

    @FindBy(xpath = "//span[contains(text(),'My uploads')]")
    public WebElement myUploads;

    @FindBy(xpath = "//span[contains(text(),'Invitations')]")
    public WebElement invitationsButton;

    @FindBy(xpath = "//a[contains(text(),'Terms')]")
    public WebElement termsAndConditionsButton;


    public void clickButton(String button) throws InterruptedException {
        switch (button.toLowerCase().trim()) {

//            case "home" -> {
//                try {
//                    BrowserUtils.waitForClickable(homeButton, DocuportConstants.LARGE).click();
//                } catch (StaleElementReferenceException se) {
//                    BrowserUtils.waitForClickable(homeButton, DocuportConstants.LARGE).click();
//                }
//
//            }

//            case "home" -> BrowserUtils.waitForClickable2(homeButton, DocuportConstants.LARGE);

//

           case "home" -> {
               BrowserUtils.waitForClickable(homeButton, DocuportConstants.LARGE);
           BrowserUtils.clickWithJS(homeButton);
           }


            case "upload" -> BrowserUtils.waitForClickable(uploadButton, DocuportConstants.LARGE).click();
            case "received doc" -> BrowserUtils.waitForClickable(receivedDocsButton, DocuportConstants.LARGE).click();

            case "invitations" -> {
                BrowserUtils.waitForClickable(invitationsButton, DocuportConstants.LARGE);
            BrowserUtils.clickWithJS(invitationsButton);
            }

            case "terms and conditions" ->
                    BrowserUtils.waitForClickable(termsAndConditionsButton, DocuportConstants.LARGE).click();
            case "my uploads" -> BrowserUtils.waitForClickable(myUploads, DocuportConstants.LARGE).click();
            default -> throw new IllegalArgumentException("Not such a button: " + button);
        }
    }
}



















