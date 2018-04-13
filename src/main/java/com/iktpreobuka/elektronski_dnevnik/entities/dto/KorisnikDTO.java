package com.iktpreobuka.elektronski_dnevnik.entities.dto;

public class KorisnikDTO {
	//@ValidEmail
   // @NotNull
    private String email;	
	//@ValidPassword
    private String password;
    //username?
    private String role;
 
	public KorisnikDTO() {
	}

	public KorisnikDTO(String email, String password, String role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	

   /* @NotNull
    @Size(min = 1)
    private String matchingPassword;*/
    
    
}
