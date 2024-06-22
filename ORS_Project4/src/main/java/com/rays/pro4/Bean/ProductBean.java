package com.rays.pro4.Bean;

import java.util.Date;

public class ProductBean extends BaseBean {

	private long id;
	private String productName;
	private String productAmount;
	private Date purchaseDate;
	private String productCatagory;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(String productAmount) {
		this.productAmount = productAmount;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getProductCatagory() {
		return productCatagory;
	}

	public void setProductCatagory(String productCatagory) {
		this.productCatagory = productCatagory;
	}

	@Override
	public String getkey() {
		// TODO Auto-generated method stub
		return productCatagory;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return productCatagory;
	}

}
