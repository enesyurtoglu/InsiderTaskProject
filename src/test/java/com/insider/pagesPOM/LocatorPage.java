package com.insider.pagesPOM;

import com.insider.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LocatorPage {

    public LocatorPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "wt-cli-accept-all-btn")
    public WebElement acceptAllButton;

    @FindBy(xpath = "//span[.='More']")
    public WebElement moreButton;

    @FindBy(xpath = "//h5[.='Careers']")
    public WebElement careersLink;

    @FindBy(xpath = "//section[@id=\"career-our-location\"]/div/div/div/div//h3")
    public WebElement ourLocationsHeader;

    @FindBy(xpath = "//section[@data-id=\"a8e7b90\"]/div/div/div/div/div/div/div//h2")
    public WebElement lifeAtInsiderHeader;

    @FindBy(xpath = "//section[@id=\"career-find-our-calling\"]/div/div/a")
    public WebElement seeAllTeamsLink;

    @FindBy(xpath = "//section[@id=\"career-find-our-calling\"]/div/div/div/div[12]/div[1]//a")
    public WebElement qualityAssuranceJobLink;

    @FindBy(xpath = "//section[@id=\"page-head\"]/div/div/div//a")
    public WebElement seeAllQAJobs;

    @FindBy(id = "filter-by-location")
    public WebElement filterByLocation;

    @FindBy(id = "filter-by-department")
    public WebElement filterByDepartment;

    @FindBy(id = "jobs-list")
    public WebElement jobsList;

    @FindBy(css = "p[class='position-title font-weight-bold']")
    public List<WebElement> positionTitle;

    @FindBy(css = "span[class='position-department text-large font-weight-600 text-primary']")
    public List<WebElement> positionDepartment;

    @FindBy(css = "div[class='position-location text-large']")
    public List<WebElement> positionLocation;

    @FindBy (xpath = "    //a[.='Apply Now']")
    public List<WebElement> applyNowButtons;

    @FindBy (xpath = "//div[@id='jobs-list']//a")
    public WebElement applyNowButton;

}
