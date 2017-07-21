package com.kingmed.dp.gateway.constants;

public class Constants {
	
	public static final String SMS = "sms";
	
	public static final String EMAIL = "email";
	
	public static final String LIR = "LIR";
	
	public static final String OBPM = "OBPM";
	
	public static final String NEW_CST = "NewCst";
	
	public static final String ADD_IHC = "AddIHC";
	
	public static final String CHAT = "Chat";
	
	public static final String DP = "dp";
	
	public static final int IS_TRUE = 1;
	
	/*方法返回失败的标志*/
	public static final int IS_FALSE = -1;
	
	/*方法返回成功的标志*/
	public static final int SUCCESS = 0;
	
	/*配置dp-webapp相关开关*/
	public static final String DPWEBAPP_CLOSEANDOFF = "1";
	
	/*配置是否向mq发送消息*/
	public static final String ISTOMQ = "1";
	
	/*切片上传失败是否发送短信*/
	public static final String TOSLIDEOFF = "1";
	
	/*切片上传 发送到MQ警告消息类型*/
	public static final String SLIDE_TYPE = "Slide";
	
	public static final String SLIDE_FROM = "other";
	
	/*数字切片扫描仪IP地址异常变动 短信类型标志*/
	public static final String CLIENT_TYPE = "CientIP";
	
	public static final String CLIENT_FROM = "Cient";
	
	/*加做完成时间保持接口url*/
	public static final String FIN_ADD_PROJECT = "/dp/addProJect/result/save";

	/*该值时，监控客户端ip发生了改变，需要发送短信改管理员*/
	public static final String CLIENT_IP_STATE_TRUE = "1";
	public static final String CLIENT_IP_STATE_FAIL = "-1";
	
	
	
}
