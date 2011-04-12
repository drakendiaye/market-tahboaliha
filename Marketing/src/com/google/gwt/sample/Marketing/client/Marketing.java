package com.google.gwt.sample.Marketing.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Marketing implements EntryPoint ,ValueChangeHandler {
	
	private FlexTable productFlexTable  = new FlexTable();
	private FlexTable editProductFlexTable  = new FlexTable();
	private FlexTable cartFlexTable  = new FlexTable();
	private FlexTable orderFlexTable  = new FlexTable();
	private FlexTable accountFlexTable  = new FlexTable();
	private FlexTable deliveredFlexTable  = new FlexTable();
	private FlexTable addProductFlexTable  = new FlexTable();
	private VerticalPanel mainPanel = new VerticalPanel();
	private VerticalPanel orderPanel = new VerticalPanel();
	private VerticalPanel loginPanel = new VerticalPanel();
	private VerticalPanel registerPanel = new VerticalPanel();
	private VerticalPanel cartPanel = new VerticalPanel();
	private VerticalPanel productPanel = new VerticalPanel();
	private VerticalPanel editProductPanel = new VerticalPanel();	
	private VerticalPanel deliveredPanel =  new VerticalPanel();
	private VerticalPanel linkPanel = new VerticalPanel();	
	private VerticalPanel adminlinkPanel = new VerticalPanel();	
	private MarketingServiceAsync marketingSvc = GWT.create(MarketingService.class);
	private Label errorMsgLabel = new Label();
	private FlexTable loginTable  = new FlexTable();
	private Label loginLabel = new Label();
	private TextBox usernameTextBox = new TextBox();
	private PasswordTextBox passwordTextBox = new PasswordTextBox();
	private Button loginBtn = new Button("login");
	private Account account = new Account("Guest", "123");
	private Label ViewCart= new Label("Cart : 0 , $0");
	private Button orderButton= new Button("Buy");
	private Button registerButton= new Button("Register");
	private TextBox carttypeTextBox = new TextBox();
	private TextBox holdernameTextBox = new TextBox();
	private TextBox cardnumberTextBox= new TextBox();
	private TextBox expiryyearTextBox= new TextBox();
	private TextBox expirymonthTextBox= new TextBox();
	private TextBox securitynumberTextBox= new TextBox();
	private TextBox newusernameTextBox = new TextBox();
	private PasswordTextBox newpasswordTextBox = new PasswordTextBox();
	private TextBox firstnameTextBox= new TextBox();
	private TextBox lastnameTextBox= new TextBox();
	private TextBox emailTextBox= new TextBox();
	private TextBox phonenumberTextBox= new TextBox();
	private TextBox addressTextBox= new TextBox();
	private TextBox cityTextBox= new TextBox();
	private TextBox zipTextBox= new TextBox();
	private TextBox pidTextBox= new TextBox();
	private TextBox modelTextBox= new TextBox();
	private TextBox priceTextBox= new TextBox();
	private TextBox discountTextBox= new TextBox();
	private TextBox imgTextBox= new TextBox();
	private Button addProductButton= new Button("Add product");
	private Hyperlink link1 = new Hyperlink("Product list", "productlist");
	private Hyperlink link2 = new Hyperlink("View cart", "viewcart");
	private Hyperlink link3 = new Hyperlink("Check out", "checkout");
	private Hyperlink link4 = new Hyperlink("Sign up", "signup");
	private Hyperlink link5 = new Hyperlink("Delivered items", "delivereditems");
	private Hyperlink link6 = new Hyperlink("Edit products", "editproducts");
		
	public void onModuleLoad() {

		linkPanel.add(link1);
        linkPanel.add(link2);
        linkPanel.add(link3);
        linkPanel.add(link4);
        adminlinkPanel.add(link5);
        adminlinkPanel.add(link6);
        adminlinkPanel.setVisible(false);
        
        RootPanel.get("leftnav").add(linkPanel);
        RootPanel.get("leftnav").add(adminlinkPanel);

        History.addValueChangeHandler(this);
       
        if(History.getToken().isEmpty()){
            History.newItem("productlist");
        } else {
            changePage(History.getToken());
        }
        
        addProductFlexTable.setText(0, 0, "Product ID");
        addProductFlexTable.setWidget(0, 1,pidTextBox );
        addProductFlexTable.setText(1, 0, "Model");
        addProductFlexTable.setWidget(1, 1, modelTextBox);
        addProductFlexTable.setText(2, 0, "Price");
        addProductFlexTable.setWidget(2, 1, priceTextBox);
        addProductFlexTable.setText(3, 0, "Discount");
        addProductFlexTable.setWidget( 3, 1, discountTextBox);
        addProductFlexTable.setText(4, 0, "Image");
        addProductFlexTable.setWidget(4, 1, imgTextBox);
        addProductFlexTable.setWidget(5, 1, addProductButton);
        
        addProductFlexTable.setCellPadding(6);
		    
        addProductFlexTable.getCellFormatter().addStyleName(0, 0, "phoneListHeader");
        addProductFlexTable.getCellFormatter().addStyleName(1, 0, "phoneListHeader");
        addProductFlexTable.getCellFormatter().addStyleName(2, 0, "phoneListHeader");
        addProductFlexTable.getCellFormatter().addStyleName(3, 0, "phoneListHeader");
        addProductFlexTable.getCellFormatter().addStyleName(4, 0, "phoneListHeader");
        addProductFlexTable.addStyleName("phoneList");
        
        addProductButton.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				insertProduct(Integer.parseInt(pidTextBox.getText()), modelTextBox.getText(),
						Double.parseDouble(priceTextBox.getText()),Integer.parseInt(discountTextBox.getText()),imgTextBox.getText());
				
			}
		});
        
       
		accountFlexTable.setText(0, 0, "First name");
		accountFlexTable.setWidget(0, 1, firstnameTextBox);
		accountFlexTable.setText(1, 0, "Last name");
		accountFlexTable.setWidget(1, 1, lastnameTextBox);
		accountFlexTable.setText(2, 0, "Email");
		accountFlexTable.setWidget(2, 1, emailTextBox);
		accountFlexTable.setText(3, 0, "Phone number");
		accountFlexTable.setWidget(3, 1, phonenumberTextBox);
		accountFlexTable.setText(4, 0, "Address");
		accountFlexTable.setWidget(4, 1, addressTextBox);
		accountFlexTable.setText(5, 0, "City");
		accountFlexTable.setWidget(5, 1, cityTextBox);
		accountFlexTable.setText(6, 0, "Zip Code");
		accountFlexTable.setWidget(6, 1, zipTextBox);
		accountFlexTable.setText(7, 0, "Username");
		accountFlexTable.setWidget(7, 1, newusernameTextBox);
		accountFlexTable.setText(8, 0, "Password");
		accountFlexTable.setWidget(8, 1, newpasswordTextBox);
		
		accountFlexTable.getCellFormatter().addStyleName(0, 0, "phoneListHeader");
		accountFlexTable.getCellFormatter().addStyleName(1, 0, "phoneListHeader");
		accountFlexTable.getCellFormatter().addStyleName(2, 0, "phoneListHeader");
		accountFlexTable.getCellFormatter().addStyleName(3, 0, "phoneListHeader");
		accountFlexTable.getCellFormatter().addStyleName(4, 0, "phoneListHeader");
		accountFlexTable.getCellFormatter().addStyleName(5, 0, "phoneListHeader");
		accountFlexTable.getCellFormatter().addStyleName(6, 0, "phoneListHeader");
		accountFlexTable.getCellFormatter().addStyleName(7, 0, "phoneListHeader");
		accountFlexTable.getCellFormatter().addStyleName(8, 0, "phoneListHeader");
		accountFlexTable.addStyleName("phoneList");
		
	    orderFlexTable.setText(0, 0, "Cart type");
	    orderFlexTable.setWidget(0, 1, carttypeTextBox);
	    orderFlexTable.setText(1, 0, "Holder's name");
	    orderFlexTable.setWidget(1, 1,holdernameTextBox);
	    orderFlexTable.setText(2, 0, "Card number");
	    orderFlexTable.setWidget(2, 1,cardnumberTextBox);
	    orderFlexTable.setText(3, 0, "Expiry year");
	    orderFlexTable.setWidget(3, 1, expiryyearTextBox);
	    orderFlexTable.setText(4, 0, "Expiry month");
	    orderFlexTable.setWidget(4, 1,expirymonthTextBox);
	    orderFlexTable.setText(5, 0, "Security number");
	    orderFlexTable.setWidget(5, 1,securitynumberTextBox);	    
	    
	    orderFlexTable.getCellFormatter().addStyleName(0, 0, "phoneListHeader");
	    orderFlexTable.getCellFormatter().addStyleName(1, 0, "phoneListHeader");
	    orderFlexTable.getCellFormatter().addStyleName(2, 0, "phoneListHeader");
	    orderFlexTable.getCellFormatter().addStyleName(3, 0, "phoneListHeader");
	    orderFlexTable.getCellFormatter().addStyleName(4, 0, "phoneListHeader");
	    orderFlexTable.getCellFormatter().addStyleName(5, 0, "phoneListHeader");
	    orderFlexTable.addStyleName("phoneList");
	    
	    loginTable.setText(0, 0, "username");
	    loginTable.setWidget(0, 1, usernameTextBox);
	    loginTable.setText(1, 0, "password");
	    loginTable.setWidget(1, 1, passwordTextBox);
	    loginTable.setWidget(2, 0, loginBtn);
	    loginTable.setText(3, 0, "Hello: ");
	    loginTable.setWidget(3, 1, loginLabel);
	    loginTable.setWidget(4, 0, ViewCart);
	    loginPanel.add(loginTable);
	    
	    loginTable.getCellFormatter().addStyleName(0, 0, "phoneListHeader");
	    loginTable.getCellFormatter().addStyleName(1, 0, "phoneListHeader");
	    loginTable.addStyleName("phoneList");
	    
	    registerButton.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if ((newusernameTextBox.getText().trim().equals("")) || (newpasswordTextBox.getText().trim().equals(""))){
					Window.alert("The username and password are neccesary!");
					newusernameTextBox.setFocus(true);
					newpasswordTextBox.setText("");
				}
				else if(emailTextBox.getText().trim().equals("")){
					Window.alert("The email is neccesary!");
				}					
				else{
					insertAccount();
				}
			}
		});
	    
	    editProductPanel.add(editProductFlexTable);
	    editProductPanel.add(addProductFlexTable);
	    editProductPanel.setVisible(false);
	    mainPanel.add(editProductPanel);
	    
	    deliveredPanel.add(deliveredFlexTable);
	    deliveredPanel.setVisible(false);
	    mainPanel.add(deliveredPanel);
	    
	    registerPanel.add(accountFlexTable);
	    registerPanel.add(registerButton);
	    registerPanel.setVisible(false);
	    mainPanel.add(registerPanel);
	  
	    productPanel.add(productFlexTable);
	    mainPanel.add(productPanel);	    
	    
	   cartPanel.add(cartFlexTable);  
	   cartPanel.setVisible(false);
	    mainPanel.add(cartPanel);
	    	  
	    orderPanel.add(orderFlexTable);
	    orderPanel.add(orderButton);
	    orderPanel.setVisible(false);
	    mainPanel.add(orderPanel);
	    
	    RootPanel.get("leftnav").add(loginPanel);
	    RootPanel.get("content").add(mainPanel);
	    
	    orderButton.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				/*if  (!cardnumberTextBox.getText().trim().matches("^[0-9]$")) {
				      Window.alert("'" + cardnumberTextBox.getText() + "' is not a valid card number.");
				      holdernameTextBox.selectAll();
				      return;
			    }*/
				if (ShoppingCart.getTotalCount() == 0){
					Window.alert("There is no item in Shopping cart");
				}else if (carttypeTextBox.getText().trim().equals("") ||  holdernameTextBox.getText().trim().equals("") ||
				    	  cardnumberTextBox.getText().trim().equals("") || expiryyearTextBox.getText().trim().equals("") ||
				    	  expirymonthTextBox.getText().trim().equals("") || securitynumberTextBox.getText().trim().equals("") ){
			    			Window.alert("please fill all required fields.");
			    }
				else{
			    	genID();
			    }
				
			}			
		});
	    
	    
	    loginBtn.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if ((account.getUsername() != "Guest") 	&& (ShoppingCart.cart != null) 
						&& (Window.confirm("if you login again all the purchases will be ignored! are you sure?") == false)){
						Window.alert("you can continue purchasing by this user : " + account.getUsername());
						usernameTextBox.setText("");
						passwordTextBox.setText("");
				}					
				else{
					ShoppingCart.cart = null;
					ViewCart.setText("View Cart : 0 , $0");
					account.setUsername(usernameTextBox.getText());
					account.setPassword(passwordTextBox.getText());
					checkPassword();
					passwordTextBox.setText("");
				}					
			}
		});
	    
	    readProductList();	    
	    
	}
	
	 private void insertProduct(int PID, String model,double price,int discount,String img){
		// Initialize the service proxy.
		    if (marketingSvc == null) {
		    	marketingSvc = GWT.create(MarketingService.class);
		    }

		    // Set up the callback object.
		    AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
		      public void onFailure(Throwable caught) {
		    	  // If the stock code is in the list of delisted codes, display an error message.
		          String details = caught.getMessage();
		          errorMsgLabel .setText("Error: " + details);
		          errorMsgLabel.setVisible(true);     
		         }

		      public void onSuccess(Boolean result) {
		    	  pidTextBox.setText("");
		    	  modelTextBox.setText("");
		    	  priceTextBox.setText("");
		    	  discountTextBox.setText("");
		    	  imgTextBox.setText("");
		    	  
		      }
		    };

		    // Make the call to the stock price service.
		    marketingSvc.insertProduct(PID, model, price, discount, img, callback);
		    Product.add(new Product(PID, model, price, discount, img));
     }
     
	public void changePage(String token) {
	    if(History.getToken().equals("signup")) {
	    	orderPanel.setVisible(false);
	    	registerPanel.setVisible(true);
	    	cartPanel.setVisible(false);	   
	    	productPanel.setVisible(false);
	    	deliveredPanel.setVisible(false);
	    	editProductPanel.setVisible(false);
	    }
	    else if (History.getToken().equals("checkout")) {
	    	orderPanel.setVisible(true);
	    	registerPanel.setVisible(false);
	    	cartPanel.setVisible(false);	   
	    	productPanel.setVisible(false);
	    	deliveredPanel.setVisible(false);
	    	editProductPanel.setVisible(false);
	    }
	    else if (History.getToken().equals("viewcart")) {
	    	orderPanel.setVisible(false);
	    	registerPanel.setVisible(false);
	    	cartPanel.setVisible(true);	   
	    	productPanel.setVisible(false);
	    	deliveredPanel.setVisible(false);
	    	editProductPanel.setVisible(false);
	    	if (ShoppingCart.getTotalCount() != 0)
	    		updateCart();	
	    	else
	    		Window.alert("There is no item in Shopping cart!");
	    }else if(History.getToken().equals("delivereditems")){
	    	orderPanel.setVisible(false);
	    	registerPanel.setVisible(false);
	    	cartPanel.setVisible(false);	   
	    	productPanel.setVisible(false);
	    	deliveredPanel.setVisible(true);
	    	editProductPanel.setVisible(false);
	    	//readDeliveredProductList();
	    }	    
	    else if (History.getToken().equals("editproducts")){
	    	orderPanel.setVisible(false);
	    	registerPanel.setVisible(false);
	    	cartPanel.setVisible(false);	   
	    	productPanel.setVisible(false);
	    	deliveredPanel.setVisible(false);
	    	editProductPanel.setVisible(true);
	    	readEditProductList();
	    }	    
	    else {
	    	orderPanel.setVisible(false);
	    	registerPanel.setVisible(false);
	    	cartPanel.setVisible(false);	   
	    	productPanel.setVisible(true);
	    	deliveredPanel.setVisible(false);
	    	editProductPanel.setVisible(false);
	    	 readProductList();	    
	    }
	}
	
	private void insertAccount(){
		// Initialize the service proxy.
	    if (marketingSvc == null) {
	    	marketingSvc = GWT.create(MarketingService.class);
	    }

	    // Set up the callback object.
	    AsyncCallback<String> callback = new AsyncCallback<String>() {
	      public void onFailure(Throwable caught) {
	    	  // If the stock code is in the list of delisted codes, display an error message.
	          String details = caught.getMessage();
	          errorMsgLabel .setText("Error: " + details);
	          errorMsgLabel.setVisible(true);     
	         }

	      public void onSuccess(String result) {
	    	  account.setUsername(result);
	    	  loginLabel.setText(account.getUsername());
	    	  newusernameTextBox.setText("");
	    	  newpasswordTextBox.setText("");
	    	  firstnameTextBox.setText("");
	    	  lastnameTextBox.setText("");
	    	  emailTextBox.setText("");
	    	  phonenumberTextBox.setText("");
	    	  addressTextBox.setText("");
	    	  cityTextBox.setText("");
	    	  zipTextBox.setText("");
	      }
	    };

	    // Make the call to the stock price service.
	    marketingSvc.insertAccount(newusernameTextBox.getText(), newpasswordTextBox.getText(), firstnameTextBox.getText(), lastnameTextBox.getText(), 
	    		emailTextBox.getText(), phonenumberTextBox.getText(), addressTextBox.getText(), cityTextBox.getText(), zipTextBox.getText(), callback);
	}
	
	private void insertOrder() {
		// Initialize the service proxy.
	    if (marketingSvc == null) {
	    	marketingSvc = GWT.create(MarketingService.class);
	    }

	    // Set up the callback object.
	    AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
	      public void onFailure(Throwable caught) {
	    	  // If the stock code is in the list of delisted codes, display an error message.
	          String details = caught.getMessage();
	          errorMsgLabel .setText("Error: " + details);
	          errorMsgLabel.setVisible(true);     
	         }

	      public void onSuccess(Boolean result) {
	    	  ShoppingCart.cart = null;
	    	  account.setUsername("Guest");
	    	  account.setPassword("");
	    	  loginLabel.setText("Guest");
	    	  ViewCart.setText("Cart : 0 , $0");
	    	  carttypeTextBox.setText("");
	    	  holdernameTextBox.setText("");
	    	  cardnumberTextBox.setText("");
	    	  expiryyearTextBox.setText("");
	    	  expirymonthTextBox.setText("");
	    	  securitynumberTextBox.setText("");
	    	  Window.alert("Thanks for your shopping. your shopping id is " + ShoppingCart.cartid);
	      }
	    };

	    // Make the call to the stock price service.
	    order o = new order(ShoppingCart.cartid, ShoppingCart.cart, carttypeTextBox.getText(), holdernameTextBox.getText(), cardnumberTextBox.getText(),
				expiryyearTextBox.getText(), expirymonthTextBox.getText(), securitynumberTextBox.getText(), ShoppingCart.getTotalPrice());	
	    marketingSvc.insertOrder(o.getCartId(), o.getCartType(), o.getHolderName(), o.getCardNumber(), o.getExpiryYear(), 
	    													o.getExpiryMonth(), o.getSecurityNumber(), o.getPayableAmount(), callback); 
	}

	private void updateCart() {		
		cartFlexTable.removeAllRows();
		cartFlexTable.setText(0, 0, "Model");
	    cartFlexTable.setText(0, 1, "Price");
	    cartFlexTable.setText(0, 2, "Image");
	    cartFlexTable.setText(0, 3, "Qty");
	    cartFlexTable.setText(0, 4, " X ");
	    cartFlexTable.setText(0, 5, "save");
	    
	    cartFlexTable.getRowFormatter().addStyleName(0, "phoneListHeader");
	    cartFlexTable.addStyleName("phoneList");
	    cartFlexTable.getCellFormatter().addStyleName(0, 1, "phoneListNumericColumn");
	    cartFlexTable.getCellFormatter().addStyleName(0, 4, "phoneListRemoveColumn");
	    cartFlexTable.getCellFormatter().addStyleName(0, 5, "phoneListUpdateColumn");
	    
		for (int i = 0; i < ShoppingCart.cart.length; i++) {
			updateCartTable(ShoppingCart.cart[i]);
		 }	  
		
	}
	
	private void updateCartTable(ShoppingCart cart) {
		Button removeProductButton = new Button(" X ");
		Button updateQtyButton = new Button("save");
		TextBox qty = new TextBox();
		Image image = new Image();
		image.setPixelSize(50, 60);
		int row = cartFlexTable.getRowCount();
		final int pid = cart.getProduct().getPID();
		String modelText = cart.getProduct().getModel();
		String discountPriceText = NumberFormat.getFormat("#,##0.00").format(cart.getProduct().getDiscountPrice());
		image.setUrl("images/" + cart.getProduct().getImg());
		qty.setText(Integer.toString(cart.getQty()));

		removeProductButton.addClickHandler(new ClickHandler() {				
				public void onClick(ClickEvent event) {
					int index = ShoppingCart.indexOf(pid);
					ShoppingCart.remove(index);		
					cartFlexTable.removeRow(index+1);
					ViewCart.setText("View Cart : " + ShoppingCart.getTotalCount() + " $" + NumberFormat.getFormat("#,##0.00").format(ShoppingCart.getTotalPrice()));
				}
			});		 
		
		updateQtyButton.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				int index = ShoppingCart.indexOf(pid);
				ShoppingCart.cart[index].setQty(Integer.parseInt( ((TextBox)cartFlexTable.getWidget(index+1, 3)).getText()));
				ViewCart.setText("View Cart : " + ShoppingCart.getTotalCount() + " $" + NumberFormat.getFormat("#,##0.00").format(ShoppingCart.getTotalPrice()));
			}
		});
		
		  cartFlexTable.setText(row, 0, modelText);
		  cartFlexTable.setText(row, 1, "$" + discountPriceText);
		  cartFlexTable.setWidget(row,2,image);
		  cartFlexTable.setWidget(row,3,qty);
		  cartFlexTable.setWidget(row,4,removeProductButton);
		  cartFlexTable.setWidget(row,5,updateQtyButton);
		  
		
	}

	private void insertCartDB() {
		// Initialize the service proxy.
	    if (marketingSvc == null) {
	    	marketingSvc = GWT.create(MarketingService.class);
	    }

	    // Set up the callback object.
	    AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
	      public void onFailure(Throwable caught) {
	    	  // If the stock code is in the list of delisted codes, display an error message.
	          String details = caught.getMessage();
	          errorMsgLabel.setText("Error: " + details);
	          errorMsgLabel.setVisible(true);      }

	      public void onSuccess(Boolean result) {
	    	
	      }		
	    };

	    // Make the call to the stock price service.
	    
	    for (int i= 0; i< ShoppingCart.cart.length; i++)
	    	marketingSvc.insertCart(ShoppingCart.cartid, ShoppingCart.cart[i].getProduct().getPID(), ShoppingCart.cart[i].getUsername(), ShoppingCart.cart[i].getQty(), callback);
	   
	    
	}	
	
	private void genID() {
		 if (marketingSvc == null) {
		    	marketingSvc = GWT.create(MarketingService.class);
		    }
		// Set up the callback object.
		    AsyncCallback<Integer> callback = new AsyncCallback<Integer>() {
		      public void onFailure(Throwable caught) {
		    	  // If the stock code is in the list of delisted codes, display an error message.
		          String details = caught.getMessage();
		          errorMsgLabel.setText("Error: " + details);
		          errorMsgLabel.setVisible(true);      }

		      public void onSuccess(Integer result) {
		    	  ShoppingCart.cartid = result;
		    	  insertCartDB();
		    	  insertOrder();
		    	  sendEmail(ShoppingCart.cartid, account.getUsername());
		      }
	
		    };
		    marketingSvc.genID(callback);
	}

	private void sendEmail(int cartid, String username) {
		// Initialize the service proxy.
	    if (marketingSvc == null) {
	    	marketingSvc = GWT.create(MarketingService.class);
	    }

	    // Set up the callback object.
	    AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
	      public void onFailure(Throwable caught) {
	    	  // If the stock code is in the list of delisted codes, display an error message.
	          String details = caught.getMessage();
	          errorMsgLabel .setText("Error: " + details);
	          errorMsgLabel.setVisible(true);     
	         }

	      public void onSuccess(Boolean result) {
	       
	      }
	    };

	    // Make the call to the stock price service.
	    marketingSvc.sendEmail(Integer.toString(cartid), username, callback) ;
				
	}		
	
	private void readProductList() {
		// Initialize the service proxy.
	    if (marketingSvc == null) {
	    	marketingSvc = GWT.create(MarketingService.class);
	    }

	    // Set up the callback object.
	    AsyncCallback<Product[]> callback = new AsyncCallback<Product[]>() {
	      public void onFailure(Throwable caught) {
	    	  // If the stock code is in the list of delisted codes, display an error message.
	          String details = caught.getMessage();
	          errorMsgLabel.setText("Error: " + details);
	          errorMsgLabel.setVisible(true);      }

	      public void onSuccess(Product[] result) {
	        updateProductTable(result);
	      }		
	    };

	    // Make the call to the stock price service.
	    marketingSvc.readProductList(callback); 
		
	}

	private void updateProductTable(Product[] result) {
		 Product.productList = result;
		 productFlexTable.removeAllRows();
		 productFlexTable.setText(0, 0, "Product ID");
		productFlexTable.setText(0, 1, "Model");
		productFlexTable.setText(0, 2, "Price");
		productFlexTable.setText(0, 3, "Discount");
		productFlexTable.setText(0, 4, "Image");
		productFlexTable.setText(0, 5, "Add to Cart");
		productFlexTable.setCellPadding(6);
		
		productFlexTable.getRowFormatter().addStyleName(0, "phoneListHeader");
		productFlexTable.addStyleName("phoneList");
		productFlexTable.getCellFormatter().addStyleName(0, 2, "phoneListNumericColumn");
		productFlexTable.getCellFormatter().addStyleName(0, 3, "phoneListNumericColumn");
		productFlexTable.getCellFormatter().addStyleName(0, 5, "phoneListUpdateColumn");
				    
		for (int i = 0; i < result.length; i++) {
			updateListTable(result[i]);
		 }	  
		
	}
	
	private void updateListTable(Product product) {
		Button addProductButton = new Button("Add to Shopping cart");
		Image image = new Image();
		image.setPixelSize(50, 60);
		int row = productFlexTable.getRowCount();
		final String PIDText = Integer.toString(product.getPID());
		String modelText = product.getModel();
		String priceText = NumberFormat.getFormat("#,##0.00").format(product.getPrice());
		NumberFormat discountFormat = NumberFormat.getFormat("#,##0.00");
		String discountText = discountFormat.format(product.getDiscount());
		String discountPriceText = discountFormat.format(product.getDiscountPrice());
		image.setUrl("images/" + product.getImg());

		 addProductButton.addClickHandler(new ClickHandler() {
				
				public void onClick(ClickEvent event) {
					int index = Product.indexOf(PIDText);
					Product p = Product.productList[index];
					if (account.getUsername() == "Guest"){
						Window.alert("Please login before any purchase");
						usernameTextBox.setFocus(true);
					}
					else{
						ShoppingCart.add(new ShoppingCart(p, account.getUsername()));
						ViewCart.setText("View Cart : " + ShoppingCart.getTotalCount() + " $" + NumberFormat.getFormat("#,##0.00").format(ShoppingCart.getTotalPrice()));
					}
				}
			});
		  
		  productFlexTable.setText(row, 0, PIDText);
		  productFlexTable.setText(row, 1, modelText);
		  productFlexTable.setText(row, 2, "$" + priceText);
		  productFlexTable.setText(row, 3, "$" + discountPriceText + " (" + discountText + "%)");
		  productFlexTable.setWidget(row,4,image);
		  productFlexTable.setWidget(row,5,addProductButton);
		  		 
	}
	
	private void readEditProductList() {
		// Initialize the service proxy.
	    if (marketingSvc == null) {
	    	marketingSvc = GWT.create(MarketingService.class);
	    }

	    // Set up the callback object.
	    AsyncCallback<Product[]> callback = new AsyncCallback<Product[]>() {
	      public void onFailure(Throwable caught) {
	    	  // If the stock code is in the list of delisted codes, display an error message.
	          String details = caught.getMessage();
	          errorMsgLabel.setText("Error: " + details);
	          errorMsgLabel.setVisible(true);      }

	      public void onSuccess(Product[] result) {
	        updateEditProductTable(result);
	      }		
	    };

	    // Make the call to the stock price service.
	    marketingSvc.readProductList(callback); 
		
	}

	private void updateEditProductTable(Product[] result) {
		 Product.productList = result;
		 editProductFlexTable.removeAllRows();
		 editProductFlexTable.setText(0, 0, "Product ID");
		 editProductFlexTable.setText(0, 1, "Model");
		 editProductFlexTable.setText(0, 2, "Price");
		 editProductFlexTable.setText(0, 3, "Discount");
		 editProductFlexTable.setText(0, 4, "Image");
		 editProductFlexTable.setText(0, 5, "Save");
		 editProductFlexTable.setCellPadding(6);
		    
		 editProductFlexTable.getRowFormatter().addStyleName(0, "phoneListHeader");
		 editProductFlexTable.addStyleName("phoneList");
		 editProductFlexTable.getCellFormatter().addStyleName(0, 2, "phoneListNumericColumn");
		 editProductFlexTable.getCellFormatter().addStyleName(0, 3, "phoneListNumericColumn");
		 editProductFlexTable.getCellFormatter().addStyleName(0, 5, "phoneListUpdateColumn");
		for (int i = 0; i < result.length; i++) {
			updateEditListTable(result[i]);
		 }	  
		
	}
	
	private void updateEditListTable(Product product) {
		Button editProductButton = new Button("Save");
		int row = editProductFlexTable.getRowCount();
		final String PIDText = Integer.toString(product.getPID());
		TextBox modelText = new TextBox();
		modelText.setText(product.getModel());
		TextBox priceText = new TextBox(); 
		priceText.setText(Double.toString(product.getPrice()));
		TextBox discountText = new TextBox() ;
		discountText.setText(Integer.toString(product.getDiscount()));
		TextBox imgText = new TextBox();
		imgText.setText(product.getImg());

		editProductButton.addClickHandler(new ClickHandler() {
				
				public void onClick(ClickEvent event) {
					int index = Product.indexOf(PIDText);
					Product p = Product.productList[index];
					index++;
					String model = ((TextBox)editProductFlexTable.getWidget(index, 1) ).getText();
					double price = Double.parseDouble(((TextBox)editProductFlexTable.getWidget(index, 2) ).getText());
					int discount = Integer.parseInt(((TextBox)editProductFlexTable.getWidget(index, 3) ).getText());
					String img = ((TextBox)editProductFlexTable.getWidget(index, 4) ).getText();				
					updateProduct(p.getPID(),model , price, discount, img);
					p.setPrice(price);
					p.setDiscount(discount);
					p.setImg(img);
					p.setPrice(price);
				}
			});
		  
		editProductFlexTable.setText(row, 0, PIDText);
		editProductFlexTable.setWidget(row, 1, modelText);
		editProductFlexTable.setWidget(row, 2, priceText);
		editProductFlexTable.setWidget(row, 3, discountText );
		editProductFlexTable.setWidget(row,4,imgText);
		editProductFlexTable.setWidget(row,5,editProductButton);
		  		 
	}

	public void updateProduct(int PID, String model,double price,int discount,String img){
		// Initialize the service proxy.
	    if (marketingSvc == null) {
	    	marketingSvc = GWT.create(MarketingService.class);
	    }

	    // Set up the callback object.
	    AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
	      public void onFailure(Throwable caught) {
	    	 
	          String details = caught.getMessage();
	          errorMsgLabel .setText("Error: " + details);
	          errorMsgLabel.setVisible(true);     
	         }

	      public void onSuccess(Boolean result) {

	      }	
	    };
	    
	    marketingSvc.updateProduct(PID, model, price, discount, img, callback) ;
	}
	
	/*
	private void readDeliveredProductList() {
		// Initialize the service proxy.
	    if (marketingSvc == null) {
	    	marketingSvc = GWT.create(MarketingService.class);
	    }

	    // Set up the callback object.
	    AsyncCallback<order[]> callback = new AsyncCallback<order[]>() {
	      public void onFailure(Throwable caught) {
	    	  // If the stock code is in the list of delisted codes, display an error message.
	          String details = caught.getMessage();
	          errorMsgLabel.setText("Error: " + details);
	          errorMsgLabel.setVisible(true);      }

	      public void onSuccess(order[] result) {
	        updatedeliveredTable(result);
	      }		
	    };

	    // Make the call to the stock price service.
	    marketingSvc.readOrdersList(callback); 
		
	}*/

	private void updatedeliveredTable(order[] result) {
		 //order.deliveredList = result;
		 editProductFlexTable.removeAllRows();
		 editProductFlexTable.setText(0, 0, "ID");
		 editProductFlexTable.setText(0, 1, "payable amount");
		 editProductFlexTable.setText(0, 2, "deliver");
		 editProductFlexTable.setCellPadding(6);
		    
		 editProductFlexTable.getRowFormatter().addStyleName(0, "phoneListHeader");
		 editProductFlexTable.addStyleName("phoneList");		 
		 editProductFlexTable.getCellFormatter().addStyleName(0, 1, "phoneListNumericColumn");
		 editProductFlexTable.getCellFormatter().addStyleName(0, 2, "phoneListUpdateColumn");
		    
		for (int i = 0; i < result.length; i++) {
			updatedeliveredListTable(result[i]);
		 }	  
		
	}
	
	private void updatedeliveredListTable(order o) {
		Button cartIdButton = new Button("Id");
		Button deliverButton = new Button("Deliver");
		int row = deliveredFlexTable.getRowCount();
		final int id = o.getCartId();
		cartIdButton.setText( Integer.toString(id));
		String priceText = NumberFormat.getFormat("#,##0.00").format(o.getPayableAmount());

		 addProductButton.addClickHandler(new ClickHandler() {
				
				public void onClick(ClickEvent event) {
					int index = 1;//order.indexOf(id)+1;
					setDelivered(id);
					
				}

				private void setDelivered(int id) {
					// TODO Auto-generated method stub
					
				}
			});
		  
		 /*deliveredFlexTable.setText(row, 0, PIDText);
		 deliveredFlexTable.setText(row, 1, modelText);
		 deliveredFlexTable.setText(row, 2, "$" + priceText);
		 deliveredFlexTable.setText(row, 3, "$" + discountPriceText + " (" + discountText + "%)");
		 deliveredFlexTable.setWidget(row,4,image);
		 deliveredFlexTable.setWidget(row,5,addProductButton);*/
		  		 
	}
	
	
	public void checkPassword(){
		// Initialize the service proxy.
	    if (marketingSvc == null) {
	    	marketingSvc = GWT.create(MarketingService.class);
	    }

	    // Set up the callback object.
	    AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
	      public void onFailure(Throwable caught) {
	    	 
	          String details = caught.getMessage();
	          errorMsgLabel .setText("Error: " + details);
	          errorMsgLabel.setVisible(true);     
	         }

	      public void onSuccess(Boolean result) {
	        if (result == true){
	        	loginLabel.setText(account.getUsername());
	        	usernameTextBox.setText("");
	        	passwordTextBox.setText("");
	        	isAdmin();
	        }
	        else{
	        	Window.alert("Username or Password is not correct! Please try again.");
	        	loginLabel.setText("");
	        	passwordTextBox.setText("");
	        	passwordTextBox.setFocus(true);
	        }
	      }	
	    };
	    
	    marketingSvc.checkPassword(account.getPassword(), account.getUsername(), callback); 
	}

	private boolean isAdmin() {
		String username = account.getUsername();
		// Initialize the service proxy.
	    if (marketingSvc == null) {
	    	marketingSvc = GWT.create(MarketingService.class);
	    }

	    // Set up the callback object.
	    AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
	      public void onFailure(Throwable caught) {
	    	  // If the stock code is in the list of delisted codes, display an error message.
	          String details = caught.getMessage();
	          errorMsgLabel .setText("Error: " + details);
	          errorMsgLabel.setVisible(true);     
	         }

	      public void onSuccess(Boolean result) {
	    	  account.setIsAdmin(result);
	    	  if (result == true)
	        		adminlinkPanel.setVisible(true);
	        	else
	        		adminlinkPanel.setVisible(false);
	      }
	    };

	    marketingSvc.checkIsAdmin(username, callback);
	    return account.getIsAdmin();
	}
	
	public void onValueChange(ValueChangeEvent event) {
		changePage(History.getToken());
		
	}
	

}
