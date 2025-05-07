package com.wiseai.reservemeetingroom.meeting_room.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wiseai.reservemeetingroom.meeting_room.domain.MeetingRoom;
import com.wiseai.reservemeetingroom.meeting_room.domain.QMeetingRoom;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

/**
 * 이 클래스는 사용자에 대한 커스텀 조회 로직을 담당합니다.
 */
@RequiredArgsConstructor
public class MeetingRoomQueryRepositoryImpl implements MeetingRoomQueryRepository {

	private final JPAQueryFactory queryFactory;

	QMeetingRoom meetingRoom = QMeetingRoom.meetingRoom;

	@Override
	public List<MeetingRoom> searchActiveMeetingRooms(String keyword) {
		BooleanBuilder condition = new BooleanBuilder();
		condition.and(meetingRoom.deleted.isFalse());

		if (null != keyword && !keyword.isBlank()) {
			condition.and(
				meetingRoom.name.containsIgnoreCase(keyword)
					.or(meetingRoom.location.containsIgnoreCase(keyword))
			);
		}

		return queryFactory
			.selectFrom(meetingRoom)
			.where(condition)
			.fetch();
	}
	@Override
	public List<MeetingRoom> searchActiveMeetingRooms() {
		BooleanBuilder condition = new BooleanBuilder();
		condition.and(meetingRoom.deleted.isFalse());

		return queryFactory
			.selectFrom(meetingRoom)
			.where(condition)
			.fetch();
	}

	@Override
	public Optional<MeetingRoom> findActiveById(Long id) {
		return Optional.ofNullable(
			queryFactory
				.selectFrom(meetingRoom)
				.where(meetingRoom.id.eq(id).and(meetingRoom.deleted.isFalse()))
				.fetchOne()
		);
	}
}
