package com.orderfood.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiUtil {
	private static Properties props;
	static {
		loadProp();
	}
	public static void loadProp() {
		props=new Properties();
		InputStream is=null;
		
		try {
			is=PropertiUtil.class.getClassLoader().getResourceAsStream("config.properties");
			props.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String get(String key) {
		if(null==props) {
			loadProp();;
		}
		return props.getProperty(key,"");
		
	}
}
