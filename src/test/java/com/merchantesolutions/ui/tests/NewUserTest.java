package com.merchantesolutions.ui.tests;

import com.merchantesolutions.common.Constants;
import com.merchantesolutions.common.SeleniumUtils;
import com.merchantesolutions.ui.pageobjects.NewUserPage;
import com.merchantesolutions.ui.pageobjects.UsersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class NewUserTest {
    WebDriver driver;

    static int count = 100;

    @Test
    public void createNewUser() {
        driver = SeleniumUtils.getDriver();
        driver.get(Constants.BASE_URL + "admin/users/new");
        NewUserPage newUserPage = new NewUserPage(driver);
        newUserPage.verifyPage();

        newUserPage.createUser("user" + count, "user" + count, "user" + count++ + "@email.com");
        newUserPage.verifyPage();
        newUserPage.verifyNewUserCreation();
    }

    //TODO: New user creation confirmation/details page can be validated for customer details.

    @AfterTest
    public void closeDriver() {
        this.driver.close();
    }
}
