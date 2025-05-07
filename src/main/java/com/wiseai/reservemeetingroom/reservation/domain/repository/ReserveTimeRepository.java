package com.wiseai.reservemeetingroom.reservation.domain.repository;

import com.wiseai.reservemeetingroom.reservation.domain.ReserveTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 이 클래스는 예약 시간 저장소를 담당합니다.
 */
public interface ReserveTimeRepository extends JpaRepository<ReserveTime, Long> {

	List<ReserveTime> findAllByReservationId(Long reservationId);

	void deleteAllByReservationId(Long reservationId);
}
