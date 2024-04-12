package uts.isd.model;
import java.io.Serializable;

public class User implements Serializable {
	protected String email;
	protected String name;
	protected String phone;
	protected String password;
	protected String gender;
	protected String favCol;
 
	public User() {
	}
 
	public User(String _email, String _name, String _phone, String _password, String _gender, String _favCol) {
		this.email = _email;
		this.name = _name;
		this.phone = _phone;
		this.password = _password;
		this.gender = _gender;
		this.favCol = _favCol;
	}
 
	public void setEmail(String value) {
		this.email = value;
	}
 
	public void setName(String value) {
		this.name = value;
	}
 
	public void setPhone(String value) {
		this.phone = value;
	}
 
	public void setPassword(String value) {
		this.password = value;
	}
 
	public void setGender(String value) {
		this.gender = value;
	}
 
	public void setFavCol(String value) {
		this.favCol = value;
	}
 
	public String getEmail() {
		return this.email;
	}
 
	public String getName() {
		return this.name;
	}
 
	public String getPhone() {
		return this.phone;
	}
 
	public String getPassword() {
		return this.password;
	}
 
	public String getGender() {
		return this.gender;
	}
 
	public String getFavCol() {
		return this.favCol;
	}
	public boolean checkPassword(String username) {
	return this.password.equals(password);
	}
}