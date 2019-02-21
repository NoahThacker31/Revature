package com.revature.dto;

public class LoginRequestDTO {
	private String email;
	private String password;

	@Override
	public String toString() {
		return "LoginRequestDTO [email=" + email + ", password=" + password + "]";
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

	public LoginRequestDTO() {
		super();
	}

	public LoginRequestDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
}