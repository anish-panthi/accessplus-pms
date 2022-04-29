package com.accessplus.pcm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Anish Panthi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {

  private String uuid;

  private String firstName;

  private String lastName;

  private String username;

  private String email;

  private String password;

  private List<Role> roles;

  private boolean isActive;

  private boolean isVerified;

  private Date createdDateTime;

  private Date lastUpdatedDateTime;

  public UserDto(UserDto userDto) {
    this(
        userDto.uuid,
        userDto.firstName,
        userDto.lastName,
        userDto.username,
        userDto.email,
        userDto.password,
        userDto.roles,
        userDto.isActive,
        userDto.isVerified,
        userDto.createdDateTime,
        userDto.lastUpdatedDateTime);
  }
}
