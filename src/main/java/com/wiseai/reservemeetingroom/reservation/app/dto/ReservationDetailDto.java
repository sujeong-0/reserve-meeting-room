package com.wiseai.reservemeetingroom.reservation.app.dto;

import com.wiseai.reservemeetingroom.meeting_room.app.dto.MeetingRoomDto;
import com.wiseai.reservemeetingroom.reservation.domain.Reservation;
import com.wiseai.reservemeetingroom.reservation.domain.ReserveTime;
import com.wiseai.reservemeetingroom.reservation.exception.SlotNotFoundForReservationException;
import com.wiseai.reservemeetingroom.user.app.dto.UserDto;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * 이 클래스는 예약 관련 DTO를 담당합니다.
 */
@Builder
@Getter
@AllArgsConstructor
public class ReservationDetailDto {

	/**
	 * 예약 아이디
	 */
	private Long reservationId;

	/**
	 * 예약한 유저
	 */
	private UserDto user;

	/**
	 * 예약된 회의실
	 */
	private MeetingRoomDto meetingRoom;

	/**
	 * 예약시간(시작)
	 */
	private Instant startTime;

	/**
	 * 예약시간(종료)
	 */
	private Instant endTime;

	/**
	 * 생성일시
	 */
	private LocalDateTime createdAt;

	/**
	 * 수정일시
	 */
	private LocalDateTime updatedAt;


	public static ReservationDetailDto from(Reservation reservation, List<ReserveTime> times) {
		if (times.isEmpty()) {
			throw new SlotNotFoundForReservationException(reservation.getId());
		}

		LocalDateTime createdAt = LocalDateTime.ofInstant(reservation.getCreatedAt(), ZoneId.of("Asia/Seoul"));
		LocalDateTime updatedAt = LocalDateTime.ofInstant(reservation.getUpdatedAt(), ZoneId.of("Asia/Seoul"));

		return ReservationDetailDto.builder()
			.reservationId(reservation.getId())
			.user(UserDto.from(reservation.getUser()))
			.meetingRoom(MeetingRoomDto.from(times.get(0).getSlot().getMeetingRoom()))
			.startTime(times.get(0).getSlot().getStartTime())
			.endTime(times.get(times.size() - 1).getSlot().getEndTime())
			.createdAt(createdAt)
			.updatedAt(updatedAt)
			.build();
	}
}
