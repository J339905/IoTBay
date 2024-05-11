package uts.isd.model;
import java.io.Serializable;

public class User implements Serializable {
	protected int userId;
	protected String email;
	protected String firstname;
	protected String lastname;
	protected int phone;
	protected String password;
	protected String gender;
	protected String favCol;
	protected String role;
	protected String status;
 
	public User() {
	}    

 
	public User(int userId, String firstname, String lastname, String email,  int phone, String password, String gender, String role) {
		this.userId = userId;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.password = password;
		this.gender = gender;
		this.role =role;

	}


	public void setUserID(int value){
		this.userId = value;
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

	public void setRole(String value) {
		this.role = value;
	}
	public void setStatus(String value) {
		this.status = value;
	}
	public int getUserID(){
		return this.userId;
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

	public String getRole(){
		return this.role;
	}
	public String getStatus(){
		return this.status;
	}
	public boolean checkPassword(String username) {
	return this.password.equals(password);
	}
} //register login and logout

