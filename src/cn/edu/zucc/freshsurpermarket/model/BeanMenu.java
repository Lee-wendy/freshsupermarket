package cn.edu.zucc.freshsurpermarket.model;

import java.awt.Image;

public class BeanMenu {
	private int menucode;
	private String menuname;
	private String menumaterials;
	private String step;
	private Image picture;
	public int getMenucode() {
		return menucode;
	}
	public void setMenucode(int menucode) {
		this.menucode = menucode;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getMenumaterials() {
		return menumaterials;
	}
	public void setMenumaterials(String menumaterials) {
		this.menumaterials = menumaterials;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public Image getPicture() {
		return picture;
	}
	public void setPicture(Image picture) {
		this.picture = picture;
	}
	
	
}
