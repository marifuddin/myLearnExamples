package classes;

import java.awt.Robot;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.openqa.jetty.servlet.SendRedirect;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.python.modules.thread.thread;

import com.arsin.ArsinSeleniumAPI;
import com.thoughtworks.selenium.webdriven.commands.Click;
import com.thoughtworks.selenium.webdriven.commands.KeyEvent;


public class SabaLearningPage {

	ArsinSeleniumAPI oASelFW;

	public SabaLearningPage() {
	}

	public SabaLearningPage(ArsinSeleniumAPI oASelFW) {
		this.oASelFW=oASelFW;
	}

	/* @Author - Mangala 
	 * Description - This method will Clicks on Learning LINKS[Manage Learning,Manage Skills,Manage Jobs,...]
	 * 
	 */


	public void click_LearningHome_Links(String learningHomeLinks)
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

		oASelFW.effecta("waitForElementPresent","//a[@title='"+learningHomeLinks+"']","60");
		oASelFW.effecta("click","//a[@title='"+learningHomeLinks+"']",learningHomeLinks);
		oASelFW.driver.switchTo().defaultContent();
	}

	/* @Author - Mangala 
	 * Description - This method will Clicks on Manage Learning Sub LINKS[Session Templates,Audience Types....]
	 * 
	 */
	public void click_ManageLearning_SubLinks(String manageLearningSubLinks)
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

		oASelFW.effecta("waitForElementPresent","//a[text()='"+manageLearningSubLinks+"']","60");
		oASelFW.effecta("click","//a[text()='"+manageLearningSubLinks+"']",manageLearningSubLinks);
		System.out.println("Clicked on "+manageLearningSubLinks);
		oASelFW.driver.switchTo().defaultContent();
	}
	/*@Author - Arifuddin Mohd
	 * Description - This method will help to select the Start date and Available date
	 * 
	 */
	public void startDateAndAvailableDateWithDeliveryType(String deliveryType)
	{
		try {
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

			Actions act = new Actions(oASelFW.driver);

			oASelFW.effecta("select","//label[text()='Delivery']/../../following-sibling::td//select",deliveryType,"Delivery Type");

			Thread.sleep(5000);
			oASelFW.effecta("clickAndWait","//label[contains(text(),'Available')]/../../following-sibling::td[1]//a//img&&//label[contains(text(),'Start')]/../../following-sibling::td[1]//a//img","Look Up","Pass");
			String window1[] = oASelFW.effectaArray("getAllWindowNames");
			oASelFW.effecta("selectWindow", window1[1]);
			Thread.sleep(1000);
			System.out.println("in select domain window");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMMM-d");
			Calendar cal = Calendar.getInstance();
			Date date1 = cal.getTime();
			cal.add(Calendar.DATE, 0);
			Date nextYear = cal.getTime();
			String enddatetime = sdf.format(nextYear);
			System.out.println(enddatetime);
			String[] date = enddatetime.split("-");
			String month = date[1];
			String year = date[0];
			String day = date[2];
			System.out.println("month"+date[1]);
			System.out.println("year"+date[0]);
			System.out.println("day"+date[2]);

			System.out.println("Day from calendar is*************"+day);
			System.out.println("Now the date is :=>  " + enddatetime);

			act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[contains(text(),'"+day+"')]"))).build().perform();
			act.click().build().perform();
			Thread.sleep(2000);
			oASelFW.effecta("selectWindow",window1[0]);
			oASelFW.driver.switchTo().defaultContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/* @Author - Mangala 
	 * Description - This method will clicknew session template
	 * 
	 */

	public void click_NewSession_Links()
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

		oASelFW.effecta("waitForElementPresent","//a[text()='New Session Template']","60");
		oASelFW.effecta("click","//a[text()='New Session Template']","New Session Template");
		System.out.println("Clicked on New Session Template");
		oASelFW.driver.switchTo().defaultContent();
	}





	/* @Author - Mangala 
	 * Description - This method will Create new session template
	 * 
	 */
	public void create_NewSessionTemplate_Details(String sessionName,String NoOfWeeks,String SessionType)
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

		oASelFW.effecta("waitForElementPresent","//span[text()='Name*']/following::td/input[@name='Name']","60");
		oASelFW.effecta("type","//span[text()='Name*']/following::td/input[@name='Name']",sessionName,"sessionName::"+sessionName+"");
		System.out.println("sessionName::"+sessionName);
		oASelFW.effecta("type","//span[text()='No. Of Weeks*']/following::td/input[@name='_displayNoOfWeeks_display']",NoOfWeeks,"NoOfWeeks::"+NoOfWeeks+"");
		oASelFW.effecta("click","//input[@value='week']","SessionType::"+SessionType);
		oASelFW.effecta("click","//a[@title='Next']","Next");

	}

	public void type_AddsessionValues(String sessionDay,String StartHour,String StartMinute,String endHour,String endMinute) throws InterruptedException
	{

		oASelFW.effecta("click","//a[@title='Add Session']","Add Session");
		String windows[]=oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",windows[1]);
		Thread.sleep(5000);
		System.out.println("In window");
		oASelFW.effecta("type","//input[@name='startTime_hour']",StartHour,"StartHour::"+StartHour+"");
		oASelFW.effecta("type","//input[@name='startTime_minute']",StartMinute,"StartMinute::"+StartMinute+"");
		oASelFW.effecta("type","//input[@name='endTime_hour']",endHour,"endHour::"+endHour+"");
		oASelFW.effecta("type","//input[@name='endTime_minute']",endMinute,"endMinute::"+endMinute+"");
		Select selectByVisibleText = new Select (oASelFW.driver.findElement(By.xpath("//select[@name='SessionDay']")));
		selectByVisibleText.selectByVisibleText(sessionDay);


		//click on save 
		oASelFW.effecta("click","//span[text()='Save']","save");
		oASelFW.effecta("click","//span[text()='Close']","Close");
		oASelFW.effecta("selectWindow",windows[0]);	

	}
	public void editSessionTemplate_Link(String weekDay, String sessionDay,String StartHour,String StartMinute,String endHour,String endMinute) throws InterruptedException
	{

		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		//Actions act = new Actions(oASelFW.driver);
		//act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='"+weekDay+"']/../../descendant::td[4]//a"))).build().perform();
		//act.click().build().perform();

		oASelFW.effecta("click","//span[text()='"+weekDay+"']/../../descendant::td[4]//a","Edit Session");
		oASelFW.driver.switchTo().defaultContent();
		Thread.sleep(4000);

		String windows[]=oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",windows[windows.length-1]);
		Thread.sleep(4000);

		Select selectByVisibleText = new Select (oASelFW.driver.findElement(By.xpath("//select[@name='SessionDay']")));
		selectByVisibleText.selectByVisibleText(sessionDay.trim());
		Thread.sleep(2000);

		System.out.println("In window");
		oASelFW.driver.findElement(By.xpath("//input[@name='startTime_hour']")).clear();
		System.out.println("Start hour"+StartHour);
		oASelFW.effecta("type","//input[@name='startTime_hour']",StartHour.trim(),"StartHour::"+StartHour+"");
		Thread.sleep(2000);
		oASelFW.driver.findElement(By.xpath("//input[@name='startTime_minute']")).clear();
		oASelFW.effecta("type","//input[@name='startTime_minute']", StartMinute.trim(), "StartMinute:: "+StartMinute+"");
		Thread.sleep(2000);
		oASelFW.driver.findElement(By.xpath("//input[@name='endTime_hour']")).clear();
		oASelFW.effecta("type","//input[@name='endTime_hour']", endHour.trim(),"endHour:: "+endHour+"");
		Thread.sleep(2000);
		oASelFW.driver.findElement(By.xpath("//input[@name='endTime_minute']")).clear();
		oASelFW.effecta("type","//input[@name='endTime_minute']",endMinute.trim(),"endMinute::"+endMinute+"");
		Thread.sleep(2000);

		//click on save 
		oASelFW.effecta("click","//span[text()='Save']","save");
		Thread.sleep(2000);
		oASelFW.effecta("click","//span[text()='Close']","Close");


	}

	/* @Author - Mangala 
	 * Description - This method will Save the created session
	 * 
	 * 
	 */

	public void click_SaveSession_Button() throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		oASelFW.effecta("waitForElementPresent","//span[text()='Save']","60");
		oASelFW.effecta("click","//span[text()='Save']","save");
		oASelFW.effecta("sendReport","verified the sessions created are saved ","Succesfully verified the sessions created are saved","Pass");
		oASelFW.driver.switchTo().defaultContent();

	}


	/* @Author - Mangala 
	 * Description - This method will add session in sessiontemplate to create a new session
	 */

	public void click_AddSession()
	{
		oASelFW.effecta("waitForElementPresent","//a[text()='Add Session']","60");
		oASelFW.effecta("click","//a[text()='Add Session']","Add Session");
		System.out.println("clicked on Add Session");
	}


	/* @Author - Mangala 
	 * Description - This method will enter session week ,session day,start time ,end time and clicks on save and close button
	 */

	public void type_SessionWeek_Day_Time(String sessionWeek,String sessionDay,String startHour,String startMinute,String endHour,String endMinute)
	{
		oASelFW.effecta("waitForElementPresent","//span[text()='Session Week*']/following::td/input[@name='_displaySessionWeek_display']","100");
		oASelFW.effecta("type","//span[text()='Session Week*']/following::td/input[@name='_displaySessionWeek_display']",sessionWeek,"sessionWeek: "+sessionWeek+"");
		oASelFW.effecta("waitForElementPresent","//select[@name='SessionDay']","60");
		Select selectByVisibleText = new Select (oASelFW.driver.findElement(By.xpath("//select[@name='SessionDay']")));
		selectByVisibleText.selectByVisibleText(sessionDay);
		oASelFW.effecta("waitForElementPresent","//input[@name='startTime_hour']","60");

		oASelFW.effecta("type","//input[@name='startTime_hour']",startHour,"StartHour::"+startHour+"");
		oASelFW.effecta("type","//input[@name='startTime_minute']",startMinute,"StartMinute::"+startMinute+"");
		oASelFW.effecta("type","//input[@name='endTime_hour']",endHour,"endHour::"+endHour+"");
		oASelFW.effecta("type","//input[@name='endTime_minute']",endMinute,"endMinute::"+endMinute+"");
		//click on save 
		oASelFW.effecta("click","//span[text()='Save']","save");
		//	oASelFW.effecta("click","//span[text()='Close']","Close");

	}


	/* @Author - Mangala 
	 * Description - This method will delete the session created
	 * 
	 */

	public void click_DeleteSession_link(String sessionNumber)
	{

		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		oASelFW.effecta("waitForElementPresent","//a[@title='Delete']/preceding::td/span/a[text()='"+sessionNumber+"']","60");
		oASelFW.effecta("click","//a[@title='Delete']/preceding::td/span/a[text()='"+sessionNumber+"']","Delete");

		oASelFW.driver.switchTo().defaultContent();

	}

	/* @Author - Mangala 
	 * Description - This method will edit  the session created
	 * 
	 */

	public void click_Sessions_sessionNumber(String sessionNumber)
	{
		oASelFW.effecta("waitForElementPresent","//a[@title='Session']","60");
		oASelFW.effecta("click","//a[text()='"+sessionNumber+"']","sessionNumber"+sessionNumber);
	}



	/* @Author - Mangala 
	 * Description - This method will enter the created sessionname and clicks on search
	 * 
	 */
	public void type_SessionTemplateName(String sessionTemplateName)
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

		oASelFW.effecta("waitForElementPresent","//label[text()='Name']/following::td/input[@name='session_query_var_name$kString$kLike']","60");
		oASelFW.effecta("type","//label[text()='Name']/following::td/input[@name='session_query_var_name$kString$kLike']",sessionTemplateName,"sessionTemplateName: "+sessionTemplateName+"");
		System.out.println("sessionTemplateName:"+sessionTemplateName);
		oASelFW.effecta("click","//a[@title='Search']/span[text()='Search']","Search button");
	}


	/* @Author - Mangala 
	 * Description - This method willclicks on session template name
	 * 
	 */

	public void click_SessionTemplateName_Link(String sessionTemplateName)
	{
		oASelFW.effecta("waitForElementPresent","//a[text()='"+sessionTemplateName+"']","60");
		oASelFW.effecta("click","//a[text()='"+sessionTemplateName+"']","sessionTemplateName:"+sessionTemplateName);
	}



	/* @Author - Mangala 
	 * Description - This method will Delete sessions from session template page 
	 * 
	 */

	public void delete_SessionsFromSessionTempletePage(String sessionTemplateName)
	{

		type_SessionTemplateName(sessionTemplateName);
		oASelFW.effecta("waitForElementPresent","//a[text()='"+sessionTemplateName+"']/following::td/a[text()='Delete']","60");
		oASelFW.effecta("click","//a[text()='"+sessionTemplateName+"']/following::td/a[text()='Delete']","Delete Link");
		handle_Alerts_PopUP();

		oASelFW.driver.switchTo().defaultContent();

	}


	//VERIFY THE SESSION IS DELETED

	public void verify_SessionDeleted(String sessionTemplateName)
	{

		type_SessionTemplateName(sessionTemplateName);
		oASelFW.effecta("waitForElementPresent","//span[text()='No items found']","60");
		String noItemsText=oASelFW.effecta("getText","//span[text()='No items found']","No items found");
		if(noItemsText.equals("No items found"))
		{
			oASelFW.effecta("sendReport","verified the session template is deleted","Veridied succesfully the session template is deleted and showing message"+noItemsText+"","Pass");

		}
		else
			oASelFW.effecta("sendReportWithOutExit","verified the session template is deleted","Veridied succesfully the session template is deleted and showing message"+noItemsText+"","Fail");

		oASelFW.driver.switchTo().defaultContent();

	}

	//ALert Handlinh

	public void handle_Alerts_PopUP()
	{
		try{
			Thread.sleep(2000);
			WebDriverWait wait = new WebDriverWait(oASelFW.driver,15);
			if(wait.until(ExpectedConditions.alertIsPresent())==null){
				System.out.println("alert was not present");
			}
			else{
				System.out.println("alert was present");
				Thread.sleep(2000);				
				oASelFW.driver.switchTo().alert().accept();
				Thread.sleep(1000);
			}
		}
		catch(Exception e){
			System.out.println("In catch alert was not present");
		}

	}



	//EDIT THE DOMAIN NAME IN SESSION TEMPLATE DETAILS PAGE

	public void edit_Domain_InSessionTemplate(String sessionTemplateName,String domainName) throws InterruptedException
	{

		type_SessionTemplateName(sessionTemplateName);
		click_SessionTemplateName_Link(sessionTemplateName);
		oASelFW.effecta("waitForElementPresent","//span[text()='Domain*']/../../following-sibling::td[1]/span/span/input","60");
		oASelFW.effecta("type","//span[text()='Domain*']/../../following-sibling::td[1]/span/span/input",domainName,"domainName: "+domainName+"");
		oASelFW.driver.findElement(By.xpath("//div[@class='sbMainContentArea']")).click();
		Thread.sleep(5000);
		System.out.println("clicked outside");
		oASelFW.driver.switchTo().defaultContent();


	}




	//CLICK ON NEW CLASS ROOM LINK

	public void click_NewClassRoom_Link()
	{

		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

		oASelFW.effecta("waitForElementPresent","//a[text()='New Classroom']","60");
		oASelFW.effecta("click","//a[text()='New Classroom']","New Classroom");
		System.out.println("Clicked on New Classroom");
		oASelFW.driver.switchTo().defaultContent();

	}

	//enter classroom data[name,domain,room id,capcity...]

	public void create_NewClassRoom_Details(String className,String roomId,String maxCapacity,String Location,String roomType,String facilityType, String Domain,String id) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

		oASelFW.effecta("waitForElementPresent","//span[text()='Name*']/following::td/input[@name='comp_name']","60");

		oASelFW.effecta("type","//span[text()='Name*']/following::td/input[@name='comp_name']",className,"className::"+className+"");
		System.out.println("sessionName::"+className);
		oASelFW.effecta("type","//span[text()='Room ID*']/following::td/input[@name='comp_room_no']",roomId,"roomId::"+roomId+"");
		Thread.sleep(3000);
		oASelFW.insertDataIntoAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData","update ClassRommData set CLASS_NAME='"+className+"' where ID="+id+"");
		Thread.sleep(3000);

		oASelFW.insertDataIntoAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData","update ClassRommData set ROOM_ID='"+roomId+"' where ID="+id+"");

		Thread.sleep(3000);

		oASelFW.effecta("type","//span[text()='Max. Capacity*']/following::td/input[@name='_displaycomp_capacity_display']",maxCapacity,"maxCapacity::"+maxCapacity+"");

		oASelFW.effecta("clickAndWait","//span[text()='Location*']/../following-sibling::td//a//img","Location Look Up","Pass");
		Thread.sleep(2000);
		String window[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window[1]);
		Thread.sleep(1000);
		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='Search']")))
		{
			System.out.println("in select Location window");
			oASelFW.effecta("type","//label[text()='Name']/../../following-sibling::td//input", Location,"Location");
			oASelFW.effecta("clickAndWait","//a[@title='Search']","Search","Pass");
			Thread.sleep(2000);
			oASelFW.effecta("clickAndWait","//span[text()='"+Location.trim()+"']/../../descendant::td[2]//a//img", Location,"Pass");
		}
		oASelFW.effecta("selectWindow",window[0]);

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		oASelFW.effecta("select","//span[text()='Room Type*']/../following-sibling::td//select",roomType,"Room Type::"+roomType+"");

		System.out.println("Selected room type");

		oASelFW.effecta("clickAndWait","//label[text()='Facility']/../../following-sibling::td//a//img","facility Look Up","Pass");
		Thread.sleep(2000);
		String window2[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window2[1]);
		Thread.sleep(1000);
		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='Search']")))
		{
			System.out.println("in select Facility window");
			oASelFW.effecta("type","//label[text()='Name']/../../following-sibling::td//input", facilityType,"Facility Type");
			oASelFW.effecta("clickAndWait","//a[@title='Search']","Search","Pass");
			Thread.sleep(3000);
			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='No items found']")))
			{
				oASelFW.effecta("sendReportWithOutExit","Entering the Location Name as 'Null' ","Successfully Set the Location Name as 'Null' ","Pass");
				oASelFW.driver.findElement(By.xpath("//span[text()='Close']")).click();
			}
			else{
				oASelFW.effecta("clickAndWait","//span[text()='"+facilityType.trim()+"']/../../descendant::td//a//img", facilityType,"Pass");
			}
		}
		oASelFW.effecta("selectWindow",window2[0]);

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		oASelFW.effecta("clickAndWait","//span[text()='Domain*']/../following-sibling::td//a//img","Domain Look Up","Pass");
		Thread.sleep(2000);
		String window1[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window1[1]);
		Thread.sleep(1000);
		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='Search']")))
		{
			System.out.println("in select domain window");
			oASelFW.effecta("type","//label[text()='Name']/../../following-sibling::td//input", Domain,"Domain");
			oASelFW.effecta("clickAndWait","//a[@title='Search']","Search","Pass");
			Thread.sleep(2000);
			oASelFW.effecta("clickAndWait","//span[text()='"+Domain.trim()+"']/../../descendant::td[2]//a//img", Domain,"Pass");

		}
		oASelFW.effecta("selectWindow",window1[0]);

		oASelFW.driver.switchTo().defaultContent();
	}


	//CLICK ON SAVE TO SAVE THE CLASSROOM DETAILS

	public void click_ClassRoom_Save_Button()
	{

		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		oASelFW.effecta("waitForElementPresent","//span[text()='Save']","60");
		oASelFW.effecta("click","//span[text()='Save']","save");

		oASelFW.driver.switchTo().defaultContent();

	}


	//CHECK DUPLICATE DATA SHOULD NOT ENTER

	public void verify_DuplicateRoomId_Textpresent()
	{
		String expectedMessage="A duplicate value for Room ID was used. ";
		oASelFW.effecta("waitForElementPresent","//span[text()='Save']","60");
		String errorMessage = oASelFW.effecta("getText","//span[contains(text(),'A duplicate value for Room ID was used. ')]","Duplicate value Room Id ");
		if(errorMessage.equals(expectedMessage))
		{
			oASelFW.effecta("sendReport","Verified duplicate value of room id text appeared:","Succesfully verified duplicate entry of room id is present :"+errorMessage+"","Pass");
		}

		else
		{
			oASelFW.effecta("sendReport","Verified duplicate value of room id text not appeared:","Succesfully verified duplicate entry of room id is not present :"+errorMessage+"","Fail");
		}

	}
	/*
	 * @author - Arifuddin Mohd
	 * Description - This method will help to navigate to the New Catalog Items Page by clicking mainlink and sub link in Top Learning Activities Page.
	 */
	public void topLearningActivitiesLinks(String mainLink, String subLink)
	{
		try {
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			System.out.println("in Top Learning Activities Page");
			Thread.sleep(8000);
			oASelFW.effecta("clickAndWait","//span[text()='"+mainLink.trim()+"']/../../following-sibling::div//descendant::span[text()='"+subLink.trim()+"']","Pass");

			oASelFW.driver.switchTo().defaultContent();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * @author - Arifuddin Mohd
	 * Description - This method will help to navigate to Next new Page by clicking link in New Catalog Items Page.
	 */
	public void newCatalogItemsPage(String mLink, String sLink)
	{
		try {
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			System.out.println("in New Catalog Item");
			Thread.sleep(3000);
			Actions act = new Actions(oASelFW.driver);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//div[contains(text(),'"+mLink+"')]/following-sibling::div//a[text()='"+sLink+"']"))).build().perform();
			act.click().build().perform();
			oASelFW.effecta("sendReport","Clicking on the "+sLink+" ","Successfully clicked on "+sLink+" ","Pass");
			oASelFW.driver.switchTo().defaultContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * @author - Arifuddin Mohd
	 * Description - This method will fill the course details with the following information title, courseid and domain
	 */

	public void CreateNewCourseDetails_Page(String title, String domain,String startDate,String id) throws ParseException
	{
		try {
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			Thread.sleep(2000);
			oASelFW.effecta("type","//span[text()='Title*']/../../following-sibling::td//input", title,"Title");
			Thread.sleep(3000);
			//oASelFW.insertDataIntoAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData","update ManageClassesData set Course_Title='"+title+"' where ID="+id+"");
			Thread.sleep(2000);

			oASelFW.effecta("clickAndWait","//span[text()='Domain*']/../../following-sibling::td//a//img","Look Up","Pass");
			Thread.sleep(3000);
			String window[] = oASelFW.effectaArray("getAllWindowNames");
			oASelFW.effecta("selectWindow",window[1]);
			Thread.sleep(2000);
			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='Search']")))
			{
				System.out.println("in select domain window");
				oASelFW.effecta("type","//label[text()='Name']/../../following-sibling::td//input", domain,"Domain Name");
				oASelFW.effecta("clickAndWait","//a[@title='Search']","Search","Pass");
				Thread.sleep(2000);
				oASelFW.effecta("clickAndWait","//span[text()='"+domain.trim()+"']/../../descendant::td[2]//a//img", domain,"Pass");
			}
			oASelFW.effecta("selectWindow",window[0]);

			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

			oASelFW.effecta("clickAndWait","//span[text()='Available From*']/../../following-sibling::td//a//img","Look Up","Pass");
			Thread.sleep(3000);
			String window1[] = oASelFW.effectaArray("getAllWindowNames");
			oASelFW.effecta("selectWindow", window1[1]);
			Thread.sleep(1000);
			System.out.println("in select domain window");

			/*Calendar currentDate = Calendar.getInstance(); //Get the current date
			SimpleDateFormat formatter= new SimpleDateFormat("yyyy/MMMM/dd"); //format it as per your requirement
			String dateNow = formatter.format(currentDate.getTime());
			String[] date = dateNow.split("/");
			String month = date[1];
			String year = date[0];
			String day = date[2];

			System.out.println(date[1]);
			System.out.println("Day from calendar is*************"+day);
			System.out.println("Now the date is :=>  " + dateNow);*/
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMMM-d");
			Calendar cal = Calendar.getInstance();
			Date date1 = cal.getTime();
			cal.add(Calendar.DATE, 0);
			Date nextYear = cal.getTime();
			String enddatetime = sdf.format(nextYear);
			System.out.println(enddatetime);
			String[] date = enddatetime.split("-");
			String month = date[1];
			String year = date[0];
			String day = date[2];
			System.out.println("month"+date[1]);
			System.out.println("year"+date[0]);
			System.out.println("day"+date[2]);

			System.out.println("Day from calendar is*************"+day);
			System.out.println("Now the date is :=>  " + enddatetime);

			Actions act = new Actions(oASelFW.driver);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[contains(text(),'"+day+"')]"))).build().perform();
			act.click().build().perform();
			//oASelFW.effecta("clickAndWait","//a[text()='"+startDate+"']", ""+startDate+"/"+month+"/"+year+"","Pass");
			Thread.sleep(2000);
			oASelFW.effecta("selectWindow",window1[0]);

			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

			SimpleDateFormat newDateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
			String da = oASelFW.driver.findElement(By.xpath("//span[text()='Available From*']/../../following-sibling::td//input")).getAttribute("value");
			System.out.println("Available date ::::::::::"+da);

			Date MyDate = newDateFormat.parse(da.trim());
			Thread.sleep(3000);
			newDateFormat.applyPattern("EEEE d MMMM yyyy");
			String W_day = newDateFormat.format(MyDate);
			String[] arr = W_day.split(" "); 
			System.out.println(arr[0]);
			Thread.sleep(3000);
			oASelFW.insertDataIntoAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData","update ManageClassesData set SessionStartDay='"+arr[0].trim()+"' where ID="+id+"");

			System.out.println("Day of a week "+W_day);
			oASelFW.driver.switchTo().defaultContent();
			System.out.println("updating the day"+W_day);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void EnterDiscontinuedDate() throws ParseException
	{
		try {
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			Thread.sleep(2000);

			oASelFW.effecta("clickAndWait","//label[text()='Discontinued From']/ancestor::td[1]/following-sibling::td[1]/descendant::img[1]","Look Up","Pass");
			Thread.sleep(3000);
			String window1[] = oASelFW.effectaArray("getAllWindowNames");
			oASelFW.effecta("selectWindow", window1[1]);
			Thread.sleep(1000);
			System.out.println("in select domain window");


			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMMM-d");
			Calendar cal = Calendar.getInstance();
			Date date1 = cal.getTime();
			cal.add(Calendar.DATE, 0);
			Date nextYear = cal.getTime();
			String enddatetime = sdf.format(nextYear);
			System.out.println(enddatetime);
			String[] date = enddatetime.split("-");
			String month = date[1];
			String year = date[0];
			String day = date[2];
			System.out.println("month"+date[1]);
			System.out.println("year"+date[0]);
			System.out.println("day"+date[2]);

			System.out.println("Day from calendar is*************"+day);
			System.out.println("Now the date is :=>  " + enddatetime);

			Actions act = new Actions(oASelFW.driver);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[contains(text(),'"+day+"')]"))).build().perform();
			act.click().build().perform();

			Thread.sleep(2000);
			oASelFW.effecta("selectWindow",window1[0]);



		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void CertificateTemplateSearch(String certName)
	{
		try{
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

			Thread.sleep(3000);
			String window[] = oASelFW.effectaArray("getAllWindowNames");
			oASelFW.effecta("selectWindow",window[1]);
			Thread.sleep(2000);
			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='Search']")))
			{
				System.out.println("in select domain window");
				oASelFW.effecta("type","//label[contains(text(),'Name')]/../../following-sibling::td//input&&//label[text()='Title']/../../following-sibling::td//input", certName,certName);
				oASelFW.effecta("clickAndWait","//a[@title='Search']","Search","Pass");
				Thread.sleep(2000);
				oASelFW.effecta("clickAndWait","//span[text()='"+certName.trim()+"']/../../descendant::td[2]//a//img", certName,"Pass");
			}
			oASelFW.effecta("selectWindow",window[0]);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	public void preRequisiteAndRecommendedSelection(ArrayList<String> certName, String preOrRec)
	{
		try{
			for(int i=0;i<certName.size(); i++)
			{
				Actions act= new Actions(oASelFW.driver);
				//WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
				//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

				Thread.sleep(3000);
				String window[] = oASelFW.effectaArray("getAllWindowNames");
				oASelFW.effecta("selectWindow",window[1]);
				Thread.sleep(2000);
				if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='Search']")))
				{
					System.out.println("in select domain window");
					oASelFW.effecta("type","//label[text()='Name']/../../following-sibling::td//input&&//label[text()='Title']/../../following-sibling::td//input", certName.get(i),certName.get(i));
					oASelFW.effecta("clickAndWait","//a[@title='Search']","Search","Pass");
					Thread.sleep(2000);
					if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='"+certName.get(i).trim()+"']")))
					{
						act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='"+certName.get(i).trim()+"']/../../descendant::td[2]//input"))).build().perform();
						act.click().build().perform();
						//oASelFW.effecta("clickAndWait","//span[text()='"+certName.get(i).trim()+"']/../../descendant::td[2]//a[contains(text(),'"+preOrRec+"')]//img", certName,"Pass");
						Thread.sleep(3000);
						act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[contains(text(),'Add These')]"))).build().perform();
						act.click().build().perform();
						Thread.sleep(3000);
						oASelFW.effecta("sendReportWithOutExit","Clicking on the Add These Equivalent Tab","Successfully clicked on the Add Equivalent Tab","Pass");
						act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[contains(text(),'Close')]"))).build().perform();
						act.click().build().perform();
						Thread.sleep(3000);
						act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Skip']"))).build().perform();
						act.click().build().perform();
						Thread.sleep(3000);

					}
					else
					{
						act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='"+certName.get(i).trim()+"']/../../descendant::td["+preOrRec+"]//a//img"))).build().perform();
						act.click().build().perform();
						//oASelFW.effecta("clickAndWait","//span[text()='"+certName.get(i).trim()+"']/../../descendant::td[2]//a[contains(text(),'"+preOrRec+"')]//img", certName,"Pass");
						Thread.sleep(3000);
					}
				}
				oASelFW.effecta("selectWindow",window[0]);
				//oASelFW.driver.switchTo().defaultContent();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	public void validatingNumberOfCertificateforSpecificCourse(String textTitle)
	{

		try {
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

			String text="";
			String cnt=oASelFW.effecta("getXpathCount","//span[text()='Title']/../../../tr");
			int count=Integer.parseInt(cnt);
			System.out.println("count is ***********"+count);	
			Thread.sleep(5000);
			for(int i=2; i<=count-2; i++){
				Thread.sleep(2000);
				text=oASelFW.driver.findElement(By.xpath("//span[text()='Name']/../../../tr["+i+"]/descendant::td[2]//span&&//span[text()='Title']/../../../tr["+i+"]/descendant::td[2]//span")).getText();
				Thread.sleep(2000);
				System.out.println(text+"*******");
				oASelFW.effecta("sendReport","Validating the Number of "+textTitle+" found ","Successfully validate "+textTitle+" in "+textTitle+" Page are "+text+"","Pass");
			}
			oASelFW.driver.switchTo().defaultContent();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	/*
	 * @author - Arifuddin Mohd
	 * Description- This method will validate the number of records in Related Info Page for Name Title.
	 */
	public void validatingNumberOfRecordsforSpecific_NameTitleInEquivalence(String Title, String textTitle)
	{

		try {
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='"+Title.trim()+"']/../../../../../../following-sibling::tr//descendant::tr/descendant::td[2]//span")))
			{
				String text="";
				String cnt=oASelFW.effecta("getXpathCount","//span[text()='Equivalents']/../../../../../../following-sibling::tr//descendant::tr");
				int count=Integer.parseInt(cnt);
				System.out.println("count is ***********"+count);	
				Thread.sleep(5000);
				for(int i=2; i<=count-2; i++){
					Thread.sleep(2000);
					text=oASelFW.driver.findElement(By.xpath("//span[text()='Equivalents']/../../../../../../following-sibling::tr//descendant::tr["+i+"]/descendant::td[2]//span")).getText();
					Thread.sleep(2000);
					System.out.println(text+"*******");
					oASelFW.effecta("sendReport","Validating the Number of "+textTitle+" found ","Successfully validate "+textTitle+" in "+textTitle+" Page are "+text+"","Pass");
				}
			}
			else
			{}
			oASelFW.driver.switchTo().defaultContent();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void deleteTheLinksFromMainPage(ArrayList<String> list)
	{

		try {
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

			String text="";
			String cnt=oASelFW.effecta("getXpathCount","//span[text()='Title']/../../../tr");
			int count=Integer.parseInt(cnt);
			System.out.println("count is ***********"+count);	
			Thread.sleep(5000);
			for(int i=2, j=0; i<=count-2; i++){
				Thread.sleep(2000);
				System.out.println("int i value "+i);
				System.out.println("int j value "+j);
				System.out.println("list value"+list.get(j).trim());

				//text=oASelFW.driver.findElement(By.xpath("//span[text()='Title']/../../../tr["+i+"]/descendant::td[2]//span[contains(text(),'"+list.get(j)+"')]")).getText();
				text = oASelFW.effecta("getText", "//span[text()='Title']/../../../tr["+i+"]/descendant::td[2]//span[contains(text(),'"+list.get(j).trim()+"')]&&//span[text()='Name']/../../../tr[2]/descendant::td[2]//span[contains(text(),'"+list.get(j).trim()+"')]", "Successfully validate "+list.get(j)+" in "+list.get(j)+" Page are "+text+"");
				Thread.sleep(3000);
				System.out.println(text+"*******");
				oASelFW.effecta("sendReport","Validating the Number of "+list.get(j)+" found ","Successfully validate "+list.get(j)+" in "+list.get(j)+" Page are "+text+"","Pass");
				if(text.trim().contains(list.get(j).trim()))
				{
					//oASelFW.driver.findElement(By.xpath("//span[contains(text(),'"+list.get(j).trim()+"')]/../following-sibling::td[6]//a")).click();
					oASelFW.effecta("clickAndWait","//span[contains(text(),'"+list.get(j).trim()+"')]/../following-sibling::td[2]//a&&//span[contains(text(),'"+list.get(j).trim()+"')]/../following-sibling::td[6]//a","Delete Link "+list.get(j).trim()+"","Pass");
					//oASelFW.effecta("sendReportWithOutExit","Clicking on the Delete link","Successfully clicked on the Delete for "+list.get(j).trim()+" Link","Pass");
					Thread.sleep(2000);
					String window[] = oASelFW.effectaArray("getAllWindowNames");
					oASelFW.effecta("selectWindow",window[window.length-1]);
					Thread.sleep(1000);
					if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='Save']")))
					{
						System.out.println("in select domain window");
						oASelFW.effecta("type","//span[text()='Reason*']/../following-sibling::td//textarea","test","Delete Mesg");
						oASelFW.effecta("clickAndWait","//span[text()='Save']","Save Button","Pass");
						Thread.sleep(4000);
					}
					else if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='OK']")))
					{
						oASelFW.effecta("clickAndWait","//span[text()='OK']","OK Button","Pass");
						Thread.sleep(4000);
					}
					oASelFW.effecta("selectWindow",window[0]);
					Thread.sleep(4000);
					handle_Alerts_PopUP();
					Thread.sleep(3000);
					j++;
				}
				else
				{
					break;
				}
			}
			oASelFW.driver.switchTo().defaultContent();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * @author - Arifuddin Mohd
	 * Description - This Method will click on Tab in Course Details page after creating the course Title.
	 */

	public void click_TabsHeadingInCourseDetailsPage(String value)
	{
		try {
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			oASelFW.effecta("clickAndWait","//td[@class='sbSectionTextBkg']//a[text()='"+value+"']&&//td[@class='sbSectionTextSelBkg']//span[text()='"+value+"']",value,"Pass");
			Thread.sleep(3000);
			oASelFW.driver.switchTo().defaultContent();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void addDeliveryTypes()
	{
		try {
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			oASelFW.effecta("clickAndWait","//span[text()='Delivery Types']/../following-sibling::td//a[text()='Add Delivery Type']","Add Delivery Type","Pass");
			oASelFW.driver.switchTo().defaultContent();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchUserInManagePeople(String People, String ManagePeople) throws InterruptedException
	{
		Switchframe();
		ClickOnElement("//a[text()='"+People+"']", "Click on People Admin");
		ClickOnElement("//a[text()='"+ManagePeople+"']", "Click On "+ ManagePeople);
		Thread.sleep(4000);
		switch_to_default_content();

	}

	public void CreateNewSessionTemplate(String date) throws InterruptedException
	{
		oASelFW.effecta("waitForElementPresent","//span[text()='Create New Session Template']","60");
		oASelFW.effecta("clickAndWait","//span[text()='Create New Session Template']","Create New Session Template radio button","Pass");
		String window[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window[1]);
		Thread.sleep(1000);
		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//a[text()='Add Session']")))
		{
			System.out.println("in add session template Window");
			oASelFW.effecta("clickAndWait","//a[text()='Add Session']","Add Session","Pass");
			String window2[] = oASelFW.effectaArray("getAllWindowNames");
			oASelFW.effecta("selectWindow",window2[2]);
			String window3[] = oASelFW.effectaArray("getAllWindowNames");
			oASelFW.effecta("selectWindow",window3[3]);
			oASelFW.effecta("clickAndWait","//a[text()='"+date+"']","Search","Pass");
			oASelFW.effecta("selectWindow",window3[2]);

			oASelFW.effecta("type","//input[@name='startTime_hour']","07","StartHour::"+07+"");
			oASelFW.effecta("type","//input[@name='startTime_minute']","15","StartHour::"+15+"");

			oASelFW.effecta("type","//input[@name='endTime_hour']","09","StartHour:: 09");
			oASelFW.effecta("type","//input[@name='endTime_minute']","00","StartHour:: 00");
			oASelFW.effecta("clickAndWait","//a[text()='Save']","Save","Pass");
			oASelFW.effecta("clickAndWait","//a[text()='Close']","Close","Pass");
			oASelFW.effecta("selectWindow", window3[1]);
			oASelFW.effecta("clickAndWait","//span[text()='Finish']","Finish button","Pass");
		}
		oASelFW.effecta("selectWindow",window[0]);

	}


	/*
	 * @author - Arifuddin Mohd
	 * Description - This method will Validate the Folder Structure in Content Repository.
	 */
	public void manageContentFolderStructure(ArrayList<String> value) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

		Thread.sleep(2000);
		String text="";
		String cnt=oASelFW.effecta("getXpathCount","//span[text()='Item']/../../../../tbody/tr");
		int count=Integer.parseInt(cnt);
		System.out.println("count is ***********"+count);	
		Thread.sleep(5000);
		for(int i=4,j=0; i<=count-1 && j<value.size(); i=i+2){
			Thread.sleep(2000);
			text=oASelFW.driver.findElement(By.xpath("//span[text()='Item']/../../../../tbody/tr["+i+"]//a")).getText();
			Thread.sleep(2000);
			System.out.println(text+"*******"+value.get(j));
			if(text.trim().equals(value.get(j))){
				oASelFW.effecta("sendReportWithOutExit","Validating the Actual Name "+value.get(j)+" with the Expected "+text+"","Successfully validate the Actual Name "+value.get(j)+" with the Expected "+text+"","Pass");
				Thread.sleep(2000);
				j++;	
			}

			else{
				oASelFW.effecta("sendReportWithOutExit","Validating the Actual Name "+value.get(j)+" with the Expected "+text+"","No "+text+" link found in Access DB","Pass");
			}
		}
		oASelFW.driver.switchTo().defaultContent();

	}

	/*
	 * @author - Arifuddin Mohd
	 * Description - This method will Validate the Folders "contents" Details in Productio Repository page.
	 */
	public void ValidateProductionRepositoryContentDetails(String value, int val) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

		Thread.sleep(5000);
		String text="";
		String cnt = oASelFW.effecta("getXpathCount","//span[text()='Item']/../../../../tbody/tr");
		int count=Integer.parseInt(cnt);
		System.out.println("count is ***********"+count);	
		Thread.sleep(5000);
		for(int i=4; i<=count-1; i=i+2){
			Thread.sleep(2000);
			text=oASelFW.driver.findElement(By.xpath("//span[text()='Item']/../../../../tbody/tr["+i+"]//a")).getText();
			Thread.sleep(2000);
			System.out.println(text+"*******"+value);
			if(text.trim().equals(value)){
				oASelFW.effecta("sendReport","Validating the Actual Name "+value+" with the Expected "+text+"","Successfully validate the Actual Name "+value+" with the Expected "+text+"","Pass");
				oASelFW.effecta("clickAndWait","//span[text()='Item']/../../../../tbody/tr["+i+"]//a[text()='"+text.trim()+"']",text,"Pass");
				i=2;
				Thread.sleep(2000);
				if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='Name']/../../following-sibling::tr["+val+"]//descendant::a")))
				{
					String vale = oASelFW.effecta("getText","//span[text()='Name']/../../following-sibling::tr["+val+"]//descendant::a","Content Names");
					oASelFW.effecta("sendReport","Validating the Content details table ","Successfully validated the content details Names "+vale,"Pass");
					oASelFW.effecta("clickAndWait","//span[text()='Name']/../../following-sibling::tr["+val+"]//descendant::a","Content Name"+vale,"Pass");
				}
				else{
					oASelFW.effecta("sendReportWithOutExit","Validating the Content details page ","No items found for "+text+" ","Pass");
				}
				Thread.sleep(2000);
				break;
			}

			else{
				System.out.println("unable to click on the link   "+text);
			}
		}

		oASelFW.driver.switchTo().defaultContent();

	}
	/*
	 * @author - Arifuddin Mohd
	 * Description - This method will Validate the Folders "contents" Details in Productio Repository page.
	 */
	public void validateContentInformationDetails(ArrayList<String> value) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

		Thread.sleep(5000);
		String text="";
		String cnt=oASelFW.effecta("getXpathCount","//span[text()='Item']/../../../../tbody/tr");
		int count=Integer.parseInt(cnt);
		System.out.println("count is ***********"+count);	
		Thread.sleep(5000);
		for(int i=4,j=0; i<=count-1 && j<=value.size(); i=i+2){
			Thread.sleep(2000);
			text=oASelFW.driver.findElement(By.xpath("//span[text()='Item']/../../../../tbody/tr["+i+"]//a")).getText();
			Thread.sleep(2000);
			System.out.println(text+"*******"+value);
			if(text.trim().equals(value.get(j))){
				oASelFW.effecta("sendReport","Validating the Actual Name "+value+" with the Expected "+text+"","Successfully validate the Actual Name "+value+" with the Expected "+text+"","Pass");
				oASelFW.effecta("clickAndWait","//span[text()='Item']/../../../../tbody/tr["+i+"]//a[text()='"+text.trim()+"']",text,"Pass");
				j++;
			}
			else{
				System.out.println("unable to click on the link   "+text);
			}
		}
		oASelFW.driver.switchTo().defaultContent();
	}

	/*
	 * @author - Arifuddin Mohd
	 * Description - This method will Validate the Data in Different Content Page For Different URLs in Another Window.
	 */
	public void navigatingToDifferentContentPageForURLs(ArrayList<String> value, int size) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

		Thread.sleep(2000);
		String text="";
		String cnt=oASelFW.effecta("getXpathCount","//span[text()='Item']/../../../../tbody/tr");
		int count=Integer.parseInt(cnt);
		System.out.println("count is ***********"+count);	
		Thread.sleep(5000);
		for(int i=4,j=0; i<=count-1 && j<size; i=i+2){
			Thread.sleep(2000);
			text=oASelFW.driver.findElement(By.xpath("//span[text()='Item']/../../../../tbody/tr["+i+"]//a")).getText();
			Thread.sleep(2000);

			System.out.println(text+"*******"+value.get(j));
			if(text.trim().equals(value.get(j))){
				oASelFW.effecta("sendReport","Validating the Actual Name "+value.get(j)+" with the Expected "+text+"","Successfully validate the Actual Name "+value.get(j)+" with the Expected "+text+"","Pass");
				oASelFW.effecta("clickAndWait","//span[text()='Item']/../../../../tbody/tr["+i+"]//a[text()='"+text.trim()+"']",text,"Pass");
				Thread.sleep(2000);
				j++;
				i=2;
				Thread.sleep(2000);
				if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='Name']/../../following-sibling::tr//descendant::a")))
				{
					String vale = oASelFW.effecta("getText","//span[text()='Name']/../../following-sibling::tr//descendant::a","Content Names");
					oASelFW.effecta("sendReport","Validating the Content details table ","Successfully validated the content details Names "+vale,"Pass");
					oASelFW.effecta("clickAndWait","//span[text()='Name']/../../following-sibling::tr//descendant::a","Content Name"+vale,"Pass");
					navigateToNewUrl();
					wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
				}
				else{
					oASelFW.effecta("sendReportWithOutExit","Validating the Content details page ","Successfully Landed on Content details Page But 'No items found' for "+text+" ","Pass");
				}
				Thread.sleep(2000);
			}

			else{
				oASelFW.effecta("sendReportWithOutExit","clicking on the displayed link","Unable to click on the link"+text,"Pass");
			}

		}
		oASelFW.driver.switchTo().defaultContent();

	}

	/**
	 * This method will select the URL link from the Content property page
	 * @throws InterruptedException
	 */
	public void navigateToNewUrl() throws InterruptedException
	{
		String siteId[]=new String[2];
		int i=0;
		String impersonate_url = "//label[text()='URL']/../../following-sibling::td//input";
		Thread.sleep(2000);
		boolean status = Boolean.parseBoolean(oASelFW.effecta("isElementPresent",impersonate_url));
		System.out.println("status is "+status);
		String get_imp_link = "";
		if(status==true)
		{
			String Impersonatelink = oASelFW.driver.findElement(By.xpath(impersonate_url)).getAttribute("value");
			get_imp_link    = Impersonatelink;
			System.out.println("---"+get_imp_link);

			Actions act = new Actions(oASelFW.driver);
			act.sendKeys(Keys.chord(Keys.CONTROL, "t")).build().perform();
			Thread.sleep(5000);
			Set<String> window=oASelFW.driver.getWindowHandles();
			for(String aSiteId: window) {
				siteId[i] = aSiteId;
				i++;
			}
			Thread.sleep(2000);
			oASelFW.driver.switchTo().window(siteId[1]);
			oASelFW.driver.navigate().to(get_imp_link);

			Thread.sleep(27000);
			act.sendKeys(Keys.chord(Keys.CONTROL, "w")).build().perform();
			oASelFW.driver.switchTo().window(siteId[0]);
			oASelFW.driver.navigate().back();
			Thread.sleep(3000);
			oASelFW.effecta("sendReportWithOutExit","Navigate to the URL "+get_imp_link+" ", "Successfully Navigated to the URL "+get_imp_link+" ", "Pass");
			Thread.sleep(4000);
			oASelFW.effecta("sendReportWithOutExit","Closing the Flash player ","Successfully Closed the Flash Played", "Pass");
		}
		else
		{
			oASelFW.effecta("sendReportWithOutExit","Navigate to the Displayed Page ", "URL Label is "+get_imp_link+" not Displayed in Navigation Page ", "Pass");
			oASelFW.driver.navigate().back();
			Thread.sleep(4000);
		}
	}
	/*
	 * @Author - Arifuddin Mohd
	 * Description - This method will Validate the information from Excel to DB
	 */
	public void validate_ContentDetailsPageInformationWith_DB(String Name,String SecurityDomain,String PlayerTemplate,String ParentFolder,String ContentFolder)
	{

		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='Name*']")))
		{
			String contentName=oASelFW.driver.findElement(By.xpath("//span[text()='Name*']/../following-sibling::td/input")).getAttribute("value");
			if(contentName.trim().equals(Name.trim()))
			{
				oASelFW.effecta("sendReport","Verified Content Name value "+contentName+"","Succesfully verified Content value "+Name+"","Pass");
			}else{
				oASelFW.effecta("sendReportWithOutExit","Verified Content Name value "+contentName+"","Unable to verify the content Name Field "+Name+" ","Fail");
			}
		}
		else
		{
			System.out.println("Unable to find the name field");
		}

		String secDom=oASelFW.driver.findElement(By.xpath("//span[text()='Security Domain*']/../following-sibling::td/span/input")).getAttribute("value");

		if(secDom.trim().equals(SecurityDomain.trim()))
		{
			oASelFW.effecta("sendReport","Verified Security Domain Name value "+secDom+"","Succesfully verified Security Domain as "+SecurityDomain+"","Pass");
		}else{
			oASelFW.effecta("sendReportWithOutExit","Verified  Security Domain Name value "+secDom+"","Unable to verify the  Security Domain Field "+SecurityDomain+" ","Fail");
		}

		String playTemp=oASelFW.driver.findElement(By.xpath("//span[text()='Player Template*']/../following-sibling::td/span/input")).getAttribute("value");
		if(playTemp.trim().equals(PlayerTemplate.trim()))
		{
			oASelFW.effecta("sendReport","Verified Player Template value "+playTemp+"","Succesfully verified Player Template value "+PlayerTemplate+"","Pass");
		}else{
			oASelFW.effecta("sendReportWithOutExit","Verified Player Template value "+playTemp+"","Unable to verify the Player Template Field "+PlayerTemplate+" ","Fail");
		}

		String parFolder=oASelFW.driver.findElement(By.xpath("//span[text()='Parent Folder*']/../following-sibling::td/span/input")).getAttribute("value");
		if(parFolder.trim().equals(ParentFolder.trim()))
		{
			oASelFW.effecta("sendReport","Verified Parent Folder value "+parFolder+"","Succesfully verified Parent folder value "+ParentFolder+"","Pass");
		}else{
			oASelFW.effecta("sendReportWithOutExit","Verified  Parent Folder value "+parFolder+"","Unable to verify the  Parent Folder Field "+ParentFolder+" ","Fail");
		}

		boolean previewContent= Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//a[text()='Preview Content']"));
		if(previewContent==true)
		{
			oASelFW.effecta("sendReport","Verifiying  Preview Content value Field ","Succesfully Preview Content Field Validated","Pass");
		}else{
			oASelFW.effecta("sendReportWithOutExit","Verifiying  Preview Content value Field ","No Content Preview Content Field Found","Fail");
		}

		boolean viewResults = Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//a[text()='View Results']"));
		if(previewContent==true)
		{
			oASelFW.effecta("sendReport","Verifiying  View Results value Field ","Succesfully View Results Field Validated","Pass");
		}else{
			oASelFW.effecta("sendReportWithOutExit","Verifiying  View Results value Field ","Unable Verifiying  View Results value Field  ","Fail");
		}

		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='Content Server*']")))
		{
			String conFolder=oASelFW.driver.findElement(By.xpath("//span[text()='Content Server*']/../following-sibling::td/span/input")).getAttribute("value");
			if(conFolder.trim().equals(ContentFolder.trim()))
			{
				oASelFW.effecta("sendReport","Verified Contact Folder value "+conFolder+"","Succesfully Contact Folder Field "+ContentFolder+"","Pass");
			}else{
				oASelFW.effecta("sendReportWithOutExit","Verified Contact Folder value "+conFolder+"","Unable to verify the Contact Folder Field "+ContentFolder+" ","Fail");
			}
		}
		else{
			oASelFW.effecta("sendReportWithOutExit","Validate  Content Server value Field ","No Content Server Field Found ","Pass");
		}


		oASelFW.driver.switchTo().defaultContent();
	}
	/*
	 * @Author - Arifuddin Mohd
	 * Description -  This method will enter the Curriculam Details in Curriculam Page.
	 */

	public String curriculamDetailsInformationPage(String CurrName, String id, String Domain, String startDate, String Status)
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		try
		{

			oASelFW.effecta("type","//span[text()='Name*']/../following-sibling::td//input",CurrName,"Curricula Name "+CurrName+"");
			Thread.sleep(3000);
			oASelFW.insertDataIntoAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData","update EndToEnd set Course_Title='"+CurrName+"' where ID="+id+"");


			Thread.sleep(3000);

			oASelFW.effecta("clickAndWait","//span[text()='Domain*']/../following-sibling::td//a//img","Look Up","Pass");
			String window[] = oASelFW.effectaArray("getAllWindowNames");
			oASelFW.effecta("selectWindow",window[1]);
			Thread.sleep(1000);
			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='Search']")))
			{
				System.out.println("in select domain window");
				oASelFW.effecta("type","//label[text()='Name']/../../following-sibling::td//input", Domain,"Domain");
				oASelFW.effecta("clickAndWait","//a[@title='Search']","Search","Pass");
				Thread.sleep(2000);
				oASelFW.effecta("clickAndWait","//span[text()='"+Domain.trim()+"']/../../descendant::td[2]//a//img", Domain,"Pass");
			}
			oASelFW.effecta("selectWindow",window[0]);

			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

			oASelFW.effecta("clickAndWait","//span[text()='Available From*']/../../following-sibling::td//a//img&&//span[text()='Available From*']/../following-sibling::td//a//img","Look Up","Pass");
			String window1[] = oASelFW.effectaArray("getAllWindowNames");
			oASelFW.effecta("selectWindow", window1[1]);
			Thread.sleep(1000);
			System.out.println("in select domain window");

			Calendar currentDate = Calendar.getInstance(); //Get the current date
			SimpleDateFormat formatter= new SimpleDateFormat("yyyy/MMMM/dd"); //format it as per your requirement
			String dateNow = formatter.format(currentDate.getTime());
			String[] date = dateNow.split("/");
			String month = date[1];
			String year = date[0];
			String day = date[2];

			System.out.println(date[1]);
			System.out.println("Day from calendar is*************"+day);
			System.out.println("Now the date is :=>  " + dateNow);

			Actions act = new Actions(oASelFW.driver);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[contains(text(),'"+startDate.trim()+"')]"))).build().perform();
			act.click().build().perform();
			//oASelFW.effecta("clickAndWait","//a[text()='"+startDate.trim()+"']", ""+startDate+"/"+month+"/"+year+"","Pass");
			Thread.sleep(2000);
			oASelFW.effecta("selectWindow",window1[0]);

			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			if(oASelFW.driver.findElement(By.xpath("//span[contains(text(),'Status*')]/../following-sibling::td//select//option[text()='"+Status.trim()+"']")).isSelected())
			{
				oASelFW.effecta("sendReport","Selecting the Availability Status ","Successfully selected the Availability status as "+Status,"Pass");
			}else
			{
				oASelFW.effecta("select","//span[contains(text(),'Status*')]/../following-sibling::td//select",Status,"Status Type::"+Status+"");
			}
			oASelFW.driver.switchTo().defaultContent();
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return CurrName;
	}
	public void addPathInCurriculamPage() throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		Thread.sleep(2000);
		Actions act = new Actions(oASelFW.driver);
		act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[@class='sbWDKButton']//span"))).build().perform();
		act.click().build().perform();
		oASelFW.effecta("sendReport","Clicking on the Add Path","Successfully click Add Path","Pass");
		oASelFW.effecta("sendReport","Navigating to the New Curriculum Page","Successfully Navigated to the New Curriculum Page","Pass");
		oASelFW.driver.switchTo().defaultContent();

	}

	public void defineCompletePathWithNewModule(String module, String element, String moduleButton, ArrayList<String> title)
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		Actions act = new Actions(oASelFW.driver);

		try {

			int random = (int) ((Math.random()) * 10000000);;
			String CurrName = "test"+random;
			oASelFW.effecta("type","//span[text()='Name*']/../following-sibling::td//input",CurrName,"Path Name "+CurrName+"");
			for(int i=0; i<module.length(); i++)
			{
				oASelFW.effecta("clickAndWait","//a[text()='New Module']","New Module","Pass");

				String window[] = oASelFW.effectaArray("getAllWindowNames");
				oASelFW.effecta("selectWindow",window[1]);
				Thread.sleep(1000);
				if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='Save']")))
				{
					System.out.println("in select domain window");
					oASelFW.effecta("type","//span[text()='Name*']/../following-sibling::td//input", CurrName,"Module Name");
					Thread.sleep(2000);
				}

				oASelFW.effecta("type","//span[contains(text(),'Elements')]/../following-sibling::td//input[2]", element,"Number of Elements "+element);

				String val=oASelFW.driver.findElement(By.xpath("//span[contains(text(),'Required*')]/../following-sibling::td/descendant::input[@value='false']")).getAttribute("value");
				boolean value = Boolean.parseBoolean(val);
				System.out.println("boolean value is*********"+value);
				if(value==true)
				{
					oASelFW.effecta("sendReport","Selecting the Module Required Button ","Successfully selected Module Required as "+moduleButton+" ","Pass");
				}
				else
				{
					Thread.sleep(2000);
					act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[contains(text(),'Required*')]/../following-sibling::td/descendant::input[@title='"+moduleButton.trim()+"']"))).build().perform();
					act.click().build().perform();
					oASelFW.effecta("sendReport","Selecting the Module Required Button","Successfully selected Module Required as "+moduleButton+" ","Pass");
				}
				Thread.sleep(5000);

				for(int j=0; j<=element.length(); j++)
				{
					oASelFW.effecta("clickAndWait","//a[text()='Add Learning Element']","Add Learning Element","Pass");
					Thread.sleep(3000);
					String[] window1 = oASelFW.effectaArray("getAllWindowNames");
					oASelFW.effecta("selectWindow",window1[window1.length-1]);
					Thread.sleep(5000);
					System.out.println("in select domain window");
					if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='Search']")))
					{
						Thread.sleep(3000);
						//oASelFW.driver.findElement(By.xpath("//label[text()='Title']/../../following-sibling::td//input")).sendKeys(title[j]);
						oASelFW.effecta("type","//label[text()='Title']/../../following-sibling::td//input", title.get(j),"Title Name");
						Thread.sleep(2000);
						oASelFW.effecta("clickAndWait","//a[@title='Search']//span","Search","Pass");
						Thread.sleep(3000);
						oASelFW.effecta("clickAndWait","//span[text()='"+title.get(j).trim()+"']/../../descendant::td[2]//input", "Selecting "+title+" ","Pass");
						act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[@title='Save']"))).build().perform();
						act.click().build().perform();
						oASelFW.effecta("sendReport","Clicking on the Save Button","Successfully Clicked on the first Save Button","Pass");

					}

					oASelFW.effecta("selectWindow",window1[window1.length-2]);
				}

				Thread.sleep(2000);
				act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[@title='Save']"))).build().perform();
				act.click().build().perform();
				System.out.println("New Module Save Button");
				oASelFW.effecta("sendReport","Clicking on the Save Button","Successfully Clicked on the second Save Button","Pass");
				Thread.sleep(2000);

				//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
				String[] window2 = oASelFW.effectaArray("getAllWindowNames");
				oASelFW.effecta("selectWindow",window2[window2.length-1]);
				Thread.sleep(2000);
			}
			/*act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[@title='Save']"))).build().perform();
			act.click().build().perform();
			System.out.println("Clicking on default window Save");
			oASelFW.effecta("sendReport","Clicking on the Save Button","Successfully Clicked on the third Save Button","Pass");
			Thread.sleep(5000);
			Thread.sleep(2000);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[@title='Save']"))).build().perform();
			act.click().build().perform();
			System.out.println("Clicking on default window Save");
			oASelFW.effecta("sendReport","Clicking on the Save Button","Successfully Clicked on the Save Button","Pass");
			Thread.sleep(5000);*/

			String[] window3 = oASelFW.effectaArray("getAllWindowNames");
			oASelFW.effecta("selectWindow",window3[window3.length-1]);
			{
				String errorMessage = "A duplicate value";
				String learnPage = "Recalculate Learner";
				if(errorMessage.contains("Duplicate Values"))
				{
					oASelFW.effecta("sendReport","Verified A duplicate value text appeared:","Succesfully verified A duplicate value is present :"+errorMessage+"","Pass");
					oASelFW.driver.navigate().back();
				}

				else if(learnPage.contains("Recalculate Learner's"))
				{

					act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Skip']"))).build().perform();
					act.click().build().perform();
					oASelFW.effecta("selectWindow",window3[window3.length-2]);
					act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Finish']"))).build().perform();
					act.click().build().perform();
					oASelFW.effecta("sendReport","Clicking on the Finish Button","Successfully Clicked on the Finish Button","Pass");

				}
				else
				{
					oASelFW.effecta("sendReportWithOutExit","Verified A duplicate value ","Succesfully verified No duplicate value Found","Pass");
				}
			}
			oASelFW.effecta("selectWindow",window3[window3.length-1]);

		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	public void saveAndFinishModuleButton() throws InterruptedException
	{

		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		Thread.sleep(2000);
		Actions act = new Actions(oASelFW.driver);
		act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[@title='Save']"))).build().perform();
		act.click().build().perform();
		oASelFW.effecta("sendReport","Clicking on the Save Button","Successfully Clicked on the Second Save Button","Pass");

		Thread.sleep(5000);
		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//a[contains(text(),'Duplicate Value')]")))
		{
			oASelFW.effecta("sendReportWithOutExit","Verified A duplicate value text appeared:","Succesfully verified A duplicate value is present ","Pass");
			oASelFW.driver.navigate().back();

		}
		else{
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[@title='Save']"))).build().perform();
			act.click().build().perform();
			Thread.sleep(5000);
			oASelFW.effecta("sendReportWithOutExit","Clicking on the Save Button","Successfully Clicked on the third Save Button","Pass");
			Thread.sleep(5000);
		}
		String[] window3 = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window3[window3.length-1]);
		//{

		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//td[contains(text(),'Recalculate')]")))
		{
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Skip']"))).build().perform();
			act.click().build().perform();
			oASelFW.effecta("sendReportWithOutExit","Clicking on the Skip Button","Successfully Clicked on the Skip Button","Pass");
			oASelFW.effecta("selectWindow",window3[window3.length-2]);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Finish']"))).build().perform();
			act.click().build().perform();
			oASelFW.effecta("sendReportWithOutExit","Clicking on the Finish Button","Successfully Clicked on the Finish Button","Pass");
			oASelFW.driver.switchTo().defaultContent();
		}

		else if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//a[contains(text(),'Duplicate Value')]")))
		{

			oASelFW.effecta("sendReportWithOutExit","Verified A duplicate value text appeared:","Succesfully verified A duplicate value is present ","Pass");
			oASelFW.driver.navigate().back();
			oASelFW.effecta("selectWindow",window3[window3.length-2]);

		}
		else
		{
			System.out.println("in recalculate else block");
		}
		Thread.sleep(4000);

	}
	public void newCertficateTemplate(String name, String available, String Domain) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

		oASelFW.effecta("type","//span[text()='Name*']/../../following-sibling::td//input", name,"Name ");
		oASelFW.effecta("clickAndWait","//span[text()='Available From*']/../../following-sibling::td//a//img","Look Up","Pass");
		String window1[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow", window1[1]);
		Thread.sleep(1000);
		System.out.println("in select domain window");

		Calendar currentDate = Calendar.getInstance(); //Get the current date
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy/MMMM/dd"); //format it as per your requirement
		String dateNow = formatter.format(currentDate.getTime());
		String[] date = dateNow.split("/");
		String month = date[1];
		String year = date[0];
		String day = date[2];

		System.out.println(date[1]);
		System.out.println("Day from calendar is*************"+day);
		System.out.println("Now the date is :=>  " + dateNow);

		oASelFW.effecta("clickAndWait","//a[text()='"+available+"']", ""+available+"/"+month+"/"+year+"","Pass");
		Thread.sleep(2000);
		oASelFW.effecta("selectWindow",window1[0]);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		oASelFW.effecta("clickAndWait","//span[text()='Domain*']/../following-sibling::td//a//img&&//span[text()='Domain*']/../../following-sibling::td//a//img","Look Up","Pass");
		String window2[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window2[window2.length-1]);
		Thread.sleep(1000);
		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='Search']")))
		{
			System.out.println("in select domain window");
			oASelFW.effecta("type","//label[text()='Name']/../../following-sibling::td//input", Domain,"Domain");
			oASelFW.effecta("clickAndWait","//a[@title='Search']","Search","Pass");
			Thread.sleep(2000);
			oASelFW.effecta("clickAndWait","//span[text()='"+Domain.trim()+"']/../../descendant::td[2]//a//img", Domain,"Pass");
			Thread.sleep(3000);
		}
		oASelFW.effecta("selectWindow",window2[window2.length-2]);
		Thread.sleep(5000);

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

		oASelFW.effecta("clickAndWait","//span[text()='Save']","Certification Save Button","Pass");
		Thread.sleep(3000);
		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//a[contains(text(),'duplicate value')")))
		{
			oASelFW.effecta("sendReportWithOutExit","Duplicate value found","Successfully Validated Duplicate Value","Pass");
			oASelFW.driver.navigate().back();
			Thread.sleep(3000);
		}
		oASelFW.driver.switchTo().defaultContent();

	}
	public String deepLinkUrlLaunchingForDiffStatus()
	{
		String deeplink ="";
		String siteId[]=new String[2];
		int i=0;
		try {
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			String link = oASelFW.effecta("getText","//td[contains(text(),'Deeplink URL')]/../descendant::td[3]//deeplinkurl2","DeepLink URL");

			deeplink = link;
			System.out.println("---"+deeplink);

			Actions act = new Actions(oASelFW.driver);
			act.sendKeys(Keys.chord(Keys.CONTROL, "t")).build().perform();
			Thread.sleep(5000);
			Set<String> window=oASelFW.driver.getWindowHandles();
			for(String aSiteId: window) {
				siteId[i] = aSiteId;
				i++;
			}
			Thread.sleep(2000);
			oASelFW.driver.switchTo().window(siteId[1]);
			oASelFW.driver.navigate().to(deeplink);

			Thread.sleep(27000);
			act.sendKeys(Keys.chord(Keys.CONTROL, "w")).build().perform();
			oASelFW.driver.switchTo().window(siteId[0]);
			oASelFW.driver.navigate().back();
			Thread.sleep(3000);
			oASelFW.effecta("sendReportWithOutExit","Navigate to the URL "+deeplink+" ", "Successfully Navigated to the URL "+deeplink+" ", "Pass");
			Thread.sleep(4000);
			oASelFW.effecta("sendReportWithOutExit","Closing the Launch Window","Successfully Closed the Launch Window", "Pass");

			oASelFW.driver.switchTo().defaultContent();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return deeplink;
	}
	/*
	 * @Author - Arifuddin Mohd
	 * Description - This method will click on the search Results from System roles Display Page.
	 */
	public Boolean searchSecurity_JobName(ArrayList<String> jobName, int i)
	{
		boolean success = false;
		try {
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			System.out.println("Entering the text  "+jobName.get(i));

			oASelFW.effecta("type","//label[text()='Role Name']/../..//following-sibling::td//input",jobName.get(i), "JobName");
			Thread.sleep(2000);
			oASelFW.effecta("clickAndWait","//span[text()='Search']","Search");
			System.out.println("no items found "+jobName.get(i));
			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='No items found']")))
			{
				oASelFW.effecta("sendReportWithOutExit","Enter the Valid Name ","No items Found for the given job name:  "+jobName.get(i),"Pass");
			}
			else{
				success = true;
			}
			oASelFW.driver.switchTo().defaultContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Boolean status "+success);

		switch_to_default_content();
		return success;

	}
	/*
	 * @Author - Arifuddin Mohd
	 * Description - This method will select the System Search Links from Main content Page.
	 */
	public void clickOnTheSecuritySearchActions(ArrayList<String> action, int i) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		Thread.sleep(5000);
		Actions act = new Actions(oASelFW.driver);
		act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[text()='"+action.get(i).trim()+"']"))).build().perform();
		act.click().build().perform();
		oASelFW.effecta("sendReport","Clicking on the Actions Link "+action,"Successfully clicked on Action "+action.get(i),"Pass");
		System.out.println("Clicked on ******************************>>"+action.get(i));
		Thread.sleep(2000);
		switch_to_default_content();
	}

	public void simpleSecurityRoleDetailsPage(String tab, ArrayList<String> jobname, int i)
	{
		try {
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			Thread.sleep(4000);
			//validate grant access 
			Thread.sleep(3000);
			Actions act = new Actions(oASelFW.driver);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//td[@class='sbSectionTextBkg']//a[text()='"+tab+"']"))).build().perform();
			act.click().build().perform();
			Thread.sleep(5000);
			String status = oASelFW.effecta("isElementPresent","//span[text()='Security Role*']/../following-sibling::td//input[text()='"+jobname.get(i).trim()+"']");
			System.out.println(status);
			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//a[text()='User Name']/../../../following-sibling::tr/td[2]//a")))
			{
				oASelFW.effecta("sendReportWithOutExit","Validating Peoples assigned for the Security Role ","Successfully Validated Peoples Assign for the Security Roles","");
			}else
			{
				oASelFW.effecta("sendReportWithOutExit","Validating Peoples assigned for the Security Role ","No Peoples Assign for the Security Roles","Pass");
			}
			switch_to_default_content();
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	public void verifyRosterButton()
	{
		try {

			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			String button = oASelFW.effecta("getText","//span[text()='Roster']&&//span[text()='Results']","Button");
			oASelFW.effecta("verifyElementPresent","//span[text()='Roster']&&//span[text()='Results']",button);

			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='Roster']")))
			{
				Actions act = new Actions(oASelFW.driver);
				act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Roster']"))).build().perform();
				Thread.sleep(2000);
				act.click().build().perform();
				Thread.sleep(8000);
				oASelFW.effecta("sendReport","Clickin on the Roster Button ","Successfully clicked on the Roster Button and Navigated to the Class Page","Pass");	
				Thread.sleep(8000);
			}
			else
			{
				Actions act = new Actions(oASelFW.driver);
				act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Results']"))).build().perform();
				Thread.sleep(2000);
				act.click().build().perform();
				Thread.sleep(8000);
				oASelFW.effecta("sendReport","Clickin on the Result Button ","Successfully clicked on the Result Button and Navigated to the Class Page","Pass");	
				Thread.sleep(8000);

			}

			String text="";
			String cnt = oASelFW.effecta("getXpathCount","//span[text()='Name']/../../../../../following-sibling::div/descendant::tbody//tr");
			int count = Integer.parseInt(cnt);
			System.out.println("count is ***********"+count);
			Thread.sleep(5000);
			for(int i=1; i<=count; i++){
				Thread.sleep(2000);
				text=oASelFW.driver.findElement(By.xpath("//span[text()='Name']/../../../../../following-sibling::div/descendant::tbody//tr["+i+"]/descendant::td[2]/descendant::div[2]")).getText();
				Thread.sleep(2000);
				System.out.println(text+"*******");
				oASelFW.effecta("sendReport","Validating the Number of Users in Class Page ","Successfully Retriving Users in Class Page as "+text+"","Pass");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		switch_to_default_content();
	}


	public void addSessionWithWeekDayTimining(String session, String week, String day, String sessionTime)
	{
		try
		{
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

			Actions act = new Actions(oASelFW.driver);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[text()='"+session+"']"))).build().perform();
			act.click().build().perform();
			Thread.sleep(3000);
			String[] window6 = oASelFW.effectaArray("getAllWindowNames");
			window6 = oASelFW.effectaArray("getAllWindowNames");
			oASelFW.effecta("selectWindow",window6[window6.length-1]);

			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='Session Week*']")))
			{

				Thread.sleep(3000);
				oASelFW.effecta("type", "//span[text()='Session Week*']/../following-sibling::td//input[2]", week, "Session Week");
				Thread.sleep(2000);
				oASelFW.effecta("select","//span[text()='Session Day*']/../following-sibling::td//select",day,"Session Day");

				String timing = sessionTime.trim();
				System.out.println(timing);
				String delimiter = "/";
				String[] time = timing.trim().split(delimiter);
				System.out.println("length of total timing:::::::"+time.length);
				Thread.sleep(2000);
				oASelFW.effecta("type","//span[contains(text(),'Start Time')]/../following-sibling::td/descendant::input[1]", time[0].trim(),"Start Time in Hr");
				Thread.sleep(2000);
				oASelFW.effecta("type","//span[contains(text(),'Start Time')]/../following-sibling::td/descendant::input[2]", time[1].trim(),"Start Time in Min");
				Thread.sleep(2000);
				oASelFW.effecta("type","//span[contains(text(),'End Time')]/../following-sibling::td/descendant::input[1]", time[2].trim(),"End Time in Hr");
				Thread.sleep(2000);
				oASelFW.effecta("type","//span[contains(text(),'End Time')]/../following-sibling::td/descendant::input[2]", time[3].trim(),"End Time in Min");
				Thread.sleep(2000);
				oASelFW.effecta("clickAndWait","//span[contains(text(),'End Time')]/../following-sibling::td/descendant::input[3]", "End Time in AM","Pass");
				Thread.sleep(3000);
				oASelFW.effecta("clickAndWait","//span[text()='Save']", "Session Window Save","Pass");
				Thread.sleep(2000);
				oASelFW.effecta("clickAndWait","//span[text()='Close']", "Session Window Close","Pass");
				Thread.sleep(4000);
				oASelFW.effecta("selectWindow",window6[0]);
				Thread.sleep(3000);

				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
				Thread.sleep(2000);
				act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Save']"))).build().perform();
				act.click().build().perform();
				//oASelFW.effecta("clickAndWait","//span[text()='Save']", "Main Window Save ","Pass");
				switch_to_default_content();

			}
			else{

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void click_ActiosnLinkOfSession_link(String sessionName, String action)
	{
		try {

			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			//oASelFW.effecta("waitForElementPresent","//a[text()='"+sessionName+"']/../../following-sibling::td[2]//a[text()='"+action+"']","60");

			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//a[text()='"+sessionName+"']")))
			{
				oASelFW.effecta("click","//a[text()='"+sessionName+"']/../../following-sibling::td[2]//a[text()='"+action+"']","Delete");
				handle_Alerts_PopUP();
			}
			oASelFW.driver.switchTo().defaultContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ValidateManageCategoriesSetUp_Page()
	{
		try {

			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			Thread.sleep(2000);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@id='categorywrapper']")));

			String text="";
			Thread.sleep(5000);

			String cnt = oASelFW.effecta("getXpathCount","//span[contains(text(),'Categories')]/../../../../../../table[@class='wicket-tree-content']");
			//int count = oASelFW.driver.findElements(By.xpath("//span[contains(text(),'Categories')]/../../../../../../table")).size();

			int count=Integer.parseInt(cnt);
			System.out.println("count is ***********"+count);
			Thread.sleep(5000);
			for(int i=2; i<=count-1; i++){
				Thread.sleep(2000);
				text=oASelFW.driver.findElement(By.xpath("//span[contains(text(),'Categories')]/../../../../../../table[@class='wicket-tree-content']["+i+"]/descendant::tr/td[3]//span[1]")).getText();
				Thread.sleep(2000);
				System.out.println(text+"*******");
				oASelFW.effecta("sendReport","Validating the text "+text+" found ","Successfully validate "+text+" in Categories Page ","Pass");
			}
			oASelFW.driver.switchTo().defaultContent();
			oASelFW.driver.switchTo().defaultContent();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void validatePrescriptiveRulesTitles(ArrayList<String> list)
	{
		try {
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			Thread.sleep(2000);
			oASelFW.effecta("clickAndWait","//span[text()='Search']","Search Button","Pass");
			Thread.sleep(6000);

			String text="";
			String cnt = oASelFW.effecta("getXpathCount","//a[text()='Name']/../../../../tr");

			int count=Integer.parseInt(cnt);
			System.out.println("count is ***********"+count);
			Thread.sleep(5000);
			for(int i=2, j=0; i<count; i++){
				Thread.sleep(2000);
				text=oASelFW.driver.findElement(By.xpath("//a[text()='Name']/../../../../tr["+i+"]/td[2]//a")).getText();
				Thread.sleep(2000);
				if(text.contains(list.get(j))){
					System.out.println(text+"*******");
					oASelFW.effecta("sendReport","Validating the text "+list.get(j)+" found ","Successfully validate "+text+" in Prescriptive Rules Page ","Pass");
					j++;
				}

			}

			oASelFW.driver.switchTo().defaultContent();

		} catch (Exception e) {
			e.printStackTrace();
		}


	}
	public void validateActivitiesPageForResourceAndEquivalent()
	{

		try {
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

			oASelFW.effecta("getText","//a[contains(text(),'Purpose')]/../../../following-sibling::tr[1]//td[2]//span","Instructor Leader");
			oASelFW.effecta("verifyElementPresent","//a[contains(text(),'Purpose')]/../../../following-sibling::tr[1]//td[2]//span","Instructor Leader");

			oASelFW.effecta("getText","//span[contains(text(),'Evaluation')]/../../../../../../following-sibling::tr//descendant::tr[2]//td[2]//span//a","Evaluation");
			oASelFW.effecta("verifyElementPresent","//span[contains(text(),'Evaluation')]/../../../../../../following-sibling::tr//descendant::tr[2]//td[2]//span//a","Evaluation");

			oASelFW.driver.switchTo().defaultContent();

		} catch (Exception e) {
			// TODO: handle exception
		}


	}

	public void validateActivitiesTabPageForActivities()
	{

		try {
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

			String Name= oASelFW.effecta("getText", "//span[contains(text(),'Module Name')]/../../following-sibling::tr[1]//td[6]//a", "Module Name");
			oASelFW.effecta("verifyElementPresent", "//span[contains(text(),'Module Name')]/../../following-sibling::tr[1]//td[6]//a", Name);

			oASelFW.driver.switchTo().defaultContent();

		} catch (Exception e) {
			// TODO: handle exception
		}


	}

	public void validateOtherInformationForSurveyType(String Surveytype)
	{
		try {
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

			String cnt=oASelFW.effecta("getXpathCount","//label[text()='Survey Type']/../../following-sibling::td//select//option");
			int count=Integer.parseInt(cnt);
			System.out.println("count is ***********"+count);	
			System.out.println("Delivery Types length ***************"+count);
			for(int i=2; i<=count; i++)
			{
				String value =oASelFW.driver.findElement(By.xpath("//label[text()='Survey Type']/../../following-sibling::td//select//option["+i+"]")).getText();
				oASelFW.effecta("sendReport", "Retriving Survey Types for "+Surveytype+" Course", "Successfully Validating the Survey Types "+value,"Pass");
			}


			oASelFW.driver.switchTo().defaultContent();

		} catch (Exception e) {
			e.printStackTrace();
		}



	}

	//--------------------------analysis reporting

	public String[] retrivingColumnsData(String[] columnsData, String columName)

	{
		String data[]=new String [columnsData.length];
		int col[]=new int [columnsData.length];
		int k=0;
		try {

			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			int count=oASelFW.driver.findElements(By.xpath("//div[text()='"+columName.trim()+"']/../../th")).size();
			for(int j=0;j<columnsData.length;)
			{
				for(int l=1;l<=count;l++)
				{
					String text=oASelFW.driver.findElement(By.xpath("//div[text()='"+columName.trim()+"']/../../th["+l+"]")).getText();
					System.out.println("Text "+text);
					if(text.equals(columnsData[j]))
					{
						col[k]=l;
						System.out.println("column L value"+l);
						k++;
						break;
					}
				}
				j++;
			}

			System.out.println(Arrays.toString(col));

			for(int i=0;i<columnsData.length;i++)
			{
				data[i]=oASelFW.driver.findElement(By.xpath("//div[text()='"+columnsData[i].trim()+"']/../following::div//table//tbody//tr[3]//td["+col[i]+"]//div")).getText();
				oASelFW.effecta("sendReport","Retrieving data  "+data[i]+" found ","Retrieving data  "+data[i]+"","Pass");
				System.out.println("data  :"+data[i]);

			}
			oASelFW.driver.switchTo().defaultContent();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public String[] retrivingColumnsData_tr1(String[] columnsData, String columName)

	{
		String data01[]=new String [columnsData.length];
		int col[] = new int [columnsData.length];
		int k=0;
		try {

			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			int count=oASelFW.driver.findElements(By.xpath("//div[text()='"+columName.trim()+"']/../../th")).size();
			System.out.println("th count is: "+count);
			for(int j=0;j<columnsData.length;)
			{
				for(int l=1;l<=count;l++)
				{
					String text=oASelFW.driver.findElement(By.xpath("//div[text()='"+columName.trim()+"']/../../th["+l+"]")).getText();
					System.out.println("Text "+text);
					if(text.equals(columnsData[j]))
					{
						col[k]=l;
						System.out.println("l value "+l);
						k++;
						break;
					}
				}
				j++;
			}

			System.out.println(Arrays.toString(col));

			for(int i=0;i<columnsData.length-1;i++)
			{
				data01[i]=oASelFW.driver.findElement(By.xpath("//div[contains(text(),'"+columnsData[i].trim()+"')]/../following::div//table//tbody//tr[4]//td["+col[i]+"]//div")).getText();
				oASelFW.effecta("sendReport","Retrieving data  "+data01[i]+" found ","Retrieving data  "+data01[i]+"","Pass");
				System.out.println("data  :"+data01[i]);

			}
			oASelFW.driver.switchTo().defaultContent();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return data01;
	}




	public String[] retrivingExpectedColumnData(String[] columnsData, String name) throws InterruptedException
	{

		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		Actions act = new Actions(oASelFW.driver);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		String data[]=new String [columnsData.length];
		for(int i=0,j=0; i<columnsData.length; i++)
		{
			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='"+columnsData[i]+"']/../following-sibling::td"))&&j!=2)
			{
				data[i]=oASelFW.driver.findElement(By.xpath("//span[text()='"+columnsData[i]+"']/../following-sibling::td")).getText();
				oASelFW.effecta("sendReport","Retrieving data  "+data[i]+" found ","Retrieving data  "+data[i]+"","Pass");
				System.out.println("data  :"+data[i]);
			}
			else if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//td[text()='"+columnsData[i]+"']/following-sibling::td"))&&j!=2)
			{
				data[i]=oASelFW.driver.findElement(By.xpath("//td[text()='"+columnsData[i]+"']/following-sibling::td")).getText();
				oASelFW.effecta("sendReport","Retrieving data  "+data[i]+" found ","Retrieving data  "+data[i]+"","Pass");
				System.out.println("data  :"+data[i]);
			}
			if(j==2)
			{
				act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[contains(text(),'Results')]"))).build().perform();
				act.click().build().perform();
				Thread.sleep(9000);
				System.out.println("clicking item "+name);
				if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//a[contains(text(),'"+name.trim()+"')]")))
				{
					data[i]=oASelFW.driver.findElement(By.xpath("//a[contains(text(),'"+name.trim()+"')]")).getText();
					oASelFW.effecta("sendReport","Retrieving data  "+data[i]+" found ","Retrieving data  "+data[i]+"","Pass");
					System.out.println("data  :"+data[i]);
				}

				act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[contains(text(),'"+name.trim()+"')]"))).build().perform();
				act.click().build().perform();
				Thread.sleep(9000);
				oASelFW.effecta("sendReprot","Click on the User ","Successfully clicked on "+name,"Pass");
				String window[] = oASelFW.effectaArray("getAllWindowNames");
				oASelFW.effecta("selectWindow",window[1]);
				Thread.sleep(4000);

			}
			System.out.println("i value "+i);
			j++;
			System.out.println("j value "+j);

		}
		Thread.sleep(1000);
		oASelFW.driver.close();
		Thread.sleep(2000);
		String window[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window[window.length-1]);
		Thread.sleep(2000);
		return data;	
	}

	public String[] retrivingExpectedColumnData_01(String[] columnsData) throws InterruptedException
	{

		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		Actions act = new Actions(oASelFW.driver);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		String data[]=new String [columnsData.length];
		for(int i=0,j=0; i<columnsData.length; i++)
		{
			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='"+columnsData[i]+"']/../following-sibling::td"))&&j!=2)
			{
				data[i]=oASelFW.driver.findElement(By.xpath("//span[text()='"+columnsData[i]+"']/../following-sibling::td")).getText();
				oASelFW.effecta("sendReport","Retrieving data  "+data[i]+" found ","Retrieving data  "+data[i]+"","Pass");
				System.out.println("data  :"+data[i]);
			}
			else if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//td[text()='"+columnsData[i]+"']/following-sibling::td"))&&j!=2)
			{
				data[i]=oASelFW.driver.findElement(By.xpath("//td[text()='"+columnsData[i]+"']/following-sibling::td")).getText();
				oASelFW.effecta("sendReport","Retrieving data  "+data[i]+" found ","Retrieving data  "+data[i]+"","Pass");
				System.out.println("data  :"+data[i]);
			}
			/*if(j==2)
			{
				act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[contains(text(),'Results')]"))).build().perform();
				act.click().build().perform();
				Thread.sleep(9000);
				System.out.println("clicking item "+name);
				if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//a[contains(text(),'"+name.trim()+"')]")))
				{
					data[i]=oASelFW.driver.findElement(By.xpath("//a[contains(text(),'"+name.trim()+"')]")).getText();
					oASelFW.effecta("sendReport","Retrieving data  "+data[i]+" found ","Retrieving data  "+data[i]+"","Pass");
					System.out.println("data  :"+data[i]);
				}

				act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[contains(text(),'"+name.trim()+"')]"))).build().perform();
				act.click().build().perform();
				Thread.sleep(9000);
				oASelFW.effecta("sendReprot","Click on the User ","Successfully clicked on "+name,"Pass");
				String window[] = oASelFW.effectaArray("getAllWindowNames");
				oASelFW.effecta("selectWindow",window[1]);
				Thread.sleep(4000);

			}*/
			System.out.println("i value "+i);
			j++;
			System.out.println("j value "+j);

		}
		Thread.sleep(3000);
		oASelFW.driver.switchTo().defaultContent();
		return data;	
	}

	//[Basha,Completed]
	public void clickingOnCompletedLinksInMainContentPage(String name, String status)
	{
		try {

			ClickOnElement("//td[text()='Completed:']/../descendant::td[2]//a", "Completed Link");
			Thread.sleep(2000);

			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//td[contains(text(),'"+name+"')]/following::td[2]//select")))
			{
				selectBytext("//td[contains(text(),'"+name+"')]/following::td[2]//select", status);
				Thread.sleep(3000);
				ClickOnElement("//input[@type='submit']", "Save");

			}else if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//td[text()='Email:']")))
			{
				SetTextField("//td[text()='Email:']/following::input", "bashaa@vmware.com", "Email");
				Thread.sleep(1000);
				ClickOnElement("//input[@value='Search']", "Search Button");
				Thread.sleep(1000);
				ClickOnElement("//span[contains(text(),'Basha')]/../../td[2]//input", "Check User");
				Thread.sleep(2000);
				ClickOnElement("//input[@value='Mark checked students completed']", "Mark checked students completed");
				Thread.sleep(2000);

			}

			Thread.sleep(3000);
			String RecordStatus = getText("//td[contains(text(),'Records updated')]");
			oASelFW.effecta("sendReportWithOutExit","Record updated status ","Successfully Record updated status as :"+ RecordStatus ,"Pass");


		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	//Transcript
	public void clickOnUserTranscriptLink(String name, String property, String course)
	{
		try {

			SetTextField("//input[@name='email']", "bashaa@vmware.com", "email Id");
			Thread.sleep(1000);
			ClickOnElement("//input[@value='Find']", "Find Button");
			Thread.sleep(3000);

			ClickOnElement("//td[contains(text(),'"+name+"')]/../td[1]//input", "Transcript");
			Thread.sleep(1000);
			ClickOnElement("//td[contains(text(),'Basha')]/following::td[4]//a[text()='"+property+"']", property);
			Thread.sleep(3000);

			int count = oASelFW.driver.findElements(By.xpath("//font[text()='Course']/../../../../tr")).size();
			for(int i=1; i<=count; i++)
			{
				String text = oASelFW.driver.findElement(By.xpath("//font[text()='Course']/../../../../tr["+i+"]//td")).getText();
				if(text.contains(course))
				{
					oASelFW.effecta("sendReportWithOutExit","Validating the Course in Transcript Page ","Successfully validated the Course "+course+" in Transcript Page","Pass");
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}


	}
































	//	----------------------------------------------------siddharth------------------------------------------------------



	public Boolean WaitOnElement(String Element, int Time )
	{
		try{
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, Time);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Element)));

			return true;
		}
		catch(org.openqa.selenium.NoSuchElementException e){
			System.out.println("no such element exception");
			oASelFW.effecta("sendReport","Wait on the Element "+Element," Element not found "+ Element,"Fail");
			return false;
		}

	}

	public void ClickOnElement(String Element, String ElementType)
	{
		if(WaitOnElement(Element, 15))
		{
			oASelFW.effecta("click",Element,ElementType);
			System.out.println("Clicked on "+ElementType);
			//oASelFW.effecta("sendReport","Click on the Link/img "," Clicked on the Element "+ ElementType,"Pass");
		}

		else
		{
			oASelFW.effecta("sendReport","Click on the Link/img ", ElementType+" not found","Fail");
		}

	}
	public void SetTextField(String xpath, String Value, String FieldType)
	{
		if(WaitOnElement(xpath,5))
		{
			oASelFW.driver.findElement(By.xpath(xpath)).clear();
			oASelFW.driver.findElement(By.xpath(xpath)).sendKeys(Value);
			oASelFW.effecta("sendReport","Set text field with value: "+ Value.replaceAll("[^\\w\\s-]","")," Text Field "+FieldType+" set by "+Value.replaceAll("[^\\w\\s-]",""),"Pass");
		}

		else
		{
			oASelFW.effecta("sendReport","Set text field with value: "+ Value ," Text Field not found","Fail");
		}
	}


	public boolean waitOnAlert()
	{
		try {
			WebDriverWait wait = new WebDriverWait(oASelFW.driver, 10);
			wait.until(ExpectedConditions.alertIsPresent());
			return true;
		} catch (TimeoutException e) {
			oASelFW.effecta("sendReport","Waiting for Alert", "Expected Alert is not found", "Fail");
			return false;
		}
		catch (org.openqa.selenium.NoAlertPresentException e1) {
			oASelFW.effecta("sendReport","Waiting for Alert", "Expected Alert is not found", "Fail");
			return false;
		}
	}
	public void acceptAlert()
	{
		waitOnAlert();
		oASelFW.driver.switchTo().alert().accept();
		oASelFW.effecta("sendReport","Accepting Alert", "Successfully Accepted Alert", "Pass");
	}
	public void dismissAlert()
	{
		waitOnAlert();
		oASelFW.driver.switchTo().alert().dismiss();
		oASelFW.effecta("sendReport","Dismissing Alert", "Successfully Dismiss Alert", "Pass");
	}

	public String getTextFromAlert()
	{
		waitOnAlert();
		return oASelFW.driver.switchTo().alert().getText();
	}
	public void selectBytext(String xpath, String value)
	{

		if(WaitOnElement(xpath, 5))
		{
			new org.openqa.selenium.support.ui.Select(oASelFW.driver.findElement(By.xpath(xpath))).selectByVisibleText(value);


			oASelFW.effecta("sendReport","Select desired value from Drop down", "Selected "+ value, "Pass");
		}

		else
		{

			oASelFW.effecta("sendReport","Select desired value from Drop down",  value+" Not found", "Fail");
		}

	}
	public int GenerateRandomNoBetweenLimits(int upper, int lower)
	{
		int r = (int) (Math.random() * (upper - lower)) + lower;
		System.out.println(r);
		return r;
	}

	public void ClickSpanContainingElement(String TextName, String ElementType)
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 10);
		String Element= "//span[text()='"+TextName+"']";
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Element)));
		oASelFW.effecta("click",Element,TextName);
		System.out.println("Clicked on "+TextName);
	}

	public void switch_to_default_content() {
		oASelFW.driver.switchTo().defaultContent();
	}

	public void switch_to_main_content() {
		WebDriverWait wait = new WebDriverWait(oASelFW.driver, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		System.out.println("switched to main content");
	}

	public void handleAlert() 
	{ 
		WebDriverWait wait = new WebDriverWait(oASelFW.driver, 05); 
		wait.until(ExpectedConditions.alertIsPresent()); 
		oASelFW.driver.switchTo().alert().accept(); 
	}

	public void Switchframe()
	{
		switch_to_default_content();
		switch_to_main_content();
	}

	public void ClickOnButton(String TextName) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 10);
		String Element= "//span[text()='"+TextName+"']";
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Element)));
		oASelFW.effecta("click",Element,TextName +" button");
		Thread.sleep(3000);
		System.out.println("Clicked on "+ TextName);
	}

	public void SelectLearningActivity(String ActivityType)
	{
		ClickSpanContainingElement(ActivityType, ActivityType);
	}

	public void SelectClass(String Managetype, String DevType)
	{
		SelectLearningActivity(Managetype);
		selectBytext("//select[@name='Catalog_query70176_var_1$kSelect']", DevType);
		ClickOnElement("//span[text()='Search']", "Search Button");
		ClickOnElement("//span[text()='Roster']/../../following-sibling::tr[3]/td[4]/span/a", "Class Name");
	}

	public String ChangeDate() throws InterruptedException
	{

		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));



		oASelFW.effecta("clickAndWait","//span[text()='Start Date*']/../../following-sibling::td//a//img","Look Up","Pass");
		String window4[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow", window4[1]);
		Thread.sleep(1000);
		System.out.println("in select domain window");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMMM-d");
		Calendar cal = Calendar.getInstance();
		Date date1 = cal.getTime();
		cal.add(Calendar.DATE,7);
		//cal.add(Calendar.YEAR, 1);
		Date nextYear = cal.getTime();
		String enddatetime = sdf.format(nextYear);
		System.out.println(enddatetime);
		String[] date = enddatetime.split("-");
		String month = date[1];
		String year = date[0];
		String day = date[2];
		System.out.println("month"+date[1]);
		System.out.println("year"+date[0]);
		System.out.println("day"+date[2]);

		Actions act = new Actions(oASelFW.driver);
		/*Thread.sleep(3000);
		act.moveToElement(oASelFW.driver.findElement(By.xpath("//select[@name='selectedMonth']"))).build().perform();
		act.moveToElement(oASelFW.driver.findElement(By.xpath("//select[@name='selectedMonth']//options[contains(text(),'"+month+"')]"))).build().perform();
		act.click().build().perform();*/
		selectBytext("//select[@name='selectedMonth']", month);

		Thread.sleep(3000);
		act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[contains(text(),'"+day+"')]"))).build().perform();
		act.click().build().perform();
		System.out.println("selected date  "+day);

		Thread.sleep(2000);
		oASelFW.effecta("selectWindow",window4[0]);
		oASelFW.driver.switchTo().defaultContent();
		return day;

	}
	public void EditDate() throws InterruptedException
	{
		Switchframe();
		ClickOnElement("//span[text()='Start Date *']/../../following-sibling::td//a//img", "Pencil icon against Start Date");// EditDate
		String window[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window[1]);// Jump to window edit date 
		ClickOnElement("//img[@alt='Start Date']", "Start Date");
		String window1[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window1[2]);// Jump to Calander
		//int RanDate = GenerateRandomNoBetweenLimits(30, 29); // add date

		ClickOnElement("//a[text()='30']", "New Date");
		oASelFW.effecta("selectWindow",window1[1]);// back to edit date
		ClickOnButton("Next");	// Click on next
		ClickOnElement("//input[@title='Ignore Conflicts']", "Ignore Conflicts radio button");
		ClickOnButton("Save"); // Click save 
		String window2[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window2[2]);// Jump to reason box
		SetTextField("//textarea[@name='reason']", "Test reason", "ReasonBox");// Give reason
		ClickOnButton("Save"); // save date
		oASelFW.effecta("selectWindow",window2[1]);// Jump to Calander
		acceptAlert();
		oASelFW.driver.switchTo().defaultContent();
		oASelFW.effecta("selectWindow",window[0]);// Jump to main win
		System.out.println("Switched to main window");
		Switchframe();
		ClickOnButton("Save"); 
		switch_to_default_content();

	}

	public void EditSessionTemplete()
	{
		Switchframe();
		ClickOnElement("//span[text()='Session Template*']/../../following-sibling::td//a//img", "Pencil icon against Start Date");//Session Template
		String window3[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window3[1]);	//Jump to Session template Window
		ClickOnElement("//img[@alt='Session Template']", "Session Template");// click on goto new session template
		String window4[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window4[2]);// Jump to new session template win
		oASelFW.driver.findElement(By.xpath("//input[@name='session_query_var_name$kString$kLike']")).clear();
		ClickOnElement("//span[text()='Search']", "Search Button");
		ClickOnElement("//span[text()='Actions']/../../following-sibling::tr/td[2]/a", "New Session template");
		oASelFW.effecta("selectWindow",window4[1]); // Get back to Session Template
		ClickOnElement("//span[text()='Next']", "Next Button");	// Click on next
		ClickOnElement("//span[text()='Save']", "Save Button"); // Click save 
		String window5[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window5[2]);// Jump to reason box
		SetTextField("//textarea[@name='reason']", "Test reason", "ReasonBox");// Give reason
		ClickOnElement("//span[text()='Save']", "Save Button"); // save ST
		oASelFW.effecta("selectWindow",window5[1]);//jump to ST
		acceptAlert();
		oASelFW.driver.switchTo().defaultContent();
		oASelFW.effecta("selectWindow",window5[0]);// Jump to main win
		Switchframe();
		ClickOnElement("//span[text()='Save']", "Save Button"); // save 

		switch_to_default_content();

	}

	public void EditLocation() throws InterruptedException
	{

		Switchframe();
		ClickOnElement("//span[text()='Location*']/../following-sibling::td/a/img", "Pencil icon against Start Date");//Location
		String window6[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window6[1]);	//Jump to Location
		ClickOnElement("//span[text()='Search']", "Search Button"); // Search  all locations
		ClickOnElement("//span[text()='Select']/../../following-sibling::tr/td[2]/a", "Location");// select location
		String window7[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window7[1]);	//Jump to change bas e location
		ClickOnElement("//span[text()='Save and Close']", "Save and Close Button"); // save
		acceptAlert();
		//oASelFW.driver.switchTo().defaultContent();
		oASelFW.effecta("selectWindow",window7[0]);// Jump to main win
		Switchframe();
		ClickOnButton("Save"); // save
		switch_to_default_content();
	}

	public void CreateNewVLTClass(String classID, String domain, String lang, String Duration, String location,String MinCount, String MaxCount, String MaxWList ) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);

		//Give Class Id
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		oASelFW.effecta("type","//span[text()='Class ID*']/../../following-sibling::td//input&&//span[text()='Class ID*']/../following-sibling::td//input",classID,"Pass");

		//Give Domain name
		oASelFW.effecta("clickAndWait","//span[text()='Domain*']/../../following-sibling::td//a//img&&//span[text()='Domain*']/../following-sibling::td//a//img","Look Up","Pass");
		String window[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window[1]);
		Thread.sleep(1000);
		System.out.println("in select domain window");
		oASelFW.effecta("type","//label[text()='Name']/../../following-sibling::td//input", domain,"Domain");
		oASelFW.effecta("clickAndWait","//a[@title='Search']","Search","Pass");
		Thread.sleep(2000);
		oASelFW.effecta("clickAndWait","//span[text()='"+domain.trim()+"']/../../descendant::td[2]//a//img", domain,"Pass");
		oASelFW.effecta("selectWindow",window[0]);
		switch_to_default_content();

		//StartDate
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='Start Date*']/../../following-sibling::td//a//img")))
		{
			oASelFW.effecta("clickAndWait","//span[text()='Start Date*']/../../following-sibling::td//a//img","Look Up","Pass");
			String window4[] = oASelFW.effectaArray("getAllWindowNames");
			oASelFW.effecta("selectWindow", window4[1]);
			Thread.sleep(1000);
			System.out.println("in select domain window");

			Calendar currentDate = Calendar.getInstance(); //Get the current date
			SimpleDateFormat formatter= new SimpleDateFormat("yyyy/MMMM/dd"); //format it as per your requirement
			String dateNow = formatter.format(currentDate.getTime());
			String[] date = dateNow.split("/");
			String month = date[1];
			String year = date[0];
			String day = date[2];

			System.out.println(date[1]);
			System.out.println("Day from calendar is*************"+day);
			System.out.println("Now the date is :=>  " + dateNow);


			Actions act = new Actions(oASelFW.driver);

			act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[contains(text(),'"+day+"')]"))).build().perform();
			act.click().build().perform();
			System.out.println("selected date  "+day);



			//oASelFW.effecta("clickAndWait","//a[text()='"+startDate+"']", ""+startDate+"/"+month+"/"+year+"","Pass");
			Thread.sleep(2000);
			oASelFW.effecta("selectWindow",window[0]);
			switch_to_default_content();
		}

		//createSessiontemplate
		Switchframe();
		ClickOnElement("//span[text()='Create New Session Template']/../input", "Create New Session template");
		CreateSessionTemplate();
		switch_to_default_content();

		//SelectLocation
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		oASelFW.effecta("clickAndWait","//span[text()='Location*']/../../following-sibling::td//a//img&&//span[text()='Location*']/../following-sibling::td//a//img","Look Up","Pass");
		String window2[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window2[1]);
		Thread.sleep(1000);
		System.out.println("in select domain window");
		oASelFW.effecta("type","//label[text()='Name']/../../following-sibling::td//input", location,"Location");
		oASelFW.effecta("clickAndWait","//a[@title='Search']","Search","Pass");
		Thread.sleep(2000);
		oASelFW.effecta("clickAndWait","//span[text()='"+location.trim()+"']/../../descendant::td[2]//a//img", location,"Pass");
		oASelFW.effecta("selectWindow",window2[0]);
		switch_to_default_content();

		//Add Language
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		oASelFW.effecta("clickAndWait","//span[text()='Language*']/../../following-sibling::td//a//img&&//span[text()='Language*']/../following-sibling::td//a//img","Look Up","Pass");
		String window3[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window3[1]);
		Thread.sleep(1000);
		System.out.println("in select language window");
		oASelFW.effecta("type","//label[text()='Name']/../../following-sibling::td//input", lang,"Location");
		oASelFW.effecta("clickAndWait","//a[@title='Search']","Search","Pass");
		Thread.sleep(2000);
		oASelFW.effecta("clickAndWait","//span[text()='"+lang.trim()+"']/../../descendant::td[2]//a//img", lang,"Pass");
		oASelFW.effecta("selectWindow",window3[0]);
		switch_to_default_content();

		// Give Duration
		switch_to_main_content();
		oASelFW.effecta("type","//input[@name='comp_duration']", Duration,"Duration");
		switch_to_default_content();

		//MinMax

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

		oASelFW.effecta("type","//span[text()='Min Count*']/../../following-sibling::td//input[2]", MinCount,"Min Count");
		oASelFW.effecta("type","//span[text()='Max Count*']/../../following-sibling::td//input[2]", MaxCount,"Max Count");
		oASelFW.effecta("type","//span[text()='Max In Wait List*']/../../following-sibling::td//input[2]", MaxWList,"Max In Wait List");

		switch_to_default_content();


	}
	public void CreateSessionTemplate() throws InterruptedException
	{
		ClickOnElement("//span[text()='Session Template*']/../../following-sibling::td//a//img", "Pencil icon against Start Date");//Session Template
		String window[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window[1]);	//Jump to Session template Window

		Thread.sleep(1000);

		System.out.println("in add session template Window");
		oASelFW.effecta("clickAndWait","//a[text()='Add Session']","Add Session","Pass");
		String window2[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window2[2]);
		ClickOnElement("//img[@alt='Start Date']", "Start Date");
		String window3[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window3[3]);
		System.out.println("in select date window");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMMM-d");
		Calendar cal = Calendar.getInstance();
		Date date1 = cal.getTime();
		cal.add(Calendar.DATE, 2);
		Date nextYear = cal.getTime();
		String enddatetime = sdf.format(nextYear);
		System.out.println(enddatetime);
		String[] date = enddatetime.split("-");
		String month = date[1];
		String year = date[0];
		String day = date[2];
		System.out.println("month"+date[1]);
		System.out.println("year"+date[0]);
		System.out.println("day"+date[2]);

		System.out.println("Day from calendar is*************"+day);
		System.out.println("Now the date is :=>  " + enddatetime);


		Actions act = new Actions(oASelFW.driver);

		act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[contains(text(),'"+day+"')]"))).build().perform();
		act.click().build().perform();

		oASelFW.effecta("selectWindow",window3[2]);

		oASelFW.effecta("type","//input[@name='startTime_hour']","07","StartHour::"+07+"");
		oASelFW.effecta("type","//input[@name='startTime_minute']","00","StartHour::"+15+"");

		oASelFW.effecta("type","//input[@name='endTime_hour']","09","StartHour:: 09");
		oASelFW.effecta("type","//input[@name='endTime_minute']","00","StartHour:: 00");// EnterEndTime
		ClickOnElement("//a[@title='Save']", "Save Button");// Click Save
		ClickOnButton("Close");//Click Close

		oASelFW.effecta("selectWindow", window3[1]);

		ClickOnElement("//span[text()='Next']", "Next Button");	// Click on next
		/*if(waitOnAlert()== true)
			{	acceptAlert();
				ClickOnElement("//span[text()='Save']", "Save Button"); // Click save 
				String window5[] = oASelFW.effectaArray("getAllWindowNames");
				oASelFW.effecta("selectWindow",window5[2]);// Jump to reason box
				SetTextField("//textarea[@name='reason']", "Test reason", "ReasonBox");// Give reason
				ClickOnElement("//span[text()='Save']", "Save Button"); // save ST
				oASelFW.effecta("selectWindow",window5[1]);//jump to ST
				acceptAlert();
				oASelFW.driver.switchTo().defaultContent();
				oASelFW.effecta("selectWindow",window5[0]);// Jump to main win
				Switchframe();
				ClickOnElement("//span[text()='Save']", "Save Button"); // save 
			}*/

		//else
		//{
		ClickOnElement("//span[text()='Save']", "Save Button"); // Click save 
		String window5[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window5[2]);// Jump to reason box
		SetTextField("//textarea[@name='reason']", "Test reason", "ReasonBox");// Give reason
		ClickOnElement("//span[text()='Save']", "Save Button"); // save ST
		oASelFW.effecta("selectWindow",window5[1]);//jump to ST
		acceptAlert();
		oASelFW.driver.switchTo().defaultContent();
		oASelFW.effecta("selectWindow",window5[0]);// Jump to main win


		oASelFW.effecta("sendReport","verify the content is saved after creating the Session templete "," SUCCESSSFULLY SAVED THE CLASS AFTER CREATING THE SESSION TEMPLETE","Pass");

		switch_to_default_content();

	}

	public void AddPrice(String CurrencyType ) throws InterruptedException
	{	
		Switchframe();
		int Val= GenerateRandomNoBetweenLimits(100, 50);
		String  Value = Integer.toString(Val);
		ClickOnElement("//a[text()='Add Price']", "Add");
		String window[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window[1]);
		System.out.println("The CurrencyType Is:"+ CurrencyType);
		ClickOnElement("//img[@alt='Currency']", "Currency Selection Button");// Click On logo
		String window1[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window1[2]);
		ClickOnButton("Search");
		ClickOnElement("//img[@alt='Select']", "Currency type");
		oASelFW.effecta("selectWindow",window1[1]);
		SetTextField("//input[@name='_displayprice_display']", Value, "Currency value");
		System.out.println("Selected value: "+Value);
		ClickOnElement("//input[@name='calculatePricesForAll']", "Check Box");
		ClickOnButton("Save");// Save
		switch_to_default_content();

	}

	public void ValidatePreviewContent_ViewResult(String RepositoryType, String LinkToTest1, String LinkToTest2) throws InterruptedException
	{
		ClickOnElement("//a[text()='"+RepositoryType+"']", RepositoryType);
		if(WaitOnElement("//span[text()='Name']/../../following-sibling::tr/td[2]/span/a", 05))
		{
			String ContentTyp = oASelFW.driver.findElement(By.xpath("//span[text()='Name']/../../following-sibling::tr/td[2]/span/a")).getText().trim();
			oASelFW.effecta("sendReport","Verify any content present under content section ", ContentTyp+" content Found", "Pass");

			ClickOnElement("//span[text()='Name']/../../following-sibling::tr/td[2]/span/a", "Content");
			ClickOnElement("//a[text()='"+LinkToTest1+"']", LinkToTest1);
			Thread.sleep(10000);
			String window[]= oASelFW.effectaArray("getAllWindowNames");

			oASelFW.effecta("selectWindow",window[1]);
			Thread.sleep(20000);
			oASelFW.driver.close();
			Thread.sleep(2000);
			System.out.println("Closed Window");
			oASelFW.effecta("sendReport","Verified and Closed the preview Content Window", "verified", "Pass");
			oASelFW.effecta("selectWindow",window[0]);
			switch_to_main_content();
			Actions act=new Actions(oASelFW.driver);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//img[@id='player-close-toolEl']"))).build().perform();
			act.click().build().perform();
			Thread.sleep(5000);

			//ClickOnElement("//img[@id='player-close-toolEl']", "Close Button");

			Switchframe();
			ClickOnElement("//a[text()='"+LinkToTest2+"']", LinkToTest2);
			Thread.sleep(5000);

			act.moveToElement(oASelFW.driver.findElement(By.xpath("//img[@class='x-tool-img x-tool-close']"))).build().perform();
			act.click().build().perform();

			oASelFW.effecta("sendReport","Verified and Closed the preview Content Window", "verified", "Pass");

		}
		else
		{
			oASelFW.effecta("sendReport","Verify any content present under content section ", "No Content Found, System msg: No items found", "Pass");
		}
	}

	public void ValidatePreviewContentForPluralsight(String RepositoryType, String LinkToTest1, String LinkToTest2) throws InterruptedException
	{
		ClickOnElement("//a[text()='"+RepositoryType+"']", RepositoryType);
		if(WaitOnElement("//span[text()='Name']/../../following-sibling::tr/td[2]/span/a", 05))
		{
			String ContentTyp = oASelFW.driver.findElement(By.xpath("//span[text()='Name']/../../following-sibling::tr/td[2]/span/a")).getText().trim();
			oASelFW.effecta("sendReport","Verify any content present under content section ", ContentTyp+" content Found", "Pass");

			ClickOnElement("//span[text()='Name']/../../following-sibling::tr/td[2]/span/a", "Content");
			ClickOnElement("//a[text()='"+LinkToTest1+"']", LinkToTest1);
			Thread.sleep(10000);


			Actions act=new Actions(oASelFW.driver);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//img[@id='player-close-toolEl']"))).build().perform();
			act.click().build().perform();
			Thread.sleep(5000);
			oASelFW.effecta("sendReport","Verified and Closed the preview Content Window", "verified", "Pass");
			//ClickOnElement("//img[@id='player-close-toolEl']", "Close Button");

			Switchframe();
			ClickOnElement("//a[text()='"+LinkToTest2+"']", LinkToTest2);
			Thread.sleep(5000);

			act.moveToElement(oASelFW.driver.findElement(By.xpath("//img[@class='x-tool-img x-tool-close']"))).build().perform();
			act.click().build().perform();

			oASelFW.effecta("sendReport","Verified and Closed the preview Content Window", "verified", "Pass");

		}
		else
		{
			oASelFW.effecta("sendReport","Verify any content present under content section ", "No Content Found, System msg: No items found", "Pass");
		}
	}
	public void RefreshRepository(String RepositoryType, String Button1) throws InterruptedException
	{
		ClickOnElement("//a[text()='"+RepositoryType+"']", RepositoryType);
		ClickOnButton(Button1);
	}


	public void ValidatePreviewContent_ViewResult(String RepositoryType,  String Button2) throws InterruptedException
	{
		ClickOnElement("//a[text()='"+RepositoryType+"']", RepositoryType);
		ClickOnElement("//span[text()='Name']/../../following-sibling::tr/td[2]/span/a", "Content");
		ClickOnButton(Button2);

	}

	public void AddNewContent(String ProdRepository) throws InterruptedException//Attach Content
	{
		switch_to_main_content();
		if(oASelFW.driver.findElement(By.xpath("//a[text()='Add Activities']")).isDisplayed()==true)
		{
			ClickOnElement("//a[text()='Add Activities']", "Add Activities");
			Actions act = new Actions(oASelFW.driver);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[text()='Attach Content']"))).click().build().perform();
			System.out.println("Clicked on Attached Window");
			Thread.sleep(3000);
			String window[]= oASelFW.effectaArray("getAllWindowNames");
			Thread.sleep(2000);
			oASelFW.effecta("selectWindow",window[1]);
			Thread.sleep(2000);
			ClickOnElement("//input[@title='Virtual Class Recording']", "Virtual Class Recording Radio button");
			Thread.sleep(1000);
			ClickOnElement("//input[@title='Formal Content']", "Formal Content Radio button");
			ClickOnElement("//label[contains(text(),'"+ProdRepository.trim()+"')]/../../../td[1]/a/img", ProdRepository.trim()+" type");
			Thread.sleep(2000);
			ClickOnElement("//input[@name='contentInventories']", ProdRepository.trim()+" Package type");
			ClickOnButton("Add Activities");
			ClickOnButton("Save");

			oASelFW.effecta("selectWindow",window[0]);

			switch_to_default_content();
		}
		else
		{
			ClickOnElement("//a[text()='Attach Content']", "Add Activity link");
			String window[]= oASelFW.effectaArray("getAllWindowNames");
			oASelFW.effecta("selectWindow",window[1]);
			Thread.sleep(2000);
			ClickOnElement("//input[@title='Virtual Class Recording']", "Virtual Class Recording Radio button");
			Thread.sleep(1000);
			ClickOnElement("//input[@title='Formal Content']", "Formal Content Radio button");
			ClickOnElement("//label[contains(text(),'"+ProdRepository.trim()+"')]/../../../td[1]/a/img", ProdRepository.trim()+" type");
			Thread.sleep(2000);
			ClickOnElement("//input[@name='contentInventories']", ProdRepository.trim()+" Package type");
			ClickOnButton("Add Activities");
			ClickOnButton("Save");
			oASelFW.effecta("selectWindow",window[0]);
			switch_to_default_content();

		}

	}


	public void SearchUserInManagePeople(String Username) throws InterruptedException
	{
		Switchframe();
		SetTextField("//label[text()='Username']/../../following-sibling::td/input", Username, "Username");
		ClickOnButton("Search");
		switch_to_default_content();
	}
	public void DropLearnerFromRoaster() throws InterruptedException
	{
		Switchframe();
		ClickOnElement("//a[text()='Edit Profile Information']", "Edit Profile Information");
		ClickOnElement("//span[text()='Profile Quicklinks']/../../../tr[6]/td/a[text()='Enrollments']", "Enrollments");
		ClickOnElement("//a[text()='Drop']", "Drop");
		ClickOnButton("Drop");
		String window[]= oASelFW.effectaArray("getAllWindowNames");
		Thread.sleep(2000);
		oASelFW.effecta("selectWindow",window[1]);
		SetTextField("//textarea[@name='reason']", "test reason", "reason text box");
		ClickOnButton("Save");
		oASelFW.effecta("selectWindow",window[0]);
		switch_to_default_content();
	}
	public void clickOnActAsProxy()
	{

		try {
			Switchframe();
			ClickOnElement("//a[text()='Act as Proxy']", "Act as Proxy");
			Thread.sleep(2000);
			String window[]= oASelFW.effectaArray("getAllWindowNames");
			Thread.sleep(2000);
			oASelFW.effecta("selectWindow",window[1]);
			SetTextField("//textarea[@name='reason']", "test reason", "reason text box");
			ClickOnButton("Save");
			oASelFW.effecta("selectWindow",window[0]);
			switch_to_default_content();

		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void DropLearnerFromRoaster2(String CourseName) throws InterruptedException
	{
		Switchframe();
		ClickOnElement("//a[text()='Edit Profile Information']", "Edit Profile Information");
		ClickOnElement("//span[text()='Profile Quicklinks']/ancestor::tbody[1]/descendant::a[text()='Enrollments']", "Enrollments");

		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//a[text()='"+CourseName+"']/../../following-sibling::td[14]/span/a[text()='Drop']")))
		{
			ClickOnElement("//a[text()='"+CourseName+"']/../../following-sibling::td[14]/span/a[text()='Drop']", "Drop link");
			ClickOnButton("Drop");
			Thread.sleep(2000);
			String window[] = oASelFW.effectaArray("getAllWindowNames");
			oASelFW.effecta("selectWindow",window[window.length-1]);
			Thread.sleep(1000);
			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='Save']")))
			{
				System.out.println("in select domain window");
				oASelFW.effecta("type","//span[text()='Reason*']/../following-sibling::td//textarea","test","Delete Mesg");
				oASelFW.effecta("clickAndWait","//span[text()='Save']","Save Button","Pass");
				Thread.sleep(4000);
			}
			else if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='OK']")))
			{
				oASelFW.effecta("clickAndWait","//span[text()='OK']","OK Button","Pass");
				Thread.sleep(4000);
			}
			oASelFW.effecta("selectWindow",window[0]);
			Thread.sleep(4000);
			handle_Alerts_PopUP();

			/*String window[]= oASelFW.effectaArray("getAllWindowNames");
		Thread.sleep(2000);
		oASelFW.effecta("selectWindow",window[1]);
		SetTextField("//textarea[@name='reason']", "test reason", "reason text box");
		ClickOnButton("Save");
		oASelFW.effecta("selectWindow",window[0]);*/
		}
		else{

		}
		switch_to_default_content();
	}

	public void goToUserLinks(String QuickLinks)
	{
		try {
			Switchframe();
			ClickOnElement("//a[text()='Edit Profile Information']", "Edit Profile Information");
			Thread.sleep(2000);
			ClickOnElement("//a[text()='"+QuickLinks+"']", QuickLinks);
			switch_to_default_content();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
	public void VerifyCompltedCourse(String actions) throws InterruptedException
	{
		Switchframe();

		Actions act = new Actions(oASelFW.driver);

		//ClickOnElement("//a[@title='Actions']", "Actions");
		act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[@title='Actions']"))).build().perform();
		act.click().build().perform();
		Thread.sleep(5000);
		WebElement element = oASelFW.driver.findElement(By.xpath("//a[@title='"+actions+"']"));
		JavascriptExecutor executor = (JavascriptExecutor)oASelFW.driver;
		executor.executeScript("arguments[0].click();", element);

		//oASelFW.driver.findElement(By.xpath("//a[@title='Print Certificate']")).click();
		//act.doubleClick(oASelFW.driver.findElement(By.xpath("//a[@title='Print Certificate']"))).build().perform();
		Thread.sleep(5000);
		oASelFW.effecta("sendReportWithOutExit","Validate If user can Clicked on "+ actions," succesfully validated, User has clicked on "+actions,"Pass");
		Thread.sleep(3000);
		switch_to_default_content();
	}


	public void verifyUserCanPrint() throws InterruptedException
	{

		/*String window[]= oASelFW.effectaArray("getAllWindowNames");
		Thread.sleep(2000);
		oASelFW.effecta("selectWindow",window[1]);
		if(Boolean.parseBoolean((oASelFW.effecta("isElementPresent","//button[text()='Print']"))))
		{
			oASelFW.effecta("sendReportWithOutExit","Validate If user can Print the certificate"," yes, User can print the certificate ","Pass");
		}

		String window1[]= oASelFW.effectaArray("getAllWindowNames");
		Thread.sleep(2000);
		oASelFW.effecta("selectWindow",window[0]);*/
		Actions act = new Actions(oASelFW.driver);
		String[] window1 = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window1[window1.length-1]);
		Thread.sleep(7000);
		act.sendKeys(Keys.chord(Keys.CONTROL, "w")).build().perform();
		Thread.sleep(5000);
		oASelFW.effecta("selectWindow",window1[window1.length-2]);
		Thread.sleep(5000);
		act.sendKeys(Keys.chord(Keys.CONTROL, "w")).build().perform();
		Thread.sleep(3000);
		oASelFW.effecta("selectWindow",window1[window1.length-3]);
		Thread.sleep(4000);




	}
	public void VerifyCertAttributes() throws InterruptedException
	{

		String window[]= oASelFW.effectaArray("getAllWindowNames");
		Thread.sleep(2000);
		oASelFW.effecta("selectWindow",window[1]);
		ClickOnElement("//button[text()='Cancel']", "Cancel Button");
		switch_to_default_content();
		Thread.sleep(5000);
		if(Boolean.parseBoolean((oASelFW.effecta("isElementPresent","//img[@id='Picture 1']"))))
		{
			oASelFW.effecta("sendReportWithOutExit","Validate if HIVE banner is present"," Validated, HIVE banner is present ","Pass");
		}

		else{
			oASelFW.effecta("sendReportWithOutExit","  Validate if HIVE banner is present"," Validate, HIVE banner is not present ","fail");
		}
		if(Boolean.parseBoolean((oASelFW.effecta("isElementPresent","//p[contains(text(),'DATE OF COMPLETION:')]/span"))))
		{
			String DOC= getText("//p[contains(text(),'DATE OF COMPLETION:')]/span");
			oASelFW.effecta("sendReportWithOutExit","Validate If date of completion is showing up in certificate"," Date of completion is found as: "+DOC,"Pass");
		}
		else
		{
			oASelFW.effecta("sendReportWithOutExit","Validate If date of completion is showing up in certificate"," Date of completion not found ","Fail");
		}


	}

	public void AddNewContent1(ArrayList<String> ProdRepository) throws InterruptedException//Attach Content
	{
		for(int i=0;i<ProdRepository.size(); i++)
		{
			switch_to_main_content();
			if(oASelFW.driver.findElement(By.xpath("//a[text()='Add Activities']")).isDisplayed()==true)
			{
				ClickOnElement("//a[text()='Add Activities']", "Add Activities");
				Actions act = new Actions(oASelFW.driver);
				act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[text()='Attach Content']"))).click().build().perform();
				System.out.println("Clicked on Attached Window");
				Thread.sleep(3000);
				String window[]= oASelFW.effectaArray("getAllWindowNames");
				Thread.sleep(2000);
				oASelFW.effecta("selectWindow",window[1]);
				Thread.sleep(2000);
				ClickOnElement("//input[@title='Virtual Class Recording']", "Virtual Class Recording Radio button");
				Thread.sleep(1000);
				ClickOnElement("//input[@title='Formal Content']", "Formal Content Radio button");
				ClickOnElement("//label[contains(text(),'"+ProdRepository.get(i).trim()+"')]/../../../td[1]/a/img", ProdRepository.get(i).trim()+" type");
				Thread.sleep(2000);
				ClickOnElement("//input[@name='contentInventories']", ProdRepository.get(i).trim()+" Package type");
				ClickOnButton("Add Activities");
				ClickOnButton("Save");

				oASelFW.effecta("selectWindow",window[0]);

				switch_to_default_content();
			}
			else
			{
				ClickOnElement("//a[text()='Attach Content']", "Add Activity link");
				String window[]= oASelFW.effectaArray("getAllWindowNames");
				oASelFW.effecta("selectWindow",window[1]);
				Thread.sleep(2000);
				ClickOnElement("//input[@title='Virtual Class Recording']", "Virtual Class Recording Radio button");
				Thread.sleep(1000);
				ClickOnElement("//input[@title='Formal Content']", "Formal Content Radio button");
				ClickOnElement("//label[contains(text(),'"+ProdRepository.get(i).trim()+"')]/../../../td[1]/a/img", ProdRepository.get(i).trim()+" type");
				Thread.sleep(2000);
				ClickOnElement("//input[@name='contentInventories']", ProdRepository.get(i).trim()+" Package type");
				ClickOnButton("Add Activities");
				ClickOnButton("Save");
				oASelFW.effecta("selectWindow",window[0]);
				switch_to_default_content();

			}
		}

	}

	public void AddInstructor(String InstructorName) throws InterruptedException
	{
		Switchframe();
		ClickOnElement("//a[text()='Add Resource']", "Add Rsource Link");
		String window[]= oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window[1]);
		selectBytext("//select[@name='resourceType']", "Person");
		Thread.sleep(2000);
		selectBytext("//select[@name='purpose']", "1- Instructor");
		Thread.sleep(2000);

		ClickOnButton("Next ->");
		oASelFW.driver.findElement(By.xpath("//input[@name='searchByLocationbookkeep']")).clear();
		Thread.sleep(1000);
		SetTextField("//input[@name='resourceName']", InstructorName, "Instructor Name");
		ClickOnButton("Search");
		Thread.sleep(1000);
		ClickOnElement("//span[text()='"+InstructorName+"']/../../td[2]/input", "Select instructor radio Buton");
		ClickOnButton("Next ->");
		ClickOnElement("//input[@name='IgnoreConflicts']", "Ignore Scheduling Conflicts Check box");
		ClickOnButton("Done");
		oASelFW.effecta("selectWindow",window[0]);
		switch_to_default_content();
	}
	public void AddInstructorToVILT(String InstructorName) throws InterruptedException
	{
		Switchframe();
		ClickOnElement("//a[text()='Add Resource']", "Add Rsource Link");
		String window[]= oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window[1]);
		selectBytext("//select[@name='resourceType']", "Person");
		Thread.sleep(2000);
		selectBytext("//select[@name='purpose']", "1- Instructor");
		Thread.sleep(2000);

		selectBytext("//select[@name='instructorRole']", "Leader");
		Thread.sleep(2000);
		ClickOnButton("Next ->");
		oASelFW.driver.findElement(By.xpath("//input[@name='searchByLocationbookkeep']")).clear();
		Thread.sleep(1000);
		SetTextField("//input[@name='resourceName']", InstructorName, "Instructor Name");
		ClickOnButton("Search");
		Thread.sleep(1000);
		ClickOnElement("//span[text()='"+InstructorName+"']/../../td[2]/input", "Select instructor radio Buton");

		ClickOnButton("Next ->");
		ClickOnElement("//input[@name='IgnoreConflicts']", "Ignore Scheduling Conflicts Check box");
		ClickOnButton("Done");
		oASelFW.effecta("selectWindow",window[0]);
		switch_to_default_content();
	}
	public void SelectSabaVLE()
	{
		switch_to_main_content();
		ClickOnElement("//img[@alt='Pick VLE Provider']", "VLE provider image");
		String window[]= oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window[1]);
		ClickOnElement("//span[text()='Saba Classroom']/../../td[2]/a/img", "Select Saba Classroom");
		oASelFW.effecta("selectWindow",window[0]);
		switch_to_default_content();
	}
	public void AddLearner(String LearnerName) throws InterruptedException
	{
		Switchframe();
		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='Results']")))
		{
			ClickOnButton("Results");
			Thread.sleep(5000);
		}else
		{
			ClickOnButton("Roster");
			Thread.sleep(5000);
		}
		//ClickOnElement("//span[@id='link-1097-btnIconEl']", "Add learner logo");
		ClickOnElement("//label[contains(text(),'Edit selected')]/following::div[1]/span/div/a[1]", "Add learner logo");
		//oASelFW.effecta("click","//span[@id='link-1101-btnIconEl']&&//span[@id='link-1102-btnIconEl']","Add Learner","Pass");
		Thread.sleep(3000);
		SetTextField("//label[contains(text(),'Search for')]/../following-sibling::td//input", LearnerName, "Search For Learner text Field");
		Thread.sleep(3000);
		ClickOnElement("//label[text()='Search for people']/../../../../../a[1]", "Search Learner");	
		Thread.sleep(4000);
		//ClickOnButton("//a[contains(text(),'"+LearnerName.trim()+"')]/../../../../following-sibling::td[2]//button/span[text()='Select']");
		Actions act = new Actions(oASelFW.driver);
		Thread.sleep(3000);
		act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[contains(text(),'"+LearnerName.trim()+"')]/../../../../following-sibling::td[2]//button/span[text()='Select']"))).build().perform();
		Thread.sleep(3000);
		act.click().build().perform();
		Thread.sleep(4000);

		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//a[text()='Continue']")))
		{		
			Thread.sleep(2000);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[text()='Continue']"))).build().perform();
			Thread.sleep(1000);
			act.click().build().perform();
			Thread.sleep(3000);
			ClickOnElement("//span[text()='Add']/following-sibling::span", "Add Button");
			Thread.sleep(5000);
		}
		else
		{
			Thread.sleep(2000);
			ClickOnElement("//span[text()='Add']/following-sibling::span", "Add Button");
			Thread.sleep(6000);

		}
		switch_to_default_content();

	} 



	public void AddLearnerToVlt(String LearnerName) throws InterruptedException
	{
		Switchframe();
		ClickOnButton("Roster");
		Thread.sleep(10000);
		ClickOnElement("//span[@id='link-1098-btnIconEl']", "Add learner logo");
		SetTextField("//input[@name='textfield-1159-inputEl']", LearnerName, "Search For Learner text Field");
		Thread.sleep(1000);
		ClickOnElement("//label[text()='Search for people']/../../../../../a[1]", "Search Learner");	
		ClickOnButton("Select");
		Thread.sleep(5000);
		ClickOnElement("//span[text()='Add']/following-sibling::span", "Add Button");
		Thread.sleep(5000);

		switch_to_default_content();
	}

	public void  AddLearnerToWaitList(String[] list) throws InterruptedException
	{
		Switchframe();
		ClickOnButton("Roster");
		Thread.sleep(10000);
		ClickOnElement("//label[text()='Edit selected for:']/../../../following-sibling::div/span/div/a[1]", "Add learner logo");
		Thread.sleep(2000);
		for (String learnerName:list)
		{

			SetTextField("//label[contains(text(),'Search for people')]/../following-sibling::td/input", learnerName, "Search For Learner text Field");
			Thread.sleep(1000);
			ClickOnElement("//label[text()='Search for people']/../../../../../a[1]", "Search Learner");	
			ClickOnElement("//a[text()='"+learnerName+"']/ancestor::tr[1]/descendant::span[text()='Select']", "Select button");
			Thread.sleep(2000);
			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//a[text()='Continue']")))
			{
				ClickOnElement("//a[text()='Continue']", "Continue");
			}
			Thread.sleep(1000);
		}
		Thread.sleep(4000);
		ClickOnElement("//span[text()='Add']/following-sibling::span", "Add Button");
		Thread.sleep(5000);

		switch_to_default_content();
	}

	public void  AddLearnerToAfterDeletion(String[] list) throws InterruptedException
	{
		Switchframe();
		ClickOnElement("//label[text()='Edit selected for:']/../../../following-sibling::div/span/div/a[1]", "Add learner logo");
		Thread.sleep(2000);
		for (String learnerName:list)
		{

			SetTextField("//label[contains(text(),'Search for people')]/../following-sibling::td/input", learnerName, "Search For Learner text Field");
			Thread.sleep(1000);
			ClickOnElement("//label[text()='Search for people']/../../../../../a[1]", "Search Learner");	
			ClickOnElement("//a[text()='"+learnerName+"']/ancestor::tr[1]/descendant::span[text()='Select']", "Select button");
			Thread.sleep(2000);
			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//a[text()='Continue']")))
			{
				ClickOnElement("//a[text()='Continue']", "Continue");
			}
			Thread.sleep(1000);
		}
		Thread.sleep(4000);
		ClickOnElement("//span[text()='Add']/following-sibling::span", "Add Button");
		Thread.sleep(5000);

		switch_to_default_content();
	}

	public void changeStatusOflearner(String updatedstatus) throws InterruptedException
	{
		Switchframe();
		ClickOnElement("//a[text()='Registered']", "Registeration Status");
		Thread.sleep(3000);
		ClickOnElement("//label[text()='Update status']/ancestor::td/descendant::td[1]/input", "Input box");
		/*Actions act = new Actions(oASelFW.driver);
		act.moveToElement(oASelFW.driver.findElement(By.xpath("//label[text()='Update status']/ancestor::td/descendant::td[1]/input"))).click().build();*/
		Thread.sleep(3000);
		oASelFW.driver.findElement(By.xpath("//li[text()='"+updatedstatus+"']")).click();
		//act.moveToElement(oASelFW.driver.findElement(By.xpath("//li[text()='Move to the Waitlist with the priority:']"))).click().build().perform();
		Thread.sleep(2000);
		SetTextField("//textarea[@name='reason']", "Test reason", "Reason text Box");
		ClickOnElement("//label[text()='Reason']/ancestor::div[5]/descendant::span[text()='Save']/following-sibling::span", "Save");
		Thread.sleep(5000);

		switch_to_default_content();

	}

	public void changeStatusOflearnerWithName(String updatedstatus, String username) throws InterruptedException
	{
		Switchframe();
		//ClickOnElement("//a[contains(text(),'"+username.trim()+"')]/../../../following-sibling::td[1]//a", "Registeration Status");
		Thread.sleep(5000);
		oASelFW.effecta("clickAndWait","//a[contains(text(),'"+username.trim()+"')]/../../../following-sibling::td[1]//a&&//a[contains(text(),'"+username.trim()+"')]/../../../following-sibling::td[2]//a",username,"Pass");
		Thread.sleep(3000);
		ClickOnElement("//label[text()='Update status']/ancestor::td/descendant::td[1]/input", "Input box");
		/*Actions act = new Actions(oASelFW.driver);
		act.moveToElement(oASelFW.driver.findElement(By.xpath("//label[text()='Update status']/ancestor::td/descendant::td[1]/input"))).click().build();*/
		Thread.sleep(3000);
		oASelFW.driver.findElement(By.xpath("//li[text()='"+updatedstatus+"']")).click();
		//act.moveToElement(oASelFW.driver.findElement(By.xpath("//li[text()='Move to the Waitlist with the priority:']"))).click().build().perform();
		Thread.sleep(4000);
		SetTextField("//textarea[@name='reason']", "Test reason", "Reason text Box");
		Thread.sleep(2000);
		ClickOnElement("//label[text()='Reason']/ancestor::div[5]/descendant::span[text()='Save']/following-sibling::span", "Save");
		Thread.sleep(8000);

		switch_to_default_content();

	}



	public void EditTheContent() throws InterruptedException
	{
		Thread.sleep(5000);
		ClickOnElement("//a[text()='Actions']", "Actions link");
		Thread.sleep(1000);
		Actions act = new Actions(oASelFW.driver);
		act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[text()='Delete Content Module']"))).build().perform();
		Thread.sleep(2000);
		acceptAlert();
		switch_to_default_content();
	}




	public void login_email(String username,String password)throws Exception
	{      
		String url = "https://mail.vmware.com/owa/#path=/mail";
		oASelFW.driver.get(url);
		oASelFW.driver.manage().window().maximize();
		oASelFW.driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
		oASelFW.effecta("sendReportWithOutExit","Launching main page","Successfully launched main page: "+url,"Pass");

		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//table[@id='"+username+"_vmware_com']")))
		{
			oASelFW.effecta("clickAndWait","//table[@id='"+username+"_vmware_com']",username);
			Thread.sleep(15000);
		}

		else if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","id=cred_userid_inputtext")))
		{
			oASelFW.effecta("verifyElementPresent","id=cred_userid_inputtext","Username");
			oASelFW.effecta("type","id=cred_userid_inputtext",username+"@vmware.com","Username");
			oASelFW.driver.findElement(By.id("cred_userid_inputtext")).sendKeys(Keys.TAB);
			Thread.sleep(10000);

			System.out.println("Before Robot");

			Robot robot = new Robot();
			robot.delay(10000);
			robot.keyPress(java.awt.event.KeyEvent.VK_ESCAPE);
			robot.keyRelease(java.awt.event.KeyEvent.VK_ESCAPE);
			String authURL=oASelFW.driver.getCurrentUrl();
			System.out.println(authURL);

			/*Thread.sleep(4000);
	                     String[] newURL=authURL.split("https://");

	                     String urlAuthenticate = "https://"+username+":"+password+"@";
	                     oASelFW.driver.navigate().to(newURL[1]+urlAuthenticate);*/
			Thread.sleep(5000);

			System.out.println("On RSA page");

			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//input[@id='username']")))
			{
				System.out.println("RSA ID pwd");

				SetTextField("//input[@id='username']", username, "RSA User Id");
				Thread.sleep(2000);
				SetTextField("//input[@id='password']", username, "RSA Password");
				Thread.sleep(2000);
				oASelFW.effecta("click","//input[@id='signIn']","Sign in");
				Thread.sleep(20000);
			}
		}
	}

	public String getText(String xpath)
	{
		String text = null;
		if(WaitOnElement(xpath, 05))
		{
			text=oASelFW.driver.findElement(By.xpath(xpath)).getText();
			if(text!=null)
			{
				oASelFW.effecta("sendReportWithOutExit","get text","Successfully Fetched text as "+text,"Pass");
			}

			else
			{
				oASelFW.effecta("sendReportWithOutExit","get text","Successfully Fetched text as " +text,"fail");
			}
		}
		else
		{
			oASelFW.effecta("sendReportWithOutExit","get text","invalid xpath ","fail");
		}
		return text;


	}
	public String getValue(String xpath)
	{
		String text = null;
		if(WaitOnElement(xpath, 05))
		{
			text=oASelFW.driver.findElement(By.xpath(xpath)).getAttribute("value");
			if(text!=null)
			{
				oASelFW.effecta("sendReportWithOutExit","get text","Successfully Fetched text as "+text,"Pass");
			}

			else
			{
				oASelFW.effecta("sendReportWithOutExit","get text","Successfully Fetched text as " +text,"fail");
			}
		}
		else
		{
			oASelFW.effecta("sendReportWithOutExit","get text","invalid xpath ","fail");
		}
		return text;
	}
	public void email_Search_Using_text(String sub)throws Exception
	{

		/*WebElement search_mail = oASelFW.driver.findElement(By.xpath("//button[@autoid='_n_a']"));    
	              Actions act = new Actions(oASelFW.driver);
	              act.moveToElement(search_mail).click().build().perform();
	              oASelFW.effecta("sendReportWithOutExit","Clicking button/link/image: Search_mail","Successfully clicked button/link/image: Search_mail","Pass");
	              Thread.sleep(3000);
	              WebElement omResults2 = oASelFW.driver.findElement(By.xpath("//input[@autoid='_n_7']"));     
	              act.sendKeys(omResults2,sub);
	              oASelFW.effecta("sendReportWithOutExit","Setting value of: Search_mail","Search_mail is set to value: "+sub,"Pass");
	              act.sendKeys(Keys.ENTER).build().perform();*/

		System.out.println("In email search****************");
		Thread.sleep(25000);
		WebDriverWait wait = new WebDriverWait(oASelFW.driver,60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@autoid='_n_4']")));
		WebElement search_mail = oASelFW.driver.findElement(By.xpath("//button[@autoid='_n_4']"));    
		Actions act = new Actions(oASelFW.driver);
		act.moveToElement(search_mail).click().build().perform();
		System.out.println("after search click*******************");
		oASelFW.effecta("sendReportWithOutExit","Clicking button/link/image: Search_mail","Successfully clicked button/link/image: Search_mail","Pass");
		Thread.sleep(5000);

		oASelFW.driver.findElement(By.xpath("//input[contains(@aria-label,'Search mail and people, type your search term then press enter to search.')]")).sendKeys(sub);
		System.out.println("after search type*******************");
		oASelFW.driver.findElement(By.xpath("//input[contains(@aria-label,'Search mail and people, type your search term then press enter to search.')]")).sendKeys(Keys.ENTER);
		Thread.sleep(8000);

		oASelFW.driver.findElement(By.xpath("//div[@autoid='_lvv_3']")).click();
		oASelFW.effecta("sendReportWithOutExit","verify the "+ sub,"Verified, found the "+ sub,"Pass");

	}

	public void Validate_FieldsOFWD()
	{
		Switchframe();
		ClickOnElement("//span[text()='Internal Organization Number']/../../following-sibling::tr[2]/td[2]/span/a", "Internal Organisation");
		String orgName		= getValue("//span[text()='Internal Organization*']/../following-sibling::td/input");
		String OrgNum		= getValue("//span[text()='Internal Organization Number*']/../following-sibling::td/input");
		String PartnerOrg	= getValue("//span[text()='Parent Organization*']/../following-sibling::td/input");
		switch_to_default_content();
	}

	public void ValidteNameParentOrgDefaultCurrencyFields(String OrgName, String OrgNum) throws InterruptedException 
	{
		Switchframe();
		ClickOnButton("Save");
		String AlertTxt= getTextFromAlert();
		acceptAlert();
		oASelFW.effecta("sendReportWithOutExit","verify the response when Internal org field left blank ","Verified, a validation message is thrown by the system "+ AlertTxt,"Pass");
		oASelFW.effecta("sendReportWithOutExit","verify the response when parent org field left blank" ,"Verified, a validation message is thrown by the system "+ AlertTxt, "Pass");
		oASelFW.effecta("sendReportWithOutExit","verify the response when Default currency field left blank","Verified, a validation message is thrown by the system "+ AlertTxt,"Pass");
		SetTextField("//input[@name='comp_name']", OrgName, "Company Name");
		SetTextField("//input[@name='comp_number']", OrgNum, "organization number");
		SetTextField("//input[@name='comp_parent_idbookkeep']", "Arc", "Parent Org");
		ClickOnElement("//span[text()='Parent Organization*']/../following-sibling::td/span/img", "Search Logo");
		Thread.sleep(2000);
		WebElement prntOrg = oASelFW.driver.findElement(By.xpath("//div[text()='Architecture and Solutions-CA10']"));
		Actions act = new Actions(oASelFW.driver);
		act.moveToElement(prntOrg).click().build().perform();
		System.out.println("Selected Parent org");
		oASelFW.effecta("sendReportWithOutExit","Clicking button/link/image from list: Parent Organization","Successfully clicked button/link/image: Parent Organization","Pass");
		oASelFW.effecta("sendReportWithOutExit","Clicking button/link/image from list: Default Currency*","Successfully clicked button/link/image: Default Currency","Pass");
		ClickOnButton("Save");
		switch_to_default_content();
	}
	public void getUrl(String courseStaticLink) throws IOException
	{
		String courseUrl = courseStaticLink;
		System.out.println(courseUrl);


		courseUrl = courseUrl.replace("mylearn", "mylearnci");
		System.out.println("URL after change is--"+courseUrl);
		oASelFW.driver.get(courseUrl);

		oASelFW.effecta("sendReport","Verify if the user is navigated to portal","User successfully navigated to Portal "+courseUrl,"Pass");

	}

	public void LaunchTheClass()
	{

		ClickOnElement("//a[text()='Test Course for ASLI 99']/ancestor::div/descendant::span[contains(text(),'Launch')]", "Launch Button");

	}

	public void createNewAudience(String audienceType) throws InterruptedException
	{
		Switchframe();
		ClickOnElement("//a[text()='"+audienceType+"']", audienceType);
		switch_to_default_content();
	}
	public String addCriteria(String Value,String Value2, String Value3, String Value4) throws InterruptedException{
		Switchframe();
		/*ClickOnButton("Add criteria");
		Thread.sleep(1000);
		ClickOnElement("//span[text()='Group 1']/ancestor::div[6]/div[2]/descendant::div[15]", "Second drop Down");
		Thread.sleep(3000);
		ClickOnElement("//li[text()='"+Value+"']", " "+Value+" from drop Down");
		ClickOnElement("//span[text()='Group 1']/ancestor::div[6]/div[2]/descendant::div[19]", "Third Drop Down");
		Thread.sleep(3000);
		ClickOnElement("//li[text()='"+Value2+"']", " "+Value2+" from drop Down");
		ClickOnElement("//span[text()='Group 1']/ancestor::div[6]/div[2]/descendant::div[25]", "Fourth Drop Down");
		Thread.sleep(3000);
		ClickOnElement("//span[contains(text(), '"+Value3+"')]", " "+Value3+" from drop Down");
		ClickOnElement("//span[text()='Group 1']/ancestor::div[6]/div[2]/descendant::div[31]", "Fifth Drop Down");
		Thread.sleep(3000);
		ClickOnElement("//span[contains(text(), '"+Value4+"')]/../span[1]/img", " "+Value4+" from drop Down");
		ClickOnButton("Save");*/
		Thread.sleep(5000);


		ClickOnButton("Preview");
		Thread.sleep(5000);
		String UserName=getText("//label[contains(text(),'Displaying 1 to 5')]");
		ClickOnElement("//span[text()='Close']/../span[2]", "Close Button");
		oASelFW.effecta("sendReportWithOutExit","get total records Fetched","total records fetched are: "+ UserName.replace("Displaying 1 to 5 of", "").trim(),"Pass");
		switch_to_default_content();
		return UserName.replace("Displaying 1 to 5 of", "").trim();

	}

	public void SearchPerspectiveRules(String[] name) throws InterruptedException
	{
		Switchframe();
		for(String ruleName:name)
		{
			SetTextField( "//input[@name='PrescriptionRule_query_var_name$kString$kLike']", ruleName, "Name text field");
			ClickOnButton("Search");
			if(Boolean.parseBoolean((oASelFW.effecta("isElementPresent","//a[text()='"+ruleName.trim()+"']"))))
			{
				oASelFW.effecta("sendReportWithOutExit","Validate If the Rule name exists","Successfully found the rule "+ruleName.replaceAll("[^a-zA-Z0-9]+", "").trim()+" name in record","Pass");
			}
			else
			{
				oASelFW.effecta("sendReportWithOutExit","Validate If the Rule name exists","Sorry, Could not found the rule "+ruleName.replaceAll("[^a-zA-Z0-9]+", "").trim()+" name in record","fail");
			}
		}
		switch_to_default_content();
	}

	public void SearchPerspectiveRules2(String ruleName) throws InterruptedException
	{
		Switchframe();

		SetTextField( "//input[@name='PrescriptionRule_query_var_name$kString$kLike']", ruleName, "Name text field");
		ClickOnButton("Search");
		if(Boolean.parseBoolean((oASelFW.effecta("isElementPresent","//a[text()='"+ruleName.trim()+"']"))))
		{
			oASelFW.effecta("sendReportWithOutExit","Validate If the Rule name exists","Successfully found the rule "+ruleName.replaceAll("[^a-zA-Z0-9]+", "").trim()+" name in record","Pass");
		}
		else
		{
			oASelFW.effecta("sendReportWithOutExit","Validate If the Rule name exists","Sorry, Could not found the rule "+ruleName.replaceAll("[^a-zA-Z0-9]+", "").trim()+" name in record","fail");
		}

		switch_to_default_content();
	}
	public void  addLearnerIfWaitListisFull(String[] list, String text, String text2) throws InterruptedException
	{
		Actions act = new Actions(oASelFW.driver);
		Thread.sleep(2000);
		ClickOnButton("Roster");
		Thread.sleep(5000);
		for (String learnerName:list)
		{
			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//label[text()='Edit selected for:']/../../../following-sibling::div/span/div/a[1]")))
			{
				ClickOnElement("//label[text()='Edit selected for:']/../../../following-sibling::div/span/div/a[1]", "Add learner logo");
				Thread.sleep(2000);
				SetTextField("//label[contains(text(),'Search for people')]/../following-sibling::td/input", learnerName, "Search For Learner text Field");
				Thread.sleep(1000);
			}
			else if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//div[@id='assignLE']")))
			{	
				Thread.sleep(1000);
				SetTextField("//label[contains(text(),'Search for people')]/../following-sibling::td/input", learnerName, "Search For Learner text Field");
				Thread.sleep(1000);
			}
			//SetTextField("//label[contains(text(),'Search for people')]/../following-sibling::td/input", learnerName, "Search For Learner text Field");
			//Thread.sleep(1000);
			ClickOnElement("//label[text()='Search for people']/../../../../../a[1]", "Search Learner");	
			ClickOnElement("//a[text()='"+learnerName+"']/ancestor::tr[1]/descendant::span[text()='Select']", "Select button");
			Thread.sleep(2000);
			if(Boolean.parseBoolean((oASelFW.effecta("isElementPresent","//li[contains(text(),'"+text2+"')]"))))
			{
				oASelFW.effecta("sendReportWithOutExit","Validate If the message: "+text2+" appears","Successfully found the desired message","Pass");
				ClickOnElement("//a[text()='Continue']", "Continue");
				Thread.sleep(4000);
				act.moveToElement(oASelFW.driver.findElement(By.xpath("//div[@id='assignLE_header_hd']/../descendant::div[2]/img"))).build().perform();
				act.click().build().perform();
				Thread.sleep(2000);
			}
			else if(Boolean.parseBoolean((oASelFW.effecta("isElementPresent","//li[contains(text(),'"+text+"')]"))))
			{
				oASelFW.effecta("sendReportWithOutExit","Validate If the message: "+text+" appears","Successfully found the desired message","Pass");
				ClickOnElement("//span[text()='Cancel']/following-sibling::span", "Cancel Button");
				Thread.sleep(4000);
			}
			else
			{
				Thread.sleep(4000);
				ClickOnElement("//span[text()='Add']/following-sibling::span", "Add Button");
				Thread.sleep(5000);
			}

			Thread.sleep(1000);
		}
		switch_to_default_content();
	}
	public void VerifyText(String text) throws InterruptedException
	{
		Thread.sleep(2000);
		if(Boolean.parseBoolean((oASelFW.effecta("isElementPresent","//li[contains(text(),' "+text+"')]"))))
		{
			oASelFW.effecta("sendReportWithOutExit","Validate If the message: "+text+" appears","Successfully found the desired message","Pass");
		}
		else
		{
			oASelFW.effecta("sendReportWithOutExit","Validate If the message: "+text+" appears","No message found","Fail");
		}
	}

	public void validatePortletsAreAvailable(String[] portletName)
	{
		for(String pname:portletName)
		{
			if(Boolean.parseBoolean((oASelFW.effecta("isElementPresent","//span[text()='"+pname+"']"))))
			{
				oASelFW.effecta("sendReportWithOutExit","Validate If portlet  "+pname+" is visible on home screen","Successfully found the portlet","Pass");
			}
			else
			{
				oASelFW.effecta("sendReportWithOutExit","Validate If portlet  "+pname+" is visible on home screen","portlet not found","Fail");
			}
		}
	}

	public void validatePortletCanBeRemoved(String[] portletName)
	{
		for(String pname:portletName)
		{
			if(Boolean.parseBoolean((oASelFW.effecta("isElementPresent","//span[text()='"+pname+"']/ancestor::div[2]/descendant::img[@title='Close']"))))
			{
				oASelFW.effecta("sendReportWithOutExit","Validate If user can close "+pname+" From dashboard","User can close ","Pass");
			}
			else
			{
				oASelFW.effecta("sendReportWithOutExit","Validate If user can close "+pname+" From dashboard","User can close, No close button found","Fail");
			}
		}
	}

	public void verifyDownloads()
	{
		System.out.println("In saba signout");
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//img[@title='User Options']")));

		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//img[@title='User Options']"))){
			oASelFW.effecta("click","//img[@title='User Options']","User Options");

			oASelFW.effecta("waitForElementPresent","//span[contains(text(),'Sign out')]","60");
			oASelFW.effecta("click","//span[contains(text(),'Downloads')]","Downloads");
			System.out.println("On Downloads ");
			if(Boolean.parseBoolean((oASelFW.effecta("isElementPresent","//div[text()='Saba Cloud mobile application for iOS and Android smartphones and tablets']"))))
			{
				oASelFW.effecta("sendReportWithOutExit","Validate msg on the Downloads page ","Msg found: " +
						"Saba Cloud mobile application for iOS and Android smartphones and tablets"+
						"Install the Saba Cloud native application for iOS and Android smartphones and tablets to stay connected and access learning in the palm of your hand at anytime from anywhere."+
						"To install the Saba Cloud mobile application for your device:"+
						"1.	Download the Saba Cloud application to your mobile device from the App Store on your device or through iTunes (for iOS) and Google Play (for Android) on your computer."+
						"2. Log in to the application using your Saba Cloud credentials. When prompted to enter the site name, use <vmwareqa>."+
						"Please send any feedback or suggestions to feedback@saba.com. ","Pass");
			}
		}
	}

	public void verifyEditLinksOnMyProfile(String[] tabNames)
	{

		ClickOnElement("//span[text()='Profile']", "Profile Link ");

		for(String pname:tabNames)
		{
			if(Boolean.parseBoolean((oASelFW.effecta("isElementPresent","//div[text()='"+pname+"']/ancestor::legend/following-sibling::div/descendant::span[text()='Edit']"))))
			{
				oASelFW.effecta("sendReportWithOutExit","Validate If Edit link is found for "+pname ," Edit Link found for "+pname,"Pass");
			}
			else
			{
				oASelFW.effecta("sendReportWithOutExit","Validate If Edit link is found for "+pname ," Edit Link not found for "+pname,"Pass");
			}
		}

	}

	public void verifyRosterForAllDevType(String list) throws InterruptedException
	{
		Switchframe();
		selectBytext("//select[@id='id1014']", list);
		Thread.sleep(2000);
		ClickOnButton("Search");
		Thread.sleep(4000);
		ClickOnElement("//span[text()='Title']/ancestor::tr[1]/following-sibling::tr[1]/descendant::a[2]", "Class title");
		oASelFW.effecta("sendReportWithOutExit","Validate If user can see Filtered classes of "+list+ " delivery type"," yes, User can see results of "+list+ "Class","Pass");
		String btnType= getText("//span[text()='Send Notification']/ancestor::td[2]/following-sibling::td[2]/descendant::a");
		ClickOnElement("//span[text()='Send Notification']/ancestor::td[2]/following-sibling::td[2]/descendant::a", btnType);
		Thread.sleep(3000);
		if(Boolean.parseBoolean((oASelFW.effecta("isElementPresent","//span[text()='Registration status']"))))
		{
			oASelFW.effecta("sendReportWithOutExit","Validate If user can see Roster"," yes, User can see roster page","Pass");
		}


		switch_to_default_content();
	}
	public void FilterClassUsingDevtypSatus(String DevTyp, String status) throws InterruptedException
	{
		Switchframe();
		selectBytext("//label[text()='Delivery']/ancestor::td[1]/following-sibling::td[1]/select", DevTyp);
		Thread.sleep(2000);
		selectBytext("//label[text()='Status']/ancestor::td[1]/following-sibling::td[1]/select",status );
		Thread.sleep(2000);
		ClickOnButton("Search");
		switch_to_default_content();
	}
	public void FilterClassUsingDevtypDate(String DevTyp) throws InterruptedException
	{
		Switchframe();
		selectBytext("//label[text()='Delivery']/ancestor::td[1]/following-sibling::td[1]/select", DevTyp);
		Thread.sleep(2000);

		ClickOnElement("//img[@alt='Pick Start Date >=']", "Start date");
		String window[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window[1]);
		Calendar currentDate = Calendar.getInstance(); //Get the current date
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy/MMMM/dd"); //format it as per your requirement
		String dateNow = formatter.format(currentDate.getTime());
		String[] date = dateNow.split("/");
		String month = date[1];
		String year = date[0];
		String day = date[2];
		System.out.println(day);
		ClickOnElement("//a[@title='"+day+"']", "Select day");
		//oASelFW.effecta("clickAndWait","", ""+day+"/"+month+"/"+year+"","Pass");
		Thread.sleep(2000);
		oASelFW.effecta("selectWindow",window[0]);
		Thread.sleep(2000);
		Switchframe();
		ClickOnButton("Search");
		switch_to_default_content();
	}
	public void FilterClassUsinDEvtypeStatusAndTitle(String DevTyp, String status) throws InterruptedException
	{
		Switchframe();
		selectBytext("//label[text()='Delivery']/ancestor::td[1]/following-sibling::td[1]/select", DevTyp);
		Thread.sleep(2000);
		selectBytext("//label[text()='Status']/ancestor::td[1]/following-sibling::td[1]/select",status );
		Thread.sleep(2000);
		SetTextField("//label[text()='Title']/ancestor::td[1]/following-sibling::td[1]/input", "Ace &amp; Champion Training", "Course title");
		Thread.sleep(2000);
		ClickOnButton("Search");

		switch_to_default_content();
	}
	public void verifyRosterForSelfpaced(String list) throws InterruptedException
	{
		Switchframe();
		selectBytext("//select[@id='id1014']", list);
		Thread.sleep(2000);
		ClickOnButton("Search");
		Thread.sleep(4000);
		ClickOnElement("//span[text()='Title']/ancestor::tr[1]/following-sibling::tr[3]/descendant::a[1]", "Class title");
		oASelFW.effecta("sendReportWithOutExit","Validate If user can see Filtered classes of "+list+ " delivery type"," yes, User can see results of "+list+ "Class","Pass");
		String btnType= getText("//span[text()='Send Notification']/ancestor::td[2]/following-sibling::td[2]/descendant::a");
		ClickOnElement("//span[text()='Send Notification']/ancestor::td[2]/following-sibling::td[2]/descendant::a", btnType);
		Thread.sleep(3000);
		if(Boolean.parseBoolean((oASelFW.effecta("isElementPresent","//span[text()='Registration status']"))))
		{
			oASelFW.effecta("sendReportWithOutExit","Validate If user can see Roster"," yes, User can see roster page","Pass");
		}


		switch_to_default_content();
	}

	public void validateLinksUnderMyDev(String Ltov)
	{

		if(Boolean.parseBoolean((oASelFW.effecta("isElementPresent","//a[text()='"+Ltov+"']"))))
		{
			oASelFW.effecta("sendReportWithOutExit","Validate If user can see " +Ltov +" under My Developement"," Validated, User can see "+ Ltov+ " under My Developement","Pass");
			oASelFW.driver.findElement(By.xpath("//a[text()='"+Ltov+"']")).click();
			oASelFW.effecta("sendReportWithOutExit","Validate If user can see sub categories under " +Ltov +" page"," Validated, user can see sub categories under " +Ltov +" page","Pass");
		}
		else{
			oASelFW.effecta("sendReportWithOutExit","Validate If user can see " +Ltov +" under My Developement"," Validated, User can't see the link under My Developement","Fail");
		}

	}

	public void validateLinksUnderBrowse(String Ltov)
	{

		if(Boolean.parseBoolean((oASelFW.effecta("isElementPresent","//span[text()='"+Ltov+"']"))))
		{
			oASelFW.effecta("sendReportWithOutExit","Validate If user can see " +Ltov +" under Browse page"," Validated, User can see "+ Ltov+ " under Browse","Pass");

		}
		else{
			oASelFW.effecta("sendReportWithOutExit","Validate If user can see " +Ltov +" under Browse Page"," Validated, User can't see the link under browse","Fail");
		}

	}
	public void validateLinksOnManageDev(String Ltov) throws InterruptedException
	{
		Switchframe();
		if(Boolean.parseBoolean((oASelFW.effecta("isElementPresent","//span[contains(text(),'"+Ltov+"')]"))))
		{
			oASelFW.effecta("sendReportWithOutExit","Validate If user can see " +Ltov +" under Manage Categories"," Validated, User can see "+ Ltov+ " under Manage Categories","Pass");
			ClickOnElement("//span[contains(text(),'"+Ltov+"')]/ancestor::tr/td[2]/a", "+ logo to expand");
			Thread.sleep(5000);
			/*	if(Boolean.parseBoolean((oASelFW.effecta("isElementPresent","//span[contains(text(),'"+SubCat+"')]"))))
			oASelFW.effecta("sendReportWithOutExit","Validate If user can see sub categories "+SubCat+" under " +Ltov +" link"," Validated, user can see sub categories "+SubCat+" under " +Ltov +" link","Pass");*/
		}
		else{
			oASelFW.effecta("sendReportWithOutExit","Validate If user can see "+ Ltov+ " under Manage Categories"," Validated, user can't see "+ Ltov + " under Manage Categories","fail");
		}
		switch_to_default_content();
	}

	public void ValidateCourseWithE_C(String cId) throws InterruptedException
	{
		Switchframe();
		selectBytext("//label[text()='Learning Event Type']/ancestor::td[1]/following-sibling::td[1]/select", "Course");
		Thread.sleep(1000);
		SetTextField("//label[text()='Course ID']/ancestor::td[1]/following-sibling::td[1]/input", cId, "course Id");
		ClickOnButton("Search");
		Thread.sleep(3000);
		ClickOnElement("//span[text()='Title']/ancestor::tr[1]/following-sibling::tr[1]/descendant::a[text()='Advanced Edit']", "Advance Edit");
		ClickOnElement("//a[text()='Related Info']", "Related Info Tab");
		if(Boolean.parseBoolean((oASelFW.effecta("isElementPresent","//span[text()='Category']/ancestor::tr[2]/following-sibling::tr/descendant::span[contains(text(),'Ethics & Compliance')]"))))
		{
			oASelFW.effecta("sendReportWithOutExit","Validate If user can see 'Ethics & Compliance' under Categories for Course Id:"+ cId ," Validated, user can see 'Ethics & Compliance' under Categories for Course Id:"+ cId,"Pass");
		}
		else
		{
			oASelFW.effecta("sendReportWithOutExit","Validate If user can see 'Ethics & Compliance' under Categories for Course Id:"+ cId ," Validated, user can't see 'Ethics & Compliance' under Categories for Course Id:"+ cId,"Fail");
		}
		switch_to_default_content();
	}

	public String ValidateLinks(String classId , String cName) throws InterruptedException
	{

		String status=null;

		SetTextField("//input[@name='search-input']", classId.trim(), "Search class with class Id as: "+ classId);
		ClickOnElement("//img[@title='Search']", "Search Img");
		if(Boolean.parseBoolean((oASelFW.effecta("isElementPresent","//span[text()='Launch']"))))
		{
			//oASelFW.effecta("sendReportWithOutExit","Verify If class with course name: "+cName+" and class ID: "+classId +" is available", " Class found with course name: "+cName+" and Class Id: "+classId,"Pass");

			ClickOnElement("//span[text()='Launch']", "Class Name"+ classId);
			Thread.sleep(12000);
			oASelFW.effecta("sendReportWithOutExit","Click on Launch Button"," Clicked on Enroll button","Pass"); 
			Thread.sleep(35000);
			//Thread.sleep(15000);

			/*while(window[1]==null){
					Thread.sleep(2000);
					window[]= oASelFW.effectaArray("getAllWindowNames");
				}*/
			String window[]= oASelFW.effectaArray("getAllWindowNames");
			if(window[1]!=null)
			{
				oASelFW.effecta("selectWindow",window[1]);
				oASelFW.driver.close();
				oASelFW.effecta("selectWindow",window[0]);
				oASelFW.effecta("sendReportWithOutExit","Verify that course is launched successully", cName+"  is Launched succesfully ","Pass");
				status = "Launched Successfully";
				System.out.println("Status in method: "+status);
			}
			else
			{
				oASelFW.effecta("sendReportWithOutExit","Verify that course is launched successully", cName+"  could not Launched succesfully ","Pass");
				status = "Couldnt Launch Successfully";
				System.out.println("Status in method: "+status);
			}

		}

		/*else if(Boolean.parseBoolean((oASelFW.effecta("isElementPresent","//a[text()=' Launch']"))))
			{
				ClickOnElement("//a[text()=' Launch']", "Launch Button");
				oASelFW.effecta("sendReportWithOutExit","Click on Launch Button"," Clicked on Launch button","Pass");
				Thread.sleep(13000);
				String window[]= oASelFW.effectaArray("getAllWindowNames");
				oASelFW.effecta("selectWindow",window[1]);
				oASelFW.driver.close();
				oASelFW.effecta("selectWindow",window[0]);
				oASelFW.effecta("sendReportWithOutExit","Verify that course is launched successully", cName+"  is Launched succesfully ","Pass");
				status = "Launched Successfully";
				System.out.println("Status in method: "+status);
			}*/

		else if(Boolean.parseBoolean((oASelFW.effecta("isElementPresent","//span[text()='Completed successfully']"))))
		{

			oASelFW.effecta("sendReportWithOutExit", cName+ "Verify Course status"," Course already taken","Pass");
			status = "Course already taken";
			System.out.println("Status in method: "+status);
		}

		else if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//label[contains(text(),'There are no active classes availabl')]")))
		{
			oASelFW.effecta("sendReportWithOutExit", "Verify Course status of course "+cName,"Text Found: There are no active classes available.","Pass");
			status = "There is no active class";
			System.out.println("Status in method:"+ status);
		}





		else
		{
			System.out.println(classId+" Class not found");
			oASelFW.effecta("sendReportWithOutExit","Verify If class with course name: "+cName+" and class ID: "+classId +" is available", "Sorry, no Class found with course name: "+cName+" and Class Id: "+classId,"Pass");
			status = "Class not found in the for the" + classId;
		}
		System.out.println(status);
		return status;
	}

	public void searchCourse(String courseName) throws InterruptedException
	{
		Switchframe();
		selectBytext("//label[text()='Learning Event Type']/ancestor::td[1]/following-sibling::td[1]/select", "Course");
		Thread.sleep(2000);
		SetTextField("//label[text()='Title']/ancestor::td[1]/following-sibling::td[1]/input", courseName, "Course title");
		ClickOnButton("Search");
		ClickOnElement("//a[text()='Edit']", "Edit Link");
		Thread.sleep(2000);
		switch_to_default_content();
	}
	public void addPre_TestRequirement(String testType) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(oASelFW.driver, 30);
		Actions act = new Actions(oASelFW.driver);

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		Thread.sleep(2000);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@id='wizardwrapper']")));
		System.out.println("switched to main content");

		/*act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Save & Next']"))).build().perform();
		act.click().build().perform();
		oASelFW.effecta("sendReportWithOutExit","Click on Save and Next Button","Clicked on Save and Next Button ","Pass");*/
		ClickOnElement("//span[text()='Save & Next']", "Save and Next Button");

		Thread.sleep(2000);

		//ClickOnElement("//span[text()='Save & Next']/following-sibling::span", "Save and Next Button");
		Thread.sleep(2000);
		/*act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='New Online Class']"))).build().perform();
		act.click().build().perform();
		oASelFW.effecta("sendReportWithOutExit","Click on New Online Class Button","Clicked on New Online Class Button ","Pass");*/
		ClickOnElement("//span[text()='New Online Class']", "New Online Class");
		Thread.sleep(2000);

		SetTextField("//label[text()='Duration (HH:MM)']/ancestor::td[1]/following-sibling::td/input", "00:10", "Duration of Class");
		Thread.sleep(3000);

		/*act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Save & Add Activities']"))).build().perform();
		act.click().build().perform();
		oASelFW.effecta("sendReportWithOutExit","Click on Save & Add Activities Button","Clicked on Save & Add Activities Button ","Pass");*/
		ClickOnElement("//span[text()='Save & Add Activities']", "Save & Add Activities");
		Thread.sleep(4000);

		/*act.moveToElement(oASelFW.driver.findElement(By.xpath("//input[@placeholder='Select Activity']/ancestor::td[1]/following-sibling::td/div"))).build().perform();
		//act.click().build().perform();*/
		ClickOnElement("//input[@placeholder='Select Activity']/ancestor::td[1]/following-sibling::td/div", "'+' logo against Select activity");
		/*act.moveToElement(oASelFW.driver.findElement(By.xpath("//input[@placeholder='Select Activity']/ancestor::td[1]/following-sibling::td/div"))).build().perform();
		act.click().build().perform();
		Thread.sleep(1000);

		Thread.sleep(2000);
		act.moveToElement(oASelFW.driver.findElement(By.xpath("//li[text()='Test']"))).build().perform();
		act.click().build().perform();*/



		/*act.moveToElement(oASelFW.driver.findElement(By.xpath("//input[@placeholder='Select Activity']/ancestor::td[1]/following-sibling::td/div"))).build().perform();
			act.click().build().perform();
			oASelFW.effecta("sendReportWithOutExit","Click on Select Activity drop down","Clicked on Select Activity drop down ","Pass");*/
		Thread.sleep(2000);
		act.moveToElement(oASelFW.driver.findElement(By.xpath("//li[text()='Test']"))).build().perform();
		act.click().build().perform();
		oASelFW.effecta("sendReportWithOutExit","Select test from drop down","Selected Test from drop down","Pass");
		Thread.sleep(2000);

		act.moveToElement(oASelFW.driver.findElement(By.xpath("//label[text()='"+testType+"']/preceding::input[1]"))).build().perform();
		act.click().build().perform();
		oASelFW.effecta("sendReportWithOutExit","Click on "+testType+" radio button","Clicked on "+testType+" radio button","Pass");

		Thread.sleep(2000);

		act.moveToElement(oASelFW.driver.findElement(By.xpath("//label[text()='Import From Content Library']/preceding::input[1]"))).build().perform();
		act.click().build().perform();
		oASelFW.effecta("sendReportWithOutExit","Click on Import From Content Library radio button","Clicked on Import From Content Library radio button","Pass");
		Thread.sleep(2000);

		ClickOnElement("//input[@placeholder='Search...']/ancestor::td[3]/following-sibling::td[2]/descendant::div[2]", "Content type Drop down");
		Thread.sleep(2000);
		act.moveToElement(oASelFW.driver.findElement(By.xpath("//b[text()='Academic Specializ ...']/../following-sibling::span"))).build().perform();
		act.click().build().perform();
		Thread.sleep(5000);

		//ClickOnElement("xpath=(//span[text()='Add'])[2]", "Click on Add");
		oASelFW.effecta("click","xpath=(//span[text()='Add'])[2]","Add");
		//act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Add']"))).build().perform();
		//act.click().build().perform();
		System.out.println("Click on Add button");
		Thread.sleep(6000);

		//ClickOnElement("//span[text()='Add']/following-sibling::span", "Add button");
		act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Save']/following-sibling::span"))).build().perform();
		act.click().build().perform();
		oASelFW.effecta("sendReportWithOutExit","Click on Save button","Clicked on Save button","Pass");
		Thread.sleep(2000);

		act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Publish']/following-sibling::span"))).build().perform();
		act.click().build().perform();
		oASelFW.effecta("sendReportWithOutExit","Click on Publish button","Clicked on Publish button","Pass");
		Thread.sleep(2000);
		//ClickOnElement("//span[text()='Publish']/following-sibling::span", "Publish button");
		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//li[contains(text(),'Class successfully published')]")))
		{
			oASelFW.effecta("sendReportWithOutExit","Verify if the class is Published"," Class Published successfully","Pass");
		}

		switch_to_default_content();

	}

	public void verifyNotifcationURLTakesUserToCourse()
	{

		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='test3335522']")))
		{
			oASelFW.effecta("sendReportWithOutExit","Verify the Course Name"," Course Name found as: test3335522","Pass");

		}
		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='Siddharth Pangotra']")))
		{
			oASelFW.effecta("sendReportWithOutExit","Verify the Learner Name"," Learner Name found as: Siddharth Pangotra","Pass");

		}
		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='In Progress']")))
		{
			oASelFW.effecta("sendReportWithOutExit","Verify the status of the course"," Status found as: In Progress","Pass");

		}
	}
	public void PrintExportFromRoster() throws InterruptedException
	{
		Switchframe();

		ClickOnElement("//a[text()='Roster']", "Roster Link");
		Thread.sleep(2000);
		ClickOnElement("//span[text()='Edit']/following-sibling::span", "Edit Button");
		Thread.sleep(2000);
		String window[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window[1]);
		ClickOnElement("//a[text()='Print']", "Print Link");
		oASelFW.effecta("sendReportWithOutExit","Verify the user can print the roster"," Verified, user can print the roster","Pass");
		ClickOnElement("//a[text()='Export']", "Export Link");
		oASelFW.effecta("sendReportWithOutExit","Verify the user can export the roster"," Verified, user can export the roster","Pass");
		Thread.sleep(1000);
		oASelFW.driver.close();
		oASelFW.effecta("selectWindow",window[0]);

		switch_to_default_content();

	}
	public void CancelClass() throws InterruptedException
	{
		Switchframe();

		ClickOnElement("//a[text()='Roster']", "Roster Link");
		Thread.sleep(2000);
		ClickOnElement("//b[text()='Status:']/../following-sibling::span/a", "Status type");

		String window[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window[1]);
		Thread.sleep(2000);
		ClickOnElement("//span[text()='Cancelled']/ancestor::td[1]//input[@name='kStatusRadioButton']", "Cancelled radio Button");
		ClickOnButton("Save");
		oASelFW.effecta("sendReportWithOutExit","Verify the status of the Class"," Status found as: Cancelled","Pass");
		Thread.sleep(1000);
		oASelFW.driver.close();
		oASelFW.effecta("selectWindow",window[0]);

		switch_to_default_content();
	}
	public void reScheduleClass() throws Exception
	{
		Switchframe();
		int rand= GenerateRandomNoBetweenLimits(2, 41);
		String classtitle= getText("//span[text()='Title']/ancestor::td[1]/descendant::tr[2]/td[4]/span/a");
		ClickOnElement("//span[text()='Title']/ancestor::td[1]/descendant::tr["+rand+"]/td[4]/span/a", "Class Title:"+classtitle);
		ClickOnElement("//img[@alt='Start Date']", "Start date");
		String window[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window[1]);
		ClickOnElement("//img[@alt='Start Date']", "Start date");
		String window1[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window1[2]);
		ClickOnElement("//a[text()='30']", "Select date");
		oASelFW.effecta("selectWindow",window1[1]);
		ClickOnButton("Next");
		ClickOnElement("//span[text()='Ignore Conflicts']/../input", "Ignore Conflicts");
		ClickOnButton("Save");
		Thread.sleep(2000);
		if(waitOnAlert()== true)
		{acceptAlert();}
		oASelFW.effecta("selectWindow",window[0]);
		Switchframe();
		ClickOnButton("Save");

	}
	public void ClickOnAuditTrail(String scenario) throws InterruptedException
	{
		if(scenario=="onClassPage")
		{
			Switchframe();
			int rand= GenerateRandomNoBetweenLimits(2, 41);
			String classtitle= getText("//span[text()='Title']/ancestor::td[1]/descendant::tr["+rand+"]/td[4]/span/a");
			ClickOnElement("//span[text()='Title']/ancestor::td[1]/descendant::tr["+rand+"]/td[4]/span/a", "Class Title:"+classtitle);
			ClickOnElement("//img[@alt='Audit Trail']", "Audit Trail");
			String window[] = oASelFW.effectaArray("getAllWindowNames");
			oASelFW.effecta("selectWindow",window[1]);
			Thread.sleep(3000);
			oASelFW.effecta("sendReportWithOutExit","Verify Admin can view Audit Trail"," Verified, user can view audit trail","Pass");
			oASelFW.driver.close();
			oASelFW.effecta("selectWindow",window[0]);

		}

		if(scenario=="onRosterPage")
		{
			Switchframe();
			int rand= GenerateRandomNoBetweenLimits(2, 41);
			ClickOnElement("//span[text()='Title']/ancestor::td[1]/descendant::tr[2]/td[2]/a", "Roster Link");
			String orderNum = getText("//span[text()='Order no']/../../ancestor::div[3]/following-sibling::div[1]/descendant::tbody/descendant::td[8]/descendant::a");
			ClickOnElement("//span[text()='Order no']/../../ancestor::div[3]/following-sibling::div[1]/descendant::tbody/descendant::td[8]/descendant::a", "Order Number: "+orderNum);
			String window[] = oASelFW.effectaArray("getAllWindowNames");
			oASelFW.effecta("selectWindow",window[1]);
			Thread.sleep(3000);
			oASelFW.effecta("sendReportWithOutExit","Verify Admin can view Audit Trail on roster page"," Verified, user can view audit trail on roster page","Pass");
			oASelFW.driver.close();
			oASelFW.effecta("selectWindow",window[0]);

		}
	}

	//=========================Mylearn===================
	public static Properties config = null;	
	public static String basedir    = System.getProperty("user.dir");
	String errormsg = "ElementNotFoundException";
	String adminster_btn = "//a[contains(@title,'myLearn Administration Portal')]";
	String vmwedu_btn = "//a[contains(@title,'VMware Education')]";
	String vmware_edge = "//a[contains(@title,'VMware Edge portal')]/img";
	String menu_frame = "//frame[@name='left']";
	String main_frame = "//frame[@name='right']";
	String education_btn = "//a[contains(@title,'VMware Education')]/img";
	String RandDevXpath = "//a[contains(@title,'R&D Education')]/img";
	String searchXpath = "//input[@name='submutBtn']";


















	public HashMap<String, String> switchTabs()

	{
		String defaultcontent = oASelFW.driver.getWindowHandle();
		String default_title = oASelFW.driver.switchTo().defaultContent()
				.getTitle();
		HashMap<String, String> tab = new HashMap<String, String>();
		ArrayList<String> tabs = new ArrayList<String>(
				oASelFW.driver.getWindowHandles());

		for (String tabval : tabs) {
			System.out.println(tabval);
			String tabname = oASelFW.driver.switchTo().window(tabval)
					.getTitle();
			System.out.println(tabname);
			tab.put(tabname, tabval);
		}
		tab.put(default_title, defaultcontent);

		return tab;
	}
	public String loadprops(String prop) throws IOException
	{
		FileInputStream fis = new FileInputStream(basedir + File.separator + File.separator + "src" + File.separator + "constant.properties");
		config = new Properties();
		config.load(fis);
		String value = config.getProperty(prop);
		return value;
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

	public void naviagate_adminster_portal() {
		try {
			WebDriverWait wait = new WebDriverWait(oASelFW.driver,
					timeOutSecs());
			wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath(adminster_btn)));
			oASelFW.driver.findElement(By.xpath(adminster_btn)).click();

			oASelFW.driver.switchTo().window(
					switchTabs().get("myLearn Administration Portal"));
			String title = oASelFW.driver.getCurrentUrl();
			System.out.println("switched to mylearn admin portal");
			oASelFW.effecta(
					"sendReport",
					"Verify if the user is navigated to Administration portal",
					"User successfully navigated to myLearn Administration Portal",
					"Pass");
		} catch (Exception e) {
			if (e.equals(errormsg)) {
				oASelFW.effecta("sendReport", "WebElement verification",
						"Element not found at " + adminster_btn, "Fail");
			}
		}
	}

	public void switch_to_myLearnMainContent() {
		WebDriverWait wait = new WebDriverWait(oASelFW.driver, timeOutSecs());
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By
				.xpath(main_frame)));
		System.out.println("switched to main content");
	}


}













