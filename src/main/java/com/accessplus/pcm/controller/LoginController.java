package com.accessplus.pcm.controller;

import com.accessplus.pcm.dto.ApiResponseDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author Anish Panthi
 */
@RestController
@SecurityRequirement(name = "accessplus-pms")
public class LoginController {

    public ResponseEntity<Mono<ApiResponseDto>> register(){

        return null;
    }

}
