package classes;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.remote.server.handler.interactions.SendKeyToActiveElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.arsin.ArsinSeleniumAPI;

public class VmwareEducationPage extends myutils{

	public String searchXpath = "//input[@class='searchBarButton']";
	String submit	= "//input[@name='send']";
	String scorm_btn = "//a[contains(@title,'SCORM Test (Golf)')]";
	String errormsg      = "ElementNotFoundException";

	public VmwareEducationPage(ArsinSeleniumAPI oASelFW) {
		super(oASelFW);
		// TODO Auto-generated constructor stub
	}

	public void verifyEmployeePriceDisplay()
	{
		String priceDisplay = "//td[contains(text(),'Due:')]/following-sibling::td";
		waitOnXpath(priceDisplay, timeOutSecs());
		String disp_price = getText(priceDisplay);
		if(disp_price.contains("0.00"))
		{
			sendReport("Verify course price display for internal employees", "Course price display for internal employees is correctly displayed as -"+disp_price, "Pass");
		}
		else
		{
			sendReport("Verify course price display for internal employees", "Course price display for internal employees is wrongly displayed as -"+disp_price, "Fail");
		}
	}

	public void checkTermsAndConditions()
	{
		String terms_check = "//input[@name='flag_terms_accepted']";
		if(waitOnXpath(terms_check, timeOutSecs()))
		{
			clickOnElement(terms_check);
		}
	}

	public void continueToFeeDetails()
	{
		checkTermsAndConditions();
		String continue_btn = "//input[@value='Continue']";
		if(waitOnXpath(continue_btn, timeOutSecs()))
		{
			clickOnElement(continue_btn);
			sendReport("Verify if user is navigated to fee details page, by checking terms","Successfully selected terms and navigated to fee details page","Pass");
		}
		else
		{
			sendReport("Verify if user is navigated to fee details page, by checking terms","Unabled to select terms and conditions","Fail");
		}
	}

	public void getEnrollmentsFor(String userType)
	{
		String emp_enroll = "//a[contains(text(),'myEnrollments')]";
		switch (userType) {
		case "Employee":
			if(waitOnXpath(emp_enroll, timeOutSecs()))
			{
				clickOnElement(emp_enroll);
			}
			break;
		default:
			break;
		}
	}
	/**
	 * Set the enrollment status to either Begin , Conntinue,Finish or Remove
	 * @param classname
	 * @param type
	 */
	public void setEnrollmentTo(String classname, String type)
	{
		String enroll_type = "//a[contains(text(),'"+classname+"')]/../../descendant::input[contains(@value,'"+type+"')]";
		if(waitOnXpath(enroll_type, timeOutSecs()))
		{
			clickOnElement(enroll_type);
		}
	}

	public void setCourseStatus(String type)
	{
		String set_status = "//a[contains(text(),'"+type+"')]";
		switch (type) {
		case "completed":
			waitOnXpath(set_status, timeOutSecs());
			break;
		default:
			break;
		}
	}
	public void getPastEnrollments()
	{
		String past_link = "//a[contains(text(),'Past Enrollments')]";
		if(waitOnXpath(past_link, timeOutSecs()))
		{
			clickOnElement(past_link);
		}
	}

	public void verifyEnrollmentStatusForCourse(String course,String ExpStatus)
	{
		String courseStatus = "//td[contains(text(),'"+course+"')]/../descendant::td[contains(text(),'"+ExpStatus+"')]";
		if(waitOnXpath(courseStatus, timeOutSecs()))
		{
			sendReport("Enrollment status verification in My Learn Page", "Successfully verified the enrollment status for the course "+course+ " as "+ExpStatus, "Pass");
		}
		else
		{
			sendReport("Enrollment status verification in My Learn Page", "Expected course status in not displayed in the past enrollments page", "Fail");
		}
	}

	public void getLinkFromVMwareEducationPage(String linkName)
	{
		String path1 = "//a[text()='"+linkName+"']";
		String path2 = "//span[text()='"+linkName+"']";
		System.out.println(waitOnXpath(path1, 10)||waitOnXpath(path2, 10));
		if(waitOnXpath(path1, 10)||waitOnXpath(path2, 10))
		{
			oASelFW.effecta("click","//a[text()='"+linkName+"']&&//span[text()='"+linkName+"']");
			sendReport("Clicking on the Link", "Successfully Clicked on "+linkName, "Pass");
		}
		else
		{
			System.out.println(linkName+" not found");
			sendReport("Cliking on the Link", "Unable to Find the Given Link"+linkName, "Fail");
		}
	}
	public void sendTextToSearchContent(String value)
	{
		String boxXpath = "//input[@class='searchBarInput']";
		String selectXpath = "//a[@title='Learn More']//img";
		if(waitOnXpath(boxXpath, timeOutSecs()))
		{
			setTextField(boxXpath, value);
			clickOnElement(searchXpath);
			sendReport("Clicking on the Search Button", "Successfully Clicked on the Search Button", "Pass");
			clickOnElement(selectXpath);
			sendReport("Selecting the given link on Page", "Successfully selected the Displayed Link Learn More", "Pass");
		}
		else
		{
			sendReport("Clicking the the Search Button", "Unable to Locate the Search Button on Education Page", "Fail");
		}
	}


	public void search_CourseCatalogDropDown(String option)
	{
		String catalogXpath = "//select[@name='category']";
		if(waitOnXpath(catalogXpath, timeOutSecs()));
		{
			selectByVisibleText(catalogXpath, option);
			sendReport("Selecting the Value from Drop Down ", "Successfully Selected "+option+" from Drop Down ", "Pass");
			clickOnElement(searchXpath);
			sendReport("Clicking on the Search Button ", "Successfully Clicked on the Search Button", "Pass");
		}
	}
	public void changeCountyPreference(String country)
	{
		String changeCounty = "//a[text()='change your preferences']";
		String countyXpath = "//select[@id='countryPreferenceSelect']";
		String setpreference = "//input[@name='save']";
		try {
			if(waitOnXpath(changeCounty, timeOutSecs()))
			{
				clickOnElement(changeCounty);
				sendReport("Clicking on the Change Country Prefrence Link ", "Successfully Click on Change Country Preference ", "Pass");
				waitOnXpath(countyXpath, timeOutSecs());
				selectByVisibleText(countyXpath, country);
				sendReport("Selecting "+country+" as set preference ", "Successfully Selected "+country+" as set Preference ", "Pass");
				clickOnElement(setpreference);
			}else{}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void displayedSearchedRecordsLink(String option)
	{
		String learnMore = "//a[@title='Learn More']";
		String viewSchedule = "//a[@title='View Schedule']";
		String inquireOnsite = "//a[@title='Inquire about Onsite']";
		try {
			waitOnXpath(viewSchedule, timeOutSecs());
			if(option.contains("View Schedule"))
			{
				waitOnXpath(viewSchedule, timeOutSecs());
				clickOnElement(viewSchedule);
				sendReport("Clicking on the "+option+" Link ", "Successfully Clicked on the "+option+" ", "Pass");
			}
			if(option.contains("Inquire about Onsite"))
			{
				waitOnXpath(inquireOnsite, timeOutSecs());
				System.out.println("in inquire onsite link");
				clickOnElement(inquireOnsite);
				sendReport("Clicking on the "+option+" Link ", "Successfully Clicked on the "+option+" ", "Pass");
			}
			if(option.contains("Learn More"))
			{
				waitOnXpath(learnMore, timeOutSecs());
				clickOnElement(learnMore);
				System.out.println("in learn more link");
				sendReport("Clicking on the "+option+" Link ", "Successfully Clicked on the "+option+" ", "Pass");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getCourseOptionsAs(String option)
	{
		String option_holder = "//td//div/a[@title='"+option+"']/img";
		if(waitOnXpath(option_holder, timeOutSecs()))
		{
			clickOnElement(option_holder);
		}
		else
		{
			System.out.println("expected option is not displayed");
		}
	}

	public void getCourseRelatedLinks(String linkName)
	{
		String link_holder = "//a[contains(@title,'"+linkName+"')]/img";
		if(waitOnXpath(link_holder,60))
		{
			clickOnElement(link_holder);
		}
		else{
			System.out.println("expected link "+"linkName"+"not displayed for the course");
		}
	}

	public void verifyMandatoryFieldsInOnsiteForm(List<String> fields)
	{

		for(String field : fields)
		{
			String field_name = "//td[contains(text(),'"+field+"')]";
			System.out.println(field_name);
			if(waitOnText(field_name))
			{
				String filed_attr = oASelFW.driver.findElement(By.xpath(field_name)).getAttribute("class");
				if(filed_attr.contains("formrequired"))
				{
					sendReport("Required filed verification for field-"+field+" in the onsite enquiry form", "Successfully verified that the field-"+field+" is marked as a required field", "Pass");
				}
				else
				{
					sendReport("Required filed verification for field-"+field+" in the onsite enquiry form", "The Field-"+field+" is  not marked as a required field", "Fail");
				}	
			}

		}
	}
	public void searchedRecordsLinkInDescriptionPage(String option)
	{
		String inquireOnsite = "//a[@name='&lpos=apps_scodevmw : 4']";
		try {
			waitOnXpath(inquireOnsite, timeOutSecs());
			if(option.contains("Inquire about Onsite"))
			{
				waitOnXpath(inquireOnsite, timeOutSecs());
				clickOnElement(inquireOnsite);
				sendReport("Clicking on the "+option+" Link ", "Successfully Clicked on the "+option+" ", "Pass");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void fillDataForMandatoryFields(String city, String country, String count, String phone)
	{
		String cityXpath = "//input[@name='training_city']";
		String countryXpath = "//select[@name='training_country']";
		String studentAttending = "//select[@name='students']";
		String phoneXpath = "//input[@name='phNum']";

		waitOnXpath(cityXpath, timeOutSecs());
		setTextField(cityXpath, city);
		sendReport("Entering the city as "+city+" ", "Successfully Enter the city as "+city+"", "Pass");

		waitOnXpath(countryXpath, timeOutSecs());
		setTextField(countryXpath, country);
		sendReport("Entering the country as "+country+" ", "Successfully Enter the country as "+country+"", "Pass");

		waitOnXpath(studentAttending, timeOutSecs());
		selectByVisibleText(studentAttending, count);
		sendReport("Entering the count as "+count+" ", "Successfully Enter the count as "+count+"", "Pass");

		waitOnXpath(phoneXpath, timeOutSecs());
		setTextField(phoneXpath, phone);
		sendReport("Entering the phoneNumber as "+phone+" ", "Successfully Enter the phoneNumber as "+phone+"", "Pass");
		submitButtonForFormSubmittion();

	}
	public void submitButtonForFormSubmittion()
	{

		if(waitOnXpath(submit, timeOutSecs()))
		{
			clickOnElement(submit);
			sendReport("Clicking on the Submit Button", "Successfully Clicked on Submit Button", "Pass");
		}
		else
		{
			sendReport("Clicking on the Submit Button", "Unable to Click on the Submit Button", "Fail");
		}
	}

	public void submitMessageConf()
	{
		String mesgXpath = "//td[@class='bodytext']/p";
		waitOnXpath(mesgXpath, timeOutSecs());
		String element =  oASelFW.driver.findElement(By.xpath(mesgXpath)).getText();
		if(element.equalsIgnoreCase("Your request has been submitted"))
		{
			sendReport("verifiying the submitted message", "Successfully verifiyed the Message as "+element, "Pass");
		}
		else
		{
			sendReport("verifiying the submitted message", "Unable to view the Displayed Message ", "Fail");
		}
	}
	public void emailWaitingTimeRefresh() throws InterruptedException
	{
		String refreshXpath = "//img[@alt='Refresh the screen']";
		String info = "//td[contains(text(),'1.')]/../following-sibling::tr[2]/td[4]//a";

		waitOnXpath(refreshXpath, timeOutSecs());
		clickOnElement(refreshXpath);

		if(waitOnXpath(info, timeOutSecs()))
		{
			clickOnElement(refreshXpath);
			Thread.sleep(90000);
			clickOnElement(refreshXpath);
			clickOnElement(info);
		}

		oASelFW.driver.switchTo().alert().accept();
		sendReport("Clicking on Ok Button", "Successfully Click on Ok Button", "Pass");
	}
	public void getrespectiveUrl() throws IOException
	{
		String copyUrl = loadprops("linkpaste");
		String instance = loadinstanceprops("instance");
		if(instance.equalsIgnoreCase("ci"))
		{
			copyUrl = copyUrl.replace("mylearn", "mylearnci");
			oASelFW.driver.get(copyUrl);
		}
		else if(instance.equalsIgnoreCase("stage"))
		{
			copyUrl = copyUrl.replace("mylearn", "mylearnstg");
			oASelFW.driver.get(copyUrl);
		}

		else {
			oASelFW.driver.get(copyUrl);
		}

		oASelFW.effecta("sendReport","Verify if the user is navigated to portal","User successfully navigated to Portal "+copyUrl,"Pass");
	}

	public void getRespectiveCourseUrl(String courseStaticLink) throws IOException
	{
		String courseUrl = courseStaticLink;
		System.out.println("Before change "+courseUrl);
		String instance = loadinstanceprops("instance");
		if(instance.equalsIgnoreCase("ci"))
		{
			courseUrl = courseUrl.replace("mylearn", "mylearnci");
			System.out.println("URL after change is--"+courseUrl);
			oASelFW.driver.get(courseUrl);
		}
		else if(instance.equalsIgnoreCase("stage"))
		{
			courseUrl = courseUrl.replace("mylearn", "mylearnstg");
			System.out.println("URL after change is--"+courseUrl);
			oASelFW.driver.get(courseUrl);
		}
		else
		{
			System.out.println("URL after change is--"+courseUrl);
			oASelFW.driver.get(courseUrl);
		}
		oASelFW.effecta("sendReport","Verify if the user is navigated to portal","User successfully navigated to Portal "+courseUrl,"Pass");

	}

	public void displayOnlineTrainingRequestForm(String formName)
	{
		String onlineForm = "//h3[contains(text(),'Online Training')]";

		if(waitOnText(onlineForm))
		{
			sendReport("Verifiying the Form "+formName+"", "Successfully Verifiyed "+formName+" ", "Pass");
		}else
		{
			sendReport("Verifiying the Form "+formName+" ", "Unable to view the Expected form "+formName+" ", "Fail");
		}
	}
	public void getAlertMesgComparision()
	{
		if(waitOnXpath(submit, timeOutSecs()))
		{
			waitOnAlert();
			sendReport("Verify is the validation message is displayed ", "Successfully verified the validation message "+getTextFromAlert()+ " ", "Pass");
			acceptAlert();
		}
		else
		{
			sendReport("Verify is the validation message is displayed ", "Unable to Find the Alert", "Fail");
		}
	}

	public void searchCourse()
	{
		String srch_course_btn = "//input[@class='searchBarButton']";
		if(waitOnXpath(srch_course_btn, timeOutSecs()))
		{
			clickOnElement(srch_course_btn);
		}
		else
		{
			sendReport("Verify if user is able to search for details","User unable to search for course details","Fail");
		}
	}

	public void verifyClassTypeDisplay(String classtype)
	{
		String classTypes_holder = "//a[@title='Show all 9 Delivery types']/img/../../../div";
		String expandClasses     = "//a[@title='Show all 9 Delivery types']/img";
		if(waitOnXpath(expandClasses, shortWait()))
		{
			clickOnElement(expandClasses);
			if(oASelFW.driver.findElement(By.xpath(classTypes_holder)).isEnabled())
			{
				WebElement types_holder = oASelFW.driver.findElement(By.xpath(classTypes_holder));
				List<WebElement> types      = types_holder.findElements(By.xpath("//a[@title='Show only results with this Delivery type']"));
				for(WebElement type : types)
				{
					if(type.getText().trim().equalsIgnoreCase(classtype))
					{
						sendReport("Verify if the class type "+classtype+" is displayed on the course filter of VMware Education page", "Successfully verified the display of class "+classtype+" in vmware education page", "Pass");
					}
					else
					{
						System.out.println("match not found");	
					}
				}
			}
		}
	}

	public void setSearhFieldOnEduHomePage(String srch_val)
	{
		String srch_txt = "//div[@class='searchBarInputBG']/div/input";
		if(waitOnXpath(srch_txt, 60))
		{
			setTextField(srch_txt, srch_val);
			searchCourse();
		}
	}

	public void verifyDeliveryLinks()
	{
		String link_holder = "//td[text()='Title']/../../tr[5]/td/a";
		if(waitOnXpath(link_holder, timeOutSecs()))
		{
			clickOnElement(link_holder);
			sendReport("verify if user is able to select the class room link", "Class room link is activatted and user has successfully selected", "Pass");
		}
		else
		{
			sendReport("verify if user is able to select the class room link", "Class Room links are not displayed for the user", "Fail");
		}
	}
	public String feeInformationDisplay()
	{
		String feeInfo = "//legend[text()='Fee Information']/following-sibling::table/descendant::tr[2]/td[2]";
		String infoText = getText(feeInfo).trim();
		if(waitOnXpath(feeInfo, timeOutSecs()))
		{
			sendReport("Verifiying the Display Fee Information", "Successfully Verifiyed the Diplayed Fee Information "+infoText, "Pass");
		}else {
			sendReport("Verifiying the Display Fee Information", "Unable to Verifiyed the Diplayed Fee Information "+infoText, "Fail");
		}
		return infoText;
	}
	public void quantityOfUsers(String userCount, String address, String city, String state, String zipCode, String country) throws InterruptedException
	{
		String state_Holder = "//select[@name='coAdd_country']";
		String users_Holder = "//td[contains(text(),'Quantity')]/../following-sibling::tr/td//input";

		if(waitOnXpath(state_Holder, 20))
		{
			giveNewAddressInEducationPage(address, city, state, zipCode,country);
			System.out.println("Country Updated");
		}
		if(waitOnXpath("//select[@name='selectedCurrency']", timeOutSecs()))
		{
			retrivingFeeInfoDropDownAndMailingAddress();
			System.out.println("retriving price");
		}

		if(waitOnXpath(users_Holder, timeOutSecs()))
		{
			System.out.println("after page");
			clean_ExistingData(users_Holder);
			setTextField(users_Holder, userCount);
			sendReport("Selecting the Number of Users ", "Successfully Selected "+userCount+" as Quantity of Users ", "Pass");
			System.out.println("quantity of users "+userCount);
		}
		else
		{
			sendReport("Selecting the Number of Users", "Unable to locate the Field on Displayed WebPage ", "Fail");
		}
	}
	/*
	 * @Author - arifuddin
	 * @Description - This method will help to retrive the Mailing Address and Verifiying the price DropDown
	 */
	public void retrivingFeeInfoDropDownAndMailingAddress()
	{
		String price_Dd = "//select[@name='selectedCurrency']";
		String mail_Add = "//legend[text()='Shipping Information']/../descendant::tr[2]/descendant::tr/td[2]";
		if(waitOnXpath(price_Dd, timeOutSecs()))
		{
			sendReport("Verifiying the Fee Price Drop Down ", "Successfully Verify the 'Price Drop Down' ", "Pass");
			System.out.println("The Mailing Address :::::::::"+getText(mail_Add));
			sendReport("Retriving the Shipping Address Infomation ", "Successfully Retrived the Address as: "+getText(mail_Add), "Pass");
		}
		else
		{
			System.out.println("Unable to Load the Shipping Page");
		}
	}
	/**
	 * @author arifuddin_mohd
	 * Description -- This Method will Verify the shipping Information Address Box.
	 */
	public void validatingShippingInfomationDetailsBox()
	{
		String shipping_Xpath = "//legend[text()='Shipping Information']/../descendant::tr[2]";
		if(waitOnXpath(shipping_Xpath, timeOutSecs()))
		{
			sendReport("Verifiying the Shipping Address Filed Box ", "Successfully Validated the Shipping Information Box: "+getText(shipping_Xpath), "Pass");
		}else
		{
			sendReport("Verifiying the Shipping Address Filed Box ", "Shipping Address box not found ", "Fail");
		}
	}

	/**
	 * This method is able to Change the Address Information
	 */
	public void changingShippingAddress()
	{
		String changeAddXpath = "//a[contains(text(),'Change to a new address')]";
		if(waitOnXpath(changeAddXpath, timeOutSecs()))
		{
			clickOnElement(changeAddXpath);
			sendReport("Verifiying the Displayed Change Address Link ", "Successfully verify and Clicked on Change Address Link ", "Pass");
		}else
		{
			sendReport("Verifiying the Displayed Change Address Link ", "Unable to verify the Display address Link ", "Fail");
		}
	}
	public void giveNewAddressInEducationPage(String address, String city, String state, String zipCode, String country) throws InterruptedException
	{
		String state_Holder = "//select[@name='coAdd_country']";
		if(waitOnXpath(state_Holder, timeOutSecs()))
		{
			oASelFW.effecta("typeSpecifiedText","//input[@name='coAdd_street']",address,"Street");
			oASelFW.effecta("typeSpecifiedText","//input[@name='coAdd_city']",city,"City");
			oASelFW.effecta("typeSpecifiedText","//input[@name='coAdd_state']",state,"State");
			oASelFW.effecta("typeSpecifiedText","//input[@name='coAdd_zip']",zipCode,"Zip Code");
			Thread.sleep(2000);
			selectByIndex(state_Holder, 1);
			sendReport("Selecting the Respective Country ", "Successfully Selected the Country as 'United States' ", "Pass");
			oASelFW.effecta("clickAndWait","//td[@class='formbuttons']//input[2]","Update Address");
		}
	}
	/***This method is usefull for Submitting the Shipping Information Data.
	 * @throws InterruptedException 
	 * 
	 */
	public void updatingMailingAddress(String address, String city, String state, String zipCode, String country) throws InterruptedException
	{
		String state_Holder = "//select[@name='coAdd_country']";
		if(waitOnXpath(state_Holder, 30))
		{
			oASelFW.effecta("typeSpecifiedText","//input[@name='coAdd_street']",address,"Street");
			oASelFW.effecta("typeSpecifiedText","//input[@name='coAdd_city']",city,"City");
			oASelFW.effecta("typeSpecifiedText","//input[@name='coAdd_state']",state,"State");
			oASelFW.effecta("typeSpecifiedText","//input[@name='coAdd_zip']",zipCode,"Zip Code");
			Thread.sleep(2000);
			selectByIndex(state_Holder, 1);
			sendReport("Selecting the Respective Country ", "Successfully Selected the Country as 'United State' ", "Pass");
			oASelFW.effecta("clickAndWait","//td[@class='formbuttons']//input[2]","Update Address");
			if(waitOnXpath("//td[2]/strong", 30))
			{
				String errMesg = getText("//td[2]/strong").trim();
				sendReport("Verifiying the Displayed Error Message On the Page ", "Successfully fetched and verify the Message as "+errMesg, "Pass");
			}
			else
			{
				sendReport("Verifiying the Displayed Error Message On the Page ", "Unable to fetched the Error Message from the Web Page ", "Fail");
			}
		}else{
			System.out.println("Unable to locate the Address Field Boxes");

		}
	}

	/***
	 * Description- This method will select the prefered student type from user Information Drop Down
	 * 
	 */
	public void setStudentType(String text)
	{
		String studentHolder = "//select[@name='id_user']";
		if(waitOnXpath(studentHolder, timeOutSecs()))
		{
			selectByVisibleText(studentHolder, text);
			sendReport("Selecting "+text+" as set preference ", "Successfully Selected "+text+" as set Preference ", "Pass");
		}else
		{
			sendReport("Selecting "+text+" as set preference ", "Unable to Select "+text+" as set Preference ", "Fail");
		}
	}
	/**
	 * Description- This method will Click on the Continue Button of Vmware Education Page after Enter Desired Data.
	 * @throws InterruptedException 
	 */
	public void clickEductionContPageButton() throws InterruptedException
	{
		System.out.println("Clicking on continue Button ");
		oASelFW.effecta("clickAndWait","//td[@class='formbuttons']/input[2]","Continue Button");
	}
	/*
	 * This method will Start the Course on Flash Player By clicking on Start Button 
	 * @
	 */
	public void clickStartCourseButton_EdgePortal() throws InterruptedException
	{
		System.out.println("clicking on Start Course Button ");
		oASelFW.effecta("clickAndWait","//input[@type='button']","Start Course");
		Thread.sleep(3000);

	}

	public void naviagate_Scorm_portalPage()
	{
		try {

			String next_Nav = "//div[@id='navDiv']/input[@value='Next ->']";
			String exit_Nav = "//div[@id='navDiv']/input[@onclick='doExit();']";
			oASelFW.driver.switchTo().window(switchTabs().get("SCORM Test (Golf)"));
			System.out.println("Inside of SCORM Test (Golf) Frames********************");
			System.out.println("Waiting for alert");
			Thread.sleep(5000);
			//Alert alert = oASelFW.driver.switchTo().alert();
			//alert.accept();
			System.out.println("Accepted Alert");
			Thread.sleep(5000);
			oASelFW.effecta("sendReport","Verify if the user is navigated to Scorm portal","User successfully navigated to Scorm Portal","Pass");
			for(;;)
			{
				WebDriverWait wait=new WebDriverWait(oASelFW.driver,10);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//frame[@name='SCO']")));
				if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//input[@value='Submit Answers']")))
				{
					System.out.println("In Boolean if method ");
					scormCompletionForm();
					clickOnElement(exit_Nav);
				}else if(waitOnXpath(next_Nav, 10))
				{
					System.out.println("About to click on Next");
					clickOnElement(next_Nav);
					Thread.sleep(5000);
					System.out.println("Successfully clicking on Next Button");
				}
				else
				{
					System.out.println("Inside QA Page");
					scormCompletionForm();
					oASelFW.driver.switchTo().defaultContent();
					System.out.println("outside1 of SCO ");
					break;
				}
				oASelFW.driver.switchTo().defaultContent();
			}
			System.out.println("window switch to VMware Education");
			oASelFW.driver.switchTo().window(switchTabs().get("VMware Education"));

		} catch (Exception e) {
			if (e.equals(errormsg)) {
				oASelFW.effecta("sendReport","WebElement verification","Element not found Scorm Page","Fail");
			}
		}
	}
	public void scormCompletionForm()
	{
		String rulesXpath = "//div[contains(text(),'USGA')]//input";
		String underPar_Xpath = "//div[contains(text(),'eagle')]//input";
		String goldCourse	= "//div[contains(text(),'typical')]/descendant::div//input";
		String stableford_Xpath = "//div[contains(text(),'stableford')]/descendant::div[1]/input";
		String parXpath	= "//div[contains(text(),'Par')]/descendant::div//input";
		String attempting_Xpath = "//div[contains(text(),'Out')]//input";
		String generally_Xpath = "//div[contains(text(),'Generally')]/descendant::div[1]/input";
		String players_Xpath = "//div[contains(text(),'First')]//input";
		String formula_Xpath = "//div[contains(text(),'Slope Rating')]//input";
		String golfer_Xpath	= "//div[contains(text(),'6.')]/descendant::div//input";
		String scratch_Xpath	= "//div[contains(text(),'scratch')]/descendant::div//input";
		String golfer2_Xpath	= "//div[contains(text(),'3.')]/descendant::div//input";
		String friends_Xpath = "//div[contains(text(),'friends')]/descendant::div[2]/input";
		String Knickers_Xpath = "//div[contains(text(),'Knickers')]/descendant::div[2]/input";
		String seriously_Xpath = "//div[contains(text(),'seriously')]/descendant::div[2]/input";
		String submitAnswers_Holder = "//input[@value='Submit Answers']";

		try{

			System.out.println("In Try");
			WebDriverWait wait = new WebDriverWait(oASelFW.driver,10);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@id='contentFrame']")));

			clickOnElement(rulesXpath);

			clickOnElement(underPar_Xpath);

			setTextField(goldCourse, "18");

			clickOnElement(stableford_Xpath);

			setTextField(parXpath, "3");

			clickOnElement(attempting_Xpath);

			clickOnElement(generally_Xpath);

			clickOnElement(players_Xpath);

			clickOnElement(formula_Xpath);

			setTextField(golfer_Xpath, "1");

			setTextField(scratch_Xpath, "0");

			setTextField(golfer2_Xpath, "2");

			clickOnElement(friends_Xpath);

			clickOnElement(Knickers_Xpath);

			clickOnElement(seriously_Xpath);

			clickOnElement(submitAnswers_Holder);

			oASelFW.driver.switchTo().defaultContent();

			System.out.println("outside0 of contentFrame ");

			Thread.sleep(2000);
			sendReport("Submmitting the Scorm Course Completion Form ", "Successfully Completed the Course and Submitted the Scorm Course Form", "Pass");

			//oASelFW.driver.close();

		}
		catch(Exception e)
		{
			e.printStackTrace();

		}

	}

	/**
	 * Description-This Method will update the Student Infomation depends on Student type.
	 * @param fName
	 * @param lName
	 * @param compy
	 * @param emailID
	 * @throws InterruptedException 
	 */
	public void enteringDataForNewStudent(String fName, String lName, String compy, String emailID) throws InterruptedException
	{
		String fNameHolder = "//input[@name='fname']";

		if(waitOnXpath(fNameHolder, timeOutSecs()))
		{
			oASelFW.effecta("typeSpecifiedText","//input[@name='fname']",fName,"First Name");
			oASelFW.effecta("typeSpecifiedText","//input[@name='lname']",lName,"Last Name");
			oASelFW.effecta("typeSpecifiedText","//input[@name='company_name']",compy,"Company");
			oASelFW.effecta("typeSpecifiedText","//input[@name='email']",emailID,"Email ID");
			Thread.sleep(2000);
			oASelFW.effecta("clickAndWait","//table[@class='bodytext']/descendant::tr[2]/td[2]/a[2]//img","Continue Button");
		}
		else
		{
			sendReport("Verifiying the Student Infomation ", "Unable to View the Student Info Page ", "Fail");
		}
	}
	public void billAndPaymentInformation(String address, String type, String amount) throws InterruptedException
	{
		String existingHolder = "//select[@name='id_address']";
		if(waitOnXpath(existingHolder, timeOutSecs()))
		{
			oASelFW.effecta("select",existingHolder,address,"Existing Address");
			oASelFW.effecta("select","//select[@id='paymentMethodName']",type,"Payment Type");
			oASelFW.effecta("typeSpecifiedText","//input[@name='tx_amt']&&name=tx_amt",amount,"Amount");
			Thread.sleep(2000);
			oASelFW.effecta("clickAndWait","//input[@value='Continue']","Continue Button");
		}
		else
		{
			sendReport("Verifiying the Billing Information", "Unable to Verify the Billing Information", "Fail");
		}
	}
	public void paymentDetailsForSKU(String sku, String amount, String currency, String poNumb, String storeID, String college)
	{
		String skuXpath = "//select[@name='id_SKU']";
		String collegeXpath = "//legend[text()='Account Configuration']/../descendant::td[4]";
		try {
			if(waitOnXpath(skuXpath, timeOutSecs()))
			{
				oASelFW.effecta("select",skuXpath,sku,"SKU");
				oASelFW.effecta("typeSpecifiedText","id=tx_paid&&//input[@id='tx_paid']&&name=tx_paid",amount,"Amount");
				oASelFW.effecta("select","id=paid_currency&&//select[@id='paid_currency']",currency,"Currency");
				oASelFW.effecta("typeSpecifiedText","id=tx_num&&//input[@id='tx_num']",poNumb,"PO Number");
				oASelFW.effecta("typeSpecifiedText","id=order_number&&//input[@id='order_number']",storeID,"Store ID");
				oASelFW.effecta("clickAndWait","//input[@value='Continue']","Continue Button");
				waitOnXpath(collegeXpath, timeOutSecs());
				oASelFW.effecta("click","//legend[text()='Account Configuration']/../descendant::td[4]/input["+college+"]","VMware Customer Education");
			}
			else
			{System.out.println("Unable to enter the SKU details "); }

		} catch (Exception e) {
			e.printStackTrace();
			oASelFW.effecta("click","//legend[text()='Account Configuration']/../descendant::td[4]/input["+college+"]","College as VMware Customer Education");
		}

	}
	public void saveConfigurePayment()
	{
		String saveHolder = "//input[@value='Save']";
		oASelFW.effecta("clickAndWait",saveHolder,"Save Button");
	}
	public void creditCard_BillingInfomation(String fName, String lName, String Address, String city, String zipCode, String country, String phoneNumb, String Email) throws InterruptedException
	{
		String secureCheck = "//img[@alt='checkout']";
		String continueButton = "//input[@id='checkoutButton']";
		String cont_Xpath = "//div[@id='dr_continueShopping']/a/img";
		if(waitOnXpath(secureCheck, timeOutSecs()))
		{
			sendReport("Retriving the Product SubTotal Cost ", "Successfully Retrived the SubTotal Cost: "+getText("//td[text()='Sub-Total']/following-sibling::td").trim(), "Pass");
			oASelFW.effecta("clickAndWait",secureCheck,"Continue Button");
			Thread.sleep(4000);
			oASelFW.effecta("typeSpecifiedText","//fieldset[@id='dr_billing']/descendant::span[contains(text(),'First Name')]/../../descendant::input[2]&&//input[@id='billingName1']",fName,"First Name");
			oASelFW.effecta("typeSpecifiedText","//fieldset[@id='dr_billing']/descendant::span[contains(text(),'Last Name')]/../../descendant::input[2]&&//input[@id='billingName2']",lName,"Last Name");
			oASelFW.effecta("typeSpecifiedText","//input[@id='billingAddress1']",Address,"Address");
			oASelFW.effecta("typeSpecifiedText","//input[@id='billingCity']",city,"City");
			selectByIndex("//select[@id='billingState']", 12);
			oASelFW.effecta("typeSpecifiedText","//input[@id='billingPostalCode']",zipCode,"Zip Code");
			oASelFW.effecta("select","//select[@id='billingCountry']",country,"Country");
			oASelFW.effecta("typeSpecifiedText","//input[@id='billingPhoneNumber']",phoneNumb,"Phone Number");
			oASelFW.effecta("typeSpecifiedText","//input[@id='email']",Email,"Email Address");

		}else
		{
			sendReport("Verifiying the Secure Check Information Page ", "Unable to Verify the Secure Check Page ", "Fail");
		}
		if(waitOnXpath("//input[@id='ccNum']", timeOutSecs()))
		{
			oASelFW.effecta("typeSpecifiedText","//input[@id='ccNum']","4111111111111111","Credit Card Number");
			oASelFW.effecta("select","//select[@id='ccMonth']","March","Expire Date 1");
			oASelFW.effecta("select","//select[@id='ccYear']","2017","Expire Date 2");
			oASelFW.effecta("typeSpecifiedText","//input[@id='cardSecurityCode']","123","Card Security Code");
		}
		clickOnElement(continueButton);
		sendReport("Clicking on the Continue Button ", "Successfully Clicked on the Continue Button ", "Pass");
		if(waitOnXpath("//input[@value='0']", timeOutSecs()))
		{
			oASelFW.effecta("clickAndWait","//input[@value='0']","Billing Address");
			if(waitOnXpath("//input[@type='image']", timeOutSecs()))
			{
				oASelFW.effecta("clickAndWait","//input[@type='image']","Billing Address");
				if(waitOnXpath("//input[@id='submitBottom']", timeOutSecs()))
				{
					oASelFW.effecta("clickAndWait","//input[@id='submitBottom']","Submit Billing Address");
					Thread.sleep(3000);
					clickOnElement(cont_Xpath);
					sendReport("Clicking on the Continue Button ", "Successfully Clicked on the Continue Button ", "Pass");
				}
			}
			else
			{
				System.out.println("uanble to locate the billing Address");
			}
		}
	}

	/*
	 * @Author - Arifuddin
	 * Description - This method will Return the product Completed  Status form Vmware Education Page
	 */
	public void registrationCompletedStatus()
	{
		if(waitOnXpath("//p", timeOutSecs()))
		{
			sendReport("Retriving the Registration Message ", "Successfully Retrived the Message as "+getText("//p").trim(), "Pass");
		}
	}

	public void myAccountEducationTabs(String tabName)//myEnrollments
	{
		String tabLInk = "//a[contains(text(),'"+tabName+"')]";
		if(waitOnXpath(tabLInk, timeOutSecs()))
		{
			clickOnElement(tabLInk);
			sendReport("Clicking on the Displayed tab from the Menu Bar ", "Successfully Clicked on "+tabName+" ", "Pass");
		}
	}
	/**
	 * @Author arifuddin_mohd
	 * @Description-- THIS METHOD WILL HELP TO LOCATE THE PRODUCTS IN 'myEnrollment' tab
	 */
	public void myEnrollmentSubscriptionsLinks()
	{
		String subscription_Holder = "//div[@id='content-container']/descendant::table[4]/descendant::tr[3]//td[1]//a";
		if(waitOnXpath(subscription_Holder, timeOutSecs()))
		{
			String getName = getText(subscription_Holder).trim();
			sendReport("Retriving the Displayed Subscription Link ", "Successfully Retrived the Subscription Name as: "+getName, "Pass");
			clickOnElement(subscription_Holder);
			sendReport("Clicking on the Subscription Name Link ", "Successfully Clicked on the Subscription Link And Navigating to the Date Page ", "Pass");
		}
		else
		{
			System.out.println("unable to find the link in mysubscriptionsubTitlesLinks Method ");
		}
	}
	/**
	 * @author arifuddin_mohd
	 * @throws InterruptedException
	 * Description - This method will click on the courses Detail link in product Name from class page.
	 */
	public void myEnrollmentCoursesDetailsLink() throws InterruptedException
	{
		String courseXpath = "//legend[text()='Courses']/../descendant::tr[1]/td[2]/a";
		String details_Xpath = "//a[text()='Course Detail']";
		if(waitOnXpath(courseXpath, timeOutSecs()))
		{
			clickOnElement(courseXpath);
			Thread.sleep(1000);
			clickOnElement(details_Xpath);
			sendReport("Verifiying the Course Detail Link ", "Successfully Clicked on the Course Details ", "Pass");
		}
		else
		{
			System.out.println("Unable to click on the couser details ");
		}

	}
	/** @author arifuddin_mohd
	 * @throws InterruptedException
	 * Description - This method will verify the courses Detail link after Scorm Test Completed .
	 */

	public void myTranscriptCoursesDetailsLink() throws InterruptedException
	{
		String courseXpath = "//strong[text()='Courses']/../following::table/descendant::tr[3]/td[3]";
		if(waitOnXpath(courseXpath, timeOutSecs()))
		{
			sendReport("Verifiying the SCORM Test (Golf) Detail ", "Successfully verify the SCORM Test (Golf) From Courses Page", "Pass");
		}
		else
		{
			sendReport("Verifiying the SCORM Test (Golf) Detail ", "Unable to verify the SCORM Test (Golf) From Courses Page", "Fail");
		}

	}
	/**
	 * @author arifuddin_mohd
	 * Description - This Method will help to get the retrive the expire Date from Product page.
	 * @return
	 */
	public String retrivingProductEndDateFromMyEnrollmentTab()
	{
		String endDate_Xpath = "//td[contains(text(),'Summary')]/following-sibling::td/div[2]/descendant::strong[2]";
		String end_Date = getText(endDate_Xpath).trim();
		sendReport("Retriving the Course End Date ", "Successfully Retrived the Course End Date "+end_Date, "Pass");
		System.out.println("product End Date**********"+end_Date);
		end_Date = end_Date.replace(" ", "-");

		return end_Date;
	}

	/**
	 * @Author arifuddin_mohd
	 * @Description-- This method will validate the Subscription link in Getting Started Page
	 */
	public void gettingStartedSubscriptionsLinks()
	{
		String subscription_Holder = "//td[text()='Name']/../following-sibling::tr[1]//a";
		if(waitOnXpath(subscription_Holder, timeOutSecs()))
		{
			String getName = getText(subscription_Holder).trim();
			sendReport("Retriving the Displayed Subscription Link ", "Successfully Retrived the Subscription Name as: "+getName, "Pass");
			clickOnElement(subscription_Holder);
			sendReport("Clicking on the Subscription Name Link ", "Successfully Clicked on the Subscription Link And Navigating to the Date Page ", "Pass");
		}
		else
		{
			System.out.println("unable to find the link in mysubscriptionsubTitlesLinks Method ");
		}
	}
	public void subscribeTheSubsciptionLinkFromGettingStartedTab()
	{
		String subscribe_Holder = "//legend[text()='Subscriptions']/../descendant::tr[1]/td[3]/a//img";
		if(waitOnXpath(subscribe_Holder, timeOutSecs()))
		{
			clickOnElement(subscribe_Holder);
			System.out.println("**********Clicked on subscribe tab*********");
			sendReport("Clicking on the Subscribe Link ", "Successfully Product has been Subscribed ", "Pass");
		}else
		{
			System.out.println("**************Subscription links are not available in getting started Page *******************");
		}

	}

	/**
	 * @author arifuddin_mohd
	 * @Description-- This method will Retrive all the Course Links from VMware Education Page.
	 */
	public void retriveCourseLinksInVMwareEducationPage(String courseName)
	{
		WebElement src_body = oASelFW.driver.findElement(By.xpath("//td[text()='Course']/../../../tbody"));
		List<WebElement> rows = src_body.findElements(By.tagName("tr"));
		System.out.println("total numbers of rows in the table are "+rows.size());
		for(int i=3;i<rows.size();i++)
		{
			String courses = oASelFW.driver.findElement(By.xpath("//td[text()='Course']/../../../tbody/tr["+i+"]/td[2]//a")).getText();
			System.out.println("The course Name is:::   "+courses);
			if(courses.equalsIgnoreCase(courseName))
			{
				clickOnElement("//td[text()='Course']/../../../tbody/tr["+i+"]/td[2]//a");
				sendReport("Clicking on the Displayed Course Link ", "Successfully Clicked on the Course Link ", "Pass");
				break;
			}
		}
	}
	/**
	 * @author arifuddin_mohd
	 * @Description - This method is userfull for completing the Product Registration process.
	 */
	public void registerTheProductInmyEnrollmentTab()
	{
		String subscribe_Holder = "//table[@class='tabularResults']/descendant::tr[2]/td[5]/a//img";
		String continue_Button = "//td[@class='formbuttons']/input[2]";

		if(waitOnXpath(subscribe_Holder, timeOutSecs()))
		{
			clickOnElement(subscribe_Holder);
			System.out.println("**********Clicked on Register Button*********");
			sendReport("Clicking on the Register Button ", "Successfully Clicked on Register Button ", "Pass");
			clickOnElement(continue_Button);
			sendReport("Clicking on the Continue Button ", "Successfully Clicked on the Continue Button ", "Pass");
		}else
		{
			System.out.println("**************Register Button/links are not available *******************");
		}
	}

	/**
	 * @author arifuddin_mohd
	 * @Description -- This Method will be usefull of validating the courses class schedule page.
	 */
	public void verifiyingCoursesClassSchedulePage()
	{
		String deliveryType = "//table[@class='tabularResults']/descendant::tr[1]/th[4]";
		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//h1[contains(text(),'Class Schedule')]")))
		{
			String deliverType = getText(deliveryType).trim();
			sendReport("Validating the Delivery Type Element ", "Successfully Validated the Element as: "+deliveryType, "Pass");
			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//table[@class='tabularResults']/descendant::tr[2]/td[5]/a//img")))
			{
				sendReport("Validating the Course Result Page Tab ", "Successfully Validated Tab Name 'Register Now' ", "Pass");
				if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//table[@class='tabularResults']/descendant::tr[2]/td[5]/div[1]/a//img")))
				{
					sendReport("Validating the Course Result Page Tab ", "Successfully Validated Tab Name 'Learn More' ", "Pass");
				}
			}
		}
	}
	/**
	 * @author siddharth_pangotra
	 * @Description- On successful payment, this method will take you to configure you subscription under MySubscription
	 */
	public void configureUrSubscription()
	{
		String xpathForLink = "//a[text()='Configure your subscription']";
		if(waitOnXpath(xpathForLink, timeOutSecs()))
		{
			clickOnElement(xpathForLink);
			sendReport("Clicking on the Displayed Element", "Successfully Clicked on Configure your Subscription link ", "Pass");
		}
	}

	/**
	 * @author siddharth_pangotra
	 * @Description- On configuring subscription, this methods helps you to select the method to configure
	 */
	public void selectmethodtoconfigure(String selcttype) throws IOException
	{

		String continueXpath = "//input[@name='continue']";

		if(selcttype.equalsIgnoreCase("Named User"))
		{
			String NamedUserXpath = "//input[@value='U']";
			if(waitOnXpath(NamedUserXpath, timeOutSecs()))
			{
				clickOnElement(NamedUserXpath);
				sendReport("Select the Namded User to configure ", "Successfully selected the Namded User to configure ", "Pass");
			}
		}
		else if(selcttype.equalsIgnoreCase("Named Domains"))
		{
			String NamedUserXpath = "//input[@value='D']";
			if(waitOnXpath(NamedUserXpath, timeOutSecs()))
			{
				clickOnElement(NamedUserXpath);
				sendReport("Select the Namded User to configure ", "Successfully selected the Namded User to configure ", "Pass");
			}

		}

		else if(selcttype.equalsIgnoreCase("Passcode"))
		{
			String NamedUserXpath = "//input[@value='P']";
			if(waitOnXpath(NamedUserXpath, timeOutSecs()))
			{
				clickOnElement(NamedUserXpath);
				sendReport("Select the Namded User to configure ", "Successfully selected the Namded User to configure ", "Pass");
			}

		}

		clickOnElement(continueXpath);

	}	

	/**
	 *  @author arifuddin
	 *   @Description- User will be Taken to Uploading File Page.
	 */
	public void usersBulkUploadPage()
	{
		String uploadXpath = "//a[contains(text(),'Users Bulk')]";
		if(waitOnXpath(uploadXpath, timeOutSecs()))
		{
			clickOnElement(uploadXpath);
			sendReport("Verifiying the Users Bulk Upload Page ", "Successfully verify and Navigated to the Source Upload Page ", "Pass");
		}
	}
	/**
	 * Description - This method will Browse the Available Files form Source location.
	 */
	public void clickonBrowseButton()
	{
		waitOnXpath("//td[contains(text(),'Source')]/../descendant::td//input", timeOutSecs());
		oASelFW.effecta("clickAndWait","//td[contains(text(),'Source')]/../descendant::td//input","Browse Files");
	}
	
	public void uploadFile() throws IOException, InterruptedException
	{
		Thread.sleep(5000);
		Runtime.getRuntime().exec(oASelFW.sAutomationPath+"\\myLearn\\autoit\\Fileupload.exe");
	}

	public void switchToFrameforUpload()
	{
		WebDriverWait wait = new WebDriverWait(oASelFW.driver, 10);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//frame[@name='right']")));
		
	}
	
	/**
	 *  @author arifuddin
	 *   @Description- This Method will Upload the Excel File from Source Location and Update at Target Fields.
	 */

	public String uploadSampleSourceFile(String File)
	{
		String fileName =null;
		try{

			System.out.println("in the click_Attachfilelink");
			String filename=(oASelFW.sAutomationPath+"autoit\\"+File+".xls");
			System.out.println(filename);

			Thread.sleep(3000);
			int index = filename.lastIndexOf("\\");
			fileName = filename.substring(index + 1);

			if(oASelFW.sBrowser.contains("Chrome")){
				Process process = new ProcessBuilder(oASelFW.sAutomationPath+"\\myLearn\\autoit\\NewUploadChrome.exe", filename, "Open").start(); 
				Thread.sleep(8000);
			}
			if(oASelFW.sBrowser.contains("Firefox")){
				System.out.println("in the fire fox browser");
				Process process = new ProcessBuilder(oASelFW.sAutomationPath+"\\myLearn\\autoit\\NewUploadFirefox.exe", filename, "Open").start(); 
				Thread.sleep(8000);
			}

			if(oASelFW.sBrowser.contains("Internet Explorer")){
				Process process = new ProcessBuilder(oASelFW.sAutomationPath+"\\ECMS_PSO_S1\\src\\scripts\\ecms\\UpLoadAttachmentIE.exe", filename, "Open").start(); 
				Thread.sleep(8000);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return fileName;
	}

	/**
	 *  @author arifuddin
	 * @throws InterruptedException 
	 *   @Description- This method will Verify the Downloading File Link and Download the Sample File.
	 */
	public void clickOnDownloadSampleTemplateLink() throws InterruptedException
	{
		String sampleLink = "//a[contains(text(),'sample template')]";
		if(waitOnXpath(sampleLink, timeOutSecs()))
		{
			clickOnElement(sampleLink);
			Thread.sleep(2000);
			sendReport("Clicking on the Sample Template File Link ", "Successfully Downloaded the Sample Template File ", "Pass");
		}else
		{
			sendReport("Verifiying the Users Bulk Upload Page ", "Unable to Locate the Sample Template File Link ", "Fail");
		}
	}
	/*
	 * Description - This method will userfull for Importing the Files.
	 */
	public void clickToImportFile() throws InterruptedException
	{
		oASelFW.effecta("click","//input[@value='Import']","Import File");
		Thread.sleep(10000);
	}
	/**
	 * Description - This Method will Validate the Empty File Error Message.
	 */
	public void validatingEmptyErrorFileUploadMessage()
	{
		String errMesg = "//li[contains(text(),'Spreadsheet appears to be empty')]";
		if(waitOnXpath(errMesg, timeOutSecs()))
		{
			oASelFW.driver.findElement(By.xpath(errMesg)).isDisplayed();
			sendReport("Validating the Empty Error File Message ", "Successfully Retrived the Error Message as: "+getText(errMesg).trim(), "Pass");
		}

	}
	public void fillEndUserDetails()
	{
		String fname = "//input[@name='fname']";
		String lname = "//input[@name='lname']";
		String company_name = "//input[@name='company_name']";
		String email = "//input[@name='email']";
		String save_n_finish = "//input[@value='Save & Finish']";
		if (waitOnXpath(fname, timeOutSecs()))			
			setTextField(fname, "Lokesh");

		if (waitOnXpath(lname, timeOutSecs()))
			setTextField(lname, "Joshi");

		if (waitOnXpath(company_name, timeOutSecs()))

			setTextField(company_name, "HCl");


		if (waitOnXpath(email, timeOutSecs()))
			setTextField(email, "LokeshJ@hcl.com");

		if (waitOnXpath(save_n_finish, timeOutSecs()))
		{
			clickOnElement(save_n_finish);
			sendReport("Click Save and Finish ", "Successfully Clicked on Save and Finish ", "Pass");
		}


	}


	public void goToGettingStartedPage()
	{
		String gettingStarted_xpath= "//a[text()='Getting Started']";
		String subscrptionName_xpath = "//td[@width = '80%']/a";


		if (waitOnXpath(gettingStarted_xpath, timeOutSecs()))		
			clickOnElement(gettingStarted_xpath);

		try{
			if (waitOnXpath(subscrptionName_xpath, timeOutSecs()))
				clickOnElement(subscrptionName_xpath);
			sendReport("VErify that Subsciption is enlisted on getting started Page", "VErified Subscription Link on Getting Started page", "Pass" );
		}

		catch (Exception e) {
			e.printStackTrace();
			sendReport("VErify that Subsciption is enlisted on getting started Page", "VErified Subscription Link on Getting Started page not found", "Fail" );
		}
	}





	{
		String waitSphereLink = "//legend[text()='Subscriptions']/following-sibling::table/descendant::tr[2]/td//a";

		try {
			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//a[contains(text(),'VMware vSphere')]")))
			{
				String subName = getText(waitSphereLink).trim();
				sendReport("Retriving the Displayed Name ", "Successfully Retrived the Names as "+subName, "Pass");
			}
			else if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//legend[text()='Subscriptions']/../descendant::td/a[contains(text(),'VMware')]")))
			{
				System.out.println("in sendreport with out exit method");
				oASelFW.effecta("sendReportWithOutExit","Verifiying the Displayed Link From Web Page ","Successfully verify the Displayed Page ","Pass");
			}
		} catch (NoSuchElementException e) {
			System.out.println("unable to find the vmware vsphere link ");
			sendReport("Verifiying the Displayed Link From Web Page ", "Unable to verify the Displayed Links ", "Fail");
		}
	}
	/**
	 * This method will display the links in 'mySubscription' tab
	 */
	public void mySubscriptionSubTitlesLinks()
	{
		String titles_Holder = "//div[@id='content-container']/descendant::table[4]/descendant::tr[2]//td[1]";
		String clickManage = "//div[@id='content-container']/descendant::table[4]/descendant::tr[2]//td[5]/a[contains(text(),'Manage')]";

		if(waitOnXpath(titles_Holder, timeOutSecs()))
		{
			String getTitle = getText(titles_Holder).trim();
			sendReport("Retriving the Displayed Titles ", "Successfully Retrived the titles form the Page "+getTitle, "Pass");
			clickOnElement(clickManage);
			sendReport("Clicking on the Manage Link ", "Successfully Clicked on the Manage Link ", "Pass");

		}
		else
		{
			System.out.println("unable to find the link in mysubscriptionsubTitlesLinks Method ");
		}
	}

	/**
	 * @arifuddin mohd
	 * @param linkName = This argument will take the value and click on the respective tab from vmware education page...!!
	 */

	public void getVmwareEducationSubLinks(String linkName)//Users
	{
		String path1 = "//a[text()='"+linkName+"']";
		if(waitOnXpath(path1, timeOutSecs()))
		{
			clickOnElement(path1);
			sendReport("Clicking on the Link", "Successfully Clicked on "+linkName, "Pass");
		}
		else
		{
			System.out.println(linkName+" not found");
			sendReport("Cliking on the Link", "Unable to Find the Given Link"+linkName, "Fail");
		}
	}
	/**
	 * @arifuddin mohd 
	 * This method will help to send the activation request to the users
	 */

	public void clickOnActivationRequestLink(String user, String invite)//Niels, Resend invite.
	{
		String resend_Link = "//td[contains(text(),'"+user+"')]/following-sibling::td//a[contains(text(),'"+invite+"')]";
		String email_Mesg = "//td[contains(text(),'Email invitation')]";
		if(waitOnXpath(resend_Link, timeOutSecs()))
		{
			clickOnElement(resend_Link);
			sendReport("Clikcing on the Resend Link ", "Successfully clicked on the Resend Link ", "Pass");
			sendReport("Validating the Email Resend Message ", "Successfully Validated the Email Message as: "+getText(email_Mesg), "Pass");
		}
		else
		{
			System.out.println("Unable to locate 'Resend Invite Link' ");
		}
	}
	/**
	 * @arifuddin mohd 
	 * @Description - This method will Delete the User from Displayed vmware education page.
	 */
	public void delete_UserFromVmwareEducationPage(String user)
	{
		String del_User = "//td[contains(text(),'"+user+"')]/following-sibling::td//a//img";
		if(waitOnXpath(del_User, timeOutSecs()))
		{
			clickOnElement(del_User);
			sendReport("Clikcing on the Trash Button ", "Successfully clicked on the Trash Button ", "Pass");
		}
		else
		{
			System.out.println("Unable to locate 'Trash Button ' ");
		}
	}

	public String verifiyingDisplayedUsers(String fname, String lname, String comp, String email)
	{
		String value="";
		String userName = "+fname+" +"+lname+";
		String addUser = "//a[contains(text(),'Add User(s)')]";
		String cnt=oASelFW.effecta("getXpathCount","//td[text()='Email']/../following-sibling::tr/..");
		int count=Integer.parseInt(cnt);
		System.out.println("count"+count);
		for(int i=1;i<count;i++){
			value=oASelFW.driver.findElement(By.xpath("//td[text()='Email']/../following-sibling::tr["+i+"]/td[2]/a[contains(text(),'"+userName+"')]")).getText();
			System.out.println(value+"*****************"+userName);
			if(value.trim().contains(userName)){
				//value=value.substring(0,value.indexOf("-"));
				oASelFW.driver.findElement(By.xpath("//td[text()='Email']/../following-sibling::tr["+i+"]/td[2]/a[contains(text(),'"+userName+"')]"));
				break;
			}else
			{
				clickOnElement(addUser);
				addUserInformation(fname, lname, comp, email);
			}
		}
		return value.trim();
	}
	public void addUserInformation(String fname, String lname, String comp, String email)
	{
		if(waitOnXpath("//input[@name='fname']", timeOutSecs()))
		{
			oASelFW.effecta("typeSpecifiedText","//input[@name='fname']",fname,"User First Name");
			oASelFW.effecta("typeSpecifiedText","//input[@name='lname']",lname,"User Last Name");
			oASelFW.effecta("typeSpecifiedText","//input[@name='company_name']",comp,"Company");
			oASelFW.effecta("typeSpecifiedText","//input[@name='email']",email,"Email ID");
			oASelFW.effecta("clickAndWait","//input[@name='snf']","Save and Continue");
		}
		else
		{
			System.out.println("unable to locate the element");
		}
	}
	/**
	 * @ arifuddin mohd
	 * This method will perform the Verification of Displayed links and Date of Subscription.
	 */
	public String verifyExpireDateForSubscriptionInVMwareSphere()
	{
		String dateInfo = null;
		String waitSphereLink = "//div[@id='content']/descendant::h2";
		String subsDate = "//div[@id='content']/div//div//table/descendant::tr[3]/td[3]//strong";
		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//a[contains(text(),'VMware vSphere')]")))
		{
			String subName = getText(waitSphereLink).trim();
			sendReport("Retriving the Displayed Product Name ", "Successfully Retrived the Product Names as "+subName, "Pass");
			dateInfo = getText(subsDate).trim();
			System.out.println("************ Expire Date for Product is *************"+dateInfo);
			sendReport("Retriving the Subscription Date ", "Successfully Verifiyed the Subscription Date "+dateInfo, "Pass");

		}else{
			sendReport("Verifiying the Displayed Date ", "Unable to Find Expected Date ", "Fail");
			System.out.println("Unable to locate the element");
		}
		return dateInfo;
	}


	public void compareDatesByCompareTo(DateFormat df, Date oldDate, Date newDate) {

		//how to check if date1 is equal to date2
		if (oldDate.compareTo(newDate) == 0) {
			System.out.println(df.format(oldDate) + " and " + df.format(newDate) + " are equal to each other");
			sendReport("Validating the two Date ", "Successfully Validated Subscription and course date are Found to be Equal ", "Pass");
		}

		//checking if date1 is less than date 2
		if (oldDate.compareTo(newDate) < 0) {
			System.out.println(df.format(oldDate) + " is less than " + df.format(newDate));
			sendReport("Validating the two Date ", "Successfully Validated the subscription Date "+oldDate+" is lesser than the Course Date "+newDate+" ", "Pass");
		}

		//how to check if date1 is greater than date2 in java
		if (oldDate.compareTo(newDate) > 0) {
			System.out.println(df.format(oldDate) + " is greater than " + df.format(newDate));
			sendReport("Validating the two Date ", "Successfully Validated the subscription Date "+oldDate+" is greater than the Course Date "+newDate+" ", "Pass");
		}

	}


	/*
	 * @Author-- arifuddin
	 * Description -- This method will retrive the Email ID of the Existing Users from VMware Education Page.
	 * 
	 */
	public void retriveUserWithEmailIDfromUsersTab()
	{
		if(waitOnXpath("//td[contains(text(),'Results')]/../../../../../../../tbody", timeOutSecs()))
		{

			WebElement src_body = oASelFW.driver.findElement(By.xpath("//td[contains(text(),'Results')]/../../../../../../../tbody"));
			List<WebElement> rows = src_body.findElements(By.tagName("tr"));
			System.out.println("total numbers of rows in the table are "+rows.size());
			for(int i=3;i<rows.size();i++)
			{
				System.out.println("the users got uploading::: "+rows.get(i).findElement(By.xpath("//td[2]")).getText());
			}
			sendReport("Verifiying the Uploaded Users ", "Successfully Users got uploaded ", "Pass");
		}
		else
		{
			sendReport("Results verification inline search", "No results for the inline search are displayed", "Fail");
		}

	}

	public void verifyUsersUnderSubscription() throws IOException
	{
		List<String> nameList = new ArrayList<>();
		nameList.add(loadprops("user1"));
		nameList.add(loadprops("user2"));
		nameList.add(loadprops("user3"));
		nameList.add(loadprops("user4"));
		nameList.add(loadprops("user5"));

		String coordinator_Xpath = "//div[@id='content']/strong";
		sendReport("Verifiying the Coordinator on VMware Education Page ", "Successfully Verify and Retrived the Coordinator as: "+getText(coordinator_Xpath), "Pass");

		for(String link : nameList)
		{
			String link_text = "//td[contains(text(),'"+link+"')]";
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

	public void ClickOnUsersInMysubscription()
	{
		String 	users_xpath="//td[text()='Users']";
		if(waitOnXpath(users_xpath, timeOutSecs()))
		{
			clickOnElement(users_xpath);
			sendReport("Click on Users Tab", "Successfully clicked on Users tab", "Pass");
		}
		else
		{
			sendReport("Click on Users Tab", "Users tab not Found", "Fail");
		}

	}

	/**
	 * This method will help the user to verify whether the URL is present on not
	 * 1. xpath= the system will retrieve text from this xpath
	 * 2. name= this will assist system that from where the verification is needed to be made
	 * 3. txt_to_verify = is the text which we need to verify from the page
	 * 4. Status1 = what status to show if link is found
	 * 5. Status2 = What status to show if link not found
	 * @param xpath
	 * @param name
	 * @param txt_to_verify
	 */

	public void VerifyPresenceOfURL(String xpath, String name, String txt_to_verify, String Status1, String Status2 )
	{
		if(waitOnXpath(xpath,timeOutSecs()))
		{
			sendReport("Verify " +txt_to_verify+" is present on VMware Education" , txt_to_verify+ " found", Status1 );

		}

		else
		{
			sendReport("Verify " +txt_to_verify+" is present on" + name  , txt_to_verify+ " found on "+ name, Status2 );
		}
	}


	public void VerifymySubscriptionPresentonVMwareEdu(String xpath, String name, String txt_to_verify)
	{

		String subscribeUser_xpath="//div[@id='content']/table[2]/tbody/tr[2]/td/table/tbody/tr[2]/td[4]/a";

		if(waitOnXpath(xpath, 15))
		{
			sendReport("Verify " +txt_to_verify+" is present on VMware Education" , txt_to_verify+ " found", "Click on "+txt_to_verify   );
			String retrieved_text = oASelFW.driver.findElement(By.xpath("//td[contains(text(),'Subscription:')]/following-sibling::td")).getText();
			System.out.println(retrieved_text);
			clickOnElement("//a[text()='mySubscriptions']");

			if(waitOnXpath(subscribeUser_xpath,timeOutSecs()))
			{

				String users_text = oASelFW.driver.findElement(By.xpath(subscribeUser_xpath)).getText();
				System.out.println(users_text);


				if(users_text.equalsIgnoreCase("0 of 1"))
				{
					sendReport("Verify number of user " , users_text+ " number of users found", "Fail"   );

				}
				else
				{
					sendReport("Verify number of user " , " number of users did not match", "Pass"   );
				}
			}
			else
			{
				sendReport("Verify number of user " , " users not found", "Pass");
			}

		}

		else
		{
			sendReport("Verify " +txt_to_verify+" is present on" + name  , txt_to_verify+ " did not found on "+ name, "Pass" );
		}
	}

	public void verifyBeginAndContinue(String courseName,String begin_xpath, String continue_xpath)
	{
		String courseName_xpath = "//a[text()='"+courseName+"']";

		if (waitOnXpath(courseName_xpath, timeOutSecs()))
		{
			sendReport("Verify Course name in myEnrollments", "Found Course name "+courseName, "Pass");

			if(waitOnXpath(begin_xpath, 15))
			{
				clickOnElement(begin_xpath);

				sendReport("Verify Begin Button", "Found Begin Button", "Pass");
			}

			else
			{
				clickOnElement(continue_xpath);
				sendReport("Verify Begin Button", "Found Continue Button", "Pass");
			}
		}

		else 
			sendReport("Verify Course name in myEnrollments", "Course not found ", "Fail");
	}
	/**
	 * @author arifuddin_mohd
	 * Description -- This method will Retrive the Result of the Bulk Upladed Users from vmware Education Page---> Navigating to Users Tab.
	 */

	public void verifiyingBuldUsersResults()
	{
		String result_Xpath = "//td[contains(text(),'User/')]/../../descendant::table/descendant::tr//td";
		String result = getText(result_Xpath);
		if(waitOnXpath(result_Xpath, timeOutSecs()))
		{
			System.out.println("The numbers of users are ::::::::"+result);
			sendReport("Verifiying the Result of Bulk Users ", "Successfully Verify the Numbers of Users Got Uploaded are "+result, "Pass");
		}
		else
		{
			sendReport("Verifiying the Result of Bulk Users ", "Unable to locate the Results Bulk Page ", "Fail");

		}

	}
}



















