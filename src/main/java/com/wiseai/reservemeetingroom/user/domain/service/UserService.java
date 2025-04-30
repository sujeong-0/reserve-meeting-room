package com.wiseai.reservemeetingroom.user.domain.service;

import com.wiseai.reservemeetingroom.user.domain.User;
import com.wiseai.reservemeetingroom.user.domain.repository.UserRepository;
import com.wiseai.reservemeetingroom.user.exception.NotFoundUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 이 클래스는 유저 서비스를 담당합니다.
 */
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public User findExistingUser(Long userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> new NotFoundUserException(userId));
	}
	public User findExistingUser(String email) {
		return userRepository.findByEmail(email)
			.orElseThrow(() -> new NotFoundUserException(email));
	}
}
