package io.loop.pages;

import io.loop.utilities.BrowserUtils;
import io.loop.utilities.DocuportConstants;
import io.loop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyUploadsPage {

    public MyUploadsPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    // Adjust xpaths if your UI differs in spelling/case
    @FindBy(xpath = "//span[normalize-space()='Upload documents' or normalize-space()='Upload Documents']")
    public WebElement uploadDocumentsBtn;

    @FindBy(xpath = "//span[normalize-space()='Upload file' or normalize-space()='Upload File' or contains(., 'Upload file')]")
    public WebElement uploadFileBtn;

    public void clickButton(String button) {
        switch (button.toLowerCase().trim()) {
            case "upload documents" ->
                    BrowserUtils.waitForClickable(uploadDocumentsBtn, DocuportConstants.LARGE).click();

            case "upload file" ->
                    BrowserUtils.waitForClickable(uploadFileBtn, DocuportConstants.LARGE).click();

            default -> throw new IllegalArgumentException("Not such a button: " + button);
        }
    }

    // (Optional) If you later need to actually upload a file instead of only clicking:
    // @FindBy(css = "input[type='file']") public WebElement fileInput;
    // public void upload(String absolutePath) { fileInput.sendKeys(absolutePath); }
}
