package classes;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.arsin.ArsinSeleniumAPI;

public class SabaHomePage {


	ArsinSeleniumAPI oASelFW;

	public SabaHomePage() {
	}

	public SabaHomePage(ArsinSeleniumAPI oASelFW) {
		this.oASelFW=oASelFW;
	}
	
	/* @Author - Mangala 
	 * Description - This method will clicks on Menu links[Admin,Groups,Me,People..].
	 */
	

	//CLICK ON MENU ITEMS LINKS

	public void click_Menu_Items(String MenuTabs) throws InterruptedException{

		WebDriverWait wait=new WebDriverWait(oASelFW.driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='spf-nav-home']")));
		oASelFW.effecta("click","//span[text()='"+MenuTabs+"']",MenuTabs);
		Thread.sleep(8000);
		System.out.println("Clicked on "+MenuTabs);
	}

	/* @Author - Arifuddin Mohd
	 * Description - This method will select the parent and sub Services from parent DropDown.
	 */
	public void clickParentAndSub_Service(String parentService, String subService) throws InterruptedException{
		WebDriverWait wait=new WebDriverWait(oASelFW.driver,20);
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table/descendant::div[@class='sb-left-nav-menu']/descendant::li[2]//a")));
		String value="";
		String cnt=oASelFW.effecta("getXpathCount","//div[@class='sb-left-nav-menu']/descendant::li");
		int count=Integer.parseInt(cnt);
		for(int i=1; i<=count;i++){
			value=oASelFW.driver.findElement(By.xpath("//div[@class='sb-left-nav-menu']/descendant::li["+i+"]/span/a")).getText();
			System.out.println(value+"*******"+parentService);
			if(value.trim().equals(parentService)){
				oASelFW.effecta("clickAndWait","//div[@class='sb-left-nav-menu']/descendant::li["+i+"]/span/a",parentService.trim());
				Thread.sleep(2000);
				break;
			}
			else{

			}}
		WebDriverWait wt=new WebDriverWait(oASelFW.driver,15);
		wt.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='sb-left-nav-menu']/ul/descendant::ul[3]")));
		String dispvalue="";
		String cnt1=oASelFW.effecta("getXpathCount","//div[@class='sb-left-nav-menu']/ul/descendant::ul");
		int count1=Integer.parseInt(cnt1);
		for(int i=1; i<=count1; i++){
			dispvalue=oASelFW.driver.findElement(By.xpath("//div[@class='sb-left-nav-menu']/ul/descendant::ul["+i+"]/descendant::a")).getText();
			System.out.println(dispvalue+"++++++++++++++"+subService);	
			if(dispvalue.trim().equals(subService)){
				System.out.println(value+"---------------------------------------"+subService);	
				oASelFW.effecta("clickAndWait","//div[@class='sb-left-nav-menu']/ul/descendant::ul["+i+"]/descendant::a",subService.trim());
				Thread.sleep(2000);
				break;
			}
			else{

			}}
	}
	
	
	
	

	/* @Author - Arifuddin Mohd
	 * Description - This method will upload the Data present in ExcelSheet.
	 */
	public String uploadSampleSourceFile(String File)
	{
		String fileName =null;
		try{

			System.out.println("in the click_Attachfilelink");
			String XMLfilename=(oASelFW.sAutomationPath+"bulkData\\"+File+".xls");
			System.out.println(XMLfilename);

			Thread.sleep(3000);
			int index = XMLfilename.lastIndexOf("\\");
			fileName = XMLfilename.substring(index + 1);

			if(oASelFW.sBrowser.contains("Chrome")){
				Process process = new ProcessBuilder(oASelFW.sAutomationPath+"\\Saba\\autoit\\NewUploadChrome.exe", XMLfilename, "Open").start(); 
				Thread.sleep(10000);
			}
			if(oASelFW.sBrowser.contains("Firefox")){
				System.out.println("in the fire fox browser");
				Process process = new ProcessBuilder(oASelFW.sAutomationPath+"\\Saba\\autoit\\NewUploadFirefox.exe", XMLfilename, "Open").start(); 
				Thread.sleep(8000);
			}

			if(oASelFW.sBrowser.contains("Internet Explorer")){
				Process process = new ProcessBuilder(oASelFW.sAutomationPath+"\\Saba\\autoit\\UpLoadAttachmentIE.exe", XMLfilename, "Open").start(); 
				Thread.sleep(8000);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return fileName;
	}
	
	
	/* @author - Arifuddin Mohd
	 * Description - This method will upload the File in UI Import field Column.
	 */
	public void UI_ImportExcelSheet(String objValue, String File)
	{

		try {

			if(Boolean.parseBoolean(oASelFW.effecta("isElementPresent","//span[text()='UI Import']")))
			{
				oASelFW.effecta("verifyElementPresent","//span[text()='UI Import']","UI Import");
				oASelFW.effecta("type","//label[contains(text(),'Object Name')]/../following-sibling::td/descendant::td[1]//input",objValue,"Object Value");
				oASelFW.driver.findElement(By.xpath("//li[text()='"+objValue+"']")).sendKeys(Keys.ENTER);
				oASelFW.effecta("clickAndWait","//label[contains(text(),'Import File')]/../following-sibling::td/descendant::td[2]//input","Browse File","5000");
				uploadSampleSourceFile(File);
				oASelFW.effecta("clickAndWait","//span[@id='button-1186-btnInnerEl']","Save Button","5000");

			}else{
				System.out.println("Unable to find the UI Import Element");
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void validateMEPage()
	{
		try {
			
			oASelFW.effecta("verifyElementPresent","//span[text()='My Plan']","My Plan Text");
			oASelFW.effecta("verifyElementPresent","//span[text()='In Progress']","Learning In Prrogress Link");
			oASelFW.effecta("verifyElementPresent","//span[contains(text(),'Show filters')]","Show Filter Drop Down");
			oASelFW.effecta("verifyElementPresent","//span[contains(text(),'Plan')]","Plan Link");
			oASelFW.effecta("verifyElementPresent","//label[contains(text(),'Plan View')]","Plan View Field");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	


}




