package com.kingmed.dp.gateway.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kingmed.dp.dto.CstSlideEvent;
import com.kingmed.dp.gateway.constants.Constants;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

public class ConsultSlideObpmCommand extends HystrixCommand<Integer>{

	private static final Logger LOG = LoggerFactory.getLogger(ConsultSlideObpmCommand.class);
	
	private static Gson gson = new GsonBuilder().create();
	
	private CstSlideEvent event;
	
	private ConsultObpmClient consultObpmClient;
	
			
	public ConsultSlideObpmCommand(CstSlideEvent event) {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ConsultCaseObpmGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("ConsultCaseObpmKey"))
                /* 配置依赖超时时间,10000毫秒*/ 
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(10000))
        		);
		this.event = event;
		
		consultObpmClient = Feign.builder()
				.decoder(new GsonDecoder())
				.encoder(new GsonEncoder())
				.target(ConsultObpmClient.class, "http://localhost:8040/dp-consult-obpm");
	}
	
	@Override
	protected Integer run() throws Exception {
		String eventGson = gson.toJson(event);
		LOG.info("请求数字切片事件 " + eventGson);
		
		int r = consultObpmClient.doUploadSlide(eventGson);
		LOG.info("请求数字切片，返回值: " + r);
		return r;
	}
	
	@Override
	protected Integer getFallback() {
		LOG.error("请求数字切片, 失败 " + JSON.toJSONString(event));
		return Constants.IS_FALSE;
	}
	
	
}
