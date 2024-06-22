package com.rays.pro4.Bean;

public class BankBean extends BaseBean {
	
	private String bankName;
	private String accountNo;
	private String amount;
	private String accountHolder;
	

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	@Override
	public String getkey() {
		// TODO Auto-generated method stub
		return bankName;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return bankName;
	}

}
