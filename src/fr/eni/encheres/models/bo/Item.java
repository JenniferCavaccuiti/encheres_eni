package fr.eni.encheres.models.bo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Item {

	private int idItem;
	private String itemName;
	private String description;
	private LocalDateTime bidsStartDate;
	private LocalDateTime bidsEndDate;
	private int initialPrice;
	private int currentPrice;
	private int idSeller;
	private String street;
	private String postalCode;
	private String city;
	private int idCategory;

	// pour avoir directement le nom de la catégorie dans l'objet
	private String categoryName;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	// pour avoir directement le login du vendeur dans l'item
	private String sellerName;

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	// association bitaupe que je sais pas encore trop quoi en faire mais c'est zoli
	List<Bid> bidsList = new ArrayList<>();
	
	
	//--------- Constructeur
	
	public Item() {}

	public Item(String itemName, String description, LocalDateTime bidsStartDate, LocalDateTime bidsEndDate, int initialPrice,
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

	public Item(int idItem, String itemName, String description, LocalDateTime bidsStartDate, LocalDateTime bidsEndDate,
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

	public LocalDateTime getBidsStartDate() {
		return bidsStartDate;
	}

	public void setBidsStartDate(LocalDateTime bidsStartDate) {
		this.bidsStartDate = bidsStartDate;
	}

	public LocalDateTime getBidsEndDate() {
		return bidsEndDate;
	}

	public void setBidsEndDate(LocalDateTime bidsEndDate) {
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

	public void addBid(Bid bid) {
		bidsList.add(bid);
	}

	public List<Bid> getBidsList() {
		return bidsList;
	}

	//-------- Méthode toString
	
	@Override
	public String toString() {
		return "Id : " + idItem + ", Name : " + itemName + ", Description : " + description
				+ ", Start date : " + bidsStartDate + ", End date : " + bidsEndDate + ", Initial price : "
				+ initialPrice + ", Current price : " + currentPrice + ", Seller id : " + idSeller + ", Street : " + street
				+ ", Postal code : " + postalCode + ", City : " + city + ", Category : " + idCategory + ", " +categoryName + "\n"
				+ "Bids : " + bidsList + " Seller name : "+ sellerName +"\n";
	}
}
