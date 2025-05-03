package com.wiseai.reservemeetingroom.meeting_room.domain.repository;

import com.wiseai.reservemeetingroom.meeting_room.domain.MeetingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 이 클래스는 회의실 저장소를 담당합니다.
 */
public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, Long>, MeetingRoomQueryRepository {

}
