/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kingmed.dp.gateway.command;

import com.kingmed.dp.gateway.client.DocService;
import com.kingmed.dp.gateway.dto.ParamsDto;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 *searchDocument与removeDocument方法调用   已注释掉了
 * @author zhanglei
 */
public class UsersCommand extends HystrixCommand<String>{
	
    private final ParamsDto params;
    
    private DocService doc = DocService.getInstance();
    
    public UsersCommand (ParamsDto params) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("UsersGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("UsersURL"))
                /* 配置依赖超时时间,10000毫秒*/ 
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(10000))
        		);
        this.params = params;
    }
    
    @Override
    protected String run() throws Exception {
    	String rnt= doc.getAllUsers();
    	return rnt;
    }
    
    @Override
    protected String getFallback() {
    	return "Fail";
    }
    
    
    
}
