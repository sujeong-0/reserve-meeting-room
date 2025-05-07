package com.wiseai.reservemeetingroom.reservation.app;

import com.wiseai.reservemeetingroom.reservation.api.request.CreateReservation;
import com.wiseai.reservemeetingroom.reservation.app.dto.ReservationDetailDto;
import com.wiseai.reservemeetingroom.reservation.domain.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 이 클래스는 예약 서비스를 담당합니다.
 */
@Service
@RequiredArgsConstructor
public class ReservationFacade {

	private final ReservationService service;

	public ReservationDetailDto searchReservation(Long id) {
		return service.findReservation(id);
	}

	public ReservationDetailDto createReservation(CreateReservation createReservation) {
		return service.createReservation(createReservation.getUserId(), createReservation.getSlotIds());
	}


}
