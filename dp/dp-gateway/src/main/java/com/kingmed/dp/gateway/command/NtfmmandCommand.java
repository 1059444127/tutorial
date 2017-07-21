/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kingmed.dp.gateway.command;

import java.util.Map;

import org.apache.log4j.Logger;

import com.kingmed.dp.gateway.constants.Constants;
import com.kingmed.dp.gateway.util.HttpRequest;
import com.kingmed.dp.gateway.util.ModelUtil;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 *
 * @author zhanglei
 */
public class NtfmmandCommand extends HystrixCommand<String>{
	private static Logger logger = Logger.getLogger(NtfmmandCommand.class);
    private final Map<String,String> map;
    
    public NtfmmandCommand (Map<String,String> map) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ntfjson"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("ntfjson"))
                /* 配置依赖超时时间,3000毫秒*/ 
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(3000))
        		);
        this.map = map;
    }
    
    @Override
    protected String run() throws Exception {
    	int rnt = Constants.SUCCESS;
    	try {
			HttpRequest.post(ModelUtil.Q_NTY_INPUT, map,ModelUtil.MQ_USERNAME,ModelUtil.MQ_PASSWORD);
		} catch (Exception e) {
			rnt = Constants.IS_FALSE;
			logger.error("推送信息到消息队列异常，异常信息："+map,e);
		}
    	return rnt+"";
    }
    
    @Override
    protected String getFallback() {
    	return "Fail";
    }
    
    
    
}
