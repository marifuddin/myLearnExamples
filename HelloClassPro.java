package classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import com.arsin.ArsinSeleniumAPI;


public class HelloClassPro extends myutils{
	
	public HelloClassPro(ArsinSeleniumAPI oASelFW) {
		super(oASelFW);
		// TODO Auto-generated constructor stub
	}

	public static void main(String args[]) throws FileNotFoundException
	{
		Properties pros = new Properties();
		FileInputStream fis = new FileInputStream("D:/myLearnAutoSuite/application.txt");
		
		
	}
	

}
