package cn.edu.zucc.freshsurpermarket.model;

import java.util.Date;

public class BeanFullDiscount {
	private int fulldiscountcode;
	private String content;
	private int apgoodsquantity;
	private double discount;
	private Date begindate;
	private Date enddate;
	public int getFulldiscountcode() {
		return fulldiscountcode;
	}
	public void setFulldiscountcode(int fulldiscountcode) {
		this.fulldiscountcode = fulldiscountcode;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getApgoodsquantity() {
		return apgoodsquantity;
	}
	public void setApgoodsquantity(int apgoodsquantity) {
		this.apgoodsquantity = apgoodsquantity;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
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
	
}
