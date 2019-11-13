package io.forbiddenbot.dto;

import java.io.Serializable;

import io.forbiddenbot.domain.Admin;


public class VerifierDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private Integer id;
	private String facebookUrl;
	

	public VerifierDTO() {
	}
	
	public VerifierDTO(Admin adm) {
		this.id = adm.getId();
		this.facebookUrl = adm.getFacebookUrl();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFacebookUrl() {
		return facebookUrl;
	}

	public void setFacebookUrl(String facebookUrl) {
		this.facebookUrl = facebookUrl;
	}


	
	

}
