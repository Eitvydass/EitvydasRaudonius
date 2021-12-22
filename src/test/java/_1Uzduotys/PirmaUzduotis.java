package _1Uzduotys;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Order;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PirmaUzduotis {

    public WebDriver driver;


    public void invokeDriver() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("-ignore-certificate-errors");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void clickOnMyAccountButton() {
        invokeDriver();
        driver.get("https://demo.opencart.com/");

        WebElement myAccountButton = driver.findElement(By.className("dropdown"));
        myAccountButton.click();
    }

    @Test(priority = 2)
    public void clickOnLoginButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Login")));
        driver.findElement(By.partialLinkText("Login")).click();

    }

    @Test(priority = 3)
    public void checkIfNewCustomerBlockIsDisplayed() {

        WebElement newCustomerBlock = driver.findElement(By.cssSelector(".well"));
        boolean newCustomerBlockDisplayed = newCustomerBlock.isDisplayed();
        assertTrue(newCustomerBlockDisplayed, "New Customer block is not displayed");


    }

    @Test(priority = 4)
    public void checkIfReturningCustomerBlockIsDisplayed() {

        WebElement returningCustomerBlock = driver.findElement(By.cssSelector(".well"));
        boolean returningCustomerBlockDisplayed = returningCustomerBlock.isDisplayed();
        assertTrue(returningCustomerBlockDisplayed, "Returning Customer block is not displayed");

    }

    @Test(priority = 5)
    public void enterEmailAndPassword() {
        WebElement returningCustomerBlock = driver.findElement(By.cssSelector(".well"));
        boolean returningCustomerBlockDisplayed = returningCustomerBlock.isDisplayed();
        assertTrue(returningCustomerBlockDisplayed, "Returning Customer block is not displayed");

        if (returningCustomerBlockDisplayed) {
            driver.findElement(By.name("email")).sendKeys("test@test.lt");
            driver.findElement(By.name("password")).sendKeys("1234567");
        } else {
            System.out.println("Paieškos langelis nerastas");
        }


    }

    @Test(priority = 6)
    public void clickLoginAfterEmailAndPasswordEnter() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        driver.findElement(By.xpath("//input[@type='submit' and @value='Login']")).click();

        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")), "Warning: No match for E-Mail Address and/or Password."));

    }

}
