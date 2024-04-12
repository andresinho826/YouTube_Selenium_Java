package com.youtube.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.testng.Assert;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class YouTubeTest {

    private WebDriver driver ;
    String urlPage = "https://www.youtube.com/";
    String pathDriver = "src/main/resources/chromedriver.exe";

    SoftAssert softAssertion = new SoftAssert();

    @BeforeClass
    public void setUrlPage(){
        System.setProperty("webdriver.chrome.driver", pathDriver);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("acceptInsecureCerts", false);
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--incognito");
        options.merge(capabilities);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.navigate().to(urlPage);
    }

    @Test
    public void loginYouTube(){
        softAssertion.assertEquals(driver.getTitle(),"YouTube");

        WebElement signInBtn = driver.findElement(By.cssSelector("#end .yt-spec-touch-feedback-shape__fill"));
        signInBtn.click();

        WebElement user = driver.findElement(By.cssSelector("#identifierId"));
        user.sendKeys("andresinho826");

        WebElement nextBtnOne = driver.findElement(By.cssSelector("#identifierNext"));
        nextBtnOne.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#password > div.aCsJod.oJeWuf > div > div.Xb9hP > input")));

        WebElement pass = driver.findElement(By.cssSelector("#password > div.aCsJod.oJeWuf > div > div.Xb9hP > input"));
        pass.sendKeys("Jacob2020");

        WebElement nextBtnTwo = driver.findElement(By.cssSelector("#passwordNext"));
        nextBtnTwo.click();

    }

    @AfterClass
    public void tearDown(){
        driver.close();
    }

}
