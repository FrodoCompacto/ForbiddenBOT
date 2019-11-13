package io.forbiddenbot.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.forbiddenbot.domain.Forbidden;


public class ForbiddenDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	

	private Integer id;
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date postDate;

	private List<ExodiaPartDTO> exodiaParts = new ArrayList<>();
	
	public ForbiddenDTO(){}

	public ForbiddenDTO(Forbidden fbdn) {
		this.id = fbdn.getId();
		this.postDate = fbdn.getPostDate();
		this.exodiaParts = fbdn.getExodiaParts().stream().map(obj -> new ExodiaPartDTO(obj)).collect(Collectors.toList());
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

	public List<ExodiaPartDTO> getExodiaParts() {
		return exodiaParts;
	}

	public void setExodiaParts(List<ExodiaPartDTO> exodiaParts) {
		this.exodiaParts = exodiaParts;
	}

	
	
}
