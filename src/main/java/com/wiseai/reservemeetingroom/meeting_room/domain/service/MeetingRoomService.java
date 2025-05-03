package com.wiseai.reservemeetingroom.meeting_room.domain.service;

import com.wiseai.reservemeetingroom.meeting_room.domain.repository.MeetingRoomRepository;
import com.wiseai.reservemeetingroom.meeting_room.domain.MeetingRoom;
import com.wiseai.reservemeetingroom.meeting_room.exception.NotFoundMeetingRoomException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 이 클래스는 회의실 서비스를 담당합니다.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class MeetingRoomService {
	private final MeetingRoomRepository meetingRoomRepository;


	@Transactional(readOnly = true)
	public MeetingRoom findExistingMeetingRoom(Long meetingRoomId) {
		return meetingRoomRepository.findActiveById(meetingRoomId)
			.orElseThrow(() -> new NotFoundMeetingRoomException(meetingRoomId));
	}

	@Transactional(readOnly = true)
	public List<MeetingRoom> findMeetingRooms(String keyword) {
		return meetingRoomRepository.searchActiveMeetingRooms(keyword);
	}

	public MeetingRoom createMeetingRoom(MeetingRoom meetingRoom) {
		return meetingRoomRepository.save(meetingRoom);
	}

	public MeetingRoom updateMeetingRoom(Long meetingRoomId, MeetingRoom meetingRoom) {
		MeetingRoom findMeetingRoom = meetingRoomRepository.findActiveById(meetingRoomId)
			.orElseThrow(() -> new NotFoundMeetingRoomException(meetingRoomId));

		findMeetingRoom.updateFrom(meetingRoom);
		return findMeetingRoom;
	}

	public MeetingRoom deleteMeetingRoom(Long meetingRoomId) {
		MeetingRoom deleteMeetingRoom = meetingRoomRepository.findActiveById(meetingRoomId)
			.orElseThrow(() -> new NotFoundMeetingRoomException(meetingRoomId));

		deleteMeetingRoom.markAsDeleted();

		return deleteMeetingRoom;
	}

}
