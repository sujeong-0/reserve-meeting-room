package com.wiseai.reservemeetingroom.meeting_room.exception;

import static com.wiseai.reservemeetingroom.core.exception.ErrorCode.NOT_FOUND_MEETING_ROOM;

import com.wiseai.reservemeetingroom.core.exception.NotFoundException;

public class NotFoundMeetingRoomException extends NotFoundException {

	public NotFoundMeetingRoomException(Long meetingRoomId) {
		super(NOT_FOUND_MEETING_ROOM.getMessage().formatted(meetingRoomId));
	}

	@Override
	public String getErrorCode() {
		return NOT_FOUND_MEETING_ROOM.name();
	}
}
