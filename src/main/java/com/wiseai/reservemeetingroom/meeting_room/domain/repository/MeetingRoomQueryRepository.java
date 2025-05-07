package com.wiseai.reservemeetingroom.meeting_room.domain.repository;

import com.wiseai.reservemeetingroom.meeting_room.domain.MeetingRoom;
import java.util.List;
import java.util.Optional;

/**
 * 이 클래스는 []를 담당합니다.
 */
public interface MeetingRoomQueryRepository {

	List<MeetingRoom> searchActiveMeetingRooms(String keyword);
	List<MeetingRoom> searchActiveMeetingRooms();
	Optional<MeetingRoom> findActiveById(Long id);
}
