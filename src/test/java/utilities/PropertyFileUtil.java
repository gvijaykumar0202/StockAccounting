package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class PropertyFileUtil {

	public static String getValueForKey (String key) throws Exception 
	{
		Properties p = new Properties();
		
		FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+"\\PropertiesFiles\\dummy.properties");
		p.load(fi);
		String keyvalue= (String) p.get(key);
		return keyvalue;
	}

}
