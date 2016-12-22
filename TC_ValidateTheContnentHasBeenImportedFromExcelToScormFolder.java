package scripts.UAT_BATCases;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import classes.SabaAdminPage;
import classes.SabaHomePage;
import classes.SabaLearningPage;
import classes.SabaLoginPage;

import com.arsin.ArsinSeleniumAPI;

public class TC_ValidateTheContnentHasBeenImportedFromExcelToScormFolder {
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
	public void ValidateTheContnentHasBeenImportedFromExcelToScormFolder(){
		try
		{	
			SabaLoginPage sabaLogin 		= new SabaLoginPage(oASelFW);
			SabaHomePage  homepage			= new SabaHomePage(oASelFW);
			SabaAdminPage adminpage			= new SabaAdminPage(oASelFW);
			SabaLearningPage learningPage	= new SabaLearningPage(oASelFW);

			String menuLink     			= oASelFW.getConstValFrmPropertyFile("ADMIN_LINK");
			String LearningOptions			= oASelFW.getConstValFrmPropertyFile("LEARNING");
			String ManageContent			= oASelFW.getConstValFrmPropertyFile("ManageContent");


			String sSQL						= "select * from OutputData where  bFlag='T'";
			HashMap<String, String> data	= oASelFW.readDataFromAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData",sSQL);

			String userName					= data.get("sUserEmail");
			String password					= data.get("sPassword");
			String Url     					= data.get("sTestcaseId");
			
			String contentFolder  			= "SCORM";
			String contentNameValid			= "2016 Anti-Bribery: Doing Business with Integrity (FCPA)/Fundamentals of Application Security";

			String[] val = contentNameValid.split("/");

			oASelFW.driver.get(Url);

			sabaLogin.saba_Login_Details(userName, password);

			homepage.click_Menu_Items(menuLink);

			adminpage.click_Options_Link(LearningOptions);

			adminpage.click_HrHome_Links(ManageContent);

			System.out.println("content name length *********"+val.length);

			learningPage.ValidateProductionRepositoryContentDetails(contentFolder, 1);

			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			oASelFW.effecta("verifyElementPresent","//span[@class='sbAttributeValueFileUpload']","ZIP File");	
			oASelFW.driver.switchTo().defaultContent();

			Thread.sleep(3000);

			sabaLogin.saba_Logout();

			System.out.println("executed");
			if(oASelFW.sResultFlag.contains("Fail")){
				oASelFW.testNgFail();
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	@AfterClass(enabled=true)
	public void oneTearDown() throws IOException
	{
		oASelFW.stopSelenium();
	}

}
