package cn.edu.zucc.freshsurpermarket.model;

public class BeanSystemUser {
	private int staffcode;
	private String name;
	private String password;
	private int offjob;
	
	
	public int getStaffcode() {
		return staffcode;
	}
	public void setStaffcode(int staffcode) {
		this.staffcode = staffcode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getOffjob() {
		return offjob;
	}
	public void setOffjob(int offjob) {
		this.offjob = offjob;
	}
	
}
