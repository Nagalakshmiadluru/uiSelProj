package com.merchantesolutions.ui.pageobjects;

import com.merchantesolutions.common.Constants;
import com.merchantesolutions.common.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Date;
import java.util.List;

/**
 * Users Page object class
 */
public class UsersPage {
    @FindBy(xpath = "//*[@id='page_title']")
    WebElement titleUsers;

    @FindBy(xpath = "//a[contains(.,'New User')]")
    WebElement linkNewUser;

    @FindBy(xpath = "//*[@id='q_username_input']//select")
    WebElement selectUsername;

    @FindBy(xpath = "//*[@id='q_username']")
    WebElement inputUsername;

    @FindBy(xpath = "//*[@id='q_email_input']//select")
    WebElement selectEmail;

    @FindBy(xpath = "//*[@id='q_email']")
    WebElement inputEmail;

    @FindBy(xpath = "//*[@id='q_created_at_gteq_datetime']")
    WebElement inputDateRangeStart;

    @FindBy(xpath = "//*[@id='q_created_at_lteq_datetime']")
    WebElement inputDateRangeEnd;

    @FindBy(xpath = "//input[@value='Filter']")
    WebElement submitFilter;

    @FindBy(xpath = "//a[@class='clear_filters_btn']")
    WebElement clearFilters;

    @FindBy(xpath = Constants.XPATH_USERS_TABLE)
    WebElement usersTable;

    WebDriver driver;

    public UsersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * To verify this page
     */
    public void verifyPage() {
        Assert.assertEquals(titleUsers.getText().equalsIgnoreCase(Constants.TXT_USERS), true, "Users page title verification failed.");
    }

    public void selectUsernameDropdownOptionAndFilter(String filterType, String filterValue) {
        Select select = new Select(selectUsername);
        if (!select.getFirstSelectedOption().getText().equalsIgnoreCase(filterType)) {
            select.selectByValue(filterType);
        }
        enterUsername(filterValue);
        clickFilter();
    }

    public void selectEmailDropdownOptionAndFilter(String filterType, String filterValue) {
        Select select = new Select(selectEmail);
        if (!select.getFirstSelectedOption().getText().equalsIgnoreCase(filterType)) {
            select.selectByVisibleText(filterType);
        }
        enterEmail(filterValue);
        clickFilter();
    }

    public void enterOrChooseCreatedAtRangeAndFilter(String rangeStart, String rangeEnd) {
        enterDateInput(inputDateRangeStart, rangeStart);
        enterDateInput(inputDateRangeEnd, rangeEnd);
        clickFilter();

        //TODO: select dates from calendar
        /*
        //created at from date
        driver.findElement(By.xpath("//input[@id='q_created_at_gteq_datetime']")).click();
        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[2]/td[4]/a")).click();
        //created at to date
        driver.findElement(By.xpath("//input[@id='q_created_at_lteq_datetime']")).click();
        driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/table/tbody/tr[3]/td[1]/a")).click();
        */


    }

    public void enterUsername(String userNameFilter) {
        inputUsername.clear();
        inputUsername.sendKeys(userNameFilter);
    }

    public void enterEmail(String emailFilter) {
        inputEmail.clear();
        inputEmail.sendKeys(emailFilter);
    }

    public void enterDateInput(WebElement element, String inputString) {
        element.clear();
        element.sendKeys(inputString);
    }

    public void clickFilter() {
        submitFilter.click();
    }

    public void clearFilter() {
        clearFilters.click();
    }

    public void clickOnNewUser() {
        linkNewUser.click();
    }

    public boolean isFilterSectionEnabled() {
        WebDriverWait wait = new WebDriverWait(driver, 5);

        try {
            WebElement filterSection = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='filters_sidebar_section']")));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


/*
    //get text value
    String value = driver.findElement(By.xpath("//*[@id='q_created_at_input']/label")).getText();
  	    System.out.println("Value is" + value);

*/


    public void verifyFilterResultForUsername(String filterType, String filterToMatch) {
        String updatedStringToMatch = filterToMatch.trim().toLowerCase();
        List<WebElement> tableRows = usersTable.findElements(By.xpath(Constants.XPATH_USERS_TABLE + "/tr"));
        if (tableRows.isEmpty()) {
            System.out.println("No results found for the filter");
            //TODO: Check if results should have valid response, then need to add as negative scenario.
        } else {
            for (WebElement row : tableRows) {
                if (filterType.equals(Constants.TXT_CONTAINS))
                    Assert.assertEquals(row.findElement(By.xpath("//td[contains(@class, 'col-username')]")).getText().toLowerCase().contains(updatedStringToMatch), true, "Expected:" + filterToMatch + " not contained for username value in " + row.getText());
                else if (filterType.equals(Constants.TXT_EQUALS))
                    Assert.assertEquals(row.findElement(By.xpath("//td[contains(@class, 'col-username')]")).getText().equalsIgnoreCase(updatedStringToMatch), true, "Expected:" + filterToMatch + " not equals for username value in " + row.getText());
                else if (filterType.equals(Constants.TXT_STARTSWITH))
                    Assert.assertEquals(row.findElement(By.xpath("//td[contains(@class, 'col-username')]")).getText().toLowerCase().startsWith(updatedStringToMatch), true, "Expected:" + filterToMatch + " not a prefix for username value in " + row.getText());
                else if (filterType.equals(Constants.TXT_STARTSWITH))
                    Assert.assertEquals(row.findElement(By.xpath("//td[contains(@class, 'col-username')]")).getText().toLowerCase().endsWith(updatedStringToMatch), true, "Expected:" + filterToMatch + " not a suffix for username value in " + row.getText());
            }
        }
    }

    public void verifyFilterResultForEmail(String filterType, String filterToMatch) {
        String updatedStringToMatch = filterToMatch.trim().toLowerCase();
        List<WebElement> tableRows = usersTable.findElements(By.xpath(Constants.XPATH_USERS_TABLE + "/tr"));
        if (tableRows.isEmpty()) {
            System.out.println("No results found for the filter");
            //TODO: Check if results should have valid response, then need to add as negative scenario.
        } else {
            for (WebElement row : tableRows) {
                if (filterType.equals(Constants.TXT_CONTAINS))
                    Assert.assertEquals(row.findElement(By.xpath("//td[contains(@class, 'col-email')]")).getText().toLowerCase().contains(updatedStringToMatch), true, "Expected:" + filterToMatch + " not contained for email value in " + row.getText());
                else if (filterType.equals(Constants.TXT_EQUALS))
                    Assert.assertEquals(row.findElement(By.xpath("//td[contains(@class, 'col-email')]")).getText().equalsIgnoreCase(updatedStringToMatch), true, "Expected:" + filterToMatch + " not equals for email value in " + row.getText());
                else if (filterType.equals(Constants.TXT_STARTSWITH))
                    Assert.assertEquals(row.findElement(By.xpath("//td[contains(@class, 'col-email')]")).getText().toLowerCase().startsWith(updatedStringToMatch), true, "Expected:" + filterToMatch + " not a prefix for email value in " + row.getText());
                else if (filterType.equals(Constants.TXT_STARTSWITH))
                    Assert.assertEquals(row.findElement(By.xpath("//td[contains(@class, 'col-email')]")).getText().toLowerCase().endsWith(updatedStringToMatch), true, "Expected:" + filterToMatch + " not a suffix for email value in " + row.getText());
            }
        }
    }

    public void verifyFilterResultForCreatedAt(Date rangeStartDate, Date rangeEndDate) {
        List<WebElement> tableRows = usersTable.findElements(By.xpath(Constants.XPATH_USERS_TABLE + "/tr"));
        if (tableRows.isEmpty()) {
            System.out.println("No results found for the filter");
            //TODO: Check if results should have valid response, then need to add as negative scenario or assert failure.
        } else {
            DateUtils dateUtils = new DateUtils();
            for (WebElement row : tableRows) {
                    String createdAtString = row.findElement(By.xpath("//td[contains(@class, 'col-created_at')]")).getText();
                    Date createdAtDate = dateUtils.getDate(createdAtString, "MMMM d, yyyy HH:mm");
                    Assert.assertEquals(!rangeStartDate.after(createdAtDate) &&  !rangeEndDate.before(createdAtDate), true, "CreatedAt Date from filtered result not in filterRange from response: " + createdAtString);
            }
        }
    }
}
