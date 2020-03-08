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

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    @FindBy(id = "firstNameTxt")
    WebElement firstName;
    @FindBy(id = "companyNameTxt")
    WebElement companyName;
    @FindBy(id = "phoneTxt")
    WebElement phoneNumber;
    @FindBy(id = "saveCompanyName")
    WebElement getStartedBtn;


    public void signUpForFree() {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            if (closeBtn.isDisplayed()) {
                executor.executeScript("arguments[0].click();", closeBtn);
            }
            signUpButton.click();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            long mail = timestamp.getTime();
            String em = Long.toString(mail);
            email.sendKeys("hippovideo" + em + "@mailinator.com");
            password.sendKeys("hippovideo");
            signUp.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean selectPlans() {
        boolean res = false;
        try {
            signUpForFree();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            WebDriverWait wait = new WebDriverWait(driver, 15000);
            WebElement businessBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='business']//ancestor::div[@class='plan-button relative']")));
            businessBtn.click();
            // business.click();
            personalizedMarketing.click();
            //WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement nextBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Business-next-btn")));
            nextBtn.click();
            WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='firstNameTxt']")));
            res = firstName.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public boolean fillDomainForms() {
        boolean res = false;
        try {
            firstName.sendKeys("saravana");
            companyName.sendKeys("hippovideo");
            phoneNumber.sendKeys("9875642310");
            getStartedBtn.click();
            WebDriverWait wait = new WebDriverWait(driver, 5000);
            WebElement createVideoIcn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'importContainer')]")));
            res = createVideoIcn.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

}
