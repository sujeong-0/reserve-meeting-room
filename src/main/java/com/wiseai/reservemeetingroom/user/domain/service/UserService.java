package com.wiseai.reservemeetingroom.user.domain.service;

import com.wiseai.reservemeetingroom.user.domain.User;
import com.wiseai.reservemeetingroom.user.domain.repository.UserRepository;
import com.wiseai.reservemeetingroom.user.exception.DuplicateEmailException;
import com.wiseai.reservemeetingroom.user.exception.NotFoundUserException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 이 클래스는 유저 서비스를 담당합니다.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

	private final UserRepository userRepository;


	@Transactional(readOnly = true)
	public User findExistingUser(Long userId) {
		return userRepository.findById(userId)
			.orElseThrow(() -> new NotFoundUserException(userId));
	}

	@Transactional(readOnly = true)
	public List<User> findUsers(String keyword) {
		return userRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword);
	}

	public User createUser(User user) {
		userRepository.findByEmail(user.getEmail())
			.ifPresent(existing -> {
				throw new DuplicateEmailException(user.getEmail());
			});

		return userRepository.save(user);
	}

	public User updateUser(Long userId, User user) {
		User findUser = userRepository.findById(userId)
			.orElseThrow(() -> new NotFoundUserException(userId));

		userRepository.findByEmail(user.getEmail())
			.ifPresent(existing -> {
				throw new DuplicateEmailException(user.getEmail());
			});

		findUser.updateFrom(user);
		return findUser;
	}
}
