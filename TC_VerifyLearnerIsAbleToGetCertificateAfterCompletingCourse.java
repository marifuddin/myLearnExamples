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

public class TC_VerifyLearnerIsAbleToGetCertificateAfterCompletingCourse {
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
	public void VerifyLearnerIsAbleToGetCertificateAfterCompletingCourse(){
		try
		{	
			SabaLoginPage sabaLogin = new SabaLoginPage(oASelFW);
			SabaHomePage  homepage	= new SabaHomePage(oASelFW);
			SabaMePage		mepage	= new SabaMePage(oASelFW);
			
			String ME_LINK     			= oASelFW.getConstValFrmPropertyFile("ME_LINK");
			String sSQL					= "select * from OutputData where  bFlag='T'";
			HashMap<String, String> data=oASelFW.readDataFromAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData",sSQL);

			String userName			= "spangotra@vmware.com";
			String password			= "VMware";
			String Url     			= data.get("sTestcaseId");
		
			oASelFW.driver.get(Url);

			//LOGIN as a learner
			sabaLogin.saba_Login_Details(userName, password);
			
			//CLICK ON Me LINK
			homepage.click_Menu_Items(ME_LINK);

			//CLICK ON Completed Learning LINK
			mepage.click_RightPlane_Link("Completed Learning");
			
			//click on the title courses link
			mepage.clickOnTitleLink();			
			
			//click on the print certification tab
			mepage.printCertificationButton();	

			Thread.sleep(2000);
			
			sabaLogin.saba_Logout();

		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterClass(enabled=true)
	public void oneTearDown() throws IOException
	{
		oASelFW.stopSelenium();
	}
	
}
