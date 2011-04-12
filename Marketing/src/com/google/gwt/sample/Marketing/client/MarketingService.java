package com.google.gwt.sample.Marketing.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("market")
public interface MarketingService  extends RemoteService  {
	Product[] readProductList();
	boolean checkPassword(String password, String username);
	 boolean  insertCart(int cartid, int pid, String username, int qty);
	boolean  insertOrder(int cartid, String carttype, String holdername, String cardnumber,
			String expiryyear, String expirymonth, String securitynumber,double payableAmount);
	String insertAccount(String username,String password,String firstname,String lastname,
						String email,String phonenumber,String address,String city,String zip);
	int genID();
	boolean sendEmail( String cartid, String toName); 
	 boolean insertProduct(int PID, String model,double price,int discount,String img);
	 boolean checkIsAdmin(String username);
	 boolean updateProduct(int PID, String model,double price,int discount,String img);
	 //order[] readOrdersList();
	 boolean setDelivered(int cartid);
}
