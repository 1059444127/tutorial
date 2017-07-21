package com.kingmed.dp.servlet.modules;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.kingmed.dp.servlet.config.GenericBootstrapConstants;

public class Gateway {

	private static Properties prop;
	
	static{
		prop = new Properties();
		try {
			InputStream is = new Gateway().getClass().getResourceAsStream("/"+GenericBootstrapConstants.GATEWAY_SYS);
			prop.load(is);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public static String gateways(String key) {
        try {
			return prop.getProperty(key).trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "";
	}
}
