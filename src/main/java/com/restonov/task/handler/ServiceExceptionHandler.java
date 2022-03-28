package com.restonov.task.handler;

import com.restonov.task.dto.ExceptionDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;


@RestControllerAdvice
@RequiredArgsConstructor
public class ServiceExceptionHandler extends DefaultHandlerExceptionResolver {
    private static final int NOT_FOUND_CUSTOM_CODE = 40401;
    private static final int BAD_REQUEST_CUSTOM_CODE = 40005;
    private static final int FORBIDDEN_CUSTOM_CODE = 40300;

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ExceptionDto> resourceNotFound(ServiceException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionDto(e.getMessage(), NOT_FOUND_CUSTOM_CODE)
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionDto> accessDeniedException(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new ExceptionDto(e.getMessage(), FORBIDDEN_CUSTOM_CODE)
        );
    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity<ExceptionDto> forbiddenException(HttpClientErrorException.Forbidden e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new ExceptionDto(e.getMessage(), FORBIDDEN_CUSTOM_CODE)
        );
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ExceptionDto> badRequest(Throwable e) {
        return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body (
                new ExceptionDto( e.getMessage(), BAD_REQUEST_CUSTOM_CODE )
        );
    }
}
