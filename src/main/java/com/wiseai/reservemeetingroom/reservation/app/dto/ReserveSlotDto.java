package com.wiseai.reservemeetingroom.reservation.app.dto;

import com.wiseai.reservemeetingroom.meeting_room.app.dto.MeetingRoomDto;
import com.wiseai.reservemeetingroom.reservation.domain.ReserveSlot;
import java.time.Instant;
import lombok.Builder;
import lombok.Getter;

/**
 * 이 클래스는 예약 슬롯을 담당합니다.
 */
@Builder
@Getter
public class ReserveSlotDto {

	/**
	 * 슬롯 아이디
	 */
	private Long slotId;

	/**
	 * 회의실
	 */
	private MeetingRoomDto meetingRoomDto;

	/**
	 * 예약시간(시작)
	 */
	private Instant startTime;

	/**
	 * 예약시간(종료)
	 */
	private Instant endTime;

	/**
	 * 예약 가능여부
	 */
	private Boolean isReserved;

	public static ReserveSlotDto from(ReserveSlot slot) {
		return ReserveSlotDto.builder()
			.slotId(slot.getId())
			.meetingRoomDto(MeetingRoomDto.from(slot.getMeetingRoom()))
			.isReserved(slot.isReserved())
			.startTime(slot.getStartTime())
			.endTime(slot.getEndTime())
			.build();
	}
}
