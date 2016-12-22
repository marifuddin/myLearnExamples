
package classes;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.python.modules.thread.thread;

import com.arsin.ArsinSeleniumAPI;
import com.thoughtworks.selenium.webdriven.commands.KeyEvent;

import classes.SabaLearningPage;

public class SabaMePage extends SabaLearningPage {

	ArsinSeleniumAPI oASelFW;
	String menu_frame = "//frame[@name='left']";


	public SabaMePage() {
	}

	public SabaMePage(ArsinSeleniumAPI oASelFW) {
		this.oASelFW=oASelFW;
	}
	/* @Author - Arifuddin 
	 * Description - This method will help in Searching the content
	 * 
	 */

	public void searchContentLookUp(String passText)
	{
		try {

			Actions act = new Actions(oASelFW.driver);

			oASelFW.effecta("type","//input[@id='search-input']",passText,"Title Name");
			Thread.sleep(3000);
			oASelFW.effecta("sendReport","Searching with the required value","Successfully type the Value in Search Field","Pass");

			act.moveToElement(oASelFW.driver.findElement(By.xpath("//input[@id='search-input']/../following-sibling::li//img"))).build().perform();
			act.click().build().perform();
			Thread.sleep(5000);
			oASelFW.effecta("sendReport","Clicking on the Look Up link","Successfully Clicked on the Look Up link","Pass");


		} catch (Exception e) {
			e.printStackTrace();
		}	

	}
	public void searchResultsDisplayLink(String link, String register,String completecourse)
	{
		try {

			Actions act = new Actions(oASelFW.driver);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='"+link.trim()+"']"))).build().perform();
			act.click().build().perform();
			Thread.sleep(5000);
			oASelFW.effecta("verifyElementPresent","//span[text()='"+link.trim()+"']",link);
			Thread.sleep(2000);
			oASelFW.effecta("sendReportWithOutExit","Clicking on the Available link","Successfully clicked "+link+" ","Pass");
			Thread.sleep(6000);

			if(register.equals("Register"))
			{
				act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[contains(text(),'"+register.trim()+"')]"))).build().perform();
				act.click().build().perform();
				Thread.sleep(2000);
				oASelFW.effecta("sendReportWithOutExit","Clicking on the Register Button","Successfully clicked on the Register Button And Navigate to Course Title Page","Pass");
				Thread.sleep(7000);

				oASelFW.effecta("verifyElementPresent","//span[text()='"+completecourse.trim()+"']",completecourse);
				Thread.sleep(2000);
			}
			else if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='"+link.trim()+"']")))
			{
				oASelFW.effecta("verifyElementPresent","//span[text()='"+link.trim()+"']","Course Title");
				act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='"+link.trim()+"']"))).build().perform();
				Thread.sleep(3000);
				act.click().build().perform();
				Thread.sleep(8000);
				oASelFW.effecta("sendReportWithOutExit","Validating the Course Title "+link.trim()+" ","Successfully Validate the Course "+link+" ","Pass");
			}
			else{				
			}


		} catch (Exception e) {
			e.printStackTrace();
		}	

	}
	/*
	 * @author - arifuddin mohd
	 * Description - This method will clicked on the enroll button from the main page for learner
	 * [Enroll[1], Enroll[2],   ]
	 */
	public void clickOnEnrollThruLearnerCredentials()
	{
		String siteId[]=new String[2];
		int j=0;

		try {
			Actions act = new Actions(oASelFW.driver);

			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//label[contains(text(),'Suggested')]/../descendant::span[text()='Enroll'][1]")))
			{
				act.moveToElement(oASelFW.driver.findElement(By.xpath("//label[contains(text(),'Suggested')]/../descendant::span[text()='Enroll'][1]"))).build().perform();
				act.doubleClick().build().perform();
				Thread.sleep(4000);			
				oASelFW.effecta("sendReportWithOutExit","Clicking on the Enroll Button","Successfully Clicked on the Enroll Button","Pass");

				Thread.sleep(20000);
				Set<String> window=oASelFW.driver.getWindowHandles();
				for(String aSiteId: window) {
					siteId[j] = aSiteId;
					j++;
				}
				Thread.sleep(5000);
				oASelFW.driver.switchTo().window(siteId[1]);
				Thread.sleep(5000);
				oASelFW.effecta("sendReportWithOutExit","Navigating to the Flash Player in New Window ","Successfully Navigated to the Flash Player in New Window","Pass");
				Thread.sleep(2000);
				act.sendKeys(Keys.chord(Keys.CONTROL, "w")).build().perform();
				Thread.sleep(5000);
				oASelFW.effecta("sendReportWithOutExit","Closing the Flash Player from New Window","Successfully Closed the Flash Player from New Window","Pass");
				oASelFW.driver.switchTo().window(siteId[0]);

				act.moveToElement(oASelFW.driver.findElement(By.xpath("//div[@id='player-close']/descendant::img"))).build().perform();
				act.click().build().perform();
				Thread.sleep(8000);
				Thread.sleep(3000);
				handle_Alerts_PopUP();
				oASelFW.effecta("sendReport","Clicking on the Close Button","Successfully Clicked on the Close Button","Pass");
			}
			else
			{
				printCertificationButton();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	

	}

	public void clickLaunchOnClassToLaunch(String link)
	{
		try {

			String siteId[]=new String[2];
			int j=0;
			Actions act = new Actions(oASelFW.driver);
			Thread.sleep(6000);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='"+link+"']"))).build().perform();
			Thread.sleep(4000);
			act.click().build().perform();
			oASelFW.effecta("sendReportWithOutExit","Clicking on the Available link","Successfully clicked on the Link "+link+"","Pass");
			Thread.sleep(6000);

			Thread.sleep(6000);
			System.out.println("clikclaunconClasss Method");
			String cnt=oASelFW.effecta("getXpathCount","//span[contains(text(),'Not evaluated')]/../../../../../tr");
			int count=Integer.parseInt(cnt);
			System.out.println("count is ***********"+count);	
			Thread.sleep(5000);
			for(int i=1; i<=count; i++){
				Thread.sleep(2000);
				if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[contains(text(),'Not evaluated')]/../../../../../tr["+i+"]/descendant::td[3]/descendant::a[contains(text(),'Launch')]")))
				{
					Thread.sleep(3000);
					oASelFW.driver.findElement(By.xpath("//span[contains(text(),'Not evaluated')]/../../../../../tr["+i+"]/descendant::td[3]/descendant::a")).click();
					Thread.sleep(2000);
					System.out.println("clicked "+i);
					Thread.sleep(20000);
					Set<String> window=oASelFW.driver.getWindowHandles();
					for(String aSiteId: window) {
						siteId[j] = aSiteId;
						j++;
					}
					Thread.sleep(5000);
					oASelFW.driver.switchTo().window(siteId[1]);
					Thread.sleep(5000);
					act.sendKeys(Keys.chord(Keys.CONTROL, "w")).build().perform();
					Thread.sleep(5000);
					oASelFW.driver.switchTo().window(siteId[0]);

					Thread.sleep(2000);
					act.moveToElement(oASelFW.driver.findElement(By.xpath("//div[@id='player-close']/div[@class='x-mask']"))).build().perform();
					act.click().build().perform();
					Thread.sleep(3000);

					oASelFW.effecta("sendReport","Clicking on the Close Button","Successfully Clicked on the Close Button","Pass");
				}
				else{}
			}




		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	//Alert Handlinh

	public void handle_Alerts_PopUP()
	{
		try{
			Thread.sleep(2000);
			WebDriverWait wait = new WebDriverWait(oASelFW.driver,15);
			if(wait.until(ExpectedConditions.alertIsPresent())==null){
				System.out.println("alert was not present");
			}
			else{
				System.out.println("alert was present");
				Thread.sleep(2000);				
				oASelFW.driver.switchTo().alert().accept();
				Thread.sleep(1000);
			}
		}
		catch(Exception e){
			System.out.println("In catch alert was not present");
		}

	}

	public void clickonSpecificClassToLaunch(String ClassID, String launch)
	{
		try {
			String siteId[]=new String[2];
			int j=0;
			Actions act = new Actions(oASelFW.driver);
			Thread.sleep(6000);

			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[contains(text(),'Class ID')]/../descendant::span[text()='"+ClassID.trim()+"']/../../../../../../following-sibling::div/descendant::span[text()='"+launch.trim()+"']")))
			{
				Thread.sleep(3000);
				act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[contains(text(),'Class ID')]/../descendant::span[text()='"+ClassID.trim()+"']/../../../../../../following-sibling::div/descendant::span[text()='"+launch.trim()+"']"))).build().perform();
				Thread.sleep(2000);
				act.click().build().perform();
				Thread.sleep(3000);

				//oASelFW.driver.findElement(By.xpath("//span[contains(text(),'Class ID')]/../descendant::span[text()='"+ClassID.trim()+"']/../../../../../../following-sibling::div/descendant::span[text()='"+launch+"']")).click();
				Thread.sleep(2000);
				System.out.println("clicked ");
				Thread.sleep(20000);
				Set<String> window=oASelFW.driver.getWindowHandles();
				for(String aSiteId: window) {
					siteId[j] = aSiteId;
					j++;
				}
				Thread.sleep(5000);
				oASelFW.driver.switchTo().window(siteId[1]);
				Thread.sleep(5000);
				act.sendKeys(Keys.chord(Keys.CONTROL, "w")).build().perform();
				Thread.sleep(5000);
				oASelFW.driver.switchTo().window(siteId[0]);

				Thread.sleep(2000);
				act.moveToElement(oASelFW.driver.findElement(By.xpath("//div[@id='player-close']/descendant::img"))).build().perform();
				act.click().build().perform();
				Thread.sleep(3000);
				oASelFW.effecta("sendReport","Clicking on the Close Button","Successfully Clicked on the Close Button","Pass");
				if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[contains(text(),'Yes')]")))
				{
					act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[contains(text(),'Yes')]"))).build().perform();
					act.click().build().perform();
					Thread.sleep(6000);
					oASelFW.effecta("sendReport","Clicking on the Yes Button","Successfully Clicked on the Yes Button","Pass");
				}else
				{}


			}
			else{

				oASelFW.effecta("sendReport","Course is already Completed","Course is already Successfully Completed","Pass");
			}



		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void clickonSpecificVILT_ClassToLaunch(String ClassID, String launch)
	{
		try {
			String siteId[]=new String[2];
			int j=0;
			Actions act = new Actions(oASelFW.driver);
			Thread.sleep(6000);

			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[contains(text(),'Class ID')]/../descendant::span[text()='"+ClassID.trim()+"']/../../../../../../following-sibling::div/descendant::span[text()='"+launch.trim()+"']")))
			{
				Thread.sleep(3000);
				act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[contains(text(),'Class ID')]/../descendant::span[text()='"+ClassID.trim()+"']/../../../../../../following-sibling::div/descendant::span[text()='"+launch.trim()+"']"))).build().perform();
				Thread.sleep(2000);
				act.click().build().perform();
				Thread.sleep(3000);
				oASelFW.effecta("sendReport","Clicking on the "+launch+"  Button","Successfully Enrolled for the class "+ClassID+" ","Pass");

				//oASelFW.driver.findElement(By.xpath("//span[contains(text(),'Class ID')]/../descendant::span[text()='"+ClassID.trim()+"']/../../../../../../following-sibling::div/descendant::span[text()='"+launch+"']")).click();
				Thread.sleep(2000);
				System.out.println("clicked ");
				Thread.sleep(20000);

				act.moveToElement(oASelFW.driver.findElement(By.xpath("//button[text()='Close']"))).build().perform();
				Thread.sleep(2000);
				act.click().build().perform();
				Thread.sleep(6000);

			}
			else{

				oASelFW.effecta("sendReport","Course is already Completed","Course is already Successfully Completed","Pass");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public void clickonSpecificVILT_ClassToLaunchAndCompleteTheClassSuccessfully(String ClassID, String launch)
	{
		try {
			String siteId[]=new String[2];
			int j=0;
			Actions act = new Actions(oASelFW.driver);
			Thread.sleep(6000);

			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[contains(text(),'Class ID')]/../descendant::span[text()='"+ClassID.trim()+"']/../../../../../../following-sibling::div/descendant::span[text()='"+launch.trim()+"']")))
			{
				Thread.sleep(3000);
				act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[contains(text(),'Class ID')]/../descendant::span[text()='"+ClassID.trim()+"']/../../../../../../following-sibling::div/descendant::span[text()='"+launch.trim()+"']"))).build().perform();
				Thread.sleep(2000);
				act.click().build().perform();
				Thread.sleep(3000);
				oASelFW.effecta("sendReport","Clicking on the "+launch+"  Button","Successfully Enrolled for the class "+ClassID+" ","Pass");

				Thread.sleep(2000);
				System.out.println("clicked ");
				Thread.sleep(20000);
				act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='OK']"))).build().perform();
				Thread.sleep(2000);
				act.click().build().perform();
				Thread.sleep(6000);
				Thread.sleep(20000);
				act.moveToElement(oASelFW.driver.findElement(By.xpath("//button[text()='Close']"))).build().perform();
				Thread.sleep(2000);
				act.click().build().perform();
				Thread.sleep(6000);


			}else if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","")))
			{

			}
			String cnt=oASelFW.effecta("getXpathCount","//span[contains(text(),'Not evaluated')]/../../../../../tr");
			int count=Integer.parseInt(cnt);
			System.out.println("count is ***********"+count);	
			Thread.sleep(5000);
			for(int i=1; i<=count; i++){
				Thread.sleep(2000);

				if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[contains(text(),'Not evaluated')]/../../../../../tr["+i+"]/descendant::td[3]/descendant::a[contains(text(),'Launch')]")))
				{
					Thread.sleep(3000);
					oASelFW.driver.findElement(By.xpath("//span[contains(text(),'Not evaluated')]/../../../../../tr["+i+"]/descendant::td[3]/descendant::a")).click();
					Thread.sleep(2000);
					System.out.println("clicked "+i);
					Thread.sleep(20000);

					act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Start']"))).build().perform();
					Thread.sleep(2000);
					act.click().build().perform();
					Thread.sleep(2000);
					oASelFW.effecta("sendReport","Clicking on Start Class Button","Successfully Clicked on Start Button","Pass");


					for(int k=1; k<2;k++)
					{
						String text = oASelFW.effecta("getText","//div[text()='Yes']&&//div[text()='Google chrome']&&//div[text()='Saba']","Field Text");
						Thread.sleep(2000);
						System.out.println("Retrived Text of input box "+text);
						act.moveToElement(oASelFW.driver.findElement(By.xpath("//div[text()='"+text.trim()+"']/../../../../table//input"))).build().perform();
						Thread.sleep(2000);
						act.click().build().perform();
						Thread.sleep(2000);

						String next = oASelFW.effecta("getText","//span[text()='Next']&&//span[text()='Finish']","Next Button");
						Thread.sleep(2000);
						System.out.println("Retrived Text of Next button "+next);
						act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='"+next.trim()+"']"))).build().perform();
						Thread.sleep(2000);
						act.click().build().perform();
						Thread.sleep(2000);
						oASelFW.effecta("sendReport","Clicking on Next","Successfully Clicked on Next Button","Pass");
					}




					/*if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//div[text()='Yes']/../../../../table//input")))
					{
						act.moveToElement(oASelFW.driver.findElement(By.xpath("//div[text()='Yes']/../../../../table//input"))).build().perform();
						Thread.sleep(2000);
						act.click().build().perform();
						Thread.sleep(2000);
						oASelFW.effecta("sendReport","Selecting Sky is Blue or Not","Successfully Selected Sky is Blue","Pass");

						act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Next']"))).build().perform();
						Thread.sleep(2000);
						act.click().build().perform();
						Thread.sleep(2000);
						oASelFW.effecta("sendReport","Clicking on Next","Successfully Clicked on Next Button","Pass");
					}else if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//div[text()='Google chrome']/../../../../table//input")))
					{
						act.moveToElement(oASelFW.driver.findElement(By.xpath("//div[text()='Google chrome']/../../../../table//input"))).build().perform();
						Thread.sleep(2000);
						act.click().build().perform();
						Thread.sleep(2000);
						oASelFW.effecta("sendReport","Selecting Chrome as a Browser","Successfully Selected Chrome as a Browser","Pass");

						act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Next']"))).build().perform();
						Thread.sleep(2000);
						act.click().build().perform();
						Thread.sleep(2000);
						oASelFW.effecta("sendReport","Clicking on Next","Successfully Clicked on Next Button","Pass");
					}
					else if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//div[text()='Saba']/../../../../table//input")))
					{
						act.moveToElement(oASelFW.driver.findElement(By.xpath("//div[text()='Saba']/../../../../table//input"))).build().perform();
						Thread.sleep(2000);
						act.click().build().perform();
						Thread.sleep(2000);

						act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Finish']"))).build().perform();
						Thread.sleep(2000);
						act.click().build().perform();
						Thread.sleep(2000);
						oASelFW.effecta("sendReport","Clicking on Finish","Successfully Clicked on Finish Button","Pass");
					}
					if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//div[text()='Google chrome']/../../../../table//input")))
					{
						act.moveToElement(oASelFW.driver.findElement(By.xpath("//div[text()='Google chrome']/../../../../table//input"))).build().perform();
						Thread.sleep(2000);
						act.click().build().perform();
						Thread.sleep(2000);
						oASelFW.effecta("sendReport","Selecting Chrome as a Browser","Successfully Selected Chrome as a Browser","Pass");

						act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Next']"))).build().perform();
						Thread.sleep(2000);
						act.click().build().perform();
						Thread.sleep(2000);
						oASelFW.effecta("sendReport","Clicking on Next","Successfully Clicked on Next Button","Pass");
					}else if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//div[text()='Yes']/../../../../table//input")))
					{
						act.moveToElement(oASelFW.driver.findElement(By.xpath("//div[text()='Yes']/../../../../table//input"))).build().perform();
						Thread.sleep(2000);
						act.click().build().perform();
						Thread.sleep(2000);
						oASelFW.effecta("sendReport","Selecting Sky is Blue or Not","Successfully Selected Sky is Blue","Pass");

						act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Next']"))).build().perform();
						Thread.sleep(2000);
						act.click().build().perform();
						Thread.sleep(2000);
						oASelFW.effecta("sendReport","Clicking on Next","Successfully Clicked on Next Button","Pass");
					}

					else if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//div[text()='Saba']/../../../../table//input")))
					{
						act.moveToElement(oASelFW.driver.findElement(By.xpath("//div[text()='Saba']/../../../../table//input"))).build().perform();
						Thread.sleep(2000);
						act.click().build().perform();
						Thread.sleep(2000);

						act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Finish']"))).build().perform();
						Thread.sleep(2000);
						act.click().build().perform();
						Thread.sleep(2000);
						oASelFW.effecta("sendReport","Clicking on Finish","Successfully Clicked on Finish Button","Pass");
					}*/
					/*act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Finish']"))).build().perform();
					Thread.sleep(2000);
					act.click().build().perform();
					Thread.sleep(2000);
					oASelFW.effecta("sendReport","Clicking on Finish","Successfully Clicked on Finish Button","Pass");*/

					act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Submit']"))).build().perform();
					Thread.sleep(2000);
					act.click().build().perform();
					Thread.sleep(2000);
					oASelFW.effecta("sendReport","Clicking on Submit Button ","Successfully Clicked on Submit Button","Pass");

					act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Exit']"))).build().perform();
					Thread.sleep(2000);
					act.click().build().perform();
					Thread.sleep(2000);
					oASelFW.effecta("sendReport","Clicking on Exit Button ","Successfully Clicked on Exit Button","Pass");

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void clickLaunchOnClassToLaunchAfterEnrolling()
	{
		try {

			String siteId[]=new String[2];
			int j=0;
			Actions act = new Actions(oASelFW.driver);
			Thread.sleep(6000);

			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[contains(text(),'Not evaluated')]")))
			{
				Thread.sleep(6000);
				System.out.println("clikclaunconClasss Method");
				String cnt=oASelFW.effecta("getXpathCount","//span[contains(text(),'Not evaluated')]/../../../../../tr");
				int count=Integer.parseInt(cnt);
				System.out.println("count is ***********"+count);	
				Thread.sleep(5000);
				for(int i=1; i<=count; i++){
					Thread.sleep(2000);
					if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[contains(text(),'Not evaluated')]/../../../../../tr["+i+"]/descendant::td[3]/descendant::a[contains(text(),'Launch')]")))
					{
						Thread.sleep(3000);
						oASelFW.driver.findElement(By.xpath("//span[contains(text(),'Not evaluated')]/../../../../../tr["+i+"]/descendant::td[3]/descendant::a")).click();
						Thread.sleep(2000);
						System.out.println("clicked "+i);
						Thread.sleep(20000);
						Set<String> window=oASelFW.driver.getWindowHandles();
						for(String aSiteId: window) {
							siteId[j] = aSiteId;
							j++;
						}
						Thread.sleep(5000);
						oASelFW.driver.switchTo().window(siteId[1]);
						Thread.sleep(5000);
						act.sendKeys(Keys.chord(Keys.CONTROL, "w")).build().perform();
						Thread.sleep(5000);
						oASelFW.driver.switchTo().window(siteId[0]);

						Thread.sleep(2000);
						act.moveToElement(oASelFW.driver.findElement(By.xpath("//div[@id='player-close']/descendant::img"))).build().perform();
						act.click().build().perform();
						Thread.sleep(3000);
						oASelFW.effecta("sendReport","Clicking on the Close Button","Successfully Clicked on the Close Button","Pass");
						if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[contains(text(),'Yes')]")))
						{
							act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[contains(text(),'Yes')]"))).build().perform();
							act.click().build().perform();
							Thread.sleep(6000);
							oASelFW.effecta("sendReport","Clicking on the Yes Button","Successfully Clicked on the Yes Button","Pass");
						}else
						{}


					}
					else{
						oASelFW.effecta("sendReport","Course is already Completed","Course is already Successfully Completed","Pass");
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void actionToPerformOnClass(String dro, String status)
	{
		Actions act = new Actions(oASelFW.driver);
		try {
			Thread.sleep(8000);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[(text()='"+dro.trim()+"')]"))).build().perform();
			act.click().build().perform();
			oASelFW.effecta("sendReport","Clicking on the "+dro+" Button ","Successfully clicked on the "+dro+" Button ","Pass");
			Thread.sleep(3000);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[contains(text(),'"+status.trim()+"')]"))).build().perform();
			act.click().build().perform();
			Thread.sleep(3000);

			oASelFW.effecta("type","//label[contains(text(),'Reason')]/../following-sibling::td//textarea","Test","Reason Test");

			Thread.sleep(3000);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Save']"))).build().perform();
			act.click().build().perform();
			Thread.sleep(5000);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public void EnrollClassByClickingEnrollLink(String value)
	{
		try {
			Actions act = new Actions(oASelFW.driver);
			Thread.sleep(8000);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[contains(text(),'"+value+"')]"))).build().perform();
			act.click().build().perform();
			oASelFW.effecta("sendReport","Clicking on the "+value+" Button ","Successfully clicked on the "+value+" Button ","Pass");
			Thread.sleep(8000);

			oASelFW.effecta("getText","//label[contains(text(),'You have registered')]","Enrollment Conformation");
			Thread.sleep(2000);



		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//click on right side plain[profile, completed learning,......]
	public void click_RightPlane_Link(String optionLink) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		oASelFW.effecta("waitForElementPresent","//span[contains(text(),'"+optionLink+"')]","60");
		Thread.sleep(2000);
		oASelFW.effecta("click","//span[contains(text(),'"+optionLink+"')]",optionLink);
		Thread.sleep(2000);
	}

	public void clickonShowFilterInPlanPage(String type)
	{
		try {

			Actions act = new Actions(oASelFW.driver);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Show filters']"))).build().perform();
			act.click().build().perform();
			Thread.sleep(3000);
			oASelFW.effecta("sendReportWithOutExit","Clicked on Show Filter Tab","Successfully clicked on Show Filter Tab","Pass");
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//label[text()='Type']/../following-sibling::table/descendant::input"))).build().perform();
			act.click().build().perform();
			Thread.sleep(3000);
			oASelFW.effecta("sendReportWithOutExit","Clicked on Drop down ","Successfully clicked on Drop Down Field","Pass");
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//li[text()='"+type+"']"))).build().perform();
			Thread.sleep(2000);
			act.click().build().perform();
			Thread.sleep(2000);
			oASelFW.effecta("sendReport","Selecting type form drop down ","Successfully Selected value as "+type+" ","Pass");
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Apply filters']"))).build().perform();
			Thread.sleep(2000);
			act.click().build().perform();
			Thread.sleep(2000);
			oASelFW.effecta("sendReport","Clicking on Apply Filter","Successfully clicked on Apply Filter","Pass");



		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	public void clickonSearchTitleLink(String type)
	{
		try {

			Actions act = new Actions(oASelFW.driver);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Acquired']/ancestor::td/../descendant::a[contains(text(),'"+type+"')]"))).build().perform();
			act.click().build().perform();
			Thread.sleep(5000);
			oASelFW.effecta("sendReportWithOutExit","Clicked on Title Link","Successfully clicked on Title Link","Pass");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public void clickOnTitleLink() throws InterruptedException
	{
		try {
			Thread.sleep(5000);
			Actions act = new Actions(oASelFW.driver);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Successful']/ancestor::table/descendant::tr[1]/td[1]//a"))).build().perform();
			act.click().build().perform();
			Thread.sleep(7000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void printCertificationButton() throws InterruptedException
	{
		try {

			Actions act = new Actions(oASelFW.driver);
			Thread.sleep(7000);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Print Certificate']"))).build().perform();
			act.click().build().perform();
			Thread.sleep(7000);
			oASelFW.effecta("sendReportWithOutExit","Clicked on Print Certificate","Successfully clicked on Print Certificate","Pass");
			String[] window1 = oASelFW.effectaArray("getAllWindowNames");
			oASelFW.effecta("selectWindow",window1[window1.length-1]);
			Thread.sleep(7000);
			act.sendKeys(Keys.chord(Keys.CONTROL, "w")).build().perform();
			Thread.sleep(5000);
			oASelFW.effecta("selectWindow",window1[window1.length-2]);
			Thread.sleep(5000);
			act.sendKeys(Keys.chord(Keys.CONTROL, "w")).build().perform();
			Thread.sleep(3000);
			oASelFW.effecta("selectWindow",window1[window1.length-3]);
			Thread.sleep(4000);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void printCertificationWithSpecificClassID(String classID, String printcertificate) throws InterruptedException
	{
		try {

			Actions act = new Actions(oASelFW.driver);
			Thread.sleep(7000);
			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[contains(text(),'Class ID')]/../descendant::span[text()='"+classID.trim()+"']/../../../../../../following-sibling::div/descendant::span[text()='"+printcertificate.trim()+"']")))
			{
				act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[contains(text(),'Class ID')]/../descendant::span[text()='"+classID.trim()+"']/../../../../../../following-sibling::div/descendant::span[text()='"+printcertificate.trim()+"']"))).build().perform();
				act.click().build().perform();
				Thread.sleep(7000);
				oASelFW.effecta("sendReportWithOutExit","Clicked on Print Certificate","Successfully clicked on Print Certificate","Pass");
				String[] window1 = oASelFW.effectaArray("getAllWindowNames");
				oASelFW.effecta("selectWindow",window1[window1.length-1]);
				Thread.sleep(7000);
				act.sendKeys(Keys.chord(Keys.CONTROL, "w")).build().perform();
				Thread.sleep(5000);
				oASelFW.effecta("selectWindow",window1[window1.length-2]);
				Thread.sleep(5000);
				act.sendKeys(Keys.chord(Keys.CONTROL, "w")).build().perform();
				Thread.sleep(3000);
				oASelFW.effecta("selectWindow",window1[window1.length-3]);
				Thread.sleep(4000);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public void clickonActionsTabDropDown(String droplink)
	{
		try {
			Thread.sleep(3000);
			oASelFW.driver.findElement(By.xpath("//span[text()='My Plan']/../../../../following-sibling::div[2]/descendant::span[2]")).click();
			Thread.sleep(5000);
			Actions act = new Actions(oASelFW.driver);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='"+droplink+"']"))).build().perform();
			Thread.sleep(2000);
			act.click().build().perform();
			oASelFW.effecta("sendReport","Clicking on the Add Learning","Successfully Clicked on the Add Learning ","Pass");

		} catch (Exception e) {
			e.printStackTrace();
		}


	}
	/*public void clickonViewSummaryToDropClass(String droplink)
	{
		try {
			Thread.sleep(3000);
			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","")))
			oASelFW.driver.findElement(By.xpath("")).click();
			Thread.sleep(5000);
			Actions act = new Actions(oASelFW.driver);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='"+droplink+"']"))).build().perform();
			Thread.sleep(2000);
			act.click().build().perform();
			oASelFW.effecta("sendReport","Clicking on the Add Learning","Successfully Clicked on the Add Learning ","Pass");

		} catch (Exception e) {
			e.printStackTrace();
		}


	}*/

	public void availableLearningShowFilter(String type)
	{
		try {
			Actions act = new Actions(oASelFW.driver);
			Thread.sleep(5000);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//label[contains(text(),'Search')]/../../../../../descendant::span[text()='Show filters']"))).build().perform();
			Thread.sleep(2000);
			act.click().build().perform();
			oASelFW.effecta("sendReport","Clicking on the Show filters Tab","Successfully Clicked on the Show filters ","Pass");
			Thread.sleep(2000);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//table//tbody//table//tbody//table//tbody//table//tbody/tr/td[2]/div"))).build().perform();
			Thread.sleep(2000);
			act.click().build().perform();

			Thread.sleep(2000);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//li[text()='"+type+"']"))).build().perform();
			Thread.sleep(2000);
			act.click().build().perform();
			Thread.sleep(2000);
			oASelFW.effecta("sendReport","Selecting type form drop down ","Successfully Selected value as "+type+" ","Pass");
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Search']"))).build().perform();
			Thread.sleep(2000);
			act.click().build().perform();
			oASelFW.effecta("sendReport","Clicking on the Search Button","Successfully Clicked on Search Button","Pass");
			Thread.sleep(2000);


		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void tileSelectDueDateAddToPlaine(String course)
	{
		try {
			Actions act = new Actions(oASelFW.driver);
			/*act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Load More']"))).build().perform();
			Thread.sleep(2000);
			act.click().build().perform();
			Thread.sleep(2000);*/

			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='"+course.trim()+"']/../../../../descendant::div[text()='Select date']"))).build().perform();
			Thread.sleep(2000);
			act.click().build().perform();
			oASelFW.effecta("sendReport","Clicking on the Select Date link ","Successfully Clicked on Select Date Link","Pass");
			Thread.sleep(2000);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMMM-d");
			Calendar cal = Calendar.getInstance();
			Date date1 = cal.getTime();
			//cal.add(Calendar.YEAR, 1);
			Date nextYear = cal.getTime();
			String enddatetime = sdf.format(nextYear);
			System.out.println(enddatetime);
			String[] date = enddatetime.split("-");
			String month = date[1];
			String year = date[0];
			String day = date[2];
			System.out.println("month"+date[1]);
			System.out.println("year"+date[0]);
			System.out.println("day"+date[2]);

			System.out.println("Day from calendar is*************"+day);
			System.out.println("Now the date is :=>  " + enddatetime);

			act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[text()='"+day+"']"))).build().perform();
			Thread.sleep(2000);
			act.click().build().perform();
			oASelFW.effecta("sendReport","Selecting the date from Calender ","Successfully Selected the Date from Calender as "+day,"Pass");

			Thread.sleep(2000);

			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='"+course+"']/../../../../descendant::span[text()='Select']"))).build().perform();
			Thread.sleep(2000);
			act.click().build().perform();
			oASelFW.effecta("sendReport","Clicking on the Select link","Successfully Clicked Select Link","Pass");
			Thread.sleep(2000);

			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Add to Plan']"))).build().perform();
			Thread.sleep(2000);
			act.click().build().perform();
			oASelFW.effecta("sendReport","Clicking on the Add to Plan Link","Successfully Clicked on Add to Plan","Pass");
			Thread.sleep(8000);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Cancel']"))).build().perform();
			Thread.sleep(5000);
			act.click().build().perform();
			oASelFW.effecta("sendReport","Closing the Pop up Window","Successfully Clicked on the Pop up window","Pass");
			Thread.sleep(8000);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void deleteTitleExistInPlane(String course, String action)
	{
		Actions act = new Actions(oASelFW.driver);
		try {
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[contains(text(),'"+course+"')]/ancestor::tr/descendant::span[text()='Register']...."))).build().perform();
			Thread.sleep(2000);
			act.click().build().perform();
			Thread.sleep(2000);

			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='"+action+"']"))).build().perform();
			Thread.sleep(2000);
			act.click().build().perform();
			Thread.sleep(2000);

			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Done']"))).build().perform();
			Thread.sleep(2000);
			act.click().build().perform();
			System.out.println("Successfully removed the item from plane");
			//oASelFW.effecta("sendReport","Removing the item from the plane","Successfully removed the item from plane","Pass");
			Thread.sleep(2000);

			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Done']"))).build().perform();
			Thread.sleep(2000);
			act.click().build().perform();
			System.out.println("Successfully removed the item from plane");
			//oASelFW.effecta("sendReport","Removing the item from the plane","Successfully removed the item from plane","Pass");
			Thread.sleep(3000);

			act.moveToElement(oASelFW.driver.findElement(By.xpath("//li[contains(text(),'Action')]/ancestor::span/../following-sibling::div/descendant::span[5]"))).build().perform();
			Thread.sleep(2000);
			act.click().build().perform();
			Thread.sleep(3000);

		} catch (Exception e) {
			e.printStackTrace();
		}


	}


	public void showNameAfterFilterAvailableResults(String course)
	{
		Actions act = new Actions(oASelFW.driver);
		try {
			//if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//a[contains(text(),'"+course+"')]/ancestor::tr/descendant::span[text()='Register']")))
			//{}

			act.moveToElement(oASelFW.driver.findElement(By.xpath("//a[contains(text(),'"+course+"')]/ancestor::tr/descendant::span[text()='Register']"))).build().perform();
			Thread.sleep(2000);
			act.click().build().perform();
			Thread.sleep(4000);
			oASelFW.effecta("sendReport","Clicking on the Register Button ","Successfully Clicked on Register Button","Pass");
			Thread.sleep(7000);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[contains(text(),'Path')]/../../../../../following-sibling::div/descendant::span[text()='Complete Registration'][1]"))).build().perform();
			Thread.sleep(3000);
			act.click().build().perform();
			oASelFW.effecta("sendReport","Clicking on the Complete Registration Tab","Successfully Clicked on Complete Registration Tab","Pass");
			Thread.sleep(3000);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void CompletedConformationMesg()
	{
		try {
			Actions act = new Actions(oASelFW.driver);
			Thread.sleep(2000);

			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[contains(text(),'Path')]/../../../../../following-sibling::div/descendant::span[text()='Complete Registration'][1]"))).build().perform();
			act.click().build().perform();
			oASelFW.effecta("sendReport","Clicking on the Complete Registration Tab","Successfully Clicked on Complete Registration Tab","Pass");
			Thread.sleep(6000);

			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Congratulations!']/ancestor::div/descendant::li[contains(text(),'You have successfully')]"))).build().perform();
			Thread.sleep(2000);
			oASelFW.effecta("sendReport","Validating the Alert Pop up","Successfully Validated the Alert Pop up","Pass");

			oASelFW.driver.findElement(By.xpath("//span[text()='Congratulations!']/ancestor::div/descendant::li[contains(text(),'You have successfully')]")).getText();
			Thread.sleep(2000);

			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Congratulations!']/ancestor::div/descendant::span[text()='Close']"))).build().perform();
			Thread.sleep(2000);
			act.click().build().perform();
			oASelFW.effecta("sendReport","Clicking on the Alert Accept Button ","Successfully Accepted the Alert Pop up","Pass");
			Thread.sleep(2000);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//Global Field Readiness,People Development,Performance Acceleration,Talent Acceleration,Transformation Toolkits,Workday, Leadership Summit,Good Gigs
	public void myDevelopmentPageContent(String trainingLink1,String trainingLink2, String progLink1, String progLink2, String resLink1,String resLink2, String eventLink1, String eventLink2)
	{
		try {
			Thread.sleep(1000);
			oASelFW.effecta("verifyElementPresent","//a[text()='"+trainingLink1.trim()+"']",trainingLink1);
			oASelFW.effecta("verifyElementPresent","//a[text()='"+trainingLink2.trim()+"']",trainingLink2);
			Thread.sleep(2000);
			oASelFW.effecta("verifyElementPresent","//a[text()='"+progLink1.trim()+"']",progLink1);
			oASelFW.effecta("verifyElementPresent","//a[text()='"+progLink2.trim()+"']",progLink2);
			Thread.sleep(1000);
			oASelFW.effecta("verifyElementPresent","//a[text()='"+resLink1.trim()+"']",resLink1);
			oASelFW.effecta("verifyElementPresent","//a[text()='"+resLink2.trim()+"']",resLink2);
			Thread.sleep(2000);
			oASelFW.effecta("verifyElementPresent","//a[text()='"+eventLink1.trim()+"']",eventLink1);
			oASelFW.effecta("verifyElementPresent","//a[text()='"+eventLink2.trim()+"']",eventLink2);
			Thread.sleep(1000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void dropRegistrationClassByClickingDropLink()
	{
		try {
			Actions act = new Actions(oASelFW.driver);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Drop']"))).build().perform();
			act.click().build().perform();
			oASelFW.effecta("sendReport","Clicking on the Drop Button ","Successfully clicked on the Drop Button","Pass");
			Thread.sleep(3000);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Drop Registration']/../../../../../following-sibling::div//a[text()='Drop']"))).build().perform();
			act.click().build().perform();
			oASelFW.effecta("sendReport","Clicking on the Drop Registration Button ","Successfully clicked on the Drop Registration Button","Pass");
			Thread.sleep(6000);

			oASelFW.driver.findElement(By.xpath("//label[contains(text(),'Reason')]/../following-sibling::td//textarea")).sendKeys("test");
			Thread.sleep(2000);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[text()='Save']"))).build().perform();
			act.click().build().perform();
			Thread.sleep(8000);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//---------------------------------------------------myLearn--------------------------------------------------------
	public void getApp() throws IOException
	{

		String url      = null;
		if(oASelFW.instanceName.equalsIgnoreCase("ci"))
		{
			url = "http://mylearnci.vmware.com/";
		}
		else if(oASelFW.instanceName.equalsIgnoreCase("stage"))
		{
			url = "http://mylearnstg.vmware.com/";
		}
		else if(oASelFW.instanceName.equalsIgnoreCase("qa"))
		{
			//url = "http://mylearn.vmware.com/";
		}
		System.out.println("Url for mylearn "+url);
		oASelFW.driver.get(url);
		oASelFW.driver.manage().window().maximize();
		oASelFW.effecta("sendReport","Launching the App URL","Successfully launched the URL as "+url,"Pass");
	}


	public void loginIntoApp() throws IOException, InterruptedException
	{
		String uname_txt 		 = "//input[@name='username']";
		String pass_txt  		 = "//input[@name='password']";
		String lgn_btn   		 = "//input[@value='Login']";
		String uname,pwd;
		addip();
		boolean status = Boolean.parseBoolean(oASelFW.effecta("isElementPresent", uname_txt));
		if(status==true)
		{
			String instance = oASelFW.instanceName;
			if(instance.equalsIgnoreCase("ci")||instance.equalsIgnoreCase("stage"))
			{
				uname            		 = "npalley@vmware.com";
				pwd                      = "Testme@01";

				oASelFW.driver.findElement(By.xpath(uname_txt)).sendKeys(uname);;
				oASelFW.driver.findElement(By.xpath(pass_txt)).sendKeys(pwd);
				oASelFW.driver.findElement(By.xpath(lgn_btn)).click();
				oASelFW.effecta("sendReport","User login verification", "Successfully logged in as "+uname, "Pass");
			}
			else if(instance.equalsIgnoreCase("qa"))
			{
				uname            		 = "npalley@vmware.com";
				pwd                      = "Testme@01";
				Thread.sleep(3000);
				oASelFW.driver.findElement(By.xpath(uname_txt)).sendKeys(uname);;
				oASelFW.driver.findElement(By.xpath(pass_txt)).sendKeys(pwd);
				oASelFW.driver.findElement(By.xpath(lgn_btn)).click();
				oASelFW.effecta("sendReport","User login verification", "Successfully logged in as "+uname, "Pass");
			}

		}
		else
		{
			oASelFW.effecta("sendReport","User login verification", "Unable to login to the application", "Fail");
		}
	}


	public void addip() throws IOException
	{
		String iperror_msg    = "//td[contains(text(),'Access Denied')]";
		String pwd_txt        = "//input[@name='password']";
		String cng_ip_btn     = "//input[@name='submit']";
		String cng_ip_pwd  	  = "itlmsqa";
		String add_btn        = "//input[@name='submit']";
		String ip_adddress    = "//input[@name='ip_address']";
		String lgn_mylrn_link = "//a[@title='Go to login page']";
		try {
			if(oASelFW.driver.findElement(By.xpath(iperror_msg)).isDisplayed())
			{
				String curr_url = oASelFW.driver.getCurrentUrl();
				String ip_url   = curr_url+"/ip.cfm";
				oASelFW.driver.get(ip_url);
				oASelFW.effecta("sendReport","Change Ip Address page navigation","Navigated to-"+ip_url,"Pass");
				WebDriverWait wait = new WebDriverWait(oASelFW.driver, 120);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pwd_txt)));
				oASelFW.driver.findElement(By.xpath(pwd_txt)).sendKeys(cng_ip_pwd);
				oASelFW.driver.findElement(By.xpath(cng_ip_btn)).click();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(add_btn)));
				oASelFW.driver.findElement(By.xpath(add_btn)).click();
				String added_ip = oASelFW.driver.findElement(By.xpath(ip_adddress)).getAttribute("value");
				oASelFW.effecta("sendReport","Add current ip details verification","Added ip adderess"+added_ip+"to ip.cfm","Pass");
				oASelFW.driver.findElement(By.xpath(lgn_mylrn_link)).click();
			}
		} catch (TimeoutException e) {
			System.out.println("add ip not displayed");
		}
		catch (NoSuchElementException e) {
			System.out.println("add ip not displayed");
		}
	}

	String adminster_btn = "//a[contains(@title,'myLearn Administration Portal')]";
	public void naviagate_adminster_portal() {
		try {
			WebDriverWait wait = new WebDriverWait(oASelFW.driver,15);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(adminster_btn)));
			oASelFW.driver.findElement(By.xpath(adminster_btn)).click();

			oASelFW.driver.switchTo().window(switchTabs().get("myLearn Administration Portal"));
			String title = oASelFW.driver.getCurrentUrl();
			System.out.println("switched to mylearn admin portal");
			oASelFW.effecta(
					"sendReport",
					"Verify if the user is navigated to Administration portal",
					"User successfully navigated to myLearn Administration Portal",
					"Pass");
		} catch (Exception e) {
			if (e.equals("ElementNotFoundException")) {
				oASelFW.effecta("sendReport", "WebElement verification",
						"Element not found at " + adminster_btn, "Fail");
			}
		}
	}

	public HashMap<String, String> switchTabs()

	{
		String defaultcontent = oASelFW.driver.getWindowHandle();
		String default_title = oASelFW.driver.switchTo().defaultContent().getTitle();
		HashMap<String, String> tab = new HashMap<String, String>();
		ArrayList<String> tabs = new ArrayList<String>(oASelFW.driver.getWindowHandles());

		for (String tabval : tabs) {
			System.out.println(tabval);
			String tabname = oASelFW.driver.switchTo().window(tabval).getTitle();
			System.out.println(tabname);
			tab.put(tabname, tabval);
		}
		tab.put(default_title, defaultcontent);

		return tab;
	}

	public void switch_to_menu_content() {
		WebDriverWait wait = new WebDriverWait(oASelFW.driver, 5);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(menu_frame)));
		System.out.println("In Menu content");
	}

	public void manage_content() throws InterruptedException {
		String manage_link = "//a[text()='Manage']";
		oASelFW.driver.findElement(By.xpath(manage_link)).click();
		Thread.sleep(2000);
		oASelFW.effecta("sendReport", "Clicking on Manage Link","Successfully Clicked on Manage Link", "Pass");
	}


	public void click_ManageClasses_Link() throws InterruptedException {
		String manage_Classes = "//a[contains(text(),'Classes')]";
		oASelFW.driver.findElement(By.xpath(manage_Classes)).click();
		Thread.sleep(3000);
	}

	public void click_ManageUser_Link() throws InterruptedException {
		//String manage_Classes = "//a[contains(text(),'Users')]";
		Thread.sleep(3000);
		oASelFW.driver.findElement(By.xpath("//a[contains(text(),'Users')]")).click();
		Thread.sleep(2000);
	}


	public void switch_to_default_content() {
		oASelFW.driver.switchTo().defaultContent();
	}
	public void switch_to_main_content() {
		WebDriverWait wait = new WebDriverWait(oASelFW.driver, 5);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(main_frame)));
		System.out.println("switched to main content");
	}


































































	//-----------------------------------------------Siddharth-----------------------------------


	public Boolean WaitOnElement(String Element, int Time )
	{
		try{
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, Time);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Element)));

			return true;
		}
		catch(org.openqa.selenium.NoSuchElementException e){
			System.out.println("no such element exception");
			oASelFW.effecta("sendReport","Wait on the Element "+Element," Element not found "+ Element,"Fail");
			return false;
		}

	}
	public void selectBytext(String xpath, String value)
	{

		if(WaitOnElement(xpath, 5))
		{
			new org.openqa.selenium.support.ui.Select(oASelFW.driver.findElement(By.xpath(xpath))).selectByVisibleText(value);


			oASelFW.effecta("sendReport","Select desired value from Drop down", "Selected "+ value, "Pass");
		}

		else
		{

			oASelFW.effecta("sendReport","Select desired value from Drop down",  value+" Not found", "Fail");
		}

	}

	public void ClickOnElement(String Element, String ElementType)
	{
		if(WaitOnElement(Element, 05))
		{
			oASelFW.effecta("click",Element,ElementType);
			System.out.println("Clicked on "+ElementType);
			//oASelFW.effecta("sendReport","Click on the Link/img "," Clicked on the Element "+ ElementType,"Pass");
		}

		else
		{
			oASelFW.effecta("sendReport","Click on the Link/img ", ElementType+" not found","Fail");
		}

	}

	public void SetTextField(String xpath, String Value, String FieldType)
	{
		if(WaitOnElement(xpath,5))
		{
			oASelFW.driver.findElement(By.xpath(xpath)).clear();
			oASelFW.driver.findElement(By.xpath(xpath)).sendKeys(Value);
			oASelFW.effecta("sendReport","Set text field with value: "+ Value.replaceAll("[^\\w\\s-]","")," Text Field "+FieldType+" set by "+Value.replaceAll("[^\\w\\s-]",""),"Pass");
		}

		else
		{
			oASelFW.effecta("sendReport","Set text field with value: "+ Value ," Text Field not found","Fail");
		}
	}
	public String getText(String xpath)
	{
		String text = null;
		if(WaitOnElement(xpath, 05))
		{
			text=oASelFW.driver.findElement(By.xpath(xpath)).getText();
			if(text!=null)
			{
				oASelFW.effecta("sendReportWithOutExit","get text","Successfully Fetched text as "+text,"Pass");
			}

			else
			{
				oASelFW.effecta("sendReportWithOutExit","get text","Successfully Fetched text as " +text,"fail");
			}
		}
		else
		{
			oASelFW.effecta("sendReportWithOutExit","get text","invalid xpath ","fail");
		}
		return text;


	}


	//==========Methods below
	public void SearchClass(String DevType) throws InterruptedException
	{
		//Thread.sleep(2000);
		System.out.println(DevType);
		selectBytext("//select[@name='st_name']", DevType);
		Thread.sleep(2000);
		selectBytext("//select[@name='college']", "VMware Customer Education");
		Thread.sleep(2000);
		selectBytext("//select[@name='delivery']", "VMware, Inc.");
		Thread.sleep(2000);
		selectBytext("//select[@name='status_active']", "Open");

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		Date date1 = cal.getTime();
		cal.add(Calendar.DATE, 0);
		Date nextYear = cal.getTime();
		String enddatetime = sdf.format(nextYear);
		System.out.println("current Date "+enddatetime);
		Thread.sleep(2000);
		
		SetTextField("//td[contains(text(), 'Start Date')]/following::input[1]", enddatetime, "Current Date");
		Thread.sleep(1000);
		ClickOnElement("//input[@value='Find']", "Find Button");
		Thread.sleep(3000);
	}


public void randomClick() throws InterruptedException
{
	Thread.sleep(2000);
	int randomNumber=(int) (Math.random()*100);
	System.out.println("randomNumber   :"+randomNumber);
	String randomNumber1=randomNumber+".";
	for(int i=3;i<=100;i++)
	{

		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//td[text()='"+randomNumber1.trim()+"']")))
		{
			ClickOnElement("//td[text()='"+randomNumber1.trim()+"']/following::td[2]/a", "Click Random Value");
			Thread.sleep(5000);
			break;

		}
		else
		{
			ClickOnElement("//span["+i+"]/a", "Click Next Page");
			Thread.sleep(5000);

		}
	}

}
public String[] completeCourse() throws InterruptedException
{
	String[]  arr = new String[2];

	String classId = getText("//td[text()='Class ID:']/following::td[1]");
	String courseId= getText("//td[text()='Course:']/following::td[1]");
	arr[0]=classId;
	arr[1]=courseId;
	System.out.println("classid   "+arr[0]);
	System.out.println("courseId   "+arr[1]);
	System.out.println("Class/course "+ classId+" "+ courseId);
	ClickOnElement("//a[text()='Hold']/following::a[1]", "Add Link");
	SetTextField("//input[@name='email']", "bashaa@vmware.com", "Email id:");
	ClickOnElement("//input[@value='Find']", "Find Button");
	Thread.sleep(1000);
	ClickOnElement("//input[@name='GetThis']", "Select User radio Button");
	ClickOnElement("//input[@name='notice']", "unmark Notify User");
	ClickOnElement("//input[@value='Continue']", "Continue Button");
	Thread.sleep(1000);
	if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//input[@value='Continue']")))
	{
		ClickOnElement("//input[@value='Continue']", "Continue Button");
		Thread.sleep(2000);
	}
	if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//input[@value='Continue']")))
	{
		ClickOnElement("//input[@value='Continue']", "Continue Button");
		Thread.sleep(2000);
	}
	return arr;

}






}







