package com.insider.insider_test;

import com.insider.pagesPOM.LocatorPage;
import com.insider.utilities.BrowserUtils;
import com.insider.utilities.ConfigurationReader;
import com.insider.utilities.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Task_03 {

    LocatorPage locator;
    Actions actions;
    Select selectLocation;
    Select selectDepartment;
    WebDriverWait wait;


    @BeforeMethod
    public void setUpBeforeTests() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        locator = new LocatorPage();
        actions = new Actions(Driver.getDriver());
        wait = new WebDriverWait(Driver.getDriver(), 10);
        locator.acceptAllButton.click();// To close cookies pop up window in case it opens in each run

    }

    @Test
    //1. Visit https://useinsider.com/ and check Insider home page is opened or not

    public void checkInsiderHomePage() {
        String expectedTitle = "#1 Leader in Individualized, Cross-Channel CX — Insider";
        BrowserUtils.verifyTitle(Driver.getDriver(), expectedTitle);
    }

    @Test
    //2. Select “More” menu in navigation bar, select “Careers” and check Career page, its
    //Locations, Teams and Life at Insider blocks are opened or not

    public void checkCareerPage() {
        locator.moreButton.click();
        locator.careersLink.click();

        Assert.assertTrue(locator.seeAllTeamsLink.isDisplayed());
        Assert.assertTrue(locator.ourLocationsHeader.isDisplayed());
        Assert.assertTrue(locator.lifeAtInsiderHeader.isDisplayed());
    }

    @Test
    //3. Click “See All Teams”, select Quality Assurance, click “See all QA jobs”, filter jobs by
    //Location - Istanbul, Turkey and department - Quality Assurance, check presence of
    //jobs list

    public void seeAllQAJobs() {
        locator.moreButton.click();
        locator.careersLink.click();
        BrowserUtils.scrollToElement(locator.seeAllTeamsLink);
        locator.seeAllTeamsLink.click();
        BrowserUtils.scrollToElement(locator.qualityAssuranceJobLink);
        locator.qualityAssuranceJobLink.click();
        locator.seeAllQAJobs.click();
        selectLocation = new Select(locator.filterByLocation);
        selectLocation.selectByVisibleText("Istanbul, Turkey");
        BrowserUtils.sleep(2000);
        selectDepartment = new Select(locator.filterByDepartment);
        selectDepartment.selectByVisibleText("Quality Assurance");

        Assert.assertTrue(locator.jobsList.isDisplayed());
    }

    @Test
    //4. Check that all jobs’ Position contains “Quality Assurance”, Department contains
    //“Quality Assurance”, Location contains “Istanbul, Turkey” and “Apply Now” button

    public void checkPositionDepartmentLocation() throws InterruptedException {
        locator.moreButton.click();
        locator.careersLink.click();
        BrowserUtils.scrollToElement(locator.seeAllTeamsLink);
        locator.seeAllTeamsLink.sendKeys(Keys.ENTER);
        BrowserUtils.scrollToElement(locator.qualityAssuranceJobLink);
        locator.qualityAssuranceJobLink.sendKeys(Keys.ENTER);
        locator.seeAllQAJobs.click();
        selectLocation = new Select(locator.filterByLocation);
        selectLocation.selectByVisibleText("Istanbul, Turkey");
        BrowserUtils.sleep(3);
        selectDepartment = new Select(locator.filterByDepartment);
        selectDepartment.selectByVisibleText("Quality Assurance");

        String expectedPositionTitle = "Quality Assurance";
        String expectedDepartment = "Quality Assurance";
        String expectedPositionLocation = "Istanbul, Turkey";
        String expectedApplyButtonName = "Apply Now";

        List<WebElement> actualPositionTitle = locator.positionTitle;
        List<WebElement> actualDepartment = locator.positionDepartment;
        List<WebElement> actualPositionLocation = locator.positionLocation;
        List<WebElement> actualApplyButtonName = locator.applyNowButtons;

        ((JavascriptExecutor) Driver.getDriver()).executeScript("window.scrollTo(0,500)");

        BrowserUtils.sleep(5);

        for (int i = 0; i < actualPositionTitle.size(); i++) {

            actions.moveToElement(actualPositionTitle.get(i)).perform();
            Assert.assertTrue(actualPositionTitle.get(i).getText().contains(expectedPositionTitle), "Position Title does NOT include QA");
            Assert.assertEquals(actualDepartment.get(i).getText(), expectedDepartment, "The Department name is NOT matching");
            Assert.assertEquals(actualPositionLocation.get(i).getText(), expectedPositionLocation, "The Position Location is NOT matching");
            Assert.assertEquals(actualApplyButtonName.get(i).getText(), expectedApplyButtonName, "Apply Button is Missing");

        }

    }

    @Test
    public void clickApplyButton() {
        locator.moreButton.click();
        locator.careersLink.click();
        BrowserUtils.scrollToElement(locator.seeAllTeamsLink);
        locator.seeAllTeamsLink.sendKeys(Keys.ENTER);
        BrowserUtils.scrollToElement(locator.qualityAssuranceJobLink);
        locator.qualityAssuranceJobLink.sendKeys(Keys.ENTER);
        locator.seeAllQAJobs.click();
        selectLocation = new Select(locator.filterByLocation);
        selectLocation.selectByVisibleText("Istanbul, Turkey");
        BrowserUtils.sleep(3);
        selectDepartment = new Select(locator.filterByDepartment);
        selectDepartment.selectByVisibleText("Quality Assurance");

        locator.applyNowButton.sendKeys(Keys.ENTER);

        BrowserUtils.switchWindowAndVerify(Driver.getDriver(), "jobs.lever.co/useinsider", "Insider. - Software Quality Assurance Engineer (Remote)");

    }

    @AfterMethod
    public static void quitDriver() {
        Driver.quitDriver();
    }
}
