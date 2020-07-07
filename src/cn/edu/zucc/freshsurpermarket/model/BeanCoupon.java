package cn.edu.zucc.freshsurpermarket.model;

import java.util.Date;

public class BeanCoupon {
	private int couponcode;
	private String content;
	private double apamount;
	private double deamount;
	private Date begindate;
	private Date enddate;
	private int isdelete;
	public int getCouponcode() {
		return couponcode;
	}
	public void setCouponcode(int couponcode) {
		this.couponcode = couponcode;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public double getApamount() {
		return apamount;
	}
	public void setApamount(double apamount) {
		this.apamount = apamount;
	}
	public double getDeamount() {
		return deamount;
	}
	public void setDeamount(double deamount) {
		this.deamount = deamount;
	}
	public Date getBegindate() {
		return begindate;
	}
	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public int getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(int isdelete) {
		this.isdelete = isdelete;
	}
	
}
