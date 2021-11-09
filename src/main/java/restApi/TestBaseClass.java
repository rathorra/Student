package restApi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBaseClass 

{
	public void TestBase() {
		try {
			Properties prop = new Properties();
			FileInputStream ip=new FileInputStream("C:\\Users\\Dinesh.Kanna\\eclipse-workspace\\IGT\\src\\main\\java\\config\\Configuration.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	

}
