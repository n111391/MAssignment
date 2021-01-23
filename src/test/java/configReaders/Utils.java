package configReaders;

import java.io.FileInputStream;
import java.util.Properties;
import org.junit.Assert;


//This method is used to read config properties
public class Utils {

	Properties props = new Properties();

	public String readConfig(String key){

		String result= "";

		try{
			props.load(new FileInputStream("config.properties"));
			result= props.getProperty(key);
		}catch (Exception e){
			Assert.fail(e.getMessage());
		}
		return result;
	}

}
