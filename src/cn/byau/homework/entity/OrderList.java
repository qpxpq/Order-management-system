/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.byau.homework.entity;

/**
 *
 * @author Administrator
 */
public class OrderList {
    private String orderID;
    private String product;
    private int number;
    private String name;
    private String kindNo;
    
    private String kindName;

	public OrderList() {
		super();
	}

	public OrderList(String orderID, String product, int number, String name, String kindNo, String kindName) {
		super();
		this.orderID = orderID;
		this.product = product;
		this.number = number;
		this.name = name;
		this.kindNo = kindNo;
		this.kindName = kindName;
	}
	

	public OrderList(String orderID, String product, int number, String name, String kindNo) {
		super();
		this.orderID = orderID;
		this.product = product;
		this.number = number;
		this.name = name;
		this.kindNo = kindNo;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKindNo() {
		return kindNo;
	}

	public void setKindNo(String kindNo) {
		this.kindNo = kindNo;
	}

	public String getKindName() {
		return kindName;
	}

	public void setKindName(String kindName) {
		this.kindName = kindName;
	}

	@Override
	public String toString() {
		return "OrderList [orderID=" + orderID + ", product=" + product + ", number=" + number + ", name=" + name + ", kindNo="
				+ kindNo + ", kindName=" + kindName + "]";
	}
    
   
   
}
