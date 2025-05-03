package com.wiseai.reservemeetingroom.user.exception;

import static com.wiseai.reservemeetingroom.core.exception.ErrorCode.DUPLICATE_EMAIL;
import static com.wiseai.reservemeetingroom.core.exception.ErrorCode.NOT_FOUND_USER;

import com.wiseai.reservemeetingroom.core.exception.NotFoundException;

public class DuplicateEmailException extends NotFoundException {
    public DuplicateEmailException(String email) {
        super(DUPLICATE_EMAIL.getMessage().formatted(email));
    }

    @Override
    public String getErrorCode() {
        return DUPLICATE_EMAIL.name();
    }
}
