package com.wiseai.reservemeetingroom.reservation.domain.repository;

import com.wiseai.reservemeetingroom.reservation.app.dto.ReservationDetailDto;
import java.util.Optional;

/**
 * 이 클래스는 예약 관련 query repository를 담당합니다.
 */
public interface ReservationQueryRepository {

	Optional<ReservationDetailDto> findDetailByReservationId(Long reservationId);
}
