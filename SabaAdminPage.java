package classes;

import java.awt.AWTException;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.arsin.ArsinSeleniumAPI;
import com.gargoylesoftware.htmlunit.javascript.host.dom.Document;
import com.thoughtworks.selenium.webdriven.commands.IsAlertPresent;



public class SabaAdminPage {


	ArsinSeleniumAPI oASelFW;

	public SabaAdminPage() {
	}

	public SabaAdminPage(ArsinSeleniumAPI oASelFW) {
		this.oASelFW=oASelFW;
	}


	/* @Author - Mangala 
	 * Description - This method will Clicks on Admin Links[HR ADMIN,CONFIG ADMIN,Learning ADMIN,....]
	 */
	//CLICK ON ADMIN LINKs	
	public void click_Admin_Links(String linkName) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		oASelFW.effecta("waitForElementPresent","//span[text()='"+linkName.trim()+"']","60");
		oASelFW.effecta("clickAndWait","//div[@class='sjs-admin-home-generic-main-container']/descendant::span[text()='"+linkName.trim()+"']",linkName);
		System.out.println("Clicked on "+linkName);	
		oASelFW.driver.switchTo().defaultContent();

	}

	/* @Author - Mangala 
	 * Description - This method will Clicks on options like[AdminHome, SYSTEM, MARKETPLACE, PEOPLE, SOCIAL, MEETINGS, Analytics...] 
	 */
	//CLICK ON SYSYTEM LINK 

	public void click_Options_Link(String optionLink) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

		oASelFW.effecta("waitForElementPresent","//a[text()='"+optionLink+"']","60");
		Thread.sleep(3000);
		oASelFW.effecta("click","//a[text()='"+optionLink+"']",optionLink);
		Thread.sleep(15000);
		oASelFW.driver.switchTo().defaultContent();

	}


	public void analyticsSearchBar(String name)
	{
		try {
			Actions act = new Actions(oASelFW.driver);
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			
			Thread.sleep(15000);
			
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//li[contains(text(),'All')]"))).build().perform();
			Thread.sleep(2000);
			act.click().build().perform();
			Thread.sleep(5000);
			
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='"+name.trim()+"']"))).build().perform();
			Thread.sleep(5000);
			act.click().build().perform();
			
			
			
			
			Thread.sleep(50000);
			oASelFW.driver.switchTo().defaultContent();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void analyticsSearchAndClickOn_Link(String link)
	{
		try {
			Actions act = new Actions(oASelFW.driver);
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//li[contains(text(),'All')]"))).build().perform();
			Thread.sleep(2000);
			act.click().build().perform();
			Thread.sleep(5000);
			
			oASelFW.effecta("type","//input[@name='searchField']",link,link);
			Thread.sleep(2000);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//em[text()='"+link.trim()+"']"))).build().perform();
			Thread.sleep(5000);
			act.click().build().perform();
			Thread.sleep(100000);
			Thread.sleep(50000);
			oASelFW.driver.switchTo().defaultContent();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	/* @Author - Mangala 
	 * Description - This method will Clicks on HRAdmin, LearningAdmin and PeopleAdmin after navigation right side links[Manage organizations,Manage Skills,Manage Jobs,...],[Manage Learning Catalog,Manage Classes,Manage Content,Manage Resources,....].
	 * [Manage People, Manage Signup Rules, Rules Engine,Manage To-Do Lists,.... ],[]
	 */
	public void click_HrHome_Links(String hrHomeLinks)
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

		oASelFW.effecta("waitForElementPresent","//a[@title='"+hrHomeLinks+"']","60");
		oASelFW.effecta("click","//a[@title='"+hrHomeLinks+"']",hrHomeLinks);
		System.out.println("Clicking on the link ::::::::::::::"+hrHomeLinks);
		oASelFW.driver.switchTo().defaultContent();
	}

	/* @Author - Mangala 
	 * Description - This method will Clicks on Manage Jobs Sub LINKS[Job Families,Jobs....]
	 * 
	 */

	public void click_ManageJobs_SubLinks(String mangaeJobsSubLinks)
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

		oASelFW.effecta("waitForElementPresent","//a[text()='"+mangaeJobsSubLinks+"']","60");
		oASelFW.effecta("click","//a[text()='"+mangaeJobsSubLinks+"']",mangaeJobsSubLinks);
		System.out.println("Clicked on "+mangaeJobsSubLinks);
		oASelFW.driver.switchTo().defaultContent();
	}


	/* @Author - Mangala 
	 * Description - This method will Clicks on Search link 
	 * 
	 */


	public void click_JobFamily_SearchLinks()
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		oASelFW.effecta("waitForElementPresent","//span[text()='Search']","60");
		oASelFW.effecta("click","//span[text()='Search']","Search");
		System.out.println("Clicked on Search Button");
		oASelFW.driver.switchTo().defaultContent();
	}


	/* @Author - Mangala 
	 * Description - This method Deletes the existing files in download directory 
	 * 
	 */

	public void DeleteFiles_Path_FileExtnName_01(String FilePath,String Exn) throws InterruptedException, IOException {
		File dir = new File(FilePath);
		String fileName1=null;
		FileFilter fileFilter = new WildcardFileFilter("*"+Exn);
		File[] files = dir.listFiles(fileFilter);
		try{
			for (int i = 0; i < files.length; i++) {
				System.out.println("before Deleting files"+files[i]);
				File file = new File(String.valueOf(files[i]));
				if(file.delete()){
					System.out.println(file.getName() + " is deleted!");
				}else{
					System.out.println("Delete operation is failed.");
				}

				fileName1=file.getName();
				System.out.println(fileName1 +"********************");
			}

		}catch(NullPointerException ext){
			ext.printStackTrace();
		}

		Thread.sleep(3000);
		System.out.println("Delete Existing download files from Drive");
	}
	public void click_Export_Links(String LinkName)
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

		oASelFW.effecta("waitForElementPresent","//a[text()='"+LinkName+"']","60");
		oASelFW.effecta("click","//a[text()='"+LinkName+"']",LinkName);
		oASelFW.driver.switchTo().defaultContent();
		handle_Alerts_PopUP();
	}

	/**
	 * This method will wait on Alert to be present withing the Short Time Out
	 * @return
	 */
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


	public boolean handle_Alerts_PopUP()
	{
		boolean status = false;
		waitOnAlert();
		oASelFW.driver.switchTo().alert().accept();
		oASelFW.effecta("sendReport","Accepting Alert", "Successfully Accepted Alert", "Pass");
		return status;
	}

	public void dismissAlert()
	{
		waitOnAlert();
		oASelFW.driver.switchTo().alert().dismiss();
		oASelFW.effecta("sendReport","Dismissing Alert", "Successfully Dismiss Alert", "Pass");
	}


	/**@author arifuddin_mohd
	 * Description - This method will switch the control to right frame(main menu content) in the Saba portal page
	 */
	public void switch_to_main_content() {
		WebDriverWait wait = new WebDriverWait(oASelFW.driver, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		System.out.println("switched to main content");
	}

	/**@author arifuddin_mohd
	 * Description - This method will switch the control back to default in Saba portal page
	 */
	public void switch_to_default_content() {
		oASelFW.driver.switchTo().defaultContent();
	}


	/*@Author - Arifuddin Mohd
	 * Description - This method will validate links in UI Comparing with DB.
	 */

	public void verifyingJobFamilyName_InAdminpg(ArrayList<String> jobName)
	{
		try {
			WebDriverWait wt=new WebDriverWait(oASelFW.driver,15);
			List<WebElement> li = oASelFW.driver.findElements(By.xpath("//table[@id='id_JobFamily_result']/descendant::table[2]/tbody/tr/td/a[@class='sbLinkGeneral']"));
			ArrayList<String> passedNames = new ArrayList<String>();
			int i=0;
			int count=0;
			do
			{
				for(int k=0;k<jobName.size();k++)
				{
					if(oASelFW.driver.findElements(By.xpath("//a[text()='"+jobName.get(k)+"']")).size()>0)
					{
						passedNames.add(jobName.get(k));
						count=1;
						break;
					}
					else
					{
						oASelFW.effecta("clickAndWait","xpath=(//table[@id='id_JobFamily_result']/descendant::table[2]/tbody/tr/td/a[@class='sbLinkGeneral'])["+(i+1)+"]", "Next");
					}
				}
				i++;
			}while(i<li.size());
			for(int k=0;k<jobName.size();k++)
			{
				if(passedNames.contains(jobName.get(k)))
					oASelFW.effecta("sendReportWithOutExit","verifying the values in UI","verified successfully"+jobName,"Pass");
				else
					oASelFW.effecta("sendReportWithOutExit","Verifying the values in UI","Unable to verify the Values bcoz count is "+count,"Fail");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/* @Author - Mangala 
	 * Description - This method will VALIDATES JOB DETAILS PAGE WITH DB 
	 * 
	 */

	public void compare_JobDetailsPage_DB(String Name,String JobFamily,String JobCode,String Domain,String JobFamilyGroup,String status,String Role,String RoleRequired)
	{

		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		oASelFW.effecta("waitForElementPresent","//span[text()='Name*']","60");
		oASelFW.effecta("verifyElementPresent","//span[text()='Name*']/following::input[@value='"+Name+"']","JOB NAME: "+Name+" is present in UI");
		oASelFW.effecta("verifyElementPresent","//span[text()='Name*']/following::input[@value='"+JobFamily+"']","JobFamily: "+JobFamily+" is present in UI");
		oASelFW.effecta("verifyElementPresent","//span[text()='Name*']/following::input[@value='"+JobCode+"']","JobCode: "+JobCode+" is present in UI");
		oASelFW.effecta("verifyElementPresent","//span[text()='Name*']/following::input[@value='"+Domain+"']","Domain: "+Domain+" is present in UI");

		if(JobFamilyGroup.equalsIgnoreCase("None"))
		{
			JobFamilyGroup="";
			String value=oASelFW.effecta("getText","//span[text()='Name*']/following::input[@value='"+JobFamilyGroup+"']","Getting value of Domain");

			if(value.equals(JobFamilyGroup))
			{
				oASelFW.effecta("sendReport","Verified JobFamilyGroup is having null value "+value+"","Succesfully verified JobFamilyGroup "+value+"","Pass");

			}
			else{
				oASelFW.effecta("sendReportWithOutExit","Verified JobFamilyGroup is not  having null value "+value+""," verified JobFamilyGroup "+value+"","Fail");

			}

		}
		else
		{
			oASelFW.effecta("verifyElementPresent","//span[text()='Name*']/following::input[@value='"+JobFamilyGroup+"']","JobFamilyGroup: "+JobFamilyGroup+" is present in UI");

		}


		oASelFW.effecta("verifyElementPresent","//span[text()='Status']/following::td[@class='sbInputFormCell']/span[@class='sbAttributeValue']/input[@value='100']","Status as"+status);
		oASelFW.effecta("verifyElementPresent","//a[text()='"+Role+"']","Role value "+Role+"");
		//String rolerequired=oASelFW.effecta("getText","//a[text()='Mark Optional']"," Role");
		String rolerequired= oASelFW.driver.findElement(By.xpath("//a[text()='Mark Optional']")).getText();

		//oASelFW.effecta("getText","//a[text()='Mark Optional']"," Role");

		if(rolerequired.equals("Mark Optional"))
		{
			oASelFW.effecta("sendReport","verified role required is YES ","succesfully verified role is required...YES","Pass");
			oASelFW.effecta("verifyElementPresent","//a[text()='"+RoleRequired+"']","Role is required "+RoleRequired+"");
		}

		else if(rolerequired.equals("Mark Required"))
		{
			oASelFW.effecta("sendReport","verified role reuired is NO ","succesfully verified role is not required","Pass");
		}

		oASelFW.driver.switchTo().defaultContent();

	}


	public void verify_Description(String Internal_Description)
	{

		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		System.out.println("Switched to 1st frame");//iframe[contains(@title,'Rich Text Area')])[1]
		oASelFW.effecta("waitForElementPresent","//label[text()='Internal Description']","60");
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("(//iframe[contains(@title,'Rich Text Area')])[1]")));
		System.out.println("Switched to 2nd frame");//iframe[contains(@title,'Rich Text Area')])[1]
		String description = oASelFW.driver.findElement(By.tagName("p")).getText();
		System.out.println("Description:"+description.toString());

		if(description.equals(Internal_Description))
		{
			oASelFW.effecta("sendReport","verified Internal Description displayed as per SOurce file "+Internal_Description+"","succesfully verified Internal Description and displayed text "+Internal_Description+"","Pass");
		}
		else
		{
			oASelFW.effecta("sendReport","verified Internal Description not displayed as per SOurce file","succesfully verified Internal Description","Fail");
		}
		oASelFW.driver.switchTo().defaultContent();
		oASelFW.driver.switchTo().defaultContent();




	}


	public void compare_Attachments_JobDetailsPage(String NextCarrerStep,String Optional_Job_Roles,String Skill_Requirements,String Attachments,String Learning_Events)
	{

		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		oASelFW.effecta("waitForElementPresent","//span[text()='Name*']","60");
		oASelFW.effecta("verifyElementPresent","//span[text()='"+NextCarrerStep+"']/following::tr[3]/th/span",""+NextCarrerStep+"");
		oASelFW.effecta("verifyElementPresent","//span[text()='"+Optional_Job_Roles+"']/following::tr[3]/th/span",""+Optional_Job_Roles+"");
		oASelFW.effecta("verifyElementPresent","//span[text()='"+Skill_Requirements+"']/following::tr[3]/th/span",""+Skill_Requirements+"");
		oASelFW.effecta("verifyElementPresent","//span[text()='"+Attachments+"']/following::tr[3]/th/span",""+Attachments+"");
		oASelFW.effecta("verifyElementPresent","//span[text()='"+Learning_Events+"']/following::tr[3]/th/span",""+Learning_Events+"");

		oASelFW.driver.switchTo().defaultContent();


	}

	public void searchManage_JobName(int loopcount,ArrayList<String> jobName)
	{
		try {

			for(int i=0; i<loopcount;i++)
			{
				WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

				String job=jobName.get(i);

				System.out.println("Entering the text  "+job);

				oASelFW.effecta("type","//label[text()='Name']/../..//following-sibling::td//input",job,"Name "+job);

				oASelFW.effecta("click","//span[text()='Search']","Search");
				if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='No items found']")))
				{
					oASelFW.effecta("sendReportWithOutExit","Enter the Valid Name ","No items Found for the given job name:  "+jobName,"Pass");
				}
				else{
					oASelFW.effecta("clickAndWait","//span[text()='Name']/../../following-sibling::tr/descendant::td[2]//a","Job Name Displayed");

					oASelFW.driver.navigate().back();
					System.out.println("Clicked on the displayed job name ");
				}
			}
			oASelFW.driver.switchTo().defaultContent();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/* @Author - Mangala 
	 * Description - This method will VALIDATES LOCATION DETAILS PAGE WITH DB 
	 * 
	 */



	public void compare_LocationDetailsPage_DB(String locationNumber,String locationName,String Domain,String city,String state,String Country,String Enabled)
	{

		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		oASelFW.effecta("waitForElementPresent","//span[text()='Number*']","60");
		String location_number=oASelFW.effecta("getValue","//span[text()='Number*']/following::td/input[@name='comp_loc_no']","Location Number");
		System.out.println("location_number:"+location_number);
		String toupperlocationNumber=locationNumber.toUpperCase();
		oASelFW.effecta("verifyElementPresent","//span[text()='Number*']/following::input[@value='"+toupperlocationNumber+"']","locationNumber: "+toupperlocationNumber+" is present in UI");
		oASelFW.effecta("verifyElementPresent","//span[text()='Number*']/following::input[@value='"+locationName+"']","locationName: "+locationName+" is present in UI");
		if(Domain.equalsIgnoreCase("None"))
		{
			Domain="";
			String value=oASelFW.effecta("getText","//span[text()='Number*']/following::input[@value='"+Domain+"']","Getting value of Domain");

			if(value.equals(Domain))
			{
				oASelFW.effecta("sendReport","Verified state is having null value "+value+"","Succesfully verified state "+value+"","Pass");

			}
			else{
				oASelFW.effecta("sendReportWithOutExit","Verified state is not  having null value "+value+""," verified state "+value+"","Fail");

			}

		}
		else
		{
			oASelFW.effecta("verifyElementPresent","//span[text()='Number*']/following::input[@value='"+Domain+"']","Domain: "+Domain+" is present in UI");

		}

		if(city.equalsIgnoreCase("None"))
		{
			city="";
			String value=oASelFW.effecta("getText","//span[text()='Number*']/following::input[@value='"+city+"']","Getting value of Domain");

			if(value.equals(city))
			{
				oASelFW.effecta("sendReport","Verified state is having null value "+value+"","Succesfully verified state "+value+"","Pass");

			}
			else{
				oASelFW.effecta("sendReportWithOutExit","Verified state is not  having null value "+value+""," verified state "+value+"","Fail");

			}

		}
		else
		{
			oASelFW.effecta("verifyElementPresent","//span[text()='Number*']/following::input[@value='"+city+"']","city: "+city+" is present in UI");

		}


		if(state.equalsIgnoreCase("None"))
		{
			state="";
			String value=oASelFW.effecta("getText","//span[text()='Number*']/following::input[@value='"+state+"']","Getting value of State");

			if(value.equals(state))
			{
				oASelFW.effecta("sendReport","Verified state is having null value "+value+"","Succesfully verified state "+value+"","Pass");

			}
			else{
				oASelFW.effecta("sendReportWithOutExit","Verified state is not  having null value "+value+""," verified state "+value+"","Fail");

			}

		}
		else
		{
			oASelFW.effecta("verifyElementPresent","//span[text()='Number*']/following::input[@value='"+state+"']","state: "+state+" is present in UI");

		}


		if(Country.equalsIgnoreCase("None"))
		{
			Country="";
			String value=oASelFW.effecta("getText","//span[text()='Number*']/following::input[@value='"+Country+"']","Getting value of State");

			if(value.equals(Country))
			{
				oASelFW.effecta("sendReport","Verified state is having null value "+value+"","Succesfully verified state "+value+"","Pass");

			}
			else{
				oASelFW.effecta("sendReportWithOutExit","Verified state is not  having null value "+value+""," verified state "+value+"","Fail");

			}

		}
		else
		{
			oASelFW.effecta("verifyElementPresent","//span[text()='Number*']/following::input[@value='"+Country+"']","JobFamilyGroup: "+Country+" is present in UI");

		}


		String Enable_Value=oASelFW.effecta("getValue","//label[text()='Enabled']/following::td/input[@name='comp_enabled']","Enabled value");
		System.out.println("Enable_Value :"+Enable_Value);
		String toupperEnable=Enable_Value.toUpperCase();
		if(toupperEnable.equals(Enabled))
		{
			oASelFW.effecta("sendReport","Verified enabled checkbox is "+toupperEnable+"","sucessfully verified enabled value from source "+Enabled+"and target "+Enable_Value+" files are equal","Pass");
		}
		else
		{
			oASelFW.effecta("sendReportWithOutExit","Verified enabled checkbox is "+toupperEnable+"","sucessfully verified enabled value from source "+Enabled+"and target "+Enable_Value+" files are not equal","Fail");

		}
		oASelFW.driver.switchTo().defaultContent();

	}
	/*
	 * @author - Arifuddin Mohd
	 * Description - This method will verify the manditory fields in the Saba Application for location Details Page.
	 */
	public void verifyLocationDetailsPage(String toupperlocationNumber,String locationName, String Domain, String timezone)
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		oASelFW.effecta("waitForElementPresent","//span[text()='Number*']","60");
		oASelFW.effecta("verifyElementPresent","//span[text()='Number*']/following::input[@value='"+toupperlocationNumber+"']","Location Number: "+toupperlocationNumber+" is present in UI");
		oASelFW.effecta("verifyElementPresent","//span[text()='Name*']/following::input[@value='"+locationName+"']","Location Name: "+locationName+" is present in UI");
		oASelFW.effecta("verifyElementPresent","//span[text()='Domain*']/following::input[@value='"+Domain+"']","Domain: "+Domain+" is present in UI");
		oASelFW.effecta("verifyElementPresent","//span[text()='TimeZone*']/../following-sibling::td//select//option[text()='"+timezone+"']","TimeZone: "+timezone+" is present in UI");
		oASelFW.driver.switchTo().defaultContent();

	}


	public void compare_TimeZOne_Location(String Timezone)
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		oASelFW.effecta("waitForElementPresent","//span[text()='Number*']/following::select","60");
		Select select=new Select(oASelFW.driver.findElement(By.xpath("//span[text()='Number*']/following::select")));
		WebElement selectedValue=select.getFirstSelectedOption();

		String value=selectedValue.getText();
		System.out.println("value:"+value);
		oASelFW.driver.switchTo().defaultContent();


	}


	public void compare_RolesDetailsPage(String RoleName,String Description,String Domain)
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		oASelFW.effecta("waitForElementPresent","//span[text()='Name*']","60");
		oASelFW.effecta("verifyElementPresent","//span[text()='Name*']/following::input[@value='"+RoleName+"']","RoleName: "+RoleName+" is present in UI");
		oASelFW.effecta("verifyElementPresent","//span[text()='Name*']/following::input[@value='"+Description+"']","Description: "+Description+" is present in UI");
		oASelFW.effecta("verifyElementPresent","//span[text()='Name*']/following::input[@value='"+Domain+"']","Domain: "+Domain+" is present in UI");
		oASelFW.driver.switchTo().defaultContent();

	}

	public void compare_JobFamilyDetailsPage(String JobFamilyName)
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		oASelFW.effecta("waitForElementPresent","//span[text()='Name*']","60");
		oASelFW.effecta("verifyElementPresent","//span[text()='Name*']/following::input[@value='"+JobFamilyName+"']","JobFamilyName: "+JobFamilyName+" is present in UI");
	}
	/*
	 * @Author - Arifuddin Mohd
	 * Description - This method will click on the Link for Creating New Activity[New Class,New Catalog Item,.......]
	 */
	public void clickOnNewLocationLink(String value)
	{
		try {
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			//oASelFW.driver.switchTo().defaultContent();
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			oASelFW.effecta("clickAndWait","//span[text()='"+value+"']/../following-sibling::td//a","New "+value);
			oASelFW.driver.switchTo().defaultContent();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}



	/*
	 * @author - Arifuddin Mohd
	 * Description - This method will create the new location .
	 */
	public void newLocationInformationDetailsPage(String number, String name, String domain, String timezone) throws InterruptedException//(GMT-08:00) Pacific Time (US & Canada), Tijuana
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

		oASelFW.effecta("type","//span[text()='Number*']/../following-sibling::td//input", number,"Number"+number);
		oASelFW.effecta("type","//span[text()='Name*']/../following-sibling::td//input",name,"Name "+name);
		oASelFW.effecta("clickAndWait","//span[text()='Domain*']/../following-sibling::td//a[@title='Pick Domain']","Look Up","Pass");
		String window[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window[1]);
		Thread.sleep(1000);
		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='Search']")))
		{
			System.out.println("in select domain window");
			oASelFW.effecta("type","//label[text()='Name']/../../following-sibling::td//input", domain,"Domain "+domain);
			oASelFW.effecta("clickAndWait","//a[@title='Search']","Search","Pass");
			Thread.sleep(2000);
			oASelFW.effecta("clickAndWait","//span[text()='"+domain.trim()+"']/../../descendant::td[2]//a//img", domain,"Pass");
		}
		oASelFW.effecta("selectWindow",window[0]);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		Select dropdown = new Select(oASelFW.driver.findElement(By.xpath("//span[text()='TimeZone*']/../following-sibling::td//select")));
		dropdown.selectByVisibleText(timezone);
		switch_to_default_content();

	}
	/*
	 * @author - Arifuddin Mohd
	 * Description - This method will create the New Facility in facility page.
	 */
	public void createNewFacilityInFacilitypage(String number, String name, String admin, String domain,String location) throws InterruptedException//(GMT-08:00) Pacific Time (US & Canada), Tijuana
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

		oASelFW.effecta("type","//span[text()='Facility Number*']/../following-sibling::td//input", number,"Facility Number");
		oASelFW.effecta("type","//span[text()='Name*']/../following-sibling::td//input",name,"Name");
		oASelFW.effecta("type","//span[text()='Administrator*']/../following-sibling::td//input",admin,"Administrator");
		oASelFW.effecta("clickAndWait","//label[text()='Location']/../../following-sibling::td//img","Look Up","Pass");
		String window11[] = oASelFW.effectaArray("getAllWindowNames");
		//oASelFW.effecta("selectWindow",window11[1]);
		Thread.sleep(1000);
		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='Search']")))
		{
			System.out.println("in select domain window");
			oASelFW.effecta("type","//label[text()='Name']/../../following-sibling::td//input", location,"Location");
			oASelFW.effecta("clickAndWait","//a[@title='Search']","Search","Pass");
			Thread.sleep(2000);
			oASelFW.effecta("clickAndWait","//span[text()='"+location.trim()+"']/../../descendant::td[2]//a//img", domain,"Pass");
		}
		oASelFW.effecta("selectWindow",window11[0]);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		oASelFW.effecta("clickAndWait","//span[text()='Domain*']/../following-sibling::td//a[@title='Pick Domain']","Look Up","Pass");
		String window[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window[1]);
		Thread.sleep(1000);
		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='Search']")))
		{
			System.out.println("in select domain window");
			oASelFW.effecta("type","//label[text()='Name']/../../following-sibling::td//input", domain,"Domain "+domain);
			oASelFW.effecta("clickAndWait","//a[@title='Search']","Search","Pass");
			Thread.sleep(2000);
			oASelFW.effecta("clickAndWait","//span[text()='"+domain.trim()+"']/../../descendant::td[2]//a//img", domain,"Pass");
		}
		oASelFW.effecta("selectWindow",window[0]);
		switch_to_default_content();

	}


	public void saveLocationInfomationData()
	{
		try {
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			Thread.sleep(3000);
			oASelFW.effecta("clickAndWait","//a[@title='Save']//span","Save Button","Pass");
			switch_to_default_content();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void validateDuplicationLocationMessage()
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		oASelFW.effecta("getText","//span[@class='sbMainPageInstructions']","Duplicate Record Found");
		switch_to_default_content();

	}
	/*
	 * @Author - Arifuddin Mohd
	 * Description - This method will Select the Delivery Type and Class Couses Name in New Class Page.
	 */
	public void createNewClassWithCourseAndDelivery(String course, String deliveryType) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		Thread.sleep(3000);
		oASelFW.effecta("clickAndWait","//span[text()='Based on Course*']/../following-sibling::td//a//img","Look Up","Pass");
		String window[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window[1]);
		Thread.sleep(3000);
		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='Search']")))
		{
			System.out.println("in select domain window");
			oASelFW.effecta("type","//label[text()='Title']/../../following-sibling::td//input", course.trim(),"Course");
			oASelFW.effecta("clickAndWait","//a[@title='Search']","Search","Pass");
			Thread.sleep(2000);
			oASelFW.effecta("clickAndWait","//span[text()='"+course.trim()+"']/../../descendant::td[2]//a//img", course,"Pass");
		}
		oASelFW.effecta("selectWindow",window[0]);

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		oASelFW.effecta("select","//span[text()='Delivery Type*']/../following-sibling::td//select",deliveryType.trim(),"Pass");
		oASelFW.effecta("clickAndWait","//a[@title='Next']//span","Next Button","Pass");
		switch_to_default_content();

	}
	/*
	 * @Author - Arifuddin Mohd
	 * Description - This method will verify the Delivery Types DropDown in Class links
	 */
	public void verifyDeliveryTypesInManageClasses(String course)
	{
		try {
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			oASelFW.effecta("clickAndWait","//span[text()='Based on Course*']/../following-sibling::td//a//img","Look Up","Pass");
			String window[] = oASelFW.effectaArray("getAllWindowNames");
			oASelFW.effecta("selectWindow",window[1]);
			Thread.sleep(3000);
			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='Search']")))
			{
				System.out.println("in select domain window");
				oASelFW.effecta("type","//label[text()='Title']/../../following-sibling::td//input", course.trim(),"Course "+course);
				oASelFW.effecta("clickAndWait","//a[@title='Search']","Search","Pass");
				Thread.sleep(2000);
				oASelFW.effecta("clickAndWait","//span[text()='"+course.trim()+"']/../../descendant::td[2]//a//img", course,"Pass");
			}
			oASelFW.effecta("selectWindow",window[0]);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			String cnt=oASelFW.effecta("getXpathCount","//span[text()='Delivery Type*']/../following-sibling::td//select//option");
			int count=Integer.parseInt(cnt);
			System.out.println("count is ***********"+count);	
			System.out.println("Delivery Types length ***************"+count);
			for(int i=2; i<=count; i++)
			{
				//String DeliveryTypes = oASelFW.effecta("getText","//span[text()='Delivery Type*']/../following-sibling::td//select//option["+i+"]","Delivery Types");
				String DeliveryTypes =oASelFW.driver.findElement(By.xpath("//span[text()='Delivery Type*']/../following-sibling::td//select//option["+i+"]")).getText();
				oASelFW.effecta("sendReport", "Retriving Delivery Types for "+course+" Course", "Successfully Validating the Delivery Types "+DeliveryTypes,"Pass");
			}
			switch_to_default_content();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	/*@author - arifuddin Mohd
	 * Description - This method will select the Learning Event Type from Learning Catalog Page.
	 * [Curriculum, Courses, Curriculum]
	 */
	public void learningCatalog_LearningEventType(String type, String name)
	{
		try {

			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			oASelFW.effecta("select","//label[contains(text(),'Learning Event Type')]/../../following-sibling::td//select", type.trim()," Event Type"+type);
			Thread.sleep(1000);
			oASelFW.effecta("clickAndWait","//span[text()='Search']","Search");
			Thread.sleep(2000);
			oASelFW.effecta("type","//label[contains(text(),'Name')]/../../following-sibling::td//input",name,"Name");
			Thread.sleep(1000);
			oASelFW.effecta("clickAndWait","//span[text()='Search']","Search");
			oASelFW.driver.switchTo().defaultContent();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	/*
	 * @Author - Arifuddin Mohd
	 * Description - This method will click on the search Results from Catalog Search Display.
	 */
	public Boolean searchManage_JobName(String jobName)
	{
		boolean success = false;
		try {
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			System.out.println("Entering the text  "+jobName);

			oASelFW.effecta("type","//label[text()='Title']/../..//following-sibling::td//input&&//label[text()='Name']/../..//following-sibling::td//input",jobName, "JobName");
			Thread.sleep(1000);
			oASelFW.effecta("clickAndWait","//span[text()='Search']","Search");
			Thread.sleep(1000);
			System.out.println("items found "+jobName);
			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='No items found']")))
			{
				oASelFW.effecta("sendReportWithOutExit","Enter the Valid Name ","No items Found for the given job name:  "+jobName,"Pass");
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
	public Boolean searchManage_JobNameWithClassID(String ClassID)
	{
		boolean success = false;
		try {
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			System.out.println("Entering the text  "+ClassID);

			oASelFW.effecta("type","//label[text()='Class ID']/../..//following-sibling::td//input",ClassID, "ClassID");
			Thread.sleep(2000);
			oASelFW.effecta("clickAndWait","//span[text()='Search']","Search");
			Thread.sleep(3000);
			System.out.println("no items found "+ClassID);
			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='No items found']")))
			{
				oASelFW.effecta("sendReportWithOutExit","Enter the Valid Name ","No items Found for the given job name:  "+ClassID,"Pass");
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

	public Boolean searchManage_JobNameWithClassIDAndNavigate(String ClassID)//Course ID, Title
	{ 
		boolean success = false;
		try {
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			System.out.println("Entering the text  "+ClassID);
			Actions act = new Actions(oASelFW.driver);
			if(!ClassID.isEmpty())
			{
				oASelFW.effecta("type","//label[text()='Class ID']/../..//following-sibling::td//input&&//label[text()='Title']/../..//following-sibling::td//input",ClassID, ClassID);
				Thread.sleep(1000);
				oASelFW.effecta("clickAndWait","//span[text()='Search']","Search");
				Thread.sleep(2000);
				System.out.println("items found "+ClassID);
				if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='No items found']")))
				{
					oASelFW.effecta("sendReportWithOutExit","Enter the Valid Name ","No items Found for the given job name:  "+ClassID,"Pass");
				}
				else{
					success = true;
					act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Title']/../../following-sibling::tr[1]//td[2]//a"))).build().perform();
					act.click().build().perform();
					Thread.sleep(3000);
				}
			}else
			{
				oASelFW.effecta("sendReportWithOutExit","No Text Found in Search Reports ","No Text Found in Search Reports ","Pass");
			}


			oASelFW.driver.switchTo().defaultContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Boolean status "+success);

		switch_to_default_content();
		return success;

	}
	public Boolean searchManage_JobNameWithClassIDAndNavigate_01(String field, String value)//Course ID, Title
	{ 
		boolean success = false;
		try {
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			System.out.println("Entering the text  "+value);
			Actions act = new Actions(oASelFW.driver);
			if(!value.isEmpty())
			{
				oASelFW.effecta("type","//label[text()='"+field+"']/../..//following-sibling::td//input",value, value);
				Thread.sleep(1000);
				oASelFW.effecta("clickAndWait","//span[text()='Search']","Search");
				Thread.sleep(2000);
				System.out.println("items found "+value);
				if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='No items found']")))
				{
					oASelFW.effecta("sendReportWithOutExit","Enter the Valid Name ","No items Found for the given job name:  "+value,"Pass");
				}
				else{
					success = true;
					act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Title']/../../following-sibling::tr[1]//td[2]//a"))).build().perform();
					act.click().build().perform();
					Thread.sleep(3000);
				}
			}else
			{
				oASelFW.effecta("sendReportWithOutExit","No Text Found in Search Reports ","No Text Found in Search Reports ","Pass");
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
	 * Description - This method will click on the search Results from session Template Search Display.
	 */

	public boolean sessionTemplateWithStartDay(String jobName, String weekDay)
	{
		boolean success = false;
		try {
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			System.out.println("Entering the text  "+jobName);

			oASelFW.effecta("type","//label[text()='Name']/../..//following-sibling::td//input",jobName, "JobName");
			Thread.sleep(2000);
			oASelFW.effecta("select","//label[text()='Start Day']/../..//following-sibling::td//select",weekDay, "Start Day");
			oASelFW.effecta("clickAndWait","//span[text()='Search']","Search");
			System.out.println("no items found "+jobName);
			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='No items found']")))
			{
				oASelFW.effecta("sendReportWithOutExit","Enter the Valid Name ","No items Found for the given job name:  "+jobName,"Pass");
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
	 * Description - This method will click on the search Results from Catalog Search Display using classid and title.
	 */
	public Boolean searchManage_JobNameAndClassId(String jobName, String classID, String status)
	{
		boolean success = false;
		try {
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			System.out.println("Entering the text  "+jobName);

			oASelFW.effecta("type","//label[text()='Title']/../..//following-sibling::td//input",jobName, "JobName");
			Thread.sleep(2000);
			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//label[text()='Class ID']/../..//following-sibling::td//input")))
			{
				Thread.sleep(2000);
				oASelFW.effecta("type","//label[text()='Class ID']/../..//following-sibling::td//input",classID, "Class ID");
				Thread.sleep(3000);
				oASelFW.effecta("select","//label[text()='Status']/../../following-sibling::td//select",status,"Delivery Type");
			}else
			{}
			oASelFW.effecta("clickAndWait","//span[text()='Search']","Search");
			Thread.sleep(3000);
			System.out.println("no items found "+jobName);
			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='No items found']")))
			{
				oASelFW.effecta("sendReportWithOutExit","Enter the Valid Name ","No items Found for the given job name:  "+jobName,"Pass");
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
	 * Description - This method will select the Catalog Search Links from Main content Page.
	 */
	public void clickOnTheCatalogSearchActions(String action) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		Thread.sleep(5000);
		//oASelFW.effecta("click","//span[text()='Actions']/../../following-sibling::tr[1]//a[text()='"+action.trim()+"']&&//a[text()='"+action.trim()+"']","Actions "+action,"Pass");
		Actions act = new Actions(oASelFW.driver);
		act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[text()='"+action.trim()+"']"))).build().perform();
		act.click().build().perform();
		Thread.sleep(3000);
		oASelFW.effecta("sendReport","Clicking on the Actions Link "+action,"Successfully clicked on Action "+action,"Pass");
		System.out.println("Clicked on ******************************>>"+action);
		Thread.sleep(2000);
		switch_to_default_content();
	}
	/*
	 * @Author - Arifuddin Mohd
	 * Description - This method will select the Catalog Search Links from Main content Page.
	 * [Edit, Advanced Edit, New Class, Assign Course, View Classes,.....]
	 */
	public void clickOnTheDisplayedCatalogSearchActionsLinks(String name) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

		Actions act = new Actions(oASelFW.driver);
		act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[contains(text(),'"+name+"')]"))).build().perform();
		act.click().build().perform();
		Thread.sleep(2000);
		oASelFW.effecta("sendReportWithOutExit","Clicking on the Link "+name,"Successfully clicked on "+name,"Pass");
		System.out.println("Clicked on ******************************>>"+name);

		switch_to_default_content();
	}

	public void viewSelectClassInformationFromDisplayedPage() throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		String text="";
		Thread.sleep(3000);
		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//th[text()='ID']/../../tr")))
		{
			String cnt=oASelFW.effecta("getXpathCount","//th[text()='ID']/../../tr");
			int count=Integer.parseInt(cnt);
			System.out.println("count is ***********"+count);	
			Thread.sleep(5000);
			for(int i=2; i<=count; i++){
				Thread.sleep(2000);
				text=oASelFW.driver.findElement(By.xpath("//th[text()='ID']/../../tr["+i+"]")).getText();
				Thread.sleep(2000);
				System.out.println(text+"*******");
				oASelFW.effecta("sendReportWithOutExit","Retriving the Select Class ID As ","Successfully Retrivied the Select Class ID as "+text+" ","Pass");
				Thread.sleep(2000);

			}
		}
		else{
			oASelFW.effecta("sendReportWithOutExit","Validating Records from Class Details page ","No Records Found ","Pass");

		}
		switch_to_default_content();
	}

	public void newDefineClassInfomation(String classID,String domain, String startDate,  String sessionTime, String sessionday,String location, String lang, String MinCount, String MaxCount, String MaxWList) throws InterruptedException, ParseException
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='Class ID*']")))
		{
			oASelFW.effecta("type","//span[text()='Class ID*']/../../following-sibling::td//input&&//span[text()='Class ID*']/../following-sibling::td//input",classID,"Class ID "+classID);
		}
		else
		{
			Thread.sleep(3000);
			String classid = oASelFW.effecta("getText","//span[text()='Class ID']/../../following-sibling::td//span&&//span[text()='Class ID']/../following-sibling::td//span","Class ID");
			Thread.sleep(2000);
			oASelFW.insertDataIntoAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData","update EndToEnd set Class_ID='"+classid+"' where ID="+sessionday+"");
			Thread.sleep(3000);
		}

		oASelFW.effecta("clickAndWait","//span[text()='Domain*']/../following-sibling::td//a//img&&//span[text()='Domain*']/../../following-sibling::td//a//img","Look Up","Pass");
		String window[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window[1]);
		Thread.sleep(1000);
		System.out.println("in select domain window");
		oASelFW.effecta("type","//label[text()='Name']/../../following-sibling::td//input", domain,"Domain");
		oASelFW.effecta("clickAndWait","//a[@title='Search']","Search","Pass");
		Thread.sleep(1000);
		oASelFW.effecta("clickAndWait","//span[text()='"+domain.trim()+"']/../../descendant::td[2]//a//img", domain,"Pass");
		oASelFW.effecta("selectWindow",window[0]);
		switch_to_default_content();

		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='Start Date*']/../../following-sibling::td//a//img")))
		{
			/*oASelFW.effecta("clickAndWait","//span[text()='Start Date*']/../../following-sibling::td//a//img","Look Up","Pass");
			String window4[] = oASelFW.effectaArray("getAllWindowNames");
			oASelFW.effecta("selectWindow", window4[1]);
			Thread.sleep(1000);
			System.out.println("in select date window");*/

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
			Calendar cal = Calendar.getInstance();
			Date date1 = cal.getTime();
			cal.add(Calendar.DATE, 2);
			Date nextYear = cal.getTime();
			String enddatetime = sdf.format(nextYear);
			System.out.println(enddatetime);
			String[] date = enddatetime.split("-");
			String month = date[1];
			String day = date[0];
			String year = date[2];
			System.out.println("month "+month);
			System.out.println("year "+year);
			System.out.println("day "+day);

			System.out.println("Day from calendar is*************"+day);
			System.out.println("Now the date is :=>  " + enddatetime);
			

			JavascriptExecutor js = (JavascriptExecutor) oASelFW.driver;  
			js.executeScript("arguments[0].setAttribute('value','"+day+"-"+month+"-"+year+"');",oASelFW.driver.findElement(By.xpath("//span[text()='Start Date*']/following::input[1]")));
			Thread.sleep(2000);

			Actions act = new Actions(oASelFW.driver);

			/*act.moveToElement(oASelFW.driver.findElement(By.xpath("//tr[@class='sbDatePickTableProperties']//select[1]"))).build().perform();
			act.click().build().perform();
			Thread.sleep(2000);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//tr[@class='sbDatePickTableProperties']//select[1]//option[text()='"+month+"']"))).build().perform();
			act.click().build().perform();
			Thread.sleep(3000);


			act.moveToElement(oASelFW.driver.findElement(By.xpath("//tr[@class='sbDatePickTableProperties']//select[2]"))).build().perform();
			act.click().build().perform();
			Thread.sleep(2000);

			act.moveToElement(oASelFW.driver.findElement(By.xpath("//tr[@class='sbDatePickTableProperties']//select[2]//option[text()='"+year+"']"))).build().perform();
			act.click().build().perform();
			Thread.sleep(3000);*/
			Thread.sleep(3000);
			
			/*act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[contains(text(),'"+day+"')]"))).build().perform();
			act.click().build().perform();
			System.out.println("selected date  "+day);*/

			/*oASelFW.effecta("clickAndWait","//a[text()='"+day+"+*']", day,"Pass");
			Thread.sleep(2000);*/
			//oASelFW.effecta("selectWindow",window4[0]);
			switch_to_default_content();

			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			Thread.sleep(2000);

			oASelFW.effecta("clickAndWait","//span[text()='Session Template*']/../following-sibling::td//a//img&&//span[text()='Session Template*']/../../following-sibling::td//a//img", "Session Edit Img","Pass");
			Thread.sleep(2000);

			String window1[] = oASelFW.effectaArray("getAllWindowNames");
			oASelFW.effecta("selectWindow",window1[1]);
			Thread.sleep(1000);
			System.out.println("in select domain window");
			String dayText = "";
			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//label[text()='Start Day']/../../following-sibling::td//select")))
			{
				oASelFW.effecta("select","//label[text()='Start Day']/../../following-sibling::td//select", day.trim(),"Start Day "+day);
				Thread.sleep(3000);
				dayText = oASelFW.effecta("getText","//label[text()='Start Day']/../../following-sibling::td//select//option[@selected='']","Start Day");
				System.out.println("Day name "+dayText);
				Thread.sleep(2000);
				oASelFW.insertDataIntoAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData","update ManageClassesData set SessionStartDay='"+dayText+"' where ID="+sessionday+"");
				Thread.sleep(3000);
				oASelFW.insertDataIntoAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData","update CurriculaRelatedData set SessionStartDay='"+dayText+"' where ID="+sessionday+"");


				oASelFW.effecta("type","//label[contains(text(),'Duration')]/../../following-sibling::td//input", sessionTime,"Session Time");
				oASelFW.effecta("clickAndWait","//a[@title='Search']","Search","Pass");
				Thread.sleep(2000);
				oASelFW.effecta("clickAndWait","//span[text()='Select']/../../following::tr[1]/td[2]//input&&//span[text()='Select']/../../following-sibling::tr[2]/td[2]//input", "Radio Button","Pass");
				oASelFW.effecta("clickAndWait","//span[text()='Select And Close']", "Select And Close","Pass");
			}
			else{
				//add session here

				if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='Sessions']/../following-sibling::td//a")))
				{

					oASelFW.effecta("clickAndWait","//span[text()='Sessions']/../following-sibling::td//a","Add Session","Pass");

					String[] window6 = oASelFW.effectaArray("getAllWindowNames");
					window6 = oASelFW.effectaArray("getAllWindowNames");
					oASelFW.effecta("selectWindow",window6[window6.length-1]);

					JavascriptExecutor jack = (JavascriptExecutor) oASelFW.driver;  
					jack.executeScript("arguments[0].setAttribute('value','"+startDate+"-"+month+"-"+year+"');",oASelFW.driver.findElement(By.xpath("//span[text()='Start Date*']/../following-sibling::td//input")));
					Thread.sleep(2000);

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
					oASelFW.effecta("clickAndWait","//span[text()='Save']", "Save","Pass");
					Thread.sleep(2000);
					oASelFW.effecta("selectWindow",window6[window6.length-1]);
					oASelFW.driver.close();
					oASelFW.effecta("selectWindow",window1[window1.length-1]);

					Thread.sleep(3000);
					dayText = oASelFW.effecta("getText","//span[text()='Day']/../../following-sibling::tr/descendant::td[8]//span","Day");
					Thread.sleep(2000);
					oASelFW.insertDataIntoAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData","update ManageClassesData set SessionStartDay='"+dayText+"' where ID="+sessionday+"");
					Thread.sleep(3000);
					oASelFW.effecta("clickAndWait","//span[text()='Finish']", "Finish","Pass");

				}
				else{

				}
			}
			oASelFW.effecta("selectWindow",window1[0]);

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

			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//label[text()='Facility']/../../../following-sibling::td//a//img&&//label[text()='Facility']/../../following-sibling::td//a//img")))
			{
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
				oASelFW.effecta("clickAndWait","//label[text()='Facility']/../../../following-sibling::td//a//img&&//label[text()='Facility']/../../following-sibling::td//a//img","Look Up","Pass");
				String window6[] = oASelFW.effectaArray("getAllWindowNames");
				oASelFW.effecta("selectWindow",window6[1]);
				Thread.sleep(2000);
				System.out.println("in select Facility window");
				oASelFW.effecta("type","//label[text()='Name']/../../following-sibling::td//input", location,"Location");
				oASelFW.effecta("clickAndWait","//a[@title='Search']","Search","Pass");
				Thread.sleep(3000);
				oASelFW.effecta("clickAndWait","//span[text()='Select']/../../following-sibling::tr/td[2]//a//img", "Facility Name","Pass");
				Thread.sleep(3000);
				oASelFW.effecta("selectWindow",window6[0]);
			}
			else{}
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			oASelFW.effecta("clickAndWait","//span[text()='Language*']/../../following-sibling::td//a//img&&//span[text()='Language*']/../following-sibling::td//a//img","Look Up","Pass");
			String window3[] = oASelFW.effectaArray("getAllWindowNames");
			oASelFW.effecta("selectWindow",window3[1]);
			Thread.sleep(1000);
			System.out.println("in select domain window");
			oASelFW.effecta("type","//label[text()='Name']/../../following-sibling::td//input", lang,"Location");
			oASelFW.effecta("clickAndWait","//a[@title='Search']","Search","Pass");
			Thread.sleep(2000);
			oASelFW.effecta("clickAndWait","//span[text()='"+lang.trim()+"']/../../descendant::td[2]//a//img", lang,"Pass");
			oASelFW.effecta("selectWindow",window3[0]);
			switch_to_default_content();

			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

			oASelFW.effecta("type","//span[text()='Min Count*']/../../following-sibling::td//input[2]", MinCount,"Min Count");
			oASelFW.effecta("type","//span[text()='Max Count*']/../../following-sibling::td//input[2]", MaxCount,"Max Count");
			oASelFW.effecta("type","//span[text()='Max In Wait List*']/../../following-sibling::td//input[2]", MaxWList,"Max In Wait List");

			switch_to_default_content();

		}else
		{
			oASelFW.effecta("clickAndWait","//span[text()='Language*']/../../following-sibling::td//a//img&&//span[text()='Language*']/../following-sibling::td//a//img","Look Up","Pass");
			String window3[] = oASelFW.effectaArray("getAllWindowNames");
			oASelFW.effecta("selectWindow",window3[1]);
			Thread.sleep(1000);
			System.out.println("in select domain window");
			oASelFW.effecta("type","//label[text()='Name']/../../following-sibling::td//input", lang,"Location");
			oASelFW.effecta("clickAndWait","//a[@title='Search']","Search","Pass");
			Thread.sleep(2000);
			oASelFW.effecta("clickAndWait","//span[text()='"+lang.trim()+"']/../../descendant::td[2]//a//img", lang,"Pass");
			oASelFW.effecta("selectWindow",window3[0]);
			switch_to_default_content();
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			oASelFW.effecta("type","//label[contains(text(),'Duration')]/../../following-sibling::td//input", sessionTime,"Session Time");

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

			oASelFW.effecta("clickAndWait","//a[text()='"+startDate+"']", ""+startDate+"/"+month+"/"+year+"","Pass");
			Thread.sleep(2000);
			oASelFW.effecta("selectWindow",window1[0]);

		}


	}

	public void CreateClassToEnrollLearnerToVLT(String classID,String domain, String startDate,  String sessionTime, String sessionday,String location, String lang, String MinCount, String MaxCount, String MaxWList) throws InterruptedException, ParseException
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		oASelFW.effecta("type","//span[text()='Class ID*']/../../following-sibling::td//input&&//span[text()='Class ID*']/../following-sibling::td//input",classID,"Class ID "+classID);

		Thread.sleep(3000);
		//String courseID = oASelFW.effecta("getText","//span[text()='Course ID']/../following-sibling::td//span","Course ID");
		Thread.sleep(2000);
		//oASelFW.insertDataIntoAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData","update ManageClassesData set Course_Title='"+courseID+"' where ID="+sessionday+"");
		Thread.sleep(3000);

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

			act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[contains(text(),'"+startDate+"')]"))).build().perform();
			act.click().build().perform();
			System.out.println("selected date  "+startDate);

			//oASelFW.effecta("clickAndWait","//a[text()='"+startDate+"']", ""+startDate+"/"+month+"/"+year+"","Pass");
			Thread.sleep(2000);
			oASelFW.effecta("selectWindow",window[0]);
			switch_to_default_content();

			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			oASelFW.effecta("clickAndWait","//span[text()='Session Template*']/../../following-sibling::td//a//img&&//span[text()='Session Template*']/../following-sibling::td//a//img", "Session Edit Img","Pass");
			String window1[] = oASelFW.effectaArray("getAllWindowNames");
			oASelFW.effecta("selectWindow",window1[1]);
			Thread.sleep(1000);
			System.out.println("in select domain window");
			String dayText = "";
			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//label[text()='Start Day']/../../following-sibling::td//select")))
			{
				oASelFW.effecta("select","//label[text()='Start Day']/../../following-sibling::td//select", day.trim(),"Start Day"+day);
				Thread.sleep(3000);
				dayText = oASelFW.effecta("getText","//label[text()='Start Day']/../../following-sibling::td//select//option[@selected='']","Start Day");
				Thread.sleep(2000);
				oASelFW.insertDataIntoAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData","update ManageClassesData set SessionStartDay='"+dayText+"' where ID="+sessionday+"");

				oASelFW.effecta("type","//label[contains(text(),'Duration')]/../../following-sibling::td//input", sessionTime,"Session Time");
				oASelFW.effecta("clickAndWait","//a[@title='Search']","Search","Pass");
				Thread.sleep(2000);
				oASelFW.effecta("clickAndWait","//span[text()='Select']/../../following-sibling::tr[2]/td[2]//input", "Radio Button","Pass");
				oASelFW.effecta("clickAndWait","//span[text()='Select And Close']", "Select And Close","Pass");
			}
			else{
				//add session here

				if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='Sessions']/../following-sibling::td//a")))
				{
					Thread.sleep(3000);
					oASelFW.effecta("clickAndWait","//span[text()='Sessions']/../following-sibling::td//a","Add Session","Pass");

					String[] window6 = oASelFW.effectaArray("getAllWindowNames");
					window6 = oASelFW.effectaArray("getAllWindowNames");
					oASelFW.effecta("selectWindow",window6[window6.length-1]);

					JavascriptExecutor js = (JavascriptExecutor) oASelFW.driver;  
					js.executeScript("arguments[0].setAttribute('value','"+startDate+"-"+month+"-"+year+"');",oASelFW.driver.findElement(By.xpath("//span[text()='Start Date*']/../following-sibling::td//input")));
					Thread.sleep(4000);

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
					oASelFW.effecta("clickAndWait","//span[text()='Save']", "Save","Pass");
					Thread.sleep(2000);
					oASelFW.effecta("selectWindow",window6[window6.length-1]);
					oASelFW.driver.close();
					oASelFW.effecta("selectWindow",window1[window1.length-1]);

					Thread.sleep(3000);
					dayText = oASelFW.effecta("getText","//span[text()='Day']/../../following-sibling::tr/descendant::td[8]//span","Day");
					Thread.sleep(2000);
					oASelFW.insertDataIntoAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData","update ManageClassesData set SessionStartDay='"+dayText+"' where ID="+sessionday+"");
					Thread.sleep(3000);
					oASelFW.effecta("clickAndWait","//span[text()='Finish']", "Finish","Pass");

				}
				else{

				}
			}
			oASelFW.effecta("selectWindow",window1[0]);

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


			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			oASelFW.effecta("clickAndWait","//span[text()='Language*']/../../following-sibling::td//a//img&&//span[text()='Language*']/../following-sibling::td//a//img","Look Up","Pass");
			String window3[] = oASelFW.effectaArray("getAllWindowNames");
			oASelFW.effecta("selectWindow",window3[1]);
			Thread.sleep(1000);
			System.out.println("in select domain window");
			oASelFW.effecta("type","//label[text()='Name']/../../following-sibling::td//input", lang,"Location");
			oASelFW.effecta("clickAndWait","//a[@title='Search']","Search","Pass");
			Thread.sleep(2000);
			oASelFW.effecta("clickAndWait","//span[text()='"+lang.trim()+"']/../../descendant::td[2]//a//img", lang,"Pass");
			oASelFW.effecta("selectWindow",window3[0]);
			switch_to_default_content();

			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

			oASelFW.effecta("type","//span[text()='Min Count*']/../../following-sibling::td//input[2]", MinCount,"Min Count");
			oASelFW.effecta("type","//span[text()='Max Count*']/../../following-sibling::td//input[2]", MaxCount,"Max Count");
			oASelFW.effecta("type","//span[text()='Max In Wait List*']/../../following-sibling::td//input[2]", MaxWList,"Max In Wait List");

			switch_to_default_content();
		}else
		{
			oASelFW.effecta("clickAndWait","//span[text()='Language*']/../../following-sibling::td//a//img&&//span[text()='Language*']/../following-sibling::td//a//img","Look Up","Pass");
			String window3[] = oASelFW.effectaArray("getAllWindowNames");
			oASelFW.effecta("selectWindow",window3[1]);
			Thread.sleep(1000);
			System.out.println("in select domain window");
			oASelFW.effecta("type","//label[text()='Name']/../../following-sibling::td//input", lang,"Location");
			oASelFW.effecta("clickAndWait","//a[@title='Search']","Search","Pass");
			Thread.sleep(2000);
			oASelFW.effecta("clickAndWait","//span[text()='"+lang.trim()+"']/../../descendant::td[2]//a//img", lang,"Pass");
			oASelFW.effecta("selectWindow",window3[0]);
			switch_to_default_content();
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			oASelFW.effecta("type","//label[contains(text(),'Duration')]/../../following-sibling::td//input", sessionTime,"Session Time");

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

			oASelFW.effecta("clickAndWait","//a[text()='"+startDate+"']", ""+startDate+"/"+month+"/"+year+"","Pass");
			Thread.sleep(2000);
			oASelFW.effecta("selectWindow",window1[0]);

		}


	}


	public void CreateNewVLTClass(String classID, String domain, String lang, String Duration ) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);

		//Give Class Id
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		oASelFW.effecta("type","//span[text()='Class ID*']/../../following-sibling::td//input&&//span[text()='Class ID*']/../following-sibling::td//input",classID,"Class ID "+classID);

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


	}
	public void CreateNewWBT(String classID, String domain, String lang, String Duration) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

		//Give Class Id
		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='Class ID*']")))
		{
			oASelFW.effecta("type","//span[text()='Class ID*']/../../following-sibling::td//input&&//span[text()='Class ID*']/../following-sibling::td//input",classID,"Pass");
		}
		else
		{
			String ClassID = oASelFW.effecta("getText","//span[text()='Class ID']/../following-sibling::td//span","Class ID");
			Thread.sleep(3000);
			oASelFW.insertDataIntoAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData","update EndToEnd set Class_ID='"+ClassID.trim()+"' where ID=1");
			Thread.sleep(4000);
		}
		/*Thread.sleep(3000);
		String courseID = oASelFW.effecta("getText","//span[text()='Course ID']/../following-sibling::td//span","Course ID");
		Thread.sleep(2000);
		oASelFW.insertDataIntoAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData","update ManageClassesData set Course_Title='"+courseID+"' where ID="+sessionday+"");
		Thread.sleep(3000);*/

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


	}

	public void clickFinishButtonInNewClassPage(String value) throws InterruptedException, AWTException
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		Thread.sleep(3000);
		oASelFW.effecta("clickAndWait","//a//span[text()='"+value.trim()+"']",value,"Pass");
		Actions act=new Actions(oASelFW.driver);
		act.sendKeys(Keys.ENTER).sendKeys(Keys.ENTER).build().perform();
		Thread.sleep(2000);
		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='Error Encountered']")))
		{
			String mesg = oASelFW.effecta("getText","//span[@class='sbMainPageInstructions']","Error Encountered");
		}

		switch_to_default_content();
	}

}









