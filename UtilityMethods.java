package classes;

import java.sql.*;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;




import com.arsin.ArsinSeleniumAPI;




public class UtilityMethods {
	/**
	 * @author Yatheesh_Nadindlu
	 * @date 30-12-2013
	 * @param oASelFW
	 * @param b 
	 */
	
	ArsinSeleniumAPI oASelFW;
	public UtilityMethods(ArsinSeleniumAPI oASelFW){
		this.oASelFW=oASelFW;
	}

public String getValuesFromPropertiesFile(String propertiesFileName, String key) {
	try
	{
	
		
		FileInputStream fis = new FileInputStream(new File(oASelFW.sAutomationPath+oASelFW.sProjectName+"\\Constants\\"+propertiesFileName+".properties"));
	
		//System.out.println("---------------------------"+oASelFW.sAutomationPath+oASelFW.sProjectName+"\\Constants\\"+propertiesFileName+".properties"); 
		
		Properties prop=new Properties();
	prop.load(fis);
	return prop.get(key).toString();
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}

public HashMap<String,String> getDBData(String dbName,String testCaseName) throws FileNotFoundException, IOException{
	String sSelectSQL ="";
	Properties prop= new Properties();
	prop.load(new FileInputStream(oASelFW.sAutomationPath+oASelFW.sProjectName+File.separator+"Constants"+File.separator+"testCaseNames.properties"));
	String sTestCaseName=prop.getProperty(testCaseName);
	String dbNme=prop.getProperty(dbName);
	if(dbName.contains("DRDataBase")){
		sSelectSQL = "Select * from "+dbNme+" where sTestcaseId = '"+sTestCaseName+"' and sEnvironment = '"+oASelFW.instanceName+"'";
	}
	
	else{
		sSelectSQL = "Select * from "+dbNme+" where sTestcaseId = '"+sTestCaseName+"' and sEnvironment = '"+oASelFW.instanceName+"' and bFlag='T'";
		System.out.println(sSelectSQL);
	}
	
	HashMap oHash=oASelFW.readDataFromAccessDB(sSelectSQL);
	return oHash;		
}


/**
 * @author naresh_akkala
 * @throws IOException 
 * @Date:10/03/14
 * This method is to insert into Properties file.
 */
public void insertIntoPropertiesFile(String propertiesfile,HashMap<String,String> ohash) {
	try {
		FileOutputStream fos=new FileOutputStream(new File(oASelFW.sAutomationPath+"VMWare\\Constants\\"+propertiesfile+".properties"),true);
		Properties prop=new Properties();
		prop.putAll(ohash);
		prop.store(fos, "Order details");
		fos.close();
	} catch (Exception e) {

		e.printStackTrace();
	}

}

public void longWaitUntilElementVisible(String ConFile,String locator) throws InterruptedException
{
	
	UtilityMethods utility 			= new UtilityMethods(oASelFW);
	WebDriverWait wait = new WebDriverWait(oASelFW.driver, 120);
	String actualLocator = "";
	
	locator  = utility.getValuesFromPropertiesFile(ConFile,locator);

	

	if(locator.startsWith("css"))
	{
		actualLocator = locator.substring(4);			
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(actualLocator)));			
	}
	if(locator.startsWith("xpath"))
	{
		actualLocator = locator.substring(5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(actualLocator)));
	}
	if(locator.startsWith("//"))
	{
		actualLocator = locator;
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(actualLocator)));
	}
	if(locator.startsWith("id"))
	{
		actualLocator = locator.substring(3);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(actualLocator)));
	}
	if(locator.startsWith("link"))
	{
		actualLocator = locator.substring(5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(actualLocator)));
	}
	if(locator.startsWith("name"))
	{
		actualLocator = locator.substring(5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(actualLocator)));
	}
	Thread.sleep(2000);
} 

public void waitUntilElementVisible(String ConFile,String locator) throws InterruptedException
{
	
	UtilityMethods utility 			= new UtilityMethods(oASelFW);
	WebDriverWait wait = new WebDriverWait(oASelFW.driver, 60);
	String actualLocator = "";
	
	locator  = utility.getValuesFromPropertiesFile(ConFile,locator);

	

	if(locator.startsWith("css"))
	{
		actualLocator = locator.substring(4);			
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(actualLocator)));			
	}
	if(locator.startsWith("xpath"))
	{
		actualLocator = locator.substring(5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(actualLocator)));
	}
	if(locator.startsWith("//"))
	{
		actualLocator = locator;
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(actualLocator)));
	}
	if(locator.startsWith("id"))
	{
		actualLocator = locator.substring(3);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(actualLocator)));
	}
	if(locator.startsWith("link"))
	{
		actualLocator = locator.substring(5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(actualLocator)));
	}
	if(locator.startsWith("name"))
	{
		actualLocator = locator.substring(5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(actualLocator)));
	}
	Thread.sleep(2000);
} 









public String getValidationMsge(String propertiesFileName,String key){
	try{
		Properties prop=new Properties();
		prop.load(new FileInputStream(oASelFW.sAutomationPath+oASelFW.sProjectName+File.separator+"Constants"+File.separator+propertiesFileName+".properties"));
		return prop.get(key).toString();
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return null;

}
/**
 * @author naresh_akkala
 * @throws IOException 
 * @throws FileNotFoundException 
 * @Date 07/02/2014
 * @param dbName(database table Name given in properties file),testCaseName(testcaseId),keyValues(database column heading,value) in hash map,String databaseName if database is other than MYVMWare else no need to pass parameter.
 * This method is used to update data into database if testcaseid is present,else insert data in to any database 
 * Note:Any doubts please contact naresh kumar akkala
 */
public void updateOrInsertIntoDataBase(String tableName,String testCaseName,HashMap<String,String> keyValues,String... dbName) throws FileNotFoundException, IOException{
	String dbname="MYVMware";
	Properties prop= new Properties();
	String sData="";
	Set<String> set=null;
	prop.load(new FileInputStream(oASelFW.sAutomationPath+oASelFW.sProjectName+File.separator+"Constants"+File.separator+"testCaseNames.properties"));
	String tableNme=prop.getProperty(tableName);
	String sSelectSQL = "Select count(*)  from "+tableNme+" where sTestCaseID = '"+testCaseName+"' and sEnvironment = '"+oASelFW.instanceName+"'";
	System.out.println(sSelectSQL);
	HashMap<String,String> oHash=oASelFW.readDataFromAccessDB(sSelectSQL);
  if(oHash.get("Expr1000").equals("0")){
		sData="insert into  "+tableNme+"(sTestcaseId,";
  set=keyValues.keySet();
		for(String key:set){
			sData=sData+key+" ,";
		}
		sData=sData+"sEnvironment) values('"+testCaseName+"','";
		for(String keyset:set){
			sData=sData+keyValues.get(keyset)+"','";
		}
		sData=sData+oASelFW.instanceName+"')";
		}
  else{
	 sData="update "+tableNme+" set ";
	 set=keyValues.keySet();
		for(String key:set){
			sData=sData+key+"= '"+keyValues.get(key)+"' ,";
		}
		sData=sData.substring(0, sData.lastIndexOf(","));
		sData=sData+" where  sTestcaseId='"+testCaseName+"' and sEnvironment='"+oASelFW.instanceName+"'";
  }
  try{
  if(dbName[0].length()>0){
	  dbname=prop.getProperty(dbName[0]);
  }
  }
  catch(Exception e){
	  System.out.println("dbname not given");
  }
  System.out.println(sData);
  oASelFW.insertDataIntoAccessDB(oASelFW.sAutomationPath+"Data\\"+oASelFW.sProjectName+"\\"+dbname,sData);
}



public HashMap readDataFromOracleDB(String sSQL) throws SQLException {
	//String dbPath = "MYVMWare";
	Connection conn = getConnectionObj();
	HashMap namelist = new HashMap();
	Statement smt = null;
	try {
		smt = conn.createStatement();
		smt.execute(sSQL);
		ResultSet rs = smt.getResultSet();
		ResultSetMetaData rsmd = rs.getMetaData();
		rs.next();
		for (int i = 1; i <= rsmd.getColumnCount(); ++i) {
			String sColName = rsmd.getColumnName(i);
			String sColVal = rs.getString(i);
			namelist.put(sColName, sColVal);
		}
		smt.close();
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}

	return namelist;
}


public Connection getConnectionObj() throws SQLException {
	
	Connection conn = null;
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //;conn = DriverManager.getConnection("jdbc:oracle:thin:@//ora-uat-ebs-r11.vmware.com:1521/vmwuat","appsrdonly","apps1234");
        conn = DriverManager.getConnection("jdbc:oracle:thin:@//ora-stage-ebs-r1-vip.vmware.com:1521/VMWSTAGE","appsrdonly","uy64jfg53");
    }catch (ClassNotFoundException e1) {
        e1.printStackTrace();
    }
    return conn;
}




/*public String[][] getTableArray(String xlFilePath, String sheetName,
        String tableName) {
     String[][] tabArray = null;
     try {
        // System.out.println(xlFilePath);
        Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
        // System.out.print(workbook);
        Sheet sheet = workbook.getSheet(sheetName);
        int startRow, startCol, endRow, endCol, ci, cj;
        Cell tableStart = sheet.findCell(tableName);
        startRow = tableStart.getRow();
        startCol = tableStart.getColumn();
        Cell tableEnd = sheet.findCell(tableName, startCol + 1,
                    startRow + 1, 100, 64000, false);
        endRow = tableEnd.getRow();
        endCol = tableEnd.getColumn();
        // System.out.println("startRow="+startRow+", endRow="+endRow+", " +
        // "startCol="+startCol+", endCol="+endCol);
        tabArray = new String[endRow - startRow - 1][endCol - startCol - 1];
        ci = 0;
        for (int i = startRow + 1; i < endRow; i++, ci++) {
                 cj = 0;
                 for (int j = startCol + 1; j < endCol; j++, cj++) {
                    tabArray[ci][cj] = sheet.getCell(j, i).getContents();
                 }
        }
     } catch (Exception e) {
        System.out.println("error in getTableArray()");
     }
     return (tabArray);
}
*/

/**
 * @author Balakrishna Deema 
 */

public String[] get_testdataLinkValues(String dbpath,String sSQL){

	Connection conn = oASelFW.getConnectionObj(dbpath);
	Statement smt = null;
	String testData[] =new String[200];
	try
	{
		smt = conn.createStatement();
		smt.execute(sSQL);
		ResultSet rs = smt.getResultSet();
		int i=0;
		while(rs.next())
		{ 
			String str1 = rs.getString("TestDataTableLinkID");
			System.out.println("%%%%%%%%%"+str1);
			testData[i]=str1;
			i++;

		}
		smt.close();
		conn.close();
	}
	catch(Exception e){

	}

	return testData;

}
/**
 * @author Balakrishna Deema 
 */

public String[] get_TPSNum(String dbpath,String sSQL){

	Connection conn = oASelFW.getConnectionObj(dbpath);
	Statement smt = null;
	String testData[] =new String[200];
	try
	{
		smt = conn.createStatement();
		smt.execute(sSQL);
		ResultSet rs = smt.getResultSet();
		int i=0;
		while(rs.next())
		{ 
			String str1 = rs.getString("TPSNumber");
			System.out.println("%%%%%%%%%"+str1);
			testData[i]=str1;
			i++;

		}
		smt.close();
		conn.close();
	}
	catch(Exception e){

	}

	return testData;

}


public String[] get_Template(String dbpath,String sSQL){

	Connection conn = oASelFW.getConnectionObj(dbpath);
	Statement smt = null;
	String testData[] =new String[200];
	try
	{
		smt = conn.createStatement();
		smt.execute(sSQL);
		ResultSet rs = smt.getResultSet();
		int i=0;
		while(rs.next())
		{ 
			String str1 = rs.getString("ID");
			System.out.println("%%%%%%%%%"+str1);
			testData[i]=str1;
			i++;

		}
		smt.close();
		conn.close();
	}
	catch(Exception e){

	}

	return testData;

}
public static void setClipboardData(String fileLocation) {
    StringSelection stringSelection = new StringSelection(fileLocation);
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
 }

public void uploadFile_CommonUpload(String fileLocation) throws InterruptedException{
Thread.sleep(2000);
try {
  setClipboardData(fileLocation);
  Robot robot = new Robot();          
  robot.keyPress(KeyEvent.VK_CONTROL);
  robot.keyPress(KeyEvent.VK_V);
  robot.keyRelease(KeyEvent.VK_V);
  robot.keyRelease(KeyEvent.VK_CONTROL);
  robot.keyPress(KeyEvent.VK_ENTER);
  robot.keyRelease(KeyEvent.VK_ENTER);
} catch (Exception exp) {
    exp.printStackTrace();
}
}

//Siddharth

public Boolean WaitOnElement(String Element, int Time )
{
	try{
	WebDriverWait wait=new WebDriverWait(oASelFW.driver, 10);
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Element)));
	return true;
	}
	catch(org.openqa.selenium.NoSuchElementException e){
		System.out.println("no such element exception");
		return false;
	}

}

public void ClickOnElement(String Element, String ElementType)
{
	WebDriverWait wait=new WebDriverWait(oASelFW.driver, 10);
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Element)));
	oASelFW.effecta("click",Element,ElementType);
	System.out.println("Clicked on "+ElementType);
	
}
public void SetTextField(String xpath, String Value, String FieldType)
{
	if(WaitOnElement(xpath,20))
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
public int GenerateRandomNoBetweenLimits(int upper, int lower)
{
	 int r = (int) (Math.random() * (upper - lower)) + lower;
	 return r;
}

}
