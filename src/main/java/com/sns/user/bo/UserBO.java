package com.sns.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.user.entity.UserEntity;
import com.sns.user.repository.UserRepository;

@Service
public class UserBO {
	@Autowired
	private UserRepository userRepository;

	public UserEntity getUserEntityByLoginId(String loginId) {
		return userRepository.findByLoginId(loginId);
	}

	public Integer addUser(String loginId, String password, String name, String email) {
		UserEntity user = userRepository.save(UserEntity.builder()
										.loginId(loginId)
										.password(password)
										.name(name)
										.email(email).build());
		if (user != null) {
			return user.getId();
		} else {
			return null;
		}
	}

	public UserEntity getUserEntityByLoginIdPassword(String loginId, String password) {
		return userRepository.findByLoginIdAndPassword(loginId, password);
	}
}
