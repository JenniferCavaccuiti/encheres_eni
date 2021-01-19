package fr.eni.encheres.models.bo;

import java.time.LocalDate;

public class Item {

	private int idItem;
	private String itemName;
	private String description;
	private LocalDate bidsStartDate;
	private LocalDate bidsEndDate;
	private int initialPrice;
	private int currentPrice;
	private int idSeller;
	private String street;
	private String postalCode;
	private String city;
	private int idCategory;
	
	
	//--------- Constructeur
	
	public Item() {}

	public Item(String itemName, String description, LocalDate bidsStartDate, LocalDate bidsEndDate, int initialPrice,
			int currentPrice, int idSeller, String street, String postalCode, String city, int idCategory) {
		
		this.itemName = itemName;
		this.description = description;
		this.bidsStartDate = bidsStartDate;
		this.bidsEndDate = bidsEndDate;
		this.initialPrice = initialPrice;
		this.currentPrice = currentPrice;
		this.idSeller = idSeller;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
		this.idCategory = idCategory;
	}

	public Item(int idItem, String itemName, String description, LocalDate bidsStartDate, LocalDate bidsEndDate,
			int initialPrice, int currentPrice, int idSeller, String street, String postalCode, String city,
			int idCategory) {
		
		this.idItem = idItem;
		this.itemName = itemName;
		this.description = description;
		this.bidsStartDate = bidsStartDate;
		this.bidsEndDate = bidsEndDate;
		this.initialPrice = initialPrice;
		this.currentPrice = currentPrice;
		this.idSeller = idSeller;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
		this.idCategory = idCategory;
	}

	
	//---------- Getters/Setters
	
	public int getIdItem() {
		return idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getBidsStartDate() {
		return bidsStartDate;
	}

	public void setBidsStartDate(LocalDate bidsStartDate) {
		this.bidsStartDate = bidsStartDate;
	}

	public LocalDate getBidsEndDate() {
		return bidsEndDate;
	}

	public void setBidsEndDate(LocalDate bidsEndDate) {
		this.bidsEndDate = bidsEndDate;
	}

	public int getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(int initialPrice) {
		this.initialPrice = initialPrice;
	}

	public int getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(int currentPrice) {
		this.currentPrice = currentPrice;
	}

	public int getIdSeller() {
		return idSeller;
	}

	public void setIdSeller(int idSeller) {
		this.idSeller = idSeller;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	
	
	//-------- Méthode toString
	
	@Override
	public String toString() {
		return "Id : " + idItem + ", Name : " + itemName + ", Description : " + description
				+ ", Start date : " + bidsStartDate + ", End date : " + bidsEndDate + ", Initial price : "
				+ initialPrice + ", Current price : " + currentPrice + ", Seller id : " + idSeller + ", Street : " + street
				+ ", Postal code : " + postalCode + ", City : " + city + ", Category : " + idCategory + "/n";
	}

	
		
	
}
