package io.forbiddenbot.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import io.forbiddenbot.domain.ExodiaPart;
import io.forbiddenbot.domain.enums.PartType;
import io.forbiddenbot.services.validation.ExodiaPartInsert;


@ExodiaPartInsert
public class ExodiaPartNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message="Required field.")
	private String image;
	@Length(min=0, max=15, message="Max characters: 15")
	private String uploader;
	private String uploaderIp;
	@Min(value = 0,message = "Type must be ARM, LEG or HEAD.")
	private Integer type;
	private Boolean isLeftOriented;
	
	
	
	public ExodiaPartNewDTO() {
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

	public String getUploaderIp() {
		return uploaderIp;
	}

	public void setUploaderIp(String uploaderIp) {
		this.uploaderIp = uploaderIp;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public ExodiaPart toExodiaPart() {
		ExodiaPart ex = new ExodiaPart();
		ex.setId(null);
		ex.setImage(this.image);
		ex.setUploader(this.uploader);
		ex.setUploaderIp(this.uploaderIp);
		ex.setUploadDate(new Date());
		ex.setType(this.getType());
		ex.setIsLeftOriented(this.isLeftOriented);
		ex.setIsVerified(false);
		ex.setVerifier(null);
		ex.setSourceFor(new ArrayList<>());
		return ex;
	}





	
	
	

}
