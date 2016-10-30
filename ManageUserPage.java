package classes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.arsin.ArsinSeleniumAPI;
//import com.sun.net.httpserver.Filter;

public class ManageUserPage extends  myutils{

	public String properties_link = "//td[text()='1.']/following-sibling::td[4]//a";
	public String functions_pane  = "//span[contains(text(),'Functions')]";
	String add_new_address        = "//td[text()='Change to a new address']";
	String fee_information        = "//legend[text()='Fee Information']/../table/tbody/tr[2]/td[2]";
	String update_info_btn        = "//input[@value='Update']";
	String continue_btn           = "//input[@value='Continue']";
	String save_btn               = "//input[@value='Save']";
	String accid_ele              = "//td[contains(text(),'ID:')]/following-sibling::td";
	String adminRight_select = "//select[@id='id_admin_psg']";
	String note_area         = "//textarea[@id='comment']";

	public ManageUserPage(ArsinSeleniumAPI oASelFW) {
		super(oASelFW);
	}

	/**
	 * This method performs a wild card search using account status as 'active' and user type as 'Employee'
	 * @throws InterruptedException
	 */
	public void wildCardSearch(String user) throws InterruptedException
	{
		String usertype   = "//select[@name='type']";
		String acc_Status = "//select[@name='flag_active']";
		String find_user  = "//input[@name='test']";
		String chk_all    = "//a[text()='Add New']";

		waitOnXpath(usertype, timeOutSecs());
		selectByVisibleText(acc_Status, "Active");
		selectByVisibleText(usertype, user);
		clickOnElement(find_user);
		waitOnXpath(chk_all, timeOutSecs());
		sendReport("Search User results verification", "Successfully fetched the results for the search account status 'active' and usertype: "+user, "Pass");
	}
	public void wildCardStatusSearch(String user, String sdate, String edate) throws InterruptedException
	{

		String acc_Status = "//select[@name='accountStatus']";
		String find_user  = "//input[@id='findBottom']";
		String date_start = "//input[@id='sdate']";
		String date_end = "//input[@id='enddate']";
		String edit_Link = "//a[contains(text(),'Edit')]";

		waitOnXpath(find_user, timeOutSecs());
		selectByVisibleText(acc_Status, "Inactive");
		setTextField(date_start, sdate);
		setTextField(date_end, edate);
		clickOnElement(find_user);
		waitOnXpath(edit_Link, timeOutSecs());
		sendReport("User results verification", "Successfully fetched the results for the search account status 'Inactive' ", "Pass");
	}
	public void accountsDisplayedLinks(String option)
	{
		String adjustXpath = "//a[contains(text(),'Adjust')]";
		String editXpath = "//a[contains(text(),'Edit')]";
		String notesXpath = "//a[contains(text(),'Notes')]";

		if(option.contains("Adjust"))
		{
			waitOnXpath(adjustXpath, timeOutSecs());
			clickOnElement(adjustXpath);
			sendReport("Clicking on the "+option+" Link", "Successfully Click on the "+option+" Link ", "Pass");

		}
		if(option.contains("Edit"))
		{
			waitOnXpath(editXpath, timeOutSecs());
			clickOnElement(editXpath);
			sendReport("Clicking on the "+option+" Link", "Successfully Click on the "+option+" Link ", "Pass");

		}
		if(option.contains("Notes"))
		{
			waitOnXpath(notesXpath, timeOutSecs());
			clickOnElement(notesXpath);
			sendReport("Clicking on the "+option+" Link", "Successfully Click on the "+option+" Link ", "Pass");

		}
	}
	public void accountPropertiesStatusPage()
	{
		String statusXpath = "//a[contains(text(),'Cancel account')]";
		String activationMesg = oASelFW.driver.findElement(By.xpath("//td[contains(text(),'Note: This')]")).getText();
		String status_Text = oASelFW.driver.findElement(By.xpath(statusXpath)).getText();
		waitOnXpath(statusXpath, timeOutSecs());
		sendReport("Verifiying the Displayed Message", "Successfully Verifiyed the Given Message as"+activationMesg, "Pass");

		if(waitOnXpath(statusXpath, timeOutSecs()))
		{
			System.out.println(status_Text);
			clickOnElement(statusXpath);
			sendReport("Clicking on the Status Link", "Successfully click on "+status_Text, "Pass");
		}
	}

	public void wildcardsearchAtAddUserToClass(String usertyp)
	{
		String usertype   = "//select[@name='type']";
		String acc_Status = "//select[@name='flag_active']";
		String find_user  = "//input[@value='Find']";

		waitOnXpath(usertype, timeOutSecs());
		selectByVisibleText(acc_Status, "Active");
		selectByVisibleText(usertype, usertyp);

		waitOnXpath(find_user, timeOutSecs());
		clickOnElement(find_user);
		sendReport("Clicking on Find Button ", "Successfully Clicked on Find Button ", "Pass");
	}
	public void changeSavedConformationMesg()
	{
		String saveMesg = "//td[contains(text(),'Changes Saved')]";
		String text_Mesg = getText(saveMesg);
		if(waitOnXpath(saveMesg, timeOutSecs()))
		{
			sendReport("Retriving the Message after clicking on Ok Button ", "Successfully Retrived the Message as "+text_Mesg, "Pass");
		}
		else
		{
			sendReport("Retriving the Message after clicking on Ok Button ", "Unable to Retrived the Message as "+text_Mesg, "Fail");
		}

	}

	public void click_RevenueReports_Link()
	{
		String revenueReports = "//a[text()='Revenue Reports']";
		waitOnXpath(revenueReports, timeOutSecs());
		clickOnElement(revenueReports);	
	}

	public void click_RevenueReportbyStudentILT_Link()
	{
		String revenueReptByStILTxPth = "//a[text()='Revenue Report by Student [ILT]']";
		waitOnXpath(revenueReptByStILTxPth, timeOutSecs());
		clickOnElement(revenueReptByStILTxPth);	
	}
	public void click_RevenueReportbyStudent_SP_Link()
	{
		String revenueReptByStILTxPth = "//a[text()='Revenue Report by Student [SP]']";
		waitOnXpath(revenueReptByStILTxPth, timeOutSecs());
		clickOnElement(revenueReptByStILTxPth);	
	}

	public void type_EndDate_Field()
	{
		String endDatexPath = "//input[@id='fixedText1']";
		waitOnXpath(endDatexPath, timeOutSecs());
		setTextField(endDatexPath, "05/29/2015");
	}


	public String getuseremailfromsearch()
	{
		System.out.println("in user email from search");
		String uname = "//input[@name='username']";
		String disp_uname = null;
		selectUserActions("Properties");
		if(waitOnXpath(uname, timeOutSecs()))
		{
			disp_uname = getTextByAttribute(uname, "value");
		}
		System.out.println("The Email Address::::::"+disp_uname);
		return disp_uname;
		
	}
	public String[] getUserInformation()
	{
		System.out.println("in get first Name ");
		String fName = "//input[@name='fname']";
		String lName = "//input[@name='lname']";
		String comp_Name = "//input[@name='company_name']";

		String disp_fName = null;
		String disp_LName = null;
		String disp_Comp = null;
		if(waitOnXpath(fName, timeOutSecs()))
		{
			disp_fName = getTextByAttribute(fName, "value");
			disp_LName =  getTextByAttribute(lName, "value");
			disp_Comp  = getTextByAttribute(comp_Name, "value");
		}
		String dis[] = {disp_fName, disp_LName, disp_Comp};
		return dis;

	}
	public String getUserEmailFromSearchPayment()
	{
		System.out.println("in user email from search");
		String uname = "//input[@name='username']";
		String userXpath = "//td[@class='inputlabel']/../descendant::td[2]//a";
		String disp_uname = null;
		//selectUserActions("Properties");
		clickOnElement(userXpath);
		if(waitOnXpath(uname, timeOutSecs()))
		{
			disp_uname = getTextByAttribute(uname, "value");
		}
		return disp_uname;
	}
	public void openUrlInExitWebPage()
	{
		String URL = "http://mylearnci.vmware.com/portals/consulting";
		String new_Page = "//input[@name='continue']";
		waitOnXpath(new_Page, timeOutSecs());
		oASelFW.driver.navigate().to(URL);
		waitOnXpath(add_new_address, timeOutSecs());
		sendReport("Registration page display verification", "Navigated to the new registration page "+URL, "Pass");
	}
	public void filling_ExistingWebPage()
	{
		String type_Engagement    = "//select[@id='Type_of_Engagement']";

		waitOnXpath(type_Engagement, timeOutSecs());
		engagement_Details();
		customer_Info();
		main_Contact();
		Enter_VmwareContact_Field();
		Enter_SpecialRequestIssues_Field();
		click_Continue_Button();
		Enter_CustomDescript_Field();
		Enter_ScndEngagementDetal_Fields();
		click_SendThisRequestPSO_Button();
		verifying_ThankYou_Field();
	}public void engagement_Details()
	{
		String dateRange = "//input[@id='Dates']";
		String paymtMethodSerce= "//select[@id='Payment_Service']";
		String paymtMethodTE = "//select[@id='Payment_TE']";
		setTextField(dateRange, "05/06/2015");
		setTextField(paymtMethodSerce, "Prepaid Credits");
		setTextField(paymtMethodTE, "Prepaid Credits");
	}
	public void customer_Info()
	{
		String cmpanyName = "//input[@id='Company']";
		String engagmntAddress= "//textarea[@id='Engagement_Address']";
		String recommndHotlTrelInfo = "//textarea[@id='Travel_Info']";
		setTextField(cmpanyName, "addd");
		setTextField(engagmntAddress, "text");
		setTextField(recommndHotlTrelInfo, "xyz");
	}
	public void main_Contact()
	{
		String contactName = "//input[@id='Contact_Name']";
		String emailAddress = "//input[@id='Contact_Email']";
		String phoneNumber = "//input[@id='Contact_Phone']";
		setTextField(contactName, "arifuddin");
		setTextField(emailAddress, "arif@vmware.com");
		setTextField(phoneNumber, "123456");
	}
	public void Enter_VmwareContact_Field()
	{
		String vmWareContactNam = "//input[@id='VMware_Contact']";
		setTextField(vmWareContactNam, "hello_Arif");
	}
	public void Enter_SpecialRequestIssues_Field()
	{
		String specilaRuestIssues = "//textarea[@id='Notes']";
		setTextField(specilaRuestIssues, "description");
	}
	public void click_Continue_Button()
	{
		String continueButnxPth = "//input[@name='continue']";
		waitOnXpath(continueButnxPth, timeOutSecs());
		clickOnElement(continueButnxPth);
	}
	public void Enter_CustomDescript_Field()
	{
		String customDescriptxPth = "//textarea[@name='custom']";
		setTextField(customDescriptxPth, "Qa_Testing");
	}
	public void Enter_ScndEngagementDetal_Fields()
	{
		String paymntServics = "//input[@name='payment_acct']";
		String paymntTE = "//input[@name='payment_acct_te']";
		setTextField(paymntServics, "1234567");
		setTextField(paymntTE, "1234567");
	}
	public void click_SendThisRequestPSO_Button()
	{
		String sendThisRequetPSOxPth = "//input[@value='Send this request to PSO']";
		waitOnXpath(sendThisRequetPSOxPth, timeOutSecs());
		clickOnElement(sendThisRequetPSOxPth);
	}
	public void clicking_Find_Button()
	{
		String inputSubmit = "//input[@name='submit']";
		waitOnXpath(inputSubmit, timeOutSecs());
		clickOnElement(inputSubmit);

	}
	public void verifying_ThankYou_Field()
	{
		String mesg = "//div[@id='content']/h3";
		String verifyngThankYOU = getText(mesg);
		if(verifyngThankYOU.contains("Thank you!"))
		{
			sendReport("Verifiying Registration Page: ", "Successfully Validated Registration Page: ", "Pass");
		}
		else
		{
			sendReport("Verifiying Registration Page: ", "Fail to Validated Registration Page: ", "Fail");
		}

	}
	/**
	 * This method will select the user actions as--"Properties,Record,Notes,New Payment"--from the search user results page
	 * @param action
	 */
	public void selectUserActions(String action)
	{
		String useraction = "//td[text()='1.']/../descendant::a[contains(text(),'"+action+"')]";
		if(waitOnXpath(useraction, timeOutSecs()))
		{
			clickOnElement(useraction);
			sendReport("Verify is the user action"+action+" is selected", "Successfully selected the user actions as "+action, "Pass");
		}
		else
		{
			sendReport("Verify is the user action"+action+" is selected", "Unable to select the user action "+action, "Fail");
		}
	}

	/**
	 * This will set the filter for searching course record details in User Records page
	 * This accepts filter and filter data as parameters.
	 * @param filter
	 * @param filterData
	 */
	public void setCourseRecordFilterBy(String filter,String filterData)
	{
		String searchTypeDD = "//select[@name='SearchType']";
		String searchTypeID = "//input[@name='SearchType_id']";
		String search_btn   = "//input[@name='submit']";
		String delTyp_DD    = "//select[@name='st_id']";
		switch (filter)
		{
		case "ClassID":
			if(waitOnXpath(searchTypeDD, timeOutSecs()))
			{
				selectByVisibleText(searchTypeDD, "Class ID");
				if(waitOnXpath(searchTypeID, shortWait()))
				{
					setTextField(searchTypeID, filterData);
					sendReport("set text field for filter "+filter+" as "+filterData, "Successfully entered the value for filter "+filter+" as "+filterData, "Pass");
					clickOnElement(search_btn);
				}
				else
				{
					sendReport("set text field for filter "+filter+" as "+filterData, "unable to enter the value for filter "+filter+" as "+filterData, "Fail");
				}
			}
			break;

		case "DeliveryTypeTest":
			if(waitOnXpath(delTyp_DD, timeOutSecs()))
			{
				selectByVisibleText(delTyp_DD, "Test");
				clickOnElement(search_btn);
				sendReport("set delivery type filter verification", "Successfully selected the delivery type filter as Test", "Pass");
			}
			else
			{
				sendReport("set delivery type filter verification", "Unable to select the delivery type filter as Test", "Fail");
			}
		}
	}
	/**
	 * This method verifies the search results based on the filter used during search
	 * @param delType
	 */
	public void verifyDeliveryTypeDisplayInSearchResults(String delType)
	{
		if(waitOnXpath("//td[contains(text(),'Registered/ID')]/../../../tbody", timeOutSecs()))
		{

			WebElement src_body = oASelFW.driver.findElement(By.xpath("//td[contains(text(),'Registered/ID')]/../../../tbody"));
			List<WebElement> rows = src_body.findElements(By.tagName("tr"));
			System.out.println("total numbers of rows in the table are "+rows.size());
			for(int i=1;i<rows.size();i++)
			{
				if(i>2)
				{
					String rowdata = rows.get(i).getText();
					System.out.println(rowdata);
					if(rowdata.contains(delType))
					{
						sendReport("Delivery type verification when inline search is set to type "+delType, "Delivery type display for "+delType+" is succesfully verified from the inline search results", "Pass");
					}
					else
					{
						sendReport("Delivery type verification when inline search is set to type "+delType, "Expected delivery type display is "+delType+" but displayed as "+rowdata, "Fail");
					}
				}
			}

		}
		else
		{
			sendReport("Results verification after inline search", "No results for the inline search are displayed", "Fail");
		}

	}

	/**
	 * This method will verify the inline search results from the course details display page,
	 * This will verify if the displayed results are as per criteria
	 * @param byFilter
	 * @param byCriteria
	 */
	public void verifyInlineSearchResults(String byFilter, String byCriteria)
	{
		String displayed_row = "//td[contains(text(),'Registered/ID')]/../following-sibling::tr";
		waitOnXpath(displayed_row, timeOutSecs());
		String displayed_class_data = getText(displayed_row);
		System.out.println(displayed_class_data);
		if(displayed_class_data.contains(byCriteria))
		{
			sendReport("Verify the inline search display by "+byFilter, "Successfully verified the inline search display by "+byFilter+" "+byCriteria, "Pass");
		}
		else
		{
			sendReport("Verify the inline search display by "+byFilter, "inline search display results are wrongly displayed for when searched by class-ID "+byCriteria, "Fail");
		}
	}

	/**
	 * This is a generic search method which is used to perform an inline search on the records page of a user
	 * the files 'Mul_ftr' and 'txtval' are non mandatory fields
	 * @param filterName
	 * @param filterVal
	 * @param Mul_ftr - This is used when filter name in multiple and always passes the value for delivery type filter
	 * @param txtval
	 */
	public void setInlineSearchAtRecordsPageTo(String filterName, String filterVal,String Mul_ftr, String txtval)
	{
		String ids_dd    	 = "//select[@name='SearchType']";
		String id_val    	 = "//input[@name='SearchType_id']";
		String deltyp_dd	 = "//select[@name='st_id']";
		String search_inline = "//input[@value='Search']";
		String regid_txt     = "//input[@name='id']";
		String course_txt    = "//input[@name='sub_name']";
		String status_dd     = "//select[@name='regState']";
		String date_dd       = "//select[@name='orderBy']";

		switch (filterName) {
		case "ids":
			if(waitOnXpath(ids_dd, timeOutSecs()))
			{
				selectByVisibleText(ids_dd, filterVal);
				setTextField(id_val, txtval);
			}
			break;

		case "deltype":
			if(waitOnXpath(deltyp_dd, timeOutSecs()))
			{
				selectByVisibleText(deltyp_dd, filterVal);
				setTextField(id_val, txtval);
			}
			break;

		case "multiple":
			selectByVisibleText(ids_dd, filterVal);
			selectByVisibleText(deltyp_dd, Mul_ftr);
			setTextField(id_val, txtval);
			break;

		case "regID":
			waitOnXpath(regid_txt, timeOutSecs());
			setTextField(regid_txt, txtval);
			break;

		case "courseName":
			waitOnXpath(course_txt, timeOutSecs());
			setTextField(course_txt, txtval);
			break;

		case "status":
			waitOnXpath(status_dd, timeOutSecs());
			selectByVisibleText(status_dd, filterVal);
			break;

		case "byDate":
			waitOnXpath(date_dd, timeOutSecs());
			selectByVisibleText(date_dd, filterVal);

		}
		System.out.println("after switch");
		clickOnElement(search_inline);
	}

	/**
	 * This method used to select user properties from wild card search,
	 * This will always select the first user displayed after the wild card search
	 */
	public void selectUserProperties()
	{
		if(waitOnXpath(properties_link, timeOutSecs()))
		{
			String inputBox = "//td[text()='1.']/../td[1]//input";
			clickOnElement(inputBox);
			clickOnElement(properties_link);
			waitOnXpath(functions_pane, timeOutSecs());
			sendReport("Select user properties from user search results page", "successfully selected the user properties from user search results", "Pass");
		}
		else
		{
			sendReport("Select user properties from user search results page", "Unable to select user properties from search results page", "Fail");
		}
	}
	public void click_ExitImpersonation_Button()
	{
		String exitImpersonate = "//img[@src='/ui/images/buttons/en/btngraylarge_exit-impersonation.png']";
		waitOnXpath(exitImpersonate, timeOutSecs());
		clickOnElement(exitImpersonate);
		System.out.println("Clicking on Exit Impersonate Button");
	}

	/**
	 * This method will select the impersonate user link from the user properties
	 * @throws InterruptedException
	 */
	public void impersonateUser() throws InterruptedException
	{

		String impersonate_user = "//form[@id='webForm']//a[contains(text(),'Impersonate')]";
		boolean status = waitOnXpath(impersonate_user, timeOutSecs());
		System.out.println("status is "+status);
		if(status==true)
		{
			String Impersonatelink = oASelFW.driver.findElement(By.xpath(impersonate_user)).getAttribute("href");
			String get_imp_link    = Impersonatelink;
			System.out.println("---"+get_imp_link);
			oASelFW.driver.navigate().to(get_imp_link);
			sendReport("Clicking on the Impersonate Link From Displayed Page ", "Successfully Impersonated user from User properties page", "Pass");
		}
		else
		{
			sendReport("Verify Impersonate user from User properties page", "Unable to impersonate user from User properties page", "Fail");
		}
	}

	/**
	 * 
	 * This method is used to verify the register class page ,
	 *  this can be used for all the class types except self paced
	 * @param URL
	 * @throws IOException
	 */
	public void RegisterCoursePage(String URL) throws IOException
	{
		String adminster_btn = "//a[contains(@title,'myLearn Administration Portal')]";
		String change_address_btn = "//td[@class='formbuttons']/input[2]";
		String course_details     = "//td[contains(text(),'Name:')]/../td[2]";
		waitOnXpath(adminster_btn, timeOutSecs());
		oASelFW.driver.navigate().to(URL);
		System.out.println(getText(course_details));
		System.out.println(loadprops("Edit-Course-type"));
		boolean status = waitOnXpath(change_address_btn, timeOutSecs());
		if(status==true&&getText(course_details).trim().contains(loadprops("Edit-Course-type")))
		{
			sendReport("Course details verification for the impersonated user", "Successfully verified the course details for the impersonated user as"+loadprops("Edit-Course-type"), "Pass");
		}
		else
		{
			sendReport("Course details verification for the impersonated user", "Expected course option is "+loadprops("Edit-Course-type")+" but displayed as "+getText(change_address_btn).trim(), "Fail");
		}
	}

	/**
	 * This method will verify the user registration display in the register course page
	 * @throws IOException 
	 */
	public void verifyUserRegistration() throws IOException
	{
		String change_address_btn = "//td[@class='formbuttons']/input[2]";
		String complete_confimation = "//td[contains(text(),'Class:')]/../td[2]";
		if(waitOnXpath(change_address_btn, timeOutSecs()))
		{
			clickOnElement(change_address_btn);
			if(waitOnXpath(complete_confimation, timeOutSecs()))
			{
				sendReport("Verify confirmation after user registration", "Confirmation verified and User has successfully registered to the created course", "Pass");	
			}
			else
			{
				sendReport("Verify confirmation after user registration", "confirmation not displayed, user regisration failed", "Fail");	
			}
		}
	}

	public void getCourseRegisterURL(String URL)
	{
		String course_page = "//img[@src='/ui/images/buttons/en/btngraylarge_exit-impersonation.png']";
		waitOnXpath(course_page, timeOutSecs());
		oASelFW.driver.navigate().to(URL);
		waitOnXpath(add_new_address, timeOutSecs());
		sendReport("Course registration page display verification", "Navigated to the new course page "+URL, "Pass");
	}

	public String addNewAddress(String Tier,String Country) throws InterruptedException
	{
		String address_name       = "//input[@name='coAdd_street']";
		String city_name          = "//input[@name='coAdd_city']";
		String state_name         = "//input[@name='coAdd_state']";
		String Zipcode            = "//input[@name='coAdd_zip']";
		String country_select     = "//select[@name='coAdd_country']";
		String change_address_btn = "//td[@class='formbuttons']/input[2]";
		String select_new_address_radio = "//td[contains(text(),'Change to a new address')]/../td/input";

		waitOnXpath(select_new_address_radio, timeOutSecs());
		clickOnElement(select_new_address_radio);
		waitOnXpath(address_name, timeOutSecs());
		setTextField(address_name, "123 ave");
		setTextField(city_name, "test");
		setTextField(state_name, "te");
		setTextField(Zipcode, "90012");

		switch (Tier) {
		case "Tier1":
			selectByVisibleText(country_select, Country);
			break;
		case "Tier2":
			selectByVisibleText(country_select, Country);
			break;
		case "Tier3":
			selectByVisibleText(country_select, Country);
			break;
		case "Tier4":
			selectByVisibleText(country_select, Country);
			break;
		case "Tier5":
			selectByVisibleText(country_select, Country);
			break;
		case "Tier6":
			selectByVisibleText(country_select, Country);
			break;
		}
		waitOnXpath(change_address_btn, timeOutSecs());
		clickOnElement(change_address_btn);
		waitOnXpath(fee_information, timeOutSecs());

		return Country;

	}

	/**
	 * This method verifies the pricing information display in the register course page 
	 * @param Tier
	 * @param Country
	 */
	public void verifyPricingFor(String Tier,String Country)
	{
		waitOnXpath(fee_information, timeOutSecs());
		String displayed_fee = oASelFW.driver.findElement(By.xpath(fee_information)).getText().trim();
		System.out.println(displayed_fee+"-- displayed fee is --");
		switch (Tier) {
		case "Tier1":
			if(Country.equalsIgnoreCase("Australia")&&displayed_fee.contains("AUD"))
			{
				System.out.println("Pass");
				sendReport("Expected currency display for "+Country+" is AUD", "Actual displayed currency is "+displayed_fee, "Pass");
			}
			else
			{
				System.out.println("Fail");
				sendReport("Expected currency display for "+Country+" is AUD", "Actual displayed currency is "+displayed_fee, "Fail");
			}
			break;

		case "Tier2":
			if(Country.equalsIgnoreCase("China")&&displayed_fee.contains("CNY"))
			{
				sendReport("Expected currency display for "+Country+" is CNY", "Actual displayed currency is "+displayed_fee, "Pass");
			}
			else
			{
				sendReport("Expected currency display for "+Country+" is CNY", "Actual displayed currency is "+displayed_fee, "Fail");
			}
			break;

		case "Tier3":
			if(Country.equalsIgnoreCase("France")&&displayed_fee.contains("EUR"))
			{
				sendReport("Expected currency display for "+Country+" is EUR", "Actual displayed currency is "+displayed_fee, "Pass");
			}
			else
			{
				sendReport("Expected currency display for "+Country+" is EUR", "Actual displayed currency is "+displayed_fee, "Fail");
			}
			break;

		case "Tier4":
			if(Country.equalsIgnoreCase("Japan")&&displayed_fee.contains("JPY"))
			{
				sendReport("Expected currency display for "+Country+" is JPY", "Actual displayed currency is "+displayed_fee, "Pass");
			}
			else
			{
				sendReport("Expected currency display for "+Country+" is JPY", "Actual displayed currency is "+displayed_fee, "Fail");
			}
			break;

		case "Tier5":
			if(Country.equalsIgnoreCase("United Kingdom")&&displayed_fee.contains("GBP"))
			{
				sendReport("Expected currency display for "+Country+" is GBP", "Actual displayed currency is "+displayed_fee, "Pass");
			}
			else
			{
				sendReport("Expected currency display for "+Country+" is GBP", "Actual displayed currency is "+displayed_fee, "Fail");
			}
			break;

		case "Tier6":
			if(Country.equalsIgnoreCase("United States")&&displayed_fee.contains("USD"))
			{
				sendReport("Expected currency display for "+Country+" is USD", "Actual displayed currency is "+displayed_fee, "Pass");
			}
			else
			{
				sendReport("Expected currency display for "+Country+" is USD", "Actual displayed currency is "+displayed_fee, "Fail");
			}
			break;
		}
	}

	public List<String> getUserCreatedDate()
	{
		String create_date_label = "//td[contains(text(),'Created:')]/following-sibling::td";
		String created_date = null;
		List<String> dates = new ArrayList<>();
		if(waitOnXpath(create_date_label, timeOutSecs()))
		{
			created_date = getText(create_date_label);
			String day = created_date.substring(0, 2);
			System.out.println(day);
			String month  = created_date.substring(3, 6);
			System.out.println(month);
			dates.add(day);
			dates.add(month);
		}
		else
		{
			sendReport("verify user created date display", "User created date is not displayed in the user properties page", "Pass");
		}

		return dates;
	}

	public void verifyDisplayedDate(String day,String month)
	{
		int day_disp = Integer.parseInt(day);
		switch (month) {
		case "Jan":
			if(day_disp<23)
			{
				sendReport("Verify the created date of partner central user", "Created date is less than Jan 23", "Fail");
			}

		default:
			sendReport("Verify the created date of partner central user", "Created date is greated than Jan 23", "Pass");
			break;
		}
	}

	public void changeAdminRight()
	{
		if(waitOnXpath(adminRight_select, timeOutSecs()))
		{
			try {
				selectByIndex(adminRight_select, 2);
			} catch (org.openqa.selenium.NoSuchElementException e) {
				System.out.println("In no such exception");
				selectByIndex(adminRight_select, 1);
			}

			waitOnXpath(note_area, timeOutSecs());
			setTextField(note_area, "test note");
			updateInfo();
		}
	}

	public void changeAdminRightToNone()
	{
		if(waitOnXpath(adminRight_select, timeOutSecs()))
		{
			try {
				selectByIndex(adminRight_select, 2);	
			} catch (Exception e) {
				System.out.println("In No such element exception");
				selectByIndex(adminRight_select, 1);
			}

			waitOnXpath(note_area, timeOutSecs());
			setTextField(note_area, "test note");
			updateInfo();
		}	
	}
	public void setAdminRight()
	{
		String adminRight_select = "//select[@id='id_admin_psg']";
		if(waitOnText(adminRight_select))
		{
			selectByIndex(adminRight_select, 2);
			sendReport("set the admin right to coordinator", "Successfully changed the admin right to coordinator", "Pass");
		}
		else
		{
			sendReport("set admin right to Coordinator", "Unable to change the admin right", "Fail");
		}

	}

	public void changeAdminRightAs(String right)
	{
		String adminRight_select = "//select[@id='id_admin_psg']";
		if(waitOnXpath(adminRight_select, timeOutSecs()))
		{
			selectByVisibleText(adminRight_select, right);
			sendReport("Change Admin right verification", "Admin right successfully changed to "+right, "Pass");
			waitOnXpath(note_area, timeOutSecs());
			setTextField(note_area, "test note");
			updateInfo();
		}
		else
		{
			sendReport("Change Admin right verification", "Unable to change Admin right to "+right, "Fail");
		}

	}

	public void navigateBackToUserProperties()
	{
		String userPropsLink = "//a[contains(@href,'userEdit.cfm')]";
		if(waitOnXpath(userPropsLink, timeOutSecs()))
		{
			clickOnElement(userPropsLink);
		}
		else
		{
			System.out.println("Unable to click on user props link");
		}
	}

	public void updateInfoAndVerifyErrorForUserType(String userType)
	{
		if(waitOnXpath(update_info_btn, timeOutSecs()))
		{
			clickOnElement(update_info_btn);
			waitOnAlert();
			sendReport("verify is the validation message is displayed for user type "+userType, "Successfully verified the validation message "+getTextFromAlert()+ "for the user "+userType, "Pass");
			acceptAlert();
		}
	}

	public void getLinkFromWorkFlow(String link)
	{
		String workFlowLink = "//a[contains(text(),'"+link+"')]";
		if(waitOnXpath(workFlowLink, timeOutSecs()))
		{
			clickOnElement(workFlowLink);
		}

	}

	public void updateInfo()
	{
		String save_confirmation = "//td[text()='Changes Saved ']";
		waitOnXpath(update_info_btn, timeOutSecs());
		clickOnElement(update_info_btn);
		if(waitOnXpath(save_confirmation, timeOutSecs()))
		{
			sendReport("Change admin right verification", "User admin right successfully changed from properites page", "Pass");
		}
		else
		{
			sendReport("Change admin right verification", "Unable to change user admin rights from properties page", "Fail");
		}
	}


	/**
	 * This method will get the user actions displayed at the top of the user properties page
	 * @param actionname
	 */
	public void getUserActions(String actionname)
	{
		String action_link = "//a[text()='"+actionname+"']";
		if(waitOnXpath(action_link, timeOutSecs()))
		{
			clickOnElement(action_link);
		}
	}
	public void setTrainingPlanFilter(String filter,String filterData)
	{
		String planID = "//input[@name='cct_id']";
		String planName  = "//input[@name='cct_name']";
		String search_btn    = "//input[@name='submit']";
		switch (filter)
		{
		case "Plan ID:":
			if(waitOnXpath(planID, shortWait()))
			{
				setTextField(planID, filterData);
				sendReport("Entered "+filter+" as "+filterData, "Successfully entered the "+filter+" as "+filterData, "Pass");
				clickOnElement(search_btn);
			}
			else
			{
				sendReport("Entered "+filter+" as "+filterData, "Unable to enter the "+filter+" as "+filterData, "Fail");
			}
			break;

		case "Plan Name:":
			if(waitOnXpath(planID, timeOutSecs()))
			{
				setTextField(planName, filterData);
				sendReport("Entered "+filter+" as "+filterData, "Successfully entered the "+filter+" as "+filterData, "Pass");
				clickOnElement(search_btn);
			}
			else
			{
				sendReport("Entered "+filter+" as "+filterData, "Unable to enter the "+filter+" as "+filterData, "Fail");
			}
			break;
		}
	}
	/*This method will verify the completed and Renewed Date Status in Training Plan Filter Area.
	 * @author - arifuddin mohd
	 */
	public void verifyingCompletedRenewedStatus()
	{
		String renewedStatus = "//td[contains(text(),'Completed')]/../following::tr/descendant::td[5]";
		String dateInfo = getText(renewedStatus).trim();
		if(dateInfo.contains(""))
		{
			waitOnXpath(renewedStatus, timeOutSecs());
			sendReport("Verifiying the Completed and Renewed Data Status ", "Successfully Validated and Found Null Data in Completed Renewed Field ", "Pass");
		}
		else
		{
			sendReport("Verifiying the Completed and Renewed Data Status ", "Found Data in Completed Renewed Field "+dateInfo, "Fail");
		}
	}
	
	public void verifyNotes()
	{
		String notes_link = "//tr[contains(@ondblclick,'showNote')]/td[2]/a";
		String note_displayed = "//tr[contains(@ondblclick,'showNote')]/following::tr/td[2]";
		if(waitOnXpath(notes_link, timeOutSecs()))
		{
			clickOnElement(notes_link);
			sendReport("Verify the note display with User->Notes page", "Added note is user properties is displayed in User->Notes page", "Pass");
		}
		else
		{
			sendReport("Verify the note display with User->Notes page", "Added note is not displayed in User->Notes page", "Fail");
		}
		if(waitOnXpath(note_displayed, timeOutSecs()))
		{
			if(getText(note_displayed).equalsIgnoreCase("test"))
			{
				sendReport("User note Assertion in the displayed note", "entered note while changing the user properties is successfully verfied in the notes", "Pass");
			}
			else
			{
				sendReport("User note Assertion in the displayed note", "Assertion failed - entered note in user properties is not present in the displayed note", "Fail");
			}
		}
	}

	public int returnMonth(int month)
	{
		return month;
	}

	public void selectAddressforAccount()
	{
		String address_dd = "//select[@name='id_address']";
		if(waitOnXpath(address_dd, timeOutSecs()))
		{
			selectByIndex(address_dd, 3);
		}
	}

	public void selectPaymentMethod(String paymethod) throws InterruptedException
	{
		String pay_method = "//select[@name='paymentMethodName']";
		String pay_amount = "//input[@name='tx_amt']";
		if(waitOnXpath(pay_method, timeOutSecs()))
		{
			selectByVisibleText(pay_method, paymethod);
		}
		waitOnXpath(pay_amount, shortWait());
		setTextField(pay_amount, "200");
		Thread.sleep(3000);
	}
	/**
	 * This method will select the payment details from the New payment page.
	 * @param paymethod
	 * @throws InterruptedException 
	 */
	public void setPaymentInformation(String paymethod) throws InterruptedException
	{
		selectAddressforAccount();
		selectPaymentMethod(paymethod);
		waitOnXpath(continue_btn, shortWait());
		clickOnElement(continue_btn);
	}

	public void selectSKU()
	{
		String SKU_DD = "//select[@name='id_SKU']";
		if(waitOnXpath(SKU_DD, timeOutSecs()))	
		{
			selectByIndex(SKU_DD, 2);
		}
	}

	public void orderOriginFrom(String origin)
	{
		String order_origin_dd = "//select[@name='order_origin']";
		if(waitOnXpath(order_origin_dd, timeOutSecs()))
		{
			selectByVisibleText(order_origin_dd, origin);
		}
	}

	public void setPaymentFields()
	{
		String tx_paid = "//input[@name='tx_paid']";
		waitOnXpath(tx_paid, timeOutSecs());
		setTextField(tx_paid, "200");
		setTextField("//input[@name='tx_num']", "1234");
		setTextField("//input[@name='order_number']", "1234");
	}

	/**
	 * This method will set the payment details in the payments order details page
	 * @param origin
	 */
	public void setPaymentDetails(String origin)
	{
		selectSKU();
		orderOriginFrom(origin);
		setPaymentFields();
		if(waitOnXpath(continue_btn, timeOutSecs()))
		{
			clickOnElement(continue_btn);
			sendReport("Select the values from the payment details page", "Successfully selected the values from the payment details page", "Pass");
		}
		else
		{
			sendReport("Select the values from the payment details page", "Unable to select the values from the payment details page", "Fail");
		}
	}

	/**
	 * This method will set the configure payment page details by selecting the college
	 */
	public void setConfigurePayment()
	{
		String college_check = "//td[contains(text(),'College:')]/following-sibling::td/input[1]";
		if(waitOnXpath(save_btn, timeOutSecs()))
		{
			clickOnElement(college_check);
			clickOnElement(save_btn);
			sendReport("Save college details from configure payment page", "Successfully saved the configure details from the configure payment page", "Pass");
		}
		else
		{
			sendReport("Save college details from configure payment page", "Unable to save the details from the configure payment page", "Fail");
		}
	}

	public String getAccountID()
	{
		return getText(accid_ele);
	}
	public void myProfile_Form()
	{
		String myprofile_xpath = "//a[contains(text(),'myProfile')]";
		String alterNativeFieldxPth = "//input[@name='email_alt']";
		String workPhoneXpath = "//input[@name='work']";
		String homephoneXpath = "//input[@name='home']";
		String mobilePhoneFieldxPth = "//input[@name='mobile']";
		String updateProfilexPth = "//input[@value='Update Profile']";

		clickOnElement(myprofile_xpath);
		clean_ExistingData(alterNativeFieldxPth);
		clean_ExistingData(workPhoneXpath);
		clean_ExistingData(homephoneXpath);
		clean_ExistingData(mobilePhoneFieldxPth);
		clickOnElement(updateProfilexPth);
	}

	public void verifying_ProfileStrength_ProcField(String percent)throws IOException
	{
		String profileStrengthxPth = "//div[@id='content']/fieldset/legend";
		String profilestrength = getText(profileStrengthxPth);
		if(profilestrength.contains(percent))
		{
			sendReport("Verifying Profile Strenght from UI: "+profilestrength, "Successfully Verifying Profile Strenght from UI: "+profilestrength, "Pass");
		}
		else
		{
			sendReport("Verify confirmation percentage"+profilestrength, "confirmation not displayed, user mention percentage"+profilestrength, "Fail");
		}
	}

	public void filling_ProfileForm()
	{
		String workPhoneXpath = "//input[@name='work']";
		String homephoneXpath = "//input[@name='home']";
		String mobilePhoneFieldxPth = "//input[@name='mobile']";
		String updateProfilexPth = "//input[@value='Update Profile']";

		waitOnXpath(updateProfilexPth, timeOutSecs());
		setTextField(workPhoneXpath,"1234567");
		setTextField(homephoneXpath,"1234567");
		setTextField(mobilePhoneFieldxPth,"1234567");
		clickOnElement(updateProfilexPth);
	}

	public void type_AlterNavName_field()
	{
		String alterNativeFieldxPth = "//input[@name='email_alt']";
		String updateProfilexPth = "//input[@value='Update Profile']";
		waitOnXpath(alterNativeFieldxPth, timeOutSecs());
		setTextField(alterNativeFieldxPth, "hello123@vmware.com");
		clickOnElement(updateProfilexPth);
	}
	public void verifying_AddreUpdate_ConfirmMessge() throws IOException
	{
		String accntUpdatedConfim = "//td[text()='Account information updated!']";
		if(accntUpdatedConfim.contains("Account information updated!"))
		{
			sendReport("Verifying confirmation for Profile Updation ", "Successfully Profile has Updated "+"Account information updated!", "Pass");
		}
		else
		{
			sendReport("Verify confirmation profile updation ", "Fail to updated profile: ", "Fail");
		}
	}
	public void click_LogOut_Link()
	{
		String logoutButton = "//a[contains(text(),'Log Out')]";
		waitOnXpath(logoutButton, timeOutSecs());
		clickOnElement(logoutButton);
	}	

	public String addUserToClass()
	{
		String duplicate_usr    = "//td/strong/font";
		String nxt_active_usr   = "//td/strong/font/../../../following-sibling::tr/td/input";
		String active_usr       = "//input[@name='GetThis']";
		String user;
		waitOnXpath(active_usr, timeOutSecs());
		if(oASelFW.driver.findElement(By.xpath(duplicate_usr)).isDisplayed())
		{
			clickOnElement(nxt_active_usr);
			user = getText("//td/strong/font/../../../following-sibling::tr/td/input/../../td[2]");
		}
		else
		{
			clickOnElement(active_usr);
			user = getText("//input[@name='GetThis']/../following-sibling::td");
		}
		clickOnElement(continue_btn);
		return user;
	}

	public void verifyResyncmessage()
	{
		String resyngMsg = "//td[contains(text(),'User programs sync process complete!')]";
		if(waitOnText(resyngMsg))
		{
			sendReport("Verify if the message after resync is displayed", "Successfully verified the display of the Resync message", "Pass");
		}
		else
		{
			sendReport("Verify if the message after resync is displayed", "Expected Resync message is not displayed", "Fail");
		}
	}
	
	
}
