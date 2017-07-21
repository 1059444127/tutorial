package com.kingmed.dp.lis.facade;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.kingmed.dp.gateway.util.ModelUtil;
import com.kingmed.dp.gateway.web.msg.SlideReadyMsg;

/**
 * 智方LIS
 * @author zhengjunjie
 *
 */
public class ZhiFangLISFacade implements LISFacade{
	private static Logger logger = Logger.getLogger(ZhiFangLISFacade.class);
	
	@Override
	public Response slideReady(SlideReadyMsg slide) {
		Gson gson = new Gson();
		String msg = gson.toJson(slide);
		Response res = new Response();
		try {
			res = this.doPost(msg);
		} catch (Exception e) {
			e.printStackTrace();
			res.setErrorCode("-1");
			res.setErrorMsg("内部错误");
		}
		return res;
	}
	
	/**
	 * 查询LIS目标服务，智方的LIS在共建点独立部署，存在多个服务
	 * @param slide
	 * @return
	 */
	public String getTarget(SlideReadyMsg slide){
		//TODO
		throw new UnsupportedOperationException();
	}
	
	private Response doPost(String msg) throws Exception {
		Response res = new Response();
		CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
        	String path = ModelUtil.SLIDE_READY_4_GT;
            HttpPost httppost = new HttpPost(path);
            httppost.addHeader("Content-Type", "application/json;charset=UTF-8");
            
            StringEntity entry = new StringEntity(msg);
            httppost.setEntity(entry);

            logger.info("slideReady4GT, Executing request: " + httppost.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                String re = EntityUtils.toString(response.getEntity());
                logger.info("slideReady4GT, msg = " +msg +", " + response.getStatusLine()+",entity = " + re);
                Gson gson = new Gson();
                res =gson.fromJson(re, Response.class);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
        return res;
	}

}
