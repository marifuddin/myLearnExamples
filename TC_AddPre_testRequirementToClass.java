package scripts.UAT_BATCases;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import classes.SabaAdminPage;
import classes.SabaHomePage;
import classes.SabaLearningPage;
import classes.SabaLoginPage;

import com.arsin.ArsinSeleniumAPI;

public class TC_AddPre_testRequirementToClass {
	private String ClassID = null;
	ArsinSeleniumAPI oASelFW = null;
	private String courseTitle;

	@Parameters({ "prjName", "testEnvironment", "instanceName", "sauceUser",
			"moduleName", "testSetName" })
	@BeforeClass
	public void oneTimeSetUp(String prjName, String testEnvironment,
			String instanceName, String sauceUser, String moduleName,
			String testSetName) throws InterruptedException {
		String[] environment = new ArsinSeleniumAPI().getEnvironment(
				testEnvironment, this.getClass().getName());
		String os = environment[0];
		String browser = environment[1];
		String testCasename = this.getClass().getSimpleName();
		oASelFW = new ArsinSeleniumAPI(prjName, testCasename, browser, os,
				instanceName, sauceUser, moduleName, testSetName);
		oASelFW.startSelenium();
	}

	@Test
	public void AddPre_testRequirementToClass() {
		try {
			SabaLoginPage sabaLogin = new SabaLoginPage(oASelFW);
			SabaHomePage homepage = new SabaHomePage(oASelFW);
			SabaAdminPage adminpage = new SabaAdminPage(oASelFW);
			SabaLearningPage learningPage = new SabaLearningPage(oASelFW);

			String menuLink = oASelFW.getConstValFrmPropertyFile("ADMIN_LINK");
			String LearningOptions = oASelFW
					.getConstValFrmPropertyFile("LEARNING");

			String ManageLearningCatalog = oASelFW
					.getConstValFrmPropertyFile("MANAGE_LEARNING");
			String New_CatalogItem = oASelFW
					.getConstValFrmPropertyFile("New_CatalogItem");

			String NewCatalogAdvanced = oASelFW
					.getConstValFrmPropertyFile("NewCatalogAdvanced");
			String CatalogNewCourse = oASelFW
					.getConstValFrmPropertyFile("CatalogNewCourse");
			String DeliveryTypes = oASelFW
					.getConstValFrmPropertyFile("DeliveryTypes");

			String sSQL = "select * from OutputData where  bFlag='T'";
			HashMap<String, String> data = oASelFW.readDataFromAccessDB(
					oASelFW.sAutomationPath + "Data\\" + oASelFW.sProjectName
							+ "\\SabaData", sSQL);

			String userName = data.get("sUserEmail");
			String password = data.get("sPassword");
			String Url = data.get("sTestcaseId");
			String DeliveryType = "Self-Paced Training";
			String DomainName = "Employee";

			int random = (int) ((Math.random()) * 10000000);
			courseTitle = "test" + random;

			String[] list = DeliveryType.split("/");
			System.out.println("Number of delivery types***********"
					+ Arrays.toString(list));

			oASelFW.driver.get(Url);
			// LOGIN DETAILS
			sabaLogin.saba_Login_Details(userName, password);

			// CLICK ON ADMIN LINK
			homepage.click_Menu_Items(menuLink);


			LinkedList<String> ll = new LinkedList<String>();
			ll.add("Pre-Test");
			ll.add("Post-Test");
			for (String testType : ll) {
				adminpage.click_Options_Link(LearningOptions);

				learningPage.Switchframe();
				learningPage.ClickOnElement(
						"//a[text()='Manage Learning Catalog']",
						"Manage Learning Catalog");
				learningPage.switch_to_default_content();

				learningPage.searchCourse("test3335522");
				learningPage.addPre_TestRequirement(testType);//

			}
			learningPage.switch_to_default_content();

			sabaLogin.saba_Logout();

			System.out.println("executed");
			if (oASelFW.sResultFlag.contains("Fail")) {
				oASelFW.testNgFail();
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			oASelFW.reportStepDtlsWithScreenshot(e.getMessage(),
					e.getMessage(), "Fail");
		}
	}

	@AfterClass(enabled = true)
	public void oneTearDown() throws IOException {
		oASelFW.stopSelenium();
	}
}
