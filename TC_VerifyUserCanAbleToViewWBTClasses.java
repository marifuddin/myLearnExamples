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

public class TC_VerifyUserCanAbleToViewWBTClasses {
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
	public void VerifyUserCanAbleToViewWBTClasses(){
		try
		{
			SabaLoginPage sabaLogin 		= new SabaLoginPage(oASelFW);
			SabaHomePage  homepage			= new SabaHomePage(oASelFW);
			SabaAdminPage adminpage			= new SabaAdminPage(oASelFW);
			SabaLearningPage learningPage	= new SabaLearningPage(oASelFW);

			String menuLink     			= oASelFW.getConstValFrmPropertyFile("ADMIN_LINK");
			String Classes					= oASelFW.getConstValFrmPropertyFile("Classes");
			String Manage_Classes			= oASelFW.getConstValFrmPropertyFile("Manage_Classes");
			String LEARNING_ADMIN_LINK		= oASelFW.getConstValFrmPropertyFile("LEARNING_ADMIN_LINK");


			String sSQL						= "select * from OutputData where  bFlag='T'";
			HashMap<String, String> data	= oASelFW.readDataFromAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData",sSQL);
			
			String userName					= data.get("sUserEmail");
			String password					= data.get("sPassword");
			String Url     					= data.get("sTestcaseId");
			
			oASelFW.driver.get(Url);

			//LOGIN DETAILS
			sabaLogin.saba_Login_Details(userName, password);

			//CLICK ON ADMIN LINK
			homepage.click_Menu_Items(menuLink);

			//click on learning admin
			adminpage.click_Admin_Links(LEARNING_ADMIN_LINK);

			//click on the manage classes
			adminpage.click_HrHome_Links(Manage_Classes);

			//click on classes
			learningPage.click_ManageLearning_SubLinks(Classes);
			
			//entering the class name
			//learningPage.startDateAndAvailableDateWithDeliveryType("Self-Paced Training");
			
			adminpage.searchManage_JobName("Multiple Check");
			
			//logout
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
