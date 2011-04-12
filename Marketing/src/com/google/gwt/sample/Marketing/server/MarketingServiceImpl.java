package com.google.gwt.sample.Marketing.server;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.google.gwt.sample.Marketing.client.MarketingService;
import com.google.gwt.sample.Marketing.client.Product;
import com.google.gwt.sample.Marketing.client.order;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class MarketingServiceImpl extends RemoteServiceServlet implements MarketingService{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4031222897242529848L;
	/**
	 * 
	 */
	private database db = new database();
	private static final String SUBJECT = "confirmation for shopping id <cartid> ";
	private static final String BODY = "Dear <to_name>, this is your confirmation message for shopping id <cartid> at  " + ( new Date());
	private static final String FROM_ADDRESS = "tahbo763@student.liu.se";
	private static final String FROM_NAME = "tahbo763-marme660";
	
	public Product[] readProductList(){
		return db.readProductList();		  
	}
	
	/*public order[] readOrdersList(){
		return db.readOrdersList();
	}*/
	
	public boolean setDelivered(int cartid){
		db.setDelivered(cartid);
		return true;
	}
	
	public int genID(){
		return db.genID();
	}
	
	public boolean  insertCart(int cartid, int pid, String username, int qty){
		//this.getThreadLocalRequest().getSession(true).setAttribute("user", cartid);

			db.insertCart(cartid, pid, username, qty);
			return true; 
	}
	
	public boolean  insertOrder(int cartid, String carttype, String holdername, String cardnumber,
			String expiryyear, String expirymonth, String securitynumber,double payableAmount){
		 db.insertOrder( cartid, carttype, holdername, cardnumber, expiryyear, expirymonth, securitynumber,payableAmount);
		 return true;
	}
	
	public  boolean checkPassword(String password, String username){
		return db.checkPassword(password, username);
	}
	
	public String insertAccount(String username,String password,String firstname,String lastname,String email,String phonenumber,String address,String city,String zip){
		db.insertAccount(username, password, firstname, lastname, email, phonenumber, address, city, zip);
		return username;
	}	

	public boolean checkIsAdmin(String username){
		return db.checkIsAdmin(username);
	}
	
	public boolean updateProduct(int PID, String model,double price,int discount,String img){
		db.updateProduct(PID, model, price, discount, img);
		return true;
	}
	
	public boolean insertProduct(int PID, String model,double price,int discount,String img){
		db.insertProduct(PID, model, price, discount, img);
		return true;
	}
	
	public boolean sendEmail( String cartid, String toName ) {
		String emailAddress =	db.getEmail(toName);
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		String subject = SUBJECT.replace( "<to_name>", toName ).replace("<cartid>", cartid );
		String body = BODY.replace( "<to_name>", toName ).replace(	"<cartid>", cartid );;
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress( FROM_ADDRESS, FROM_NAME ));
			msg.addRecipient(Message.RecipientType.TO,
					new InternetAddress( emailAddress, toName ));
	
			msg.setSubject(subject);
			msg.setText( body );
			Transport.send(msg);
		}
		finally{
			return true;
		}
		
	 }
	 
}
