package DriverFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ERP_Functions

{
	static WebDriver driver;
	static String url;
	static String res;
	public static String launchApp(String url)
	{
		System.setProperty("webdriver.chrome.driver", "D:\\Vijay G\\DataDriven\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		if(driver.findElement(By.id("btnsubmit")).isDisplayed())
		{
			res="Pass";
		}else
			
		{	
			res="Fail";
		}
		return res;	
	}
	
	public String login(String username, String password) throws Exception
	{
		driver.findElement(By.id(username)).clear();
		driver.findElement(By.id(username)).sendKeys(username);
		driver.findElement(By.id(password)).clear();
		driver.findElement(By.id(password)).sendKeys(password);
		driver.findElement(By.id("btnsubmit")).click();
		
		Thread.sleep(5000);
		
		if(driver.findElement(By.id("btnsubmit")).isDisplayed())
		{
			res="Pass";
		}else
		{
			res="Fail";
		}
		return res;
	}
	public String supplier(String sname, String address, String city, String country, String cperson, String pnumber, String mail, String mnumber, String note)
	{
		driver.findElement(By.xpath("//*[@id='mi_a_suppliers']/a")).click();
		driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/div[1]/div[1]/div[1]/div/a/span")).click();
		
		String expdata = driver.findElement(By.id("x_Supplier_Number")).getAttribute("value");
		driver.findElement(By.id("x_Supplier_Name")).sendKeys(sname);
		driver.findElement(By.id("x_Address")).sendKeys(address);
		driver.findElement(By.id("x_City")).sendKeys(city);
		driver.findElement(By.id("x_Country")).sendKeys(country);
		driver.findElement(By.id("x_Contact_Person")).sendKeys(cperson);
		driver.findElement(By.id("x_Phone_Number")).sendKeys(pnumber);
		driver.findElement(By.id("x__Email")).sendKeys(mail);
		driver.findElement(By.id("x_Mobile_Number")).sendKeys(mnumber);
		driver.findElement(By.id("x_Notes")).sendKeys(note);
		
		driver.findElement(By.id("btnAction")).click();
		driver.findElement(By.xpath("//button[contains(text(),'OK!')]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'OK!')]")).click();
		
		if(driver.findElement(By.id("psearch")).isDisplayed())
		{
			driver.findElement(By.id("pserach")).clear();
			driver.findElement(By.id("psearch")).sendKeys(expdata);
			driver.findElement(By.id("btnsubmit")).click();
			String actdata = driver.findElement(By.xpath("//*[@id='r1_a_suppliers']/td[6]/div")).getText();
			
			if(expdata.equalsIgnoreCase(actdata))
			{
				res = "Pass";
			}else
			{
				res = "Fail";
			}
		}else
			
		{
			driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button")).click();
			driver.findElement(By.id("pserach")).clear();
			driver.findElement(By.id("psearch")).sendKeys(expdata);
			driver.findElement(By.id("btnsubmit")).click();
			String actdata = driver.findElement(By.xpath("//*[@id='r1_a_suppliers']/td[6]/div")).getText();
			
			if(expdata.equalsIgnoreCase(actdata))
			{
				res = "Pass";
			}else
			{
				res = "Fail";
			}
			
				
		}
		
		return res;
	}	
	
	public String logout()
	
	{
		driver.findElement(By.id("logout")).click();
		driver.findElement(By.xpath("//button[contains(text(),'OK!')]")).click();
		
		if(driver.findElement(By.id("btnsubmit")).isDisplayed())
		{
			res = "Pass";
		}else
		{
			res = "Fail";
		}
		return res;
	}
	
	
}
