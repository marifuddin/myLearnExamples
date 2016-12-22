package classes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jboss.netty.logging.JBossLoggerFactory;
import org.openqa.jetty.servlet.SendRedirect;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.arsin.ArsinSeleniumAPI;

public class SabaLoginPage {


	ArsinSeleniumAPI oASelFW;

	public SabaLoginPage() {
	}
	public SabaLoginPage(ArsinSeleniumAPI oASelFW) {
		this.oASelFW=oASelFW;
	}

	public Boolean searchManage_JobName(String jobName, String ID, String Number)
	{
		boolean success = true;
		try {
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
			System.out.println("Entering the text  "+jobName);

			oASelFW.effecta("type","//label[text()='Name']/../..//following-sibling::td//input",jobName, "JobName");
			Thread.sleep(2000);
			oASelFW.effecta("clickAndWait","//span[text()='Search']","Search");
			/*try {

				Document doc = null;
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();f
				DocumentBuilder db = dbf.newDocumentBuilder();


				File tarXML = new File("C:\\testing\\howto.xml"); 
				doc = db.parse("C:\\testing\\howto.xml");

				Element ele = (Element) doc.getElementsByTagName("title").item(0);
				ele.setTextContent(jobName);

				StringWriter sw = new StringWriter();        
				final TransformerFactory transformerFactory = TransformerFactory.newInstance();
				final Transformer transformer1 = transformerFactory.newTransformer();
				final DOMSource source = new DOMSource(doc);
				final StreamResult result = new StreamResult(tarXML);//new File(Path+QuoteNumber+".xml"
				//transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer1.transform(source, result);

				TransformerFactory tFactory = TransformerFactory.newInstance();


				Transformer transformer = tFactory.newTransformer (new javax.xml.transform.stream.StreamSource("C:\\testing\\howto.xsl"));

				transformer.transform
				(new javax.xml.transform.stream.StreamSource
						("C:\\testing\\howto.xml"),
						new javax.xml.transform.stream.StreamResult
						( new FileOutputStream("C:\\testing\\howto.html")));
			}
			catch (Exception e) {
				e.printStackTrace( );
			} */
			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='No items found']")))
			{
				oASelFW.effecta("sendReportWithOutExit","Enter the Valid Name ","No items Found for the given job name:  "+jobName,"Pass");
				System.out.println("no items found "+jobName);

			}else{
				oASelFW.effecta("clickAndWait","//span[text()='Name']/../../following-sibling::tr/descendant::td[2]//a",jobName);
				System.out.println("Clicked on the displayed job name ");

			}
			oASelFW.driver.switchTo().defaultContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Boolean status "+success);
		return success;
	}

	/* @Author - Mangala 
	 * Description - This method will Login to Saba
	 */

	//LOGIN TO SABA
	public void saba_Login_Details(String userName,String password)
	{
		try{
			oASelFW.driver.manage().window().maximize();
			WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='j_username']")));
			oASelFW.effecta("type","//input[@name='j_username']",userName,"username");
			System.out.println("User Name :::::::::::::::::"+userName);
			Thread.sleep(2000);
			oASelFW.effecta("type","//input[@name='j_password']",password,"password");
			Thread.sleep(2000);
			System.out.println("Password :::::::::::::::"+password);
			oASelFW.effecta("clickAndWait","//a[contains(text(),'Sign In')]","Signin");
			Thread.sleep(4000);
		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	
	public void rsaToken()
	{
		
		try {
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	//LOGIN TO SABA
	public void saba_Login_Details_Trial()
	{
		String userName1="";
		String password1="";	
		try{
			
			if(oASelFW.instanceName.contains("qa"))
			{
				oASelFW.insertDataIntoAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData","update OutputData set bFlag='T' where sEnvironment='"+oASelFW.instanceName+"'");
				Thread.sleep(3000);
				oASelFW.insertDataIntoAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData","update OutputData set bFlag='F' where sEnvironment='dev'");
				String sSQL					= "Select * from OutputData where  bFlag='T'";
				HashMap<String, String> data=oASelFW.readDataFromAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData",sSQL);
				
				userName1			= data.get("sUserEmail");
				password1			= data.get("sPassword");
				String Url     			= data.get("sTestcaseId");
				
				System.out.println("in qa instance method");
				
				WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='j_username']")));

				oASelFW.driver.get(Url);
				Thread.sleep(1000);
				oASelFW.driver.manage().window().maximize();
				Thread.sleep(2000);
				oASelFW.effecta("type","//input[@name='j_username']",userName1,"username");
				System.out.println("User Name :::::::::::::::::"+userName1);
				Thread.sleep(2000);
				oASelFW.effecta("type","//input[@name='j_password']",password1,"password");
				Thread.sleep(2000);
				System.out.println("Password :::::::::::::::"+password1);
			}
			else if(oASelFW.instanceName.contains("dev"))
			{
				oASelFW.insertDataIntoAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData","update CurriculaRelatedData set bFlag='T' where sEnvironment='"+oASelFW.instanceName+"'");
				Thread.sleep(3000);
				oASelFW.insertDataIntoAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData","update OutputData set bFlag='F' where sEnvironment='qa'");
				
				String sSQL					= "Select * from OutputData where  bFlag='T'";
				HashMap<String, String> data=oASelFW.readDataFromAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData",sSQL);
				userName1			= data.get("sUserEmail");
				password1			= data.get("sPassword");
				String Url     		= data.get("sTestcaseId");
				
				System.out.println("in dev instance method");
				WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='j_username']")));

				oASelFW.driver.get(Url);
				Thread.sleep(1000);
				oASelFW.driver.manage().window().maximize();
				Thread.sleep(2000);
				
				oASelFW.effecta("type","//input[@name='j_username']",userName1,"username");
				System.out.println("User Name :::::::::::::::::"+userName1);
				Thread.sleep(2000);
				oASelFW.effecta("type","//input[@name='j_password']",password1,"password");
				Thread.sleep(2000);
				System.out.println("Password :::::::::::::::"+password1);
			}
			else
			{
				oASelFW.insertDataIntoAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData","update CurriculaRelatedData set bFlag='F' where sEnvironment='"+oASelFW.instanceName+"'");
				Thread.sleep(3000);
				oASelFW.insertDataIntoAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData","update OutputData set bFlag='F' where sEnvironment='qa'");
				Thread.sleep(3000);
				oASelFW.insertDataIntoAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData","update OutputData set bFlag='T' where sEnvironment='uat'");
			
				String sSQL					= "Select * from OutputData where  bFlag='T'";
				HashMap<String, String> data=oASelFW.readDataFromAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\SabaData",sSQL);
				userName1			= data.get("sUserEmail");
				password1			= data.get("sPassword");
				String Url     		= data.get("sTestcaseId");
				
				System.out.println("in dev instance method");
				WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='j_username']")));

				oASelFW.driver.get(Url);
				Thread.sleep(1000);
				oASelFW.driver.manage().window().maximize();
				Thread.sleep(2000);
				
				oASelFW.effecta("type","//input[@name='j_username']",userName1,"username");
				System.out.println("User Name :::::::::::::::::"+userName1);
				Thread.sleep(2000);
				oASelFW.effecta("type","//input[@name='j_password']",password1,"password");
				Thread.sleep(2000);
				System.out.println("Password :::::::::::::::"+password1);
				
			}
			
			
			oASelFW.effecta("clickAndWait","//a[contains(text(),'Sign In')]","Signin");
			Thread.sleep(2000);
		}catch(Exception e)
		{
			e.printStackTrace();
		}

	}



	/* @Author - Mangala 
	 * Description - This method will Logout from Saba
	 */
	//LOGOUT FROM SABA
	public void saba_Logout() throws InterruptedException
	{
		System.out.println("In saba signout");
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//img[@title='User Options']")));

		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//img[@title='User Options']"))){
			oASelFW.effecta("click","//img[@title='User Options']","User Options");

			oASelFW.effecta("waitForElementPresent","//span[contains(text(),'Sign out')]","60");
			oASelFW.effecta("click","//span[contains(text(),'Sign out')]","Logout");
			System.out.println("Logout Successfull");

		}

	}
	public void deleteLocationNameFromLocationPage(String name) throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 15);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		do{
			oASelFW.effecta("type","//label[text()='Name']/../..//following-sibling::td//input",name, "Name");
			Thread.sleep(1000);
			oASelFW.effecta("clickAndWait","//span[text()='Search']","Search");

			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//a[text()='"+name+"']/../../following-sibling::td//a")))
			{
				String cnt=oASelFW.effecta("getXpathCount","//span[text()='Name']/../../../tr");
				int count=Integer.parseInt(cnt);
				oASelFW.effecta("sendReport","Validating Number of Records ","Successfully found "+cnt+" Records ","Pass");
				oASelFW.effecta("clickAndWait","//a[text()='"+name+"']/../../following-sibling::td//a","Delete");
				Alert alert = oASelFW.driver.switchTo().alert();
				Thread.sleep(1000);
				alert.accept();
			}
			else{
				oASelFW.effecta("sendReport","No Records Found ","Successfully Validate No Records","Pass");
			}

		}
		while(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='No items found']"))==false);

		//oASelFW.effecta("type","//label[text()='Name']/../..//following-sibling::td//input",name, "Name");
		//Thread.sleep(1000);
		//oASelFW.effecta("clickAndWait","//span[text()='Search']","Search");
		oASelFW.effecta("sendReportWithOutExit","Verifiying the Deleted Name ","Successfully verifiyed as "+name,"Pass");
		//oASelFW.effecta("isElementPresent","//span[text()='No items found']");
		oASelFW.driver.switchTo().defaultContent();
	}



}
