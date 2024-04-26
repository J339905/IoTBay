package uts.isd.model;

public class Staff extends User {
    private String role;

    public Staff(String _email, String _name, String _phone, String _password, String _gender, String _favCol, String role){
        super(_email, _name, _phone,_password, _gender, _favCol);
        this.role = role;
            
    }
    public String getRole(){
        return role;
    }
    public void setRole(String role){
        this.role = role;
    }
}
