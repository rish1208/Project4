package com.rays.pro4.Bean;

import java.util.Date;

public class OrderBean extends BaseBean{
	private int quantity;
	private String product;
	private Date date;
	private String amount;
	

	
	
	

	


	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public String getkey() {
		// TODO Auto-generated method stub
		return product;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return product;
	}

}
