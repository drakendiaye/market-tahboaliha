package com.google.gwt.sample.Marketing.client;

import java.io.Serializable;

public class ShoppingCart implements Serializable {
	public static int cartid;
	
	private Product product;	
	private String username;
	private int qty;
	public static ShoppingCart[] cart;
	
	public ShoppingCart(Product product, String username){
		this.username = username;
		this.product =product;
		this.qty = 1;
	}
	
	public ShoppingCart(int pid, String username, int qty){
		this.product = Product.productList[Product.indexOf(Integer.toString(pid))];			
		this.username=username;
		this.qty=qty;
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public Product getProduct(){
		return this.product;
	}
	
	public int getQty(){
		return this.qty;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public void setQty(int qty){
		this.qty = qty;
	}
	
	public static double getTotalPrice(){
		if (cart == null)
			return 0;
		double sum=0;
		for (int i=0; i< cart.length; i++){
			sum += cart[i].getQty() * cart[i].product.getDiscountPrice();
		}		
		return sum;
	}
	
	public static int getTotalCount(){
		if (cart == null)
			return 0;
		int sum=0;
		for (int i=0; i< cart.length; i++){
			sum += cart[i].getQty() ;
		}		
		return sum;
	}
	
	public static int indexOf(int pid) {
		for (int i=0; i< cart.length; i++){
			if (cart[i].product.getPID() ==pid)
				return i;
		}
		return -1;
	}
		
	public static void remove(int index){
		if (cart.length == 1)
			cart = null;
		else{
			ShoppingCart[] list = new ShoppingCart[cart.length-1];
			int j=0;
			for (int i=0; i< cart.length; i++){
				if (index == i)
					i++;
				list[j]=cart[i];
				j++;
			}
		cart = list;
		}
	}

	public static void add(ShoppingCart shoppingCart) {
		ShoppingCart[] list;
		if (cart != null){
			list = new ShoppingCart[cart.length+1];
			for (int i=0; i< cart.length; i++){
				list[i]=cart[i];	
			}	
			list[cart.length] = shoppingCart;
		}
		else{
			list = new ShoppingCart[1];
			list[0] = shoppingCart;
		}		
		cart = list;		
		
	}
	
}
