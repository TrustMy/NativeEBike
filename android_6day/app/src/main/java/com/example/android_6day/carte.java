package com.example.android_6day;

public class carte {


	public String getDishid() {
		return dishid;
	}
	public void setDishid(String dishid) {
		this.dishid = dishid;
	}
	public String getDishname() {
		return dishname;
	}
	public void setDishname(String dishname) {
		this.dishname = dishname;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getStorageManager() {
		return StorageManager;
	}
	public void setStorageManager(String storageManager) {
		StorageManager = storageManager;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getRecomend() {
		return recomend;
	}
	public void setRecomend(String recomend) {
		this.recomend = recomend;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	private String dishid;
	private String dishname;
	private int price;
	private String StorageManager ;
	private int type;
	private String recomend;
	private int id;
	private int number;
	public carte(String dishid, String dishname, int price, String storageManager, int type, String recomend, int id,
			int number) {
		super();
		this.dishid = dishid;
		this.dishname = dishname;
		this.price = price;
		StorageManager = storageManager;
		this.type = type;
		this.recomend = recomend;
		this.id = id;
		this.number = number;
	}
	

}
