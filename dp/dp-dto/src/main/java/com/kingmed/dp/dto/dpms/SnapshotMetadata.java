package com.kingmed.dp.dto.dpms;

public class SnapshotMetadata {
	private String caseId;		
	private String slideId;		
	private String snapshotId;	 	
	private String userId;	
	private String title;	
	private String type;	
	private String image;	//根据此url获取截图内容
	private String thumbnail;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	
}
