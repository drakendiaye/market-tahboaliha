package com.google.gwt.sample.Marketing.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MarketingServiceAsync {
	void checkPassword(String password, String username, AsyncCallback<Boolean> callback);
	void readProductList(AsyncCallback<Product[]> callback);
	void insertOrder(int cartid, String carttype, String holdername,
			String cardnumber, String expiryyear, String expirymonth,
			String securitynumber, double payableAmount,
			AsyncCallback<Boolean> callback);
	void insertAccount(String username, String password, String firstname,
			String lastname, String email, String phonenumber, String address,
			String city, String zip, AsyncCallback<String> callback);
	void genID(AsyncCallback<Integer> callback);
	void insertCart(int cartid, int pid, String username, int qty,
			AsyncCallback<Boolean> callback);
	void sendEmail(String cartid, String toName, AsyncCallback<Boolean> callback);
	void checkIsAdmin(String username, AsyncCallback<Boolean> callback);
	void insertProduct(int PID, String model, double price, int discount,
			String img, AsyncCallback<Boolean> callback);
	void updateProduct(int PID, String model, double price, int discount,
			String img, AsyncCallback<Boolean> callback);
	//void readOrdersList(AsyncCallback<order[]> callback);
	void setDelivered(int cartid, AsyncCallback<Boolean> callback);

}
