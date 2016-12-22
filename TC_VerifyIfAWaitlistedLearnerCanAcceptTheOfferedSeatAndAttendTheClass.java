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

import com.arsin.ArsinSeleniumAPI;

public class TC_VerifyIfAWaitlistedLearnerCanAcceptTheOfferedSeatAndAttendTheClass {
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
	public void VerifyIfAWaitlistedLearnerCanAcceptTheOfferedSeatAndAttendTheClass(){
		try
		{	
			SabaLoginPage sabaLogin 		= new SabaLoginPage(oASelFW);
			SabaHomePage  homepage			= new SabaHomePage(oASelFW);
			SabaAdminPage adminpage			= new SabaAdminPage(oASelFW);
			SabaLearningPage learningPage	= new SabaLearningPage(oASelFW);
			String People			= oASelFW.getConstValFrmPropertyFile("PEOPLE");
			String ManagePeople		= oASelFW.getConstValFrmPropertyFile("Manage_People");
			String menuLink     	= oASelFW.getConstValFrmPropertyFile("ADMIN_LINK");
			String LearningOptions	= oASelFW.getConstValFrmPropertyFile("LEARNING");
			String ManageClasses	= oASelFW.getConstValFrmPropertyFile("Manage_Classes");
			String Classes			= oASelFW.getConstValFrmPropertyFile("Classes");
			String NewClass			= oASelFW.getConstValFrmPropertyFile("CreateNewClassInClassesTab");
		

			String sSQL					= "Select * from OutputData where  bFlag='T'";
			HashMap<String, String> data=oASelFW.readDataFromAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData",sSQL);

			String userName			= data.get("sUserEmail");
			String password			= data.get("sPassword");
			String Url     			= data.get("sTestcaseId");

			String BasedCourse		= "TEST-COURSE-PD-UAT";
			String DeliveryType		= "Classroom Training";

			String DomainName		= "Employee";
			String StartDate		= "25";
			String SessionTime		= "04:00";
			String LocationValue	= "IND-Pune-Maharashtra";
			String Languagespeake	= "English";
			String MinimumLearners	= "1";
			String MaximumLearners	= "1";
			String MaxWaitingList	= "1";
			String LearnerName		= "Siddharth Pangotra/Raju Kundu/Venketeswaran Neelakandan";
			String UserName			= "spangotra@vmware.com/RAJUKUNDU@VMWARE.COM/VNEELAKANDAN@VMWARE.COM";
			String updatedstatus	= "No seats are currently available in this class, and the waitlist is also full";
			String updatedstatus2	= "No open seats are currently available in this class. Click continue to waitlist the learner or close to cancel the registration.";
			String[] list 			= LearnerName.split("/");
			System.out.println("learner Names: "+ list);

			String[] UnameList		= UserName.split("/");
			System.out.println("User Names: "+ UnameList);

			int random = (int) ((Math.random()) * 10000000);

			String ClassID			= Integer.toString(random);

			oASelFW.driver.get(Url);

			sabaLogin.saba_Login_Details(userName, password);

			homepage.click_Menu_Items(menuLink);
			System.out.println("Text:::::::"+updatedstatus);
			System.out.println("Text2::::::"+updatedstatus2);
			for(String usrname:UnameList)
			{
				learningPage.searchUserInManagePeople(People,ManagePeople);

				learningPage.SearchUserInManagePeople(usrname);

				learningPage.DropLearnerFromRoaster2(BasedCourse);
			}

			adminpage.click_Options_Link(LearningOptions);

			learningPage.click_LearningHome_Links(ManageClasses);

			learningPage.click_ManageLearning_SubLinks(Classes);

			adminpage.clickOnNewLocationLink(NewClass);

			adminpage.createNewClassWithCourseAndDelivery(BasedCourse, DeliveryType);

			adminpage.newDefineClassInfomation(ClassID, DomainName, StartDate, SessionTime, "5", LocationValue, Languagespeake, MinimumLearners, MaximumLearners, MaxWaitingList);

			adminpage.switch_to_main_content();
			learningPage.ClickOnButton("Finish");
			adminpage.switch_to_default_content();

			learningPage.addLearnerIfWaitListisFull(list,updatedstatus,updatedstatus2);

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
