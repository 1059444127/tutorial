package com.kingmed.dp.gateway.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kingmed.dp.dto.CstCaseEvent;
import com.kingmed.dp.gateway.constants.Constants;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

public class TestObpmCommand extends HystrixCommand<String>{

	private static final Logger LOG = LoggerFactory.getLogger(TestObpmCommand.class);
	
	private static Gson gson = new GsonBuilder().create();
	
	private CstCaseEvent event;
	
	private ConsultObpmClient consultObpmClient;
	
			
	public TestObpmCommand(CstCaseEvent event) {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ConsultCaseObpmGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("ConsultCaseObpmKey"))
                /* 配置依赖超时时间,10000毫秒*/ 
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(10000))
        		);
		this.event = event;
		
		consultObpmClient = Feign.builder()
				//.decoder(new GsonDecoder())
				//.encoder(new GsonEncoder())
				.logger(new feign.Logger.ErrorLogger())
		        .logLevel(feign.Logger.Level.BASIC)
				.target(ConsultObpmClient.class, "http://localhost:8040/dp-consult-obpm");
	}
	
	@Override
	protected String run() throws Exception {
		
		String r = consultObpmClient.doTest("1");
		return r;
	}
	
	@Override
	protected String getFallback() {
		LOG.error("测试, 失败 " + JSON.toJSONString(event));
		return "FAIL";
	}
	
	
}
