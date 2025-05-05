package com.wiseai.reservemeetingroom.reservation.exception;

import static com.wiseai.reservemeetingroom.core.exception.ErrorCode.OVERLAPPING_RESERVATION;

import com.wiseai.reservemeetingroom.core.exception.BadRequestException;

public class OverlappingReservation extends BadRequestException {

	public OverlappingReservation(String email) {
		super(OVERLAPPING_RESERVATION.getMessage().formatted(email));
	}

	@Override
	public String getErrorCode() {
		return OVERLAPPING_RESERVATION.name();
	}
}
