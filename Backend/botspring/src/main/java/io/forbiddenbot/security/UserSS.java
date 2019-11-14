package io.forbiddenbot.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class UserSS implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	private String login;
	private String password;
	
	private final Set<GrantedAuthority> authorities = new HashSet<>();
	
	public UserSS() {
		super();
	}

	public UserSS(Integer id, String login, String password) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;;
		authorities.add(new SimpleGrantedAuthority("USER"));
	}
	

	public Integer getId() {
		return id;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	
	
}
