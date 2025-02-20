package com.yodimdasiz.yodimdasiz.auth;

import lombok.*;


@Data
@Builder
@Getter
@Setter
public class AuthenticationResponse {

    private String token;
}
