package io.forbiddenbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.forbiddenbot.domain.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

	
	@Transactional(readOnly=true)
	Admin findByLogin(String login);
}
