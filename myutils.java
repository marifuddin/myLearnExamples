package classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import com.arsin.ArsinSeleniumAPI;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.thoughtworks.selenium.webdriven.commands.AlertOverride;

public class myutils {
	public static Properties config = null;	
	public static String basedir    = System.getProperty("user.dir");
	public ArsinSeleniumAPI oASelFW;	

	Calendar cal = Calendar.getInstance();
	int month    = cal.get(Calendar.MONTH)+1;
	int day      = cal.get(Calendar.DAY_OF_MONTH);
	int year     = cal.get(Calendar.YEAR);
	int day_new;
	public myutils(ArsinSeleniumAPI oASelFW)
	{
		this.oASelFW = oASelFW;
	}

	public void initialize()
	{
		try {
			config = new Properties();
			System.out.println("url *********"+basedir + File.separator + File.separator + "src" + File.separator + "config.properties");
			FileInputStream fis = new FileInputStream(basedir + File.separator + File.separator + "src" + File.separator + "config.properties");
			config.load(fis);
		} catch (Exception e) {
			System.out.println(e);
		}

	}




	public int timeOutSecs() 
	{
		int time = 0;
		try {
			String globalTimeOut = loadprops("timeout");
			time = Integer.parseInt(globalTimeOut);
			System.out.println(time);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return time;

	}

	public int shortWait()
	{
		int time = 0;
		try {
			String shortTimeOut = loadprops("shorttimeout");
			time = Integer.parseInt(shortTimeOut);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return time;
	}

	public String loadprops(String prop) throws IOException
	{
		FileInputStream fis = new FileInputStream(basedir + File.separator + File.separator + "src" + File.separator + "constant.properties");
		config = new Properties();
		config.load(fis);
		String value = config.getProperty(prop);
		return value;
	}

	public String loadinstanceprops(String prop) throws IOException
	{
		FileInputStream fis = new FileInputStream(basedir + File.separator + File.separator + "src" + File.separator + "constant.properties");
		config = new Properties();
		config.load(fis);
		String value = config.getProperty(prop);
		return value;
	}

	/**
	 * This method will wait on Alert to be present withing the Short Time Out
	 * @return
	 */
	public boolean waitOnAlert()
	{
		try {
			WebDriverWait wait = new WebDriverWait(oASelFW.driver, shortWait());
			wait.until(ExpectedConditions.alertIsPresent());
			return true;
		} catch (TimeoutException e) {
			sendReport("Waiting for Alert", "Expected Alert is not found", "Fail");
			return false;
		}
		catch (org.openqa.selenium.NoAlertPresentException e1) {
			sendReport("Waiting for Alert", "Expected Alert is not found", "Fail");
			return false;
		}
	}


	public void acceptAlert()
	{
		waitOnAlert();
		oASelFW.driver.switchTo().alert().accept();
		sendReport("Accepting Alert", "Successfully Accepted Alert", "Pass");
	}
	public void dismissAlert()
	{
		waitOnAlert();
		oASelFW.driver.switchTo().alert().dismiss();
		sendReport("Dismissing Alert", "Successfully Dismiss Alert", "Pass");
	}

	public String getTextFromAlert()
	{
		waitOnAlert();
		return oASelFW.driver.switchTo().alert().getText();
	}

	public HashMap<String, String> switchTabs()
	{
		String defaultcontent =oASelFW.driver.getWindowHandle();
		String default_title =oASelFW.driver.switchTo().defaultContent().getTitle();
		HashMap<String, String> tab =new HashMap<String, String>();
		ArrayList<String> tabs =new ArrayList<String>(oASelFW.driver.getWindowHandles());

		for(String tabval : tabs)
		{
			System.out.println(tabval);
			String tabname =oASelFW.driver.switchTo().window(tabval).getTitle();
			System.out.println(tabname);
			tab.put(tabname, tabval);
		}
		tab.put(default_title, defaultcontent);

		return tab;
	}


	/**
	 * This method will wait for an element until it is clickable
	 * @param element - xpath
	 * @param waitime
	 */
	public boolean waitOnXpath(String element,long waitime)
	{
		try {
			WebDriverWait wait = new WebDriverWait(oASelFW.driver, waitime);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));
			Assert.assertTrue(oASelFW.driver.findElement(By.xpath(element)).isDisplayed());
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("no such element exception");
			sendReport("Waiting for "+element+" for "+waitime+" seconds", "expected element "+element+" not found after "+waitime+" seconds", "Fail");
			return false;
		}
		catch (org.openqa.selenium.TimeoutException e2) {
			System.out.println("Timed out exception");
			return false;
		}
	}

	public boolean waitonwebelement(WebElement element)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(oASelFW.driver, timeOutSecs());
			wait.until(ExpectedConditions.elementToBeClickable(element));
			Assert.assertTrue(element.isDisplayed());
			return true;
		}
		catch (NoSuchElementException e) {
			System.out.println("element not found");
			return false;
		}
	}

	public boolean waitOnText(String element)
	{
		try {
			WebDriverWait wait = new WebDriverWait(oASelFW.driver, timeOutSecs());
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
			Assert.assertTrue(oASelFW.driver.findElement(By.xpath(element)).isDisplayed());
			return true;
		} catch (NoSuchElementException e) {
			sendReport("Waiting for element on text", "element not found at "+e, "Fail");
			return false;
		}
		catch (TimeoutException e1) {
			sendReport("Waiting for element on text", "element not found at "+e1, "Fail");
			return false;
			// TODO: handle exception
		}
	}

	public void handleAlert()
	{
		WebDriverWait wait = new WebDriverWait(oASelFW.driver, timeOutSecs());
		wait.until(ExpectedConditions.alertIsPresent());
		oASelFW.driver.switchTo().alert().accept();
	}

	public void clickOnElement(String element)
	{
		try {
			waitOnXpath(element, timeOutSecs());
			oASelFW.driver.findElement(By.xpath(element)).click();
			//	System.out.println("Printed <---");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickOnWebelement(WebElement imp_link)
	{
		oASelFW.driver.findElement((By) imp_link).click();
	}

	public void setTextField(String element,String value)
	{
		if(waitOnXpath(element, timeOutSecs()))
		{
			oASelFW.driver.findElement(By.xpath(element)).sendKeys(value);
			sendReport("Entering the Required value in field", "Successfully enter the Required Value as "+value+" ", "Pass");

		}
		else
		{
			sendReport("verify is user is able to set the value for the element "+element, "Unable to enter value for the element "+element+ "as element is not found or timed out after "+timeOutSecs()+"seconds", "Fail");
		}

	}
	
	
	public void cleartextField(String element)
	{
		if(waitOnXpath(element, 05))
		{
			oASelFW.driver.findElement(By.xpath(element)).clear();
			sendReport("Clear the text field", "Cleared the text field", "Pass");
			
		}
	}
	/**
	 * This method will select an element from drop down based on the visible text
	 * @param element
	 * @param text
	 * @return
	 */
	public void selectByVisibleText(String element, String text)
	{
		if(waitOnXpath(element, timeOutSecs()))
		{
			new Select(oASelFW.driver.findElement(By.xpath(element))).selectByVisibleText(text);
			sendReport("Select desired value from Drop down", "Selected "+ text, "Pass");
		}

		else
		{

			sendReport("Select desired value from Drop down",  text+" Not found", "Fail");
		}
	}
	/**
	 * This method will select an element from drop down based on the index
	 * @param element
	 * @param index
	 */
	public void selectByIndex(String element, int index)
	{
		if(waitOnXpath(element, timeOutSecs()))
		{
			new Select(oASelFW.driver.findElement(By.xpath(element))).selectByIndex(index);	
		}
	}

	/**
	 * This method will return text present within an element
	 * @param element
	 * @return
	 */
	public String getText(String element)
	{
		String found_text = null;
		if(waitOnText(element))
		{
			found_text = oASelFW.driver.findElement(By.xpath(element)).getText().trim();
		}
		return found_text;
	}

	/**
	 * This method will return the text based on the element attribute
	 * @param element
	 * @param Attribute
	 * @return
	 */
	public String getTextByAttribute(String element, String Attribute)
	{
		String found_text = null;
		if(waitOnText(element))
		{
			found_text = oASelFW.driver.findElement(By.xpath(element)).getAttribute(Attribute);
		}
		return found_text;
	}

	public void sendReport(String actual,String Expected,String Status)
	{
		oASelFW.effecta("sendReport",actual,Expected,Status);
	}

	public void sendReportWithoutExit(String actual, String Expected, String Status)
	{
		oASelFW.effecta("sendReportWithOutExit",actual,Expected,Status);
	}

	/**
	 * This method is used for uploading files using Native OS windows
	 * @param attachment
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void uploadAttachment(String attachment) throws InterruptedException, IOException{
		try{
			System.out.println("in attached upload");
			String filename=getAutomationPath()+"\\"+getProjectName()+"\\"+"autoit\\"+attachment;

			System.out.println(filename);
			Thread.sleep(2000);
			if(oASelFW.sBrowser.contains("Firefox")){
				System.out.println("Firefox");
				Process process = new ProcessBuilder(getAutomationPath()+getProjectName()+"\\"+"autoit\\NewUploadFirefox.exe",filename,"Open").start();
			}
			else{
				Process process = new ProcessBuilder(getAutomationPath()+getProjectName()+"\\"+"autoit\\NewUploadChrome.exe",filename,"Open").start();
			}
			Thread.sleep(5000);

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * This method return a random start and End dates for room creation
	 * @param date
	 * @return HashMap
	 */
	public HashMap<String, String> createDates(int date)
	{
		HashMap<String, String> dates = new HashMap<String, String>();
		int day_st = date+1;
		int day_nd = day_st+2;
		System.out.println(day_st);
		System.out.println(day_nd);
		if(day_st>=31)
		{
			System.out.println(month);
			month  = month+1;
			System.out.println(month);
			day_st = day_st-30;
			if(day_st == 0)
			{
				day_st = day_st+1;
				//day_nd = day_st+2;
			}
			day_nd = day_nd-30;
		}
		if(day_new<=day)
		{
			month = month+1;
		}
		String start_date = month+"/"+day_st+"/"+year;
		String end_date   = month+"/"+day_nd+"/"+year;
		System.out.println(start_date+"--"+end_date);
		day_new               = day_nd;
		System.out.println(day_new+"dnew");
		dates.put("startdate", start_date);
		dates.put("enddate", end_date);
		return dates;
	}

	public String getAutomationPath()
	{
		return System.getenv("Automation_Path");
	}

	public String getProjectName()
	{
		return System.getenv("Project_Name");
	}

	public int getday()
	{
		return day;
	}

	public int getupdatedday()
	{
		return day_new;
	}
	public void clean_ExistingData(String element)
	{
		try {
			WebDriverWait wait = new WebDriverWait(oASelFW.driver, timeOutSecs());
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
			oASelFW.driver.findElement(By.xpath(element)).clear();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Map<String,String> getEmailAddressFromString(String str)
	{
		Pattern p       = Pattern.compile("\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b",Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(str);
		Map<String, String> emails = new HashMap<String, String>();
		while(matcher.find())
		{
			int i=1;
			String key = "email"+i;
			emails.put(key, matcher.group());
			i++;
		}
		return emails;
	}

	public void getRandomNumber(int upper, int lower, int totalNum) throws InterruptedException
	{
		try{

			String next_Nav = "//a[contains(text(),'Next')]";
			int Random = (lower) + (int)(Math.random()* ( upper - lower + 1));
			int i =0;
			int j =0;
		do
		{
		
			if(waitOnXpath("//td[text()='"+i+".']", 15))
			{
				
			
				if(oASelFW.driver.findElement(By.xpath("//td[text()='"+i+".']/../td[1]/input")).isSelected())
				{
					
					sendReport("Click on the host check box", "The desired check box is already checked ", "Pass");
				}
				else
				{
					clickOnElement("//td[text()='"+i+".']/../td[1]/input");
					sendReport("Click on host check box", "Clicked on host's checkbox with Sr. no "+Random , "Pass");
					j++;
				}
				
				
			}
			else
			{
				System.out.println(i);
				clickOnElement(next_Nav);
				sendReport("Click on next", "Clicked", "Pass");
				Thread.sleep(3000);
			}
			
			i++;	
		}
		while(i<=100 && j==2);
		
			
			
		}
		catch(ElementNotFoundException e){

		}

	}
}


/*	for (int i = 1; i <=30 ; i++)
{

//System.out.println("Random numbers are "+Random);

//waitOnXpath("//input[@disabled='']", timeOutSecs());


if(waitOnXpath("//td[text()='"+i+".']", 15))
{

if(oASelFW.driver.findElement(By.xpath("//td[text()='"+i+".']/../td[1]/input")).isSelected())
{
	sendReport("Click on the host check box", "The desired check box is already checked ", "Pass");
	getRandomNumber(upper, lower, totalNum);
}
else
{
	clickOnElement("//td[text()='"+Random+".']/../td[1]/input");
	sendReport("Click on host check box", "Clicked on host's checkbox with Sr. no "+Random , "Pass");

}
Random++;
}

else
{
clickOnElement(next_Nav);
sendReport("Click on next", "Clicked", "Pass");
Thread.sleep(3000);

}*/


