package com.videoamigo.pages;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.videoamigo.utility.BrowserFactory;
import com.videoamigo.utility.ConfigDataProvider;
import com.videoamigo.utility.ExcelDataProvider;
import com.videoamigo.utility.Helper;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

public class BaseClass {
    public WebDriver driver;
    public ExcelDataProvider excel;
    public ConfigDataProvider config;
    public ExtentReports report;
    public ExtentTest logger;
    String reportPath;

    @BeforeSuite
    public void setUpSuite()
    {

        Reporter.log("Setting up reports and Test is getting ready", true);

        excel = new ExcelDataProvider();
        config = new ConfigDataProvider();

        reportPath=System.getProperty("user.dir")+"/Reports/VA_"+Helper.getCurrentDateTime()+".html";

        ExtentHtmlReporter extent=new ExtentHtmlReporter(new File(reportPath));
        report=new ExtentReports();
        report.attachReporter(extent);

        Reporter.log("Setting Done- Test can be started", true);
    }

    @Parameters({"executionBrowser","appURL"})
    @BeforeClass
    public void setup(String browser,String url)
    //public void setup()
    {

        Reporter.log("Trying to start Browser and Getting application ready", true);

       // driver = BrowserFactory.startApplication(driver, config.getBrowser(), config.getStagingURL());

        driver = BrowserFactory.startApplication(driver, browser,url);

        Reporter.log("Browser and Application is up and running", true);

    }

    @AfterClass
    public void tearDown() {
        BrowserFactory.quitBrowser(driver);
    }

    @AfterMethod
    public void tearDownMethod(ITestResult result) throws IOException
    {
        Reporter.log("Test is about to end ", true);


        if (result.getStatus() == ITestResult.FAILURE)
        {
            logger.fail("Test Failed ", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
        }
        else if(result.getStatus()==ITestResult.SUCCESS)
        {
            logger.pass("Test passed ", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());

        }

        report.flush();

        Reporter.log("Test Completed >>> Reports Generated", true);

        Reporter.log("Report can be accessed via >>> "+reportPath,true);

    }
}
