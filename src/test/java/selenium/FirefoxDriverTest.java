package selenium;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FirefoxDriverTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	driver = new FirefoxDriver();
	baseUrl = "http://apexsoft-svr1.iptime.org:9090/web-demo";
	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
  }
  
  @Test
  public void testSelenium() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.id("txtKeyword")).clear();
    driver.findElement(By.id("txtKeyword")).sendKeys("자바");
    driver.findElement(By.id("btnSearch")).click();
    driver.findElement(By.xpath("(//a[contains(text(),'상세보기')])[2]")).click();
    String nextPageUrl = driver.findElement(By.xpath("(//a[contains(text(),'상세보기')])[2]")).getAttribute("href");
    driver.get(nextPageUrl);
    assertTrue(driver
    		.getTitle()
    		.startsWith("자바+안드로이드를 다루는 기술"));
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
