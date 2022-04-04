package com.social.musician.service.persistence.jpa.response;

import lombok.Value;

@Value
public class AuthenticationResponse {

    private final String jwt;
}
