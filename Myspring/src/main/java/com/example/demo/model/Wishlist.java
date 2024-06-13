package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Wishlist {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long listno;
	private Long userid;
	private int ordernum;
	private String account;
	private double totalfee;
	private double totalamount;
	
	public Long getListno() {
		return listno;
	}
	
	public void setListno(Long listno) {
		this.listno = listno;
	}

	public int getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public double getTotalfee() {
		return totalfee;
	}

	public void setTotalfee(double totalfee) {
		this.totalfee = totalfee;
	}

	public double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

}
