package classes;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.arsin.ArsinSeleniumAPI;


public class SabaLocationPage {


	ArsinSeleniumAPI oASelFW;

	public SabaLocationPage() {
	}

	public SabaLocationPage(ArsinSeleniumAPI oASelFW) {
		this.oASelFW=oASelFW;
	}

	/* @author - Mangala 
	 * Description - This method will clicks on Addowner Link
	 */

	public void click_AddOwner_Link() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(oASelFW.driver, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		System.out.println("switched to Frame");
		oASelFW.effecta("waitForElementPresent","//a[text()='Add Owner']","60");
		oASelFW.effecta("click","//a[text()='Add Owner']","Add Owner");
		Thread.sleep(3000);

		oASelFW.driver.switchTo().defaultContent();


	}

	/* @author - Mangala 
	 * Description - This method will clicks on Delete Link
	 */

	public void click_Delete_Link(String firstName,String lastName)
	{
		WebDriverWait wait = new WebDriverWait(oASelFW.driver, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		System.out.println("switched to Frame");
		oASelFW.effecta("waitForElementPresent","//a[text()='Delete']/preceding::span/a[@title='"+firstName+" "+lastName+"']","60");
		oASelFW.effecta("click","//a[text()='Delete']/preceding::span/a[@title='"+firstName+" "+lastName+"']/../../following-sibling::td//a","Delete");
		Alert alert = oASelFW.driver.switchTo().alert();
		alert.accept();

		oASelFW.driver.switchTo().defaultContent();
		

	}

	//HANDLING THE POP UP AFTER DELETE OPERATION


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

	//ENTER THE FIRST NAME TO SEARCH

	public void type_FirstName_AddOwnerWindow(String firstName)
	{
		oASelFW.effecta("waitForElementPresent","//label[text()='First Name']/following::input[@name='query_var_fname$kString$kLike']","60");
		oASelFW.effecta("type","//label[text()='First Name']/following::input[@name='query_var_fname$kString$kLike']",firstName,"firstname "+firstName+"");
	}


	//CLICK ON SEARCH BUTTON TO SELECT THE USER

	public void click_AddOwnerWindow_SearchButton()
	{
		oASelFW.effecta("waitForElementPresent","//a[@title='Search']","60");
		oASelFW.effecta("click","//a[@title='Search']","Close Button");
	}

	//SELECT THE USER TO ADD

	public void select_Owner_Checkbox(String firstName,String lastName)
	{
		oASelFW.effecta("waitForElementPresent","//span[text()='"+firstName+"']/preceding::input[contains(@value,'"+lastName+"')]","60");
		oASelFW.effecta("click","//span[text()='"+firstName+"']/preceding::input[contains(@value,'"+lastName+"')]","Select owner");
	}


	//CLICK ON SELECT BUTTON

	public void click_AddOwnerWindow_SelectButton()
	{
		oASelFW.effecta("waitForElementPresent","//a[@title='Select']","60");
		oASelFW.effecta("click","//a[@title='Select']","Select Button");
	}

	//CLICK ON CLOSE BUTTON


	public void click_AddOwnerWindow_CloseButton()
	{
		oASelFW.effecta("waitForElementPresent","//span[text()='Close']","60");
		oASelFW.effecta("click","//span[text()='Close']","Close Button");
	}


	//Method will check whether the selected owner is populated or not in location details page

	public void selectedUser_Available(String firstName,String lastName)
	{

		WebDriverWait wait = new WebDriverWait(oASelFW.driver, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		//oASelFW.effecta("waitForElementPresent","//span[text()='Owner']/following::th/span[text()='Name']/following::a[@title='"+firstName+lastName+"']","60");
		String selectedUser=oASelFW.effecta("getText","//span[text()='Owner']/following::th/span[text()='Name']/following::a[@title='"+firstName+" "+lastName+"']","SelectedUSer");
		System.out.println("selectedUser:"+selectedUser);
		System.out.println(firstName + lastName);
		if(selectedUser.equalsIgnoreCase(""+firstName+" "+lastName+""))
		{
			oASelFW.effecta("sendReport","Verified the selected user is populated in Location Details Page "+selectedUser+"","Succesfully verified selected user is present in Location Details Page "+selectedUser+"","Pass");

		}

		else
		{
			oASelFW.effecta("sendReport","Verified the selected user is not  populated in Location Details Page "+selectedUser+"","Succesfully verified selected user is not  present in Location Details Page "+selectedUser+"","Fail");
		}
		oASelFW.driver.switchTo().defaultContent();
	}

	//selecting multiple owners in addownerwindow

	public void select_MultipleUSers(String firstName,String lastName)
	{

		type_FirstName_AddOwnerWindow(firstName);
		click_AddOwnerWindow_SearchButton();
		select_Owner_Checkbox(firstName, lastName);
		click_AddOwnerWindow_SelectButton();
		String windows[]=oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",windows[0]);
		selectedUser_Available(firstName, lastName);

	}
	/*
	 * @author - arifuddin Mohd
	 * Description - this method will update the US Dollar's Rates In SabaLogin page
	 */
	public void addRatesInSabaAdminPage(String rate) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(oASelFW.driver, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		oASelFW.effecta("clickAndWait","//a[contains(text(),'US Dollars')]","US Dollar's Add Rates Link","Pass");

		String window[] = oASelFW.effectaArray("getAllWindowNames");
		oASelFW.effecta("selectWindow",window[1]);
		Thread.sleep(2000);
		System.out.println("in select domain window");
		oASelFW.effecta("type","//span[text()='Rate*']/../following-sibling::td//input[2]", rate,"US Dollar's");
		oASelFW.effecta("clickAndWait","//span[text()='Save']","Save Button","Pass");
		Thread.sleep(2000);

		oASelFW.effecta("selectWindow",window[0]);
		oASelFW.driver.switchTo().defaultContent();
	}
	public void cleanTheLocationInfomationInFacilityTabs()
	{
		WebDriverWait wait = new WebDriverWait(oASelFW.driver, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		oASelFW.driver.findElement(By.xpath("//label[text()='Location']/../../following-sibling::td//input")).clear();
		
		
		oASelFW.driver.switchTo().defaultContent();
	}
	

}




