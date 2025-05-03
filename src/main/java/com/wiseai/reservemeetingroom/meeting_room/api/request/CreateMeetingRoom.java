package com.wiseai.reservemeetingroom.meeting_room.api.request;

import com.wiseai.reservemeetingroom.meeting_room.app.dto.MeetingRoomDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 이 클래스는 회의실의 생성 데이터를 담당합니다.
 */
@Getter
@NoArgsConstructor
public class CreateMeetingRoom {

	/**
	 * 이름
	 */
	@NotBlank(message = "이름은 반드시 입력해야 합니다.")
	@Size(max = 30, message = "이름은 최대 30자까지 입력할 수 있습니다.")
	private String name;

	/**
	 * 위치
	 */
	@Pattern(regexp = "^(?!\\s*$).+", message = "공백은 허용되지 않습니다. 수정을 원치 않으시면 값을 아예 비워주세요.")
	@Size(max = 100, message = "위치은 최대 100자까지 입력할 수 있습니다.")
	private String location;

	public MeetingRoomDto toDto() {
		return MeetingRoomDto.builder()
			.name(name)
			.location(location)
			.build();
	}
}