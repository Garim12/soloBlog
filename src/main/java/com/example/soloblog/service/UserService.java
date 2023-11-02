package com.example.soloblog.service;

import com.example.soloblog.dto.RestApiResponseDto;
import com.example.soloblog.dto.UserRequestDto;
import com.example.soloblog.dto.UserResponseDto;
import com.example.soloblog.entity.User;
import com.example.soloblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public ResponseEntity<RestApiResponseDto> signup(UserRequestDto userDto) {
        try {
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

            return this.resultResponse(HttpStatus.CREATED, "회원가입에 성공하였습니다.", new UserResponseDto(user));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new RestApiResponseDto(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    public ResponseEntity<RestApiResponseDto> userDelete(String username) {
        try {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));

            userRepository.delete(user);
            return this.resultResponse(HttpStatus.OK, "회원탈퇴에 성공하였습니다.", new UserResponseDto(user));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new RestApiResponseDto(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
        }
    }

    private ResponseEntity<RestApiResponseDto> resultResponse(HttpStatus status, String message, Object result) {
        RestApiResponseDto restApiResponseDto = new RestApiResponseDto(status.value(), message, result);
        return new ResponseEntity<>(
                restApiResponseDto,
                status
        );
    }
}
