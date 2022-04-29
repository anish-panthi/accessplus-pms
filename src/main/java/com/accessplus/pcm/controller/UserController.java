package com.accessplus.pcm.controller;

import com.accessplus.pcm.dto.UserDto;
import com.accessplus.pcm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author Anish Panthi
 */
@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping(value = "/users/all", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<Page<UserDto>> getAllUsers(
      @RequestParam("page") int page, @RequestParam("size") int size) {
    return userService.findAll(PageRequest.of(page, size));
  }
}
