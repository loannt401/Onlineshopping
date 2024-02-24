package com.springboot.onlineshopping.model.user;


public class Address {

	private String addressDetail;
	private String ward;
	private String district;
	private String city;
	public Address() {
		// TODO Auto-generated constructor stub
	}

	public Address(String addressDetail, String ward, String district, String city) {
		this.addressDetail = addressDetail;
		this.ward = ward;
		this.district = district;
		this.city = city;
	}

	public String getAddressDetail() {
		return this.addressDetail;
	}

	/**
	 * 
	 * @param addressDetail
	 */
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getWard() {
		return this.ward;
	}

	/**
	 * 
	 * @param ward
	 */
	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getDistrict() {
		return this.district;
	}

	/**
	 * 
	 * @param district
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return this.city;
	}

	/**
	 * 
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getFullAddress() {
		return this.addressDetail + ", " + this.ward + ", " + this.district + ", " + this.city;
	}

}