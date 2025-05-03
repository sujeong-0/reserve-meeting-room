package com.wiseai.reservemeetingroom.meeting_room.app.dto;

import com.wiseai.reservemeetingroom.meeting_room.domain.MeetingRoom;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.Builder;
import lombok.Getter;

/**
 * 이 클래스는 회의실 DTO를 담당합니다.
 */
@Builder
@Getter
public class MeetingRoomDto {

	/**
	 * 아이디
	 */
	private Long id;


	/**
	 * 이름
	 */
	private String name;

	/**
	 * 위치
	 */
	private String location;

	/**
	 * 생성 일시
	 */
	private LocalDateTime createdAt;

	/**
	 * 마지막 수정일시
	 */
	private LocalDateTime updatedAt;

	public static MeetingRoomDto from(MeetingRoom user) {
		LocalDateTime createdAt = LocalDateTime.ofInstant(user.getCreatedAt(), ZoneId.of("Asia/Seoul"));
		LocalDateTime updatedAt = LocalDateTime.ofInstant(user.getUpdatedAt(), ZoneId.of("Asia/Seoul"));

		return new MeetingRoomDtoBuilder()
			.id(user.getId())
			.name(user.getName())
			.location(user.getLocation())
			.createdAt(createdAt)
			.updatedAt(updatedAt)
			.build();

	}

	public MeetingRoom toEntity() {
		return MeetingRoom.builder()
			.location(this.location)
			.name(this.name)
			.build();
	}
}
