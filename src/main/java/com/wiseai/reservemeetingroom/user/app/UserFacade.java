package com.wiseai.reservemeetingroom.user.app;

import com.wiseai.reservemeetingroom.user.app.dto.UserDto;
import com.wiseai.reservemeetingroom.user.domain.User;
import com.wiseai.reservemeetingroom.user.domain.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 이 클래스는 유저 서비스를 담당합니다.
 */
@Service
@RequiredArgsConstructor
public class UserFacade {

	private final UserService service;

	public UserDto searchUser(Long id) {
		return UserDto.from(service.findExistingUser(id));
	}

	public List<UserDto> searchUsers(String keyword) {
		return service.findUsers(keyword).stream().map(UserDto::from).toList();
	}

	public UserDto createUser(UserDto userData) {
		User user = service.createUser(userData.toEntity());
		return UserDto.from(user);
	}

	public UserDto updateUser(Long userId, UserDto userData) {
		User user = service.updateUser(userId, userData.toEntity());
		return UserDto.from(user);
	}

	public UserDto deleteUser(Long userId) {
		User user = service.deleteUser(userId);
		return UserDto.from(user);
	}
}
