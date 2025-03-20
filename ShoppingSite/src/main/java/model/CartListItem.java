package model;

public class CartListItem {
    private int itemId;
    private String itemName;
    private int itemPrice;
    private int itemZaiko;
    private int itemKosu;
    private String itemPas;
    private int sum;

    // 3/4仮で追加
	public CartListItem(int itemId, int itemKosu) {
		this.itemId = itemId;
		this.itemKosu = itemKosu;

	}
	 // 3/4仮で追加
	public CartListItem(int itemId, String itemName, int itemPrice, int itemKosu, int sum, String itemPas) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemKosu = itemKosu;
		this.sum = sum;
		this.itemPas = itemPas;

	}
	 // 3/4仮で追加
	public CartListItem() {

	}
    
    
    public int getItemId() {
        return itemId;
    }
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    public int getItemPrice() {
        return itemPrice;
    }
    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }
    
    public int getItemZaiko() {
        return itemZaiko;
    }
    public void setItemZaiko(int itemZaiko) {
        this.itemZaiko = itemZaiko;
    }
    
    public int getItemKosu() {
        return itemKosu;
    }
    public void setItemKosu(int itemKosu) {
        this.itemKosu = itemKosu;
    }
    public String getItemPas() {
    	return itemPas;
    }
    public void setItemPas(String itemPas) {
    	this.itemPas = itemPas;
    }
    
    public int getSum() {
    	return sum;
    }
    public void setSum(int sum) {
    	this.sum = sum;
    }
}
