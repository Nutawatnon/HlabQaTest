package org.example.hlabtest;



import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
//import static org.junit.jupiter.api.Assertions.*;
import org.testng.Assert;
//import static com.codeborne.selenide.Condition.attribute;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;


public class MainPageTest {
    WebDriver driver;
    private int MaxPerPage = 40;


    @BeforeTest
    public void setup() {


//        *****if using chromedriver uncomment 2 lines below and comment safari driver*****

//        System.setProperty("webdriver.chrome.driver", "resource/Chromedriver_3");
//        driver = new ChromeDriver();

        driver = new SafariDriver();

    }


    public void Open_lazada(){

        driver.navigate().to("https://www.lazada.co.th/"); // Shopee required real credential login on webdriver, so I use lazada instead. Hope that is ok.

    }

    @Test //Change Language to EN
    public void test1() throws InterruptedException {
        Open_lazada();
        //  find change Language button and click
        WebElement SwitchLang = driver.findElement(By.id("topActionSwitchLang"));
        SwitchLang.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        // wait for pop up change language to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lzdSwitchPop\"]/div/div[2]/span")));
        WebElement langPopup = driver.findElement(By.xpath("//*[@id=\"lzdSwitchPop\"]/div/div[2]/span"));
        // click change language
        langPopup.click();
        WebElement CurrentLang = driver.findElement(By.xpath("//*[@id=\"topActionSwitchLang\"]/span"));
        String BttnLang = CurrentLang.getText();
        //  check if change language button actually change to English, If yes the button would show in Thai
        Assert.assertEquals("เปลี่ยนภาษา", BttnLang);
        driver.quit();


    }

    @Test
    public void test2() throws InterruptedException {
        driver = new SafariDriver(); //Change if using chromeDriver
        Open_lazada();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        // wait for search box tobe visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("q")));
        WebElement textBox = driver.findElement(By.id("q"));
        textBox.clear();
        // send search keywords
        textBox.sendKeys("baby toys");
        WebElement SearchBttn = driver.findElement(By.className("search-box__search--2fC5"));
        SearchBttn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("_17mcb")));
        WebElement ProductSection = driver.findElement(By.className("_17mcb"));
        // this list contain number of product items show as a list.
        List productResult = ProductSection.findElements(By.className("Bm3ON"));
        WebElement KeyWords = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div/div[1]/div[1]/div/div[1]/div/div/span[2]"));
        // test if result show as a list. maximum items per one page is 40
        Assert.assertEquals(MaxPerPage,productResult.size());
        System.out.println(productResult.size());
        // Check if the website actually search for baby toys.
        Assert.assertEquals("baby toys",KeyWords.getText());
        driver.quit();

    }



    @AfterEach
    public void teardown() {
        driver.quit();

    }






}








