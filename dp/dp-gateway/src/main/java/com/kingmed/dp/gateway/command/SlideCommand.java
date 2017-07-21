/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kingmed.dp.gateway.command;

import java.util.Map;

import org.apache.log4j.Logger;

import com.kingmed.dp.gateway.client.LISService;
import com.kingmed.dp.gateway.constants.Constants;
import com.kingmed.dp.gateway.dto.SlideDto;
import com.kingmed.dp.gateway.util.ModelUtil;
import com.kingmed.dp.gateway.web.msg.SlideReadyMsg;
import com.kingmed.dp.lis.facade.LISFacade;
import com.kingmed.dp.lis.facade.Response;
import com.kingmed.dp.lis.facade.ZhiFangLISFacade;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

import net.sf.json.JSONObject;

/**
 *
 * @author zhengjunjie
 */
public class SlideCommand extends HystrixCommand<String>{
	
	private static Logger logger = Logger.getLogger(SlideCommand.class);
	
    private final SlideDto slide;
    
    private LISService lis = LISService.getInstance();
    
    private LISFacade zhifangLIS = new ZhiFangLISFacade();
    public SlideCommand (SlideDto slide) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("SlideGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("SlideURL"))
                /* 配置依赖超时时间,10000毫秒*/ 
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(10000))
        		);
        this.slide = slide;
    }
    
    public void setZhifangLIS(LISFacade zhifangLIS) {
		this.zhifangLIS = zhifangLIS;
	}

	@Override
    protected String run() throws Exception {
    	logger.info("数字切片信息," + slide.toString());
		Map<String,Object> result = ModelUtil.validSlide(slide);
    	
    	//TODO 根据 slide.user查询路由配置，根据路由配置决定目标LIS
    	String user = slide.getUser();
    	String barcode = slide.getBarcode();
    	
    	if(result.size()==0){
    		//广州金沙洲共建系统智方LIS
        	if("gzjsz".equalsIgnoreCase(user)){
        		String[] barcodeArray=barcode.split("_");
        		//条码由智方LIS生成
            	if ("GD001".equalsIgnoreCase(barcodeArray[1])) {
            		logger.info("上传数字切片，条码为高通系统创建, " +slide.getBarcode());
            		SlideReadyMsg slideReadyMsg = new SlideReadyMsg();
            		slideReadyMsg.setBarcode(slide.getBarcode());
            		slideReadyMsg.setThumbnail(slide.getLabelWithOverview());
            		slideReadyMsg.setUrl(slide.getUrl());
            		slideReadyMsg.setViewer("motic");
            		Response res = zhifangLIS.slideReady(slideReadyMsg);
            		result.put("status", res.getErrorCode().equals("1")?"0":"-1");
            		result.put("msg", res.getErrorMsg());
            	}else {
            		logger.info("上传数字切片，条码为LIR系统创建， " + slide.getBarcode());
            		result = lis.sendResultInfo(slide);
            	}
        	} else {//缺省，条码由LIR生成
        		logger.info("上传数字切片，条码为LIR系统创建， " + slide.getBarcode());
        		result = lis.sendResultInfo(slide);
        	}
    	}else{
    		logger.warn("数字切片信息格式错误 " + slide.getBarcode());
    		result.put("status", Constants.IS_FALSE);
    	}
    	String re = JSONObject.fromObject(result).toString();
    	logger.warn("返回值，" + re);
    	return re;
    }
    
    @Override
    protected String getFallback() {
    	logger.error("上传数字切片出现错误 " + slide.getBarcode());
    	return "Fail";
    }
    
    
    
}
