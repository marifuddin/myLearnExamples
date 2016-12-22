package scripts.UAT_BATCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
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
import classes.SabaMePage;
import classes.UtilityMethods;

import com.arsin.ArsinSeleniumAPI;

public class TC_VerifyAdminIsAbleToTagACourseToACategory {
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
	public void VerifyAdminIsAbleToTagACourseToACategory(){
		try
		{
			SabaLoginPage sabaLogin 		= new SabaLoginPage(oASelFW);
			SabaHomePage  homepage			= new SabaHomePage(oASelFW);
			SabaAdminPage adminpage			= new SabaAdminPage(oASelFW);
			SabaLearningPage learningPage	= new SabaLearningPage(oASelFW);

			String menuLink     			= oASelFW.getConstValFrmPropertyFile("ADMIN_LINK");
			String LearningOptions			= oASelFW.getConstValFrmPropertyFile("LEARNING");
			String ManageLearningCatalog	= oASelFW.getConstValFrmPropertyFile("MANAGE_LEARNING");

			String sSQL					= "select * from OutputData where  bFlag='T'";
			HashMap<String, String> data=oASelFW.readDataFromAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData",sSQL);

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

			adminpage.click_HrHome_Links(ManageLearningCatalog);

			adminpage.searchManage_JobName("test0123");

			adminpage.clickOnTheDisplayedCatalogSearchActionsLinks("Advanced Edit");

			learningPage.click_TabsHeadingInCourseDetailsPage("Related Info");

			learningPage.switch_to_main_content();	
			Actions act = new Actions(oASelFW.driver);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[text()='Add Category']"))).build().perform();
			act.click().build().perform();

			Thread.sleep(3000);
			String window[] = oASelFW.effectaArray("getAllWindowNames");
			oASelFW.effecta("selectWindow",window[1]);
			Thread.sleep(2000);
			System.out.println("in select domain window");
			oASelFW.effecta("type","//label[contains(text(),'Name')]/../../following-sibling::td//input", "Engineering","Field Text");
			oASelFW.effecta("clickAndWait","//a[@title='Search']","Search","Pass");
			Thread.sleep(2000);
			oASelFW.effecta("clickAndWait","//b[text()='Engineering']/../../../descendant::td[2]//input", "Engineering","Pass");

			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Select']"))).build().perform();
			act.click().build().perform();
			Thread.sleep(2000);
			oASelFW.effecta("selectWindow",window[0]);
			
			learningPage.switch_to_main_content();
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Engineering']/../following-sibling::td[2]//span//a"))).build().perform();
			act.click().build().perform();
			Thread.sleep(2000);
			learningPage.acceptAlert();
			Thread.sleep(3000);
			learningPage.switch_to_default_content();


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
