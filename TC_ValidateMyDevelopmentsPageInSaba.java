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

public class TC_ValidateMyDevelopmentsPageInSaba {
	ArsinSeleniumAPI oASelFW = null;

	@Parameters({"prjName", "testEnvironment","instanceName","sauceUser","moduleName","testSetName"})

	@BeforeClass
	public void oneTimeSetUp(String prjName,String testEnvironment,String instanceName,String sauceUser,String moduleName,String testSetName) throws InterruptedException
	{
		String[] environment=new ArsinSeleniumAPI().getEnvironment(testEnvironment,this.getClass().getName());
		String os=environment[0];String browser=environment[1];String testCasename=this.getClass().getSimpleName();
		oASelFW = new ArsinSeleniumAPI(prjName,testCasename,browser,os,instanceName,sauceUser,moduleName,testSetName);
		oASelFW.startSelenium();	
	}
	

	@Test
	public void ValidateMyDevelopmentsPageInSaba() throws InterruptedException{
		try
		{
			SabaLoginPage 			sabaLogin = new SabaLoginPage(oASelFW);
			SabaHomePage  			homepage  = new SabaHomePage(oASelFW);
			SabaMePage				mepage	  = new SabaMePage(oASelFW);

			String sSQL						= "Select * from OutputData where  bFlag='T'";
			HashMap<String, String> data	=oASelFW.readDataFromAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData",sSQL);

			String 	Career_DEVELOPEMENT   	= oASelFW.getConstValFrmPropertyFile("Career_DEVELOPEMENT");
			String Url     			= data.get("sTestcaseId");
			
			oASelFW.driver.get(Url);

			Thread.sleep(3000);

			sabaLogin.saba_Login_Details("spangotra@vmware.com", "VMware");

			homepage.click_Menu_Items(Career_DEVELOPEMENT);
			
			mepage.myDevelopmentPageContent("Global Field Readiness","People Development","Performance Acceleration","Talent Acceleration","Transformation Toolkits","Workday","Leadership Summit","Good Gigs");

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
