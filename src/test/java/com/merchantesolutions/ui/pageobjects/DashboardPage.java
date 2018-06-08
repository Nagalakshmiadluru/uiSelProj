package com.merchantesolutions.ui.pageobjects;

import com.merchantesolutions.common.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Dashboard page object class
 */
public class DashboardPage {

    @FindBy(xpath = "//*[@id='page_title']")
    WebElement titleDashboard;

    @FindBy(xpath = "//a[contains(.,'Users')]")
    WebElement tabUsers;

    WebDriver driver;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * To verify this page
     */
    public void verifyPage() {
        Assert.assertEquals(titleDashboard.getText().equalsIgnoreCase(Constants.TXT_DASHBOARD), true, "Dashboard page title verification failed.");
    }

    public boolean isUsersLinkAvailable() {
        boolean isAvailable = false;
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            WebElement users = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(.,'Users')]")));
            isAvailable = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isAvailable;
    }

    /**
     * To click on users tab
     */
    public void clickOnUsers() {
        if (isUsersLinkAvailable()) {
            tabUsers.click();
        } else {
            System.out.println("Assert failure to fail the test case if we need 'Users' link compulsory for testcase");
        }
    }

}
