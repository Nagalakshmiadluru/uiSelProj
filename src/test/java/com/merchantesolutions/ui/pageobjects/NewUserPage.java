package com.merchantesolutions.ui.pageobjects;

import com.merchantesolutions.common.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class NewUserPage {

    @FindBy(xpath = "//*[@id='page_title']")
    WebElement titleNewUser;

    @FindBy(xpath = "//input[@id='user_username']")
    WebElement userName;

    @FindBy(xpath = "//input[@id='user_password']")
    WebElement passWord;

    @FindBy(xpath = "//input[@id='user_email']")
    WebElement userEmail;

    @FindBy(xpath = "//input[@type='submit']")
    WebElement createUser;

    @FindBy(xpath = "//div[@class='flash flash_notice']")
    WebElement flashNotice;

    WebDriver driver;

    public NewUserPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    /**
     * To verify this page
     */
    public void verifyPage() {
        Assert.assertEquals(titleNewUser.getText().equalsIgnoreCase(Constants.TXT_NEWUSER), true, "New User page title verification failed.");
    }

    /**
     * To create new user
     * @param username
     * @param pass
     * @param email
     */
    public void createUser(String username,String pass,String email){
        userName.sendKeys(username);
        passWord.sendKeys(pass);
        userEmail.sendKeys(email);
        createUser.click();
    }

    public void verifyNewUserCreation() {
        //Need to add wait and check
        Assert.assertEquals(flashNotice.getText().equalsIgnoreCase("User was successfully created.") , true, "User creation flash message didn't display");
    }

}
