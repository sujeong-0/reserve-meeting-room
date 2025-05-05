package com.wiseai.reservemeetingroom.reservation.domain.service;

import com.wiseai.reservemeetingroom.reservation.app.dto.ReservationDetailDto;
import com.wiseai.reservemeetingroom.reservation.domain.repository.ReservationQueryRepository;
import com.wiseai.reservemeetingroom.reservation.exception.NotFoundReservationException;
import com.wiseai.reservemeetingroom.user.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 이 클래스는 예약 서비스를 담당합니다.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {

	private ReservationQueryRepository reservationQueryRepository;

	@Transactional(readOnly = true)
	public ReservationDetailDto findReservation(Long reservationId) {
		return reservationQueryRepository.findDetailByReservationId(reservationId)
			.orElseThrow(() -> new NotFoundReservationException(reservationId));
	}

}
