package com.example.restfulwebservice.user;

// HTTP Status code
// 2XX -> OK
// 4XX -> Client 측 오류
// 5xx -> Server 측 오류

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // 404 Status code를 발생 시키도록 지정
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

}
