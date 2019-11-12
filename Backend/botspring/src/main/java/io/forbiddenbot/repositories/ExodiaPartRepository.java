package io.forbiddenbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.forbiddenbot.domain.ExodiaPart;

@Repository
public interface ExodiaPartRepository extends JpaRepository<ExodiaPart, Integer> {

	
}
