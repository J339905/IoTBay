package uts.isd.model;
import java.io.Serializable;

public class User implements Serializable {
	private String email;
	private String name;
	private String phone;
	private String password;
	private String gender;
	private String favCol;
	private String tos;
 
	public User() {
	}
 
	public User(String _email, String _name, String _phone, String _password, String _gender, String _favCol, String _tos) {
		this.email = _email;
		this.name = _name;
		this.phone = _phone;
		this.password = _password;
		this.gender = _gender;
		this.favCol = _favCol;
		this.tos = _tos;
	}
 
	public void setEmail(String value) {
		this.email = value;
	}
 
	public void setfirstName(String value) {
		this.firstname = value;
	}
	public void setlastname(String value) {
		this.lastname = value;
	}
	

 
	public void setPhone(int value) {
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

	public void setTos(String value) {
		this.tos = value;
	}
 
	public String getEmail() {
		return this.email;
	}
 
	public String getfirstName() {
		return this.firstname;
	}
	public String getlastname() {
		return this.lastname;
	}
	

	public int getPhone() {
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

	public String getTos() {
		return this.tos;
	}
}