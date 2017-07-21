/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kingmed.dp.gateway.dto;

import java.io.Serializable;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;

/**
 *
 * @author zhengjunjie
 */

public class SlideDto implements Serializable{
    
	private static final long serialVersionUID = -2164555014498082423L;
	
	private String id;
	private String barcode;
    private String label;
    private String overview;
    private String labelWithOverview;
    private String url;
    private String scannerModel;
    private String createDateTime;
    private String scannerCode;
    private String physicalPath;
    private String path;
    private String token;
    private String point;
    private String user;//客户端用户名
    
    
	public SlideDto() {
		super();
	}
	public SlideDto(HttpServletRequest request) {
		super();
		this.id = UUID.randomUUID().toString();
		this.barcode = request.getParameter("barcode");
		this.label = request.getParameter("label");
		this.overview = request.getParameter("overview");
		this.labelWithOverview = request.getParameter("labelWithOverview");
		this.url = request.getParameter("url");
		this.scannerModel = request.getParameter("scannerModel").substring(0,5);
		this.createDateTime = request.getParameter("createDateTime");
		this.scannerCode = request.getParameter("scannerCode");
		this.physicalPath = request.getParameter("physicalPath");
		this.path = request.getParameter("path");
		this.token = request.getParameter("token");
		this.point = request.getParameter("point");
		this.user = SecurityUtils.getSubject().getPrincipal().toString();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getLabelWithOverview() {
		return labelWithOverview;
	}
	public void setLabelWithOverview(String labelWithOverview) {
		this.labelWithOverview = labelWithOverview;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getScannerModel() {
		return scannerModel;
	}
	public void setScannerModel(String scannerModel) {
		this.scannerModel = scannerModel;
	}
	public String getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getScannerCode() {
		return scannerCode;
	}
	public void setScannerCode(String scannerCode) {
		this.scannerCode = scannerCode;
	}
	public String getPhysicalPath() {
		return physicalPath;
	}
	public void setPhysicalPath(String physicalPath) {
		this.physicalPath = physicalPath;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "SlideDto [id=" + id + ", barcode=" + barcode + ", label=" + label + ", overview=" + overview
				+ ", labelWithOverview=" + labelWithOverview + ", url=" + url + ", scannerModel=" + scannerModel
				+ ", createDateTime=" + createDateTime + ", scannerCode=" + scannerCode + ", physicalPath="
				+ physicalPath + ", path=" + path + ", token=" + token + ", point=" + point + ", user=" + user + "]";
	}
	
}
