package classes;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.arsin.ArsinSeleniumAPI;

public class SabaPeopleAdminPage {


	ArsinSeleniumAPI oASelFW;

	public SabaPeopleAdminPage() {
	}

	public SabaPeopleAdminPage(ArsinSeleniumAPI oASelFW) {
		this.oASelFW=oASelFW;
	}


	/* @Author - Mangala 
	 * Description - This method will Clicks on pEOPLE LINKS[Manage PEOPLE,Manage SignupRules,...]
	 * 
	 */


	public void click_PeopleHome_Links(String PeopleHomeLinks)
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

		oASelFW.effecta("waitForElementPresent","//a[@title='"+PeopleHomeLinks+"']","60");
		oASelFW.effecta("click","//a[@title='"+PeopleHomeLinks+"']",PeopleHomeLinks);
		oASelFW.driver.switchTo().defaultContent();
	}



	/* @Author - Mangala 
	 * Description - This method will enter the person id
	 * 
	 */

	public void type_person_ID(String personID)
	{

		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		oASelFW.effecta("waitForElementPresent","//label[text()='Person ID']/following::input[@name='Person_query70086_var_person_no$kString$kLike']","60");
		oASelFW.effecta("type","//label[text()='Person ID']/following::input[@name='Person_query70086_var_person_no$kString$kLike']",personID,"personID:"+personID);
		oASelFW.driver.switchTo().defaultContent();

	}


	/* @Author - Mangala 
	 * Description - This method will click on search 
	 * 
	 */

	public void click_Search_Button()
	{

		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		oASelFW.effecta("waitForElementPresent","//a[@title='Search']/span[text()='Search']","60");
		oASelFW.effecta("click","//a[@title='Search']/span[text()='Search']","Search");
		oASelFW.driver.switchTo().defaultContent();

	}

	/* @Author - Mangala 
	 * Description - This method will click on EDIT PROFILE INFORMATION 
	 * 
	 */

	public void click_EditPRofileInformatiob_Link()
	{

		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		oASelFW.effecta("waitForElementPresent","//a[text()='Edit Profile Information']","60");
		oASelFW.effecta("click","//a[text()='Edit Profile Information']","Edit Profile Information");
		oASelFW.driver.switchTo().defaultContent();

	}



	/* @Author - Mangala 
	 * Description - Verify whether the text in UI is similar as per source file
	 * 
	 */

	public void compare_EditProfilePage_DB(String lastName,String firstName,String userName,String middleName,String Email,String personId,String organization,String location,String job,String approvalStatus)
	{

		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		oASelFW.effecta("waitForElementPresent","//span[text()='First Name*']","60");
		oASelFW.effecta("verifyElementPresent","//span[text()='Last Name*']/following::input[@value='"+lastName+"']","LastNAME: "+lastName+" is present in UI");
		oASelFW.effecta("verifyElementPresent","//span[text()='First Name*']/following::input[@value='"+firstName+"']","firstName: "+firstName+" is present in UI");
		oASelFW.effecta("waitForElementPresent","//span[text()='Username*']/following::input[@value='"+userName.trim()+"']","60");
		oASelFW.effecta("verifyElementPresent","//span[text()='Username*']/following::input[@value='"+userName.trim()+"']","userName: "+userName.trim()+" is present in UI");
		if(middleName.equalsIgnoreCase("None"))
		{
			middleName="";
			String value=oASelFW.effecta("getText","//span[text()='First Name*']/following::input[@value='"+middleName+"']","Getting value of Domain");

			if(value.equals(middleName))
			{
				oASelFW.effecta("sendReport","Verified middleName is having null value "+value+"","Succesfully verified state "+value+"","Pass");

			}
			else{
				oASelFW.effecta("sendReportWithOutExit","Verified middleName is not  having null value "+value+""," verified state "+value+"","Fail");

			}

		}
		else
		{
			oASelFW.effecta("verifyElementPresent","//span[text()='First Name*']/following::input[@value='"+middleName+"']","middleName: "+middleName+" is present in UI");

		}


		oASelFW.effecta("verifyElementPresent","//span[text()='First Name*']/following::input[@value='"+Email+"']","Email: "+Email+" is present in UI");
		oASelFW.effecta("verifyElementPresent","//span[text()='Organization*']/following::input[@value='"+organization+"']","organization: "+organization+" is present in UI");
		oASelFW.effecta("verifyElementPresent","//span[text()='Person No*']/following::input[@value='"+personId+"']","personId value"+personId+" present in UI");
		oASelFW.effecta("verifyElementPresent","//span[text()='First Name*']/following::input[@value='"+location+"']","location value"+location+" present in UI");

		oASelFW.effecta("verifyElementPresent","//span[text()='First Name*']/following::input[@value='"+job+"']","job value "+job+"");
		oASelFW.driver.switchTo().defaultContent();

	}

	public void searchInternalPeople_WildCard(String name) throws InterruptedException
	{

		try {
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));

			oASelFW.effecta("type","//label[text()='Username']/../../following-sibling::td//input",name,"First Name");
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		oASelFW.driver.switchTo().defaultContent();

	}

	public void searchResultsDisplay(String value)
	{
		try {
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			oASelFW.effecta("click","//span[text()='View']/../../following-sibling::tr//a[text()='"+value.trim()+"']",value.trim());
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		oASelFW.driver.switchTo().defaultContent();

	}
	
	public void profileQuickLinksInEditProfilePage(String value)
	{
		try {
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			oASelFW.effecta("click","//span[text()='Profile Quicklinks']/../../following-sibling::tr//a[text()='"+value.trim()+"']",value.trim());
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		oASelFW.driver.switchTo().defaultContent();
		
		
	}


























































	//----------------------------------------------------------------siddharth--------------------------------------------------------------------










































































}






