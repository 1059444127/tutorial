/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kingmed.dp.gateway.command;

import com.kingmed.dp.gateway.client.DocService;
import com.kingmed.dp.gateway.constants.Constants;
import com.kingmed.dp.gateway.dto.ProjectParamDto;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 *
 * @author zhengjunjie
 */
public class ProjectParamCommand extends HystrixCommand<String>{
	
    private final ProjectParamDto projectParam;
    
    private DocService doc = DocService.getInstance();
    
    public ProjectParamCommand (ProjectParamDto projectParam) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ProjectParamGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("ProjectParamURL"))
                /* 配置依赖超时时间,3000毫秒*/ 
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(3000))
        		);
        this.projectParam = projectParam;
    }
    
    @Override
    protected String run() throws Exception {
    	int rnt = Constants.IS_TRUE;
		rnt= doc.updateDocumentByDomainUser(projectParam.getDocumentId(), projectParam.getParameters(), projectParam.getDomainUserId(), projectParam.getApplicationId());
		if(rnt != Constants.SUCCESS){
			rnt = Constants.IS_FALSE;
		}
    	return rnt+"";
    }
    
    @Override
    protected String getFallback() {
    	return "Fail";
    }
    
    
    
}
