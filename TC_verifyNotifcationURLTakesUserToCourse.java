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

public class TC_verifyNotifcationURLTakesUserToCourse {
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
	public void verifyNotifcationURLTakesUserToCourse() {
		try {
			SabaLoginPage sabaLogin = new SabaLoginPage(oASelFW);
			SabaLearningPage learningPage = new SabaLearningPage(oASelFW);

			String sSQL = "select * from OutputData where  bFlag='T'";
			HashMap<String, String> data = oASelFW.readDataFromAccessDB(
					oASelFW.sAutomationPath + "Data\\" + oASelFW.sProjectName
							+ "\\SabaData", sSQL);

			String userName = data.get("sUserEmail");
			String password = data.get("sPassword");
			String Url = data.get("sTestcaseId");

			oASelFW.driver.get(Url);
			// LOGIN DETAILS
			sabaLogin.saba_Login_Details(userName, password);

			Thread.sleep(2000);
			oASelFW.driver
					.get("https://vmwareuat.sabacloud.com/Saba/Web_spf/NA3T1SNB0041/common/leclassdetail/regdw000000000244502/fromLaunchDeeplink");

			learningPage.verifyNotifcationURLTakesUserToCourse();

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
