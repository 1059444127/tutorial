package com.kingmed.common.ntf;

/**
 * 通知服务
 * @author zhengjunjie
 *
 */
public interface NtfServiceI {
	
	/**
	 * 发送短信息
	 * @return
	 */
	public int sendSMS(SMS smsl);
	
}
