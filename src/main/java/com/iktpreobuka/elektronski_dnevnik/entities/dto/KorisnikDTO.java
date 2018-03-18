package com.iktpreobuka.elektronski_dnevnik.entities.dto;

public class KorisnikDTO {
	//@ValidEmail
   // @NotNull
    private String email;
	
	//@ValidPassword
    private String password;

    
    
	public KorisnikDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KorisnikDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
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

   /* @NotNull
    @Size(min = 1)
    private String matchingPassword;*/
    
    
}
