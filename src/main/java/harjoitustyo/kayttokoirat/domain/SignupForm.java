package harjoitustyo.kayttokoirat.domain;

import jakarta.validation.constraints.*;

public class SignupForm {
    @NotEmpty(message = "Käyttäjänimi ei voi olla tyhjä.")
    @Size(min = 4, max = 20, message = "Kirjaimia tulee olla 4-20.")
    private String username = "";

    @NotEmpty(message = "Salasana ei voi olla tyhjä.")
    @Size(min = 4, max = 30, message = "Kirjaimia tulee olla 4-30.")
    private String password = "";

    @NotEmpty(message = "Salasana ei voi olla tyhjä.")
    @Size(min = 4, max = 30, message = "Kirjaimia tulee olla 4-30.")
    private String passwordCheck = "";

    @NotEmpty
    private String role = "USER";

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
    

}
