package com.wiseai.reservemeetingroom.user.app;

import com.wiseai.reservemeetingroom.user.app.dto.UserDto;
import com.wiseai.reservemeetingroom.user.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 이 클래스는 유저 서비스를 담당합니다.
 */
@Service
@RequiredArgsConstructor
public class UserFacade {

	private final UserService service;

	public UserDto searchUser(String email) {
		return UserDto.from(service.findExistingUser(email));
	}
}
