package com.google.gwt.sample.Marketing.client;

import java.io.Serializable;

public class order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//public static order[] deliveredList;
	private int cartid;
	private String carttype;
	private String holdername;
	private String cardnumber;
	private String expiryyear;
	private String expirymonth;
	private String securitynumber;
	private ShoppingCart[] cart;
	private double payableAmount;
	
	public order(int cartid, ShoppingCart[] cart, String carttype, String holdername, String cardnumber,
			String expiryyear, String expirymonth, String securitynumber,double payableAmount){
		this.cartid = cartid;
		this.carttype=carttype;
		this.holdername=holdername;
		this.cardnumber=cardnumber;
		this.expiryyear=expiryyear;
		this.expirymonth=expirymonth;
		this.securitynumber=securitynumber;
		this.cart = cart;
		this.payableAmount = payableAmount;
	}
	
	public ShoppingCart[] getCart(){
		return this.cart;
	}
	
	public String getCartType(){
		return this.carttype;
	}
	
	public String getHolderName(){
		return this.holdername;
	}
	
	public String getCardNumber(){
		return this.cardnumber;
	}
	
	public String getExpiryYear(){
		return this.expiryyear;
	}
	
	public String getExpiryMonth(){
		return this.expirymonth;
	}
	
	public String getSecurityNumber(){
		return this.securitynumber;
	}
	
	public double getPayableAmount(){
		return this.payableAmount;
	}

	public int getCartId() {
	
		return this.cartid;
	}
	
	/*public static int indexOf(int cartid) {
		for (int i=0; i< deliveredList.length; i++){
			if (deliveredList[i].getCartId() ==cartid)
				return i;
		}
		return -1;
	}*/
	
}
