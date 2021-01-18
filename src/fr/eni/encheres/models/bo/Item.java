package fr.eni.encheres.models.bo;

import java.time.LocalDate;

public class Item {

	private int item_id;
	private String item_name;
	private String description;
	private LocalDate bids_start_date;
	private LocalDate bids_end_date;
	private int initial_price;
	private int current_price;
	private int seller_id;
	private String street;
	private int postal_code;
	private String city;
	private int category_id;
	
	
	//--------- Constructeur
	
	public Item() {}

	public Item(String item_name, String description, LocalDate bids_start_date, LocalDate bids_end_date,
			int initial_price, int current_price, int seller_id, String street, int postal_code, String city,
			int category_id) {
		super();
		this.item_name = item_name;
		this.description = description;
		this.bids_start_date = bids_start_date;
		this.bids_end_date = bids_end_date;
		this.initial_price = initial_price;
		this.current_price = current_price;
		this.seller_id = seller_id;
		this.street = street;
		this.postal_code = postal_code;
		this.city = city;
		this.category_id = category_id;
	}

	public Item(int item_id, String item_name, String description, LocalDate bids_start_date, LocalDate bids_end_date,
			int initial_price, int current_price, int seller_id, String street, int postal_code, String city,
			int category_id) {
		super();
		this.item_id = item_id;
		this.item_name = item_name;
		this.description = description;
		this.bids_start_date = bids_start_date;
		this.bids_end_date = bids_end_date;
		this.initial_price = initial_price;
		this.current_price = current_price;
		this.seller_id = seller_id;
		this.street = street;
		this.postal_code = postal_code;
		this.city = city;
		this.category_id = category_id;
	}

	
	//---------- Getters/Setters
	
	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getBids_start_date() {
		return bids_start_date;
	}

	public void setBids_start_date(LocalDate bids_start_date) {
		this.bids_start_date = bids_start_date;
	}

	public LocalDate getBids_end_date() {
		return bids_end_date;
	}

	public void setBids_end_date(LocalDate bids_end_date) {
		this.bids_end_date = bids_end_date;
	}

	public int getInitial_price() {
		return initial_price;
	}

	public void setInitial_price(int initial_price) {
		this.initial_price = initial_price;
	}

	public int getCurrent_price() {
		return current_price;
	}

	public void setCurrent_price(int current_price) {
		this.current_price = current_price;
	}

	public int getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(int postal_code) {
		this.postal_code = postal_code;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	
	//-------- Méthode toString
	
	@Override
	public String toString() {
		return "Item_id : " + item_id + ", item_name : " + item_name + ", description : " + description
				+ ", bids_start_date : " + bids_start_date + ", bids_end_date : " + bids_end_date + ", initial_price : "
				+ initial_price + ", current_price : " + current_price + ", seller_id : " + seller_id + ", street : " + street
				+ ", postal_code : " + postal_code + ", city : " + city + ", category_id : " + category_id + "/n";
	}
		
	
}
