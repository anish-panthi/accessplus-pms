package com.accessplus.pcm.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Anish Panthi
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "USER")
@Builder
public class User implements Serializable {

  @Id
  @Column("RECORD_ID")
  private Long recordId;

  @Column("UUID")
  private String uuid;

  @Column("FIRST_NAME")
  private String firstName;

  @Column("LAST_NAME")
  private String lastName;

  @Column("USERNAME")
  private String username;

  @Column("EMAIL")
  private String email;

  @Column("PASSWORD")
  private String password;

  @Column("IS_ACTIVE")
  private boolean isActive;

  @Column("IS_VERIFIED")
  private boolean isVerified;

  @Column("CREATED_DTM")
  @CreatedDate
  private Date createdDateTime;

  @Column("LAST_UPDATED_DTM")
  private Date lastUpdatedDateTime;

}
