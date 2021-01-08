package com.videoamigo.utility;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Helper {

    // Screenshot, alerts,frames, windows, Sync issue, javascript executor

    public static String captureScreenshot(WebDriver driver) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = System.getProperty("user.dir") + "/Screenshots/VA_" + getCurrentDateTime()
                + ".png";

        try {

            FileHandler.copy(src, new File(screenshotPath));

            System.out.println("Screenshot captured");


        } catch (IOException e) {

            System.out.println("Unable to capture screenshot " + e.getMessage());
        }

        return screenshotPath;

    }

    public static String getCurrentDateTime() {

        SimpleDateFormat myformat=new SimpleDateFormat("HH_mm_ss_dd_MM_yyyy");

        Date date=new Date();

        return myformat.format(date);

    }

    public static boolean waitForURL(WebDriver driver,String keyword)
    {

        return new WebDriverWait(driver, 30).until(ExpectedConditions.urlContains(keyword));
    }

    public static void acceptAlert(WebDriver driver, By locator, String logInfo)
    {
        new WebDriverWait(driver, 30).until(ExpectedConditions.alertIsPresent()).accept();
        System.out.println("LOG:INFO "+logInfo);
    }

    public static void acceptAlert(WebDriver driver,By locator,int time,String logInfo)
    {
        new WebDriverWait(driver, 30).until(ExpectedConditions.alertIsPresent()).accept();
        System.out.println("LOG:INFO "+logInfo);
    }

    public static void dismissAlert(WebDriver driver,By locator,String logInfo)
    {
        new WebDriverWait(driver, 30).until(ExpectedConditions.alertIsPresent()).dismiss();
        System.out.println("LOG:INFO "+logInfo);
    }

    public static void dismissAlert(WebDriver driver,By locator,int time,String logInfo)
    {
        new WebDriverWait(driver, 30).until(ExpectedConditions.alertIsPresent()).dismiss();
        System.out.println("LOG:INFO "+logInfo);
    }



    public static void waitForMessAndPartialVerify(WebDriver driver,By locator,String expected,String logInfo)
    {

        WebElement element=new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(locator));
        String actual=element.getText();
        if(actual.contains(expected))
        {
            System.out.println("LOG:PASS- Message verified");
        }
        else
        {
            System.out.println("LOG:FAIL- Message validation failed");
        }

        System.out.println("LOG:INFO- Waited for message validation");


		/*if(new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(locator)).getText().contains(expected))
		{

		}*/
    }


    public static WebElement waitForWebElement(WebDriver driver,By locator,String logInfo)
    {
        WebElement element=new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(locator));
        System.out.println("LOG:INFO-"+logInfo);
        return element;
    }


    public static void waitForWebElementAndType(WebDriver driver, WebElement locator, String textToBeType,
                                                String logInfo, ExtentTest logger)
    {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(locator)).sendKeys(textToBeType);
        System.out.println("LOG:INFO-"+logInfo);
        logger.info("LOG:INFO-"+logInfo);
    }

    public static void waitForWebElementAndClick(WebDriver driver,WebElement locator,String logInfo,ExtentTest logger)
    {
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(locator)).click();
        System.out.println("LOG:INFO-"+logInfo);
        logger.info("LOG:INFO-"+logInfo);
    }

    public static void waitForWebElementAndClick(WebDriver driver,WebElement locator,int time,String logInfo,ExtentTest logger)
    {
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(locator)).click();
        System.out.println("LOG:INFO-"+logInfo);
        logger.info("LOG:INFO-"+logInfo);

    }



    public static void waitForSeconds(int second)
    {
        try
        {
            Thread.sleep(second* 1000L);
        } catch (InterruptedException e)
        {
            System.out.println(e);
        }
    }




    public static void verifyBrokenLinkOrImage(String url) throws IOException {
        HttpURLConnection conn=(HttpURLConnection)new URL(url).openConnection();

        conn.connect();

        int code=conn.getResponseCode();

        String serverResponse=conn.getResponseMessage();
        System.out.println("Checking url "+url);
        System.out.println("Code from server is "+code);
        System.out.println("Response from server is "+serverResponse);

        // &&
        // ||

        if(code == 200 || code==301 || code==302)
        {
            System.out.println("Link is working fine");
        }
        else
        {
            System.out.println("Broken link "+url);
        }

    }


    public static void selectValueFromList(WebDriver driver,String xpath,String value)
    {
        List<WebElement> allSugg=driver.findElements(By.xpath(xpath));

        for(WebElement element:allSugg)
        {
            if(element.getText().contains(value))
            {
                System.out.println("LOG:INFO- Element found");
                element.click();
                break;
            }
        }
    }

    public static void selectValueFromListUsingIterator(WebDriver driver,String xpath,String value)
    {
        List<WebElement> allSugg=driver.findElements(By.xpath(xpath));

        for (WebElement element : allSugg) {
            if (element.getText().contains(value)) {
                System.out.println("LOG:INFO- Element found");
                element.click();
                break;
            }
        }

    }

}
