package io.forbiddenbot.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import io.forbiddenbot.domain.ExodiaPart;
import io.forbiddenbot.domain.enums.PartType;
//import io.forbiddenbot.services.validation.ExodiaPartInsert;


//@ExodiaPartInsert
public class ExodiaPartNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Length(min=0, max=15, message="Max characters: 15")
	private String uploader;
	@Min(value = 0,message = "Type must be ARM, LEG or HEAD.")
	private Integer type;
	private Boolean isLeftOriented;
	@NotNull(message = "Image cannot be null")
	private String imageStr;
	
	
	
	
	public ExodiaPartNewDTO() {
	}

	public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Boolean getIsLeftOriented() {
		return isLeftOriented;
	}

	public void setIsLeftOriented(Boolean isLeftOriented) {
		this.isLeftOriented = isLeftOriented;
	}



	public ExodiaPart toExodiaPart() {
		ExodiaPart ex = new ExodiaPart();
		ex.setId(null);
		ex.setUploader(this.uploader);
		ex.setUploadDate(new Date());
		ex.setType(PartType.toEnum(this.getType()));
		ex.setIsVerified(false);
		ex.setVerifier(null);
		ex.setSourceFor(new ArrayList<>());
		return ex;
	}

	public String getImageStr() {
		return imageStr;
	}

	public void setImageStr(String imageStr) {
		this.imageStr = imageStr;
	}





	
	
	

}
