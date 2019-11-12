package io.forbiddenbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.forbiddenbot.domain.Forbidden;

@Repository
public interface ForbiddenRepository extends JpaRepository<Forbidden, Integer> {

	
}
