package io.corona.models;

public class Location {
	
	private String state;
	private String country;
	private int lastTotal;
	private int diff;
	
	public int getDiff() {
		return diff;
	}
	public void setDiff(int diff) {
		this.diff = diff;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLastTotal() {
		return lastTotal;
	}
	public void setLastTotal(int lastTotal) {
		this.lastTotal = lastTotal;
	}
	
	@Override
	public String toString() {
		return "Location [state=" + state + ", country=" + country + ", lastTotal=" + lastTotal + "]";
	}
	
	

}
