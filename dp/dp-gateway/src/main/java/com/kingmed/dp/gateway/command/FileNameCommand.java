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
public class FileNameCommand extends HystrixCommand<String>{
	
    private final ParamsDto params;
    
    private DocService doc = DocService.getInstance();
    
    public FileNameCommand (ParamsDto params) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("FileNameGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("FileNameURL"))
                /* 配置依赖超时时间,3000毫秒*/ 
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(3000))
        		);
        this.params = params;
    }
    
    @Override
    protected String run() throws Exception {
    	String rnt= doc.getFileName(params.getMap().get("testId").toString(), params.getMap().get("barcode").toString(), params.getMap().get("antiBody").toString());
    	return rnt;
    }
    
    @Override
    protected String getFallback() {
    	return "Fail";
    }
    
    
    
}
