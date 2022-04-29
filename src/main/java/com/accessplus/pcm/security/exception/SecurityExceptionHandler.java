package com.accessplus.pcm.security.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

/**
 * @author Anish Panthi
 */
@RestControllerAdvice
@Slf4j
public class SecurityExceptionHandler {

  @ExceptionHandler(AccessDeniedException.class)
  public Mono<ResponseEntity<String>> handleAccessDeniedException(AccessDeniedException ade) {
    log.error("{}", ExceptionUtils.getMessage(ade));
    return Mono.just(ResponseEntity.status(HttpStatus.FORBIDDEN).build());
  }
}
