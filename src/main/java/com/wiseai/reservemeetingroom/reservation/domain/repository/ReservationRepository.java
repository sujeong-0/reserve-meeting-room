package com.wiseai.reservemeetingroom.reservation.domain.repository;

import com.wiseai.reservemeetingroom.reservation.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 이 클래스는 예약 저장소를 담당합니다.
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long>, ReservationQueryRepository {

}
