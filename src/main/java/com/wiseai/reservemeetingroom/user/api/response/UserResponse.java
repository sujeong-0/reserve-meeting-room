package com.wiseai.reservemeetingroom.user.api.response;

import com.wiseai.reservemeetingroom.user.app.dto.UserDto;
import java.time.LocalDateTime;

/**
 * 이 클래스는 유저 조회의 응답을 담당합니다.
 */
public record UserResponse(
	Long id,
	String name,
	String email,
	LocalDateTime createdAt,
	LocalDateTime updatedAt
) {

	public static UserResponse from(UserDto user) {
		return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt(), user.getUpdatedAt());
	}
}
