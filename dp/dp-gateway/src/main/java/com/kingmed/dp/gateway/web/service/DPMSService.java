package com.kingmed.dp.gateway.web.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestBody;

import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;
import com.kingmed.dp.gateway.command.SnapshotCommand;
import com.kingmed.dp.gateway.dto.ParamsDto;
import com.kingmed.dp.gateway.dto.SnapshotDto;

/**
 * 
 * @author zl
 *
 */
@RequestScoped
@Path("/DPMS/webhook")
public class DPMSService {

	private final HttpServletRequest request;
    private final HttpServletResponse response;

    @Inject
    public DPMSService(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }
	
    /**
     * 切片信息
     * @return
     */
    @POST
    @Path("/slideReady")
//    @RequiresPermissions("DPMS:allowed") //认证   需要去掉BootstrapShiroModule.java中的dpms匿名访问设置
//    @RequiresAuthentication
    @Produces(MediaType.APPLICATION_JSON)
    public Response slideReady(@RequestBody String body) {
    	ParamsDto params = new ParamsDto(body,SnapshotDto.SLIDE);
    	String fs = new SnapshotCommand(params,SnapshotDto.SLIDE_TYPE).execute();
        //解析上传结果
        return Response.ok(fs).build();
    }
    
    /**
     * 截图信息
     * @return
     */
    @POST
    @Path("/snapshotAdded")
//    @RequiresPermissions("DPMS:allowed")
//    @RequiresAuthentication
    @Produces(MediaType.APPLICATION_JSON)
    public Response snapshotAdded(@RequestBody String body) {
    	ParamsDto params = new ParamsDto(body,SnapshotDto.SNAPSHOTADD);
    	String fs = new SnapshotCommand(params,SnapshotDto.ADD_TYPE).execute();
    	//解析上传结果
    	return Response.ok(fs).build();
    }
    
    /**
     * 删除截图
     * @return
     */
    @POST
    @Path("/snapshotDeleted")
//    @RequiresPermissions("DPMS:allowed")
//    @RequiresAuthentication
    @Produces(MediaType.APPLICATION_JSON)
    public Response snapshotDeleted(@RequestBody String body) {
    	ParamsDto params = new ParamsDto(body,SnapshotDto.SNAPSHOTDELETE);
    	String fs = new SnapshotCommand(params,SnapshotDto.DELETE_TYPE).execute();
    	//解析上传结果
    	return Response.ok(fs).build();
    }
}
