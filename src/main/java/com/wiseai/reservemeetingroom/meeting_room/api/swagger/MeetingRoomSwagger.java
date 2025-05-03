package com.wiseai.reservemeetingroom.meeting_room.api.swagger;

import com.wiseai.reservemeetingroom.core.domain.UrlPath.MeetingRoom;
import com.wiseai.reservemeetingroom.meeting_room.api.request.CreateMeetingRoom;
import com.wiseai.reservemeetingroom.meeting_room.api.request.UpdateMeetingRoom;
import com.wiseai.reservemeetingroom.meeting_room.api.response.MeetingRoomResponse;
import com.wiseai.reservemeetingroom.meeting_room.exception.NotFoundMeetingRoomException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
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

/**
 * 이 클래스는 회의실 관련 문서를 담당합니다.
 */

@Tag(
	name = "MeetingRoom API",
	description = """
		<b>회의실 관련 API입니다.</b><br><br>
		"""
)
@RequestMapping(MeetingRoom.ROOT)
public interface MeetingRoomSwagger {

	/*──────────────────────────────────────────────────────
	 * 1. 특정 회의실 조회
	 *──────────────────────────────────────────────────────*/
	@Operation(
		summary = "MeetingRoom 특정 조회",
		description = """
			meetingRoom pk로 회의실를 조회합니다.
			""",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "회의실 조회 성공",
				content = @Content(
					schema = @Schema(implementation = MeetingRoomResponse.class)
					/*examples = @ExampleObject(
						value = """
                        {
                          "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
                          "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
                          "isOnboardingCompleted": false
                        }"""
					)*/
				)
			)
		}
	)
	@GetMapping("{meetingRoomId}")
	MeetingRoomResponse searchMeetingRoom(
		@Parameter(description = "회의실 위치", example = "A건물 3층", required = true)
		@PathVariable("meetingRoomId") Long id
	);


	/*──────────────────────────────────────────────────────
	 * 2. 전체 회의실 조회
	 *──────────────────────────────────────────────────────*/
	@Operation(
		summary = "MeetingRoom 전체 조회",
		description = """
			키워드를 입력하면, 위치와 이름에서 키워드를 포함하는 회의실를 전체 조회합니다.
			비워두면, 전체 검색을 합니다.
			""",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "회의실 조회 성공",
				content = @Content(
					schema = @Schema(implementation = MeetingRoomResponse.class)
					/*examples = @ExampleObject(
						value = """
                        {
                          "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
                          "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
                          "isOnboardingCompleted": false
                        }"""
					)*/
				)
			)
		}
	)
	@GetMapping
	List<MeetingRoomResponse> searchMeetingRooms(
		@Parameter(description = "검색어", example = "아무개")
		@RequestParam("keyword") String keyword
	);


	/*──────────────────────────────────────────────────────
	 * 3. 회의실 추가
	 *──────────────────────────────────────────────────────*/
	@Operation(
		summary = "MeetingRoom 추가",
		description = """
			이름과 위치을 입력해 회의실를 추가합니다.
			""",

		requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
			required = true,
			content = @Content(schema = @Schema(implementation = CreateMeetingRoom.class),
				examples = @ExampleObject(name = "createRequest",
					value = """
						{
						  "name": "스탠딩 회의실",
						  "location": "A건물 3층"
						}"""
				))
		),
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "회의실 추가 성공",
				content = @Content(
					schema = @Schema(implementation = MeetingRoomResponse.class)
				)
			)
		}
	)
	@PostMapping
	MeetingRoomResponse createMeetingRoom(
		@Parameter(description = "추가할 회의실 정보", required = true)
		@RequestBody CreateMeetingRoom meetingRoom
	);


	/*──────────────────────────────────────────────────────
	 * 4. 회의실 수정
	 *──────────────────────────────────────────────────────*/
	@Operation(
		summary = "MeetingRoom 수정",
		description = """
			회의실의 이름과 위치을 수정합니다.
			회의실의 아이디를 함께 입력해 수정할 회의실를 지정합니다.
			* 변경을 하고 싶은 필드만 값을 입력합니다.
			* 이름과 위치는 공백일 수 없습니다. 변경을 원지 않으시면 값을 비워주세요.
			""",

		requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
			required = true,
			content = @Content(schema = @Schema(implementation = CreateMeetingRoom.class),
				examples = @ExampleObject(name = "updateMeetingRoom",
					value = """
						{
						  "name": "스탠딩 회의실",
						  "location": "A건물 3층"
						}"""
				))
		),
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "회의실 수정 성공",
				content = @Content(
					schema = @Schema(implementation = MeetingRoomResponse.class)
				)
			),
			@ApiResponse(
				responseCode = "400",
				description = "조회된 회의실 없음",
				content = @Content(
					schema = @Schema(implementation = NotFoundMeetingRoomException.class)
				)
			)
		}
	)

	@PatchMapping("{meetingRoomId}")
	MeetingRoomResponse updateMeetingRoom(
		@Parameter(description = "수정할 회의실 정보", required = true)
		@RequestBody UpdateMeetingRoom meetingRoom,

		@Parameter(description = "수정할 회의실 아이디", required = true)
		@PathVariable Long meetingRoomId
	);




	/*──────────────────────────────────────────────────────
	 * 5. 회의실 삭제
	 *──────────────────────────────────────────────────────*/
	@Operation(
		summary = "MeetingRoom 삭제",
		description = """
			회의실를 삭제합니다.
			* 삭제하고 싶은 회의실의 id를 입력합니다.
			""",

		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "회의실 삭제 성공",
				content = @Content(
					schema = @Schema(implementation = MeetingRoomResponse.class)
				)
			),
			@ApiResponse(
				responseCode = "400",
				description = "조회된 회의실 없음",
				content = @Content(
					schema = @Schema(implementation = NotFoundMeetingRoomException.class)
				)
			)
		}
	)
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/{meetingRoomId}")
	MeetingRoomResponse deleteMeetingRoom(
		@PathVariable("meetingRoomId") Long meetingRoomId
	);
}
