package com.social.musician.service.persistence.jpa.request;

import lombok.Value;

@Value
public class AuthenticationRequest {

    private String userName;

    private String password;
}
