package io.forbiddenbot.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.forbiddenbot.domain.ExodiaPart;
import io.forbiddenbot.domain.enums.PartType;



public class ExodiaPartDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private Integer id;
	private String image;
	private String uploader;
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date uploadDate;
	private Integer type;
	private Boolean isVerified;
	
//	private VerifierDTO verifier;
	
	
	public ExodiaPartDTO() {
	}
	
	public ExodiaPartDTO(ExodiaPart ex) {
		this.id = ex.getId();
		this.image = ex.getImage();
		this.uploader = ex.getUploader();
		this.uploadDate = ex.getUploadDate();
		this.type = ex.getType().getCod();
		this.setIsVerified(ex.getIsVerified());
		
//		this.verifier = (ex.getVerifier() == null) ? new VerifierDTO(new Admin()) : new VerifierDTO(ex.getVerifier());
	
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public PartType getType() {
		return PartType.toEnum(type);
	}

	public void setType(PartType type) {
		this.type = type.getCod();
	}

	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}




	
	
	

}
