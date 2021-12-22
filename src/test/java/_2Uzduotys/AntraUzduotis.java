package _2Uzduotys;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AntraUzduotis {

    public WebDriver driver;

    @DataProvider(name = "iPods")
    public Object[][] searchData() {
        return new Object[][]{
                {"iPod Nano"},
                {"iPod Touch"},
                {"iPod Shuffle"}
        };
    }


    public void invokeDriver() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("-ignore-certificate-errors");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void selectMenuMP3Player() {
        invokeDriver();
        driver.get("https://demo.opencart.com/");

        WebElement element = driver.findElement(By.partialLinkText("MP3 Players"));
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }

    @Test(priority = 2)
    public void clickOnShowAllMP3Players() {
        driver.findElement(By.partialLinkText("Show All MP3 Players")).click();
    }

    @Test(priority = 3)
    public void isMp3PlayerCategoryIsDisplayed() {
        WebElement mp3Category = driver.findElement(By.xpath("//a[contains(text(),'MP3 Players (4)'][@class='list-group-item active']"));
        boolean mp3PlayerBlockDisplayed = mp3Category.isDisplayed();
        assertTrue(mp3PlayerBlockDisplayed, "MP3 player block is not displayed");
    }

    @Test(priority = 4)
    public void clickOnListButton(){
        driver.findElement(By.id("list-view")).click();
    }

    @Test(dataProvider = "iPods")
    public void addToCart(){

    }
}
