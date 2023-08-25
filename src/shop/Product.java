package shop;

public class Product {
	private int num;// 자동할당
	private String name;
	private int price;
	private int amount;
	private String seller;
	
	
	
	public Product() {

	}



	public Product(int num, String name, int price, int amount, String seller) {
		super();
		this.num = num;
		this.name = name;
		this.price = price;
		this.amount = amount;
		this.seller = seller;
	}



	public int getNum() {
		return num;
	}



	public void setNum(int num) {
		this.num = num;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}



	public int getAmount() {
		return amount;
	}



	public void setAmount(int amount) {
		this.amount = amount;
	}



	public String getSeller() {
		return seller;
	}



	public void setSeller(String seller) {
		this.seller = seller;
	}
	
	
}
