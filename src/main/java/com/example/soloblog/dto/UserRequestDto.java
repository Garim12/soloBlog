package com.example.soloblog.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class UserRequestDto {
    @Size(min = 5, max = 20, message = "아이디는 5자 이상 20자 이하로 입력해주세요.")
    private String username;
    @Size(min = 10, max = 20, message = "비밀번호는 10자 이상 20자 이하로 입력해주세요.")
    private String password;
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;
    @Size(min = 5, max = 20, message = "닉네임은 5자 이상 20자 이하로 입력해주세요.")
    private String nickname;
    private boolean isAdmin;

    private String adminToken;
}
