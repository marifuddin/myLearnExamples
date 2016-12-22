package scripts.EndToEndCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import classes.SabaAdminPage;
import classes.SabaHomePage;
import classes.SabaLearningPage;
import classes.SabaLoginPage;
import classes.SabaMePage;

import com.arsin.ArsinSeleniumAPI;

import classes.UtilityMethods;
public class TC_EnrollAndLaunchVILTAsLearner {
	ArsinSeleniumAPI oASelFW = null;

	@Parameters({ "prjName", "testEnvironment","instanceName","sauceUser","moduleName","testSetName"})

	@BeforeClass
	public void oneTimeSetUp(String prjName,String testEnvironment,String instanceName,String sauceUser,String moduleName,String testSetName) throws InterruptedException
	{
		String[] environment=new ArsinSeleniumAPI().getEnvironment(testEnvironment,this.getClass().getName());
		String os=environment[0];String browser=environment[1];String testCasename=this.getClass().getSimpleName();
		oASelFW = new ArsinSeleniumAPI(prjName,testCasename,browser,os,instanceName,sauceUser,moduleName,testSetName);
		oASelFW.startSelenium();	
	}
	@Test
	public void EnrollAndLaunchVILTAsLearner(){
		try
		{	
			SabaLoginPage 			sabaLogin	 = new SabaLoginPage(oASelFW);
			SabaMePage				mepage	 	 = new SabaMePage(oASelFW);

			String sSQL						= "select * from OutputData where  bFlag='T'";
			HashMap<String, String> data	= oASelFW.readDataFromAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData",sSQL);
			String Url     			= data.get("sTestcaseId");

			oASelFW.driver.get(Url);

			Thread.sleep(5000);

			sabaLogin.saba_Login_Details("spangotra@vmware.com", "VMware");

			mepage.searchContentLookUp("0000132893");

			mepage.searchResultsDisplayLink("Test-PD-Tuesday-VILT", "", "");

			mepage.clickonSpecificVILT_ClassToLaunch("0000132893","Enroll");

			mepage.dropRegistrationClassByClickingDropLink();

			sabaLogin.saba_Logout();

			System.out.println("executed");
			if(oASelFW.sResultFlag.contains("Fail")){
				oASelFW.testNgFail();
			}
		}catch (Exception e) {
			e.printStackTrace();
			oASelFW.reportStepDtlsWithScreenshot (e.getMessage(),e.getMessage(),"Fail");
		}
	}

	@AfterClass(enabled=true)
	public void oneTearDown() throws IOException
	{
		oASelFW.stopSelenium();
	}
}
