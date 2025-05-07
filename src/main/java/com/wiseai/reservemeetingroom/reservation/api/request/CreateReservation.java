package com.wiseai.reservemeetingroom.reservation.api.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 이 클래스는 예약 생성 요청 데이터를 담당합니다.
 */
@Getter
@NoArgsConstructor
public class CreateReservation {

	/**
	 * 예약자 유저 ID
	 */
	@NotNull(message = "유저 ID는 반드시 입력해야 합니다.")
	private Long userId;

	/**
	 * 예약할 슬롯 ID 목록
	 */
	@NotEmpty(message = "예약할 슬롯 ID 목록은 비어있을 수 없습니다.")
	private List<Long> slotIds;
}