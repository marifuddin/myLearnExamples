package classes;

import java.io.FileInputStream;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.arsin.ArsinSeleniumAPI;
import com.opera.core.systems.scope.protos.ExecProtos.ActionList.Action;
//import com.sun.xml.internal.fastinfoset.sax.Properties;

public class EditCoursePricePage extends myutils {

	//ArsinSeleniumAPI oASelFW;
	public EditCoursePricePage(ArsinSeleniumAPI oASelFW)
	{
		super(oASelFW);
	}

	public void add_new_price(){
		String add_new = "//a[text()='Add New']";
		clickOnElement(add_new);
	}

	public HashMap<String, String> PriceLocales()
	{
		HashMap<String, String> locales = new HashMap();
		locales.put("Tier1","Australian Dollars");
		locales.put("Tier2","Chinese Yuan");
		locales.put("Tier3","Euro");
		locales.put("Tier4","Japanese Yen");
		locales.put("Tier5","UK Pounds");
		locales.put("Tier6","US Dollars");
		return locales;
	}

	public void verifyAddedPrice()
	{
		String del_link = "//td[contains(text(),'Self-Paced')]/../following::tr[2]/td[5]/a";
		if(waitOnXpath(del_link, timeOutSecs()))
		{
			sendReport("Add price functionality verification", "Successfully verified the  added price functionalty for location", "Pass");
		}
		else
		{
			sendReport("Add price functionality verification", "Unable to add price for the location, add price functionality failed", "Fail");
		}
	}

	public void deletePrices()
	{
		int localeSize = PriceLocales().size();
		for(int i=0;i<localeSize;i++)
		{
			String del_link = "//td[contains(text(),'Self-Paced')]/../following::tr[2]/td[5]/a";
			boolean status = waitOnXpath(del_link, timeOutSecs());
			if(status)
			{
				clickOnElement(del_link);
				handleAlert();
				waitOnXpath(del_link, timeOutSecs());
			}
		}
		sendReport("Delete Price details for tiers", "successfully delete pricing for all the tiers","Pass");
	}

	public void deletePriceForDeliveryType(String deliveryType) throws InterruptedException
	{
		String delType = "//td[contains(text(),'"+deliveryType+"')]/../following::tr[2]/td[5]//a[1]";
		try {
			if(oASelFW.driver.findElement(By.xpath(delType)).isDisplayed())
			{
				Thread.sleep(2000);
				clickOnElement(delType);
				handleAlert();
				waitOnXpath(delType, timeOutSecs());
			}
			else
			{
				System.out.println("no prices added");
			}
		} 
		catch (NoSuchElementException e) {

			System.out.println("No Prices are added");
		}
	}

	/**
	 * Description - This method is used to add a new price for the given tier
	 * @param Dtype
	 * @param Currency
	 * @param product
	 * @param amount
	 * @param locale
	 */
	public void NewPrice_Selection(String Dtype, String Currency,String product, String amount, String locale)
	{
		String dd_deltype     = "//select[@name='id_member']";
		String dd_currency    = "//select[@name='id_currency']";
		String dd_product     = "//select[@name='id_product']";
		String Amnt_txt       = "//td[@id='amountCell']/span/input";
		String dd_locale      = "//select[@name='id_locale']";
		String save_price_btn = "//input[@value='Save']";
		String add_new        = "//a[text()='Add New']";

		waitOnXpath(dd_deltype, timeOutSecs());
		selectByVisibleText(dd_deltype, Dtype);

		waitOnXpath(dd_currency, timeOutSecs());
		selectByVisibleText(dd_currency, Currency);

		waitOnXpath(dd_product, timeOutSecs());
		selectByVisibleText(dd_product, product);

		waitOnXpath(Amnt_txt, timeOutSecs());
		setTextField(Amnt_txt, amount);

		waitOnXpath(dd_locale, timeOutSecs());
		selectByVisibleText(dd_locale, locale);

		waitOnXpath(save_price_btn, timeOutSecs());
		clickOnElement(save_price_btn);

		waitOnXpath(add_new, timeOutSecs());
	}


}
