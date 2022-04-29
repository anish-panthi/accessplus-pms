package com.accessplus.pcm.service;

import reactor.core.publisher.Mono;

/**
 * @author Anish Panthi
 */
public interface BaseService<E, ID, DTO> {

  /**
   * Searches by supplied ID type.
   *
   * @param uuid - Basically of type <code>String</code>
   * @return <code>Mono</code> of type E (entity)
   */
  Mono<E> findByUuid(ID uuid);

  /**
   * Saves information to database.
   *
   * @param dto - Information to store
   * @return information that is stored in database
   */
  Mono<DTO> save(DTO dto);
}
