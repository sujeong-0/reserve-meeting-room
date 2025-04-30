package com.wiseai.reservemeetingroom.core.exception;

import static org.springframework.http.HttpStatus.*;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


/**
 * 오류 코드를 정의합니다.
 */
@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Common
    INVALID_JSON(BAD_REQUEST,"잘못된 JSON 형식입니다. 요청 데이터를 확인하세요."),
    FIELD_ERROR(BAD_REQUEST,"입력이 잘못되었습니다."),
    URL_PARAMETER_ERROR(BAD_REQUEST,"입력이 잘못되었습니다."),
    METHOD_ARGUMENT_TYPE_MISMATCH(BAD_REQUEST,"입력한 값의 타입이 잘못되었습니다."),
    ALREADY_DISCONNECTED(BAD_REQUEST,"이미 클라이언트에서 요청이 종료되었습니다."),
    NO_RESOURCE_FOUND(NOT_FOUND,"요청한 리소스를 찾을 수 없습니다."),
    METHOD_NOT_SUPPORTED(METHOD_NOT_ALLOWED,"허용되지 않은 메서드입니다."),
    MEDIA_TYPE_NOT_SUPPORTED(UNSUPPORTED_MEDIA_TYPE,"허용되지 않은 미디어 타입입니다."),
    SERVER_ERROR(INTERNAL_SERVER_ERROR,"서버 오류가 발생했습니다. 관리자에게 문의하세요."),

    // user
    NOT_FOUND_USER(NOT_FOUND, "사용자를 찾을 수 없습니다. %s"),
    DUPLICATE_EMAIL(BAD_REQUEST, "이미 사용중인 이메일 입니다. email=%s"),

    // reservation
    NOT_FOUND_RESERVATION(NOT_FOUND, "예약을 찾을 수 없습니다. reservationId : %d"),

    // meeting room
    NOT_FOUND_MEETING_ROOM(TOO_MANY_REQUESTS, "회의실을 찾을 수 없습니다. meetingRoomID : %d"),
    ;

    public final HttpStatus httpStatus;
    private final String message;
}
