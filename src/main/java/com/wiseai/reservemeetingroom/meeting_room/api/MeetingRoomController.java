package com.wiseai.reservemeetingroom.meeting_room.api;

import com.wiseai.reservemeetingroom.core.domain.UrlPath.MeetingRoom;
import com.wiseai.reservemeetingroom.meeting_room.api.request.CreateMeetingRoom;
import com.wiseai.reservemeetingroom.meeting_room.api.request.UpdateMeetingRoom;
import com.wiseai.reservemeetingroom.meeting_room.api.response.MeetingRoomResponse;
import com.wiseai.reservemeetingroom.meeting_room.api.swagger.MeetingRoomSwagger;
import com.wiseai.reservemeetingroom.meeting_room.app.MeetingRoomFacade;
import com.wiseai.reservemeetingroom.meeting_room.app.dto.MeetingRoomDto;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 이 클래스는 회의실 관련 컨트롤러를 담당합니다.
 */
@RestController
@RequestMapping(MeetingRoom.ROOT)
@RequiredArgsConstructor
public class MeetingRoomController implements MeetingRoomSwagger {

	private final MeetingRoomFacade meetingRoomFacade;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{meetingRoomId}")
	@Override
	public MeetingRoomResponse searchMeetingRoom(
		@PathVariable("meetingRoomId") Long meetingRoomId
	) {
		return MeetingRoomResponse.from(meetingRoomFacade.searchMeetingRoom(meetingRoomId));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	@Override
	public List<MeetingRoomResponse> searchMeetingRooms(
		@RequestParam(value = "keyword", defaultValue = "") String keyword
	) {
		return meetingRoomFacade.searchMeetingRooms(keyword).stream().map(MeetingRoomResponse::from).toList();
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping
	@Override
	public MeetingRoomResponse createMeetingRoom(
		@RequestBody @Valid CreateMeetingRoom meetingRoom
	) {
		MeetingRoomDto saveMeetingRoom = meetingRoomFacade.createMeetingRoom(meetingRoom.toDto());
		return MeetingRoomResponse.from(saveMeetingRoom);
	}


	@ResponseStatus(HttpStatus.OK)
	@PatchMapping("/{meetingRoomId}")
	@Override
	public MeetingRoomResponse updateMeetingRoom(
		@RequestBody @Valid UpdateMeetingRoom meetingRoom,
		@PathVariable("meetingRoomId") Long meetingRoomId
	) {
		MeetingRoomDto saveMeetingRoom = meetingRoomFacade.updateMeetingRoom( meetingRoomId, meetingRoom.toDto());
		return MeetingRoomResponse.from(saveMeetingRoom);
	}



	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/{meetingRoomId}")
	@Override
	public MeetingRoomResponse deleteMeetingRoom(
		@PathVariable("meetingRoomId") Long meetingRoomId
	) {
		MeetingRoomDto deletedMeetingRoom = meetingRoomFacade.deleteMeetingRoom(meetingRoomId);
		return MeetingRoomResponse.from(deletedMeetingRoom);
	}


}