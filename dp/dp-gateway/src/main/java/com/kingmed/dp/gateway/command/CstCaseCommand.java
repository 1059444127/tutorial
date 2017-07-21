/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kingmed.dp.gateway.command;

import com.kingmed.dp.gateway.client.DocService;
import com.kingmed.dp.gateway.constants.Constants;
import com.kingmed.dp.gateway.dto.CstCaseDto;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 *
 * @author zhanglei
 */
public class CstCaseCommand extends HystrixCommand<String>{
	
    private final CstCaseDto cstCase;
    
    private DocService doc = DocService.getInstance();
    
    public CstCaseCommand (CstCaseDto cstCase) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CstCaseGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("CstCaseURL"))
                /* 配置依赖超时时间,3000毫秒*/ 
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(3000))
        		);
        this.cstCase = cstCase;
    }
    
    @Override
    protected String run() throws Exception {
    	int rnt = Constants.IS_TRUE;
		rnt= doc.createDocumentByDomainUser(cstCase.getCaseParams(),cstCase.getLeaveParams(),cstCase.getSectionParams(), cstCase.getDomainUserId(), cstCase.getApplicationId());
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
