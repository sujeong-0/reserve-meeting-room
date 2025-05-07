package com.wiseai.reservemeetingroom.reservation.api.response;

import com.wiseai.reservemeetingroom.meeting_room.app.dto.MeetingRoomDto;
import com.wiseai.reservemeetingroom.reservation.app.dto.ReservationDetailDto;
import com.wiseai.reservemeetingroom.user.app.dto.UserDto;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 이 클래스는 예약 응답을 담당합니다.
 */
public record ReservationResponse(
	Long reservationId,
	UserDto user,
	MeetingRoomDto meetingRoom,
	LocalDateTime startTime,
	LocalDateTime endTime,
	LocalDateTime createdAt,
	LocalDateTime updatedAt
) {

	public static ReservationResponse from(ReservationDetailDto detail) {
		return new ReservationResponse(
			detail.getReservationId(),
			detail.getUser(),
			detail.getMeetingRoom(),
			detail.getStartTime().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime(),
			detail.getEndTime().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime(),
			detail.getCreatedAt(),
			detail.getUpdatedAt()
		);
	}
}