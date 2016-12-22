package scripts.UAT_BATCases;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import classes.SabaAdminPage;
import classes.SabaHomePage;
import classes.SabaLearningPage;
import classes.SabaLoginPage;
import classes.UtilityMethods;

import com.arsin.ArsinSeleniumAPI;

public class TC_CreatingCurriculumAndAddPathAddModuleAsMandatatoryAddWBTCourses {
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
	public void CreatingCurriculumAndAddPathAddModuleAsMandatatoryAddWBTCourses(){
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

				String NewLearningPath		= oASelFW.getConstValFrmPropertyFile("NewLearningPath");
				String LearningPathCurricula	= oASelFW.getConstValFrmPropertyFile("LearningPathCurricula");

				String sSQL						= "select * from OutputData where  bFlag='T'";
				HashMap<String, String> data=oASelFW.readDataFromAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData",sSQL);

				String userName			= data.get("sUserEmail");
				String password			= data.get("sPassword");
				String Url     			= data.get("sTestcaseId");
				String moduleName1		= "Advanced Core Java/Advanced Python Programming";
				String moduleName2		= "Advanced Core Java/Advanced Python Programming";
				String status			= "Draft";
				String date				= "19";
				String DomainName		= "Employee";

				int random = (int) ((Math.random()) * 10000000);
				String CurrName = "test"+random;


				ArrayList<String> list3 = new ArrayList<String>();
				for(String list1:moduleName1.split("/"))
				{
					list3.add(list1);
				}

				ArrayList<String> list4 = new ArrayList<String>();
				for(String list2:moduleName2.split("/"))
				{
					list4.add(list2);
				}
				System.out.println(list3);

				System.out.println(list4);

				oASelFW.driver.get(Url);

				//LOGIN DETAILS
				sabaLogin.saba_Login_Details(userName, password);

				//CLICK ON ADMIN LINK
				homepage.click_Menu_Items(menuLink);

				//CLICK ON LEARNING LINK
				adminpage.click_Options_Link(LearningOptions);

				System.out.println(ManageLearningCatalog+"+++++++++++++++++"+New_CatalogItem);

				learningPage.topLearningActivitiesLinks(ManageLearningCatalog, New_CatalogItem);

				System.out.println(NewLearningPath+"+++++++++++++++++"+LearningPathCurricula);

				learningPage.newCatalogItemsPage(NewLearningPath, LearningPathCurricula);

				learningPage.curriculamDetailsInformationPage(CurrName, "", DomainName, date, status);

				learningPage.addPathInCurriculamPage();

				for(int k=0; k<2; k++)
				{
					if(k==0)
					{
						learningPage.defineCompletePathWithNewModule("1","2","Yes",list3);
						System.out.println(list3.size()+"list3");

					}else if(k==1)
					{
						learningPage.defineCompletePathWithNewModule("1","2","Yes",list4);
						System.out.println(list4.size()+"list4");
					}
				}
				learningPage.saveAndFinishModuleButton();

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
