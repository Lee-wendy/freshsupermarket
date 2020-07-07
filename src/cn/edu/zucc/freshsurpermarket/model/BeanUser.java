package cn.edu.zucc.freshsurpermarket.model;

import java.util.Date;

public class BeanUser {
	private int usercode;
	private String name;
	private String sex;
	private String password; 
	private String phone;
	private String email;
	private String city;
	private Date registerdate;
	private int isvip;
	private Date vipdeadline;
	private int logoff;
	public int getUsercode() {
		return usercode;
	}
	public void setUsercode(int usercode) {
		this.usercode = usercode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Date getRegisterdate() {
		return registerdate;
	}
	public void setRegisterdate(Date registerdate) {
		this.registerdate = registerdate;
	}
	public int getIsvip() {
		return isvip;
	}
	public void setIsvip(int isvip) {
		this.isvip = isvip;
	}
	public Date getVipdeadline() {
		return vipdeadline;
	}
	public void setVipdeadline(Date vipdeadline) {
		this.vipdeadline = vipdeadline;
	}
	public int getLogoff() {
		return logoff;
	}
	public void setLogoff(int logoff) {
		this.logoff = logoff;
	}
	
	
}
