package io.forbiddenbot.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.forbiddenbot.domain.ExodiaPart;

@Repository
public interface ExodiaPartRepository extends JpaRepository<ExodiaPart, Integer> {

	@Query("SELECT obj FROM ExodiaPart obj WHERE obj.isVerified = FALSE")
	Page<ExodiaPart> findUnverified(Pageable pageRequest);
	
	@Query("SELECT obj FROM ExodiaPart obj WHERE obj.isVerified = TRUE")
	Page<ExodiaPart> findVerified(Pageable pageRequest);
	
	@Query("SELECT obj FROM ExodiaPart obj WHERE obj.isVerified = FALSE and obj.type = 1")
	Page<ExodiaPart> findUnverifiedArms(Pageable pageRequest);
	
	@Query("SELECT obj FROM ExodiaPart obj WHERE obj.isVerified = FALSE and obj.type = 2")
	Page<ExodiaPart> findUnverifiedLegs(Pageable pageRequest);
	
	@Query("SELECT obj FROM ExodiaPart obj WHERE obj.isVerified = FALSE and obj.type = 3")
	Page<ExodiaPart> findUnverifiedHeads(Pageable pageRequest);
	
}
