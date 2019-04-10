package com.sample;

public class Trip {

	public static enum RouteType {
		DOMESTIC, TRANSBODER, INTERNATIONAL, UNKNOWN
	}

	private String originCode;
	private String originCountry;
	private String originRegion;

	private String destCode;
	private String destCountry;
	private String destRegion;

	private RouteType route;

	public Trip() {
		route = RouteType.UNKNOWN;
	}

	public String getOriginCode() {
		return originCode;
	}

	public void setOriginCode(String originCode) {
		this.originCode = originCode;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public String getOriginRegion() {
		return originRegion;
	}

	public void setOriginRegion(String originRegion) {
		this.originRegion = originRegion;
	}

	public String getDestCode() {
		return destCode;
	}

	public void setDestCode(String destCode) {
		this.destCode = destCode;
	}

	public String getDestCountry() {
		return destCountry;
	}

	public void setDestCountry(String destCountry) {
		this.destCountry = destCountry;
	}

	public String getDestRegion() {
		return destRegion;
	}

	public void setDestRegion(String destRegion) {
		this.destRegion = destRegion;
	}

	public RouteType getRoute() {
		return route;
	}

	public void setRoute(RouteType route) {
		this.route = route;
	}

}
