package com.wiseai.reservemeetingroom.reservation.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wiseai.reservemeetingroom.meeting_room.domain.QMeetingRoom;
import com.wiseai.reservemeetingroom.reservation.app.dto.ReservationDetailDto;
import com.wiseai.reservemeetingroom.reservation.domain.QReservation;
import com.wiseai.reservemeetingroom.reservation.domain.QReserveSlot;
import com.wiseai.reservemeetingroom.reservation.domain.QReserveTime;
import com.wiseai.reservemeetingroom.reservation.domain.Reservation;
import com.wiseai.reservemeetingroom.reservation.domain.ReserveTime;
import com.wiseai.reservemeetingroom.user.domain.QUser;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

/**
 * 이 클래스는 예약에 대한 커스텀 쿼리 로직을 담당합니다.
 */
@RequiredArgsConstructor
public class ReservationQueryRepositoryImpl implements ReservationQueryRepository {

	private final JPAQueryFactory query;

	@Override
	public Optional<ReservationDetailDto> findDetailByReservationId(Long reservationId) {
		QReservation r = QReservation.reservation;
		QReserveTime rt = QReserveTime.reserveTime;
		QReserveSlot rs = QReserveSlot.reserveSlot;
		QUser u = QUser.user;
		QMeetingRoom mr = QMeetingRoom.meetingRoom;

		// 예약 조회
		Reservation reservation = query
			.selectFrom(r)
			.join(r.user, u).fetchJoin()
			.where(r.id.eq(reservationId))
			.fetchOne();

		// 연결된 예약 슬롯 조회
		List<ReserveTime> reserveTimes = query
			.selectFrom(rt)
			.join(rt.slot, rs).fetchJoin()
			.join(rs.meetingRoom, mr).fetchJoin()
			.where(rt.reservation.id.eq(reservationId))
			.orderBy(rs.startTime.asc())
			.fetch();

		return Optional.of(ReservationDetailDto.from(reservation, reserveTimes));
	}


}