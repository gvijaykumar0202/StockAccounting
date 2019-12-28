package DriverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunctionLibrary.FunctionLibrary;
import utilities.ExcelUtilities;

public class DriverScript 
{	
	@Test
	public void startTest() throws Exception
	{
		ExcelUtilities xl = new ExcelUtilities();
		WebDriver driver=null;
		ExtentReports reports= null;
		ExtentTest test = null;
		
		int rowcount_mastertestcases = xl.rowCount("MasterTestCases");
		
		for(int i=1; i<=rowcount_mastertestcases;i++)
		{
			String ModuleStatus="";
			String TCModule=xl.getData("MasterTestCases", i, 1);
			
			String Executionmode=xl.getData("MasterTestCases", i, 2);
			
			reports = new ExtentReports(System.getProperty("user.dir")+"\\Reports\\"+TCModule+FunctionLibrary.generateDate()+".html");
			
			test = reports.startTest(TCModule);
			
			if(Executionmode.equalsIgnoreCase("Y"))
			{
				int rowcount_TCModule = xl.rowCount(TCModule);
				
				for(int j=1; j<=rowcount_TCModule;j++)
				{
					String Description=xl.getData(TCModule, j, 0);
					String Function_Name=xl.getData(TCModule, j, 1);
					String Locator_Type=xl.getData(TCModule, j, 2);
					String Locator_Value=xl.getData(TCModule, j, 3);
					String Test_Data=xl.getData(TCModule, j, 4);
					
					
					try{
						
					
					if(Function_Name.equalsIgnoreCase("startBrowser"))
					{
						driver=FunctionLibrary.startBrowser();
						test.log(LogStatus.INFO, Description);
					}else if (Function_Name.equalsIgnoreCase("openApplication")){
						FunctionLibrary.openApplication();
						test.log(LogStatus.INFO, Description);
					}else if(Function_Name.equalsIgnoreCase("waitForElement")){
						FunctionLibrary.waitForElement(driver, Locator_Type, Locator_Value, Test_Data);
						test.log(LogStatus.INFO, Description);
					}else if(Function_Name.equalsIgnoreCase("typeAction")){
						FunctionLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
						test.log(LogStatus.INFO, Description);
					}else if(Function_Name.equalsIgnoreCase("clickAction")){
						FunctionLibrary.clickAction(driver, Locator_Type, Locator_Value);
						test.log(LogStatus.INFO, Description);
					}else if(Function_Name.equalsIgnoreCase("closeBrowser")){
						FunctionLibrary.closeBrowser(driver);
						test.log(LogStatus.INFO, Description);
					}
					xl.setData(TCModule, j, 5, "PASS");
					xl.setData("MasterTestCases", i, 3, "Pass");
					test.log(LogStatus.PASS, Description);
					
					}catch(Exception e)
					
					{
						
						System.out.println("Exception Handled");
						File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
						FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"\\Screens\\"+TCModule+FunctionLibrary.generateDate()+".png"));
						
						xl.setData(TCModule, j, 5, "Fail");
						xl.setData("MasterTestCases", i, 3, "Fail");
						test.log(LogStatus.FAIL, Description);
						break;
					}
					
					
					reports.flush();
					reports.endTest(test);
							
						
				}
			}else
			{
				xl.setData("MasterTestCases", i, 3, "Not Executed");
			}
			
		
		}
	}
	

	
}
