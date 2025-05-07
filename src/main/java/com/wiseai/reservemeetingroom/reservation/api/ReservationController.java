package com.wiseai.reservemeetingroom.reservation.api;

import com.wiseai.reservemeetingroom.core.domain.UrlPath.Reservation;
import com.wiseai.reservemeetingroom.reservation.api.response.ReservationResponse;
import com.wiseai.reservemeetingroom.reservation.api.swagger.ReservationSwagger;
import com.wiseai.reservemeetingroom.reservation.app.ReservationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 이 클래스는 예약 관련 컨트롤러를 담당합니다.
 */
@RestController
@RequestMapping(Reservation.ROOT)
@RequiredArgsConstructor
public class ReservationController implements ReservationSwagger {

	private final ReservationFacade reservationFacade;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{reservationId}")
	@Override
	public ReservationResponse searchReservation(
		@PathVariable("reservationId") Long reservationId
	) {
		return ReservationResponse.from(reservationFacade.searchReservation(reservationId));
	}

}
