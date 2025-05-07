package com.wiseai.reservemeetingroom.reservation.exception;

import static com.wiseai.reservemeetingroom.core.exception.ErrorCode.NOT_FOUND_RESERVATION;
import static com.wiseai.reservemeetingroom.core.exception.ErrorCode.NOT_FOUND_RESERVATION_SLOT;

import com.wiseai.reservemeetingroom.core.exception.NotFoundException;
import java.util.List;

public class NotFoundReservationSlotException extends NotFoundException {

	public NotFoundReservationSlotException(Long reservationSlotId) {
		super(NOT_FOUND_RESERVATION_SLOT.getMessage().formatted(reservationSlotId));
	}
	public NotFoundReservationSlotException(List<Long> reservationSlotIds) {
		super(NOT_FOUND_RESERVATION_SLOT.getMessage().formatted(reservationSlotIds.toString()));
	}

	@Override
	public String getErrorCode() {
		return NOT_FOUND_RESERVATION_SLOT.name();
	}
}
