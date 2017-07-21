package com.kingmed.dp.dto.dpms;

/**
 * DPMS推送的SlideReady
 * @author zhengjunjie
 *
 */
public class SlideMetadata {
	 private String caseId;
	 private String height;
	 private String label; 		//path url of label jpg
	 private String mmPerPixel;
	 private String model;
	 private String objectivePower;
	 private String slideId; 	//WSI barcode
	 private String source;
	 private String thumbnail; 	//path url of thumbnail jpg
	 private String title;
	 private String vendor;
	 private String width;
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getMmPerPixel() {
		return mmPerPixel;
	}
	public void setMmPerPixel(String mmPerPixel) {
		this.mmPerPixel = mmPerPixel;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getObjectivePower() {
		return objectivePower;
	}
	public void setObjectivePower(String objectivePower) {
		this.objectivePower = objectivePower;
	}
	public String getSlideId() {
		return slideId;
	}
	public void setSlideId(String slideId) {
		this.slideId = slideId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	 
	 
}
