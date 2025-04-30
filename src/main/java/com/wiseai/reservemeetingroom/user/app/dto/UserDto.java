package com.wiseai.reservemeetingroom.user.app.dto;

import com.wiseai.reservemeetingroom.user.domain.User;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.Builder;
import lombok.Getter;

/**
 * 이 클래스는 []를 담당합니다.
 */
@Builder
@Getter
public class UserDto {

	/**
	 * 아이디
	 */
	private Long id;


	/**
	 * 이름
	 */
	private String name;

	/**
	 * 이메일
	 */
	private String email;

	/**
	 * 생성 일시
	 */
	private LocalDateTime createdAt;

	/**
	 * 마지막 수정일시
	 */
	private LocalDateTime updatedAt;

	public static UserDto from(User user) {
		LocalDateTime createdAt = LocalDateTime.ofInstant(user.getCreatedAt(), ZoneId.of("Asia/Seoul"));
		LocalDateTime updatedAt = LocalDateTime.ofInstant(user.getUpdatedAt(), ZoneId.of("Asia/Seoul"));

		return new UserDtoBuilder()
			.id(user.getId())
			.name(user.getName())
			.email(user.getEmail())
			.createdAt(createdAt)
			.updatedAt(updatedAt)
			.build();

	}

	public User toEntity() {
		return User.builder()
			.email(this.email)
			.name(this.name)
			.build();
	}
}
