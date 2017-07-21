package com.kingmed.dp.dto.obpm;

public class Screenshot {
	private String testId;//testId
	private String slideBarcode;//
	private String name;
	private String path;
	private String image;//Base64 encoding screenshot
	public String getTestId() {
		return testId;
	}
	public void setTestId(String testId) {
		this.testId = testId;
	}
	public String getSlideBarcode() {
		return slideBarcode;
	}
	public void setSlideBarcode(String slideBarcode) {
		this.slideBarcode = slideBarcode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}
