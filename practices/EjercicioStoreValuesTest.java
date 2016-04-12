package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class EjercicioStoreValuesTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://demo.icebergcommerce.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testEjercicioStoreValues() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.xpath("//ul[@id='nav']/li[2]/ul/li/a/span")).click();
    // Storing the price of the first item & adding to cart
    String price1 = driver.findElement(By.xpath("(//span[@class='regular-price'])[1]")).getText();
    // ERROR: Caught exception [ERROR: Unsupported command [getEval | storedVars['price1'].substring(1,storedVars['price1'].length)  | ]]
    System.out.println("precio 1er producto " + price1);
    driver.findElement(By.xpath("(//button[@type='button'])[1]")).click();
    try {
      assertTrue(isElementPresent(By.cssSelector("li.success-msg")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.cssSelector("button.button.btn-continue")).click();
    // Storing the price of the second item & adding to cart
    String price2 = driver.findElement(By.xpath("(//span[@class='regular-price'])[2]")).getText();
    // ERROR: Caught exception [ERROR: Unsupported command [getEval | storedVars['price2'].substring(1,storedVars['price2'].length)  | ]]
    System.out.println("precio 2º producto " + price2);
    driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
    try {
      assertTrue(isElementPresent(By.cssSelector("li.success-msg")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    // Checking that the subtotal displayed is equals to the sum of the item1 and item2 prices collected
    // ERROR: Caught exception [ERROR: Unsupported command [getEval | parseFloat(storedVars['price1']) + parseFloat(storedVars['price2']) | ]]
    System.out.println("subtotal = " + subtotal);
    String subtotal_pantalla = driver.findElement(By.cssSelector("td.a-right > span.price")).getText();
    // ERROR: Caught exception [ERROR: Unsupported command [getEval | storedVars['subtotal_pantalla'].substring(1,storedVars['subtotal_pantalla'].length)  | ]]
    // ERROR: Caught exception [ERROR: Unsupported command [getEval | parseFloat(storedVars['subtotal_pantalla'])==parseFloat(storedVars['subtotal']) | ]]
    // cleaning the cart
    driver.findElement(By.cssSelector("a.top-link-cart")).click();
    driver.findElement(By.id("empty_cart_button")).click();
    driver.findElement(By.linkText("here")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
