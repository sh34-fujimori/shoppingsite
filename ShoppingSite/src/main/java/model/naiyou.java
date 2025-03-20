package model;

public class naiyou{

	private String name;
	private String postcode;
    private String address;
    private String tel;
    private String mail;
    private String pay;
	
	public naiyou(String Name,String Postcode,String Address,String Tel,String Mail,String Pay) {
		this.name=Name;
		this.postcode=Postcode;
		this.address=Address;
		this.tel=Tel;
		this.mail=Mail;
		this.pay=Pay;
	}
	
	public String getName() {return name;}
	public String getPostcode() {return postcode;}
	public String getAddress() {return address;}
	public String getTel() {return tel;}
	public String getMail() {return mail;}
	public String getPay() {return pay;}
}
