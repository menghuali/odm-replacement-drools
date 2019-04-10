package com.sample;

public class Booking {

	public static enum BookingType {
		GDS, AIRMILES, TA_WEB, NORMAL, UNKNOWN
	}

	private String pcc;
	private BookingType type;
	
	public String getPcc() {
		return pcc;
	}
	public void setPcc(String pcc) {
		this.pcc = pcc;
	}
	public BookingType getType() {
		return type;
	}
	public void setType(BookingType type) {
		this.type = type;
	}
	
	
}
