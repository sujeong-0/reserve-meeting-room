package com.wiseai.reservemeetingroom.meeting_room.app;

import com.wiseai.reservemeetingroom.meeting_room.app.dto.MeetingRoomDto;
import com.wiseai.reservemeetingroom.meeting_room.domain.MeetingRoom;
import com.wiseai.reservemeetingroom.meeting_room.domain.service.MeetingRoomService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 이 클래스는 유저 서비스를 담당합니다.
 */
@Service
@RequiredArgsConstructor
public class MeetingRoomFacade {

	private final MeetingRoomService service;

	public MeetingRoomDto searchMeetingRoom(Long id) {
		return MeetingRoomDto.from(service.findExistingMeetingRoom(id));
	}

	public List<MeetingRoomDto> searchMeetingRooms(String keyword) {
		return service.findMeetingRooms(keyword).stream().map(MeetingRoomDto::from).toList();
	}

	public MeetingRoomDto createMeetingRoom(MeetingRoomDto meetingRoomData) {
		MeetingRoom meetingRoom = service.createMeetingRoom(meetingRoomData.toEntity());
		return MeetingRoomDto.from(meetingRoom);
	}

	public MeetingRoomDto updateMeetingRoom(Long meetingRoomId, MeetingRoomDto meetingRoomData) {
		MeetingRoom meetingRoom = service.updateMeetingRoom(meetingRoomId, meetingRoomData.toEntity());
		return MeetingRoomDto.from(meetingRoom);
	}

	public MeetingRoomDto deleteMeetingRoom(Long meetingRoomId) {
		MeetingRoom meetingRoom = service.deleteMeetingRoom(meetingRoomId);
		return MeetingRoomDto.from(meetingRoom);
	}
}
