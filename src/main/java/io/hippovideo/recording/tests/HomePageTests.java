package io.hippovideo.recording.tests;

import io.hippovideo.recording.pages.HomePage;
import io.hippovideo.recording.testbase.TestBase;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.aventstack.extentreports.Status.FAIL;
import static com.aventstack.extentreports.Status.PASS;

public class HomePageTests extends TestBase {
    HomePage homePage;
    SoftAssert softAssert = new SoftAssert();


    public HomePageTests() {
        super();
        homePage = PageFactory.initElements(driver, HomePage.class);
    }

    /*@Test
    public void signUp() {
        extentTest = extentReports.createTest("Sign up for free");
        boolean res = homePage.signUpForFree();
        //softAssert.assertEquals(res, true);
        if (res) {
            extentTest.log(PASS, "Able to sign up");
        } else {
            extentTest.log(FAIL, "Unable to sign up");
        }
        extentReports.flush();
        //softAssert.assertAll();
    }*/

    @Test
    public void choosePlans() {
        extentTest = extentReports.createTest("Choose plans");
        boolean res = homePage.selectPlans();
        //softAssert.assertEquals(res, true);
        if (res) {
            extentTest.log(PASS, "Able to choose plans");
        } else {
            extentTest.log(FAIL, "Unable to choose plans");
        }
        extentReports.flush();
        //softAssert.assertAll();
    }

}
