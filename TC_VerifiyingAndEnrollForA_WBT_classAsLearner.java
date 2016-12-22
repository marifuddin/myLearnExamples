package scripts.UAT_BATCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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
import classes.SabaMePage;
import classes.UtilityMethods;

import com.arsin.ArsinSeleniumAPI;

public class TC_VerifiyingAndEnrollForA_WBT_classAsLearner {
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
	public void CreateACourseInSabaAndAddAll_4_DeliveryTypes(){
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
				String DeliveryTypes			= oASelFW.getConstValFrmPropertyFile("DeliveryTypes");


				String sSQL					= "select * from OutputData where  bFlag='T'";
				HashMap<String, String> data=oASelFW.readDataFromAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData",sSQL);

				String userName			= data.get("sUserEmail");
				String password			= data.get("sPassword");
				String Url     			= data.get("sTestcaseId");
				String DeliveryType		= "Self-Paced Training";
				String DomainName		= "Employee";
				
				
				int random = (int) ((Math.random()) * 10000000);
				courseTitle = "test"+random;
				
				String[] list = DeliveryType.split("/");
				System.out.println("Number of delivery types***********"+list);
				
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
				
				adminpage.saveLocationInfomationData();

				learningPage.click_TabsHeadingInCourseDetailsPage(DeliveryTypes);
				
				System.out.println("number of delivery types "+list.length);
				for(int j= 0; j<list.length; j++)
				{
					Thread.sleep(2000);
					learningPage.addDeliveryTypes();

					String window[] = oASelFW.effectaArray("getAllWindowNames");
					oASelFW.effecta("selectWindow",window[1]);
					Thread.sleep(1000);
					if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='Next']")))
					{
						System.out.println("in select domain window");
						Thread.sleep(2000);
						oASelFW.effecta("clickAndWait","//span[text()='"+list[j]+"']/..//input", list[j],"Pass");
					}
						
					oASelFW.effecta("clickAndWait","//span[text()='Next']", "Next","Pass");
					Random rand = new Random();
					long numb = Math.round(Math.random() * 100000);
					String run = String.valueOf(numb);
					System.out.println(run);
					
					if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='ID*']/../../following-sibling::td//input")))
					{
						oASelFW.effecta("type","//span[text()='ID*']/../../following-sibling::td//input",run ,"ID+");
						oASelFW.effecta("type","//span[contains(text(),'Duration')]/../following-sibling::td//input","02:00","Duration HH:MM");
						oASelFW.effecta("clickAndWait","//span[text()='Finish']", "Finish","Pass");
						oASelFW.effecta("selectWindow",window[0]);
					}
					else
					{
					oASelFW.effecta("type","//span[contains(text(),'Duration')]/../following-sibling::td//input","02:00","Duration HH:MM");


					oASelFW.effecta("clickAndWait","//span[text()='Finish']", "Finish","Pass");
					
					oASelFW.effecta("selectWindow",window[0]);
					}

				}
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
	
	@Test(dependsOnMethods="CreateACourseInSabaAndAddAll_4_DeliveryTypes")
	public void EnrollForA_WBT_classAsLearner(){
		try{
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
			String SaveAndPublish 	= oASelFW.getConstantValue("SaveAndPublish");
			String TabActivities	= oASelFW.getConstValFrmPropertyFile("Tab_Activities");
			String Tab_Main			= oASelFW.getConstantValue("Tab_Main");

			String sSQL					= "Select * from OutputData where  bFlag='T'";
			HashMap<String, String> data=oASelFW.readDataFromAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData",sSQL);

		
			String userName			= data.get("sUserEmail");
			String password			= data.get("sPassword");

			String DeliveryType		= "Self-Paced Training";
			String DomainName		= "Employee";
			
			String Languagespeake	= "English";

			String Url     			= data.get("sTestcaseId");
			String type				= "Adobe Connect";
			String LearnerName		= "Siddharth Pangotra";

			ArrayList<String> list = new ArrayList<String>();
				list.add(type);

			int random = (int) ((Math.random()) * 10000000);
			Thread.sleep(3000);
			ClassID			= Integer.toString(random);

			oASelFW.driver.get(Url);

			sabaLogin.saba_Login_Details(userName, password);

			homepage.click_Menu_Items(menuLink);

			adminpage.click_Options_Link(LearningOptions);

			learningPage.click_LearningHome_Links(ManageClasses);

			learningPage.click_ManageLearning_SubLinks(Classes);

			adminpage.clickOnNewLocationLink(NewClass);

			adminpage.createNewClassWithCourseAndDelivery(courseTitle, DeliveryType);

			adminpage.CreateNewWBT(ClassID, DomainName, Languagespeake, "03:00");


			adminpage.clickFinishButtonInNewClassPage(InfoStatus);
			
			
			learningPage.click_TabsHeadingInCourseDetailsPage(TabActivities);

			learningPage.AddNewContent(type);

			learningPage.Switchframe();

			learningPage.ClickOnButton(SaveAndPublish);

			Thread.sleep(5000);

			learningPage.ClickOnElement("//a[text()='"+Tab_Main.trim()+"']", ""+Tab_Main+" Tab");

			learningPage.AddLearner(LearnerName);

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
	@Test(dependsOnMethods="EnrollForA_WBT_classAsLearner")
	public void loginAsLearnerToCompleteTheCourse() throws InterruptedException{
		try
		{
			SabaLoginPage 			sabaLogin = new SabaLoginPage(oASelFW);
			SabaMePage				mepage	  = new SabaMePage(oASelFW);

			String sSQL						= "Select * from OutputData where  bFlag='T'";
			HashMap<String, String> data	= oASelFW.readDataFromAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData",sSQL);
			String Url     			= data.get("sTestcaseId");
		
			Thread.sleep(3000);
			oASelFW.driver.get(Url);
			Thread.sleep(5000);

			sabaLogin.saba_Login_Details("spangotra@vmware.com", "VMware");

			mepage.searchContentLookUp(courseTitle);
			
			mepage.searchResultsDisplayLink(courseTitle, "", "");

			mepage.clickLaunchOnClassToLaunchAfterEnrolling();
			
			mepage.printCertificationWithSpecificClassID(ClassID, "Print Certificate");
			
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
