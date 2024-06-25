package com.rays.pro4.Bean;

import java.util.Date;

public class RouteBean extends BaseBean {
	private String number;
	private Date purchasedate;
	private String mobile;
	private Integer insuranceAmount;
	private String colour;
	

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getPurchasedate() {
		return purchasedate;
	}

	public void setPurchasedate(Date purchasedate) {
		this.purchasedate = purchasedate;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getInsuranceAmount() {
		return insuranceAmount;
	}

	public void setInsuranceAmount(Integer insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	@Override
	public String getkey() {
		// TODO Auto-generated method stub
		return colour;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return colour;
	}

}
