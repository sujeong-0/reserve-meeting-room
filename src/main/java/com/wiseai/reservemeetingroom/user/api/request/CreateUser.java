package com.wiseai.reservemeetingroom.user.api.request;

import com.wiseai.reservemeetingroom.user.app.dto.UserDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 이 클래스는 유저의 생성 데이터를 담당합니다.
 */
@Getter
@NoArgsConstructor
public class CreateUser {

	/**
	 * 이름
	 */
	@NotBlank(message = "이름은 반드시 입력해야 합니다.")
	@Size(max = 30, message = "이름은 최대 30자까지 입력할 수 있습니다.")
	private String name;

	/**
	 * 이메일
	 */
	@NotBlank(message = "이메일은 반드시 입력해야 합니다.")
	@Email(message = "올바른 이메일 형식이어야 합니다.")
	private String email;

	public UserDto toDto() {
		return UserDto.builder()
			.name(name)
			.email(email)
			.build();
	}
}