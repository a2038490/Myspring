package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long no;
	private Long userid;
	private String productname;
	private double price;
	private int num;
	private double feerate;
	
	public Long getNo() {
		return no;
	}
	
	public void setNo(Long no) {
		this.no=no;
	}
	
	public String getProductname() {
		return productname;
	}
	
	public void setProductname(String productname) {
		this.productname = productname;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getFeerate() {
		return feerate;
	}

	public void setFeerate(double feerate) {
		this.feerate = feerate;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	

}
