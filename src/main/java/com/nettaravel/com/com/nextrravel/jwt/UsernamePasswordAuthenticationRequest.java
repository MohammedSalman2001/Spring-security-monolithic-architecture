package com.nettaravel.com.com.nextrravel.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsernamePasswordAuthenticationRequest {
    private String username;
    private String password;
}
