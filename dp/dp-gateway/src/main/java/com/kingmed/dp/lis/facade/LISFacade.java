package com.kingmed.dp.lis.facade;

import com.kingmed.dp.gateway.web.msg.SlideReadyMsg;

/**
 * LISFacade,包括LIR、KMCS、zhifang
 * @author zhengjunjie
 *
 */
public interface LISFacade {
	
	/**
	 * 
	 * @param slide
	 * @return {"status":0}为成功，  {"msg":"失败信息","status":-1}为失败
	 */
	public Response slideReady(SlideReadyMsg slide);
	
}
