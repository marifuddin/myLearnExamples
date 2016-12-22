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

public class TC_AdminIsAbleToTagTheCourseAsFeaturedCourse {
	private String ClassID = null;
	ArsinSeleniumAPI oASelFW = null;
	private String courseTitle;

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
	public void AdminIsAbleToTagTheCourseAsFeaturedCourse(){
		try
		{
				SabaLoginPage sabaLogin 		= new SabaLoginPage(oASelFW);
				SabaHomePage  homepage			= new SabaHomePage(oASelFW);
				SabaAdminPage adminpage			= new SabaAdminPage(oASelFW);
				SabaLearningPage learningPage	= new SabaLearningPage(oASelFW);

				String menuLink     			= oASelFW.getConstValFrmPropertyFile("ADMIN_LINK");
				String LearningOptions			= oASelFW.getConstValFrmPropertyFile("LEARNING");

				String ManageLearningCatalog	= oASelFW.getConstValFrmPropertyFile("MANAGE_LEARNING");
				String New_CatalogItem			= oASelFW.getConstValFrmPropertyFile("New_CatalogItem");

				String NewCatalogAdvanced		= oASelFW.getConstValFrmPropertyFile("NewCatalogAdvanced");
				String CatalogNewCourse			= oASelFW.getConstValFrmPropertyFile("CatalogNewCourse");
				

				String sSQL					= "select * from OutputData where  bFlag='T'";
				HashMap<String, String> data=oASelFW.readDataFromAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData",sSQL);

				String userName			= data.get("sUserEmail");
				String password			= data.get("sPassword");
				String Url     			= data.get("sTestcaseId");
				String DomainName		= "Employee";
				
				
				int random = (int) ((Math.random()) * 10000000);
				courseTitle = "test"+random;
				
				oASelFW.driver.get(Url);
				//LOGIN DETAILS
				sabaLogin.saba_Login_Details(userName, password);

				//CLICK ON ADMIN LINK
				homepage.click_Menu_Items(menuLink);

				//CLICK ON LEARNING LINK
				adminpage.click_Options_Link(LearningOptions);

				System.out.println(ManageLearningCatalog+"+++++++++++++++++"+New_CatalogItem);

				learningPage.topLearningActivitiesLinks(ManageLearningCatalog, New_CatalogItem);

				System.out.println(NewCatalogAdvanced+"+++++++++++++++++"+CatalogNewCourse);

				learningPage.newCatalogItemsPage(NewCatalogAdvanced, CatalogNewCourse);

				learningPage.CreateNewCourseDetails_Page(courseTitle, DomainName, "", "");
				
				learningPage.EnterDiscontinuedDate();
				
				learningPage.switch_to_main_content();
				Actions act = new Actions(oASelFW.driver);
				act.moveToElement(oASelFW.driver.findElement(By.xpath("//label[text()='Featured']/../../../following-sibling::td//input"))).build().perform();
				act.click().build().perform();
				Thread.sleep(3000);
				oASelFW.effecta("sendReport","Selecting on the Feature CheckBox ","Successfully Selected Featured CheckBox field","Pass");
				learningPage.switch_to_default_content();
				
				adminpage.saveLocationInfomationData();

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
