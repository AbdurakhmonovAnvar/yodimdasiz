package com.yodimdasiz.yodimdasiz.auth;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String username;

    private String phone;

    private String email;

    private String password;

}
