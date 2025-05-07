package com.wiseai.reservemeetingroom.reservation.domain.service;

import com.wiseai.reservemeetingroom.reservation.app.dto.ReservationDetailDto;
import com.wiseai.reservemeetingroom.reservation.app.dto.ReserveSlotDto;
import com.wiseai.reservemeetingroom.reservation.domain.Reservation;
import com.wiseai.reservemeetingroom.reservation.domain.ReserveSlot;
import com.wiseai.reservemeetingroom.reservation.domain.ReserveTime;
import com.wiseai.reservemeetingroom.reservation.domain.repository.ReservationRepository;
import com.wiseai.reservemeetingroom.reservation.domain.repository.ReserveSlotRepository;
import com.wiseai.reservemeetingroom.reservation.domain.repository.ReserveTimeRepository;
import com.wiseai.reservemeetingroom.reservation.exception.NotFoundReservationException;
import com.wiseai.reservemeetingroom.reservation.exception.NotFoundReservationSlotException;
import com.wiseai.reservemeetingroom.reservation.exception.OverlappingReservation;
import com.wiseai.reservemeetingroom.user.domain.User;
import com.wiseai.reservemeetingroom.user.domain.service.UserService;
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

	private final UserService userService;

	private final ReservationRepository reservationRepository;
	private final ReserveTimeRepository timeRepository;
	private final ReserveSlotRepository slotRepository;

	@Transactional(readOnly = true)
	public ReservationDetailDto findReservation(Long reservationId) {
		return reservationRepository.findDetailByReservationId(reservationId)
			.orElseThrow(() -> new NotFoundReservationException(reservationId));
	}


	@Transactional
	public ReservationDetailDto createReservation(Long userId, List<Long> slotIds) {
		User user = userService.findExistingUser(userId);

		List<ReserveSlot> slots = slotRepository.findAllById(slotIds);
		if (slots.size() != slotIds.size()) {
			throw new NotFoundReservationSlotException(slotIds);
		}

		List<ReserveSlot> alreadyReserved = slots.stream().filter(ReserveSlot::isReserved).toList();
		if (!alreadyReserved.isEmpty()) {
			throw new OverlappingReservation(alreadyReserved.stream().map(ReserveSlot::getId).toList());
		}

		Reservation reservation = Reservation.of(user);
		reservationRepository.save(reservation);

		List<ReserveTime> reserveTimes = slots.stream()
			.map(slot -> ReserveTime.of(reservation, slot))
			.toList();

		timeRepository.saveAll(reserveTimes);
		slotRepository.updateIsReservedTrueByIds(slotIds);

		return ReservationDetailDto.from(reservation, reserveTimes);
	}
}
