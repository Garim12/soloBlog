package com.example.soloblog.dto;

import com.example.soloblog.entity.User;
import com.example.soloblog.entity.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String username;
    private String nickname;
    private String email;
    private UserRoleEnum isAdmin;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.isAdmin = user.getRole();
    } // UserResponseDto() end
}
