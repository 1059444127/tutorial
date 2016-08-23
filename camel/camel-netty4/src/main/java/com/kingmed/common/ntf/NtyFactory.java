package com.kingmed.common.ntf;

public class NtyFactory {
	
	public static String constructNewCstSMS(String itemId){
		return "您好，您有一例新的会诊病例，（病理号)" + itemId +"，请登录诊断，谢谢。 http://epathology.kingmed.com.cn:99/obpm【金域检验远程病理会诊平台】";
	}
	
	public static String constructAddIHCSMS(String itemId){
		return "您好，您有一例加做免疫组化已经完成并已资料更新，（病理号)" + itemId +"，请登录诊断，谢谢。 http://epathology.kingmed.com.cn:99/obpm【金域检验远程病理会诊平台】";
	}
	
}
