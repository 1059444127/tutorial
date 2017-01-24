package org.zerhusen.rest;

/**
 * 温度计
 * @author zhengjunjie
 *
 */
public class Therm {
	private String thermBarcode;
	private String deviceNo;
	private String orderNo;

	public Therm() {
	}

	public Therm(String thermBarcode, String deviceNo, String orderNo) {
		this.thermBarcode = thermBarcode;
		this.deviceNo = deviceNo;
		this.orderNo = orderNo;
	}
	

	public String getThermBarcode() {
		return thermBarcode;
	}

	public void setThermBarcode(String thermBarcode) {
		this.thermBarcode = thermBarcode;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

}
