package fr.eni.encheres.models.bo;

public class Category {
	
	private int category_id;
	private String wording;
	
	
	//--------- Constructeur
	
	public Category () {}
	
	public Category(String wording) {
		super();
		this.wording = wording;
	}
	
	public Category(int category_id, String wording) {
		super();
		this.category_id = category_id;
		this.wording = wording;
	}

	//---------- Getters/Setters
	
	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getWording() {
		return wording;
	}

	public void setWording(String wording) {
		this.wording = wording;
	}

		
	//-------- Méthode toString
	
	@Override
	public String toString() {
		return "Category_id : " + category_id + ", Wording : " + wording +"/n";
	}
	

	
	
	
	
	

}
