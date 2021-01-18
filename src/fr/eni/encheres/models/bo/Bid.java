package fr.eni.encheres.models.bo;

import java.time.LocalDate;

public class Bid {

	private int buyer_id;
	private int item_id;
	private LocalDate bid_date;
	private int bid_amount;
	
	
	//--------- Constructeur
	
	public Bid() {}

	public Bid(int buyer_id, int item_id, LocalDate bid_date, int bid_amount) {
		super();
		this.buyer_id = buyer_id;
		this.item_id = item_id;
		this.bid_date = bid_date;
		this.bid_amount = bid_amount;
	}

	
	//---------- Getters/Setters
	
	public int getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(int buyer_id) {
		this.buyer_id = buyer_id;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public LocalDate getBid_date() {
		return bid_date;
	}

	public void setBid_date(LocalDate bid_date) {
		this.bid_date = bid_date;
	}

	public int getBid_amount() {
		return bid_amount;
	}

	public void setBid_amount(int bid_amount) {
		this.bid_amount = bid_amount;
	}

	
	//-------- Méthode toString
	
	@Override
	public String toString() {
		return "Buyer_id : " + buyer_id + ", item_id : " + item_id + ", bid_date : " + bid_date + ", bid_amount : "
				+ bid_amount + "/n";
	}
	
	
	
	
	
	
	
}
