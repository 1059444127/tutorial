/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kingmed.dp.gateway.command;

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
public class ProjectResultCommand extends HystrixCommand<String>{
	private static Logger logger = Logger.getLogger(ProjectResultCommand.class);
    private final String params;
    
    @SuppressWarnings("deprecation")
	public ProjectResultCommand (String params) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("dpObpmWsjson"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("dpObpmWsjson"))
                /* 配置依赖超时时间,10000毫秒*/ 
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(10000))
        		);
        this.params = params;
    }
    
    @Override
    protected String run() throws Exception {
    	int rnt = Constants.SUCCESS;
    	try {
			HttpRequest.sendGet(ModelUtil.OBPM_WS_PRO+Constants.FIN_ADD_PROJECT, params);
		} catch (Exception e) {
			rnt = Constants.IS_FALSE;
			logger.error("加做完成时间发送异常："+params,e);
		}
    	return rnt+"";
    }
    
    @Override
    protected String getFallback() {
    	return "Fail";
    }
    
    
    
}
