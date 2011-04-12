package com.google.gwt.sample.Marketing.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gwt.sample.Marketing.client.Product;
import com.google.gwt.sample.Marketing.client.ShoppingCart;
import com.google.gwt.sample.Marketing.client.order;

public class database {
	
	public Product[] readProductList() {
		Product[] prices = new Product[PhoneCount()];
	      try {
	    	  Class.forName("com.mysql.jdbc.Driver");
	    	  Connection conn = DriverManager.getConnection("jdbc:mysql://www-und.ida.liu.se/tahbo763?" +
		                       "user=tahbo763&password=tahbo7635732");

	         Statement stat = conn.createStatement();
	      
	         int i=0;	         
	         ResultSet rs = stat.executeQuery("select * from product;");
	         while (rs.next()) {
	        	 prices[i] = new Product(rs.getInt("pid"),rs.getString("model") , rs.getFloat("price") , rs.getInt("discount") , rs.getString("img"));
	        	 i++;
	         }	         
	         rs.close();
	         conn.close();
	      } catch (Exception e) {
	         e.printStackTrace();
	      } 	     
	      return prices;
	 }
	
	public order[] readOrdersList() {
		order[] list = new order[orderCount()];
	      try {
	    	  Class.forName("com.mysql.jdbc.Driver");
	    	  Connection conn = DriverManager.getConnection("jdbc:mysql://www-und.ida.liu.se/tahbo763?" +
		                       "user=tahbo763&password=tahbo7635732");

	         Statement stat = conn.createStatement();
	      
	         int i=0;	         
	         ResultSet rs = stat.executeQuery("select * from allitems where delivered = 0;");
	         while (rs.next()) {	        	 
	        	 ShoppingCart[] cart = new ShoppingCart[cartCountOrder(rs.getInt("cartid"))];
	        	 
	        	 for (int j=0; j< cart.length; j++){
	        		 cart[j]= new ShoppingCart(rs.getInt("pid"), rs.getString("username"), rs.getInt("qty"));
	        		 rs.next();
	        	 }
	        	 list[i] = new order (rs.getInt("cartid"),cart,rs.getString("carttype") , rs.getString("holdername") , rs.getString("cardnumber") , rs.getString("expiryyear"), 
	        			 rs.getString("expirymonth"), rs.getString("securitynumber"), rs.getDouble("payableamount"));
	        	 i++;
	         }	         
	         rs.close();
	         conn.close();
	      } catch (Exception e) {
	         e.printStackTrace();
	      } 	     
	      return list;
	 }
	
	
	private int cartCountOrder(int cartid) {
		int count =0;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			  Connection conn = DriverManager.getConnection("jdbc:mysql://www-und.ida.liu.se/tahbo763?" +
			                       "user=tahbo763&password=tahbo7635732"); 
			  Statement stat;
			stat = conn.createStatement();
			ResultSet rss = stat.executeQuery("select count(*) as total from cart where cartid = " + cartid + ";");
	         rss.next();
	         count = rss.getInt("total");
	         rss.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;	
	}

	private int orderCount() {
		int count =0;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			  Connection conn = DriverManager.getConnection("jdbc:mysql://www-und.ida.liu.se/tahbo763?" +
			                       "user=tahbo763&password=tahbo7635732"); 
			  Statement stat;
			stat = conn.createStatement();
			ResultSet rss = stat.executeQuery("select count(*) as total from orders where delivered = 0;");
	         rss.next();
	         count = rss.getInt("total");
	         rss.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;	
	}

	public void insertCart(int cartid, int PID,String username, int qty){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			  Connection conn = DriverManager.getConnection("jdbc:mysql://www-und.ida.liu.se/tahbo763?" +
			                       "user=tahbo763&password=tahbo7635732");
	         
	        PreparedStatement prep = conn.prepareStatement("insert into cart (cartid, pid, username, qty) values (?,?,?,?);");
	        prep.setInt(1, cartid);
	        prep.setInt(2, PID);
	        prep.setString(3, username);
	        prep.setInt(4, qty);
	        prep.execute();
	        
	        conn.close();
     } catch (Exception e) {
        e.printStackTrace();
     } 
		
	}
	
	public int genID() {
		int id =1;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			  Connection conn = DriverManager.getConnection("jdbc:mysql://www-und.ida.liu.se/tahbo763?" +
			                       "user=tahbo763&password=tahbo7635732"); 
			  Statement stat;
			stat = conn.createStatement();
			ResultSet rss = stat.executeQuery("select max(cartid)+1 as id from cart;");
	         if (rss.next())
	        	 id = rss.getInt("id");
	         else 
	        	 id =1;
	         rss.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;	
         
	}

	public void insertOrder(int cartid, String carttype, String holdername, String cardnumber,
			String expiryyear, String expirymonth, String securitynumber,double payableAmount){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			  Connection conn = DriverManager.getConnection("jdbc:mysql://www-und.ida.liu.se/tahbo763?" +
			                       "user=tahbo763&password=tahbo7635732");
	         
	        PreparedStatement prep = 
	        	conn.prepareStatement("insert into orders (cartid, carttype, holdername, cardnumber,expiryyear, expirymonth, securitynumber,payableamount)" +
	        		"values (?,?,?,?,?,?,?,?);");
	        prep.setInt(1, cartid);
	        prep.setString(2, carttype);
	        prep.setString(3, holdername);
	        prep.setString(4, cardnumber);
	        prep.setString(5, expiryyear);
	        prep.setString(6, expirymonth);
	        prep.setString(7, securitynumber);
	        prep.setDouble(8, payableAmount);
	        
	        prep.execute();
	        
	        conn.close();
     } catch (Exception e) {
        e.printStackTrace();
     } 
		
	}
	
	public void insertProduct(int PID, String model,double price,int discount,String img){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			  Connection conn = DriverManager.getConnection("jdbc:mysql://www-und.ida.liu.se/tahbo763?" +
			                       "user=tahbo763&password=tahbo7635732");
	         
	        PreparedStatement prep = conn.prepareStatement("insert into product  (pid, model, price, discount, img) values (?,?,?,?,?);");
	        prep.setInt(1, PID);
	        prep.setString(2, model);
	        prep.setDouble(3, price);
	        prep.setInt(4, discount);
	        prep.setString(5, img);
	        prep.execute();
	        
	        conn.close();
     } catch (Exception e) {
        e.printStackTrace();
     } 
		
	}

public void setDelivered(int cartid){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			  Connection conn = DriverManager.getConnection("jdbc:mysql://www-und.ida.liu.se/tahbo763?" +
			                       "user=tahbo763&password=tahbo7635732");
	         
	        PreparedStatement prep = conn.prepareStatement("update order set delivered=1 where cartid = ?;");
	        prep.setInt(1, cartid);
	        prep.execute();
	        
	        conn.close();
     } catch (Exception e) {
        e.printStackTrace();
     } 
		
	}
	
public void updateProduct(int PID, String model,double price,int discount,String img){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			  Connection conn = DriverManager.getConnection("jdbc:mysql://www-und.ida.liu.se/tahbo763?" +
			                       "user=tahbo763&password=tahbo7635732");
	         
	        PreparedStatement prep = conn.prepareStatement("update product  set model = ?, price = ?, discount = ?, img=? where pid = ?;");
	        prep.setString(1, model);
	        prep.setDouble(2, price);
	        prep.setInt(3, discount);
	        prep.setString(4, img);
	        prep.setInt(5, PID);
	        prep.execute();
	        
	        conn.close();
     } catch (Exception e) {
        e.printStackTrace();
     } 
		
	}
	
	public void insertAccount(String username,String password,String firstname,String lastname,String email,String phonenumber,String address,String city,String zip){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			  Connection conn = DriverManager.getConnection("jdbc:mysql://www-und.ida.liu.se/tahbo763?" +
			                       "user=tahbo763&password=tahbo7635732");
	         
	        PreparedStatement prep = 
	        	conn.prepareStatement("insert into account (username, password,firstname,lastname,email,phonenumber,address,city,zip)" +
	        		"values (?,?,?,?,?,?,?,?,?);");
	        
	        
	        prep.setString(1, username);
	        prep.setString(2, password);
	        prep.setString(3, firstname);
	        prep.setString(4, lastname);
	        prep.setString(5, email);
	        prep.setString(6, phonenumber);
	        prep.setString(7, address);
	        prep.setString(8, city);
	        prep.setString(9, zip);	        
	        prep.execute();
	        
	        conn.close();
     } catch (Exception e) {
        e.printStackTrace();
     } 
		
	}
	
	public boolean checkPassword(String password, String username){
	try {
			String pass;
			Class.forName("com.mysql.jdbc.Driver");
			  Connection conn = DriverManager.getConnection("jdbc:mysql://www-und.ida.liu.se/tahbo763?" +
			                       "user=tahbo763&password=tahbo7635732"); 
			  Statement stat;
			stat = conn.createStatement();
			ResultSet rss = stat.executeQuery("select password from account where username = '" + username + "' ;");
			
	         rss.next();
	         pass = rss.getString("password");
	         rss.close();
	         if (pass.equals(password))
	        	 return true;
	         else 
	        	 return false;	         
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean checkIsAdmin(String username){
		try {
				int isAdmin;
				Class.forName("com.mysql.jdbc.Driver");
				  Connection conn = DriverManager.getConnection("jdbc:mysql://www-und.ida.liu.se/tahbo763?" +
				                       "user=tahbo763&password=tahbo7635732"); 
				  Statement stat;
				stat = conn.createStatement();
				ResultSet rss = stat.executeQuery("select isadmin from account where username = '" + username + "' ;");
				
		         rss.next();
		         isAdmin = rss.getInt("isadmin");
		         rss.close();
		         if (isAdmin == 1)
		        	 return true;
		         else 
		        	 return false;	         
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
	
	private int PhoneCount(){
		int count =0;
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			  Connection conn = DriverManager.getConnection("jdbc:mysql://www-und.ida.liu.se/tahbo763?" +
			                       "user=tahbo763&password=tahbo7635732"); 
			  Statement stat;
			stat = conn.createStatement();
			ResultSet rss = stat.executeQuery("select count(*) as total from product;");
	         rss.next();
	         count = rss.getInt("total");
	         rss.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;	
         
	}

	public String getEmail(String userName) {
		String email="";
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			  Connection conn = DriverManager.getConnection("jdbc:mysql://www-und.ida.liu.se/tahbo763?" +
			                       "user=tahbo763&password=tahbo7635732"); 
			  Statement stat;
			stat = conn.createStatement();
			ResultSet rss = stat.executeQuery("select email from account where username = '" + userName + "';");
	         rss.next();
	         email = rss.getString("email");
	         rss.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return email;
	}

}
