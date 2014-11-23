package pl.edu.agh.universallib.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

	private PropertiesLoader(){
		// hidden constructor
	}
	
	public static String getWebServiceAddress(){
		return loadProperties().getProperty("webservice.url");
	}
	
	private static Properties loadProperties() {

		Properties properties = new Properties();
		InputStream input = null;

		try {

			String filename = "webservice.properties";
			input = PropertiesLoader.class.getClassLoader().getResourceAsStream(filename);
			if (input == null) {
				System.out.println("Unable to find " + filename);
				return properties;
			}
			properties.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return properties;

	}
}