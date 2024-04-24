package com.sns.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sns.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	public UserEntity findByLoginId(String loginId);

	public UserEntity findByLoginIdAndPassword(
			@Param("loginId") String loginId,
			@Param("password") String password);

	@Query(value = "SELECT loginId FROM user WHERE id = :id")
	public String findLoginIdById(
			@Param("id") int id);
}
