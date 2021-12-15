import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HacerLogout {
    private static final String URL_LOGIN = "https://www.saucedemo.com/";
    WebDriver driver = null;

    @BeforeEach
    void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver.exe");

        driver = new ChromeDriver();
        //driver.manage().window().maximize();
    }
    @AfterEach
    void tearDown(){
        driver.quit();
    }
    @Test
    void testTitle(){
        driver.get(URL_LOGIN);

        String title = driver.getTitle();
        assertEquals("Swag Labs",title);
    }
    @Test
    void textBoxInputUserTestOK() {
        driver.get(URL_LOGIN);

        WebElement userName = driver.findElement(By.id("user-name"));
        userName.sendKeys("standard_user");

        userName = driver.findElement(By.id("user-name"));
        String inputValue = userName.getAttribute("value");
        assertEquals("standard_user", inputValue);
    }
    @Test
    void textBoxInputPasswordTestOK() {
        driver.get(URL_LOGIN);

        WebElement userName = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        userName.sendKeys("secret_sauce");

        userName = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        String inputValue = userName.getAttribute("value");
        assertEquals("secret_sauce", inputValue);
    }
    @Test
    void pressButtonLoginTestOK(){
        driver.get(URL_LOGIN);

        WebElement input1 = driver.findElement(By.xpath("//*[@id=\"user-name\"]"));
        input1.sendKeys("standard_user");
        WebElement input2 = driver.findElement(By.id("password"));
        input2.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.cssSelector("#login-button"));
        assertEquals("login-button", loginButton.getAttribute("name"));
        loginButton.click();

        WebElement div = driver.findElement(By.tagName("span"));
        String titulo1 = div.getText();
        assertEquals("PRODUCTS", titulo1);
    }

    @Test
    void pressButtonOpenMenuLogoutTestOK(){
        pressButtonLoginTestOK();

        WebElement openMenuButton = driver.findElement(By.xpath("//*[@id=\"react-burger-menu-btn\"]"));
        String titulo1 = openMenuButton.getText();
        assertEquals("Open Menu", titulo1);
        openMenuButton.click();

        Actions actions = new Actions(driver);
        actions.moveToElement(openMenuButton).perform();
        WebElement logoutButton = driver.findElement(By.id("logout_sidebar_link"));
        assertEquals("LOGOUT", logoutButton.getText());
        logoutButton.click();

        WebElement init = driver.findElement(By.id("login-button"));
        String nameButton = init.getAttribute("value");
        assertEquals("Login", nameButton);
    }
}
