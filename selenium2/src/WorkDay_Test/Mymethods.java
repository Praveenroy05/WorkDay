package WorkDay_Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.javascript.host.Window;

public class Mymethods 
{

	public WebDriver driver;
	
	public WebDriverWait wait;
	
	
	
	
	public String launch(String e, String d, String c)
	
	{
		
		if(e.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "D:\\Selenium2018\\Selenium\\selenium2\\src\\testdata\\chromedriver.exe");
			driver=new ChromeDriver();
		}
		else if(e.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "D:\\Selenium2018\\Selenium\\selenium2\\src\\testdata\\geckodriver.exe");
			
		
			
			driver=new FirefoxDriver();
		}
		
		else if(e.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver", "D:\\Selenium2018\\Selenium\\selenium2\\src\\testdata\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}
		else if(e.equalsIgnoreCase("opera"))
		{
			OperaOptions o= new OperaOptions();
			o.setBinary("D:\\Selenium2018");
			System.setProperty("webdriver.opera.driver", "D:\\Selenium2018\\Selenium\\selenium2\\src\\testdata\\operadriver.exe");
			driver=new OperaDriver(o);
		}
		else
		{
			return("Invalid Browser name");
			
		}
		
		driver.get(d);
		
		wait= new WebDriverWait(driver,50);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@aria-label='Email Address']")));
		
		driver.manage().window().maximize();
		
		
		return ("Successful");
		
	}
	
	public String fill(String e, String d, String c) throws Exception
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(e)));
		
		driver.findElement(By.xpath(e)).sendKeys(d);
		
		
		
		return ("Successful");
		
	}
	
	public String click(String e, String d, String c) throws Exception
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(e)));
		
		driver.findElement(By.xpath(e)).click();
	//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(e)));
		
		return ("Successful");
		
	}
	
	public String validateLogin(String e, String d, String c) throws Exception
	{
		
		
		try
		{
			switch(c)
			{
			
			case "all_valid":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Welcome,']")));

			driver.findElement(By.xpath("//*[text()='Welcome,']")).isDisplayed();
			
			break;
			

			case "email_blank" :
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='ERROR: Please enter a valid email']")));

			driver.findElement(By.xpath("//*[text()='ERROR: Please enter a valid email']")).isDisplayed();

			break;
			

			case "email_invalid":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='An account with this email does not exist.']")));

			driver.findElement(By.xpath("//*[text()='An account with this email does not exist.']")).isDisplayed();

			break;
			

			case "pwd_blank":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='ERROR: Please enter your password']")));

			driver.findElement(By.xpath("//*[text()='ERROR: Please enter your password']")).isDisplayed();

			break;
			

			case "pwd_invalid":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Invalid Username/Password']")));

			driver.findElement(By.xpath("//*[text()='Invalid Username/Password']")).isDisplayed();

			break;
			
			
			
			}
		}
			catch(Exception ex)
			{
				String temp= this.screenshot();
				
				return("Test interrupted & goto "+temp+".png");
			}
		return ("Passed");
		}
		
	public String validateRegistration(String e, String d, String c) throws Exception
	{
	
		if(c.equalsIgnoreCase("All_Valid_Reg") && driver.findElement(By.xpath("//*[text()='Welcome,']")).isDisplayed())
			
		{
			return("Passed");
		}
		
		else
		{
			String temp= this.screenshot();
			return("Test Failed & goto "+temp+".png");
		}
		
	}
	
	
	// 2nd way
	/*	{
			if(c.equalsIgnoreCase("all_valid")) //&& pk.findElement(By.xpath("//*[text()='SendSMS']")).isDisplayed())
				
			{
				return("Passed");
			}
			
			else if(c.equalsIgnoreCase("mbno_blank") && pk.findElement(By.xpath("//*[text()='Enter your mobile number']")).isDisplayed())
			{
				return("Passed");
			}
			
			else if(c.equalsIgnoreCase("mbno_invalid") && pk.findElement(By.xpath("(//*[contains(text(),'number is not register')])[1]")).isDisplayed())
			{
				return("Passed");
			}
			
			else if(c.equalsIgnoreCase("pwd_blank") && pk.findElement(By.xpath("(//*[text()='Enter password'])[1]")).isDisplayed())
				
			{
				return("Passed");
			}
			
			else if(c.equalsIgnoreCase("pwd_invalid") && pk.findElement(By.xpath("(//*[contains(text(),'Try Again.')])[1]")).isDisplayed())
				
			{
				return("Passed");
			}
			
			else
			{
				String temp= this.screenshot();
				return("Test Failed & goto "+temp+".png");
			}
			
			}
			catch(Exception ex)
			{
				String temp= this.screenshot();
				
				return("Test interrupted & goto "+temp+".png");
			}
		
		}*/
	
	
	public String closeSite(String e, String d, String c) throws Exception
	{
		driver.close();
		return("Done");
		
		
	}
	
	
	public String screenshot() throws IOException
	{
		Date dt= new Date();
		String ssname= dt.toString();
		File src=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest= new File(ssname +".png");
		FileUtils.copyFile(src, dest);
		
		return ssname;
		
		
	}
	
	
}
