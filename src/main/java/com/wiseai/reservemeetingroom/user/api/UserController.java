package com.wiseai.reservemeetingroom.user.api;

import com.wiseai.reservemeetingroom.core.domain.UrlPath.User;
import com.wiseai.reservemeetingroom.user.api.request.CreateUser;
import com.wiseai.reservemeetingroom.user.api.response.UserResponse;
import com.wiseai.reservemeetingroom.user.api.swagger.UserSwagger;
import com.wiseai.reservemeetingroom.user.app.UserFacade;
import com.wiseai.reservemeetingroom.user.app.dto.UserDto;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 이 클래스는 유저 관련 컨트롤러를 담당합니다.
 */
@RestController
@RequestMapping(User.ROOT)
@RequiredArgsConstructor
public class UserController implements UserSwagger {

	private final UserFacade userFacade;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{userId}")
	@Override
	public UserResponse searchUser(
		@PathVariable("userId") Long userId
	) {
		return UserResponse.from(userFacade.searchUser(userId));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	@Override
	public List<UserResponse> searchUsers(
		@RequestParam(value = "keyword", defaultValue = "") String keyword
	) {
		return userFacade.searchUsers(keyword).stream().map(UserResponse::from).toList();
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping
	@Override
	public UserResponse createUser(
		@RequestBody @Valid CreateUser user
	) {
		UserDto saveUser = userFacade.createUser(user.toDto());
		return UserResponse.from(saveUser);
	}


}