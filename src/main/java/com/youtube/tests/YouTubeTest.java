package com.youtube.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.testng.Assert;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class YouTubeTest {

    private WebDriver driver ;
    String urlPage = "https://www.youtube.com/";
    String pathDriver = "src/main/resources/chromedriver.exe";
    SoftAssert softAssertion = new SoftAssert();

    @BeforeClass
    public void setUrlPage(){
        System.setProperty("webdriver.chrome.driver", pathDriver);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void searchTestngOnYouTube(){
        driver.navigate().to(urlPage);
        softAssertion.assertEquals(driver.getTitle(),"YouTube");

        String searching = "TestNG";

        WebElement inputSearch = driver.findElement(By.cssSelector("#search-form #search"));
        inputSearch.sendKeys(searching);

        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait1.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#search-form #search")));

        inputSearch.sendKeys(Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".style-scope ytd-video-renderer")));

        List<WebElement> elements = driver.findElements(By.cssSelector(".style-scope ytd-video-renderer"));
        //for (WebElement element : elements){}

        //softAssertion.assertTrue(elements.size() < 1);
        Assert.assertTrue(elements.size() <= 200);
    }

    @AfterClass
    public void tearDown(){
        driver.close();
    }

}
