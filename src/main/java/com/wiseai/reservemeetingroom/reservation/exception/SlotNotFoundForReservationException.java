package com.wiseai.reservemeetingroom.reservation.exception;

import static com.wiseai.reservemeetingroom.core.exception.ErrorCode.INVALID_RESERVATION_SLOT;

import com.wiseai.reservemeetingroom.core.exception.BadRequestException;

public class SlotNotFoundForReservationException extends BadRequestException {

	public SlotNotFoundForReservationException(Long reservationId) {
		super(INVALID_RESERVATION_SLOT.getMessage().formatted(reservationId));
	}

	@Override
	public String getErrorCode() {
		return INVALID_RESERVATION_SLOT.name();
	}
}
