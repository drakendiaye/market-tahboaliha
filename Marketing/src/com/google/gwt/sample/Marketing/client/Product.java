package com.google.gwt.sample.Marketing.client;

import java.io.Serializable;

	public class Product  implements Serializable{
		private int PID;
		private String model;
		private double price;
		private int discount;
		private String img;
		public static Product[] productList;

		  public Product() {
		  }

		  public Product(int PID, String Model, double Price, int Discount, String img) {
		    this.PID = PID;
			  this.model = Model;
		    this.price = Price;
		    this.discount = Discount;
		    this.img = img;
		  }

		  public int getPID() {
			    return this.PID;
			  }
		  
		  public String getModel() {
		    return this.model;
		  }

		  public double getPrice() {
		    return this.price;
		  }

		  public int getDiscount() {
		    return this.discount;
		  }

		  public double getDiscountPrice() {
		    return this.price * (1.0 - ((double)this.discount / 100.0)) ;
		  }
		  
		  public String getImg() {
			    return this.img;
			  }

		  public void setPID(int PID) {
			    this.PID = PID;
			  }
		  
		  public void setModel(String model) {
		    this.model = model;
		  }

		  public void setPrice(double price) {
		    this.price = price;
		  }

		  public void setDiscount(int discount) {
		    this.discount = discount;
		  }
		  
		  public void setImg(String img) {
			    this.img = img;
			  }
		  
		  public static int indexOf(String pid) {
				for (int i=0; i< productList.length; i++){
					if (productList[i].getPID() == Integer.parseInt(pid))
						return i;
				}
				return -1;
			}
		  
		  public static void add(Product p) {
				Product[] list;
				if (productList != null){
					list = new Product[productList.length+1];
					for (int i=0; i< productList.length; i++){
						list[i]=productList[i];	
					}	
					list[productList.length] = p;
				}
				else{
					list = new Product[1];
					list[0] = p;
				}		
				productList = list;		
				
			}
	}

