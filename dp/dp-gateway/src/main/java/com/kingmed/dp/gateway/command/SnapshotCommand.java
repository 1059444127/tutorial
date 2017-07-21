package com.kingmed.dp.gateway.command;

import org.apache.log4j.Logger;

import com.kingmed.dp.gateway.dto.ParamsDto;
import com.kingmed.dp.gateway.dto.SnapshotDto;
import com.kingmed.dp.gateway.util.HttpRequest;
import com.kingmed.dp.gateway.util.ModelUtil;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class SnapshotCommand extends HystrixCommand<String>{

	private static Logger logger = Logger.getLogger(SnapshotCommand.class);
	
	private final ParamsDto params;
	
	private final String type;
    
	public SnapshotCommand(ParamsDto params,String type) {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("SnapshotGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("SnapshotURL"))
                /* 配置依赖超时时间,10000毫秒*/ 
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(10000))
        		);
        this.params = params;
        this.type = type;
	}
	
	@Override
	protected String run() throws Exception {
		logger.info("params:"+params.getMap().toString());
		String result = "0";
		if(SnapshotDto.ADD_TYPE.equals(type)){
			result = HttpRequest.postMethod(ModelUtil.OBPM_WS_PRO + "/DPMS/snapshotAdded", params.getMap(), "");
		}else if(SnapshotDto.DELETE_TYPE.equals(type)){
			result = HttpRequest.postMethod(ModelUtil.OBPM_WS_PRO + "/DPMS/snapshotDeleted", params.getMap(), "");
		}else if(SnapshotDto.SLIDE_TYPE.equals(type)){
			result = HttpRequest.postMethod(ModelUtil.OBPM_WS_PRO + "/DPMS/slideReady", params.getMap(), "");
		}
		return result;
	}
	
}
