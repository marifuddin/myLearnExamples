package scripts.UAT_BATCases;

import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import classes.SabaAdminPage;
import classes.SabaHomePage;
import classes.SabaLearningPage;
import classes.SabaLoginPage;

import com.arsin.ArsinSeleniumAPI;

public class TC_AdminViewAuditTrailInRosterinVILT {
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
	public void AdminViewAuditTrailInRosterinVILT(){
		try
		{	
			SabaLoginPage sabaLogin 		= new SabaLoginPage(oASelFW);
			SabaHomePage  homepage			= new SabaHomePage(oASelFW);
			SabaAdminPage adminpage			= new SabaAdminPage(oASelFW);
			SabaLearningPage learningPage	= new SabaLearningPage(oASelFW);

			String menuLink     			= oASelFW.getConstValFrmPropertyFile("ADMIN_LINK");
			String LearningOptions			= oASelFW.getConstValFrmPropertyFile("LEARNING");

			String ManageClasses			= oASelFW.getConstValFrmPropertyFile("Manage_Classes");
			String Classes					= oASelFW.getConstValFrmPropertyFile("Classes");
			String TabActivities			= oASelFW.getConstValFrmPropertyFile("Tab_Activities");
			String Tab_RelatedInfo			= oASelFW.getConstValFrmPropertyFile("Tab_RelatedInfo");


			String sSQL						= "select * from OutputData where  bFlag='T'";
			HashMap<String, String> data 	= oASelFW.readDataFromAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData",sSQL);

			String userName			= data.get("sUserEmail");
			String password			= data.get("sPassword");
			String Url     			= data.get("sTestcaseId");

			oASelFW.driver.get(Url);
			//LOGIN DETAILS
			sabaLogin.saba_Login_Details(userName, password);

			
			//CLICK ON ADMIN LINK
			homepage.click_Menu_Items(menuLink);

			//CLICK ON LEARNING LINK
			adminpage.click_Options_Link(LearningOptions);

			learningPage.click_LearningHome_Links(ManageClasses);

			learningPage.click_ManageLearning_SubLinks(Classes);
			
			learningPage.FilterClassUsingDevtypDate("Virtual Training");
			
			learningPage.ClickOnAuditTrail("onRosterPage");
			
			adminpage.switch_to_default_content();

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
