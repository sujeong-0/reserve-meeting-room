package com.wiseai.reservemeetingroom.meeting_room.api.response;

import com.wiseai.reservemeetingroom.meeting_room.app.dto.MeetingRoomDto;
import java.time.LocalDateTime;

/**
 * 이 클래스는 회의실 조회의 응답을 담당합니다.
 */
public record MeetingRoomResponse(
	Long id,
	String name,
	String location,
	LocalDateTime createdAt,
	LocalDateTime updatedAt
) {

	public static MeetingRoomResponse from(MeetingRoomDto meetingRoom) {
		return new MeetingRoomResponse(meetingRoom.getId(), meetingRoom.getName(), meetingRoom.getLocation(), meetingRoom.getCreatedAt(),
			meetingRoom.getUpdatedAt());
	}
}
