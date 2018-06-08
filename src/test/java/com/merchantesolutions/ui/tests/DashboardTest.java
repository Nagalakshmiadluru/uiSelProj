package com.merchantesolutions.ui.tests;

import com.merchantesolutions.common.Constants;
import com.merchantesolutions.common.SeleniumUtils;
import com.merchantesolutions.ui.pageobjects.DashboardPage;
import com.merchantesolutions.ui.pageobjects.UsersPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class DashboardTest {
    WebDriver driver;
    DashboardPage dashboardPage;
    UsersPage usersPage;

    @Test
    public void verifyDashboard() {
        this.driver = SeleniumUtils.getDriver();
        driver.get(Constants.BASE_URL + "admin");
        dashboardPage = new DashboardPage(driver);
        dashboardPage.verifyPage();
    }

    @Test
    public void verifyUsersLinkPresenceAndClick() {
        verifyDashboard();
        Assert.assertEquals(dashboardPage.isUsersLinkAvailable(), true, "Users link not displayed/available");
        dashboardPage.clickOnUsers();
        usersPage = new UsersPage(driver);
        usersPage.verifyPage();
    }

    @AfterTest
    public void closeDriver() {
        this.driver.close();
    }
}
