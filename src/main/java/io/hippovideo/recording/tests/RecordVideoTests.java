package io.hippovideo.recording.tests;

import io.hippovideo.recording.pages.RecordVideo;
import io.hippovideo.recording.testbase.TestBase;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.aventstack.extentreports.Status.FAIL;
import static com.aventstack.extentreports.Status.PASS;

public class RecordVideoTests extends TestBase {
    RecordVideo recordVideo;
    SoftAssert softAssert = new SoftAssert();

    public RecordVideoTests() {
        super();
        recordVideo = PageFactory.initElements(driver, RecordVideo.class);
    }

    @Test
    public void openCreateVideoScreen() {
        extentTest = extentReports.createTest("Go to create video screen");
        boolean res = recordVideo.openCreateVideo();
        if (res) {
            extentTest.log(PASS, "Able to click create video ");
        } else {
            extentTest.log(FAIL, "Unable to click create video menu");
        }
        extentReports.flush();
    }

    @Test
    public void importVideo() {
        extentTest = extentReports.createTest("Import Video");
        boolean res = recordVideo.importVideo();
        if (res) {
            extentTest.log(PASS, "Able to import video");
        } else {
            extentTest.log(FAIL, "Unable to import video");
        }
        extentReports.flush();
    }

    @Test
    public void openPersonalization() {
        extentTest = extentReports.createTest("Open personalization");
        boolean res = recordVideo.personalizeVideo();
        if (res) {
            extentTest.log(PASS, "Able to open personalization in video");
        } else {
            extentTest.log(FAIL, "Unable to open personalization");
        }
        extentReports.flush();
    }

    @Test
    public void addPersonalizations() {
        extentTest = extentReports.createTest("Add personalizations");
        boolean res = recordVideo.addPersonalization();
        if (res) {
            extentTest.log(PASS, "Able to add personalization in video");
        } else {
            extentTest.log(FAIL, "Unable to add personalization in video");
        }
        extentReports.flush();
    }

    @Test
    public void verifyPersonalizations() {
        extentTest = extentReports.createTest("Verify personalizations added");
        boolean res = recordVideo.verifyPersonalization();
        if (res) {
            extentTest.log(PASS, "Verified successfully");
        } else {
            extentTest.log(FAIL, "Added personalization not found");
        }
        extentReports.flush();
    }
}