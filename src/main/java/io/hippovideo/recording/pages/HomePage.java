package io.hippovideo.recording.pages;

import io.hippovideo.recording.testbase.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class HomePage extends TestBase {
    WebDriver driver;
    SoftAssert softAssert;
    InputStream testData;
    Properties prop;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        softAssert = new SoftAssert();
    }

    //HomePage
    @FindBy(xpath = "//button[text()='SIGN UP FOR FREE']")
    WebElement signUpButton;
    @FindBy(xpath = "//center//button[text()='SIGN UP FOR FREE']")
    WebElement signUp;
    @FindBy(xpath = "//div[@class='webinar-banner-site']//img[contains(@src,'close')]")
    WebElement closeBtn;
    @FindBy(xpath = "//input[@type='email']")
    WebElement email;
    @FindBy(xpath = "//input[@type='password']")
    WebElement password;
    @FindBy(xpath = "//span[text()='business']//ancestor::div[@class='plan-button relative']")
    WebElement business;
    @FindBy(id = "Marketing")
    WebElement personalizedMarketing;
    @FindBy(id = "Business-next-btn")
    WebElement businessNextBtn;
    @FindBy(id = "firstNameTxt")
    WebElement firstNameTxt;
    @FindBy(xpath = "//div[contains(@class,'page-loading')]")
    WebElement loader;


    public void signUpForFree() {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            if (closeBtn.isDisplayed()) {
                executor.executeScript("arguments[0].click();", closeBtn);
            }
            signUpButton.click();
            email.sendKeys("hiphopvideo348771@mailinator.com");
            password.sendKeys("Tester.3545");
            signUp.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean selectPlans() {
        boolean res = false;
        try {
            signUpForFree();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            business.click();
            personalizedMarketing.click();
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement nextBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Business-next-btn")));
            nextBtn.click();
            WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='firstNameTxt']")));
            res = firstName.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

}
