package com.wiseai.reservemeetingroom.reservation.domain;

import com.wiseai.reservemeetingroom.reservation.app.dto.ReserveSlotDto;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 이 클래스는 예약과 예약시간의 매핑 엔티티를 담당합니다.
 */
@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reservation_times")
public class ReserveTime {
	/** 예약 슬롯 매핑 id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** 예약 */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "reserve_id", nullable = false)
	private Reservation reservation;

	/** 예약 슬롯 */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "slot_id", nullable = false)
	private ReserveSlot slot;

	public static ReserveTime of(Reservation reservation, ReserveSlot slot) {
		return new ReserveTime(null, reservation, slot);
	}
}
