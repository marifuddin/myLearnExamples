package classes;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.arsin.ArsinSeleniumAPI;
public class MyLearnLoginPage extends myutils {

	//ArsinSeleniumAPI oASelFW;
	public static Properties config = null;
	public static String basedir    = System.getProperty("user.dir");
	public MyLearnLoginPage(ArsinSeleniumAPI oASelFW)
	{
		super(oASelFW);
	}

	/**
	 * Author     -  Bandari Paramesh
	 * @throws IOException
	 * Description - This method us used to open the application in the web browser. This reads instance
	 * variable from properties and opens up the appropriate URL
	 */
	public void getApp() throws IOException
	{
		String instance = loadinstanceprops("instance");
		String url      = null;
		if(instance.equalsIgnoreCase("ci"))
		{
			url = loadprops("ci-url");
		}
		else if(instance.equalsIgnoreCase("stage"))
		{
			url = loadprops("stage-url");
		}
		else if(instance.equalsIgnoreCase("qa"))
		{
			url = loadprops("qa-url");
		}
		System.out.println("Url for mylearn "+url);
		oASelFW.driver.get(url);
		oASelFW.driver.manage().window().maximize();
		oASelFW.effecta("sendReport","Launching the App URL","Successfully launched the URL as "+url,"Pass");
	}
	public void verifyrelogin() throws IOException
	{
		try {
			String relogin_link = "//a[contains(text(),'re-login')]";
			if(oASelFW.driver.findElement(By.xpath(relogin_link)).isDisplayed())
			{
				clickOnElement(relogin_link);
			}
				} catch (TimeoutException e) {
					System.out.println("relogin not displayed");
				}
				catch (NoSuchElementException e) {
					System.out.println("relogin not displayed");
		}
		
		
	}
	public boolean errormsg() throws NoSuchElementException
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(oASelFW.driver, timeOutSecs());
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(),'Access Denied')]")));
			Assert.assertTrue(oASelFW.driver.findElement(By.xpath("//td[contains(text(),'Access Denied')]")).isDisplayed());
			String NoSuchElementException = null;
			throw new RuntimeException(NoSuchElementException);
		}
		catch (RuntimeException e)
		{
			System.out.println("exception is "+e);
			return false;
		}
	}
	/**
	 * Description - This method is used to login to the application and accepts user type as input
	 * @param usertype
	 * @throws IOException 
	 */
	public void loginAs(String usertype) throws IOException
	{
		String uname,pwd;
		if(usertype.equalsIgnoreCase("admin"))
		{
			String uname_txt 		 = "//input[@name='username']";
			String pass_txt  		 = "//input[@name='password']";
			String lgn_btn   		 = "//input[@value='Login']";
			uname            		 = loadprops("adminuser");
			pwd                      = loadprops("adminpwd");
			waitOnXpath(uname_txt, timeOutSecs());
			oASelFW.driver.findElement(By.xpath(uname_txt)).sendKeys(uname);;
			oASelFW.driver.findElement(By.xpath(pass_txt)).sendKeys(pwd);
			oASelFW.driver.findElement(By.xpath(lgn_btn)).click();
		}
	}

	/**
	 * 
	 * @throws IOException
	 */
	public void loginIntoApp() throws IOException
	{
		String uname_txt 		 = "//input[@name='username']";
		String pass_txt  		 = "//input[@name='password']";
		String lgn_btn   		 = "//input[@value='Login']";
		String uname,pwd;
		addip();
		boolean status = waitOnXpath(uname_txt, timeOutSecs());
		if(status==true)
		{
			String instance = loadinstanceprops("instance");
			if(instance.equalsIgnoreCase("ci")||instance.equalsIgnoreCase("stage"))
			{
				uname            		 = loadprops("adminuser");
				pwd                      = loadprops("adminpwd");
				waitOnXpath(uname_txt, timeOutSecs());
				oASelFW.driver.findElement(By.xpath(uname_txt)).sendKeys(uname);;
				oASelFW.driver.findElement(By.xpath(pass_txt)).sendKeys(pwd);
				oASelFW.driver.findElement(By.xpath(lgn_btn)).click();
				sendReport("User login verification", "Successfully logged in as "+uname, "Pass");
			}
			else if(instance.equalsIgnoreCase("qa"))
			{
				uname            		 = loadprops("qauser");
				pwd                      = loadprops("qapwd");
				waitOnXpath(uname_txt, timeOutSecs());
				oASelFW.driver.findElement(By.xpath(uname_txt)).sendKeys(uname);;
				oASelFW.driver.findElement(By.xpath(pass_txt)).sendKeys(pwd);
				oASelFW.driver.findElement(By.xpath(lgn_btn)).click();
				sendReport("User login verification", "Successfully logged in as "+uname, "Pass");
			}

		}
		else
		{
			sendReport("User login verification", "Unable to login to the application", "Fail");
		}
	}
	
	public void reLogin() throws IOException
	{
		addip();
		String uname = null,pwd;
		String uname_txt      = "//input[@name='username']";
		String pass_txt   = "//input[@name='password']";
		String lgn_btn    = "//input[@value='Login']";
		if(oASelFW.driver.findElement(By.xpath(uname_txt)).isDisplayed())
		{
			String instance = loadinstanceprops("instance");
			if(instance.equalsIgnoreCase("ci")||instance.equalsIgnoreCase("stage"))
			{
				uname            		 = loadprops("adminuser");
				pwd                      = loadprops("adminpwd");
				waitOnXpath(uname_txt, timeOutSecs());
				oASelFW.driver.findElement(By.xpath(uname_txt)).sendKeys(uname);;
				oASelFW.driver.findElement(By.xpath(pass_txt)).sendKeys(pwd);
				oASelFW.driver.findElement(By.xpath(lgn_btn)).click();
				sendReport("User login verification", "Successfully logged in as "+uname, "Pass");
			}
			else if(instance.equalsIgnoreCase("qa"))
			{
				uname            		 = loadprops("qauser");
				pwd                      = loadprops("qapwd");
				waitOnXpath(uname_txt, timeOutSecs());
				oASelFW.driver.findElement(By.xpath(uname_txt)).sendKeys(uname);;
				oASelFW.driver.findElement(By.xpath(pass_txt)).sendKeys(pwd);
				oASelFW.driver.findElement(By.xpath(lgn_btn)).click();
				sendReport("User login verification", "Successfully logged in as "+uname, "Pass");
			}
		}
		else
		{
			System.out.println("Login page is not displayed");
		}
		
	}
	
	public void RandDLoginPage() throws IOException
	{
		String uname,pwd;
		String uname_txt 		 = "//input[@name='username']";
		String pass_txt  		 = "//input[@name='code']";
		String lgn_btn   		 = "//input[@value='Login']";
		uname            		 = loadprops("rDUSER");
		pwd                      = loadprops("rDPSWD");
		waitOnXpath(uname_txt, timeOutSecs());
		oASelFW.driver.findElement(By.xpath(uname_txt)).sendKeys(uname);;
		oASelFW.driver.findElement(By.xpath(pass_txt)).sendKeys(pwd);
		oASelFW.driver.findElement(By.xpath(lgn_btn)).click();
	}
	public void addip() throws IOException
	{
		String iperror_msg    = "//td[contains(text(),'Access Denied')]";
		String pwd_txt        = "//input[@name='password']";
		String cng_ip_btn     = "//input[@name='submit']";
		String cng_ip_pwd  	  = loadprops("changeip-password");
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
				WebDriverWait wait = new WebDriverWait(oASelFW.driver, timeOutSecs());
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

	
	public String getPartnerID() throws IOException, InterruptedException
	{
		String instance       = loadinstanceprops("instance");
		String partner_link   =  loadprops("partner_srch__link");
		String plink_replace  = null;
		String partner_holder = "//a[contains(text(),'Jino Francis')]";
		String partner_id     = null;
		if(instance.equalsIgnoreCase("ci"))
		{
			plink_replace = partner_link.replace("http://mylearn.vmware.com/", "http://mylearnci.vmware.com/");
			System.out.println("replaced");
			System.out.println(plink_replace);
		}
		else if(instance.equalsIgnoreCase("stage"))
		{
			plink_replace = partner_link.replace("http://mylearn.vmware.com/", "http://mylearnstg.vmware.com/");
			System.out.println("replaced");
			System.out.println(plink_replace);
		}
		System.out.println("plink_replace"+plink_replace);
		oASelFW.driver.get(plink_replace);
		if(waitOnXpath(partner_holder, 60))
		{
			System.out.println("in parnter hhlder");
			String partner_id_holder = "//a[contains(text(),'Jino Francis')]/following-sibling::span";
			partner_id = getText(partner_id_holder);
			System.out.println("partner id is-"+partner_id);
			partner_id = partner_id.replace("["," ");
			partner_id = partner_id.replace("]"," ");
			System.out.println("partner id is-"+partner_id);
		}
		else
		{
			System.out.println("holder not displayed");
		}
		
		return partner_id;
		
	}


}
