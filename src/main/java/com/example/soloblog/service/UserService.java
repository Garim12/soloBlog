package com.example.soloblog.service;

import com.example.soloblog.dto.UserRequestDto;
import com.example.soloblog.entity.User;
import com.example.soloblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void signup(UserRequestDto userDto) {
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        String nickname = userDto.getNickname();
        String email = userDto.getEmail();
        String role = userDto.getRole();

        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("아이디가 이미 존재합니다.");
        }
        if (userRepository.findByNickname(nickname).isPresent()) {
            throw new IllegalArgumentException("닉네임이 이미 존재합니다.");
        }
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("이메일이 이미 존재합니다.");
        }

        User user = new User(username, password, nickname, email, role);

        userRepository.save(user);
    }
}
