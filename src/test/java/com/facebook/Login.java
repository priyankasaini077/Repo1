package com.facebook;



	import java.io.FileInputStream;
	import java.io.IOException;
	import java.util.Properties;
	import java.util.concurrent.TimeUnit;

	import org.apache.log4j.Logger;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.annotations.AfterSuite;
	import org.testng.annotations.BeforeSuite;


	public class Login{
	public static WebDriver driver;
	public static Logger log=Logger.getLogger("devpinoyLogger");
	Properties OR=new Properties();
	Properties Config=new Properties();
	FileInputStream fis;
	public static WebDriverWait wait;
		@BeforeSuite
		public void setup() throws IOException {
			if(driver==null)
			{
				fis=new FileInputStream(System.getProperty("user.dir")+"\\properties\\Config.properties");
			   Config.load(fis);	
			  log.debug("Config properties loaded!!");
			   fis=new FileInputStream(System.getProperty("user.dir")+"\\properties\\OR.properties");
			   OR.load(fis);	
			   log.debug("OR properties loaded!!");
			   
			}
			
			
			if(Config.getProperty("browser").equals("chrome"))
			{
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\exectutables\\chromedriver.exe");
				
				driver=new ChromeDriver();
				log.debug("chrome loaded!!");
				}
			
				else if(Config.getProperty("browser").equals("firefox"))
			{        FirefoxProfile profile = new FirefoxProfile();
	        profile.setPreference("dom.webnotifications.enabled", false);
	        driver = new FirefoxDriver(profile);
				log.debug("firefox loaded!!");
			}
			else if(Config.getProperty("browser").equals("IE"))
			{   
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\exectutables\\IEDriverServer.exe");
			 
				driver=new InternetExplorerDriver();
				log.debug("IE loaded!!");
			}
			
			driver.get(Config.getProperty("testSiteURL"));
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implict.wait")),TimeUnit.SECONDS);
		    wait=new WebDriverWait(driver,Integer.parseInt(Config.getProperty("explict.wait")));
		
		    
		}
		
		public boolean isElementPresent(String locator)
		{
			try
			{if(locator.endsWith("_xpath")) 
			{
			driver.findElement(By.xpath(OR.getProperty(locator)));	
			}
			else if(locator.endsWith("_css")) 
			{
				driver.findElement(By.cssSelector(OR.getProperty(locator)));
		    }
			else if(locator.endsWith("_id")) 
			{
				driver.findElement(By.id(OR.getProperty(locator)));
		    }
			}
			catch(Throwable t)
			{
				log.debug("locator not found:"+locator+":"+t.getMessage());
			
			}
			return true;
			
			
		}
		
		public void click(String locator) {
			if(locator.endsWith("_xpath")) 
			{
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
			}
			else if(locator.endsWith("_css")) 
			{
				driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		    }
			else if(locator.endsWith("_id")) 
			{
				driver.findElement(By.id(OR.getProperty(locator))).click();
		    }
			log.debug("element clicked!");
		}
		public void type(String locator,String value) {
			if(locator.endsWith("_xpath")) 
			{
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
			}
			else if(locator.endsWith("_css")) 
			{
				driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		    }
			else if(locator.endsWith("_id")) 
			{
				driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		    }
			log.debug("element typed!");
			
		}

			
	   @AfterSuite
		public void tearDown() {
			driver.quit();
		}
	}



