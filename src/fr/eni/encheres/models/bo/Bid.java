package fr.eni.encheres.models.bo;

import java.time.LocalDateTime;

public class Bid {

	private int idBid;
	private int idBuyer;
	private int idItem;
	private LocalDateTime bidDate;
	private int bidAmount;
	
	
	//--------- Constructeur
	
	public Bid() {}

	public Bid(int idBuyer, int idItem, LocalDateTime bidDate, int bidAmount) {
		
		this.idBuyer = idBuyer;
		this.idItem = idItem;
		this.bidDate = bidDate;
		this.bidAmount = bidAmount;
	}
	
		
	public Bid(int idBid, int idBuyer, int idItem, LocalDateTime bidDate, int bidAmount) {
		
		this.idBid = idBid;
		this.idBuyer = idBuyer;
		this.idItem = idItem;
		this.bidDate = bidDate;
		this.bidAmount = bidAmount;
	}

	//---------- Getters/Setters
	
	public int getIdBid() {
		return idBid;
	}

	public void setIdBid(int idBid) {
		this.idBid = idBid;
	}
	
	public int getIdBuyer() {
		return idBuyer;
	}

	public void setIdBuyer(int idBuyer) {
		this.idBuyer = idBuyer;
	}

	public int getIdItem() {
		return idItem;
	}

	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}

	public LocalDateTime getBidDate() {
		return bidDate;
	}

	public void setBidDate(LocalDateTime bidDate) {
		this.bidDate = bidDate;
	}

	public int getBidAmount() {
		return bidAmount;
	}

	public void setBidAmount(int bidAmount) {
		this.bidAmount = bidAmount;
	}

	
	//-------- Méthode toString
	
	@Override
	public String toString() {
		return "Id : "+idBid + ", Buyer : " + idBuyer + ", Item : " + idItem + ", Date : " + bidDate + ", Amount : "
				+ bidAmount + "\n";
	}


	
	
	
	
	
	
	
}
