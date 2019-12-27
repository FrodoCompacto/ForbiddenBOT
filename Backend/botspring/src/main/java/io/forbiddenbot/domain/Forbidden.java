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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Forbidden implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date postDate;
	private String postURL;
	
	@ManyToMany
	@JoinTable(name="FORBIDDEN_PARTS",
			joinColumns = @JoinColumn(name="forbidden_id"),
			inverseJoinColumns = @JoinColumn(name="exodiapart")
			)
	private List<ExodiaPart> exodiaParts = new ArrayList<>();
	
	
	public Forbidden(){}

	public Forbidden(Integer id, Date postDate) {
		super();
		this.id = id;
		this.postDate = postDate;
	}
	
	public Forbidden(Integer id, Date postDate, List<ExodiaPart> list, String url) {
		super();
		this.id = id;
		this.postDate = postDate;
		this.exodiaParts = list;
		this.postURL = url;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public List<ExodiaPart> getExodiaParts() {
		return exodiaParts;
	}

	public void setExodiaParts(List<ExodiaPart> exodiaParts) {
		this.exodiaParts = exodiaParts;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
		Forbidden other = (Forbidden) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getPostURL() {
		return postURL;
	}

	public void setPostURL(String postURL) {
		this.postURL = postURL;
	}

	
}
