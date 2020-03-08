package io.hippovideo.recording.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class RecordVideo {
    WebDriver driver;
    SoftAssert softAssert;
    InputStream testData;
    Properties prop;

    public RecordVideo(WebDriver driver) {
        this.driver = driver;
        softAssert = new SoftAssert();
    }

    @FindBy(xpath = "//div[contains(@class,'importContainer')]")
    WebElement importIcon;
    @FindBy(xpath = "//div[@class='importPanel']//section[@data-name='Create video _+']")
    WebElement createVideoButton;
    @FindBy(id = "importVideoBtn")
    WebElement importVideoBtn;
    @FindBy(id = "importFileUpload")
    WebElement fileUploadBtn;
    @FindBy(id = "submitVideoForm")
    WebElement submitVideo;
    @FindBy(id = "importVideoFormContainer")
    WebElement progressVideo;
    @FindBy(id = "personlizationTab-heading")
    WebElement personlizationTab;
    @FindBy(id = "videoPersonalization")
    WebElement videoPersonalization;
    @FindBy(id = "personalizeButton")
    WebElement personalizeBtn;
    @FindBy(xpath = "//div[@id='previewPageLeft']//a[@id='addTextLink']")
    WebElement addTextLink;
    @FindBy(id = "seTextEditable")
    WebElement setTextEditable;
    @FindBy(id = "pauseAndPlayBtn")
    WebElement pauseAndPlay;
    @FindBy(id = "saveSimpleEdit")
    WebElement saveVideo;
    @FindBy(xpath = "//div[contains(@class,'se-text-component-selected')]//span[@class='se-text-delete']")
    WebElement delete;
    @FindBy(id = "pauseAndPlayBtn")
    WebElement pauseAndPlayBtn;
    @FindBy(xpath = "//div[@class='player-touch-guard']")
    WebElement videoPlayer;
    @FindBy(id = "editDropDownBtn")
    WebElement editVideo;


    public boolean openCreateVideo() {
        boolean res = false;
        try {
            importIcon.click();
            createVideoButton.click();
            WebDriverWait wait = new WebDriverWait(driver, 5000);
            WebElement importVideoButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("importVideoBtn")));
            importVideoButton.click();
            res = importVideoButton.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public void fileUpload() {
        File file = new File("video/plainvideo.mp4");
        fileUploadBtn.sendKeys(file.getAbsolutePath());
    }

    public boolean importVideo() {
        boolean res = false;
        try {
            importVideoBtn.click();
            fileUpload();
            WebDriverWait wait = new WebDriverWait(driver, 5000);
            WebElement submitVidBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submitVideoForm")));
            submitVidBtn.isDisplayed();
            submitVidBtn.click();
            WebElement progress = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("importVideoFormContainer")));
            if (progress.isDisplayed()) {
                res = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("importVideoFormContainer")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public boolean personalizeVideo() {
        boolean res = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, 60000);
            WebElement personalizeTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("personlizationTab-heading")));
            res = personalizeTab.isDisplayed();
            personlizationTab.click();
            videoPersonalization.click();
            personalizeBtn.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public boolean addPersonalization() {
        boolean res = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10000);
            WebElement linkText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addTextLink")));
            File file = new File("src/main/resources/personalization.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            pauseAndPlay.click();
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click();", delete);
            Thread.sleep(10000);
            if (linkText.isDisplayed()) {
                String st;
                while ((st = br.readLine()) != null) {
                    addTextLink.click();
                    Thread.sleep(1000);
                    setTextEditable.sendKeys(st);
                    Thread.sleep(9000);
                }
            }
            pauseAndPlay.click();
            res = saveVideo.isDisplayed();
            saveVideo.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public boolean verifyPersonalization() {
        boolean res = false;
        try {
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            res = editVideo.isDisplayed();
            Actions actions = new Actions(driver);
            actions.moveToElement(videoPlayer).build().perform();
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("document.getElementById('pauseAndPlayBtn').click();", pauseAndPlayBtn);
            File file = new File("src/main/resources/personalization.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                String xp = "//div[text()='" + st + "']";
                System.out.println(st);
                WebDriverWait wait = new WebDriverWait(driver, 10000);
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xp)));
                res = element.isDisplayed();
                System.out.println(xp + " " + res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

}
