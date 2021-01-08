package com.videoamigo.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class BrowserFactory {
    public static WebDriver startApplication(WebDriver driver, String browserName, String appURL)
    {
        switch (browserName) {
            case "Chrome":
                System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
                driver = new ChromeDriver();

                break;
            case "Firefox":
                System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case "IE":
                System.setProperty("webdriver.ie.driver", "./Drivers/IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                break;
            default:
                System.out.println("We do not support this browser");
                break;
        }
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(appURL);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        return driver;
    }


    public static void quitBrowser(WebDriver driver)
    {
        driver.quit();

    }


}
