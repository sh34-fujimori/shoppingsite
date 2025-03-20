package model;

import java.sql.Timestamp;
import java.util.List;

public class OrderHistoryInfo {
	private int orderId;
	private String usersId;
	private String usersName;
	private List<CartListItem> cartListItem;
	
	// 3/6 追加
	private String postcode;
    private String address;
    private String tel;
    private String mail;
    private String pay;
	private Timestamp date;	// 購入日時
	private String dateString;
    
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUsersId() {
        return usersId;
    }
    public void setUsersId(String usersId) {
        this.usersId = usersId;
    }
    
    public String getUsersName() {
        return usersName;
    }
    public void setUsersName(String usersName) {
        this.usersName = usersName;
    }
    
    public List<CartListItem> getCartListItem() {
        return cartListItem;
    }
    public void setCartListItem(List<CartListItem> cartListItem) {
        this.cartListItem = cartListItem;
    }
    
    // 3/6 追加
	public String getPostcode() {return postcode;}
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
	
	public String getAddress() {return address;}
	
    public void setAddress(String address) {
        this.address = address;
    }
    
	public String getTel() {return tel;}
    public void setTel(String tel) {
        this.tel = tel;
    }
	
	public String getMail() {return mail;}
    public void setMail(String mail) {
        this.mail = mail;
    }
	
	public String getPay() {return pay;}
	
    public void setPay(String pay) {
        this.pay = pay;
    }
    
    public void setDate(Timestamp date) {
    	this.date = date;
    }
    
    public Timestamp getDate() {
    	return date;
    }
    
    public void setDateString(String dateString) {
    	this.dateString = dateString;
    }
    
    public String getDateString() {
    	return dateString;
    }
	
}

