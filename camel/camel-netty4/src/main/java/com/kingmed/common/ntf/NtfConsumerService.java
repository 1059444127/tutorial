package com.kingmed.common.ntf;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author zhengjunjie
 *
 */
public class NtfConsumerService implements Processor {

	private static final Logger logger = LoggerFactory.getLogger(NtfConsumerService.class);
	
	public void process(Exchange msg) throws Exception {
		logger.trace("输入通知{}", msg);
        String record = msg.getIn().getBody(String.class);
        logger.info("通知内容", record);
        

       msg.getOut().setBody("return");
        
       
       JSONTokener jsonTokener = new JSONTokener(record);
       JSONObject ntfJSONObject;
       String type;
       String from;
       String pathologyExprert;
       
       try {
    	   ntfJSONObject = (JSONObject) jsonTokener.nextValue();
    	   type = ntfJSONObject.getString("type");
    	   from = ntfJSONObject.getString("from");
    	   if ("NewCst".equals(type)) { //LIS新会诊
    		   pathologyExprert = ntfJSONObject.getString("pathologyExpert");
    	   } else if ("AddIHC".equals(type)) { //LIS加做免疫组化
    		   pathologyExprert = ntfJSONObject.getString("pathologyExpert");
    	   }
    	   
    	   //根据pathologyExpert从OBPM DocumentWebService查询专家的手机号
    	   //组装短信内容
    	   //调用YunpianService 发送短息
    	   
       }catch(JSONException e) {
    	   logger.error("解析通知数据格式出错,{}", record);
       }

	}

}
