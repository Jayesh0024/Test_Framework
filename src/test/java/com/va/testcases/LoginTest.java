package com.va.testcases;

import com.va.pages.BaseClass;
import com.va.pages.LoginPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class LoginTest extends BaseClass {


    @Test(priority = 1)
    public void loginApp() {

        logger = report.createTest("Login to CRM");

        LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);





        logger.info("Starting Application");

        loginPage.loginToApplication(excel.getStringData("Login", 0, 0), excel.getStringData("Login", 0, 1),logger);

        logger.pass("Login Success");

    }
}
