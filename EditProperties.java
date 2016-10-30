package classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class EditProperties {
	BufferedReader br = null;
	BufferedWriter bw = null;
	public static String basedir    = System.getProperty("user.dir");
	public void edProperties(String instance)
	{

		try {
			FileInputStream fis = new FileInputStream(basedir + File.separator + File.separator + "src" + File.separator + "config.properties");
			Properties prop     = new Properties();
			prop.load(fis);
			System.out.println(prop.getProperty("instance"));
			prop.remove("instance");
			System.out.println("After remove="+prop.getProperty("instance"));
			prop.put("instance", instance);
			System.out.println(prop.getProperty("instance"));
			prop.store(new FileOutputStream(basedir + File.separator + File.separator + "src" + File.separator + "instance.properties"),"updated instance");

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void main(String args[])
	{
		EditProperties oprop = new EditProperties();
		oprop.edProperties(args[0]);
	}
}
