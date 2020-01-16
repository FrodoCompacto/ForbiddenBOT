package io.forbiddenbot.dto;

import java.io.Serializable;
import java.util.ArrayList;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import io.forbiddenbot.domain.Admin;


public class AdminNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Length(min=0, max=99, message="Max characters: 99")
	private String facebookUrl;
	@NotNull(message = "Login cannot be null")
	private String login;
	@NotNull(message = "Password cannot be null")
	private String password;
	
	
	public AdminNewDTO() {
	}


	public String getFacebookUrl() {
		return facebookUrl;
	}


	public void setFacebookUrl(String facebookUrl) {
		this.facebookUrl = facebookUrl;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public Admin toAdmin() {
		Admin adm = new Admin();
		adm.setId(null);
		adm.setFacebookUrl(this.facebookUrl);
		adm.setLogin(this.login);
		adm.setPassword(this.password);
		adm.setVerifiedParts(new ArrayList<>());
		return adm;
	}
	
}
