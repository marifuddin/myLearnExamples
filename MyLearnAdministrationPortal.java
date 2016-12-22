package classes;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.poi.ss.formula.ptg.ConcatPtg;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.arsin.ArsinSeleniumAPI;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
//import com.opera.core.systems.internal.WatirUtils;
import com.thoughtworks.selenium.Wait;
import com.thoughtworks.selenium.webdriven.commands.Click;

public class MyLearnAdministrationPortal extends myutils {

	String errormsg = "ElementNotFoundException";
	String adminster_btn = "//a[contains(@title,'myLearn Administration Portal')]";
	String vmwedu_btn = "//a[contains(@title,'VMware Education')]";
	String vmware_edge = "//a[contains(@title,'VMware Edge portal')]/img";
	String menu_frame = "//frame[@name='left']";
	String main_frame = "//frame[@name='right']";
	String education_btn = "//a[contains(@title,'VMware Education')]/img";
	String RandDevXpath = "//a[contains(@title,'R&D Education')]/img";
	String searchXpath = "//input[@name='submutBtn']";

	public MyLearnAdministrationPortal(ArsinSeleniumAPI oASelFW) {
		super(oASelFW);
	}

	/**
	 * This method navigates the user to admin portal from the myLearn Home page
	 */
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

	public void navigate_RND_Education_Portal() {
		try {
			WebDriverWait wait = new WebDriverWait(oASelFW.driver,
					timeOutSecs());
			wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath(adminster_btn)));
			oASelFW.driver.findElement(
					By.xpath("//a[contains(text(),'R&D Education')]")).click();
			oASelFW.driver.switchTo().window(switchTabs().get("R&D Education"));
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

	public void select_UserType() {
		String usertypeXpath = "//select[@name='type']";

		selectByVisibleText(usertypeXpath, "Deleted");

	}

	public void type_myLearnId() {
		String classSearchUserIdxPath = "//input[@name='id']";
		setTextField(classSearchUserIdxPath, "123456");
	}

	/**
	 * Description - This method will switch the control to left frame(menu
	 * frame) in the my Learn Administration portal page
	 * 
	 */
	public void switch_to_menu_content() {
		WebDriverWait wait = new WebDriverWait(oASelFW.driver, timeOutSecs());
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By
				.xpath(menu_frame)));
		System.out.println("In Menu content");
	}

	/**
	 * Description - This method will switch the control to right frame(main
	 * menu content) in the my Learn Administration portal page
	 * 
	 */
	public void switch_to_main_content() {
		WebDriverWait wait = new WebDriverWait(oASelFW.driver, timeOutSecs());
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By
				.xpath(main_frame)));
		System.out.println("switched to main content");
	}

	public void menuServiceItems(String parentService)
			throws InterruptedException {
		String clickable = "//table/descendant::td/a[contains(text(),'"
				+ parentService + "')]";
		waitOnXpath(clickable, timeOutSecs());
		clickOnElement(clickable);
	}

	public void editDeliveryTypeAs(String deltype) {
		String delitype = "//li[text()='" + deltype
				+ "']/../../following-sibling::td[1]/a/img";
		if (waitOnXpath(delitype, 20)) {
			clickOnElement(delitype);
			sendReport("Verify if user is able to edit " + deltype,
					"Successfully  Edited " + deltype + "delivery type", "Pass");
		} else {
			sendReport("Verify if user is able to edit " + deltype,
					"Unable to Edit " + deltype + "delivery type", "Fail");
		}
	}

	public List<String> getSelectedDeliveryTypes() {
		List<String> selected_deltypes = new ArrayList();
		String deltype_holder = "//td[contains(text(),'Course Delivery')]/following-sibling::td//tbody";
		WebElement deltype = oASelFW.driver.findElement(By
				.xpath(deltype_holder));
		List<WebElement> eles = deltype.findElements(By.tagName("tr"));
		System.out.println("size is " + eles.size());
		for (WebElement ele : eles) {
			List<WebElement> rows = ele.findElements(By.tagName("td"));
			System.out.println("row size " + rows.size());
			for (WebElement row : rows) {
				String row_val = row.getText();
				selected_deltypes.add(row_val);
			}

		}
		return selected_deltypes;
	}

	public void addDeliveryType(String deltype) {
		String AddDel_link = "//td[contains(text(),'Course Delivery')]/following-sibling::td//a[text()='Add']";
		String Deltype_check = "//td[text()='"+deltype+"']/../td//input";
		String save_delType = "//input[@value='Save']";
		String added_delType = "//td[contains(text(),'Course Delivery')]/following-sibling::td//tbody//td[contains(text(),'"
				+ deltype + "')]";
		String Verify_DelType="//td[Contains(text(),'"+deltype+"')]";
		
		if(waitOnXpath(Verify_DelType, timeOutSecs()))
		{
			sendReport("Verify that Delivery Type "+deltype+" is already Added", deltype+" delivery type is already added", "Pass");
		}
		else{
		if (waitOnXpath(AddDel_link, timeOutSecs())) {
			clickOnElement(AddDel_link);
			sendReport(
					"Verify if user is able to select Add delivery type link",
					"Successfully selected Add delivery type link", "Pass");
			if (waitOnXpath(Deltype_check, timeOutSecs())) {
				clickOnElement(Deltype_check);
				if (waitOnXpath(save_delType, timeOutSecs())) {
					clickOnElement(save_delType);
					if (waitOnText(added_delType)) {
						sendReport("Verify if user is able to add " + deltype
								+ " delivery type", "User Successfully added "
								+ deltype + "delivery type", "Pass");
					} else {
						sendReport("Verify if user is able to add " + deltype
								+ " delivery type", deltype
								+ "delivery type not added", "Fail");
					}
				}
			} else {
				sendReport("verify if user is able to add the delivery type "
						+ deltype, "Delivery type " + deltype
						+ " is not displayed in the list", "Fail");
			}
		} else {
			sendReport(
					"Verify if user is able to select Add delivery type link",
					"Unable to select Add delivery type link", "Fail");
		}
		}
	}

	public void verifyDeleteDeliveryTypeFor(String container, String Deltype) {
		String delete_type = "//td[contains(text(),'Course Delivery')]/following-sibling::td//tbody//td[contains(text(),'"
				+ Deltype + "')]/a/img";
		if (waitOnXpath(delete_type, 20)) {
			clickOnElement(delete_type);
			if (waitOnText(delete_type) == false) {
				sendReport("verify if user is able to deleted the " + Deltype
						+ " in " + container,
						"Successfully deleted the delivery type " + Deltype
								+ " from " + container, "Pass");
			} else {
				sendReport("verify if user is able to deleted the " + Deltype
						+ " in " + container,
						"Unable to delete the delivery type " + Deltype
								+ " from " + container, "Fail");
			}
		}
	}

	public void verifyDeliveryTypeDisplayAs(String delType, String container) {
		boolean delF = false;
		List<String> delTypes = getSelectedDeliveryTypes();
		for (String del : delTypes) {
			if (del.equalsIgnoreCase(delType)) {
				sendReport("verify if delivery type " + delType
						+ " is displayed for " + container,
						"Successfully verified the display of the delivery type "
								+ delType + " in " + container, "Pass");
				delF = true;
				break;
			} else {
				delF = false;
				System.out.println("del type is not pre selected");
			}
		}

		System.out.println("delf" + delF);

		if (!delF) {
			System.out.println("In addd del type");
			addDeliveryType(delType);
		}

	}

	public void verifyingExpiresForPlanName() {
		String expireXpath = "//tr[@valign='top']/descendant::td[6]";
		String Date = getText(expireXpath);

		if (waitOnXpath(expireXpath, timeOutSecs())) {
			sendReport("Verifiying the Expire Date ",
					"Successfully Verifiyed the Expired Date " + Date, "Pass");
		} else {
			sendReport("Verifiyed the Expired Date ",
					"Unable to Verify the Displayed Expired Date ", "Fail");
		}
	}

	public void clickHead_Subservice(String parentService, String subService)
			throws InterruptedException {

		String clickable = "//table[@id='leftNavTable']/descendant::tr/descendant::a";
		waitOnXpath(clickable, timeOutSecs());
		String value = "";
		String cnt = oASelFW.effecta("getXpathCount",
				"//table[@id='leftNavTable']");
		int count = Integer.parseInt(cnt);
		for (int i = 1; i <= count; i++) {
			value = oASelFW.driver.findElement(
					By.xpath("//table[@id='leftNavTable']/descendant::tr[" + i
							+ "]/td[2]/a")).getText();
			System.out.println(value + "*******" + parentService);
			if (value.trim().equals(parentService.trim())) {
				System.out.println(subService + "++++++++++++++++++++++++++"
						+ i);
				oASelFW.effecta("clickAndWait",
						"//table[@id='leftNavTable']/descendant::tr[" + i
								+ "]/td[2]/a", parentService);
				Thread.sleep(5000);
				break;
			} else {

			}
		}
		String clicking = "//table[@id='leftNavTable']/descendant::tr/descendant::a";
		waitOnXpath(clicking, timeOutSecs());
		String dispvalue = "";
		String cnt1 = oASelFW.effecta("getXpathCount",
				"//a[contains(text(),'Manage')]/../descendant::div");
		int count1 = Integer.parseInt(cnt1);
		System.out.println("Count value:" + cnt1);

		for (int i = 1; i <= count1; i++) {
			dispvalue = oASelFW.driver
					.findElement(
							By.xpath("//a[contains(text(),'Manage')]/../descendant::div["
									+ i + "]/a")).getText();
			System.out.println("+++++++++++++++++++" + dispvalue.trim()
					+ "+++++++++++++++");
			System.out.println("+++++++++++++++++++" + subService.trim()
					+ "+++++++++++++++");
			if (dispvalue.trim().equalsIgnoreCase(subService.trim())) {
				System.out.println("In IF");
				System.out.println(value
						+ "---------------------------------------"
						+ subService);
				oASelFW.effecta("clickAndWait",
						"//a[contains(text(),'Manage')]/../descendant::div["
								+ i + "]/a", subService);
				Thread.sleep(2000);
				break;
			} else {

			}
		}

	}

	public void switch_to_default_content() {
		oASelFW.driver.switchTo().defaultContent();
	}

	public void manage_content() {
		String manage_link = "//a[text()='Manage']";
		oASelFW.driver.findElement(By.xpath(manage_link)).click();
		oASelFW.effecta("sendReport", "Clicking on Manage Link",
				"Successfully Clicked on Manage Link", "Pass");
	}

	public void order_content() {
		String Order_link = "//a[text()='Order']";
		oASelFW.driver.findElement(By.xpath(Order_link)).click();
		oASelFW.effecta("sendReport", "Clicking on Order Link",
				"Successfully Clicked on Order Link", "Pass");
	}

	public void getTestSurverysAndLinks() {
		String link_holder = "//a[text()='Tests, Surveys & Polls']";
		if (waitOnXpath(link_holder, timeOutSecs())) {
			clickOnElement(link_holder);
			sendReport("Click on Tests, Surveys & Polls link ",
					"Clicked on Tests, Surveys & Polls", "Pass");
		} else {
			System.out
					.println("Test Surverys and Links option is not displayed");
			sendReport("Click on Tests, Surveys & Polls link ",
					"Tests, Surveys & Polls link is not found", "fail");
		}
	}

	public void getPortals() {
		String portal_link = "//a[text()='Portals']";
		if (waitOnXpath(portal_link, timeOutSecs())) {
			clickOnElement(portal_link);
			sendReport(
					"verify if user is able to select portal link from menu option",
					"Successfully selected the portal link from menu options",
					"Pass");
		} else {
			sendReport(
					"verify if user is able to select portal link from menu option",
					"User unbale to select portal link from menu options",
					"Fail");
		}
	}

	public void manageClasses() {
		String classes_link = "//a[text()='Classes']";
		if (waitOnXpath(classes_link, timeOutSecs())) {
			clickOnElement(classes_link);
			sendReport(
					"verify if user is able to select portal link from menu option",
					"Successfully selected the Classes link from menu options",
					"Pass");
		} else {
			sendReport(
					"verify if user is able to select portal link from menu option",
					"User unbale to select Classes link from menu options",
					"Fail");
		}
	}

	public void getLeftMenuLink(String value) {
		try {
			String reportsXpath = "//table/descendant::a[contains(text(),'"
					+ value + "')]";
			waitOnXpath(reportsXpath, timeOutSecs());
			clickOnElement(reportsXpath);
			sendReport("Clicking on the Link ",
					"Successfully Clicked on the Link " + value, "Pass");
		} catch (NoSuchElementException e) {
			sendReport("Expected Element on the WebPage",
					"Unable to Click the Expected element " + value
							+ " from WebPage ", "Fail");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getSubmenuFromLeftPane(String menuName) {
		String menu_link = "//table/descendant::a[text()='Reports']";
		if (waitOnXpath(menu_link, timeOutSecs())) {
			clickOnElement(menu_link);
			sendReport("Verify if user is able to select the submenu as "
					+ menuName,
					"Successfully selected the menu as " + menuName, "Pass");
		} else {
			sendReport("Verify if user is able to select the submenu as "
					+ menuName, "Unable to select the menu as " + menuName,
					"Fail");
		}

	}

	public void click_Find_Button() {
		String clicking_FindXpath = "//input[@value='Find']";
		clickOnElement(clicking_FindXpath);
		oASelFW.effecta("sendReport", "Clicking on Find Button",
				"Successfully Clicked on Find Buttton", "Pass");
	}

	public void click_Properties_Link() {
		String clicking_Properties = "//td[text()='1.']/following-sibling::td[4]/a[1]";
		if (waitOnXpath(clicking_Properties, 10)) {
			clickOnElement(clicking_Properties);
		}
	}

	public void clickOnClassProp() {
		clickOnURL("//a[@title='View Class Properties.']", "Class Properties");

	}

	public void admin_LoggingOut() {
		switch_to_menu_content();
		String adminlogout = "//table[@id='leftNavTable']//a[contains(text(),'Logout')]";
		clickOnElement(adminlogout);
	}

	public void click_Impersonate_Link() {
		String clicking_Impersonate = "//a[contains(text(),'Impersonate')]";
		clickOnElement(clicking_Impersonate);
		oASelFW.effecta("sendReport", "Clicking on Impersonate Link",
				"Successfully Impersonate the User", "Pass");
	}

	public void click_FunctionChoice(String choice) {
		String functionChoice = "//a[contains(text(),'" + choice + "')]";
		clickOnElement(functionChoice);
		oASelFW.effecta("sendReport", "Clicking on the Displayed Link",
				"Successfully clicked on the Displayed Link" + choice, "Pass");
	}

	public void click_LiveOnline_Link() {
		String liveOnlinexPath = "//a[contains(text(),'Live-Online')]";
		oASelFW.driver.findElement(By.xpath(liveOnlinexPath)).click();

	}

	public void click_NewClass_Link() {
		String newClassxPath = "//a[contains(text(),'New Class')]";
		waitOnXpath(newClassxPath, timeOutSecs());
		clickOnElement(newClassxPath);

	}

	public void click_eports_Link() {
		String eport_link = "//a[contains(text(),'Reports')]";
		WebDriverWait wait = new WebDriverWait(oASelFW.driver, timeOutSecs());
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath(eport_link)));
		oASelFW.driver.findElement(By.xpath(eport_link)).click();
		oASelFW.effecta("sendReport",
				"Verify the Navigation to Manage->Reports",
				"Successfully naviagated to Reports catalog", "Pass");
	}

	public void selectingeportsLinks(String report) {
		String reportsXpath = "//table/descendant::a[contains(text(),'"
				+ report + "')]";
		waitOnXpath(reportsXpath, timeOutSecs());
		clickOnElement(reportsXpath);
		sendReport("Selecting the Evaluation Class",
				"Successfully Clicked the Evaluation Calss: " + report, "Pass");
	}

	public void fillingEnrollmentForm(String startDate, String endDate) {
		String startDateXpath = "//input[@id='fixedText1']";
		String endDateXpath = "//input[@id='fixedText2']";
		setTextField(startDateXpath, startDate);
		sendReport("Setting the Starting Date",
				"Successfully Set the Starting Date:" + startDate, "Pass");
		setTextField(endDateXpath, endDate);
		sendReport("Setting the Ending Date",
				"Successfully Set the Ending Date:" + endDate, "Pass");
	}

	public void clickingShowButton() {
		String showXpath = "//input[@name='mL_method']";
		waitOnXpath(showXpath, timeOutSecs());
		clickOnElement(showXpath);
		sendReport("Clicking on Show Button ",
				"Successfully Clicked on Show Button ", "Pass");
	}

	public void click_EvaluationSummaryByClass_Link() {
		String evltinSmmryByClasxPath = "//a[contains(text(),'Evaluation Summary by Class')]";
		clickOnElement(evltinSmmryByClasxPath);
	}

	public void select_College_Dropdonw() {
		String college = "//select[@name='collegeID']";
		selectByVisibleText(college, "VMware Customer Education");
	}

	public void select_College_Drop(String college, String eduForm,
			String gDate, String lDate) {
		String collegeXpath = "//select[@name='collegeID']";
		String eduFormXpath = "//select[@name='evalID']";
		String gdateXpath = "//input[@name='sDate']";
		String ldateXpath = "//input[@id='eDate']";
		waitOnXpath(collegeXpath, timeOutSecs());
		selectByVisibleText(collegeXpath, college);
		sendReport("Selecting the college",
				"Successfully selected the College ", "Pass");
		selectByVisibleText(eduFormXpath, eduForm);
		sendReport("Selecting the EDU ClassRoom Evaluation",
				"Successfully selected the EDU ClassRoom Evaluation", "Pass");
		clean_ExistingData(gdateXpath);
		setTextField(gdateXpath, gDate);
		clean_ExistingData(ldateXpath);
		setTextField(ldateXpath, lDate);

	}

	public void verifiyingErroMessage() {
		String mesgXpath = "//td[contains(text(),'Too many records. Try narrowing by a date range.')]";
		String mesgText = getText(mesgXpath);
		System.out.println(mesgText);
		if (mesgText
				.contains("Too many records. Try narrowing by a date range.")) {
			sendReport("Verifiying the given Message ",
					"Successfully Verifiyed the Given Message: " + mesgText,
					"Pass");
		} else {
			sendReport("Verifiying the Given Message ",
					"Unable to Verify the Given Message: ", "Fail");
		}
	}

	public void configureRecordFilter(String lookup_type, String mode_name,
			String lockupData) {
		String mylearidXpath = "//select[@name='lookup_type']";
		String showXpath = "//select[@name='mode_name']";
		String lockupfield = "//input[@name='lookup_value']";

		waitOnXpath(mylearidXpath, timeOutSecs());
		selectByVisibleText(mylearidXpath, lookup_type);

		waitOnXpath(showXpath, timeOutSecs());
		selectByVisibleText(showXpath, mode_name);

		waitOnXpath(lockupfield, timeOutSecs());
		setTextField(lockupfield, lockupData);

	}

	public void searchButton() {
		waitOnXpath(searchXpath, timeOutSecs());
		clickOnElement(searchXpath);
		sendReport("Clicking on the Search Button",
				"Successfully Clicked on the Search Button", "Pass");
	}

	public void deepLinkUserID() {
		String deeplink = "//span[@class='objectID']//a";
		waitOnXpath(deeplink, timeOutSecs());
		clickOnElement(deeplink);
		sendReport("Clicking on the Deep Link ID",
				"Successfully Clicked on the Deep Link ID", "Pass");
	}

	public void selectingCheckBox() {
		String checkBox = "//input[@type='checkbox']";
		String mesgXpath = "//td[contains(text(),'User programs sync process complete!')]";
		if (mesgXpath.contains("User programs sync process complete!")) {
			oASelFW.driver.navigate().back();
		}
		oASelFW.driver.navigate().back();
		waitOnXpath(checkBox, timeOutSecs());
		clickOnElement(checkBox);
		sendReport("Checking the check Box",
				"Successfully Checked the Check Box", "Pass");
		searchButton();

	}

	public void deepPlanLink() {
		String planXpath = "//td[contains(text(),'Partner Central ID:')]/following-sibling::td//a";
		waitOnXpath(planXpath, timeOutSecs());
		clickOnElement(planXpath);
		sendReport("Clicking on the Plans Link",
				"Successfully Clicked on the Plans Link", "Pass");

	}

	public void select_Evaluation_Dropdonw() {
		String evaluation = "//select[@id='evalID']";
		selectByVisibleText(evaluation, "Please select one");

	}

	public void type_ClassStartDateRange_field() {
		String StartDt = "//input[@id='sDate']";
		String EndDt = "//input[@id='eDate']";
		setTextField(StartDt, "04/01/2015");
		setTextField(EndDt, "06/08/2015");
	}

	public void click_Generate_Button() {
		String generatexPath = "//input[@name='generate']";
		clickOnElement(generatexPath);
		sendReport("Clicking on Generate Report Button",
				"Successfully Clicked on Generate Button", "Pass");

	}

	public void verifyResult() {
		String error_holder = "//p/strong[text()='Internal Error']";
		if (oASelFW.driver.findElement(By.xpath(error_holder)).isDisplayed()
				|| oASelFW.driver.findElement(
						By.xpath("//td[text()='No result found']"))
						.isDisplayed()) {
			sendReport("Verify if the search fetches the expected excel sheet",
					"Error displayed after search is performed", "Fail");
		} else {
			sendReport("verify the excel sheet values value after search",
					"excel sheet values are displayed as expected", "Pass");
		}
	}

	public void click_ManageClasses_Link() {
		String manage_Classes = "//a[contains(text(),'Classes')]";
		oASelFW.driver.findElement(By.xpath(manage_Classes)).click();
	}

	public void filling_ClassForm() {
		String class_type = "//td[contains(text(),'Type:')]/../descendant::select";
		String college_type = "//td[contains(text(),'College:')]/../descendant::select";
		String host_id = "//input[@name='id_host']";
		String start_Date = "//input[@id='fixedText1']";
		String findButton = "//input[@type='button']/following-sibling::input";

		selectByVisibleText(class_type, "Lab");
		selectByVisibleText(college_type, "VMware Customer Education");
		setTextField(host_id, "889");
		setTextField(start_Date, "01/01/2015");
		oASelFW.driver.findElement(By.xpath(findButton)).click();
	}

	public void collegeCategories(String type, String choices) {

		String proCoursesXpath = "//td[contains(text(),'" + type
				+ "')]/following-sibling::td/a[contains(text(),'" + choices
				+ "')]";
		waitOnXpath(proCoursesXpath, timeOutSecs());
		clickOnElement(proCoursesXpath);

	}

	public void click_ClassRoomLink() {
		String class_Room = "//a[contains(text(),'Classroom')]";
		oASelFW.driver.findElement(By.xpath(class_Room)).click();
	}

	public void verifying_ErorSerchMesge_Field() throws IOException {
		String errorMesgXpath = "//p/strong";
		boolean status = waitOnXpath(errorMesgXpath, timeOutSecs());
		if (status == true
				&& getText(errorMesgXpath).contains(
						loadprops("verifyngErorSerchMsg"))) {

			sendReport("Verifying the Failed Message!!",
					"Successfully Verified Failed Message!!", "Pass");
		} else {
			sendReport("Verify the Given content Text",
					"Unable to verify the given content Text", "Fail");
		}
	}

	public void type_ClassID_Field() {
		String class_Id = "//input[@name='id_course']";
		setTextField(class_Id, "229260");
	}

	public void click_CertificateName_Link() {
		String certificate_Name = "//tr[3]/td[3]/a";
		clickOnElement(certificate_Name);
	}

	public void click_Evaluated_Link() {
		String evaluated_Link = "//td[text()='Evaluated:']/following::a";
		clickOnElement(evaluated_Link);
	}

	public void get_colleges_catalog() {
		String colleges_link = "//a[text()='Colleges']";
		WebDriverWait wait = new WebDriverWait(oASelFW.driver, timeOutSecs());
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath(colleges_link)));
		oASelFW.driver.findElement(By.xpath(colleges_link)).click();
		oASelFW.effecta("sendReport",
				"Verify the Navigation to Manage->Colleges",
				"Successfully naviagated to Colleges catalog", "Pass");
	}

	public void get_hosts_catalog(String value) {
		String hosts_link = "//td[@class='pagetexttitle']/../following-sibling::tr/descendant::a[contains(text(),'"
				+ value + "')]";
		WebDriverWait wait = new WebDriverWait(oASelFW.driver, timeOutSecs());
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(hosts_link)));
		oASelFW.driver.findElement(By.xpath(hosts_link)).click();
		oASelFW.effecta("sendReport", "Navigating to College-> " + value + "",
				"Successfully naviagated to " + value + " catalog", "Pass");
	}

	public void selct_menuHosts() {
		String menu_Hosts = "//a[text()='Hosts']";
		if (waitOnXpath(menu_Hosts, timeOutSecs())) {
			clickOnElement(menu_Hosts);
			sendReport(
					"verify if user is able to select Hosts from Manage content",
					"Successfully selected the Hosts from Manage", "Pass");
		} else {
			sendReport(
					"verify if user is able to select Hosts from Manage content",
					"Unable to select host from Manage content", "Fail");
		}

	}

	public void configure_content() {
		String configure_link = "//a[text()='Configure']";
		System.out.println("Before configure link ");
		oASelFW.driver.findElement(By.xpath(configure_link)).click();
		System.out.println("After configure link");
		oASelFW.effecta("sendReport", "Clicking on Configure Link",
				"Successfully Clicked on Configure Link", "Pass");
	}

	public void parentAndSubService(String parent, String sub) {
		String parentXpath = "//a[contains(text(),'" + parent
				+ "')]/..//div//a[contains(text(),'" + sub + "')]";

		if (waitOnXpath(parentXpath, timeOutSecs())) {
			clickOnElement(parentXpath);
			sendReport("Clicking on Sub Service: " + sub,
					"Successfully Clicked on Sub Service: " + sub, "Pass");
		} else {
			sendReport("Trying to Click on Sub Service: " + sub,
					"Unable to Find Sub Service Link: " + sub, "Fail");
		}
	}

	public void suscriptionTrainingPlanEdit(String name) {
		String subEditingXpath = "//td[contains(text(),'" + name
				+ "')]/following-sibling::td/descendant::img";
		clickOnElement(subEditingXpath);
		sendReport("Selecting Training Plan Edit option",
				"Successfully selected Training Plan option as " + name, "Pass");

	}

	public void viewInPortalStatus() {
		String viewPortalXpath = "//td[contains(text(),'View in Portals')]/following-sibling::td";
		String status = getText(viewPortalXpath);
		System.out.println("view portal Status:" + status);
		Boolean stat = waitOnXpath(viewPortalXpath, timeOutSecs());
		if (stat == true) {
			sendReport(
					"Verifiying View In Portal Status: ",
					"Successfully Verified view in portal status as: " + status,
					"Pass");
		} else {
			sendReport("Verifiying View In Portal Status: ",
					"Found View In Portal Status as: Yes", "Fail");
		}
	}

	public void selectingDeliveryTypes(String deliverytype) {
		String deliveryXpath = "//a[contains(text(),'" + deliverytype + "')]";
		waitOnXpath(deliveryXpath, timeOutSecs());
		clickOnElement(deliveryXpath);
	}

	public void liveOnlineFormFilling(String city, String trainingCenter,
			String room) {
		String cityXpath = "//select[@name='city']/option[contains(text(),'"
				+ city + "')]";
		String trainingCenterXpath = "//select[@name='loc']";
		String roomXpath = "//select[@name='room']";
		waitOnXpath(cityXpath, timeOutSecs());
		selectByVisibleText(cityXpath, city);
		waitOnXpath(trainingCenterXpath, timeOutSecs());
		selectByVisibleText(trainingCenterXpath, trainingCenter);
		waitOnXpath(roomXpath, timeOutSecs());
		selectByVisibleText(roomXpath, room);
		userComformationMesg();
	}

	public void newLiveOnlineForm(String lang, String host, String online) {

		String langXpath = "//select[@name='id_language']";
		String hostServXpath = "//select[@name='id_host']";
		String onlineCapXpath = "//input[@name='crs_capacity']";
		waitOnXpath(langXpath, timeOutSecs());
		selectByVisibleText(langXpath, lang);
		waitOnXpath(hostServXpath, timeOutSecs());
		selectByVisibleText(hostServXpath, host);
		waitOnXpath(onlineCapXpath, timeOutSecs());
		setTextField(onlineCapXpath, online);
	}

	public void newLiveOnlineFormTime() {
		String startDateXpath = "//input[@name='date_start']";
		String h_time = "//select[@name='hour']";
		String m_time = "//select[@name='minute']";
		String ampmXpath = "//select[@name='e_ampm']";
		String endDateXpath = "//input[@name='date_end']";
		String eh_time = "//select[@name='e_hour']";
		String em_time = "//select[@name='e_minute']";
		String e_ampmXpath = "//select[@name='e_ampm']";
		String timeZoneXpath = "//input[@class='ui-autocomplete-input']";

		waitOnXpath(startDateXpath, timeOutSecs());
		setTextField(startDateXpath, "06/19/2015");
		selectByVisibleText(h_time, "10");
		selectByVisibleText(m_time, "30");
		selectByVisibleText(ampmXpath, "PM");
		waitOnXpath(endDateXpath, timeOutSecs());
		setTextField(endDateXpath, "06/06/2015");
		selectByVisibleText(eh_time, "04");
		selectByVisibleText(em_time, "15");
		selectByVisibleText(e_ampmXpath, "PM");
		waitOnXpath(timeZoneXpath, timeOutSecs());
		setTextField(timeZoneXpath, "America/Bahia");
		String costXpath = "//select[@name='rate']";
		String instructorXpath = "//select[@name='instructors']";
		String curriculumXpath = "";
		waitOnXpath(costXpath, timeOutSecs());
		selectByVisibleText(costXpath, "$ 1500 (USD)");
		selectByVisibleText(instructorXpath, "Aalders, Andre");
	}

	public void getting_started() {
		String getting_started_link = "//div[@id='mAdminItems']/div";
		if (waitOnXpath(getting_started_link, timeOutSecs())) {
			clickOnElement(getting_started_link);
			sendReport(
					"Navigate to getting started page from configure content",
					"Successfully navigated to Configure->Getting started page",
					"Pass");
		} else {
			sendReport(
					"Navigate to getting started page from configure content",
					"Unable to navigate to Configure->Getting started page",
					"Fail");
		}
	}

	public void configure_users() {
		String users_link = "//a[text()='Domains']/../following-sibling::div/a";
		waitOnXpath(users_link, timeOutSecs());
		clickOnElement(users_link);
		sendReport("Verify the Navigation to Configure->Users",
				"Successfully Navigated to Configure->Users page", "Pass");
	}

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

	public void switchToDefaultTitle() {
		switchTabs().get("default_title");
	}

	public void navigateBackToAdmin() {
		switchTabs().get("myLearn");
	}

	public void openLinkInNewTab(String link) throws InterruptedException {
		WebElement body = oASelFW.driver.findElement(By.tagName("body"));
		body.sendKeys(Keys.CONTROL + "t");
		switchTabs().get("New Tab");
		oASelFW.driver.get(link);
	}

	public void training_Plans() {
		String trainingXpath = "//a[text()='Training Plans']";
		if (waitOnXpath(trainingXpath, timeOutSecs())) {
			clickOnElement(trainingXpath);
			oASelFW.effecta("sendReport",
					"Verify the Navigation to Manage->trainingPlans",
					"Successfully naviagated to trainingPlans catalog", "Pass");
		} else {
			oASelFW.effecta("sendReport",
					"Verify the Navigation to Manage->trainingPlans",
					"Unable to Navigate to trainingPlans catalog", "Fail");
		}

	}

	/*public void totalRegisteredLink(String link) {
		String totalLinkXpath = "//td[contains(text(),'Total Registered')]/following-sibling::td//a";
		String completedXpath = "//td[contains(text(),'Completed:')]/following-sibling::td//a";
		waitOnXpath(totalLinkXpath, timeOutSecs());
		String signInSheetXpath = "//a[contains(text(),'Sign-In Sheet')]";
		String inviteXpath = "//a[contains(text(),'Invite')]";
		String addXpath = "//a[contains(text(),'Add')]";
		String holdXpath = "//a[contains(text(),'Hold')]";
		switch (link) {
		case "Sign-In Sheet":
			clickOnElement(signInSheetXpath);
			sendReport("Clicking on Link", "Successfully clicked on Link "
					+ link, "Pass");
			break;
		case "Invite":
			clickOnElement(inviteXpath);
			sendReport("Clicking on Link", "Successfully clicked on Link "
					+ link, "Pass");
			break;
		case "Hold":
			clickOnElement(holdXpath);
			sendReport("Clicking on Link", "Successfully clicked on Link "
					+ link, "Pass");
			break;
		case "Add":
			clickOnElement(addXpath);
			sendReport("Clicking on Link", "Successfully clicked on Link "
					+ link, "Pass");
			break;
		case "Total Registered:":
			clickOnElement(totalLinkXpath);
			sendReport("Clicking on Link", "Successfully clicked on Link "
					+ link, "Pass");
			break;
		case "":
			clickOnElement(completedXpath);
			sendReport("Clicking on Link", "Successfully clicked on Link "
					+ link, "Pass");
			break;
		default:
			sendReport("Clicking on Link", "Unable to find The Link " + link,
					"Fail");
			break;
		}
		System.out.println("after switch");
	}*/

	/*public void registrantCriteriaLink(String type, String value) {
		String registerXpath = "//a[contains(text(),'Registrant Criteria')]";
		clickOnElement(registerXpath);
		String mylearnXpath = "//input[@type='text']";
		String submitXpath = "//input[@name='submit']";
		String emailXpath = "//input[@title='Enter full or partial email']";

		switch (type) {
		case "MyLearn ID:":
			waitOnXpath(mylearnXpath, timeOutSecs());
			setTextField(mylearnXpath, value);
			break;

		case "Email:":
			waitOnXpath(emailXpath, timeOutSecs());
			setTextField(emailXpath, value);
			break;

		default:
			break;
		}
		clickOnElement(submitXpath);
	}*/

	public void registerDataValidation() {
		Alert alert = oASelFW.driver.switchTo().alert();
		String message = alert.getText();
		alert.accept();
		System.out.println(message);
		sendReport("Verifiying the Alert Message ",
				"Successfully Verifiyed the Allert Message: " + message, "Pass");
		sendReport("Clicking on Alert ", "Successfully Clicked OK Button",
				"Pass");
	}

	/*public void runChronJobForPSOandTrainingRemainderEmails(String instance)
			throws IOException, InterruptedException {
		switch (instance) {
		case "ci":
			openLinkInNewTab(loadprops("ci-chron"));
			sendReport("Verify is the user is able to run the chron job in "
					+ instance + " instance", "Successfully ran the chron job "
					+ loadprops("ci-chron"), "Pass");
			break;
		case "stg":
			openLinkInNewTab("stg-chron");
			sendReport("Verify is the user is able to run the chron job in "
					+ instance + " instance", "Successfully ran the chron job "
					+ loadprops("ci-chron"), "Pass");
		case "qa":
			openLinkInNewTab("qa-chron");
			sendReport("Verify is the user is able to run the chron job in "
					+ instance + " instance", "Successfully ran the chron job "
					+ loadprops("ci-chron"), "Pass");
		}
	}*/

	public void navigate_vmwEducation_portal() {
		if (waitOnXpath(vmwedu_btn, timeOutSecs())) {
			String srch_box = "//button[@type='submit']";
			clickOnElement(vmwedu_btn);
			oASelFW.driver.switchTo().window(
					switchTabs().get("VMware Education"));
			System.out.println(switchTabs().size());
			for (int i = 0; i < switchTabs().size(); i++) {

				System.out.println(switchTabs().get(i));
			}
			if (waitOnXpath(srch_box, 20)) {
				System.out.println("switched to edu portal");
				clickOnElement(srch_box);
				System.out
						.println("*********************************In srch_box *****************************");
			} else {
				System.out.println("Fail");
			}
			sendReport(
					"Verify if user is able to navigate to vmware education portal",
					"Successfully navigated for VMware education portal",
					"Pass");
		} else {
			sendReport(
					"Verify if user is able to navigate to vmware education portal",
					"Unable to navigate to VMware education portal", "Fail");
		}
	}

	public void navigate_VmwareEdge_Portal() {
		if (waitOnXpath(vmware_edge, timeOutSecs())) {
			System.out.println("In vmware edge");
			String srch_box = "//input[@class='searchBarButton']";
			clickOnElement(vmware_edge);
			oASelFW.driver.switchTo().window(
					switchTabs().get("VMware Education"));
			if (waitOnXpath(srch_box, timeOutSecs())) {
				clickOnElement(srch_box);
				sendReport(
						"Verify if user is navigated to VMware Edge portal",
						"Successfully verified user navigated to VMware EDGE portal ",
						"Pass");
			} else {
				sendReport("Verify if user is navigated to VMware Edge portal",
						"Vmware edge portal not displayed", "Fail");
			}
		}
	}

	public void srch_box() {
		String srch_box = "//input[@class='searchBarButton']";
		if (waitOnXpath(srch_box, timeOutSecs())) {
			clickOnElement(srch_box);
		}
	}

	public void verifyCourseCatalogFilter() {
		String course_cat_filter = "//div[text()='Course Catalog Filters']";
		if (waitOnText(course_cat_filter)) {
			sendReport(
					"Verify if user is able to view the course catalog filters",
					"Course catalog filters are successfully displayed for the logged in User",
					"Pass");
		} else {
			sendReport(
					"Verify if user is able to view the course catalog filters",
					"Course catalog filters are not displayed for the logged in User",
					"Fail");
		}
	}

	public void nav_Education_Portal() {

		if (waitOnXpath(education_btn, timeOutSecs())) {
			sendReport("Clicking on the Education Portal Link",
					"Successfully Clicked on the Education Portal Link", "Pass");
			clickOnElement(education_btn);
		} else {
			sendReport("Clicking on the Education Portal Link",
					"Unable to Find the Education Portal Link", "Fail");
		}

	}

	public void naviagate_RandDEdu_portal() {
		try {
			WebDriverWait wait = new WebDriverWait(oASelFW.driver,
					timeOutSecs());
			wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath(RandDevXpath)));
			oASelFW.driver.findElement(By.xpath(RandDevXpath)).click();
			oASelFW.driver.switchTo().window(switchTabs().get("R&D Education"));
			String title = oASelFW.driver.getCurrentUrl();
			System.out.println(title);
			oASelFW.effecta("sendReport",
					"Verify if the user is navigated to R&D Education portal",
					"User successfully navigated to R&D Education", "Pass");
		} catch (Exception e) {
			if (e.equals(errormsg)) {
				oASelFW.effecta("sendReport", "WebElement verification",
						"Element not found at " + education_btn, "Fail");
			}
		}
	}

	/*public void userSearchBasics(String type, String option) {
		String findXpath = "//input[@name='test']";
		String userTypeXpath = "//select[@name='type']";
		String mylearnIdXpath = "//input[@name='id']";
		switch (type) {
		case "User Type:":
			selectByVisibleText(userTypeXpath, option);
			sendReport("Search by user Id", "User id given successfully",
					"pass");
			break;

		case "myLearn ID:":
			setTextField(mylearnIdXpath, option);

			sendReport("Give my learn Id", "mylearn Id given successfully",
					"pass");

			break;
		default:
			waitOnXpath(findXpath, timeOutSecs());
			clickOnElement(findXpath);
			break;
		}
		clickOnElement(findXpath);
	}*/

	public void userSearchEmailID(String emailID) {
		String emailXpath = "//input[@name='email']";
		setTextField(emailXpath, emailID);
	}

	/*public void userSearchSelect(String type, String filterData) {

		String administrationXpath = "//select[@name='admin_psg']";
		switch (type) {
		case "admin_psg":
			if (waitOnXpath(administrationXpath, timeOutSecs())) {
				selectByVisibleText(administrationXpath, filterData);
				sendReport("selecting text field as: " + filterData,
						"Successfully selected the value as " + filterData,
						"Pass");
			} else {
				sendReport("selecting text field as " + filterData,
						"unable to select the value as " + filterData, "Fail");
			}
			break;
		}
		System.out.println("after switch");
	}*/

	public void findButton() {
		String findXpath = "//input[@name='test']";
		clickOnElement(findXpath);
	}

	public void mylearnUsersSelecting(String userName, String option) {
		String selectingUserXpath = "//table/descendant::td[contains(text(),'"
				+ userName + "')]/../td/a[contains(text(),'" + option + "')]";
		String checkBoxXpath = "//table/descendant::td[contains(text(),'"
				+ userName + "')]/../td/input";
		sendReport("selecting the User Name ",
				"Successfully Selected the User: " + userName, "Pass");
		try {
			if (option.equalsIgnoreCase("properties")) {
				clickOnElement(checkBoxXpath);
				clickOnElement(selectingUserXpath);
				sendReport("clicking on User " + option,
						"Successfully clicked on User " + option, "Pass");
			}
		} catch (ElementNotFoundException e) {
			sendReport("clicking on User " + option, "Unable to click on User "
					+ option, "Fail");
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void domainUsersLinks(String link) {
		String linkXpath = "//td[@class='bodytext']/descendant::li/a[contains(text(),'"
				+ link + "')]";
		Boolean status = waitOnXpath(linkXpath, timeOutSecs());
		sendReport("Verifying the Link in Getting Started Page ",
				"Successfully Verifiyed the Displayed Link " + link + " ",
				"Pass");
		if (status == true) {
			clickOnElement(linkXpath);
			sendReport("Clicking on the Link ", "Successfully clicked on "
					+ link, "Pass");
		} else {
			sendReport("Clicking on the Link ",
					"Unable to find the Link on Displayed Page ", "Fail");
		}
		System.out.println("Success cliked");

	}

	public void selectingCourse(String option) {
		String selectingUserXpath = "//table/descendant::td/a[contains(text(),'"
				+ option + "')]";
		String checkBoxXpath = "//table/descendant::td/a[contains(text(),'"
				+ option + "')]/../../td/input";

		try {
			if (option.equalsIgnoreCase("Properties")) {
				clickOnElement(checkBoxXpath);
				clickOnElement(selectingUserXpath);
				sendReport("clicking on User : " + option,
						"Successfully clicked on User :" + option, "Pass");
			} else if (option.equalsIgnoreCase("New Payment")) {
				clickOnElement(checkBoxXpath);
				clickOnElement(selectingUserXpath);
				sendReport("clicking on User : " + option,
						"Successfully clicked on User :" + option, "Pass");
			}

		} catch (ElementNotFoundException e) {
			sendReport("clicking on User : " + option,
					"Unable to click on User : " + option, "Fail");
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public String getCompanyCategory(String option) {
		String opt_properties = null;
		try {
			WebDriverWait wait = new WebDriverWait(oASelFW.driver,
					timeOutSecs());

			int randomNumber = (int) ((Math.random()) * 100);
			System.out.println("The Random Number is: " + randomNumber);
			for (;;) {
				String next_Nav = "//a[contains(text(),'Next')]";

				opt_properties = "//td[text()='" + randomNumber
						+ ".']/following-sibling::td[4]/a[contains(text(),'"
						+ option + "')]";

				if (Boolean
						.parseBoolean(oASelFW
								.effecta(
										"isElementPresent",
										"//td[text()='"
												+ randomNumber
												+ ".']/following-sibling::td[4]/a[contains(text(),'"
												+ option + "')]"))) {
					if (option.equalsIgnoreCase("Properties")) {
						wait.until(ExpectedConditions.elementToBeClickable(By
								.xpath(opt_properties)));
						oASelFW.driver.findElement(By.xpath(opt_properties))
								.click();
						oASelFW.effecta("sendReport",
								"Verifiying the Displayed company category",
								"Successfully clicked on " + option
										+ " from company category", "Pass");
						break;
					}
				} else {
					clickOnElement(next_Nav);
					Thread.sleep(3000);
				}
			}
		} catch (ElementNotFoundException e) {
			oASelFW.effecta("sendReport",
					"Verifiying the Displayed company category",
					"Unable to select the Displayed Company " + option + " ",
					"Fail");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return opt_properties;
	}

	public String retriveUserIDFromPg() {
		String id_Xpath = "//td[contains(text(),'ID')]/following-sibling::td[1]";
		String id = null;
		if (waitOnXpath(id_Xpath, timeOutSecs())) {
			id = getText(id_Xpath).trim();
			System.out.println("Retriving the User ID:::::: " + id);
		}
		return id;
	}

	public void changingUserType(String userType) {
		String findXpath = "//input[@value='Update']";
		String userTypeXpath = "//select[@name='type']";
		if (waitOnXpath(userTypeXpath, timeOutSecs())) {
			selectByVisibleText(userTypeXpath, userType);
		}
		waitOnXpath(findXpath, timeOutSecs());
		clickOnElement(findXpath);
	}

	public void userComformationMesg() {
		String continueXpath = "//input[@value='Continue']";
		if (waitOnXpath(continueXpath, timeOutSecs())) {
			clickOnElement(continueXpath);
			waitOnAlert();
			acceptAlert();
			sendReport("Verifiying the Displayed Continue Button ",
					"Successfully verify and Clicked on Continue Button ",
					"Pass");
		}
	}

	public String system_ID(String college) {
		String iDXpath = "//td[@class='bodytext']/ul/li";
		String system_ID = getText(iDXpath).trim();
		if (system_ID.contains(college)) {
			sendReport("Verifiying the Displayed Result",
					"Successfully Verified the Displayed Result " + system_ID,
					"Pass");
		} else {
			sendReport("Verified the Displayed Result",
					"Unable to Verify the Displayed Result " + system_ID,
					"Fail");
		}
		return system_ID;
	}

	public void getInProductionLink(String name) {
		String manageXpath = "//td[contains(text(),'" + name
				+ "')]/following-sibling::td/a[contains(text(),'Manage')]";
		try {
			if (waitOnXpath(manageXpath, timeOutSecs())) {
				clickOnElement(manageXpath);
				sendReport(
						"Clicking on the Manage Link ",
						"Successfully Clicked on Manage Button Against " + name,
						"Pass");
			} else {
				sendReport("Clicking on the Manage Link",
						"Unable to Locate the Manage Link Against " + name,
						"Fail");
			}
		} catch (NoSuchElementException e) {
			sendReport("Expected WebElement is " + name + " ",
					"Unable to locate the WebElement " + name + " ", "Fail");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * This method will search the attributes from the Vmware manage course page
	 */
	public void vmWareEducationAttributeTabs(String tab) {
		try {
			String tabXpath = "//a[contains(text(),'" + tab + "')]";
			String tabMesg = getText(tabXpath);
			if (waitOnXpath(tabXpath, timeOutSecs())) {
				clickOnElement(tabXpath);
				sendReport("Clicking on the Tab", "Successfully clicked on "
						+ tabMesg, "Pass");
				System.out.println("in tab page");
			} else {
				sendReport("Clicking on the Tab", "Unable to Locate the Tab"
						+ tabMesg, "Fail");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifyRadioGroupDisplayForClassType(String classType) {
		String Yes_Radio = "//td[contains(text(),'" + classType
				+ "')]/following-sibling::td/input";
		String No_Radio = "//td[contains(text(),'" + classType
				+ "')]/following-sibling::td/input[2]";
		if (waitOnText(Yes_Radio) && waitOnText(No_Radio)) {
			sendReport(
					"Verify if the Radio button group with Yes and No options are displayed for class type-"
							+ classType,
					"Successfully verified the display of Yes and No options for the class type-"
							+ classType, "Pass");
		} else {
			sendReport(
					"Verify if the Radio button group with Yes and No options are displayed for class type-"
							+ classType,
					"Yes and No options for the class type-" + classType
							+ " are not properly displayed", "Fail");
		}
	}

	/*public Map<String, String> getstatusofRadiosForClassType(String classType) {
		Map<String, String> radiobuttonStatus = new HashMap<>();
		String YesButton, NoButton;
		String Yes_Radio = "//td[contains(text(),'" + classType
				+ "')]/following-sibling::td/input";
		String No_Radio = "//td[contains(text(),'" + classType
				+ "')]/following-sibling::td/input[2]";
		YesButton = oASelFW.driver.findElement(By.xpath(Yes_Radio))
				.getAttribute("checked");
		if (YesButton.equals(null)) {
			YesButton = "false";
		}
		NoButton = oASelFW.driver.findElement(By.xpath(No_Radio)).getAttribute(
				"checked");
		if (NoButton.equals(null)) {
			NoButton = "false";
		}

		radiobuttonStatus.put("Yes", YesButton);
		radiobuttonStatus.put("No", NoButton);

		return radiobuttonStatus;
	}*/

	public String getscurrentstatus(String classType, String type) {

		String YesButton = null, NoButton = null;
		String Yes_Radio = "//td[contains(text(),'" + classType
				+ "')]/following-sibling::td/input";
		String No_Radio = "//td[contains(text(),'" + classType
				+ "')]/following-sibling::td/input[2]";
		if (waitOnXpath(Yes_Radio, timeOutSecs())) {
			YesButton = oASelFW.driver.findElement(By.xpath(Yes_Radio))
					.getAttribute("checked");
			System.out.println("Yes button status " + YesButton);
			if (YesButton == null) {
				YesButton = "false";
				System.out.println(YesButton);
			}
			NoButton = oASelFW.driver.findElement(By.xpath(No_Radio))
					.getAttribute("checked");
			System.out.println("No button status " + NoButton);
			if (NoButton == null) {
				NoButton = "false";
				System.out.println(NoButton);
			}
		}

		if (type.equalsIgnoreCase("yes"))
			return YesButton;
		else
			return NoButton;

	}

	public void setRadioStatusForClass(String classType, String status) {
		String Yes_Radio = "//td[contains(text(),'" + classType
				+ "')]/following-sibling::td/input";
		String No_Radio = "//td[contains(text(),'" + classType
				+ "')]/following-sibling::td/input[2]";

		String curr_status = getscurrentstatus(classType, status);
		if (status.equalsIgnoreCase("yes")
				&& curr_status.equalsIgnoreCase("false")) {
			oASelFW.driver.findElement(By.xpath(Yes_Radio)).click();
		} else if (status.equalsIgnoreCase("no")
				&& curr_status.equalsIgnoreCase("false")) {
			oASelFW.driver.findElement(By.xpath(No_Radio)).click();
		} else {
			System.out.println("Radio button already selected");
		}

	}

	public void saveAttribute() {
		String save_holder = "//input[@name='submit']";
		if (waitOnXpath(save_holder, shortWait())) {
			clickOnElement(save_holder);
			sendReport("Verify if user is able to Save the Course attributes",
					"Successfully saved the course attributes", "Pass");
		} else {
			sendReport("Verify if user is able to Save the Course attributes",
					"Unable to set Save for course attributes", "Fail");
		}
	}

	public void checkingFeatureNameStatus(String featreName,
			String enableStatus, String option1, String option2) {
		WebDriverWait wait = new WebDriverWait(oASelFW.driver, timeOutSecs());
		String featureName = "//td[text()='" + featreName + "']";
		String statusXpath = "//td[text()='" + featreName
				+ "']/following-sibling::td/input[@value='" + enableStatus
				+ "']";
		String yesXpath = "//td[contains(text(),'Self-Paced')]/following-sibling::td/input[@value='"
				+ option1 + "']";
		String noXpath = "//td[contains(text(),'Live Online')]/following-sibling::td/input[@value='"
				+ option2 + "']";
		try {
			if (waitOnXpath(featureName, timeOutSecs())) {
				wait.until(
						ExpectedConditions.elementToBeClickable(By
								.xpath(statusXpath))).click();
				sendReport("Selecting the Radio Button ",
						"Successfully Selected the Radio Button Agains "
								+ featreName, "Pass");
				System.out.println("in try block");
				if (waitOnXpath(yesXpath, timeOutSecs())) {
					wait.until(
							ExpectedConditions.elementToBeClickable(By
									.xpath(yesXpath))).click();
					sendReport(
							"Selecting the Radio Button ",
							"Successfully Selected the Radio Button for Self-Paced",
							"Pass");
					waitOnXpath(option1, shortWait());
					wait.until(
							ExpectedConditions.elementToBeClickable(By
									.xpath(noXpath))).click();
					sendReport(
							"Selecting the Radio Button ",
							"Successfully Selected the Radio Button Live Online",
							"Pass");
					System.out.println("in self-paced page");
				} else {
					sendReport("Expected WebElement On the page" + featreName,
							"Unable to locate the Expected Element "
									+ featureName, "Fail");
				}
			}
		} catch (NoSuchElementException e) {
			sendReport("Expected WebElement On the page" + featreName,
					"Actual WebElement Locate on the Page" + featureName,
					"Fail");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectingDropDownSimpleSearchPage(String searchdefault,
			String searchMenu, String searchInterface) {
		try {

			String simpleSearchDefault = "//select[@name='defaultSearchType']";
			String simpleSearchBarCatalog = "//select[@name='flag_search_showDropdown']";
			String searchInterXpath = "//select[@name='flag_legacysearch']";

			waitOnXpath(simpleSearchDefault, timeOutSecs());
			selectByVisibleText(simpleSearchDefault, searchdefault);
			sendReport("Selecting the Value from the DropDown",
					"Successfully Selected the Value As" + searchdefault,
					"Pass");

			selectByVisibleText(simpleSearchBarCatalog, searchMenu);
			sendReport("Selecting the Value from the DropDown",
					"Successfully Selected the Value As" + searchMenu, "Pass");

			selectByVisibleText(searchInterXpath, searchInterface);
			sendReport("Selecting the Value from the DropDown",
					"Successfully Selected the Value As" + searchInterface,
					"Pass");

		} catch (ElementNotFoundException e) {
			sendReport("Verifying the WebElement on the Page",
					"Unable to Verify the WebElement As", "Fail");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void save_Button() {
		String saveXpath = "//input[@name='submit']";
		waitOnXpath(saveXpath, timeOutSecs());
		clickOnElement(saveXpath);
	}

	public void clickOnRegisrant() {

		String registrant_xpath = "//a[text()='Registrants']";
		if (waitOnXpath(registrant_xpath, timeOutSecs())) {
			clickOnElement(registrant_xpath);
			sendReport("Click on User registrant",
					"Clicked on User registrant", "Pass");

		} else {
			sendReport("Click on User registrant", "Couldn't find registrant",
					"fail");

		}

	}

	public void giveDetailsOnregistrantPage(String type, String value) {
		String user_idXpath = "//input[@name= 'id_user']";
		String reg_idXpath = "//input[@name= 'id']";

		if (type.equalsIgnoreCase("User Id:")) {

			waitOnXpath(user_idXpath, timeOutSecs());
			setTextField(user_idXpath, value);
			click_Find_Button();

			sendReport("Search Registrant with user id",
					"Searched user with User ID", "Pass");
		} else if (type.equalsIgnoreCase("Registration Number:")) {
			setTextField(reg_idXpath, value);
			click_Find_Button();
			sendReport("Search Registrant with REgistrant id",
					"Searched user with Registrant ID", "Pass");
		}

		else {
			sendReport("Search Registrant with REgistrant/user id",
					"Could not search user with Registrant ID", "fail");

		}

	}

	public void clickOnURL(String xpath, String name) {

		/*
		 * WebDriverWait wait = new WebDriverWait(oASelFW.driver,
		 * timeOutSecs());
		 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		 */
		// oASelFW.driver.findElement(By.xpath(xpath)).click();
		if (waitOnXpath(xpath, 20)) {
			clickOnElement(xpath);
			sendReport("Click on " + name, "Successfully Clicked on " + name,
					"Pass");
			
		}

		else {
			sendReport("Click on " + name, name + " not found ", "Fail");
		}
	}

	/**
	 * This method will help the user to verify text. It will take three
	 * parameteres 1. xpath= the system will retrieve text from this xpath 2.
	 * name= this will assist system that from where the verification is needed
	 * to be made 3. txt_to_verify = is the text which we need to verify from
	 * the page
	 * 
	 * @param xpath
	 * @param name
	 * @param txt_to_verify
	 */

	public void VerifyTextInURL(String xpath, String name, String txt_to_verify) {
		waitOnXpath(xpath, timeOutSecs());
		String retrieved_text = oASelFW.driver.findElement(By.xpath(xpath))
				.getText();
		System.out.println(retrieved_text);
		if (retrieved_text.equalsIgnoreCase(txt_to_verify)) {
			sendReport("Verify in " + name, "Found " + retrieved_text + " in "
					+ name, "Pass");
		} else {
			sendReport("Verify in " + name, "No text found", "Fail");
		}
	}

	public void updatingEmployedLastName(String value)// 'kui
	{
		String lName_xpath = "//input[@name='lname']";
		String Name = getTextByAttribute(lName_xpath, "value");
		// String Name =
		// oASelFW.driver.findElement(By.xpath(lName_xpath)).getAttribute(value).trim();
		String update_Holder = "//input[@value='Update']";
		System.out.println("employee name before attribute " + Name);
		clean_ExistingData(lName_xpath);
		String up_Name = Name + value;
		if (waitOnXpath(lName_xpath, timeOutSecs())) {
			clean_ExistingData(lName_xpath);
			setTextField(lName_xpath, up_Name);
			System.out.println("after attribute value " + up_Name);
			sendReport("Updating the Employee Name ",
					"Successfully Updated the Employee Name as: " + up_Name,
					"Pass");
		} else {
			sendReport("Verifiying the Employee Name Field ",
					"Unable to Find the Name Field ", "Fail");
		}
		clickOnElement(update_Holder);
		sendReport("Updating the Given Employee Information Data ",
				"Successfully Updated the Given Employee Information ", "Pass");
	}

	public void verifySubsNameinMyEnrollmnts(String xpath, String name,
			String txt_to_verify) {
		String date_xpath = "//td[text()='Date Subscribed:']/following-sibling::td";

		if (waitOnXpath(xpath, 15)) {
			String retrieved_text = oASelFW.driver.findElement(By.xpath(xpath))
					.getText();
			System.out.println(retrieved_text);

			if (retrieved_text.equalsIgnoreCase(txt_to_verify))

			{
				clickOnElement(xpath);
				waitOnXpath(date_xpath, timeOutSecs());
				String retrieved_date = oASelFW.driver.findElement(
						By.xpath(xpath)).getText();
				String date1 = GetDate();

				if (retrieved_date.equalsIgnoreCase(date1)) {
					sendReport(
							"VErify that the date matches if the Course already exist in myEnrollments",
							"Dates matched: Subscription already taken ",
							"Fail");
				}

				else {

					sendReport(
							"VErify that the date matches if the Course already exist in myEnrollments",
							"Dates did not match: User has already subscribed ",
							"Pass");
				}

			} else {
				sendReport("Verify subscription in" + name,
						"Other Subscription found", "Pass");
			}

		}

		else {
			sendReport("Verify subscription in" + name,
					"Required subscription doesn't found in " + name, "Pass");

		}
	}

	public String GetDate()

	{

		// Create object of SimpleDateFormat class and decide the format
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		// get current date time with Date()
		Date date = new Date();

		// Now format the date
		String date1 = dateFormat.format(date);

		// Print the Date
		System.out.println("Current date and time is " + date1);

		return date1;

	}

	public void searchClass(String Searchtype, String Value) {
		if (Searchtype.equalsIgnoreCase("Class ID:")) {
			setTextField("//input[@name='id_course']", Value);
			sendReport("Search the Class", "Searched with Class Id: " + Value,
					"Pass");
		}

		else if (Searchtype.equalsIgnoreCase("Course ID:")) {
			setTextField("//input[@name='id_subject']", Value);
			sendReport("Search the Class", "Searched with Course Id: " + Value,
					"Pass");

		}

		else if (Searchtype.equalsIgnoreCase("Course Name:")) {
			setTextField("//input[@name='name']", Value);
			sendReport("Search the Class", "Searched with Course name: "
					+ Value, "Pass");

		}
	}

	public void FindUser(String Usertype, String Value) {

		if (Usertype.equalsIgnoreCase("User ID:")) {
			setTextField("//input[@name='id']", Value);
			sendReport("Search the Class", "Searched with User Id", "Pass");
		}

		else if (Usertype.equalsIgnoreCase("Email:")) {
			setTextField("//input[@name='email']", Value);
			sendReport("Search the Class", "Searched with Email:", "Pass");

		} else {
			sendReport("Search the Class", "Couldn't Find the Locator", "Fail");

		}

	}

	public void ScheduleLiveClass() {
		try {

			selectByVisibleText("//select[@name='id_language']", "English");
			setTextField("//input[@name='crs_capacity']", "10");
			setTextField("//input[@name='date_start']", "12/16/2017");
			selectByVisibleText("//select[@name='hour']", "07");
			selectByVisibleText("//select[@name='minute']", "15");

			setTextField("//input[@name='date_end']", "12/31/2017");
			selectByVisibleText("//select[@name='e_hour']", "07");
			selectByVisibleText("//select[@name='e_minute']", "15");
			clickOnURL("//input[@value='Save']", "Save");

			String classId = getText("//td[text()='Class ID:']/following-sibling::td");
			VerifyTextInURL("//td[text()='Class Published!']",
					"New Class Room", "Class Published!");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	/**
	 * @author arifuddin_mohd Description-- This method will Verify the VATC
	 *         Page Fields.
	 */

	public void verifyScheduleVATCfields() {
		try {

			oASelFW.effecta("verifyElementPresent",
					"//p[text()='What']/../../following-sibling::tr//td",
					"Course");
			oASelFW.effecta("verifyElementPresent",
					"//p[text()='What']/../../following-sibling::tr[2]//td",
					"Language");
			oASelFW.effecta("verifyElementPresent",
					"//p[text()='What']/../../following-sibling::tr[3]//td",
					"Capacity");

			oASelFW.effecta("verifyElementPresent",
					"//p[text()='When']/../../following-sibling::tr//td",
					"Start Date");
			oASelFW.effecta("verifyElementPresent",
					"//p[text()='When']/../../following-sibling::tr[2]//td",
					"Start Time");
			oASelFW.effecta("verifyElementPresent",
					"//p[text()='When']/../../following-sibling::tr[3]//td",
					"End Date");
			oASelFW.effecta("verifyElementPresent",
					"//p[text()='When']/../../following-sibling::tr[4]//td",
					"End Time");
			oASelFW.effecta("verifyElementPresent",
					"//p[text()='When']/../../following-sibling::tr[5]//td",
					"Registration Close");
			oASelFW.effecta("verifyElementPresent",
					"//p[text()='When']/../../following-sibling::tr[6]//td",
					"Time Zone");

			oASelFW.effecta("verifyElementPresent",
					"//p[text()='Who']/../../following-sibling::tr//td",
					"Audience");
			oASelFW.effecta("verifyElementPresent",
					"//p[text()='Who']/../../following-sibling::tr[2]//td",
					"Registration");
			oASelFW.effecta("verifyElementPresent",
					"//p[text()='Who']/../../following-sibling::tr[3]//td",
					"View in Portals");
			oASelFW.effecta("verifyElementPresent",
					"//p[text()='Who']/../../following-sibling::tr[4]//td",
					"Host ID");
			oASelFW.effecta("verifyElementPresent",
					"//p[text()='Who']/../../following-sibling::tr[5]//td",
					"Class Evaluation");

			oASelFW.effecta("verifyElementPresent",
					"//p[text()='Resources']/../../following-sibling::tr//td",
					"Instructor");

			oASelFW.effecta("verifyElementPresent",
					"//p[text()='Notify']/../../following-sibling::tr//td",
					"Use Owner Alias");

		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void ScheduleLiveClassAsDirectUser(String timeZone) {

		try {

			selectByVisibleText("//select[@name='id_language']", "English");
			selectByVisibleText("//select[@name='id_host']", "Education WebEx");
			Thread.sleep(1000);
			setTextField("//input[@name='crs_capacity']", "10");
			setTextField("//input[@name='date_start']", "12/16/2016");
			selectByVisibleText("//select[@name='hour']", "07");
			selectByVisibleText("//select[@name='minute']", "15");

			setTextField("//input[@name='date_end']", "12/19/2016");
			selectByVisibleText("//select[@name='e_hour']", "07");
			selectByVisibleText("//select[@name='e_minute']", "15");
			clickOnURL("//button[@title='Show All Items']",
					"Click on Button to Show All Items");
			Thread.sleep(1000);
			/*
			 * clickOnURL("//button[@title='Show All Items']",
			 * "Time Zone Drop Down"); clickOnURL("//a[text()='"+timeZone+"']",
			 * "Selected '"+timeZone+"'");
			 */
			setTextField(
					"//select[@name='time_zone']/following-sibling::input",
					"America/New_York");
			clickOnURL("//a[text()='America/New_York']",
					"Selected option from drop Down");
			selectByIndex("//select[@name='rate']", 1);
			selectByIndex("//select[@name='instructors']", 2);
			selectByVisibleText("//select[@name='id_curriculum_host_id']",
					"Gilmore e-Book (E-Book)");

			clickOnURL("//input[@value='Save']", "Save");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

	public void VerifyURL(String xpath, String name, String txt_to_verify) {
		if (waitOnXpath(xpath, timeOutSecs())) {
			sendReport("Verify in " + name, "Found " + txt_to_verify + " in"
					+ name, "Pass");
		} else {
			sendReport("Verify in " + name, "No text found", "Fail");
		}
	}

	public void ScheduleLOLClassAsVATC(String host, String subject)
			throws InterruptedException {

		clickOnURL("//a[text()='Schedule/Publish a Class']",
				"Schedule/Publish Class");
		Thread.sleep(2000);
		selectByVisibleText("//select[@name='sub_type']", "Live Online");
		selectByVisibleText("//select[@name='id_host']", host);
		clickOnURL("//input[@value='Continue']", "Continue Button");
		clickOnURL("//input[@value='" + subject + "']",
				"Course wih course id '" + subject + "'");
		clickOnURL("//input[@value='Continue']", "Continue");
		clickOnURL("//input[@value='Continue']", "Continue");

		ScheduleLiveClass();
		String classId = getText("//td[text()='Class ID:']/following-sibling::td");
		VerifyTextInURL("//td[text()='Class Published!']", "New Class Room",
				"Class Published!");
	}
	public void ScheduleAcademyClassAsVATC(String host, String subject)
			throws InterruptedException {

		clickOnURL("//a[text()='Schedule/Publish a Class']",
				"Schedule/Publish Class");
		Thread.sleep(2000);
		selectByVisibleText("//select[@name='sub_type']", "Academy");
		selectByVisibleText("//select[@name='id_host']", host);
		clickOnURL("//input[@value='Continue']", "Continue Button");
		clickOnURL("//input[@value='" + subject + "']",
				"Course wih course id '" + subject + "'");
		clickOnURL("//input[@value='Continue']", "Continue");
		clickOnURL("//input[@value='Continue']", "Continue");

		ScheduleLiveClass();
		String classId = getText("//td[text()='Class ID:']/following-sibling::td");
		VerifyTextInURL("//td[text()='Class Published!']", "New Class Room",
				"Class Published!");
	}

	public void gettingStartedPage_CommonTasks(String commTask) {
		String commonTask = "//a[contains(text(),'" + commTask + "')]";

		if (waitOnXpath(commonTask, timeOutSecs())) {
			clickOnElement(commonTask);
			sendReport("Click on the Schedule/Publish a Class Tab",
					"Successfully Clicked on Schedule/Publish a Class", "Pass");

		} else {
			sendReport("", "", "Fail");
			System.out.println("Unable to find the Element");
		}

	}

	/**
	 * @author arifuddin_mohd Description-- This Method will select the Type of
	 *         class Need to Create/work with.
	 */

	public void scheduleNewClassFormManagePage(String type, String host,
			String subject) {
		try {
			selectByVisibleText("//select[@name='sub_type']", type);
			selectByVisibleText("//select[@name='id_host']", host);
			clickOnURL("//input[@value='Continue']", "Continue Button");
			clickOnURL("//input[@value='" + subject + "']",
					"Course wih course id '" + subject + "'");
			clickOnURL("//input[@value='Continue']", "Continue");

		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author arifuddin_mohd Description -- This method will Clicked on the
	 *         continue Button in
	 *         newClassPage/ClassNewClass/NewClassClassroom
	 */
	public void clickonContinueButtonforClassPublish() {
		if (waitOnXpath("//input[@value='Continue']", timeOutSecs())) {
			clickOnURL("//input[@value='Continue']", "Continue");
		} else {
			System.out.println("No Continue Button found on Page");
		}
	}

	public void VerifyDeliveryTypeAsClassRoom() {
		String add_xpath = "//td[text()='Course Delivery:']/following-sibling::td/div/a";
		clickOnURL(add_xpath, " Add Link");
		if (waitOnXpath("//td[text()='Classroom']", 10)) {
			clickOnURL(
					"//td[text()='Classroom']/preceding-sibling::td[1]/input",
					"Check Box");
			clickOnURL("//input[@value='Save']", " Save");
		}

		else {

			clickOnURL("//input[@value='Cancel']", "Cancel");
		}
	}
	public void VerifyDeliveryTypeAsWalkIn() {
		String add_xpath = "//td[text()='Course Delivery:']/following-sibling::td/div/a";
		clickOnURL(add_xpath, " Add Link");
		if (waitOnXpath("//td[text()='Walk In']", 10)) {
			clickOnURL(
					"//td[text()='Walk In']/preceding-sibling::td[1]/input",
					"Check Box");
			clickOnURL("//input[@value='Save']", " Save");
		}

		else {
			if(waitOnXpath("//input[@value='Cancel']", 05))
			{clickOnURL("//input[@value='Cancel']", "Cancel");}
			else
			{
				clickOnURL("//input[@value='Back']", "Back Button as all classes are added");
			}
		}
	}
	public void VerifyDeliveryTypeAsAcademy() {
		String add_xpath = "//td[text()='Course Delivery:']/following-sibling::td/div/a";
		clickOnURL(add_xpath, " Add Link");
		if (waitOnXpath("//td[text()='Academy']", 10)) {
			clickOnURL(
					"//td[text()='Classroom']/preceding-sibling::td[1]/input",
					"Check Box");
			clickOnURL("//input[@value='Save']", " Save");
		}

		else {

			clickOnURL("//input[@value='Cancel']", "Cancel");
		}
	}
	

	public void scheduleClassroomAsVATC(String host, String subject,
			String CityName, String TrainingCntr, String Room,
			String startDate, String endDate) throws InterruptedException,
			IOException {
		clickOnURL("//a[text()='Schedule/Publish a Class']",
				"Schedule/Publish Class");
		Thread.sleep(2000);
		selectByVisibleText("//select[@name='sub_type']", "Classroom");
		selectByVisibleText("//select[@name='id_host']", host);
		clickOnURL("//input[@value='Continue']", "Continue ");
		clickOnURL("//input[@value='" + subject + "']",
				"Course wih course id '" + subject + "'");
		clickOnURL("//input[@value='Continue']", "Continue");
		Thread.sleep(2000);
		AddNewRoom(CityName, TrainingCntr, Room);
		scheduleClassRoomClass(startDate, endDate);

		String classId = getText("//td[text()='Class ID:']/following-sibling::td");
		VerifyTextInURL("//td[text()='Class Published!']", "New Class Room",
				"Class Published!");

	}
	
	public void schedulewalkInClassAsVATC(String host, String subject,
			String CityName, String TrainingCntr, String Room,
			String startDate, String endDate) throws InterruptedException,
			IOException {
		clickOnURL("//a[text()='Schedule/Publish a Class']",
				"Schedule/Publish Class");
		Thread.sleep(2000);
		selectByVisibleText("//select[@name='sub_type']", "Walk In");
		selectByVisibleText("//select[@name='id_host']", host);
		clickOnURL("//input[@value='Continue']", "Continue ");
		clickOnURL("//input[@value='" + subject + "']",
				"Course wih course id '" + subject + "'");
		clickOnURL("//input[@value='Continue']", "Continue");
		Thread.sleep(2000);
		AddNewRoom(CityName, TrainingCntr, Room);
		scheduleClassRoomClass(startDate, endDate);

		String classId = getText("//td[text()='Class ID:']/following-sibling::td");
		VerifyTextInURL("//td[text()='Class Published!']", "New Class Room",
				"Class Published!");

	}

	public void scheduleClassRoomClassASDirectUSer(String subject,
			String startDate, String endDate) throws InterruptedException {
		clickOnURL("//a[text()='Schedule/Publish a Class']",
				"Schedule/Publish Class");
		Thread.sleep(2000);
		selectByVisibleText("//select[@name='sub_type']", "Classroom");
		clickOnURL("//input[@value='Continue']", "Continue");
		clickOnURL("//input[@value='" + subject + "']",
				"Course wih course id '" + subject + "'");
		clickOnURL("//input[@value='Continue']", "Continue");
		Thread.sleep(2000);

		AddNewRoomWithoutParameters();

		selectByVisibleText("//select[@name='id_language']", "English");
		setTextField("//input[@name='crs_capacity']", "10");
		selectByVisibleText("//select[@name='hour']", "07");
		selectByVisibleText("//select[@name='minute']", "15");

		selectByVisibleText("//select[@name='e_hour']", "07");
		selectByVisibleText("//select[@name='e_minute']", "15");
		selectByIndex("//select[@name='rate']", 1);
		int j = 1;
		for (int i = 1; i <= j; i++) {
			int k = i + 3;
			cleartextField("//input[@name='date_start']");
			setTextField("//input[@name='date_start']", "07/" + i + "/2016");
			cleartextField("//input[@name='date_end']");
			setTextField("//input[@name='date_end']", "07/" + k + "/2016");
			System.out.println("06/'" + i + "'/2016");
			System.out.println("06/'" + k + "'/2016");
			clickOnURL("//input[@value='Save']", "Save");
			if (waitOnXpath(
					"//strong[text()='Please address the issues described immediately below']",
					20)) {

				j++;
			}

			else {
				sendReport("Verify Class is Publisheded",
						"Class Published sucessfully", "Pass");
			}
		}

		String classId = getText("//td[text()='Class ID:']/following-sibling::td");
		VerifyTextInURL("//td[text()='Class Published!']", "New Class Room",
				"Class Published!");
		System.out.println(classId);

	}

	public void scheduleClassRoomClass(String startDate, String endDate) {
		try {

			selectByVisibleText("//select[@name='id_language']", "English");
			setTextField("//input[@name='crs_capacity']", "10");
			setTextField("//input[@name='date_start']", startDate);
			selectByVisibleText("//select[@name='hour']", "07");
			selectByVisibleText("//select[@name='minute']", "15");

			setTextField("//input[@name='date_end']", endDate);
			selectByVisibleText("//select[@name='e_hour']", "07");
			selectByVisibleText("//select[@name='e_minute']", "15");

			clickOnURL("//input[@name='room_check']", "Unmark Room conflict");

			clickOnURL("//input[@value='Save']", "Save");

			String classId = getText("//td[text()='Class ID:']/following-sibling::td");
			VerifyTextInURL("//td[text()='Class Published!']",
					"New Class Room", "Class Published!");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	public void scheduleLOLClassAsDirectUser(String subject, String timeZone)
			throws InterruptedException {
		clickOnURL("//a[text()='Schedule/Publish a Class']",
				"Schedule/Publish Class");
		Thread.sleep(2000);
		selectByVisibleText("//select[@name='sub_type']", "Live Online");
		clickOnURL("//input[@value='Continue']", "Continue Button");
		clickOnURL("//input[@value='" + subject + "']",
				"Course wih course id '" + subject + "'");
		clickOnURL("//input[@value='Continue']", "Continue");
		clickOnURL("//input[@type='radio']", "Address radio button");
		clickOnURL("//input[@value='Continue']", "Continue");
		ScheduleLiveClassAsDirectUser(timeZone);
	}

	public String validateXpath(String xpath) {
		String retrieved_text;

		waitOnXpath(xpath, 20);

		retrieved_text = oASelFW.driver.findElement(By.xpath(xpath)).getText();
		System.out.println(retrieved_text);
		sendReport("Copy myLearn Id", "Copied", "Pass");
		return (retrieved_text);
	}

	public void GiveMailingAddress(String address, String city, String state,
			String zipCode, String country) throws InterruptedException {
		String mailingAddr_xpath = "//td[text()='Mailing Address:']";
		String state_Holder = "//select[@name='coAdd_country']";
		if (waitOnXpath(mailingAddr_xpath, 15)) {
			oASelFW.effecta("typeSpecifiedText",
					"//input[@name='coAdd_street']", address, "Street");
			oASelFW.effecta("typeSpecifiedText", "//input[@name='coAdd_city']",
					city, "City");
			oASelFW.effecta("typeSpecifiedText",
					"//input[@name='coAdd_state']", state, "State");
			oASelFW.effecta("typeSpecifiedText", "//input[@name='coAdd_zip']",
					zipCode, "Zip Code");
			Thread.sleep(2000);
			selectByIndex(state_Holder, 2);
			sendReport("Update Address ", "Successfully updated the address ",
					"Pass");
			clickOnElement("//input[@name='bill_mailing']");
		}
	}

	public void VerifiyingPriceQuoteandAddressPage(String xpath) {
		waitOnXpath(xpath, timeOutSecs());
		String retrieved_text = oASelFW.driver.findElement(By.xpath(xpath))
				.getText();
		System.out.println(retrieved_text);

		if (waitOnXpath(xpath, timeOutSecs())) {
			sendReport("Verifying the VMware Education  Page ",
					"Successfully Verify and Retrived the value "
							+ retrieved_text, "Pass");
		} else {
			sendReport("Verifying the VMware Education  Page ",
					"No text found", "Fail");
		}
	}

	public void VerifiyingPriceQuoteandAddressPageAfterReplacing(String xpath) {
		waitOnXpath(xpath, timeOutSecs());
		String retrieved_text = oASelFW.driver.findElement(By.xpath(xpath))
				.getText();
		System.out.println(retrieved_text);
		String[] retrived = retrieved_text.split("\\\n");
		String currency = null;
		for (int i = 0; i < retrived.length; i++) {
			if (waitOnXpath(xpath, timeOutSecs())) {
				System.out.println("Retriving the amount " + retrived[i]);
				if (retrived[i].contains("$")) {
					currency = retrived[i].replace("$", "US Currency");
					System.out.println("currency " + currency);
					sendReport("Verifying the VMware Education  Page ",
							"Successfully Verify and Retrived the value "
									+ currency, "Pass");

				} else if (retrived[i].contains("€")) {
					currency = retrived[i].replace("€", "EUR Currency");
					System.out.println("currency " + currency);
					sendReport("Verifying the VMware Education  Page ",
							"Successfully Verify and Retrived the value "
									+ currency, "Pass");

				} else if (retrived[i].contains("£")) {
					currency = retrived[i].replace("£", "GBP Currency");
					System.out.println("currency " + currency);
					sendReport("Verifying the VMware Education  Page ",
							"Successfully Verify and Retrived the value "
									+ currency, "Pass");

				} else if (retrived[i].contains("¥")) {
					currency = retrived[i].replace("¥", "JPY Currency");
					System.out.println("currency " + currency);
					sendReport("Verifying the VMware Education  Page ",
							"Successfully Verify and Retrived the value "
									+ currency, "Pass");
				} else if (retrived[i].contains("Credits")) {
					currency = retrived[i].replace("Credits", "Credits");
					System.out.println("currency " + currency);
					sendReport("Verifying the VMware Education  Page ",
							"Successfully Verify and Retrived the Credits value "
									+ currency, "Pass");
				}
			} else {
				sendReport("Verifying the VMware Education  Page ",
						"No text found", "Fail");
			}
		}
	}

	public void clickOnTab(String xpath, String name) {
		if (waitOnXpath(xpath, timeOutSecs())) {
			clickOnElement(xpath);
			sendReport("Click on " + name + " tab", "Clicked on " + name
					+ " tab", "Pass");
		} else
			sendReport("Click on " + name + " tab", "Clicked on " + name
					+ " tab", "Pass");
	}

	public void verifyDuplicates() {
		String number_xpath = "//td[text()='2.']";
		if (waitOnXpath(number_xpath, 20)) {
			sendReport("Verify Duplicate ", "Duplicates found", "Fail");

		}

		else {
			sendReport("Verify Duplicate ", "Duplicates not found", "Pass");

		}

	}

	public void AddVerifyTimeZoneinClass(String timeZone) {

		if (waitOnXpath("//td[contains (text(), 'GMT-08:00')]", 05)) {
			sendReport("Verify Time zone in 'Live Online Time Zone:'",
					"Found Time Zone in Live Online Time Zone:", "Pass");

		}

		else {

			clickOnURL(
					"//td[text()='Live Online Time Zone:']/following-sibling::td/div/a",
					"Add Link");
			if (waitOnXpath(timeZone, 10)) {
				clickOnURL(timeZone, "Check box");
				clickOnURL("//input[@value='Save']", "Save");

			}

			else {

				sendReport("Check time Zone", "Time Zone not found", "Fail");
			}

		}

	}

	public void addShippingAddrOnCheckoutPage() {
		clickOnURL(
				"//div[text()='New shipping address']/../preceding-sibling::td/input",
				"New Shipping Address Radio Box");
		setTextField("//input[@name='street1']",
				"Rua Samuel Morse, 74 Cj. 24 Brooklin");
		setTextField("//input[@name='city']", "Brooklin");
		setTextField("//input[@name='state']", "Sao Paulo");
		selectByVisibleText("//select[@name='countryName']", "Brazil");
		setTextField("//input[@name='company']", "Adistec - Brazil");
		setTextField("//input[@name='contact']", "Saurabh Mandal");
		setTextField("//input[@name='phone']", "0987654321");
		setTextField("//input[@name='email']", "operation@adistec.com");
		clickOnURL("//input[@name='continueBtn']", "Continue To Payment Button");
		clickOnURL("//input[@name='continueBtn']", "Place Order button");
	}

	/*public void VerifystudentDroppedRoaster(String clockImg_xpath) {
		clickOnClassProp();
		totalRegisteredLink("Total Registered:");
		clickOnURL("//input[@name='GetThis']",
				"Check Box to select the student which is to be dropped from the list");
		clickOnURL(
				"//td[contains(text(),'1.')]/following-sibling::td/a[text()='Drop']",
				"Drop Link");
		clickOnURL("//input[@value='Continue']", "Continue");

		if (waitOnXpath(clockImg_xpath, timeOutSecs())) {
			sendReport("Verify in Inactive Registrants",
					" Green tick Found in Inactive registrants", "Fail");
		} else {
			sendReport("Verify in Inactive Registrants",
					"Green ticks Not Found in Inactive registrants", "Pass");
		}

	}*/

	public void VerifyStudentFromRoasterAfterDroppingAsDirectUser(
			String clockImg_xpath) {
		clickOnURL("//input[@name='GetThis']",
				"Check Box to select the student which is to be dropped from the list");
		clickOnURL(
				"//td[contains(text(),'1.')]/following-sibling::td/a[text()='Drop']",
				"Drop Link");
		clickOnURL("//input[@value='Continue']", "Continue");

		if (waitOnXpath(clockImg_xpath, 20)) {
			sendReport("Verify in Inactive Registrants",
					" Green tick Found in Inactive registrants", "Fail");
		} else {
			sendReport("Verify in Inactive Registrants",
					"Green ticks Not Found in Inactive registrants", "Pass");
		}

	}

	public void switchToMenu() {
		switch_to_default_content();
		switch_to_menu_content();
	}

	public void switchToMain() {
		switch_to_default_content();
		switch_to_main_content();
	}

	public void SearchHost(String HostId) {
		System.out.println("shifting to menu content frame");
		switch_to_menu_content();
		manage_content();
		parentAndSubService("Manage", "Hosts");
		switchToMain();
		setTextField("//input[@name='host_id']", HostId);
		clickOnURL("//input[@name='submit']", "Search");
	}

	public void searchClassAsVATC(String find_tab_xpath, String searchType,
			String classId) throws InterruptedException {
		switchToMenu();
		manage_content();
		parentAndSubService("Manage", "Classes");
		switchToMain();
		clickOnTab(find_tab_xpath, "Find Tab");
		Thread.sleep(2000);
		searchClass(searchType, classId);
	}

	/*public void VerifyRedempCodeUnderGreenTick() {
		clickOnClassProp();
		totalRegisteredLink("Total Registered:");

		clickOnURL("//a[contains(text(),'Notes')]/following-sibling::a/img",
				" Green tick");
		VerifyURL(
				"//a[contains(text(),'Notes')]/following-sibling::a/following-sibling::div/span",
				"on Clicking Green Tick", "e-book redemption Code");
	}*/

	public void placeOrderforeBookForVATC(String companyName_xpath,
			String CompanyName, String orderNum) {
		switchToMenu();
		order_content();
		parentAndSubService("Order", "Getting Started");
		switchToMain();
		clickOnURL("//a[text()='Student e-Books']", "Student e-Books");
		clickOnURL(companyName_xpath, CompanyName);
		clickOnURL(orderNum, "Check Box");
		clickOnURL("//input[@value='Add to Cart']", "Add to Cart button");

	}

	/*public void verifyClocklogoAfterPlacingOrder(String find_tab_xpath,
			String searchType, String classId) throws InterruptedException {
		searchClassAsVATC(find_tab_xpath, searchType, classId);
		click_Find_Button();
		clickOnURL("//a[@title='View Class Properties.']", "Class properties");
		totalRegisteredLink("Total Registered:");
		VerifyURL("//img[@title='e-Book has been requested.']",
				"Registered Student", "Circle Clock");
	}*/

	/*public void registerStudentToAFeeBasedClass(String regId, String address,
			String city, String state, String zipCode, String country)
			throws InterruptedException {
		totalRegisteredLink("Add");
		setTextField("//input[@name='id']", regId);
		click_Find_Button();
		clickOnURL("//input[@name='GetThis']", "Select User");
		clickOnURL("//input[@name='notice']", "Unmark notify User");
		clickOnURL("//input[@name='submitForm']", "Continue Button");
		GiveMailingAddress(address, city, state, zipCode, country);
		clickOnURL("//input[@name='continueBtn']",
				"Continue Button to go to Payment History");
		if (waitOnXpath("//input[@name='continueBtn']", 5)) {
			clickOnURL("//input[@name='continueBtn']",
					"Continue Button To go to Checkout page");
		}
		clickOnURL("//a[text()='Add New Transaction']", "Add New transaction");
		selectByIndex("//select[@name='id_address']", 2);
		selectByVisibleText("//select[@name='paymentMethodName']", "Check");
		setTextField("//input[@name='tx_amt']", "1316");
		clickOnURL("//input[@value='Continue']",
				"Continue To Setup New Account");
		setTextField("//input[@name='tx_num']", "12345");
		clickOnURL("//input[@value='Continue']",
				"Continue To Finish payment process");
	}*/

	public void SearchClassWithParameters(String College, String DeliveryOrg,
			String StrtDate) {
		selectByVisibleText("//select[@name='college']", College);
		selectByVisibleText("//select[@name='delivery']", DeliveryOrg);
		setTextField("//input[@name='date_startStart']", StrtDate);
	}

	public void SearchClassWithParametersForCoordinator(String College,
			String StrtDate) {
		selectByVisibleText("//select[@name='college']", College);

		setTextField("//input[@name='date_startStart']", StrtDate);
	}

	public void verifyClassTypesInSearchResult() {
		String Classroom_xpath = "//td[text()='Classroom']";
		String LiveOnline_xpath = "//td[text()='Live Online']";
		String onsite_xpath = "//td[text()='Onsite']";

		VerifyURL(Classroom_xpath, "Search result page",
				"Delivery Type as Class Room");
		VerifyURL(LiveOnline_xpath, "Search result page",
				"Delivery Type as Live Online");
		VerifyURL(onsite_xpath, "Search result page", "Delivery Type as onsite");
	}

	public void verifyClassTypesInSearchResult_Coordinator() {
		String Classroom_xpath = "//td[text()='Classroom']";
		String LiveOnline_xpath = "//td[text()='Live Online']";

		VerifyURL(Classroom_xpath, "Search result page",
				"Delivery Type as Class Room");
		VerifyURL(LiveOnline_xpath, "Search result page",
				"Delivery Type as Live Online");

	}

	public void AddNewRoom(String cityName, String TrainingCntr, String Room)
			throws InterruptedException {
		selectByIndex("//select[@name='city']", 2);
		Thread.sleep(2000);
		selectByVisibleText("//select[@name='loc']", TrainingCntr);
		Thread.sleep(2000);
		selectByVisibleText("//select[@name='room']", Room);
		Thread.sleep(2000);
		clickOnURL("//input[@value='Continue']", "Continue");

	}

	public void AddNewRoomWithoutParameters() throws InterruptedException {
		selectByIndex("//select[@name='city']", 2);
		Thread.sleep(2000);
		selectByIndex("//select[@name='loc']", 1);
		Thread.sleep(2000);
		selectByIndex("//select[@name='room']", 1);
		Thread.sleep(2000);
		clickOnURL("//input[@value='Continue']", "Continue");
	}

	public void ClickOnClassProperties() {
		clickOnURL("//a[text()='View Class Properties']", "Class Properties");

	}

	public void ClickOnGoButton() {
		clickOnURL("//input[@value='Go']", "Go Button");
	}

	public void ConvertUserFromRoaster(String fname, String lname,
			String email, String company, String street, String city,
			String state, String zipCode) {
		clickOnURL("//a[text()='Convert']", " Convert");
		setTextField("//input[@name='fname']", fname);
		setTextField("//input[@name='lname']", lname);
		setTextField("//input[@name='email']", email);
		clickOnURL("//input[@value='Continue']", "Continue to create new Page");
		setTextField("//input[@name='company_name']", company);
		setTextField("//input[@name='coAdd_street']", street);
		setTextField("//input[@name='coAdd_city']", city);
		setTextField("//input[@name='coAdd_state']", state);
		setTextField("//input[@name='coAdd_zip']", zipCode);
		selectByIndex("//input[@name='coAdd_country']", 1);
		setTextField("//input[@name='userInput_mobile']", "7689036417");
		clickOnURL("//input[@value='Save']", "Save");
	}
	

	public void addUsersToGroups(String emailid, String mylnId) {
		
		 String Email_xpath="//span[text()='"+emailid+"']"; 
		

		if (waitOnXpath(Email_xpath, 20))
		{
			sendReport("Add " + emailid + " to the group", " Already Added " + emailid
					+ " email Id", "Pass");
		}

		else {
			clickOnURL("//a[text()='Add New']", "Add New URL");
			setTextField("//input[@name='id']", mylnId);
			setTextField("//input[@name='email']", emailid);
			click_Find_Button();
			clickOnURL("//input[@name='GetThis']", "Mark the check box");
			clickOnURL("//input[@value='Add Users']", "Add users button");

		}

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

	public String GetPageSource(String Url) throws IOException, InterruptedException
	{
		String source = null;
		getRespectiveCourseUrl(Url);
		Thread.sleep(1000);
		source= oASelFW.driver.getPageSource();
		
		return source;
	}
	
}
