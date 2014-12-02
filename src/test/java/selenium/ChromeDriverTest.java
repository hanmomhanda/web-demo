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
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.chrome.driver", "D:/SW/chromedriver.exe");
	driver = new ChromeDriver();
	baseUrl = "http://apexsoft-svr1.iptime.org:9090/web-demo";
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
  }

  @Test
  public void chromeTest() throws Exception {
	    driver.get(baseUrl + "/");
	    driver.findElement(By.id("txtKeyword")).clear();
	    driver.findElement(By.id("txtKeyword")).sendKeys("자바");
	    driver.findElement(By.id("btnSearch")).click();
	    String bookTitle = driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div[2]/div/div/h3")).getText();
	    driver.findElement(By.xpath("(//a[contains(text(),'상세보기')])[2]")).click();
	    String nextPageUrl = driver.findElement(By.xpath("(//a[contains(text(),'상세보기')])[2]")).getAttribute("href");
	    driver.get(nextPageUrl);
	    assertTrue(driver
	    		.getTitle()
	    		.startsWith(bookTitle));
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
