package com.wiseai.reservemeetingroom.reservation.exception;

import static com.wiseai.reservemeetingroom.core.exception.ErrorCode.OVERLAPPING_RESERVATION;

import com.wiseai.reservemeetingroom.core.exception.BadRequestException;
import com.wiseai.reservemeetingroom.reservation.app.dto.ReservationDetailDto;
import com.wiseai.reservemeetingroom.reservation.domain.Reservation;
import java.util.List;

public class OverlappingReservation extends BadRequestException {

	public OverlappingReservation(List<Long> slotIds) {
		super(OVERLAPPING_RESERVATION.getMessage().formatted(slotIds));
	}

	@Override
	public String getErrorCode() {
		return OVERLAPPING_RESERVATION.name();
	}
}
