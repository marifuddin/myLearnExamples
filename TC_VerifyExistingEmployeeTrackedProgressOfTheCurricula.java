package scripts.UAT_BATCases;
import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import classes.SabaHomePage;
import classes.SabaLoginPage;
import classes.SabaMePage;

import com.arsin.ArsinSeleniumAPI;

public class TC_VerifyExistingEmployeeTrackedProgressOfTheCurricula {
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
	public void VerifyExistingEmployeeTrackedProgressOfTheCurricula(){
		try
		{	

			SabaLoginPage sabaLogin 		= new SabaLoginPage(oASelFW);
			SabaHomePage  homepage			= new SabaHomePage(oASelFW);
			SabaMePage		mepage			= new SabaMePage(oASelFW);
			
			
			String meLink     				= oASelFW.getConstValFrmPropertyFile("ME_LINK");
			
			String sSQL						= "select * from OutputData where  bFlag='T'";
			HashMap<String, String> data=oASelFW.readDataFromAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData",sSQL);

			String userName			= data.get("sUserEmail");
			String password			= data.get("sPassword");
			String Url     			= data.get("sTestcaseId");

			oASelFW.driver.get(Url);

			//LOGIN DETAILS
			sabaLogin.saba_Login_Details(userName, password);

			//CLICK ON ADMIN LINK
			homepage.click_Menu_Items(meLink);
			
			//CLICK ON Completed Learning LINK
			mepage.click_RightPlane_Link("Plan");
			
			mepage.clickonShowFilterInPlanPage("Curriculum");
			
			mepage.clickonSearchTitleLink("VMware Strategy Enablement");
			
			String progress = oASelFW.effecta("getText","//b[contains(text(),'This path')]/ancestor::td/../descendant::label[contains(text(),'%')]","Progress");
			
			oASelFW.effecta("verifyElementPresent","//b[contains(text(),'This path')]/ancestor::td/../descendant::label[contains(text(),'%')]","Progress Percentage "+progress);
			
			mepage.printCertificationButton();	
			
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
