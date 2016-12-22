package classes;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.arsin.ArsinSeleniumAPI;


public class VaultLoginPageMainPage {

	ArsinSeleniumAPI oASelFW;

	public VaultLoginPageMainPage() {
	}

	public VaultLoginPageMainPage(ArsinSeleniumAPI oASelFW) {
		this.oASelFW=oASelFW;
	}


	//LOGIN TO Vault SABA
	public void vault_Login_Details(String userName,String password){
		oASelFW.driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='j_username']")));
		oASelFW.effecta("type","//input[@name='j_username']",userName,"username");
		System.out.println("User Name :::::::::::::::::"+userName);
		oASelFW.effecta("type","//input[@name='j_password']",password,"password");
		System.out.println("Password :::::::::::::::"+password);
		oASelFW.effecta("clickAndWait","//a[contains(text(),'Sign In')]","Signin");


	}

	//LOGOUT FROM Vault
	public void Vault_Logout() throws InterruptedException
	{
		System.out.println("In saba signout");
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//img[@class='user-avatar-image']")));

		if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//img[@class='user-avatar-image']"))){
			oASelFW.effecta("click","//img[@class='user-avatar-image']","User Options");

			oASelFW.effecta("waitForElementPresent","//span[contains(text(),'Sign Out')]","60");
			oASelFW.effecta("click","//span[contains(text(),'Sign Out')]","Logout");
			System.out.println("Logout Successfull");

		}

	}

	public void navigateToMySitesLinks(String link, String subLink)
	{
		try {

			Actions act = new Actions(oASelFW.driver);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[contains(text(),'"+link+"')]"))).build().perform();
			act.click().build().perform();
			Thread.sleep(2000);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[contains(text(),'"+subLink+"')]"))).build().perform();
			act.click().build().perform();
			Thread.sleep(5000);

		} catch (Exception e) {
			e.printStackTrace();
		}



	}
	/*
	 * @author - Arifuddin Mohd
	 * Clicking on [Home, Documentation Library, Search portlet and Service Pages].
	 */
	public void ClickOnSecureNDARoadMapLinks(String link)
	{
		try {
			Actions act = new Actions(oASelFW.driver);
			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[contains(text(),'This Site is Used')]/../../../../../following-sibling::div//descendant::a[contains(text(),'"+link+"')]")))
			{
				act.moveToElement(oASelFW.driver.findElement(By.xpath("//span[contains(text(),'This Site is Used')]/../../../../../following-sibling::div//descendant::a[contains(text(),'"+link+"')]"))).build().perform();
				act.click().build().perform();
				Thread.sleep(5000);
			}
			else
			{
				oASelFW.effecta("sendReportWithOutExit","Navigate to the NDA RoadMap Page ","Successfully Navigate to RoadMapage Could not found "+link+" link","Pass");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void mainFolderPage(String mainFolder, String subFolder)
	{
		try {
			Actions act = new Actions(oASelFW.driver);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//th[contains(text(),'folder')]/../../following-sibling::tbody/descendant::span[contains(text(),'"+mainFolder+"')]"))).build().perform();
			act.click().build().perform();
			Thread.sleep(5000);
			act.moveToElement(oASelFW.driver.findElement(By.xpath("//th[contains(text(),'folder')]/../../following-sibling::tbody/descendant::span[contains(text(),'"+subFolder+"')]"))).build().perform();
			act.click().build().perform();
			Thread.sleep(5000);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void validateFolderStructureInVault(ArrayList<String> list, int size)
	{

		try {
			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//th[contains(text(),'folder')]/../../following-sibling::tbody/tr/td[1]//descendant::span[3]")))
			{
				String text="";
				String cnt=oASelFW.effecta("getXpathCount","//th[contains(text(),'folder')]/../../following-sibling::tbody/tr");
				int count=Integer.parseInt(cnt);
				System.out.println("count is ***********"+count);	
				Thread.sleep(5000);
				for(int i=1, j=0; i<=count; i++){
					Thread.sleep(2000);
					text=oASelFW.driver.findElement(By.xpath("//th[contains(text(),'folder')]/../../following-sibling::tbody/tr["+i+"]/td[1]//descendant::span[3]")).getText();
					Thread.sleep(2000);
					System.out.println(text+"*******");
					if(text.trim().equals(list.get(j))){
						oASelFW.effecta("sendReportWithOutExit","Validating the Folder Name "+list.get(j)+" with the Expected "+text+"","Successfully validate the Folder Name "+list.get(j)+" with the Expected "+text+"","Pass");
						Thread.sleep(2000);
						j++;	
					}
					else{
						oASelFW.effecta("sendReportWithOutExit","Validating the Folder Name "+list.get(j)+" with the Expected "+text+"","No "+text+" link found in Vault Folder Page","Pass");
					}
				}
			}
			else
			{
				oASelFW.effecta("sendReportWithOutExit","Validating the Folder Name Structure ","No Folder Structure Found ","Pass");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

































}

