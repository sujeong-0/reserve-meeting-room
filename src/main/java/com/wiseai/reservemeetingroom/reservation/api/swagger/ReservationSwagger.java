package com.wiseai.reservemeetingroom.reservation.api.swagger;

import com.wiseai.reservemeetingroom.reservation.api.response.ReservationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 이 클래스는 예약 컨트롤러의 문서를 담당합니다.
 */
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
		@Parameter(description = "예약 아이디", example = "test@email.com", required = true)
		@PathVariable("reservationId") Long id
	);



}
