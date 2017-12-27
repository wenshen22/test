package com.tarena.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Computer implements Serializable {
	private long id;
	private String model;
	private String pic;
	private String prodDesc;
	private double price;
	
	public Computer() {
	}
	public Computer(String model, String pic, String prodDesc,
			double price) {
		this.model = model;
		this.pic = pic;
		this.prodDesc = prodDesc;
		this.price = price;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getProdDesc() {
		return prodDesc;
	}
	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}
