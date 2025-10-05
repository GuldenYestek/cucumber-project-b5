package io.loop.utilities;

import io.cucumber.java.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Set;

import static org.junit.Assert.assertTrue;


public class BrowserUtils {

    public static Scenario myScenario;

    /**
     * takes screenshot
     *
     * @author nsh
     */
    public static void takeScreenshot() {
        try {
            myScenario.log("Current url is: " + Driver.getDriver().getCurrentUrl());
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            myScenario.attach(screenshot, "image/png", myScenario.getName());
        } catch (WebDriverException | ClassCastException wbd) {
            wbd.getMessage();
        }
    }

    /**
     * validate if the driver switched to the expected url or title
     *
     * @param driver
     * @param expectedUrl
     * @param expectedTitle
     * @author NSH
     * implements assertion
     */
    public static void switchWindowAndValidate(WebDriver driver, String expectedUrl, String expectedTitle) {
        // to lowercase the params in order to avoid miss type
        expectedTitle = expectedTitle.toLowerCase();
        expectedUrl = expectedUrl.toLowerCase();

        // get all window handles, switch one by one and each time check if the url matches expected to stop
        // var windowHandles = driver.getWindowHandles();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String each : windowHandles) {
            driver.switchTo().window(each);
            if (driver.getCurrentUrl().toLowerCase().contains(expectedUrl)) {
                break;
            }
        }
        // after stopping on expected url, validate the title
        assertTrue(driver.getTitle().toLowerCase().contains(expectedTitle));
    }

    /**
     * @param driver
     * @param targetTitle
     * @author NSH
     */
    public static void switchToWindow(WebDriver driver, String targetTitle) {
        String origin = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            if (driver.getTitle().contains(targetTitle)) {
                return;
            }
        }
        driver.switchTo().window(origin);

    }

    /**
     * clicks any link from loop practice
     *
     * @param nameOfPage
     * @author nsh
     */
    public static void loopLinkClick(String nameOfPage) {
        WebElement element = Driver.getDriver().findElement(By.xpath("//a[.='" + nameOfPage + "']"));
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(DocuportConstants.LARGE));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    /**
     * waits for the provided element to be clickable
     *
     * @param element
     * @param timeout
     * @return element
     * @author nsh
     */
    public static WebElement waitForClickable(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * waits for provided element to be invisible on the page
     *
     * @param element
     * @param timeout
     * @author nsh
     */
    public static void waitForInvisibility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * waits for provided element to be visible on the page
     *
     * @param element
     * @param timeout
     * @author nsh
     */
    public static WebElement waitForVisibility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void click(WebElement element) {
        click(element, 10);
    }

    public static void click(WebElement element, int timeoutSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeoutSec));
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (ElementClickInterceptedException e) {
            // JS fallback if something overlays the element
            ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
        }

    }

    public static void uploadFileForWindows(String filePath) throws AWTException {
        StringSelection selection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        //simulate keyboard paste and enter
        Robot robot = new Robot();
        robot.delay(1000);

        // press CTRL + V
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        // press Enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

    }

    public static void uploadFileForMac(String filePath) throws AWTException {
        Robot robot = new Robot();

        //copy the file path
        StringSelection selection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        robot.delay(1000);

        // press ⌘ + Shift + G to open go to finder
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_G);
        robot.keyRelease(KeyEvent.VK_G);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_META);

        // Paste file path (⌘ + V)
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_META);

        robot.delay(1000);

        // press enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        robot.delay(1000);

        // Press Enter again to confirm file selection
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

    }
    public static void uploadFileUsingAppleScript(String filePath) throws Exception {
        String script = "tell application \"System Events\"\n"
                + "delay 1\n"
                + "keystroke \"G\" using {command down, shift down}\n"
                + "delay 1\n"
                + "keystroke \"" + filePath + "\"\n"
                + "keystroke return\n"
                + "delay 1\n"
                + "keystroke return\n"
                + "end tell";

        String[] command = { "osascript", "-e", script };
        Runtime.getRuntime().exec(command);
    }
}



