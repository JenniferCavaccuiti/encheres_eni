package fr.eni.encheres.models.bo;

public class User {

	private int user_id;
	private String login;
	private String lastname;
	private String firstname;
	private String email;
	private String phone_number;
	private String street;
	private int postal_code;
	private String city;
	private String password;
	private int credits;
	private boolean administrator;
	
	
	//--------- Constructeur
	
	public User() {}

	public User(String login, String lastname, String firstname, String email, String phone_number, String street,
			int postal_code, String city, String password, int credits, boolean administrator) {
		super();
		this.login = login;
		this.lastname = lastname;
		this.firstname = firstname;
		this.email = email;
		this.phone_number = phone_number;
		this.street = street;
		this.postal_code = postal_code;
		this.city = city;
		this.password = password;
		this.credits = credits;
		this.administrator = administrator;
	}

	public User(int user_id, String login, String lastname, String firstname, String email, String phone_number,
			String street, int postal_code, String city, String password, int credits, boolean administrator) {
		super();
		this.user_id = user_id;
		this.login = login;
		this.lastname = lastname;
		this.firstname = firstname;
		this.email = email;
		this.phone_number = phone_number;
		this.street = street;
		this.postal_code = postal_code;
		this.city = city;
		this.password = password;
		this.credits = credits;
		this.administrator = administrator;
	}

	
	//---------- Getters/Setters
	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
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

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
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
		return "User_id : " + user_id + ", login : " + login + ", lastname : " + lastname + ", firstname : " + firstname
				+ ", email : " + email + ", phone_number : " + phone_number + ", street : " + street + ", postal_code : "
				+ postal_code + ", city : " + city + ", password : " + password + ", credits : " + credits
				+ ", administrator : " + administrator + "/n";
	}
	
	
	
	
	
	
	
}
