package com.va.pages;

import com.aventstack.extentreports.ExtentTest;
import com.va.utility.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LoginPage {



    WebDriver driver;

   
    public LoginPage(WebDriver ldriver)
    {
        this.driver=ldriver;


    }

    //Login page UI elements
    @FindBy(id="email") WebElement email;
    @FindBy(id="password") WebElement password;
    @FindBy(xpath="//button[@type='submit']") WebElement signInButton;


    public void loginToApplication(String uname,String pass,ExtentTest logger)
    {
        Helper.waitForWebElementAndType(driver,email,uname,"Enter email",logger);
                //(driver, username, uname, "Enter username");
        Helper.waitForWebElementAndType(driver, password, pass, "Enter password",logger);
        Helper.waitForWebElementAndClick(driver, signInButton, "Click On Login button",logger);
        Assert.assertTrue(Helper.waitForURL(driver, "dashboard"));
    }
}
