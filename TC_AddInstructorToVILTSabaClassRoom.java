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
import classes.UtilityMethods;

import com.arsin.ArsinSeleniumAPI;

public class TC_AddInstructorToVILTSabaClassRoom {
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
	public void AddInstructorToVILTSabaClassRoom(){
		try
		{	

			SabaLoginPage sabaLogin 		= new SabaLoginPage(oASelFW);
			SabaHomePage  homepage			= new SabaHomePage(oASelFW);
			SabaAdminPage adminpage			= new SabaAdminPage(oASelFW);
			SabaLearningPage learningPage	= new SabaLearningPage(oASelFW);

			String menuLink     	= oASelFW.getConstValFrmPropertyFile("ADMIN_LINK");
			String LearningOptions	= oASelFW.getConstValFrmPropertyFile("LEARNING");
			String ManageClasses	= oASelFW.getConstValFrmPropertyFile("Manage_Classes");
			String Classes			= oASelFW.getConstValFrmPropertyFile("Classes");
			String NewClass			= oASelFW.getConstValFrmPropertyFile("CreateNewClassInClassesTab");
			String InfoStatus		= oASelFW.getConstValFrmPropertyFile("InfomationEnterInClass");

			String sSQL						= "select * from OutputData where  bFlag='T'";
			HashMap<String, String> data=oASelFW.readDataFromAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData",sSQL);

			String userName			= data.get("sUserEmail");
			String password			= data.get("sPassword");

			String BasedCourse		= "Test Class";
			String DeliveryType		= "Virtual Training";
			String TabActivities	= oASelFW.getConstValFrmPropertyFile("Tab_Activities");

			String DomainName		= "Employee";
			String StartDate		= "30";
			String SessionTime		= "01:00";
			String LocationValue	= "IND-Pune-Maharashtra";
			String Languagespeake	= "English";
			String MinimumLearners	= "1";
			String MaximumLearners	= "1";
			String MaxWaitingList	= "1";
			String Url     			= data.get("sTestcaseId");

			int random = (int) ((Math.random()) * 10000000);
		
			String ClassID			= Integer.toString(random);

			oASelFW.driver.get(Url);

			sabaLogin.saba_Login_Details(userName, password);

			homepage.click_Menu_Items(menuLink);

			adminpage.click_Options_Link(LearningOptions);

			learningPage.click_LearningHome_Links(ManageClasses);

			learningPage.click_ManageLearning_SubLinks(Classes);

			adminpage.clickOnNewLocationLink(NewClass);

			adminpage.createNewClassWithCourseAndDelivery(BasedCourse, DeliveryType);

			adminpage.newDefineClassInfomation(ClassID, DomainName, StartDate, SessionTime, "", LocationValue, Languagespeake, MinimumLearners, MaximumLearners, MaxWaitingList);

			learningPage.SelectSabaVLE();// Select VLE type as Saba

			adminpage.clickFinishButtonInNewClassPage(InfoStatus);

			learningPage.click_TabsHeadingInCourseDetailsPage(TabActivities);

			learningPage.AddInstructorToVILT("Siddharth");

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
