package fr.eni.encheres.models.bo;

public class User {

	private int idUser;
	private String login;
	private String lastname;
	private String firstname;
	private String email;
	private String phoneNumber;
	private String street;
	private String postalCode;
	private String city;
	private String password;
	private int credits;
	private boolean administrator;
	
	
	//--------- Constructeur
	
	public User() {}

	public User(String login, String lastname, String firstname, String email, String phoneNumber, String street,
			String postalCode, String city, String password, int credits, boolean administrator) {

		this.login = login;
		this.lastname = lastname;
		this.firstname = firstname;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
		this.password = password;
		this.credits = credits;
		this.administrator = administrator;
	}

	public User(int idUser, String login, String lastname, String firstname, String email, String phoneNumber,
			String street, String postalCode, String city, String password, int credits, boolean administrator) {
		
		this.idUser = idUser;
		this.login = login;
		this.lastname = lastname;
		this.firstname = firstname;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
		this.password = password;
		this.credits = credits;
		this.administrator = administrator;
	}

	
	//---------- Getters/Setters
	
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public boolean isAdministrator() {
		return administrator;
	}

	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}
	
		
	//-------- Méthode toString
	
	@Override
	public String toString() {
		return "User : " + idUser + ", Login : " + login + ", Lastname : " + lastname + ", Firstname : " + firstname
				+ ", Email : " + email + ", Phone number : " + phoneNumber + ", Street : " + street + ", Postal code : "
				+ postalCode + ", City : " + city + ", Password : " + password + ", Credits : " + credits
				+ ", Administrator : " + administrator + "/n";
	}
	
}
