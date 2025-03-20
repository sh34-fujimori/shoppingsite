package model;

public class Item {
	int ItemId;
	String ItemName;
	int ItemPrice;
	int ItemZaiko;
	String ItemPas;
	
	public Item
	(int id,String name,
			int price,int zaiko,String pas) {
		this.ItemId = id;
		this.ItemName = name;
		this.ItemPrice = price;
		this.ItemZaiko = zaiko;
		this.ItemPas = pas;
	}
	
	public Item(String name) {
	    this.ItemName = name;
	}

	
	public int getId() {
		return ItemId;
	}
	public String getName() {
		return ItemName;
	}
	public int getPrice() {
		return ItemPrice;
	}
	public int getZaiko() {
		return ItemZaiko;
	}
	public String getPas() {
		return ItemPas;
	}
}
