package com.chromewebdriver.app;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import java.util.List;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ChromeWebDriverTests 
{
static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        System.out.println("launching chrome browser");
        String os = System.getProperty("os.name");
        
        ChromeOptions chromeOptions= new ChromeOptions();
        chromeOptions.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
    }

    @Test
    public void testAppCenterPageTitleInBrowser() {
        driver.navigate().to("https://appcenter.ms/");
        String strPageTitle = driver.getTitle();
        System.out.println("Page title: - " + strPageTitle);
        Assert.assertTrue("Page title doesn't match", strPageTitle.toLowerCase().contains("app center"));
        List<WebElement> elementsClass = driver.findElements(By.className("welcome-container"));
        Assert.assertTrue("Appcenter page wasn't loaded", elementsClass.size()>0);
        List<WebElement> elementsTag = driver.findElements(By.tagName("strong"));
        Assert.assertTrue("Content not found", elementsTag.stream().anyMatch(webElement -> webElement.getText().toLowerCase().contains("app center")));
    }

    // @Test
    // public void testPerformanceChrome() throws InterruptedException {
    //     WebDriverWait wait = new WebDriverWait(driver, 100000);
    //     driver.get("https://en.wikipedia.org/wiki/Main_Page");

    //     By searchInput = By.id("searchInput");
    //     wait.until(presenceOfElementLocated(searchInput));
    //     driver.findElement(searchInput).sendKeys("Software");
    //     By searchButton = By.id("searchButton");
    //     wait.until(elementToBeClickable(searchButton));
    //     driver.findElement(searchButton).click();

    //     wait.until(textToBePresentInElementLocated(By.tagName("body"),
    //             "Computer software"));
    // }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            System.out.println("Closing chrome browser");
            driver.quit();
        }
    }
}
