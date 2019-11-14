package io.forbiddenbot.dto;

import java.io.Serializable;


import io.forbiddenbot.domain.ExodiaPart;
import io.forbiddenbot.domain.enums.PartType;



public class ExodiaPartNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private String image;
	private String uploader;
	private Integer type;
	private Boolean isLeftOriented;
	
//	private VerifierDTO verifier;
	
	
	public ExodiaPartNewDTO() {
	}
	
	public ExodiaPartNewDTO(ExodiaPart ex) {
		this.image = ex.getImage();
		this.uploader = ex.getUploader();
		this.type = ex.getType().getCod();
		this.isLeftOriented = ex.getIsLeftOriented();	
	}
	


	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	public PartType getType() {
		return PartType.toEnum(type);
	}

	public void setType(PartType type) {
		this.type = type.getCod();
	}

	public Boolean getIsLeftOriented() {
		return isLeftOriented;
	}

	public void setIsLeftOriented(Boolean isLeftOriented) {
		this.isLeftOriented = isLeftOriented;
	}





	
	
	

}
