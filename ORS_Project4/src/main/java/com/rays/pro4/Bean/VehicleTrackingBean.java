package com.rays.pro4.Bean;

public class VehicleTrackingBean extends BaseBean{
	
	private Double Lat;
	private Double LongId;
	private  String VehicleId;
	private java.util.Date Date;
	
	

	
	public Double getLat() {
		return Lat;
	}

	public void setLat(Double lat) {
		Lat = lat;
	}

	public Double getLongId() {
		return LongId;
	}

	public void setLongId(Double longId) {
		LongId = longId;
	}

	public String getVehicleId() {
		return VehicleId;
	}

	public void setVehicleId(String vehicleId) {
		VehicleId = vehicleId;
	}

	public java.util.Date getDate() {
		return Date;
	}

	public void setDate(java.util.Date date) {
		Date = date;
	}

	@Override
	public String getkey() {
		// TODO Auto-generated method stub
		return LongId+"";
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return LongId+"";
	}
	@Override
	public int compareTo(BaseBean o) {
		// TODO Auto-generated method stub
		return 0;
	}
		

}
