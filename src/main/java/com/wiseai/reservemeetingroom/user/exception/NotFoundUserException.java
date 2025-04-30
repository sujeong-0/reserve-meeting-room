package com.wiseai.reservemeetingroom.user.exception;

import static com.wiseai.reservemeetingroom.core.exception.ErrorCode.NOT_FOUND_USER;

import com.wiseai.reservemeetingroom.core.exception.NotFoundException;

public class NotFoundUserException extends NotFoundException {
    public NotFoundUserException(Long userId) {
        super(NOT_FOUND_USER.getMessage().formatted("id = %d".formatted(userId)));
    }
    public NotFoundUserException(String keyword) {
        super(NOT_FOUND_USER.getMessage().formatted("keyword = %s".formatted(keyword)));
    }

    @Override
    public String getErrorCode() {
        return NOT_FOUND_USER.name();
    }
}
