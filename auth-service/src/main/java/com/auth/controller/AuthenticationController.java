package com.auth.controller;

import com.auth.service.authentication.AuthenticationService;
import com.auth.to.Device;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.auth.util.Constants.AUTHORIZATION_HEADER_NAME;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @CrossOrigin(origins = "https://www.hexolink.xyz")
    @PostMapping
    public ResponseEntity authentication(
            @RequestHeader(AUTHORIZATION_HEADER_NAME) @NotNull String authorizationToken
    ) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(HttpHeaders.AUTHORIZATION,
                authenticationService.authenticate(authorizationToken));
        return ResponseEntity.ok()
                .headers(responseHeaders).build();
    }
}
