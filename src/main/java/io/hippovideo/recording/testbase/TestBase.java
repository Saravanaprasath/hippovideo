package io.hippovideo.recording.testbase;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    public static String url = "https://www.hippovideo.io/";
    public static ExtentReports extentReports;
    public static ExtentTest extentTest;
    public static String browser = "Chrome";

    public TestBase() {
        if (driver == null) {
            initDriver();
        }
    }

    public static void initDriver() {
        String timestamp = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(Calendar.getInstance().getTime()).replaceAll(":", "-");
        String reportName = "AutomationReport_" + timestamp;
        extentReports = new ExtentReports();
        ExtentHtmlReporter reporter = new ExtentHtmlReporter("ExecutionReports/" + reportName + ".html");
        extentReports.attachReporter(reporter);
        String os = System.getProperty("os.name");
        extentReports.setSystemInfo("Operating System", os);
        os = System.getProperty("os.name").toLowerCase();
        System.out.println(os);
        if (browser.equalsIgnoreCase("chrome")) {
            if (os.contains("linux")) {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
            }
            if (os.contains("win")) {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            }
            if (os.contains("mac")) {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver_mac");
            }
            ChromeOptions chromeOptions = new ChromeOptions();
            //chromeOptions.addArguments("--disable-notifications");
            //chromeOptions.addExtensions(new File("extentions/hippovideoext.crx"));
            //DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
            //desiredCapabilities.setCapability(ChromeOptions.CAPABILITY,chromeOptions);
            driver = new ChromeDriver(chromeOptions);
            driver.manage().window().maximize();
            driver.get(url);
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            WebDriverWait wait = new WebDriverWait(driver, 15000);
        }
    }

    @AfterSuite
    public void getReport() {
        //driver.close();
        extentReports.flush();
        System.out.println("Report Generated");
    }
}
