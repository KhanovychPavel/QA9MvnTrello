package org.example.tests;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;

import com.google.common.io.Files;
import org.example.SuiteConfiguration;
import org.example.pages.HomePageHelper;
import org.example.util.LogLog4j;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;



/**
 * Base class for TestNG-based test classes
 */
public class TestBase {

  protected static URL gridHubUrl = null;
  protected static String baseUrl;
  protected static Capabilities capabilities;

  public static String LOGIN = "benhakhenn@gmail.com";
  public static String PASSWORD = "windozesax";
  public static LogLog4j log4j = new LogLog4j();
  HomePageHelper homePage;


//  protected WebDriver driver;
protected EventFiringWebDriver driver;
//  Logger logger = LoggerFactory.getLogger(TestBase.class);

  public static class MyListener extends AbstractWebDriverEventListener {
    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
      log4j.info("Element has to be found: " + by);

    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
      log4j.info("Element was found: " + by);

    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
      log4j.error("ERROR: " + throwable);
      //   getScreen((TakesScreenshot) driver);
    }
  }

    private void getScreen(TakesScreenshot driver) {
      File tmp = driver.getScreenshotAs(OutputType.FILE);
      File screen = new File("screen - " + System.currentTimeMillis() + ".png");
      try {
        Files.copy(tmp, screen);
      } catch (IOException e) {
        e.printStackTrace();
      }
      log4j.info("See screens, " + screen);
    }

  @BeforeSuite(alwaysRun = true)
  public void initTestSuite() throws IOException {
    SuiteConfiguration config = new SuiteConfiguration();
    baseUrl = config.getProperty("site.url");
    if (config.hasProperty("grid.url") && !"".equals(config.getProperty("grid.url"))) {
      gridHubUrl = new URL(config.getProperty("grid.url"));
    }
    capabilities = config.getCapabilities();
  }

  @BeforeMethod(alwaysRun = true)
  public void initWebDriver(Method m, Object[] p) {
//    driver = WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities);
    ChromeOptions options = new ChromeOptions();
    options.addArguments("lang=" + "en");
    driver = new EventFiringWebDriver(new ChromeDriver(options));
    driver.register(new MyListener());
    driver.get(baseUrl);
    log4j.info("Start test: " + m);
    if(p.length != 0) {
      log4j.info(" --> With data: " + Arrays.asList(p));
    }
    homePage = PageFactory.initElements(driver, HomePageHelper.class);
    homePage.waitUntilPageIsLoaded();

  }

  @AfterMethod(alwaysRun = true)
  public void finishTest(ITestResult result) {
    if (result.getStatus() == ITestResult.FAILURE) {
      log4j.error("Test was failure");
      getScreen((TakesScreenshot) driver);
    }
  }


  @AfterMethod(alwaysRun = true)
  public void tearDown(ITestResult result, Method method) {
    if(result.isSuccess()){
      log4j.info("Test result: PASSED");
    }else{
      log4j.error("Test result: FAILED");
    }
    log4j.info("Stop test: " + result.getMethod().getMethodName());
    log4j.info("======================================================");
    driver.quit();  //WebDriverPool.DEFAULT.dismissAll();
  }
}
