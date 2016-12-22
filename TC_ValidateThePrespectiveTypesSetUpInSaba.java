package scripts.UAT_BATCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import classes.SabaHomePage;
import classes.SabaLearningPage;
import classes.SabaLoginPage;

import com.arsin.ArsinSeleniumAPI;

public class TC_ValidateThePrespectiveTypesSetUpInSaba {
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
	public void ValidateThePrespectiveTypesSetUpInSaba(){
		try
		{	
			SabaLoginPage sabaLogin 		= new SabaLoginPage(oASelFW);
			SabaHomePage  homepage			= new SabaHomePage(oASelFW);

			SabaLearningPage learningPage	= new SabaLearningPage(oASelFW);
			String People			= oASelFW.getConstValFrmPropertyFile("PEOPLE");
			String RulesEngine		= oASelFW.getConstValFrmPropertyFile("RulesEngine");
			String menuLink     	= oASelFW.getConstValFrmPropertyFile("ADMIN_LINK");

			String sSQL						= "select * from OutputData where  bFlag='T'";
			HashMap<String, String> data=oASelFW.readDataFromAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData",sSQL);

			String userName			= data.get("sUserEmail");
			String password			= data.get("sPassword");
			String Url     			= data.get("sTestcaseId");
			String moduleName1		= "2016 Compliance Training All US Public Sector Employees/2016 Compliance Training New Hire Sales and Support/2016  Compliance Training VMware BCG for New Hires/Assignment of Training to ‘Nonexempt employees’";

			ArrayList<String> list3 = new ArrayList<String>();
			for(String list1:moduleName1.split("/"))
			{
				list3.add(list1);
			}

			oASelFW.driver.get(Url);

			sabaLogin.saba_Login_Details(userName, password);

			homepage.click_Menu_Items(menuLink);

			learningPage.searchUserInManagePeople(People,RulesEngine);

			learningPage.validatePrescriptiveRulesTitles(list3);

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
