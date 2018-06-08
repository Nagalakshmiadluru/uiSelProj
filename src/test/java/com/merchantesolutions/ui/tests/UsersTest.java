package com.merchantesolutions.ui.tests;

import com.merchantesolutions.common.Constants;
import com.merchantesolutions.common.DateUtils;
import com.merchantesolutions.common.SeleniumUtils;
import com.merchantesolutions.ui.pageobjects.UsersPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.Date;

public class UsersTest {
    WebDriver driver;


    @Test
    public void filterUsernameByContains() {

        //Need to pass as parameter or read from spreadsheet data with data provider.
        String filterToMatch = "est45";

        driver = SeleniumUtils.getDriver();
        driver.get(Constants.BASE_URL + "admin/users");
        UsersPage usersPage = new UsersPage(driver);
        usersPage.verifyPage();
        Assert.assertTrue(usersPage.isFilterSectionEnabled(), "Users filter section is not enabled.");
        String filterType = Constants.TXT_CONTAINS;
        usersPage.selectUsernameDropdownOptionAndFilter(filterType, filterToMatch);
        verifyFilterResult(usersPage, Constants.TXT_FILTERNAME_USERNAME, filterType, filterToMatch);
    }

    @Test
    public void filterEmailByStartsWith() {

        //Need to pass as parameter or read from spreadsheet data with data provider.
        String filterToMatch = "test456@";

        driver = SeleniumUtils.getDriver();
        driver.get(Constants.BASE_URL + "admin/users");
        UsersPage usersPage = new UsersPage(driver);
        usersPage.verifyPage();
        Assert.assertTrue(usersPage.isFilterSectionEnabled(), "Users filter section is not enabled.");
        String filterType = Constants.TXT_STARTSWITH;
        usersPage.selectEmailDropdownOptionAndFilter(filterType, filterToMatch);
        verifyFilterResult(usersPage, Constants.TXT_FILTERNAME_EMAIL, filterType, filterToMatch);
    }


    @Test
    public void filterCreatedAtByBetweenDates() {
        driver = SeleniumUtils.getDriver();
        driver.get(Constants.BASE_URL + "admin/users");
        UsersPage usersPage = new UsersPage(driver);
        usersPage.verifyPage();
        Assert.assertTrue(usersPage.isFilterSectionEnabled(), "Users filter section is not enabled.");

        //Need to pass as parameter or read from spreadsheet data with data provider.
        DateUtils dateUtils = new DateUtils();
        String rangeStart = "2018-06-01";
        Date rangeStartDate = dateUtils.getDate(rangeStart, "yyyy-MM-dd");
        String rangeEnd = "2018-06-05";
        Date rangeEndDate = dateUtils.getDate(rangeEnd, "yyyy-MM-dd");

        //Using static text input than selecting calendar for now.
        usersPage.enterOrChooseCreatedAtRangeAndFilter(rangeStart, rangeEnd);
        verifyFilterResult(usersPage, rangeStartDate, rangeEndDate);
    }


    public void verifyFilterResult(UsersPage usersPage, String filterName, String filterType, String filterToMatch) {
        if (filterName.equals(Constants.TXT_FILTERNAME_USERNAME)) {
            usersPage.verifyFilterResultForUsername(filterType, filterToMatch);
        } else if (filterName.equals(Constants.TXT_FILTERNAME_EMAIL)) {
            usersPage.verifyFilterResultForEmail(filterType, filterToMatch);
        }
    }

    public void verifyFilterResult(UsersPage usersPage, Date rangeStartDate, Date rangeEndDate) {
        usersPage.verifyFilterResultForCreatedAt(rangeStartDate, rangeEndDate);
    }

    @AfterTest
    public void closeDriver() {
        this.driver.close();
    }
}