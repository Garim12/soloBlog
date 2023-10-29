package com.example.soloblog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class UserRequestDto {
    private String username;
    private String password;
    private String email;
    private String nickname;
    private String role;
}
