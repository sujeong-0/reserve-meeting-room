package com.wiseai.reservemeetingroom.user.api.request;

import com.wiseai.reservemeetingroom.user.app.dto.UserDto;
import com.wiseai.reservemeetingroom.user.app.dto.UserDto.UserDtoBuilder;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Optional;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 이 클래스는 유저의 수정 데이터를 담당합니다.
 */
@Getter
@NoArgsConstructor
public class UpdateUser {

	/**
	 * 이름
	 */
	@Pattern(regexp = "^(?!\\s*$).+", message = "공백은 허용되지 않습니다. 수정을 원치 않으시면 값을 아예 비워주세요.")
	@Size(max = 30, message = "이름은 최대 30자까지 입력할 수 있습니다.")
	private String name;

	/**
	 * 이메일
	 */
	@Pattern(regexp = "^(?!\\s*$).+", message = "공백은 허용되지 않습니다. 수정을 원치 않으시면 값을 아예 비워주세요.")
	@Email(message = "올바른 이메일 형식이어야 합니다.")
	private String email;

	public UserDto toDto() {
		UserDtoBuilder builder = UserDto.builder();
		if (null != name) {
			builder.name(name);
		}
		if (null != email) {
			builder.email(email);
		}
		return builder.build();
	}
}