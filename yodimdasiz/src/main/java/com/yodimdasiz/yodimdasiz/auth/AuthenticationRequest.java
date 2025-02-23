package com.yodimdasiz.yodimdasiz.auth;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    private String username;

    private String phone;

    private String email;

    private String password;
}
