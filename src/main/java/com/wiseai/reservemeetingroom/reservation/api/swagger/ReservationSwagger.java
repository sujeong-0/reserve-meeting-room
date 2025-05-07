package com.wiseai.reservemeetingroom.reservation.api.swagger;

import com.wiseai.reservemeetingroom.reservation.api.request.CreateReservation;
import com.wiseai.reservemeetingroom.reservation.api.response.ReservationResponse;
import com.wiseai.reservemeetingroom.reservation.exception.NotFoundReservationException;
import com.wiseai.reservemeetingroom.reservation.exception.NotFoundReservationSlotException;
import com.wiseai.reservemeetingroom.reservation.exception.OverlappingReservation;
import com.wiseai.reservemeetingroom.reservation.exception.SlotNotFoundForReservationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 이 클래스는 예약 컨트롤러의 문서를 담당합니다.
 */

@Tag(
	name = "Reservation API",
	description = """
		<b>예약 관련 API입니다.</b><br><br>
		"""
)
public interface ReservationSwagger {

	/*──────────────────────────────────────────────────────
	 * 1. 특정 예약 조회
	 *──────────────────────────────────────────────────────*/
	@Operation(
		summary = "Reservation 특정 조회",
		description = """
			reservation pk로 예약를 조회합니다.
			""",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "예약 조회 성공",
				content = @Content(
					schema = @Schema(implementation = ReservationResponse.class)
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
	@GetMapping("{reservationId}")
	ReservationResponse searchReservation(
		@Parameter(description = "예약 아이디", example = "1", required = true)
		@PathVariable("reservationId") Long id
	);

	/*──────────────────────────────────────────────────────
	 * 2. 예약 생성
	 *──────────────────────────────────────────────────────*/
	@Operation(
		summary = "Reservation 생성",
		description = """
        유저 ID와 슬롯 ID 목록을 기반으로 예약을 생성합니다.
        예약할 슬롯이 이미 예약되어 있는 경우 예외를 반환합니다.
        """,
		requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
			description = "예약 생성 요청",
			required = true,
			content = @Content(
				schema = @Schema(implementation = CreateReservation.class)
			)
		),
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "예약 생성 성공",
				content = @Content(
					schema = @Schema(implementation = ReservationResponse.class)
				)
			),
			@ApiResponse(
				responseCode = "400",
				description = "예약된 슬롯에 중복 예약",
				content = @Content(schema = @Schema(implementation = OverlappingReservation.class)),
				useReturnTypeSchema = false
			),
			@ApiResponse(
				responseCode = "404",
				description = "존재하지 않는 슬롯에 예약",
				content = @Content(schema = @Schema(implementation = NotFoundReservationSlotException.class)),
				useReturnTypeSchema = false
			)
		}
	)
	@ResponseStatus(HttpStatus.OK)
	@PostMapping
	ReservationResponse createReservation(
		@RequestBody CreateReservation createReservation
	);

	/*──────────────────────────────────────────────────────
	 * 4. 예약 취소
	 *──────────────────────────────────────────────────────*/
	@Operation(
		summary = "Reservation 취소",
		description = """
		reservation pk로 예약을 취소합니다.
		취소된 예약 정보를 반환합니다.
		""",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "예약 취소 성공",
				content = @Content(
					schema = @Schema(implementation = ReservationResponse.class)
				)
			),
			@ApiResponse(
				responseCode = "404",
				description = "예약을 찾을 수 없음",
				content = @Content(
					schema = @Schema(implementation = NotFoundReservationException.class)
				)
			),
			@ApiResponse(
				responseCode = "404",
				description = "예약에 예약시간 정보가 없음",
				content = @Content(
					schema = @Schema(implementation = SlotNotFoundForReservationException.class)
				)
			)
		}
	)
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/{reservationId}")
	ReservationResponse cancelReservation(@PathVariable Long reservationId);
}
