package sauceDemo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class ComprarArticulo {

    private static final String URL_LOGIN = "https://www.saucedemo.com/";
    private static final String URL_PRODUCTS = "https://www.saucedemo.com/inventory.html";
    private static final String URL_CART = "https://www.saucedemo.com/cart.html";
    private static final String URL_CHECKOUTS1 = "https://www.saucedemo.com/checkout-step-one.html";
    private static final String URL_CHECKOUTS2 = "https://www.saucedemo.com/checkout-step-two.html";
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
    void pressButtonAddToCartTestOK(){
        driver.get(URL_PRODUCTS);

        pressButtonLoginTestOK();

        WebElement addButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        assertEquals("add-to-cart-sauce-labs-backpack", addButton.getAttribute("name"));
        addButton.click();

        WebElement div = driver.findElement(By.id("remove-sauce-labs-backpack"));
        String selectedButton = div.getText();
        assertEquals("REMOVE", selectedButton);
    }
    @Test
    void pressButtonCartContainerTestOK(){

        pressButtonAddToCartTestOK();

        driver.get(URL_PRODUCTS);

        WebElement AddButton = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a"));
        assertEquals("shopping_cart_link", AddButton.getAttribute("class"));
        AddButton.click();

        WebElement div = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]"));
        String titulo1 = div.getText();
        assertEquals("YOUR CART", titulo1);
    }
    @Test
    void pressButtonCheckoutShoppingTestOK(){

        pressButtonAddToCartTestOK();
        //pressButtonCartContainerTestOK();

        driver.get(URL_CART);

        WebElement checkoutButton = driver.findElement(By.id("checkout"));
        assertEquals("btn btn_action btn_medium checkout_button", checkoutButton.getAttribute("class"));
        checkoutButton.click();

        WebElement div = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span"));
        String titulo1 = div.getText();
        assertEquals("CHECKOUT: YOUR INFORMATION", titulo1);
    }
    @Test
    void textBoxInputInformationTestOK(){

        pressButtonAddToCartTestOK();

        driver.get(URL_CHECKOUTS1);

        WebElement firstName = driver.findElement(By.id("first-name"));
        firstName.sendKeys("Mary");

        firstName = driver.findElement(By.id("first-name"));
        String inputFnValue = firstName.getAttribute("value");
        assertEquals("Mary", inputFnValue);

        WebElement lastName = driver.findElement(By.id("last-name"));
        lastName.sendKeys("Stone");

        lastName = driver.findElement(By.id("last-name"));
        String inputLnValue = lastName.getAttribute("value");
        assertEquals("Stone", inputLnValue);

        WebElement postalCode = driver.findElement(By.id("postal-code"));
        postalCode.sendKeys("55000");

        postalCode = driver.findElement(By.id("postal-code"));
        String inputPcValue = postalCode.getAttribute("value");
        assertEquals("55000", inputPcValue);
    }

    @Test
    void pressButtonCheckoutContinueTestOK(){

        pressButtonAddToCartTestOK();
        //pressButtonCartContainerTestOK();

        driver.get(URL_CHECKOUTS1);

        WebElement firstName = driver.findElement(By.id("first-name"));
        firstName.sendKeys("Mary");

        WebElement lastName = driver.findElement(By.id("last-name"));
        lastName.sendKeys("Stone");

        WebElement postalCode = driver.findElement(By.id("postal-code"));
        postalCode.sendKeys("55000");

        WebElement continueButton = driver.findElement(By.id("continue"));
        assertEquals("submit-button btn btn_primary cart_button btn_action", continueButton.getAttribute("class"));
        continueButton.click();

        WebElement div = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span"));
        String titulo1 = div.getText();
        assertEquals("CHECKOUT: OVERVIEW", titulo1);
    }
    @Test
    void pressButtonCheckoutFinishTestOK(){

        pressButtonAddToCartTestOK();
        //pressButtonCheckoutContinueTestOK();
        driver.get(URL_CHECKOUTS2);

        WebElement finishButton = driver.findElement(By.id("finish"));
        assertEquals("FINISH", finishButton.getText());
        finishButton.click();

        WebElement div = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span"));
        String titulo1 = div.getText();
        assertEquals("CHECKOUT: COMPLETE!", titulo1);
    }
}

