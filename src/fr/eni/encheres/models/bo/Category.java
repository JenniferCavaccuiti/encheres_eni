package fr.eni.encheres.models.bo;

public class Category {
	
	private int idCategory;
	private String wording;
	
	
	//--------- Constructeur
	
	public Category () {}
	
	public Category(String wording) {
		
		this.wording = wording;
	}
	
	public Category(int idCategory, String wording) {
		
		this.idCategory = idCategory;
		this.wording = wording;
	}

	//---------- Getters/Setters
	

	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
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
		return "Category : " + idCategory + ", Wording : " + wording +"/n";
	}

	

	
	
	
	
	

}
