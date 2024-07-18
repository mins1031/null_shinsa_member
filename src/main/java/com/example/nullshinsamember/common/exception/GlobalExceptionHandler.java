package com.example.nullshinsamember.common.exception;

import com.example.nullshinsamember.user.domain.dto.response.ExceptionResponse;
import com.example.nullshinsamember.user.exception.UserException;
import com.example.nullshinsamember.user.exception.UserExceptionCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ExceptionResponse> handlerUserException(final UserException ex, final HttpServletRequest req) {
        UserExceptionCode code = ex.getUserExceptionCode();

        log.info("====  UserException ==== PATH: {}, HTTP_METHOD: {}, ERROR_MESSAGE: {}, HTTP_STATUS: {}",
                req.getRequestURI(), req.getMethod(), code.getErrorMessage(), code.getHttpStatus().toString());

        return ResponseEntity.status(code.getHttpStatus()).body(new ExceptionResponse(code.getErrorCode(), code.getErrorMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handlerException(final Exception ex, final HttpServletRequest req) {
        log.info("==== Exception ==== PATH: {}, HTTP_METHOD: {}, ERROR_MESSAGE: {}, HTTP_STATUS: {}",
                req.getRequestURI(), req.getMethod(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()));
    }

}
