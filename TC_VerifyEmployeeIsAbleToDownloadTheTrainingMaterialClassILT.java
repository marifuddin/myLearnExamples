package scripts.UAT_BATCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
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
public class TC_VerifyEmployeeIsAbleToDownloadTheTrainingMaterialClassILT {
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
	public void VerifyEmployeeIsAbleToDownloadTheTrainingMaterialClassILT(){
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

			mepage.searchContentLookUp("TEST-COURSE-PD-UAT");

			mepage.searchResultsDisplayLink("TEST-COURSE-PD-UAT", "", "");

			Thread.sleep(4000);

			Actions act = new Actions(oASelFW.driver);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Attachments']"))).build().perform();
			act.click().build().perform();
			oASelFW.effecta("sendReport","Clicking on Attachment Link","Successfully Clicked on Attachment Link","Pass");

			Thread.sleep(4000);

			act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[text()='test_txt']"))).build().perform();
			act.click().build().perform();
			oASelFW.effecta("sendReport","Clicking on Attach File","Successfully Clicked on Attach File","Pass");
			Thread.sleep(2000);
			oASelFW.driver.navigate().back();
			Thread.sleep(3000);

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
