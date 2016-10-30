package classes;

import java.io.IOException;

import com.arsin.ArsinSeleniumAPI;

public class OnlineStorePage extends myutils {

	public OnlineStorePage(ArsinSeleniumAPI oASelFW) {
		super(oASelFW);
		// TODO Auto-generated constructor stub
	}
	
	public void LoginToDRPage() throws IOException
	{
		String dr_login   = "//input[@name='loginID']";
		String dr_pwd     = "//input[@name='password']";
		String dr_lgn_btn = "//input[@value='login']";
		if(waitOnXpath(dr_login, timeOutSecs()))
		{
			setTextField(dr_login, loadprops("dr-user"));
			setTextField(dr_pwd, loadprops("dr-pwd"));
			clickOnElement(dr_lgn_btn);
			
		}
		
	}

}
