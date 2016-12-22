package classes;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.arsin.ArsinSeleniumAPI;

public class SabaSystemPage {


	ArsinSeleniumAPI oASelFW;

	public SabaSystemPage() {
	}

	public SabaSystemPage(ArsinSeleniumAPI oASelFW) {
		this.oASelFW=oASelFW;
	}
	
	
	/* @Author - Mangala 
	 * Description - This method will Clicks on SYSTEM HOME LINKS[MANAGE INTEGRATIONS,MANAGE SECURITY,CONFIGURE SYSTEM....]
	 */

	//CLICK ON SYSTEM HOME LINKS[MANAGE INTEGRATIONS,MANAGE SECURITY,CONFIGURE SYSTEM....]
	
	public void click_SystemHome_Link(String systemLinks)
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		oASelFW.effecta("waitForElementPresent","//a[text()='"+systemLinks+"']","60");
		oASelFW.effecta("click","//a[text()='"+systemLinks+"']",systemLinks);
		oASelFW.driver.switchTo().defaultContent();
		
	}
	
	/* @Author - Mangala
	  
	 * Description - This method will Clicks on LINKS UI IMPORT /NEW JOB/ MONITOR UI IMPORT
	 */

	
	
	//CLICK ON LINKS UI IMPORT /NEW JOB/ MONITOR UI IMPORT
	
	public void click_Import_Link(String importLinks)
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		
		oASelFW.effecta("waitForElementPresent","//a[@data-sabaid='"+importLinks+"']","60");
		oASelFW.effecta("click","//a[@data-sabaid='"+importLinks+"']","UI Import");
		oASelFW.driver.switchTo().defaultContent();
		
	}
	
	
	/* @Author - Mangala
	  
	 * Description - Scroll to right
	 */
	public void Scroll_Right() throws InterruptedException
	{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 20);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='spf-getting-started-iframe']")));
		Thread.sleep(3000);
		System.out.println("In scrolling");
		/*JavascriptExecutor js = (JavascriptExecutor)oASelFW.driver;  
		js.executeScript("window.scrollBy(447,0)", "");*/
		
		JavascriptExecutor je = (JavascriptExecutor) oASelFW.driver;  
		je.executeScript("arguments[0].scrollIntoView(true);",oASelFW.driver.findElement(By.xpath("(//span[text()='Download Logs'])[1]")));
		System.out.println("scrolled");
		
		
		oASelFW.driver.switchTo().defaultContent();
		
		Thread.sleep(5000);
		
		
		//div[text()='Organization, Internal']/parent::td/following-sibling::td[15]
		
		
	}
	
	
	

	}
	
	
	
	
	

