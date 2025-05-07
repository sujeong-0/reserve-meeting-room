package com.wiseai.reservemeetingroom.reservation.domain;

import com.wiseai.reservemeetingroom.core.domain.BaseTimeEntity;
import com.wiseai.reservemeetingroom.meeting_room.domain.MeetingRoom;
import com.wiseai.reservemeetingroom.reservation.app.dto.ReserveSlotDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 이 클래스는 예약 슬롯 엔티티를 담당합니다.
 */

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "reserve_slots")
public class ReserveSlot extends BaseTimeEntity {
	/**
	 * 슬롯 ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 회의실
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "meeting_room_id", nullable = false)
	private MeetingRoom meetingRoom;

	/**
	 * 슬롯 시작 시간
	 */
	@Column(name = "start_time", nullable = false)
	private Instant startTime;

	/**
	 * 슬롯 종료 시간
	 */
	@Column(name = "end_time", nullable = false)
	private Instant endTime;

	/**
	 * 예약 여부
	 */
	@Column(name = "is_reserved", nullable = false)
	private boolean isReserved;

	public static ReserveSlot of(ReserveSlotDto dto) {
		return new ReserveSlot(null, dto.getMeetingRoomDto().toEntity(), dto.getStartTime(), dto.getEndTime(), false);

	}
}
