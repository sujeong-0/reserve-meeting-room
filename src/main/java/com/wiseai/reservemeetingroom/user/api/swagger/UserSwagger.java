package com.wiseai.reservemeetingroom.user.api.swagger;

import com.wiseai.reservemeetingroom.core.domain.UrlPath.User;
import com.wiseai.reservemeetingroom.user.api.request.CreateUser;
import com.wiseai.reservemeetingroom.user.api.request.UpdateUser;
import com.wiseai.reservemeetingroom.user.api.response.UserResponse;
import com.wiseai.reservemeetingroom.user.exception.DuplicateEmailException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	@GetMapping("{userId}")
	UserResponse searchUser(
		@Parameter(description = "유저 이메일", example = "test@email.com", required = true)
		@PathVariable("userId") Long id
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
	@GetMapping
	List<UserResponse> searchUsers(
		@Parameter(description = "검색어", example = "아무개")
		@RequestParam("keyword") String keyword
	);


	/*──────────────────────────────────────────────────────
	 * 3. 유저 추가
	 *──────────────────────────────────────────────────────*/
	@Operation(
		summary = "User 추가",
		description = """
			이름과 이메일을 입력해 유저를 추가합니다.
			이메일은 중복될 수 없습니다.
			""",

		requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
			required = true,
			content = @Content(schema = @Schema(implementation = CreateUser.class),
				examples = @ExampleObject(name = "createRequest",
					value = """
						{
						  "name": "홍길동",
						  "email": "test@email.com"
						}"""
				))
		),
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "유저 추가 성공",
				content = @Content(
					schema = @Schema(implementation = UserResponse.class)
				)
			),
			@ApiResponse(
				responseCode = "400",
				description = "email 중복",
				content = @Content(
					schema = @Schema(implementation = DuplicateEmailException.class)
				)
			)
		}
	)
	@PostMapping
	UserResponse createUser(
		@Parameter(description = "추가할 유저 정보", required = true)
		@RequestBody CreateUser user
	);


	/*──────────────────────────────────────────────────────
	 * 3. 유저 수정
	 *──────────────────────────────────────────────────────*/
	@Operation(
		summary = "User 수정",
		description = """
			유저의 이름과 이메일을 수정합니다.
			유저의 아이디를 함께 입력해 수정할 유저를 지정합니다.
			* 변경을 하고 싶은 필드만 값을 입력합니다.
			* 이메일은 중복될 수 없습니다.
			""",

		requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
			required = true,
			content = @Content(schema = @Schema(implementation = CreateUser.class),
				examples = @ExampleObject(name = "updateUser",
					value = """
						{
						  "name": "홍길동",
						  "email": "test@email.com"
						}"""
				))
		),
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "유저 수정 성공",
				content = @Content(
					schema = @Schema(implementation = UserResponse.class)
				)
			),
			@ApiResponse(
				responseCode = "400",
				description = "email 중복",
				content = @Content(
					schema = @Schema(implementation = DuplicateEmailException.class)
				)
			)
		}
	)

	@PatchMapping("{userId}")
	UserResponse updateUser(
		@Parameter(description = "수정할 유저 정보", required = true)
		@RequestBody UpdateUser user,

		@Parameter(description = "수정할 유저 아이디", required = true)
		@PathVariable Long userId
	);

}
