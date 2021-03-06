package io.forbiddenbot.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.forbiddenbot.domain.enums.PartType;

@Entity
public class ExodiaPart implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String image;
	private String uploader;
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date uploadDate;
	private Integer type;
	private Boolean isVerified;
	
	@ManyToOne
	@JoinColumn(name="admin_verifier")
	private Admin verifier;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "exodiaParts")
	private List<Forbidden> sourceFor = new ArrayList<>();
	
	
	
	public ExodiaPart(){}

	public ExodiaPart(Integer id, String image, String uploader, Date uploadDate, PartType type, Boolean isVerified, Admin verifier) {
		super();
		this.id = id;
		this.image = image;
		this.uploader = uploader;
		this.uploadDate = uploadDate;
		this.type = type.getCod();
		this.isVerified = isVerified;
		this.verifier = verifier;
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

	public Boolean getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

	public Admin getVerifier() {
		return verifier;
	}

	public void setVerifier(Admin verifier) {
		this.verifier = verifier;
	}

	public PartType getType() {
		return PartType.toEnum(type);
	}

	public void setType(PartType type) {
		this.type = type.getCod();
	}

	public List<Forbidden> getSourceFor() {
		return sourceFor;
	}

	public void setSourceFor(List<Forbidden> sourceFor) {
		this.sourceFor = sourceFor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExodiaPart other = (ExodiaPart) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}
