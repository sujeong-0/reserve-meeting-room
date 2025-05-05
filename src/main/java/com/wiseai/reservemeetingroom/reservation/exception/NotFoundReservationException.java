package com.wiseai.reservemeetingroom.reservation.exception;

import static com.wiseai.reservemeetingroom.core.exception.ErrorCode.NOT_FOUND_RESERVATION;

import com.wiseai.reservemeetingroom.core.exception.NotFoundException;

public class NotFoundReservationException extends NotFoundException {

	public NotFoundReservationException(Long reservationId) {
		super(NOT_FOUND_RESERVATION.getMessage().formatted(reservationId));
	}

	@Override
	public String getErrorCode() {
		return NOT_FOUND_RESERVATION.name();
	}
}
