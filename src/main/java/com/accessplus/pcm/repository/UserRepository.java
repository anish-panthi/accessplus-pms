package com.accessplus.pcm.repository;

import com.accessplus.pcm.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Anish Panthi
 */
public interface UserRepository extends ReactiveSortingRepository<User, Long> {

    Mono<User> findUserByUsernameAndActive(String username);

    Mono<User> findUserByUuid(String uuid);

    Mono<User> findUserByEmailAndActive(String email);

    Flux<User> findAllBy(Pageable pageable);
}
