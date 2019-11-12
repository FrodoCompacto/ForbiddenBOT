package io.forbiddenbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.forbiddenbot.domain.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

	
}
