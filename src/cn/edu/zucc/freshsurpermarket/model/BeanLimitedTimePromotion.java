package cn.edu.zucc.freshsurpermarket.model;

import java.util.Date;

public class BeanLimitedTimePromotion {
	private int promotioncode;
	private int goodscode;
	private double promotionprice;
	private int promotionquantity;
	private Date begindate;
	private Date enddate;
	public int getPromotioncode() {
		return promotioncode;
	}
	public void setPromotioncode(int promotioncode) {
		this.promotioncode = promotioncode;
	}
	public int getGoodscode() {
		return goodscode;
	}
	public void setGoodscode(int goodscode) {
		this.goodscode = goodscode;
	}
	public double getPromotionprice() {
		return promotionprice;
	}
	public void setPromotionprice(double promotionprice) {
		this.promotionprice = promotionprice;
	}
	public int getPromotionquantity() {
		return promotionquantity;
	}
	public void setPromotionquantity(int promotionquantity) {
		this.promotionquantity = promotionquantity;
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
