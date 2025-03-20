package model;

public class Cart {
	int CartId;
	String CartName;
	int CartPrice;
	int CartZaiko;
	int Cartsougaku;
	int ItemId;
	String ItemPas;

	public Cart() {

	}

	public Cart(int price) {
		this.Cartsougaku = price;
	}

	public Cart(int id, String name,
			int price, int zaiko, int sougaku,int ItemId,String ItemPas) {
		this.CartId = id;
		this.CartName = name;
		this.CartPrice = price;
		this.CartZaiko = zaiko;
		this.Cartsougaku = sougaku;
		this.ItemId = ItemId;
		this.ItemPas = ItemPas;
	}

	public int getId() {
		return CartId;
	}

	public String getName() {
		return CartName;
	}

	public int getprice() {
		return CartPrice;
	}

	public int getZaiko() {
		return CartZaiko;
	}
	public int getSougaku() {
		return CartZaiko;
	}
	public int getNo() {
		return ItemId;
	}
	public String getPas() {
		return ItemPas;
	}
	public void addSougaku(int price) {
		Cartsougaku = price;
	}
}
