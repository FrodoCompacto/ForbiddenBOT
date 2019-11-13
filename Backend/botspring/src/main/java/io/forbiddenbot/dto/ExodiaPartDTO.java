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
	private String uploaderIp;
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date uploadDate;
	private Integer type;
	private Boolean isLeftOriented;
	private Boolean isVerified;
	
	private VerifierDTO verifier;
	
	
	public ExodiaPartDTO() {
	}
	
	public ExodiaPartDTO(ExodiaPart ex) {
		this.id = ex.getId();
		this.image = ex.getImage();
		this.uploader = ex.getUploader();
		this.uploaderIp = ex.getUploaderIp();
		this.uploadDate = ex.getUploadDate();
		this.type = ex.getType().getCod();
		this.isLeftOriented = ex.getIsLeftOriented();
		this.isVerified = ex.getIsVerified();
		
		this.verifier = new VerifierDTO(ex.getVerifier());
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

	public String getUploaderIp() {
		return uploaderIp;
	}

	public void setUploaderIp(String uploaderIp) {
		this.uploaderIp = uploaderIp;
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

	public Boolean getIsLeftOriented() {
		return isLeftOriented;
	}

	public void setIsLeftOriented(Boolean isLeftOriented) {
		this.isLeftOriented = isLeftOriented;
	}

	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

	public VerifierDTO getVerifier() {
		return verifier;
	}

	public void setVerifier(VerifierDTO verifier) {
		this.verifier = verifier;
	}


	
	
	

}