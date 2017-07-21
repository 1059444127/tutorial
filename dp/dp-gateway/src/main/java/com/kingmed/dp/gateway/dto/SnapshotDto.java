package com.kingmed.dp.gateway.dto;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

/**
 * DPMS截图信息
 * @author zl
 *
 */
public class SnapshotDto implements Serializable{

	private static final long serialVersionUID = -4691371434220438417L;

	public static final String[] SNAPSHOTADD = {"caseId","slideId","snapshotId","url","title","userId","type","image","thumbnail"};
	public static final String[] SNAPSHOTDELETE = {"caseId","slideId","snapshotId"};
	public static final String[] SLIDE = {"caseId","slideId","url","name","width","height","viewer","thumbnail","label"};
	public static final String ADD_TYPE = "add";
	public static final String DELETE_TYPE = "delete";
	public static final String SLIDE_TYPE = "slide";
	
	private String caseId;
	
	private String slideId;
	
	private String snapshotId;
	
	private String url;
	
	private String title;
	
	private String userId;
	
	private String type;
	
	private String image;
	
	private String thumbnail;

	public SnapshotDto() {
		super();
	}
	public SnapshotDto(HttpServletRequest request) {
		super();
		this.caseId = request.getParameter("caseId");
		this.slideId = request.getParameter("slideId");
		this.snapshotId = request.getParameter("snapshotId");
		this.url = request.getParameter("url");
		this.title = request.getParameter("title");
		this.userId = request.getParameter("userId");
		this.type = request.getParameter("type");
		this.image = request.getParameter("image");
		this.thumbnail = request.getParameter("thumbnail");
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getSlideId() {
		return slideId;
	}

	public void setSlideId(String slideId) {
		this.slideId = slideId;
	}

	public String getSnapshotId() {
		return snapshotId;
	}

	public void setSnapshotId(String snapshotId) {
		this.snapshotId = snapshotId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	@Override
	public String toString() {
		return "SnapshotDto [caseId=" + caseId + ", slideId=" + slideId + ", snapshotId=" + snapshotId + ", url=" + url
				+ ", title=" + title + ", userId=" + userId + ", type=" + type + ", image=" + image + ", thumbnail="
				+ thumbnail + "]";
	}
	
}
