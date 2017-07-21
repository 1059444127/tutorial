package com.kingmed.dp.gateway.client;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.rpc.holders.StringHolder;

import org.apache.axis.encoding.Base64;
import org.apache.log4j.Logger;
import org.tempuri.IPath;
import org.tempuri.IPathProxy;

import com.kingmed.dp.gateway.constants.Constants;
import com.kingmed.dp.gateway.dto.SlideDto;
import com.kingmed.dp.gateway.util.HttpRequest;
import com.kingmed.dp.gateway.util.ModelUtil;

public class LISService {
	
	private static Logger logger = Logger.getLogger(LISService.class);

	private static final String TEST = "00.";
	
	private static final String ONE = "1";
	
	private static final String ZERO = "0";
	
	public static LISService lISService;
	
	IPath iPath= new IPathProxy(ModelUtil.url);
	
	IPath test_iPath= new IPathProxy(ModelUtil.url_test);
	
	private LISService() {
	}
	
	public static LISService getInstance() {
		if(lISService==null){
			lISService = new  LISService();
		}
		return lISService;
	}
	
	public Map<String,Object> sendResultInfo(SlideDto slide ){
		logger.info("参数:"+slide);
		Map<String,Object> result = new HashMap<String,Object>();
		StringHolder msg = new StringHolder();
    	StringHolder _return = new StringHolder();
    	try {
    		String dpResult = "";
    		if(Constants.DPWEBAPP_CLOSEANDOFF.equals(ModelUtil.IS_TO_DP)){//关联dp-webapp的开关
    	    	//保存数据到dp-webapp中
    	    	String cookie = HttpRequest.postMethodLogin(ModelUtil.dp_url+"/dp/login",ModelUtil.getDPLoginData());
    			if(!"".equals(cookie)){
    				dpResult = HttpRequest.postMethod(ModelUtil.dp_url+"/cons/updateAll/slide",ModelUtil.getSlideMap(slide),cookie);
    				logger.info("保存数字切片dp-webapp返回结果："+dpResult);
    			}else{
    				logger.error("dp-webapp连接异常！");
    			}
        	}
    		sendResultInfo(slide,msg,_return);
    		logger.info("上传数字切片web Service返回结果："+_return.value);
			
			if(ZERO.equals(_return.value)){//上传切片成功
				result.put("status", Constants.SUCCESS);
			}else if(ONE.equals(dpResult)){//上传切片失败，保存日志成功
				boolean rs = false;
				//重复上传,取配置的重复上传次数
				int sum = Integer.parseInt(ModelUtil.UPLOAD_NUMBER);
				for(int i=0;i<sum;i++){
					sendResultInfo(slide,msg,_return);
					if(ZERO.equals(_return.value)){//上传成功
						rs = true;
						break;
					}
				}
				if(Constants.TOSLIDEOFF.equals(ModelUtil.TO_SLIDE_OFF)){
					if(!rs){//上传失败
						//发送短信
						String res = ModelUtil.sendMQMessage(slide.getId(), slide.getBarcode());
						if(!ZERO.equals(res)){//短信发送失败
							logger.error("切片上传失败，警告短信发送失败！"+slide);
						}
					}
				}
				result.put("status", Constants.SUCCESS);
			}else if(!ONE.equals(dpResult)){
				result.put("status", Constants.IS_FALSE);
				result.put("msg", "系统繁忙!");
			}
    		
    	} catch (Exception e) {
    		result.put("status", Constants.IS_FALSE);
			result.put("msg", "执行异常!");
			logger.error("barcode："+slide.getBarcode() + "上传数字切片数据执行异常：",e);
		}
    	return result;
	}
	
	//单独的上传切片到lir方法
	private void sendResultInfo(SlideDto slide,StringHolder msg,StringHolder _return) throws RemoteException{
		if(slide.getBarcode().startsWith(TEST)){//"00."开头的上传到测试lir
			test_iPath.sendResultInfo(slide.getBarcode(), slide.getUrl(), slide.getScannerModel(), slide.getLabelWithOverview(), msg, _return);
		}else{//前面是两位数字，则上传生产lir    if(slide.getBarcode().substring(0, slide.getBarcode().indexOf(".")).length()==2)
			iPath.sendResultInfo(slide.getBarcode(), slide.getUrl(), slide.getScannerModel(), slide.getLabelWithOverview(), msg, _return);
		}
	}
	
	/**
     * 图片转化成base64字符串
     * @param filePath
     * @return
     * @throws Exception
     */
    public static String imgToBase64String(String filePath) throws Exception {
		// new一个URL对象
		URL url = new URL(filePath);
		// 打开链接
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// 设置请求方式为"GET"
		conn.setRequestMethod("GET");
		// 超时响应时间为5秒
		conn.setConnectTimeout(10 * 1000);
		// 通过输入流获取图片数据
		InputStream inStream = conn.getInputStream();
		// 得到图片的二进制数据，以二进制封装得到数据，具有通用性
		byte[] data = readInputStream(inStream);

		String r = Base64.encode(data);
		return r;
	}
    
    public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		// 创建一个Buffer字符串
		byte[] buffer = new byte[1024];
		// 每次读取的字符串长度，如果为-1，代表全部读取完毕
		int len = 0;
		// 使用一个输入流从buffer里把数据读取出来
		while ((len = inStream.read(buffer)) != -1) {
			// 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
			outStream.write(buffer, 0, len);
		}
		// 关闭输入流
		inStream.close();
		// 把outStream里的数据写入内存 ip:10.5.6.130
		return outStream.toByteArray();
	}
	
}
