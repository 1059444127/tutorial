package com.kingmed.dp.gateway.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.kingmed.dp.gateway.constants.Constants;
import com.kingmed.dp.gateway.util.DateUtil;
import com.kingmed.dp.gateway.util.HttpRequest;
import com.kingmed.dp.gateway.util.ModelUtil;

import cn.myapps.webservice.client.DocumentService;
import cn.myapps.webservice.client.DocumentServiceProxy;

public class DocService {
	
	private static Logger logger = Logger.getLogger(DocService.class);

	public static DocService docService;
	
	DocumentService iPath= new DocumentServiceProxy();
	
	private DocService() {
	}
	
	public static DocService getInstance() {
		if(docService==null){
			docService = new  DocService();
		}
		return docService;
	}
	
	/**
	 * 创建会诊请求与反馈信息
	 * @param formName 不能为null	表单名称
	 * @param caseParams 不能null	 病例信息参数集合
	 * @param leaveParams 不能null	反馈信息参数集合
	 * @param sectionParams 不能null	切片信息参数集合
	 * @param domainUserId 不能为null	域用户ID
	 * @param applicationId 不能为null	应用ID
	 * @return 0 创建文档成功；-1 创建失败
	 *  @WebParam @XmlJavaTypeAdapter(HashMap.class)
	 */
	public Integer createDocumentByDomainUser( Map<String, Object> caseParams, Map<String, Object> leaveParams, Map<String, Object> sectionParams, String domainUserId, String applicationId){
		logger.debug("createDocumentByDomainUser参数值：caseParams="+caseParams+",leaveParams="+leaveParams+",sectionParams="+sectionParams+",domainUserId="+domainUserId+",applicationId="+applicationId);
		int rnt = -1;
		try {
			//调用旧系统的api  写入数据
			int rnt1 = iPath.createDocumentByDomainUser("未诊断列表", (HashMap<String,Object>)caseParams, domainUserId, applicationId);
			int rnt2 = iPath.createDocumentByDomainUser("leave_info_new", (HashMap<String,Object>)leaveParams, domainUserId, applicationId);
			int rnt3 = iPath.createDocumentByDomainUser("number_section", (HashMap<String,Object>)sectionParams, domainUserId, applicationId);
			if(rnt1 == 0 && rnt1 == 0 && rnt3 == 0){
				rnt = 0;
				logger.info("旧系统创建会诊请求与反馈信息执行成功！");
			}else{
				rnt = -1;
				logger.info("旧系统创建会诊请求与反馈信息执行失败！病例信息rnt1:"+rnt1 +",反馈信息rnt2:" +rnt2 + ",切片信息rnt3:" +rnt3);
			}
			if(Constants.DPWEBAPP_CLOSEANDOFF.equals(ModelUtil.IS_TO_DP)){
				//调用dp-webapp的api 写入数据
				String cookie = HttpRequest.postMethodLogin(ModelUtil.dp_url+"/dp/login",ModelUtil.getDPLoginData());
				
				Map<String,Object> caseJson = ModelUtil.getCstCase(caseParams);
				String dp1 = HttpRequest.postMethod(ModelUtil.dp_url+"/cons/cstCase/update", caseJson,cookie);
				if("".equals(dp1)){
		    		logger.info(DocService.class+"登陆新系统失败，请检查用户名密码是否正确！username:"+ModelUtil.DP_USERNAME+",password:"+ModelUtil.DP_PASSWORD);
		    	}
				logger.debug("新系统病例信息创建"+dp1+"条成功！");
	    		Map<String,Object> leaveJson = ModelUtil.getCstAdvice(leaveParams,caseParams.get("ci_test_id")+"");
	    		String dp2 = HttpRequest.postMethod(ModelUtil.dp_url+"/cons/cstAdvice/save",leaveJson,cookie);
	    		logger.debug("反馈信息创建"+dp2+"条成功！");
	    		Map<String,Object> sectionJson = ModelUtil.getslide(sectionParams,caseParams.get("ci_test_id")+"");
	    		String dp3 = HttpRequest.postMethod(ModelUtil.dp_url+"/cons/updateAll/updateSlide",sectionJson,cookie);
	    		logger.debug("切片信息创建"+dp3+"条成功！");
	    		if("1".equals(dp1) && "1".equals(dp2) && "1".equals(dp3)){
					rnt = 0;
					logger.info("新系统创建会诊请求与反馈信息执行成功！");
				}else{
					rnt = -1;
					logger.info("新系统创建会诊请求与反馈信息执行失败！病例信息dp1:"+dp1 +",反馈信息dp2:" +dp2 + ",切片信息dp3:" +dp3);
				}
			}
		} catch (Exception e) {
			rnt = -1;
			logger.error("创建会诊请求与反馈信息，异常日志："+e);
			e.printStackTrace();
		}
		return rnt;
	}
	
	/**
	 * 创建会诊请求与反馈信息
	 * @param formName 不能为null	表单名称
	 * @param caseParams 不能null	 病例信息参数集合
	 * @param leaveParams 不能null	反馈信息参数集合
	 * @param sectionParams 不能null	切片信息参数集合
	 * @param domainUserId 不能为null	域用户ID
	 * @param applicationId 不能为null	应用ID
	 * @return 0 创建文档成功；-1 创建失败
	 *  @WebParam @XmlJavaTypeAdapter(HashMap.class)
	 */
	public Integer createDocumentByDomainUserV1(String formName, Map<String, Object> parameters, String domainUserId, String applicationId){
		logger.debug("createDocumentByDomainUser参数值：formName="+formName+",parameters="+parameters+",domainUserId="+domainUserId+",applicationId="+applicationId);
		int rnt = -1;
		try {
			//调用旧系统的api  写入数据
			if("未诊断列表".equals(formName)){
				rnt = iPath.createDocumentByDomainUser("未诊断列表", (HashMap<String,Object>)parameters, domainUserId, applicationId);
			}else if("leave_info_new".equals(formName)){
				rnt = iPath.createDocumentByDomainUser("leave_info_new", (HashMap<String,Object>)parameters, domainUserId, applicationId);
			}else if("number_section".equals(formName)){
				rnt = iPath.createDocumentByDomainUser("number_section", (HashMap<String,Object>)parameters, domainUserId, applicationId);
			}
			if(rnt == 0){
				logger.info("旧系统创建会诊请求与反馈信息执行成功！");
			}else{
				rnt = -1;
				logger.info("旧系统创建会诊请求与反馈信息执行失败！"+formName+"+rnt:"+rnt);
			}
			
			if(Constants.DPWEBAPP_CLOSEANDOFF.equals(ModelUtil.IS_TO_DP)){
				//调用dp-webapp的api 写入数据
				String cookie = HttpRequest.postMethodLogin(ModelUtil.dp_url+"/dp/login",ModelUtil.getDPLoginData());
				
				String dp="";
				if("未诊断列表".equals(formName)){
					Map<String,Object> caseJson = ModelUtil.getCstCase(parameters);
					dp = HttpRequest.postMethod(ModelUtil.dp_url+"/cons/cstCase/update", caseJson,cookie);
					if("".equals(dp)){
			    		logger.info(DocService.class+"登陆新系统失败，请检查用户名密码是否正确！username:"+ModelUtil.DP_USERNAME+",password:"+ModelUtil.DP_PASSWORD);
			    	}
					logger.debug("新系统病例信息创建"+dp+"条成功！");
				}else if("leave_info_new".equals(formName)){
//					Map<String,Object> leaveJson = ModelUtil.getCstAdvice(leaveParams,caseParams.get("ci_test_id")+"");
//		    		dp = HttpRequest.postMethod(ModelUtil.dp_url+"/cons/cstAdvice/save",leaveJson,cookie);
		    		logger.debug("反馈信息创建"+dp+"条成功！");
				}else if("number_section".equals(formName)){
//					Map<String,Object> sectionJson = ModelUtil.getslide(sectionParams,caseParams.get("ci_test_id")+"");
//		    		dp = HttpRequest.postMethod(ModelUtil.dp_url+"/cons/updateAll/updateSlide",sectionJson,cookie);
		    		logger.debug("切片信息创建"+dp+"条成功！");
				}
	    		if("1".equals(dp)){
					rnt = 0;
					logger.info("新系统创建会诊请求与反馈信息执行成功！");
				}else{
					rnt = -1;
					logger.info("新系统创建会诊请求与反馈信息执行失败！"+formName+"dp:"+dp);
				}
			}
		} catch (Exception e) {
			rnt = -1;
			logger.error("创建会诊请求与反馈信息，异常日志："+e);
			e.printStackTrace();
		}
		return rnt;
	}
	
	/**
	 * 更新病例状态为加做项目
	 * @param documentId 不能null	文档id
	 * @param Parameters 不能null	 参数集合
	 * @param domainUserId 不能null	域用户id
	 * @param applicationId 不能null	应用id
	 * @return 0 更新文档成功；-1更新失败
	 */
	public Integer updateDocumentByDomainUser(String documentId, Map<String, Object> parameters,
			  String domainUserId, String applicationId){
		logger.debug("updateDocumentByDomainUser参数值：documentId="+documentId+",parameters="+parameters+",domainUserId="+domainUserId+",applicationId="+applicationId);
		int rnt = -1;
		try {
			rnt = iPath.updateDocumentByDomainUser(documentId, (HashMap<String, Object>)parameters, domainUserId, applicationId);
			logger.info("旧接口updateDocumentByDomainUser返回值："+rnt);
			if(rnt != 0){
				rnt = -1;
			}
		} catch (Exception e) {
			logger.error("加做项目，异常日志："+e);
			e.printStackTrace();
		}
		return rnt;
	}
	
	/**
	 * 获取nodeJS生产的文件名称
	 * @param testId 病理号
	 * @param barcode 条码号
	 * @param antiBody  iframe1的title属性值
	 * @return {"filename":"16.1_20160414_1606059949_1$HE-1$10","displayName":"B1600026-HE-1-10"}
	 * displayName   对话框显示的文件名，以及浏览图片时显示的名称
	 * filename  物理文件名
	 */
	public String getFileName(String testId, String barcode,String antiBody){
		String url = ModelUtil.NODEJS_PORT+"/cst/testId/" + testId + "/slide/" + barcode + "/antiBodies/" + antiBody + "/screenShotName";
		return HttpRequest.sendGet(url, "");
	}
	
	/**
	 * 获取会诊平台所有专家
	 * @return  返回值格式
	 * {"users" : 
	 *	[{"id":"11e4-cc54-2bfb5bdd-bd20-8bfba43091f6",
	 *	"name":"病理专家-李四",
	 *	"rolename":"国内病理专家"},
	 *	{"id":"11e4-cc54-534928c9-bd20-8bfba43091f6",
	 *	"name":"病理专家-王5",
	 *	"rolename":"外国病理专家"}]
	 *	}
	 *
	 */
	public String getAllUsers(){
		return HttpRequest.sendGet(ModelUtil.ALL_USERS_URL, "");
	}
	
	/**
	 * 调用dp-webapp的API，查询ip是否变动
	 * @param hostName
	 * @param ip
	 * @param clientDate
	 * @return 1或-1表示ip变动了，则要发送短信给管理员，-1表示更新ip操作失败     
	 * 		   -2表示第一次调用该API，且保存失败，2表示保存成功
	 * 		   0 表示ip没变动
	 */
	public String clientIpState(String hostName,String ip,String clientDate){
		//调用dp-webapp的api 查询数据
		String cookie = HttpRequest.postMethodLogin(ModelUtil.dp_url+"/dp/login",ModelUtil.getDPLoginData());
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("hostName", hostName);
		params.put("ip", ip);
		params.put("clientDate", DateUtil.getDateTimeString(Long.parseLong(clientDate)));
		String result = HttpRequest.postMethod(ModelUtil.dp_url+"/cons/clientInfo/clientAPI", params,cookie);
		if("".equals(result)){
    		logger.info(DocService.class+"登陆新系统失败，请检查用户名密码是否正确！username:"+ModelUtil.DP_USERNAME+",password:"+ModelUtil.DP_PASSWORD);
    		return "";
    	}
		if(Constants.CLIENT_IP_STATE_FAIL.equals(result)||Constants.CLIENT_IP_STATE_TRUE.equals(result)){//客户端ip发生了改变，需要发送短信
			//发送短信
			String res = ModelUtil.sendMQMessage(hostName);
			if(!"0".equals(res)){//短信发送失败
				logger.error("监控客户端ip变动，警告短信发送失败！");
				result = "11";
			}
		}
		return result;
	}
}
