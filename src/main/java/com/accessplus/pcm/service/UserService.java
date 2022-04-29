package com.accessplus.pcm.service;

import com.accessplus.pcm.domain.User;
import com.accessplus.pcm.dto.ApiResponseDto;
import com.accessplus.pcm.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Anish Panthi
 */
public interface UserService extends BaseService<User, String, UserDto> {

  /**
   * Registers new user
   *
   * @param userDto - Information of user to register
   * @return <code>Mono</code> of <code>ApiResponseDto</code>
   */
  Mono<ApiResponseDto> register(UserDto userDto);

  /**
   * Searches user by email.
   *
   * @param email - String
   * @return <code>Mono</code> of <code>User</code>
   */
  Mono<UserDto> findByEmail(String email);

  Mono<UserDto> save(UserDto userDto);

  default Mono<UserDto> update(UserDto userDto) {
    return save(userDto);
  }

  Mono<Page<UserDto>> findAll(PageRequest pageRequest);
}
