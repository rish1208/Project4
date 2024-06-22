package com.rays.pro4.Bean;

import java.util.Date;

public class DoctorBean extends BaseBean{
	
	private String name;
	private Date dob;
	private String mobile;
	private String experties;
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getExperties() {
		return experties;
	}

	public void setExperties(String experties) {
		this.experties = experties;
	}

	@Override
	public String getkey() {
		// TODO Auto-generated method stub
		return experties;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return experties;
	}

}
