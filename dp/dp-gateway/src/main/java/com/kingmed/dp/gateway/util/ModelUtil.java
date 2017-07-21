package com.kingmed.dp.gateway.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.kingmed.dp.gateway.command.NtfmmandCommand;
import com.kingmed.dp.gateway.command.ProjectResultCommand;
import com.kingmed.dp.gateway.constants.Constants;
import com.kingmed.dp.gateway.dto.SlideDto;
import com.kingmed.dp.servlet.modules.Gateway;

public class ModelUtil {

	public final static String url = Gateway.gateways("url");
	
	public final static String url_test = Gateway.gateways("url_test");
	
	public final static String UPLOAD_NUMBER = Gateway.gateways("upload_number");
	
	public final static String dp_url = Gateway.gateways("dp_url");
	
	//public final static String lisUrl = Gateway.gateways("lisUrl");
	
	public final static String ALL_USERS_URL = Gateway.gateways("all_users_url");
	
	public final static String NODEJS_PORT = Gateway.gateways("nodejs_port");
	/*切片上传失败是否发送短信开关*/
	public final static String TO_SLIDE_OFF = Gateway.gateways("to_slide_off");
	/*dp-webapp交互开关*/
	public final static String IS_TO_DP = Gateway.gateways("is_to_send");
	/*mq推送消息开关*/
	public final static String IS_TO_MQ = Gateway.gateways("is_to_mq");
	/*推送MQ信息地址*/
	public final static String Q_NTY_INPUT = Gateway.gateways("q_nty_input");
	/*加做项目完成时间处理的地址*/
	public final static String OBPM_WS_PRO = Gateway.gateways("obpm_ws_pro");
	
	public final static String MQ_USERNAME = Gateway.gateways("mq_username");
	
	public final static String MQ_PASSWORD = Gateway.gateways("mq_password");
	
	public final static String DP_USERNAME = Gateway.gateways("dp_username");
	public final static String DP_PASSWORD = Gateway.gateways("dp_password");
	
	public final static String SLIDE_READY_4_GT = Gateway.gateways("slideReady4GT");
	//public final static String POINT_USERNAME = Gateway.gateways("point_username");
	//public final static String POINT_PASSWORD = Gateway.gateways("point_password");
	
	public static Map<String,Object> getDPLoginData(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("username", DP_USERNAME);
		map.put("password", DP_PASSWORD);
		return map;
	}
	
//	public static Map<String,Object> getpointLoginData(){
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("username", POINT_USERNAME);
//		map.put("password", POINT_PASSWORD);
//		return map;
//	}
	/**
	 * 会诊-病例
	 * @param mapJson
	 * @return
	 */
	public static Map<String,Object> getCstCase(Map<String, Object> mapJson){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("caseLib.caseId", mapJson.get("ci_test_id"));
		map.put("requestId", mapJson.get("ci_request_id"));
		map.put("kmbarcode", mapJson.get("ci_request_code"));
		map.put("testCode", mapJson.get("ci_itemcode"));
		map.put("name", mapJson.get("ci_name"));
		map.put("gender", mapJson.get("ci_gender"));
		map.put("age", mapJson.get("ci_age"));
		map.put("ageUnit", mapJson.get("ci_unit"));
		map.put("cliDia", mapJson.get("ci_lczd"));
		map.put("cliMas", mapJson.get("ci_lczs"));
		map.put("famHis", mapJson.get("ci_jts"));
		map.put("othHis", mapJson.get("ci_qtbs"));
		map.put("othSup", mapJson.get("ci_qtfzjc"));
		map.put("opeOrg", mapJson.get("ci_ssbw"));
		map.put("opeFind", mapJson.get("ci_sssj"));
		map.put("priPatDia", mapJson.get("ci_czdblzd"));
		map.put("cstPatExp", mapJson.get("ci_zdz"));
		map.put("applyDate", mapJson.get("ci_sqrq"));//申请日期
		map.put("bioOrg", mapJson.get("ci_hjbw"));
		map.put("company.id", mapJson.get("ci_child_company"));
		map.put("state", mapJson.get("ci_state"));
		map.put("srcApp", mapJson.get("ci_src_app"));
		map.put("srcEnv", mapJson.get("ci_env"));
		return map;
	}
	/**
	 * 会诊-反馈信息
	 * @param mapJson
	 * @param test_id
	 * @return
	 */
	public static Map<String,Object> getCstAdvice(Map<String, Object> mapJson,String test_id){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("caseId", test_id);
		map.put("content", mapJson.get("lea_msg"));
		map.put("srcApp", mapJson.get("lea_type"));//发送系统
		return map;
	}
	/**
	 * 会诊-数字切片
	 * @param mapJson
	 * @param test_id
	 * @return
	 */
	public static Map<String,Object> getslide(Map<String, Object> mapJson,String test_id){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("caseId", test_id);
		map.put("requestId", mapJson.get("ns_request_id"));
		map.put("kmbarcode", mapJson.get("ns_request_code"));
		map.put("barcode", mapJson.get("ns_name"));
		map.put("url", mapJson.get("ns_url"));
		map.put("scanner", mapJson.get("ns_vender"));
		map.put("antitbodyName", mapJson.get("ns_antibody"));
		map.put("antitbodyCode", mapJson.get("ns_antibody"));
		map.put("overview", mapJson.get("ns_img_url"));
		return map;
	}
	/**
	 * 数字切片上传
	 * @param slide
	 * @return
	 */
	public static Map<String,Object> getSlideMap(SlideDto slide){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", slide.getId());
		map.put("barcode", slide.getBarcode() );
	    map.put("label", slide.getLabel() );
	    map.put("overview", slide.getOverview() );
	    map.put("labelWithOverview", slide.getLabelWithOverview() );
	    map.put("url", slide.getUrl() );
	    map.put("scannerModel", slide.getScannerModel() );
	    map.put("createDateTime", slide.getCreateDateTime() );
	    map.put("scannerCode", slide.getScannerCode() );
	    map.put("physicalPath", slide.getPhysicalPath() );
	    map.put("path", slide.getPath() );
	    map.put("token", slide.getToken() );
	    map.put("point", slide.getPoint() );
		return map;
	}
	/**
	 * 数字切片上传时，拼接的url,传递的参数
	 * @param slide
	 * @return
	 */
//	public static Map<String,Object> getUrlParamData(SlideDto slide){
//		String url = lisUrl+slide.getPath()+"/"+slide.getBarcode()+"?"+slide.getToken();
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("url", url);
//		map.put("point", slide.getPoint());
//		return map;
//	}
	/**
	 * 发送报文信息到MQ
	 * @param type
	 * @param pathologyExpert
	 * @param itemId
	 * @param dicId
	 */
	public static String sendMQMessage(String type,String pathologyExpert,String itemId,String dicId){
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", UUID.randomUUID().toString().replaceAll("-", ""));
		map.put("app", Constants.DP);
		map.put("type", type);
		map.put("pathologyExpert", pathologyExpert);
		map.put("testId", itemId);
		map.put("docId", dicId);
		map.put("from", Constants.LIR);
		return new NtfmmandCommand(map).execute();
	}

	/**
	 * 切片上传   失败发送警告消息到MQ
	 * @param id
	 * @param barcode
	 */
	public static String sendMQMessage(String id,String barcode){
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("type", Constants.SLIDE_TYPE);
		map.put("barcode", barcode);
		map.put("from", Constants.SLIDE_FROM);
		return new NtfmmandCommand(map).execute();
	}
	/**
	 * 切片上传   失败发送警告消息到MQ
	 * @param id
	 * @param barcode
	 */
	public static String sendMQMessage(String hostName){
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", Constants.CLIENT_TYPE);
		map.put("hostName", hostName);
		map.put("from", Constants.CLIENT_FROM);
		return new NtfmmandCommand(map).execute();
	}
	/**
	 * 发送加做完成时间到dp-obpm-ws中进行保存操作
	 * @param parent
	 * @param domainUserId
	 * @param applicationId
	 */
	public static String sendObpmWsMessage(String parent,String domainUserId,String applicationId){
		StringBuffer param = new StringBuffer();
		param.append("parent="+parent);
		param.append("&domainUserId="+domainUserId);
		param.append("&applicationId="+applicationId);
		param.append("&endDate="+new Date().getTime());
		return new ProjectResultCommand(param.toString()).execute();
	}
	
	/**
	 * 验证数据
	 * @param slide
	 * @return
	 */
	public static Map<String,Object> validSlide(SlideDto slide){
		Map<String,Object> result = new HashMap<String,Object>();
		if(slide.getBarcode()==null||slide.getBarcode().isEmpty()){
			result.put("msg", "barcode不能为空!");
		}
		/*else if(slide.getPoint()==null||slide.getPoint().isEmpty()){
			result.put("msg", "point不能为空!");
		}*/
		else if(slide.getUrl()==null||slide.getUrl().isEmpty()){
			result.put("msg", "url不能为空!");
		}
//		else if(slide.getBarcode().substring(0, slide.getBarcode().indexOf(".")).length()!=2){
//			result.put("msg", "barcode值不符合要求!");
//		}
		return result;
	}
}
