package io.forbiddenbot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.forbiddenbot.domain.Admin;
import io.forbiddenbot.repositories.AdminRepository;
import io.forbiddenbot.security.UserSS;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private AdminRepository repo;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Admin adm = repo.findByLogin(login);
		if (adm == null) {
			throw new UsernameNotFoundException(login);
		}

		return new UserSS(adm.getId(), adm.getLogin(), adm.getPassword());
	}

}
