/**
 * DocumentWSSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kingmed.dp.soap.service.client;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.kingmed.dp.dto.CaseParameter;
import com.kingmed.dp.dto.CstCaseEvent;
import com.kingmed.dp.dto.CstSlideEvent;
import com.kingmed.dp.dto.SlideParameter;
import com.kingmed.dp.gateway.constants.Constants;
import com.kingmed.dp.gateway.feign.ConsultCaseObpmCommand;
import com.kingmed.dp.gateway.feign.ConsultSlideObpmCommand;
import com.kingmed.dp.gateway.util.HttpRequest;
import com.kingmed.dp.gateway.util.ModelUtil;

import cn.myapps.webservice.client.DocumentService;
import cn.myapps.webservice.client.DocumentServiceProxy;
import net.sf.json.JSONObject;

public class DocumentWSSoapBindingImpl implements com.kingmed.dp.soap.service.client.DocumentWS{
	
	private static Logger logger = Logger.getLogger(DocumentWSSoapBindingImpl.class);
	
	private static final String NEWCST= "未诊断列表"; 
	
	private static final String LEAVE_INFO_NEW= "leave_info_new"; 
	
	private static final String NUMBER_SECTION= "number_section";
	
	private DocumentService documentService = new DocumentServiceProxy();
	

	// TODO DPMS
    public int updateDocumentByDomainUser(java.lang.String documentId, java.lang.String parameters, java.lang.String domainUserId, java.lang.String applicationId) throws java.rmi.RemoteException, com.kingmed.dp.soap.service.fault.DocumentWSFault {
    	logger.info("createDocumentByDomainUser参数值：documentId="+documentId+",parameters="+parameters+",domainUserId="+domainUserId+",applicationId="+applicationId);
    	int result = Constants.IS_TRUE;
    	try {
			result = documentService.updateDocumentByDomainUser(documentId, parameters, domainUserId, applicationId);
			if(result == Constants.SUCCESS){
				logger.info("更新病例状态为加做项目成功！");
				if(Constants.ISTOMQ.equals(ModelUtil.IS_TO_MQ)){
					ModelUtil.sendMQMessage(Constants.ADD_IHC,"","",documentId);
				}
				//ModelUtil.sendObpmWsMessage(documentId, domainUserId, applicationId);//处理加做完成时间
			}else{
				result = Constants.IS_FALSE;
				logger.error("更新病例状态为加做项目失败！");
			}
			
		} catch (Exception e) {
			logger.error("更新病例状态为加做项目，异常日志："+e);
			e.printStackTrace();
		}
    	return result;
    }

    
    // TODO DPMS
    public int createDocumentByDomainUser(java.lang.String formName, java.lang.String parameters, java.lang.String domainUserId, java.lang.String applicationId) throws java.rmi.RemoteException, com.kingmed.dp.soap.service.fault.DocumentWSFault {
    	logger.info("createDocumentByDomainUser参数值：formName="+formName+",parameters="+parameters+",domainUserId="+domainUserId+",applicationId="+applicationId);
		int rnt = Constants.IS_FALSE;
		
		//TODO DPMS add by jack
		boolean checkDPMS = this.isFilterEvent(formName);
		if(checkDPMS){
			if("未诊断列表".equalsIgnoreCase(formName)){
				logger.info("拦截到请求会诊的病人信息：formName="+formName+",parameters="+parameters+",domainUserId="+domainUserId+",applicationId="+applicationId);
				List<CaseParameter> paras = new ArrayList<CaseParameter>();
				CaseParameter cp = JSON.parseObject(parameters, CaseParameter.class);
				paras.add(cp);
				
				CstCaseEvent event = new CstCaseEvent();
				event.setFormName(formName);
				event.setDomainUserId(domainUserId);
				event.setApplicationId(applicationId);
				event.setParameters(paras);
				event.setType("createDocumentByDomainUser");
					
				Date date = new Date(); 
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");
				event.setCreateTime(df.format(date));
				
				ConsultCaseObpmCommand cmd = new ConsultCaseObpmCommand(event);
				rnt = cmd.execute();
				
			} else if("number_section".equals(formName)){
				logger.info("拦截到请求会诊的数字切片：formName="+formName+",parameters="+parameters+",domainUserId="+domainUserId+",applicationId="+applicationId);
				SlideParameter sp = JSON.parseObject(parameters, SlideParameter.class);
				List<SlideParameter> paras = new ArrayList<SlideParameter>();
				paras.add(sp);
				
				CstSlideEvent event = new CstSlideEvent();
				event.setFormName(formName);
				event.setDomainUserId(domainUserId);
				event.setApplicationId(applicationId);
				event.setParameters(paras);
				event.setType("createDocumentByDomainUser");

				Date date = new Date(); 
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");
				event.setCreateTime(df.format(date));
				
				ConsultSlideObpmCommand cmd = new ConsultSlideObpmCommand(event);
				rnt = cmd.execute();
			}
			logger.info("返回值: " + rnt + " createDocumentByDomainUser参数值：formName="+formName+",parameters="+parameters+",domainUserId="+domainUserId+",applicationId="+applicationId);
			return rnt;
		}
		//DPMS add by jack
		
		try {
			//调用旧系统的api  写入数据
				rnt = documentService.createDocumentByDomainUser(formName, parameters, domainUserId, applicationId);
			if(rnt == Constants.SUCCESS){
				logger.info("旧系统创建会诊请求与反馈信息执行成功！"+formName);
				if(Constants.ISTOMQ.equals(ModelUtil.IS_TO_MQ)){
					JSONObject jsonObject = JSONObject.fromObject(parameters);  
			        Map<String, Object> mapJson = JSONObject.fromObject(jsonObject);
					if(NEWCST.equals(formName)){
						ModelUtil.sendMQMessage(Constants.NEW_CST, mapJson.get("ci_zdz")+"", mapJson.get("ci_test_id")+"", "");
					}else if(LEAVE_INFO_NEW.equals(formName)){
						ModelUtil.sendMQMessage(Constants.CHAT,"","",mapJson.get("parentid")+"");
					}
				}
			}else{
				rnt = Constants.IS_FALSE;
				logger.info("旧系统创建会诊请求与反馈信息执行失败！"+formName+"+rnt:"+rnt);
			}
			
			if(Constants.DPWEBAPP_CLOSEANDOFF.equals(ModelUtil.IS_TO_DP)){
				//调用dp-webapp的api 写入数据
				String cookie = HttpRequest.postMethodLogin(ModelUtil.dp_url+"/dp/login",ModelUtil.getDPLoginData());
				
				String dp="";
				if(NEWCST.equals(formName)){
					//Map<String,Object> caseJson = ModelUtil.getCstCase(parameters);
					//dp = HttpRequest.postMethod(ModelUtil.dp_url+"/cons/cstCase/update", caseJson,cookie);
					if("".equals(dp)){
			    		logger.info(DocumentWSSoapBindingImpl.class+"登陆新系统失败，请检查用户名密码是否正确！username:"+ModelUtil.DP_USERNAME+",password:"+ModelUtil.DP_PASSWORD);
			    	}
					logger.debug("新系统病例信息创建"+dp+"条成功！");
				}else if(LEAVE_INFO_NEW.equals(formName)){
//					Map<String,Object> leaveJson = ModelUtil.getCstAdvice(leaveParams,caseParams.get("ci_test_id")+"");
//		    		dp = HttpRequest.postMethod(ModelUtil.dp_url+"/cons/cstAdvice/save",leaveJson,cookie);
		    		logger.debug("反馈信息创建"+dp+"条成功！");
				}else if(NUMBER_SECTION.equals(formName)){
//					Map<String,Object> sectionJson = ModelUtil.getslide(sectionParams,caseParams.get("ci_test_id")+"");
//		    		dp = HttpRequest.postMethod(ModelUtil.dp_url+"/cons/updateAll/updateSlide",sectionJson,cookie);
		    		logger.debug("切片信息创建"+dp+"条成功！");
				}
	    		if("1".equals(dp)){
					rnt = Constants.SUCCESS;
					logger.info("新系统创建会诊请求与反馈信息执行成功！");
				}else{
					rnt = Constants.IS_FALSE;
					logger.info("新系统创建会诊请求与反馈信息执行失败！"+formName+"dp:"+dp);
				}
			}
		} catch (Exception e) {
			rnt = Constants.IS_FALSE;
			logger.error("创建会诊请求与反馈信息，异常日志："+e);
			e.printStackTrace();
		}
		return rnt;
    }
    
    /********************************** comment by jack on 20170718 for log event of consult request from LIR or KMCS 
     
    public int createDocumentByDomainUser(java.lang.String formName, java.lang.String parameters, java.lang.String domainUserId, java.lang.String applicationId) throws java.rmi.RemoteException, com.kingmed.dp.soap.service.fault.DocumentWSFault {
    	logger.info("createDocumentByDomainUser参数值：formName="+formName+",parameters="+parameters+",domainUserId="+domainUserId+",applicationId="+applicationId);
		int rnt = Constants.IS_FALSE;
			
		try {
			//调用旧系统的api  写入数据
				rnt = documentService.createDocumentByDomainUser(formName, parameters, domainUserId, applicationId);
			if(rnt == Constants.SUCCESS){
				logger.info("旧系统创建会诊请求与反馈信息执行成功！"+formName);
				if(Constants.ISTOMQ.equals(ModelUtil.IS_TO_MQ)){
					JSONObject jsonObject = JSONObject.fromObject(parameters);  
			        Map<String, Object> mapJson = JSONObject.fromObject(jsonObject);
					if(NEWCST.equals(formName)){
						ModelUtil.sendMQMessage(Constants.NEW_CST, mapJson.get("ci_zdz")+"", mapJson.get("ci_test_id")+"", "");
					}else if(LEAVE_INFO_NEW.equals(formName)){
						ModelUtil.sendMQMessage(Constants.CHAT,"","",mapJson.get("parentid")+"");
					}
				}
			}else{
				rnt = Constants.IS_FALSE;
				logger.info("旧系统创建会诊请求与反馈信息执行失败！"+formName+"+rnt:"+rnt);
			}
			
			if(Constants.DPWEBAPP_CLOSEANDOFF.equals(ModelUtil.IS_TO_DP)){
				//调用dp-webapp的api 写入数据
				String cookie = HttpRequest.postMethodLogin(ModelUtil.dp_url+"/dp/login",ModelUtil.getDPLoginData());
				
				String dp="";
				if(NEWCST.equals(formName)){
					//Map<String,Object> caseJson = ModelUtil.getCstCase(parameters);
					//dp = HttpRequest.postMethod(ModelUtil.dp_url+"/cons/cstCase/update", caseJson,cookie);
					if("".equals(dp)){
			    		logger.info(DocumentWSSoapBindingImpl.class+"登陆新系统失败，请检查用户名密码是否正确！username:"+ModelUtil.DP_USERNAME+",password:"+ModelUtil.DP_PASSWORD);
			    	}
					logger.debug("新系统病例信息创建"+dp+"条成功！");
				}else if(LEAVE_INFO_NEW.equals(formName)){
//					Map<String,Object> leaveJson = ModelUtil.getCstAdvice(leaveParams,caseParams.get("ci_test_id")+"");
//		    		dp = HttpRequest.postMethod(ModelUtil.dp_url+"/cons/cstAdvice/save",leaveJson,cookie);
		    		logger.debug("反馈信息创建"+dp+"条成功！");
				}else if(NUMBER_SECTION.equals(formName)){
//					Map<String,Object> sectionJson = ModelUtil.getslide(sectionParams,caseParams.get("ci_test_id")+"");
//		    		dp = HttpRequest.postMethod(ModelUtil.dp_url+"/cons/updateAll/updateSlide",sectionJson,cookie);
		    		logger.debug("切片信息创建"+dp+"条成功！");
				}
	    		if("1".equals(dp)){
					rnt = Constants.SUCCESS;
					logger.info("新系统创建会诊请求与反馈信息执行成功！");
				}else{
					rnt = Constants.IS_FALSE;
					logger.info("新系统创建会诊请求与反馈信息执行失败！"+formName+"dp:"+dp);
				}
			}
		} catch (Exception e) {
			rnt = Constants.IS_FALSE;
			logger.error("创建会诊请求与反馈信息，异常日志："+e);
			e.printStackTrace();
		}
		return rnt;
    }
	***************************************************/
	
    public int createDocumentByDomainUserV1(java.util.HashMap caseParams, java.util.HashMap leaveParams, java.util.HashMap sectionParams, java.lang.String domainUserId, java.lang.String applicationId) throws java.rmi.RemoteException, com.kingmed.dp.soap.service.fault.DocumentWSFault {
    	int rnt = -1;
		//调用obpm的api  写入数据
		int rnt1 = documentService.createDocumentByDomainUser(NEWCST, caseParams, domainUserId, applicationId);
        int rnt2 = documentService.createDocumentByDomainUser(LEAVE_INFO_NEW, leaveParams, domainUserId, applicationId);
        int rnt3 = documentService.createDocumentByDomainUser(NUMBER_SECTION, sectionParams, domainUserId, applicationId);
        if(rnt1 == 0 && rnt1 == 0 && rnt3 == 0){
			rnt = 0;
			logger.info("旧系统创建会诊请求与反馈信息执行成功！");
		}else{
			rnt = -1;
			logger.info(DocumentWSSoapBindingImpl.class+"旧系统创建会诊请求与反馈信息执行失败！病例信息rnt1:"+rnt1 +",反馈信息rnt2:" +rnt2 + ",切片信息rnt3:" +rnt3);
		}
        if(Constants.DPWEBAPP_CLOSEANDOFF.equals(ModelUtil.IS_TO_DP)){
	        //调用dp-webapp的api 写入数据
			Map<String, Object> jsons = ModelUtil.getDPLoginData();
			String cookie = HttpRequest.postMethodLogin(ModelUtil.dp_url+"/dp/login",jsons);
			String dp1 = HttpRequest.postMethod(ModelUtil.dp_url+"/cons/cstCase/update", ModelUtil.getCstCase(caseParams),cookie);
			if("".equals(dp1)){
	    		logger.info(DocumentWSSoapBindingImpl.class+"登陆失败，请检查用户名密码是否正确！username:"+ModelUtil.DP_USERNAME+",password:"+ModelUtil.DP_PASSWORD);
	    	}
			String dp2 = HttpRequest.postMethod(ModelUtil.dp_url+"/cons/cstAdvice/save",ModelUtil.getCstAdvice(leaveParams,caseParams.get("ci_test_id").toString()),cookie);
			String dp3 = HttpRequest.postMethod(ModelUtil.dp_url+"/cons/updateAll/updateSlide", ModelUtil.getslide(sectionParams,caseParams.get("ci_test_id").toString()),cookie);
			if("1".equals(dp1) && "1".equals(dp2) && "1".equals(dp3)){
				rnt = 0;
				logger.info("新系统创建会诊请求与反馈信息执行成功！");
			}else{
				rnt = -1;
				logger.info(DocumentWSSoapBindingImpl.class+"新系统创建会诊请求与反馈信息执行失败！病例信息dp1:"+dp1 +",反馈信息dp2:" +dp2 + ",切片信息dp3:" +dp3);
			}
        }
		return rnt;
    }
    
    /**
     * 是否拦截会诊请求(病例信息、数字切片)
     * 由于病例信息中不包含数字切片信息，所以缺省拦截所有的会诊请求

     * 
     * 当WSI来自于广州的Aperio扫描仪的时候，转换WSI为DZI
     * @return
     */
    private boolean isFilterEvent(String formName){
    	if ("未诊断列表".equals(formName) || "number_section".equalsIgnoreCase(formName))
    		return true;
    	return false;
    }
    
    /**
     * 当请求类型是数字切片的时候，检测是否需要转换WSI为DZI
     * 当WSI来自于广州的Aperio扫描仪的时候，转换WSI为DZI
     */
    private boolean isConvertToDZI(SlideParameter slide ) {
    	String vender = slide.getNs_vender();
    	String imageUrl = slide.getNs_img_url();
    	if(imageUrl.contains("www.kingmed.com.cn:7380")) {
    		logger.info("数字切片是 " + vender + "," + imageUrl);
    		return true;
    	}
    	return false;
    }

}
