package com.wiseai.reservemeetingroom.user.api.swagger;

import com.wiseai.reservemeetingroom.core.domain.UrlPath.User;
import com.wiseai.reservemeetingroom.user.api.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 이 클래스는 유저 관련 문서를 담당합니다.
 */

@Tag(
	name = "User API",
	description = """
		<b>유저 관련 API입니다.</b><br><br>
		"""
)
@RequestMapping(User.ROOT)
public interface UserSwagger {

	/*──────────────────────────────────────────────────────
	 * 1. 특정 유저 조회
	 *──────────────────────────────────────────────────────*/
	@Operation(
		summary = "User 특정 조회",
		description = """
			user pk로 유저를 조회합니다.
			""",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "유저 조회 성공",
				content = @Content(
					schema = @Schema(implementation = UserResponse.class)
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
	@GetMapping(User.ROOT)
	UserResponse searchUser(
		@Parameter(description = "유저 이메일", example = "test@email.com", required = true)
		@RequestParam("email") String email
	);


	/*──────────────────────────────────────────────────────
	 * 2. 전체 유저 조회
	 *──────────────────────────────────────────────────────*/
	@Operation(
		summary = "User 전체 조회",
		description = """
			키워드를 입력하면, email과 name에서 키워드를 포함하는 유저를 전체 조회합니다.
			""",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "유저 조회 성공",
				content = @Content(
					schema = @Schema(implementation = UserResponse.class)
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
	@GetMapping(User.ROOT)
	UserResponse searchUsers(
		@Parameter(description = "검색어", example = "아무개", required = true)
		@RequestParam("keyword") String keyword
	);

}
