package com.kingmed.dp.gateway.web.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;
import com.kingmed.dp.gateway.command.ClientCommand;
import com.kingmed.dp.gateway.command.CstCaseCommand;
import com.kingmed.dp.gateway.command.FileNameCommand;
import com.kingmed.dp.gateway.command.NtfmmandCommand;
import com.kingmed.dp.gateway.command.ProjectParamCommand;
import com.kingmed.dp.gateway.command.UsersCommand;
import com.kingmed.dp.gateway.dto.CstCaseDto;
import com.kingmed.dp.gateway.dto.ParamsDto;
import com.kingmed.dp.gateway.dto.ProjectParamDto;

/**
 *
 * @author zhanglei
 */
@RequestScoped
@Path("/cst")
public class DocumentService {
	
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    @Inject
    public DocumentService(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @POST
    @Path("/case/createDocument")
    @RequiresPermissions("CST:allowed")
    @RequiresAuthentication
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDocument() {
    	CstCaseDto cstCase = new CstCaseDto(request);
        String fs = new CstCaseCommand(cstCase).execute();
        //解析上传结果
        return Response.ok(fs).build();
    }

    @POST
    @Path("/slide/updateDocument")
    @RequiresPermissions("CST:allowed")
    @RequiresAuthentication
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDocument() {
    	ProjectParamDto projectParam = new ProjectParamDto(request);
        String fs = new ProjectParamCommand(projectParam).execute();
        //解析上传结果
        return Response.ok(fs).build();
    }
    
    @GET
    @Path("/case/slide/snapshot")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFileName() {
    	
    	ParamsDto params = new ParamsDto(request,new String[]{ParamsDto.TESTID,ParamsDto.BARCODE,ParamsDto.ANTIBODY});
        String fs = new FileNameCommand(params).execute();
        //解析上传结果
        return Response.ok(fs).build();
    }
    
    @GET
    @Path("/dr")
    //1、这里解决网页显示的编码问题，如果不需要网页显示，经测试可注释掉
    @Produces({ MediaType.APPLICATION_JSON, "text/html; charset=UTF-8" })
    public Response getAllUsers() {
    	ParamsDto params = new ParamsDto(request,new String[]{});
        String fs = new UsersCommand(params).execute();
        //解析上传结果
        return Response.ok(fs).header(HttpHeaders.CONTENT_TYPE,
                MediaType.APPLICATION_JSON + ";charset=UTF-8").build();
    }
    
    @POST
    @Path("/mq/message")
    @RequiresPermissions("MQ:allowed")
    @RequiresAuthentication
    @Produces(MediaType.APPLICATION_JSON)
    public Response mqSendMessage() {
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("id", UUID.randomUUID().toString().replaceAll("-", ""));
    	map.put("app", request.getParameter("app"));
		map.put("type", request.getParameter("type"));
		map.put("testId", request.getParameter("testId"));
		map.put("pathologyExpert", request.getParameter("pathologyExpert"));
		map.put("docId", request.getParameter("docId"));
		map.put("from", request.getParameter("from"));
        String fs = new NtfmmandCommand(map).execute();
        //解析上传结果
        return Response.ok(fs).build();
    }
    
    @POST
    @Path("/client/ip")
    @RequiresPermissions("client:allowed")
    @RequiresAuthentication
    @Produces(MediaType.APPLICATION_JSON)
    public Response clientIP() {
    	ParamsDto params = new ParamsDto(request,new String[]{"hostName","ip","clientDate"});
        String fs = new ClientCommand(params).execute();
        //解析上传结果
        return Response.ok(fs).build();
    }
}
