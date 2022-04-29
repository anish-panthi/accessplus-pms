package com.accessplus.pcm.service.impl;

import com.accessplus.pcm.domain.User;
import com.accessplus.pcm.dto.ApiResponseDto;
import com.accessplus.pcm.dto.UserDetailsDto;
import com.accessplus.pcm.dto.UserDto;
import com.accessplus.pcm.repository.UserRepository;
import com.accessplus.pcm.service.UserService;
import com.accessplus.pcm.util.AppUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.ReactiveUserDetailsPasswordService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

/**
 * @author Anish Panthi
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, ReactiveUserDetailsService, ReactiveUserDetailsPasswordService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  /**
   * Registers new user
   *
   * @param userDto - Information of user to register
   * @return <code>Mono</code> of <code>ApiResponseDto</code>
   */
  @Override
  public Mono<ApiResponseDto> register(UserDto userDto) {
    var newUser = new User();
    BeanUtils.copyProperties(userDto, newUser);
    var newUuid = AppUtil.generateUuid();

    return null;
  }

  /**
   * Searches by supplied ID type.
   *
   * @param uuid - Basically of type <code>String</code>
   * @return <code>Mono</code> of type E (entity)
   */
  @Override
  public Mono<User> findByUuid(String uuid) {
    return userRepository.findUserByUuid(uuid);
  }

  /**
   * Saves information to database.
   *
   * @param userDto - Information to store
   * @return information that is stored in database
   */
  @Override
  public Mono<UserDto> save(UserDto userDto) {
    return userRepository.save(convertDtoToEntity(userDto)).map(this::convertEntityToDto);
  }

  /**
   * Find the {@link UserDetails} by username.
   *
   * @param username the username to look up
   * @return the {@link UserDetails}. Cannot be null
   */
  @Override
  public Mono<UserDetails> findByUsername(String username) {
    return userRepository
        .findUserByUsernameAndActive(username)
        .map(this::convertUserEntityToUserDetailsDto);
  }

  /**
   * Searches user by email.
   *
   * @param email - String
   * @return <code>Mono</code> of <code>User</code>
   */
  @Override
  public Mono<UserDto> findByEmail(String email) {
    return userRepository.findUserByEmailAndActive(email).map(this::convertEntityToDto);
  }

  @Override
  public Mono<Page<UserDto>> findAll(PageRequest pageRequest) {
    return userRepository
        .findAllBy(pageRequest)
        .collectList()
        .zipWith(userRepository.count())
        .map(
            t ->
                new PageImpl<>(
                    t.getT1().stream().map(this::convertEntityToDto).collect(Collectors.toList()),
                    pageRequest,
                    t.getT2()));
  }

  private UserDetailsDto convertUserEntityToUserDetailsDto(User user) {
    var userDto = new UserDto();
    BeanUtils.copyProperties(user, userDto);
    return new UserDetailsDto(userDto);
  }

  private UserDto convertEntityToDto(User user) {
    var userDto = new UserDto();
    BeanUtils.copyProperties(user, userDto);
    return userDto;
  }

  private User convertDtoToEntity(UserDto userDto) {
    var user = new User();
    BeanUtils.copyProperties(userDto, user);
    return user;
  }

  <S, D> D convert(D destination, S source){
    return null;
  }

  /**
   * Modify the specified user's password. This should change the user's password in the
   * persistent user repository (database, LDAP etc).
   *
   * @param user        the user to modify the password for
   * @param newPassword the password to change to
   * @return the updated UserDetails with the new password
   */
  @Override
  public Mono<UserDetails> updatePassword(UserDetails user, String newPassword) {
    log.info("Updating password for {}", user.getUsername());
//    return findByUsername(user.getUsername()).doOnSuccess(userDetails -> userDetails.)
    return null;
  }
}
