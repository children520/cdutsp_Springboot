package com.xiaojun.whut.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "用户已存在")
public class UserAlreadyExistException extends ResponseStatusException {

    public UserAlreadyExistException(String errorMessage) {
        super(HttpStatus.NOT_FOUND);
    }
}
