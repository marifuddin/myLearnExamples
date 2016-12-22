package classes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.record.HideObjRecord;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
/*import org.python.antlr.PythonParser.break_stmt_return;
import org.python.antlr.PythonParser.list_for_return;
import org.python.antlr.PythonParser.return_stmt_return;*/


import com.arsin.ArsinSeleniumAPI;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
public class MyLearnMainContent extends myutils{
	Date date = new Date();
	String dd_lang     = "//select[@name='id_language']";
	String dd_locale   = "//select[@name='id_locale']";
	String dd_name     = "//select[@name='id_host']";
	String class_props = "//a[@title='View Class Properties.']";
	String date_picker = "//div[@id='date_startaddForm_cf_buttondiv']";
	String capacity    = "//input[@name='crs_capacity']";
	String save_btn    = "//input[@name='saveBtn']";
	String cal_nav     = "//a[@class='calnavright']";
	String register_link = "//td[text()='Register Link:']/following-sibling::td/a";
	String date_Start	  		= "//input[@name='date_start']";
	String date_end  	  		= "//input[@name='date_end']";
	String hour_dd   	  		= "//select[@name='hour']";
	String min_dd    	  		= "//select[@name='minute']";
	String amom_dd     			= "//select[@name='ampm']";
	String saveEvent   			= "//input[@value='Save']";
	//String save_btn             = "//td[@class='formbuttons']/input[2]";
	String ehour_dd    			= "//select[@name='e_hour']";
	String emin_dd     			= "//select[@name='e_minute']";
	String eampm_dd   			= "//select[@name='e_ampm']";
	String class_published 		= "//td[text()='Class Published!']";
	String host_dd              = "//select[@name='id_host']";
	String capacity_txt         = "//input[@name='crs_capacity']";
	String time_Zone_btn        = "//button[@title='Show All Items']";
	String cost_dd              = "//select[@name='rate']";
	String instructor_dd        = "//select[@name='instructors']";
	String curriculum_dd        = "//select[@name='id_curriculum_host_id']";
	String host_id              = "//input[@name='id_vendor']";
	String browse_btn           = "//button[@id='pluploadSelectFiles']";
	String upload_btn           = "//button[@id='pluploadStartUpload']";


	public MyLearnMainContent(ArsinSeleniumAPI oASelFW)
	{
		super(oASelFW);
	}

	/*public void setsearchInSurveysPage(String searchCriteria,String val)
	{
		String testid_input = "//input[@name='id_subject']";
		String search_btn   = "//input[@value='Search']";
		String search_res   = "//td[contains(text(),'1.')]/following-sibling::td[1]/a";
		switch (searchCriteria) {
		case "TestID":
			if(waitOnXpath(testid_input, timeOutSecs()))
			{
				setTextField(testid_input, val);

			}
			break;	
		}
		clickOnElement(search_btn);
		if(waitOnXpath(search_res, timeOutSecs()))
		{
			clickOnElement(search_res);
			sendReport("verify if user is able to perform search with criteria as "+searchCriteria, "Successfully performed the search criteria using "+searchCriteria, "Pass");	
		}
		else
		{
			sendReport("verify if user is able to perform search with criteria as "+searchCriteria, "No results are fetched with the criteria as "+searchCriteria, "Fail");
		}

	}*/

	public void getReportAs(String reportName)
	{
		String rep_lnk = "//a[text()='Revenue Report by Student [SP]']";
		if(waitOnXpath(rep_lnk, timeOutSecs()))
		{
			clickOnElement(rep_lnk);
			sendReport("Verify if user is able to select the report", "Successfully selected the report as "+reportName, "Pass");
		}
		else
		{
			sendReport("Verify if user is able to select the report", "Unable to select the report as "+reportName, "Fail");
		}
	}

	/*public void setSearchInReportsPage(String criteria, String val)
	{
		String college_select = "//select[@name='id_college']";
		String find_btn       = "//input[@value='Find']";
		switch (criteria) {
		case "college":
			if(waitOnXpath(college_select, timeOutSecs()))
			{
				selectByVisibleText(college_select, val);
			}
			break;
		}
		clickOnElement(find_btn);

	}*/

	/*public void setSearchInClassManagerPge(String searchCriteria, String val)
	{
		String classId_holder = "//input[@name='id_course']";
		String find_btn       = "//input[@value='Find']";
		String search_res     = "//td[text()='1.']/following-sibling::td[2]/a";
		switch (searchCriteria) {
		case "classid":
			if(waitOnXpath(classId_holder, timeOutSecs()))
			{
				setTextField(classId_holder, val);
			}
			break;

		}
		clickOnElement(find_btn);
		if(waitOnXpath(search_res, timeOutSecs()))
		{
			clickOnElement(search_res);
			sendReport("verify if user is able to perform search with criteria as "+searchCriteria, "Successfully performed the search criteria using "+searchCriteria, "Pass");	
		}
		else
		{
			sendReport("verify if user is able to perform search with criteria as "+searchCriteria, "No results are fetched with the criteria as "+searchCriteria, "Fail");
		}

	}*/

	/**
	 * This Method will verify that the void URL is present on Payment history page under the Roaster of any class
	 * @author siddharth_pangotra
	 * @param path
	 */

	public void getVoidLinkUnderPaymentHistory(String path)
	{
		String void_path = "//a[contains(text(),'Void')]";
		WebElement rpath = oASelFW.driver.findElement(By.xpath(path));
		List<WebElement> rows = rpath.findElements(By.tagName("tr"));
		System.out.println("row size"+rows.size());
		for(int i=1;i<=rows.size()-2;i++)
		{
			System.out.println("iter "+i);
			String payment_link = "//td[contains(text(),'"+i+"')]/following-sibling::td[2]/a[contains(text(),'Payment History')]";

			if(waitOnXpath(payment_link, 90))
			{
				clickOnElement(payment_link);
				sendReport("Click on payment History link to view Void URL", "Successfully Loaded Payment History page", "Pass");
				if(waitOnXpath(void_path, 15))
				{
					clickOnElement(void_path);
					sendReport("Verify that Void link is present on payment history page", "Void link found", "Pass");

					if(waitOnXpath("//td[contains(text(), 'Account ID:')]", 15))
					{
						System.out.println("Fields found after clicking void");

						sendReport("Verify Fields are found on clicking void", "Fields are found on clicking void", "Pass");
					}
					else
					{
						System.out.println("Fields found after clicking void");

						sendReport("Verify Fields are found on clicking void", "No fields are found on clicking void", "Fail");
					}

					System.out.println("passed");
					break;
				}

				else
				{
					clickOnElement("//td[contains(text(),'Class')]/following-sibling::td/a");
					sendReport("Verify that Void link is present on payment history page", "Void is not found, going to previous page", "Pass");

				}
			}
		}
	}	

	public void getTotalRegisteredUsers()
	{
		String mylrn_id_txt = "//td[contains(text(),'1.')]";
		String total_reg_link = "//td[contains(text(),'Total Registered')]/following-sibling::td/a";
		if(waitOnXpath(total_reg_link, timeOutSecs()))
		{
			clickOnElement(total_reg_link);
			if(waitOnXpath(mylrn_id_txt, timeOutSecs()))
			{
				sendReport("verify if user is navigated to Registrant Criteria Page", "User successfully navigated to registrant criteria page", "Pass");
			}
			else
			{
				sendReport("verify if user is navigated to Registrant Criteria Page", "User failed to navigate to Registrant criteria page after clicking total registrants", "Fail");
			}
		}
	}

	/**
	 * @author siddharth_pangotra
	 * @param links
	 * Description - This method will verify the links under Actions and View tabs in Total Registrants page
	 * @param - This method accepts a list of links as inputs
	 */
	public void verifylinksUnderActionAndView(List<String> links)
	{
		for(String link : links)
		{
			String link_text = "//a[contains(text(),'"+link+"')]";
			if(waitOnXpath(link_text, 30))
			{
				sendReport("Verify if user is able to see "+link+" under Actions and View tab", "Successfully verified the display of "+link+ " under Actions and View tab", "Pass");
			}
			else
			{
				sendReport("Verify if user is able to see "+link+" under Actions and View tab", link+" not displayed under Actions and View tab", "Fail");
			}
		}
	}



	/**
	 * @author siddharth_pangotra
	 * @param links
	 * Description - This method will verify the links present on the Properties page on the class
	 * @param - This method accepts a list of links as inputs
	 */
	public void verifylinkOnClassProperties(List<String> links)
	{
		for(String link : links)
		{
			String link_text = "//a[contains(text(),'"+link+"')]";
			if(waitOnXpath(link_text, 30))
			{
				sendReport("Verify if user is able to see "+link+" under Resources tab", "Successfully verified the display of "+link+ " under Resources tab", "Pass");
			}
			else
			{
				sendReport("Verify if user is able to see "+link+" under Resources tab", link+" not displayed under Resources tab", "Fail");
			}
		}
	}


	/**
	 * @author siddharth_pangotra
	 * @throws IOException 
	 * @ Description- This method reads the values from the 'Test Host' drop down from 'test, surveys and ploos' page and verifies that required option is present in it  
	 */
	/*public void verifyContentFromTestSurveyandPollsPage(String choice, String value) throws IOException
	{
		String drop_down_xpath= "//select[@name='host_id']";

		WebElement ele = oASelFW.driver.findElement(By.xpath(drop_down_xpath));
		List<WebElement> opt = ele.findElements(By.tagName("option"));
		if(waitOnXpath(drop_down_xpath, timeOutSecs()))
		{
			sendReport("Navigating to the Test Property Page ", "Successfully Navigated and Verifiyed the 'Test Host' Element ", "Pass");
			for(WebElement i : opt )
			{
				String name= i.getText();
				System.out.println(name + "is found in the drop down");
				sendReport("Retriving all the options from Test Host DropDown ", "Successfully Retriving the Values: "+name, "Pass");
			}
		}
		switch (choice) {
		case "Test ID":
			String id_Xpath = "//input[@name='id_subject']";
			setTextField(id_Xpath, value);
			sendReport("Setting the Test ID ", "Successfully Selected the Test ID as "+value, "Pass");
			clickOnElement("//input[@name='submit']");
			break;
		case "Test Name":
			String name_Xpath = "//input[@name='sub_name']";
			setTextField(name_Xpath, value);
			sendReport("Setting the Test Name ", "Successfully Selected the Test Name as "+value, "Pass");
			clickOnElement("//input[@name='submit']");
			break;
		case "Test Host":
			String host_Xpath = "//select[@name='host_id']";
			selectByVisibleText(host_Xpath, value);
			sendReport("Setting the Test Host ", "Successfully Selected the Test Host as "+value, "Pass");
			clickOnElement("//input[@name='submit']");
			break;
		default :{

		}
		}

	}*/
	public void clickDisplayedTestName(String number)
	{
		String dis_numb = "//span[text()='["+number+"]']";
		String dis_link = "//span[text()='["+number+"]']/../a";
		String ret_hostName = "//td[text()='Host:']/following-sibling::td";

		try {

			if(waitOnXpath(dis_numb, timeOutSecs()))
			{
				clickOnElement(dis_link);
				sendReport("Verifiying the Displayed Result ", "Successfully Clicked and Navigate to the Link Page ", "Pass");
			}else
			{
				System.out.println("Unable to click on the displayed link");
				sendReport("Verifiying the Displayed Result ", "Unable to View the Link ", "Fail");
			}
			if(waitOnXpath(ret_hostName, timeOutSecs()))
			{
				String retrive_host = getText(ret_hostName).trim();
				sendReport("Validating the Host Name ", "Successfully Validated the Host Name as: "+retrive_host, "Pass");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*if(waitOnXpath(drop_down_xpath, 20))
		{
			Select drpValue = new Select(oASelFW.driver.findElement(By.name("host_id")));
			if()

				drpValue.selectByVisibleText("Pearson VUE");
		}*/



	//	public void setRegistrantSearch(String criteria, String val)

	/*public void setRegistrantSearch(String criteria, String val)

	{
		String mylrn_txt  = "//input[@id='id_user']";
		String submit_btn = "//input[@name='submit']";
		String action_holder = "//td[contains(text(),' 1')]/../td[3]/a";

		switch (criteria) {
		case "mylearnid":
			if(waitOnXpath(mylrn_txt, timeOutSecs()))
			{
				setTextField(mylrn_txt, val);
			}
			break;
		}
		clickOnElement(submit_btn);
		if(waitOnXpath(action_holder, timeOutSecs()))
		{

			sendReport("Verify if user is able to perform registrant search", "Successfully performed registrant search using the criteria as "+criteria+" value as "+val , "Pass");
		}
		else
		{
			sendReport("Verify if user is able to perform registrant search", "Unable to perform the registrant search with criteria as "+criteria+" value as "+val , "Fail");
		}



	}*/

	public String getDisplayedAction()
	{
		String disp_action = null;
		String action_holder = "//td[contains(text(),' 1')]/../td[3]/a";
		if(waitOnXpath(action_holder, timeOutSecs()))
		{
			disp_action = getText(action_holder);

		}

		return disp_action;
	}

	public void verifyDisplayAction(String displayAction , String expectedAction)
	{
		if(displayAction.equalsIgnoreCase(expectedAction))
		{
			sendReport("Verify the display of action in the report", "Successfully verified the display of the action for "+expectedAction+" user as "+displayAction, "Pass");
		}
		else
		{
			sendReport("Verify the display of action in the report", "Expected action to be displayed in the report is- "+expectedAction+" but displayed as "+displayAction, "Fail");
		}
	}
	public void verifyMandateFieldsInSurveyForm()
	{
		String sub_name = "//input[@id='sub_name']";
		String fail_week = "//input[@id='fail_weeks']";
		String min_score = "//input[@id='min_score']";
		String cred_hours = "//input[@id='sub_hours']";
		String save_btn   = "//input[@value='Save']";
		clean_ExistingData(sub_name);
		clean_ExistingData(fail_week);
		clean_ExistingData(min_score);
		clean_ExistingData(cred_hours);
		clickOnElement(save_btn);
		if(waitOnAlert())
		{
			String text = oASelFW.driver.switchTo().alert().getText();
			sendReport("Verify the Validation message displayed on the Alert", "Successfully displayed the alert display as "+text, "Pass");
		}
		else
		{
			sendReport("Verify the Validation message displayed on the Alert", "No Alert is displayed even after the mandatory fields are not entered", "Fail");
		}


	}

	public String getcollege(String college,String option)
	{
		String college_link = "//td[contains(text(),'"+college+"')]/following-sibling::td//a[contains(text(),'"+option+"')]";
		WebDriverWait wait = new WebDriverWait(oASelFW.driver,timeOutSecs());
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(college_link)));
		oASelFW.driver.findElement(By.xpath(college_link)).click();
		oASelFW.effecta("sendReport","selecting college catalog","successfully selected the option "+option+" from the college "+college,"Pass");
		return college;
	}

	public void getTabFromCollegePropertiesAs(String tabName)
	{
		String tab_holder = "//td[@class='tabNavSlimOff']/a[contains(text(),'"+tabName+"')]";
		if(waitOnXpath(tab_holder, timeOutSecs()))
		{
			clickOnElement(tab_holder);
			sendReport("Verify if user is able to navigate to "+tabName+" from college properties", "Successfully selected the tab as "+tabName+" from college properties", "Pass");
		}
		else
		{
			sendReport("Verify if user is able to navigate to "+tabName+" from college properties", "Unable to select the tab as "+tabName+" from college properties", "Fail");
		}
	}

	public void getTabFromClassPropsAs(String tabName)
	{
		String tab_holder = "//td[@class='tabNavOff']//a[contains(text(),'"+tabName+"')]";
		if(waitOnXpath(tab_holder, timeOutSecs()))
		{
			clickOnElement(tab_holder);
			sendReport("Verify if user is able to navigate to "+tabName+" from college properties", "Successfully selected the tab as "+tabName+" from college properties", "Pass");
		}
		else
		{
			sendReport("Verify if user is able to navigate to "+tabName+" from college properties", "Unable to select the tab as "+tabName+" from college properties", "Fail");
		}
	}

	public void createTokens()
	{
		String createTKN   = "//a[text()='Create Tokens']";
		String token_input = "//input[@id='totalTokens']";
		String create_btn  = "//input[@value='Create']";
		String text_display = "//td[text()='Done.']";
		if(waitOnXpath(createTKN, timeOutSecs()))
		{
			clickOnElement(createTKN);
			if(waitOnXpath(token_input, timeOutSecs()))
			{
				setTextField(token_input, "3");
				clickOnElement(create_btn);
				if(waitOnText(text_display))
				{
					sendReport("Verify if user is able to create Tokens", "Tokens successfully created", "Pass");
				}
				else
				{
					sendReport("Verify if user is able to create Tokens", "Unable to create Tokens", "Fail");
				}
			}
		}
	}
	public void verifyPropertiesForGroup(String groupName)
	{
		String group_select   = "//b[text()='"+groupName+"']/../../../table//select[@name='id_groupOnsite']";
		String group_showMems = "//b[text()='"+groupName+"']/../../../table//div[@id='OnsiteTrainingGroupMembersShow']/a/img"; 
		String group_hideMems = "//b[text()='"+groupName+"']/../../../table//div[@id='OnsiteTrainingMembersHide']/a/img";
		if(waitOnXpath(group_select, timeOutSecs()))
		{
			sendReport("Verify the display of Admin Group dropdown display", "Successfully verified display of the Admin Group drop down in the group "+groupName, "Pass");
			if(waitOnXpath(group_showMems, shortWait()))
			{
				clickOnElement(group_showMems);
				sendReport("Verify the display of (+) symbol in the group "+groupName, "Successfully verified display of (+) symbol in the group "+groupName, "Pass");
				if(waitOnXpath(group_hideMems, shortWait()))
				{
					sendReport("Verify the display of (-) symbol in the group "+groupName, "Successfully verified display of (-) symbol in the group "+groupName, "Pass");
				}
				else
				{
					sendReport("Verify the display of (-) symbol in the group "+groupName, "(-) symbol is not displayed for the group "+groupName, "Fail");
				}
			}
		}
		else
		{
			sendReport("Verify the display of Admin Group dropdown display", "Admin Group drop down is not displayed in the group "+groupName, "Fail");
		}
	}
	public void clickingTrainingPlansOptions(String options)
	{
		String option_LinkXpath = "//td[@class='tabNavSlimOff']/a[contains(text(),'"+options+"')]";
		WebDriverWait wait = new WebDriverWait(oASelFW.driver,timeOutSecs());
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(option_LinkXpath)));
		oASelFW.driver.findElement(By.xpath(option_LinkXpath)).click();
		oASelFW.effecta("sendReport","clicking on Link "+options,"Successfully clicked on "+options+" Link","Pass");
	}

	public void FindDetails()
	{
		String find_btn = "//input[@value='Find']";
		if(waitOnXpath(find_btn, timeOutSecs()))
		{
			clickOnElement(find_btn);
		}
	}
	public void certificateTrainingPlanName()
	{
		String planName_Link = "//a[@href='/mgrCourse/ctEdit.cfm?cct_id=37061&ui=myLearn']";
		WebDriverWait wait = new WebDriverWait(oASelFW.driver,timeOutSecs());
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(planName_Link)));
		oASelFW.driver.findElement(By.xpath(planName_Link)).click();
		oASelFW.effecta("sendReport","clicking on plan Link","Successfully clicked on plan Link","Pass");
	}


	public String getClassDetails(String classID)
	{
		String class_txt = "//input[@name='id_course']";
		String classname = null;
		if(waitOnXpath(class_txt, timeOutSecs()))
		{
			setTextField(class_txt, classID);
			FindDetails();
			if(waitOnXpath(class_props, timeOutSecs()))
			{
				classname = getText(class_props);
				sendReport("get details for class ID: "+classID, "Successfully retireved details for class Id as "+getText(class_props), "Pass");
			}
		}
		else
		{
			sendReport("get details for class ID: "+classID, "Unable to set class details in the class ID text box", "Fail");	
		}
		return classname;
	}

	public void viewClassProperties()
	{
		if(waitOnXpath(class_props, timeOutSecs()))
		{
			clickOnElement(class_props);
		}
	}
	public void click_EditTrainingPlan(String editLink)
	{
		String edittrain_Link = "//a[contains(text(),'"+editLink+"')]";
		WebDriverWait wait = new WebDriverWait(oASelFW.driver,timeOutSecs());
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(edittrain_Link)));
		oASelFW.driver.findElement(By.xpath(edittrain_Link)).click();
		oASelFW.effecta("sendReport","clicking on plan Link","Successfully clicked on plan Link","Pass");
	}
	public String getCourseCategory(String category,String option)
	{
		try {
			WebDriverWait wait    = new WebDriverWait(oASelFW.driver,timeOutSecs());
			String opt_properties = "//td[contains(text(),'"+category+"')]/following-sibling::td[1]/a";
			String opt_courses    = "//td[contains(text(),'"+category+"')]/following-sibling::td[2]/a";
			String opt_Reports    = "//td[contains(text(),'"+category+"')]/following-sibling::td[3]/a";
			if(option.equalsIgnoreCase("properties"))
			{
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(opt_properties)));
				oASelFW.driver.findElement(By.xpath(opt_properties)).click();
				oASelFW.effecta("sendReport","get college category","successfully selected the option-"+option+" from the category-"+category,"Pass");
			}
			if(option.equalsIgnoreCase("courses"))
			{
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(opt_courses)));
				oASelFW.driver.findElement(By.xpath(opt_courses)).click();
				oASelFW.effecta("sendReport","get college category","successfully selected the option-"+option+" from the category-"+category,"Pass");
			}
			if(option.equalsIgnoreCase("Reports"))
			{
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(opt_Reports)));
				oASelFW.driver.findElement(By.xpath(opt_Reports)).click();
				oASelFW.effecta("sendReport","get college category","successfully selected the option-"+option+" from the category-"+category,"Pass");
			}

		} 
		catch(ElementNotFoundException e){
			oASelFW.effecta("sendReport","Get course category","Unable to select course category as the expected element"+category+"was not found","Fail");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return category;
	}
	public void isElementPresent_Expired()
	{
		try {
			WebDriverWait wait    = new WebDriverWait(oASelFW.driver,timeOutSecs());
			String expiredXpath = "//a[contains(text(),'Expired')]";

			if(expiredXpath.equalsIgnoreCase("Expired"))
			{
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(expiredXpath)));
				oASelFW.driver.findElement(By.xpath(expiredXpath));
				oASelFW.effecta("sendReport","verifiying Element"+expiredXpath,"successfully verifiyed Element"+expiredXpath,"Pass");
			}else
			{
				oASelFW.effecta("sendReport","verified Element"+expiredXpath,"Unable to verifiyed Element"+expiredXpath,"Fail");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void myProfile_Selection()
	{
		ProfilesIni profile = new ProfilesIni();

		FirefoxProfile myprofile = profile.getProfile("QA_Testing");

		WebDriver driver = new FirefoxDriver(myprofile);

	}
	public void click_NewClass_Link()
	{
		String newClassxPath = "//a[contains(text(),'New Class')]";
		waitOnXpath(newClassxPath, timeOutSecs());
		clickOnElement(newClassxPath);
	}
	public void clicking_SaveDataBase()
	{
		String saveButton = "//input[@value='Save']";
		waitOnXpath(saveButton, timeOutSecs());
		clickOnElement(saveButton);
	}

	public void end_Date()
	{
		String endDateXpath = "//input[@name='date_endStart']";
		waitOnXpath(endDateXpath, timeOutSecs());
		setTextField(endDateXpath, "01/20/2015");

	}
	public void trainingPlansId()
	{
		String trainingIdXpath = "//input[@name='cct_id']";
		waitOnXpath(trainingIdXpath, timeOutSecs());
		setTextField(trainingIdXpath, "52133");
	}
	public void clicking_ReportIcon()
	{
		String reprotIconXpath = "//a[5]/img";
		waitOnXpath(reprotIconXpath, timeOutSecs());
		clickOnElement(reprotIconXpath);
	}
	public void click_ParticipationReport()
	{
		String participationXpath= "//a[contains(text(),'Participation Report')]";
		waitOnXpath(participationXpath, timeOutSecs());
		clickOnElement(participationXpath);
	}
	public void enterAssignedDates()
	{
		String StartDt = "//input[@id='fixedText1']";
		String EndDt = "//input[@id='fixedText2']";
		setTextField(StartDt, "10/16/2014");
		setTextField(EndDt, "01/20/2015");
	}
	public void printToMSexcel()
	{
		String msExcelXpath= "//select[@name='reportFormat']";
		waitOnXpath(msExcelXpath, timeOutSecs());
		selectByVisibleText(msExcelXpath, "MS Excel");
	}

	public void click_myTranscript_Link()
	{
		String myTransXPath = "//a[contains(text(),'myTranscript')]";
		waitOnXpath(myTransXPath, timeOutSecs());
		clickOnElement(myTransXPath);

	}
	public void click_AddNew()
	{
		String AddNewxPath = "//a[contains(text(),'Add New')]";
		waitOnXpath(AddNewxPath, timeOutSecs());
		clickOnElement(AddNewxPath);
	}
	public void selecting_DataBase()
	{
		String radioButton = "//form[@name='messageList']/descendant::tr[2]/td[2]/../descendant::input[@name='GetThis']";
		WebDriverWait wait = new WebDriverWait(oASelFW.driver,timeOutSecs());
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(radioButton)));
		oASelFW.driver.findElement(By.xpath(radioButton)).click();
		oASelFW.effecta("sendReport","Selecting the DataBase Sheet","Successfully selected the DataBase Sheet","Pass");
	}
	public void type_SearchCourseCatalog_Field()
	{
		String serchCourCatlxPath = "//input[@name='keyword']";
		String searchXpath 		  = "//input[@name='Search']";	
		waitOnXpath(serchCourCatlxPath, timeOutSecs());
		setTextField(serchCourCatlxPath, "QA Testing");
		clickOnElement(searchXpath);
	}
	public void hosts_Names()
	{
		String hosts_Name = "//input[@name='host_name']";
		waitOnXpath(hosts_Name, timeOutSecs());
		setTextField(hosts_Name, "Content Raven");
	}
	public String editCourse(String course)
	{
		String course_text = "//span[contains(text(),'"+course+"')]/../following-sibling::td[2]/a";
		WebDriverWait wait = new WebDriverWait(oASelFW.driver,timeOutSecs());
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(course_text)));
		oASelFW.driver.findElement(By.xpath(course_text)).click();
		oASelFW.effecta("sendReport","Edit course verification","Edited the course-"+course+" from courses catalog","Pass");
		return course;
	}
	
	/*	public String editCourse(String course, String deliveryType)
	{
		String course_text = "//span[contains(text(),'"+course+"')]/../following-sibling::td[2]/a";
		String delType = "//td[contains(text(),'"+deliveryType+"')]/../following::tr[2]/td[5]/a";
		try{
			WebDriverWait wait = new WebDriverWait(oASelFW.driver,timeOutSecs());
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(course_text)));
			if(oASelFW.driver.findElement(By.xpath(delType)).isDisplayed())
			{
				oASelFW.driver.findElement(By.xpath(course_text)).click();
				oASelFW.effecta("sendReport","Edit course verification","Edited the course-"+course+" from courses catalog","Pass");
			}
			}
		catch (NoSuchElementException e) {

			System.out.println("Already Delivery type has added");
		}
		return course;
	}*/

	public void AddUserToClass()
	{
		String add_lnk = "//a[contains(text(),'Add')]";
		if(waitOnXpath(add_lnk, timeOutSecs()))
		{
			clickOnElement(add_lnk);
		}
	}
	public void VerifyDeliveryTypeAdded(String devType)
	{
		if(waitOnXpath("//td[contains(text(),'"+devType+"')]", 05))
		{
			sendReport("Verify Delivery type added or not", "Delivery type already added", "Pass");
		}	
			
		else
		{
			AddDeliveryType(devType);
		}
			
	}
	/**
	 * This method will add the delivery type from the Edit Course page,
	 * This method accepts delivery type as parameter , if  the delivery type is already added
	 * this will skip the method.
	 * @param deliverytype
	 * @return
	 */
	public String AddDeliveryType(String deliverytype)
	{
		String delivery_select = "//select[@name='st_id']";
		WebElement deltype     = oASelFW.driver.findElement(By.xpath(delivery_select));
		waitOnXpath(delivery_select, timeOutSecs());
		List<WebElement> ddelements = deltype.findElements(By.tagName("option"));
		System.out.println(ddelements.size());
		for(WebElement element : ddelements)
		{
			String option = element.getText();
			if(option.contains(deliverytype))
			{
				String add_btn = "//input[@value='Add']";
				new Select(deltype).selectByVisibleText(option);
				System.out.println("added del type"+option);
				clickOnElement(add_btn);	
				break;
			}
			else
			{
				System.out.println("option already selected");
			}

		}
		sendReport("Verify the delivery type selection in user properties", "Delivery type successfully selected as--"+deliverytype, "Pass");
		return deliverytype;
	}


	public String verifyDeliveryType(String deliverytype)
	{
		System.out.println("in delivery types");
		String delivery_select = "//select[@name='st_id']";
		waitOnXpath(delivery_select, timeOutSecs());
		WebElement deltype     = oASelFW.driver.findElement(By.xpath(delivery_select));
		List<WebElement> ddelements = deltype.findElements(By.tagName("option"));
		System.out.println(ddelements.size());

		for(WebElement element : ddelements)
		{
			String option = element.getText();
			if(option.contains(deliverytype))
			{
				sendReport("verify the "+deliverytype+ "display in the delivery type drop down", "Successfully verified the delivery type "+deliverytype+ " display in the drop down", "Pass");			
			}
			else 
			{
				String added_type = "//td[contains(text(),'"+deliverytype+"')]/a";
				System.out.println("added type is"+added_type);
				System.out.println("Added type status is "+waitOnXpath(added_type, timeOutSecs()));
				if(waitOnXpath(added_type, timeOutSecs()))
				{
					sendReport("verify the "+deliverytype+ "display in the delivery type drop down", "Successfully verified---- the delivery type "+deliverytype+ " display in the drop down", "Pass");
				}
				else
				{
					sendReport("verify the "+deliverytype+ "display in the delivery type drop down", "Delivery type "+deliverytype+ " is not displayed in the drop down", "Fail");
				}
			}
		}
		return deliverytype;
	}

	public void navigateTotab(String tabname)
	{
		String tab_select = "//a[text()='"+tabname+"']";
		waitOnXpath(tab_select, timeOutSecs());
		oASelFW.driver.findElement(By.xpath(tab_select)).click();
	}

	public void publishSelfPacedOffering()
	{
		String selfpaced_link = "//a[text()='Publish New Self-Paced Offering']";
		waitOnXpath(selfpaced_link, timeOutSecs());
		clickOnElement(selfpaced_link);
	}

	public void scheduleNewAcademyClass()
	{
		String Academyclass_link = "//a[text()=' Schedule New Academy Class']";
		waitOnXpath(Academyclass_link, timeOutSecs());
		clickOnElement(Academyclass_link);
	}

	/**
	 * This method will schedule a specific delivery type by clicking in the schedule option
	 * @param deliverytype
	 */
	public void scheduleNewclassFor(String deliverytype)
	{
		String class_link = "//td[contains(text(),'"+deliverytype+"')]/a";
		boolean status = waitOnXpath(class_link, timeOutSecs());
		if(status==true)
		{
			clickOnElement(class_link);
			sendReport("Schedule new "+deliverytype+" classroom from user propeties", "Successfully selected schedule new "+class_link+" room link from the properties page", "Pass");
		}
		else
		{
			sendReport("Schedule new "+deliverytype+" classroom from user propeties", "link for schedule new "+class_link+" room is not displayed in the properties page", "Fail");
		}
	}

	/**
	 * This method will schedule a specific delivery type by clicking in the schedule option
	 * @param offering
	 */
	public void publishNewOffering(String offering)
	{
		String offering_link = "//td[contains(text(),'"+offering+"')]/a";
		boolean status = waitOnXpath(offering_link, timeOutSecs());
		if(status)
		{
			clickOnElement(offering_link);
			sendReport("Schedule new "+offering+" classroom from delivery types", "Successfully published the offering "+offering_link, "Pass");
		}
		else
		{
			sendReport("Schedule new "+offering+" classroom from delivery types", "Unable to publish the offering "+offering_link, "Fail");
		}
	}
	/**
	 * This  method will select a new room details from the select room page
	 * Reads the city name from the proeprties file 
	 * @throws IOException
	 */
	public void selectNewRoom() throws IOException
	{
		String city_name       = "//td[text()='City:']/../td/select";
		String training_center = "//td[text()='Training Center:']/../td/select";
		String room_dd         = "//td[text()='Room:']/../td/select";
		String save_room       = "//input[@value='Continue']";

		String city = loadprops("city");
		String city_space = "  "+city;
		boolean status = waitOnXpath(city_name, timeOutSecs());
		if(status==true)
		{
			selectByVisibleText(city_name, city_space);
			waitOnXpath(training_center, timeOutSecs());
			selectByIndex(training_center, 1);
			waitOnXpath(room_dd, timeOutSecs());
			selectByIndex(room_dd, 1);
			clickOnElement(save_room);
			sendReport("select room details from properties page","Successfully selected the room details", "Pass");	
		}
		else
		{
			sendReport("select room details from properties page", "Unable to select room details from properites page", "Fail");
		}

	}

	/*public void saveNewClassRoomEvent(String language,String instance) throws InterruptedException
	{
		waitOnXpath(dd_lang, timeOutSecs());
		selectByVisibleText(dd_lang, language);
		
		waitOnXpath(date_Start, timeOutSecs());
		waitOnXpath(date_Start, timeOutSecs());

		
		switch (instance) {
		case "Academy":
			setTextField(date_Start, "01/01/2017");
			setTextField(date_end, "01/03/2017");
			break;
		case "ClassRoom":
			setTextField(date_Start, "01/04/2017");
			setTextField(date_end, "01/06/2017");	
			break;
		case "OnDemand":
			setTextField(date_Start, "01/07/2017");
			setTextField(date_end, "01/09/2017");	
			break;
		case "FlexILT":
			setTextField(date_Start, "01/10/2017");
			setTextField(date_end, "01/12/2017");	
			break;
		case "LabConnect":
			setTextField(date_Start, "01/13/2017");
			
			break;
		case "WalkIn":
			setTextField(date_Start, "01/16/2017");
			setTextField(date_end, "01/18/2017");
			break;
		case "Podcast":
			setTextField(date_Start, "01/19/2017");
			setTextField(date_end, "01/21/2017");
			break;

		}
		selectByIndex(hour_dd,5);
		selectByIndex(min_dd, 2);
		selectByIndex(amom_dd, 0);

		selectByIndex(ehour_dd, 6);
		selectByIndex(emin_dd, 2);
		selectByIndex(eampm_dd, 0);

		Thread.sleep(4000);
		waitOnXpath(saveEvent, timeOutSecs());
		clickOnElement(saveEvent);
		Thread.sleep(5000);
		boolean status = waitOnXpath(class_published, timeOutSecs());
		if(status==true)
		{
			sendReport("Verify if new classroom details are published", "New Academy class details are successfully published", "Pass");
		}
		else
		{
			sendReport("Verify if new classroom details are published", "New Academy class details are not published", "Fail");
		}
	}*/

	public void select_host()
	{
		waitOnXpath(host_dd,timeOutSecs());
		selectByIndex(host_dd, 1);
	}


	public void setCapacity()
	{
		waitOnXpath(capacity_txt, timeOutSecs());
		setTextField(capacity_txt, "100");
	}

	public void setTimeZone()
	{
		String timezone_link = "//a[text()='America/Los_Angeles']";
		waitOnXpath(time_Zone_btn, timeOutSecs());
		clickOnElement(time_Zone_btn);
		waitOnXpath(timezone_link, timeOutSecs());
		clickOnElement(timezone_link);
	}

	public void setCost()
	{
		waitOnXpath(cost_dd, timeOutSecs());
		selectByIndex(cost_dd, 1);
	}

	public void setInstructor()
	{
		waitOnXpath(instructor_dd, timeOutSecs());
		selectByIndex(instructor_dd, 2);
	}

	public void setCurriculum()
	{
		waitOnXpath(curriculum_dd, timeOutSecs());
		selectByIndex(curriculum_dd, 2);
	}

	public void selectStartHour()
	{
		selectByIndex(hour_dd,5);
		selectByIndex(min_dd, 2);
		selectByIndex(amom_dd, 0);

	}

	public void selectEndHour()
	{
		selectByIndex(ehour_dd, 6);
		selectByIndex(emin_dd, 2);
		selectByIndex(eampm_dd, 0);
	}

	public void saveRoomDetails()
	{
		if(waitOnXpath(save_btn, timeOutSecs()))
		{
			clickOnElement(save_btn);
		}
	}

	public void saveRoomLiveOnlineClass(String language)
	{
		waitOnXpath(dd_lang, timeOutSecs());
		selectByVisibleText(dd_lang, language);
		select_host();
		setCapacity();
		setTextField(date_Start, "01/02/2017");
		selectStartHour();
		setTextField(date_end, "03/02/2017");	
		selectEndHour();
		setTimeZone();
		setCost();
		setInstructor();
		setCurriculum();
		saveRoomDetails();
	}
	public void preRequireSelection(String course, String host, String instructor, String country)
	{
		String courseXpath = "//select[@id='courseID']";
		String hostXpath = "//select[@name='hostID']";
		String instructorXpath = "//select[@name='instructorID']";
		String countryXpath = "//select[@name='loc_country']";

		waitOnXpath(courseXpath, timeOutSecs());
		selectByVisibleText(courseXpath, course);
		sendReport("Selecting the course from Drop Down", "Successfully selected the "+course+" from Drop Down", "Pass");

		waitOnXpath(hostXpath, timeOutSecs());
		selectByVisibleText(hostXpath, host);
		sendReport("Selecting the host from Drop Down", "Successfully selected the "+host+" from Drop Down", "Pass");

		waitOnXpath(instructorXpath, timeOutSecs());
		selectByVisibleText(instructorXpath, instructor);
		sendReport("Selecting the instructor from Drop Down", "Successfully selected the "+instructor+" from Drop Down", "Pass");

		waitOnXpath(countryXpath, timeOutSecs());
		selectByVisibleText(countryXpath, country);
		sendReport("Selecting the country from Drop Down", "Successfully selected the "+country+" from Drop Down", "Pass");

	}

	public void navigateToClassPropertiesPage()
	{
		String class_properties_pg_link = "//a[text()='View Class Properties']";
		boolean status = waitOnXpath(class_properties_pg_link, timeOutSecs());
		if(status==true)
		{
			clickOnElement(class_properties_pg_link);
			sendReport("Navigate to class properties page", "Successfully navigated to class properties page", "Pass");
		}
		else
		{
			sendReport("Navigate to class properties page", "class properties page link is not displayed", "Fail");
		}

	}

	public String getRegisterLink()
	{
		String register_link = "//a[contains(@href,'/register.cfm?')]";
		String returnlink = null;
		boolean status = waitOnXpath(register_link, timeOutSecs());
		if(status==true)
		{
			returnlink = getText(register_link);
			System.out.println(returnlink);
			sendReport("Register link display in class properties page", "Register link "+returnlink+" sucessfully fetched from class properties page", "Pass");
		}
		else
		{
			sendReport("Register link display from properties page", "Register link is not displayed in class properties page", "Fail");
		}
		return returnlink;
	}

	public void getRegistrationStatus()
	{
		String register_Close = "//td[contains(text(),'Registration Close')]/following-sibling::td//input";
		boolean status = waitOnXpath(register_Close, timeOutSecs());
		if(status==true)
		{
			sendReport("Registration Close is display in class properties page", " Successfully Displayed Registration Close Field in class properties page", "Pass");
		}
		else
		{
			sendReport("Registration Close is display in class properties page", "Registration Close is not displayed in class properties page", "Fail");
		}
	}
	

	public String saveSelfPacedOfferings(String lang, String locale, String host, String capacity_val )
	{

		int curr_date      = date.getDate();
		int date_sel       = curr_date+2;

		waitOnXpath(dd_lang, timeOutSecs());
		selectByVisibleText(dd_lang, lang);

		waitOnXpath(dd_locale, timeOutSecs());
		selectByVisibleText(dd_locale, locale);

		waitOnXpath(dd_name, timeOutSecs());
		selectByVisibleText(dd_name, host);

		clickOnElement(date_picker);
		waitOnXpath(cal_nav, timeOutSecs());
		System.out.println("Calculating the date");

		if(date_sel<curr_date)
		{
			clickOnElement(cal_nav);
			clickOnElement("//a[text()="+curr_date+"]");
		}
		else
		{
			clickOnElement("//a[text()='"+date_sel+"']");
		}
		System.out.println("after calculation");

		waitOnXpath(capacity, timeOutSecs());
		setTextField(capacity, capacity_val);

		waitOnXpath(save_btn, timeOutSecs());
		clickOnElement(save_btn);
		waitOnXpath(register_link, timeOutSecs());

		String captured_link = getText(register_link);

		System.out.println(captured_link);
		return captured_link;
	}

	public void selectLanguage()
	{
		if(waitOnXpath(dd_lang, timeOutSecs()))
		{
			selectByVisibleText(dd_lang, "English");	
		}
	}

	public void selecthost()
	{
		try {
			if(waitOnXpath(host_dd, timeOutSecs()))
			{
				selectByIndex(host_dd, 1);
			}
		} catch (Exception e) {
			System.out.println("Index not displayed");
		}
	}

	public void setDate()
	{
		@SuppressWarnings("deprecation")
		int curr_date      = date.getDate();
		int date_sel       = curr_date+2;
		if(waitOnXpath(date_picker, timeOutSecs()))
		{
			clickOnElement(date_picker);
			waitOnXpath(cal_nav, timeOutSecs());
			if(date_sel<curr_date)
			{
				clickOnElement(cal_nav);
				clickOnElement("//a[text()="+curr_date+"]");
			}
			else
			{
				clickOnElement("//a[text()='"+date_sel+"']");
			}
		}
	}

	public void setHostID()
	{
		if(waitOnXpath(host_id, timeOutSecs()))
		{
			setTextField(host_id, "889");
		}
	}

	public void UploadWRF(String wrfFile) throws InterruptedException, IOException
	{
		if(waitOnXpath(browse_btn, timeOutSecs()))
		{
			clickOnElement(browse_btn);
			System.out.println("clicked on browse");
			uploadAttachment(wrfFile);
			System.out.println("after browse");
			if(waitOnXpath(upload_btn, timeOutSecs()))
			{
				clickOnElement(upload_btn);
			}
		}
	}

	public void setDateWebCalendar()
	{
		if(waitOnXpath(date_Start, timeOutSecs()))
		{
			setTextField(date_Start, "09/21/2017");
		}
	}
	/**
	 * This method will set the required fields to save video tutorial
	 * @return
	 */
	public String saveNewVideoTutorial()
	{
		selectLanguage();
		select_host();
		setDate();
		setCapacity();
		saveRoomDetails();
		return getRegisterLink();
	}

	/**
	 * This method will set the required fields to save a Lab Offering
	 * @return
	 */
	public String saveNewLabOffering()
	{
		selectLanguage();
		selectByVisibleText("//select[@name='id_locale']", "Tier 1 AUD");
		select_host();
		setHostID();
		setDate();
		setCapacity();
		selectByVisibleText("//select[@name='rate']", "$ 5140 (AUD)");
		saveRoomDetails();
		return getRegisterLink();
	}

	public String saveaNewOnDemand()
	{
		selectLanguage();
		selectByIndex(host_dd, 1);
		setDate();
		setCapacity();
		saveRoomDetails();
		return getRegisterLink();
	}
	/**
	 * This method will set the required fileds to save a Webex Recording offering 
	 * @param wrfFile
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public String saveNewWebexOffering(String wrfFile) throws InterruptedException, IOException
	{
		selectLanguage();
		select_host();
		UploadWRF(wrfFile);
		setDateWebCalendar();
		setCost();
		saveRoomDetails();
		return getRegisterLink();

	}
	public void getTypesfromOfferings()
	{
		System.out.println("in offerings");
		String types_link = "//span[contains(text(),'Offerings/')]/../descendant::a[contains(text(),'Types')]";
		if(waitOnXpath(types_link, timeOutSecs()))
		{
			System.out.println("inside - pass");
			clickOnElement(types_link);
			waitOnXpath("//a[contains(@href,'typeEdit')]", timeOutSecs());
			sendReport("Verify is the user is navigated to configure->Type manager page", "Successfully navigated to type manager page", "Pass");
		}
		else
		{
			sendReport("Verify is the user is navigated to configure->Type manager page", "Unable to navigate to type manager page", "Fail");
		}
	}

	/**
	 * This method will modify the offering types to either delete or edit
	 * @param typename
	 * @param operation
	 */
	public void modifyType(String typename,String operation)
	{
		String edit_link = "//td[contains(text(),'"+typename+"')]/../descendant::a[contains(@href,'Edit')]";
		String del_link  = "//td[contains(text(),'"+typename+"')]/../descendant::a[contains(@href,'Del')]";
		if(operation.equalsIgnoreCase("edit"));
		{
			if(waitOnXpath(edit_link, timeOutSecs()))
			{
				clickOnElement(edit_link);
			}
		}
		if(operation.equalsIgnoreCase("delete"))
		{
			if(waitOnXpath(del_link, timeOutSecs()))
			{
				clickOnElement(del_link);
			}
		}

	}

	public void cancelType()
	{
		String cancel_btn = "//input[@value='Cancel']";
		if(waitOnXpath(cancel_btn, timeOutSecs()))
		{
			clickOnElement(cancel_btn);
		}
	}

	public void verifyILTDisplay(String type)
	{
		String update_btn = "//td[contains(text(),'ILT Class:')]/following-sibling::td/input";
		if(waitOnXpath(update_btn, timeOutSecs()))
		{
			sendReport("Verify (ILT)Instructer Lab Training display in Edit "+type+" delivery type" , "Successfully verified the display of ILT with Yes and No options for "+type+" delivery type", "Pass");
		}
		else
		{
			sendReport("Verify (ILT)Instructer Lab Training display in Edit "+type+" delivery type", "ILT options are not displayed in the edit page of "+type+ "delivery type", "Fail");
		}
	}

	/**
	 * This method search user based on the criteria, currently accepts 
	 * @param searchcriteria
	 * @param searchval
	 */
	/*public void userSearchBy(String searchcriteria, String searchval)
	{
		String search_user_btn = "//input[@name='test']";
		//String props_link      = "//td[text()='1.']/following-sibling::td[4]/a";
		String email_txt       = "//input[@name='email']"; 
		String fName_Xpath 	   = "//input[@name='fname']";	 

		switch (searchcriteria) {
		case "mylearnid":
			String learnid_txt = "//td[contains(text(),'myLearn')]/following-sibling::td/input";
			if(waitOnXpath(learnid_txt, timeOutSecs()))
			{
				setTextField(learnid_txt, searchval);
				sendReport("Search users using a criteria", "Searching user with mylearn id as "+searchval, "Pass");
			}
			break;
		case "email":
			if(waitOnXpath(email_txt, timeOutSecs()))
			{
				setTextField(email_txt, searchval);
				sendReport("Search users using a criteria", "Searching user with Email address as "+searchval, "Pass");
			}
			break;
		case "fName":
			if(waitOnXpath(fName_Xpath, timeOutSecs()))
			{
				setTextField(fName_Xpath, searchval);
				sendReport("Search users using a criteria", "Searching user with First Name "+searchval, "Pass");
			}

		}
		clickOnElement(search_user_btn);
	}*/


	public void userSearchWithName(String searchval)
	{
		String fName_Xpath 	   = "//input[@name='fname']";
		if(waitOnXpath(fName_Xpath, timeOutSecs()))
		{
			setTextField(fName_Xpath, searchval);
			sendReport("Search users using a criteria", "Searching user with First Name "+searchval, "Pass");
		}


	}

	public void available_HostsSearch()
	{
		String search_Button = "//input[@name='submit']";
		String expect_Element = "//form[@name='favorites']/descendant::tr[5]";
		clickOnElement(search_Button);
		if(waitOnXpath(expect_Element, timeOutSecs()))
		{
			sendReport("Clicking on the Search Button for User Info", "Successfully Clicked on Search Button agains User Info", "Pass");
		}
		else
		{
			sendReport("User search verification", "No results are fethched using the search criteria ", "Fail");
		}
	}
	public void viewHostsProperties()
	{
		String hostsprop = "//form[@name='favorites']/descendant::tr[5]/descendant::td[5]/a/img";
		waitOnXpath(hostsprop, timeOutSecs());
		clickOnElement(hostsprop);
		sendReport("Clicking on the View Host Properties ", "Successfully Clicked on the Host Properties Link ", "Pass");
	}
	public void verifiying_CollegeSubmittion(String college)
	{
		String resultXpath = "//td[contains(text(),'Colleges')]/../descendant::td[@class='bodytext']";
		String result = getText(resultXpath).trim();
		if(result.contains(college))
		{
			sendReport("Verifiying the Displayed Result","Successfully Verified the Displayed Result "+result,"Pass");
		}
		else
		{
			sendReport("Verified the Displayed Result","Unable to Verify the Displayed Result "+result, "Fail");
		}
	}
	public void verifiying_ProgramData()
	{
		String search_programXpath = "//td[contains(text(),'Programs:')]/../descendant::li";

		if(waitOnXpath(search_programXpath, timeOutSecs()))
		{
			sendReport("Verifiying the Given Program Data ","Successfully Verified the Program Data ","Pass");
		}
		else{
			sendReport("Verified the Given Program","Unable to Verify the Program", "Fail");
		}
	}
	public void readEmail()
	{
		String read_email_link = "//a[contains(text(),'Read Mail')]";
		if(waitOnXpath(read_email_link, timeOutSecs()))
		{
			clickOnElement(read_email_link);
			sendReport("Clicking on the Link Read Mail", "Successfully found and click on Read Mail", "Pass");
		}
		else
		{
			sendReport("Verify if user is messages page, by clicking on read email link","Unable to click on Read Email link", "Fail");
		}
	}

	public void verifyEmailFor(String emailSubject) throws InterruptedException
	{
		String subject_holder = "//a[contains(text(),'"+emailSubject+"')]";
		String refresh_img    = "//img[@alt='Refresh the screen']";
		for(int i=0;i<20;i++)
		{
			Thread.sleep(5000);
			clickOnElement(refresh_img);
			if(waitOnText(subject_holder))
			{
				sendReport("Verify email for "+emailSubject, "Successfully verified the email for "+emailSubject, "Pass");
				break;
			}
		}


	}

	/*public void verifyEmailSubject(String user, String paytype) throws InterruptedException
	{
		String 	subject_holder = "//td[contains(text(),'"+user+"')]/../following-sibling::tr[2]/td[4]"; 	
		String refreshXpath = "//img[@alt='Refresh the screen']";
		for(int i=1;i<10;i++)
		{
			Thread.sleep(10000);
			clickOnElement(refreshXpath);
			if(waitOnXpath(subject_holder, timeOutSecs()))
			{
				break;
			}
		}
		
		if(waitOnXpath(subject_holder, timeOutSecs()))
		{
			switch (paytype) {
			case "psocredits":
				String sub = getText(subject_holder);
				System.out.println("in pso credits"+sub+"--is sub");
				if(sub.contains("PSO Credits Activation"))
				{
					sendReport("Verify if email is received for PSO Credits activation", "Successfully verified the email for PSO Credits Activation", "Pass");
				}
				break;
			case "trainingcredits":
				String sub1 = getText(subject_holder);
				if(sub1.contains("Training Credits Activation"))
				{
					sendReport("Verify if email is received for Training Credits activation", "Successfully verified the email for Training Credits Activation", "Pass");
				}
				break;
			}
		}
		else
		{
			sendReport("Verify Email received for from PSO credits", "Email is not received for paytype as "+paytype, "Fail");
		}
	}*/


	/**
	 * This method will get the function that is passed from the user properties page
	 * @param functionName
	 */
	public void getFunctionfromUserProperties(String functionName)
	{
		String function_loc = "//span[contains(text(),'Functions:')]/../a[contains(text(),'"+functionName+"')]";
		if(waitOnXpath(function_loc, timeOutSecs()))
		{
			clickOnElement(function_loc);
		}
		else
		{
			sendReport("select "+functionName+" from user properties", "Unable to select "+functionName+" from user properties", "Fail");
		}
	}
	public void select_ClassType_Dropdonw()
	{
		String certSubTypexPath = "//select[@name='sub_type']";
		waitOnXpath(certSubTypexPath, timeOutSecs());	
		selectByVisibleText(certSubTypexPath,"Live Online");
	}
	public void college_SelectionDropDown()
	{
		String collegeXpath = "//td[contains(text(),'College:')]/../descendant::select";
		waitOnXpath(collegeXpath, timeOutSecs());	
		selectByVisibleText(collegeXpath,"All Colleges");
	}
	public void select_CourseManager()
	{
		String courseClass = "//input[@name='GetThis']";
		waitOnXpath(courseClass, timeOutSecs());	
		clickOnElement(courseClass);
	}
	public void click_ClassPropertiesReassign()
	{
		String resign = "//a[contains(text(),'Reassign Course')]";
		waitOnXpath(resign, timeOutSecs());	
		clickOnElement(resign);
	}
	public void searchButton()
	{
		String wildCard = "//input[@name='submitbtn']";
		waitOnXpath(wildCard, timeOutSecs());	
		clickOnElement(wildCard);
		sendReport("Clicking on the Search Button", "Successfully Clicked on the Search Button", "Pass");
	}
	public void wildCardSearchData(String wildSearchData)
	{
		String wildData = "//select[@name='otherFilter']";
		waitOnXpath(wildData, timeOutSecs());	
		selectByVisibleText(wildData, wildSearchData);
		sendReport("Selecting the Value from Drop Down", "Successfully Selected "+wildSearchData+" from Drop Down", "Pass");
	}
	public void selectNameCompanyUser()
	{
		String selectUser = "//td[contains(text(),'1.')]/following-sibling::td//input";
		waitOnXpath(selectUser, timeOutSecs());	
		clickOnElement(selectUser);
		sendReport("Verifiying the User Check Box", "Successfully Verified the User Check Box", "Pass");

	}
	public void additionalFunctions(String additionalFunction)
	{
		String additionalfun = "//select[@id='functionMenu']";
		String continueXpath = "//input[@id='continueBtn']";
		waitOnXpath(additionalfun, timeOutSecs());	
		selectByVisibleText(additionalfun, additionalFunction);
		sendReport("Selecting the Value from the Drop Down", "Successfully Selected the "+additionalFunction+" from Drop Down", "Pass");
		clickOnElement(continueXpath);
		sendReport("Clicking on the Continue Button", "Successfully Clicked on the Continue Button", "Pass");


	}
	public String newExpirationDate()
	{
		String expireDate = "//input[@id='expDate']";
		String textXpath = "//textarea[@id='expDateComment']";
		String updateButton = "//input[@id='updateBtn']";

		if(waitOnXpath(expireDate, timeOutSecs()))
		{
			String currDate = oASelFW.driver.findElement(By.xpath(expireDate)).getAttribute("value");
			sendReport("Retriving the Current Date", "Successfully Retrived the Current Date as "+currDate, "Pass");
			waitOnXpath(expireDate, timeOutSecs());
			clickOnElement(expireDate);
			clickOnElement("//a[@title='Prev']");
			clickOnElement("//a[text()='15']");
			sendReport("Setting the Expired Date", "Successfully Selected the Expired Date", "Pass");

			waitOnXpath(textXpath, timeOutSecs());
			setTextField(textXpath, "Test");
			sendReport("Entering the Note Description", "Successfully Enter the Description as Test", "Pass");
		}
		else
		{
			System.out.println("Need to look into newExpirationDate Methods:::::");
		}
		waitOnXpath(updateButton, timeOutSecs());
		clickOnElement(updateButton);
		sendReport("Clicking on the Update Button", "Successfully Clicked on the Updated Button", "Pass");

		String retrivingUser = "//td[@class='bodytext']/span";
		String User = getText(retrivingUser);
		return User;

		/*String dateXpath = "//input[@name='date_expire']";
		String textXpath = "//textarea[@name='comment']";
		String updateXpath = "//input[@id='updateBtn']";

		String date_Format = "dd/MM/yyyy";
		SimpleDateFormat dateFormat = new SimpleDateFormat(date_Format);
		System.out.println(dateFormat);

        Calendar cal = Calendar.getInstance();
        //cal.add(Calendar.DATE, -2);

        String fromdate = dateFormat.format(cal.getTime());
        System.out.println(fromdate);


    	Date date = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String today = formatter.format(date);
        System.out.println("Today : " + today);

        waitOnXpath(dateXpath, timeOutSecs());
        setTextField(dateXpath, today);
        sendReport("Setting the Expired Date", "Successfully Selected the Expired Date"+today, "Pass");

        setTextField(textXpath, "Test");
		sendReport("Entering the Note Description", "Successfully Enter the Description as Test", "Pass");

		waitOnXpath(updateXpath, timeOutSecs());
		clickOnElement(updateXpath);
		sendReport("Clicking on the Update Button", "Successfully Clicked on the Updated Button", "Pass");
		 */
	}
	public void validatingUserRedMark()
	{



	}

	public void setDefaultDate(int currDate)
	{
		if(currDate==1)
		{
			String backDate = "//a[contains(text(),'30')]";
			String navBack = "//span[@class='ui-icon ui-icon-circle-triangle-w']";
			clickOnElement(navBack);
			waitOnXpath(backDate, timeOutSecs());
			clickOnElement(backDate);

		}
		else if(currDate>1)
		{
			int datetobeselected = currDate-1;
			String dateSelect = Integer.toString(datetobeselected);
			waitOnXpath(dateSelect, timeOutSecs());
			Actions action = new Actions(oASelFW.driver);
			WebElement we = oASelFW.driver.findElement(By.xpath("dateSelect"));
			action.moveToElement(we).build().perform();
		}
		String expireText = "//textarea[@id='expDateComment']";
		String updateExpireDate = "//input[@id='updateBtn']";
		waitOnXpath(expireText, timeOutSecs());
		setTextField(expireText, "Text");
		clickOnElement(updateExpireDate);
	}
	public void onsiteTrainingCoGroup(String mesg)
	{
		String groupXpath = "//b[contains(text(),'"+mesg+"')]";
		String message = getText(groupXpath);
		String plusIconXpath = "//select[@name='id_groupOnsite']/../../../following-sibling::tr/descendant::a";

		waitOnXpath(plusIconXpath, timeOutSecs());
		sendReport("Verifying the '+/-' for "+mesg+" ", "Successfully found and verifiyed '+/-' for "+mesg+" ", "Pass");

		if(waitOnXpath(groupXpath, timeOutSecs()))
		{
			sendReport("Verifying the Display Message", "Successfully Verifiyed the Message as "+message, "Pass");
		}
		else{
			sendReport("Verifiying the Display Message", "Unable to View the Expected Message"+message, "Fail");
		}
	}

	public void getLinkFromMainContent(String link)
	{
		String link_holder = "//a[text()='"+link+"']";
		if(waitOnXpath(link_holder, 60))
		{
			clickOnElement(link_holder);
			sendReport("Verify is user is able to selecct "+link+" from main content", "Successfully selected "+link+" from main content", "Pass");
		}
		else
		{
			sendReport("Verify is user is able to selecct "+link+" from main content", "Unable to select link "+link+" from main content", "Fail");
		}
	}

	public void setRecordFilterInAccreditionDataPge(String filter,String searchval)
	{
		String record_fltr_dd  = "//select[@name='lookup_type']";
		String record_fltr_val = "//input[@name='lookup_value']";
		String submit_btn      = "//input[@value='Search' and @name='submutBtn']";
		if(waitOnXpath(record_fltr_dd, 60))
		{
			selectByVisibleText(record_fltr_dd, filter);
			setTextField(record_fltr_val, searchval);
			clickOnElement(submit_btn);
			sendReport("Verify if user is able to set the Accredition page filter", "Successfully selected the Accredition filter as "+filter+" "+searchval, "Pass");
		}
		else
		{
			sendReport("Verify if user is able to set the Accredition page filter", "Accredition page filter is not displayed", "Fail");
		}
	}

	@SuppressWarnings("null")
	public void verifyExpiredCertificateDisplay()
	{
		String certificate_tbl_holder = "//div[text()='Expired']";
		if(waitOnXpath(certificate_tbl_holder,	20))
		
		{
			sendReport("Verify if the user is able to view the expired certificates information", "Successfully verified the display of expired certificates", "Pass");
		}
		else
		{
			sendReport("Verify if the user is able to view the expired certificates information", "Expired Certificates are not displayed", "Fail");
		}
	}


	/* @author - mohd arifuddin
	 * Description -- This Method will help to Select the Desired Host Available Links under Manage-->Hosts
	 */
	public void manageContentForCourse(String courseName)
	{
		String path1 = "//td[@class='tabNavOff']//a[contains(text(),'"+courseName+"')]";
		String path2 = "//a[contains(text(),'"+courseName+"')]";
		System.out.println(waitOnXpath(path1, 10)||waitOnXpath(path2, 10));
		if(waitOnXpath(path1, 10)||waitOnXpath(path2, 10))
		{
			oASelFW.effecta("click","//a[text()='"+courseName+"']&&//span[text()='"+courseName+"']");
			sendReport("Clicking on the Link", "Successfully Clicked on "+courseName, "Pass");
		}
		else
		{
			System.out.println(courseName+" not found");
			sendReport("Cliking on the Link", "Unable to Find the Given Link"+courseName, "Fail");
		}
	}

	/*
	 * This method is use to retrive the Available host base on the given parameters.
	 * from Manage->Host page
	 * @ arifuddin mohd
	 */
	/*public void filterWithAvailableOptions_ManageHostPage(String filter, String value)
	{

		switch (filter) {
		case "Host ID":
			String host_ID = "//input[@name='host_id']";
			setTextField(host_ID, value);
			sendReport("Setting the Host ID ", "Successfully Selected the Host ID as: "+value, "Pass");
			clickOnElement("//input[@name='submit']");
			break;
		case "Host Name":
			String host_Name = "//input[@name='host_name']";
			setTextField(host_Name, value);
			sendReport("Setting the Host Name ", "Successfully Selected the Host Name as: "+value, "Pass");
			clickOnElement("//input[@name='submit']");
			break;
		case "Delivery Type":
			String del_type = "//select[@name='st_id']";
			selectByVisibleText(del_type, value);
			sendReport("Setting the Delivery Type ", "Successfully Selected the Delivery Type as: "+value, "Pass");
			clickOnElement("//input[@name='submit']");
			break;
		case "Favorite Hosts":
			String fav_host = "//select[@name='show_favorites']";
			selectByVisibleText(fav_host, value);
			sendReport("Setting the Favorite Hosts ", "Successfully Selected the Favorite Hosts as: "+value, "Pass");
			clickOnElement("//input[@name='submit']");
			break;
		default :{

		}

		}
	}*/
	public void verifiyingDisplayedHost_Results(String number)
	{
		String dis_numb = "//span[text()='["+number+"]']";
		String ret_hostName = "//span[text()='["+number+"]']/..";
		try {
			if(waitOnXpath(dis_numb, timeOutSecs()))
			{
				String retrive_host = getText(ret_hostName).trim();
				sendReport("Verifiying the Displayed Result ", "Successfully Verifiyed and Retrived Host Name as: "+retrive_host, "Pass");
			}else
			{
				System.out.println("Unable to click on the displayed link");
				sendReport("Verifiying the Displayed Result ", "Unable to View the Link ", "Fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
























