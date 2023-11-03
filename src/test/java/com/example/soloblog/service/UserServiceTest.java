package com.example.soloblog.service;

import com.example.soloblog.dto.RestApiResponseDto;
import com.example.soloblog.dto.UserRequestDto;
import com.example.soloblog.entity.User;
import com.example.soloblog.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Rollback
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원가입 테스트")
    void signup() {
        //given
        UserRequestDto requestDto = new UserRequestDto();

        requestDto.setUsername("test");
        requestDto.setPassword("test");
        requestDto.setNickname("test");
        requestDto.setEmail("test@test.com");

        //when
        userService.signup(requestDto);

        //then
        String Username = userRepository.findByUsername("test").get().getUsername();
        String password = userRepository.findByUsername("test").get().getPassword();
        String Nickname = userRepository.findByUsername("test").get().getNickname();
        String Email = userRepository.findByUsername("test").get().getEmail();

        assertThat(Username).isEqualTo(requestDto.getUsername());
        passwordEncoder.matches(requestDto.getPassword(), password);
        assertThat(Nickname).isEqualTo(requestDto.getNickname());
        assertThat(Email).isEqualTo(requestDto.getEmail());
    }

    @Test
    @DisplayName("회원가입실패 및 관리자 확인 테스트")
    void signupFailAndAdmin() {
        //given
        UserRequestDto requestDto = new UserRequestDto();

        requestDto.setUsername("test");
        requestDto.setPassword("test");
        requestDto.setNickname("test");
        requestDto.setEmail("test@test.com");
        requestDto.setAdmin(true);
        requestDto.setAdminToken("AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC");

        //when
        userService.signup(requestDto);

        //then
        String Username = userRepository.findByUsername("test").get().getUsername();
        String Password = userRepository.findByUsername("test").get().getPassword();
        String Nickname = userRepository.findByUsername("test").get().getNickname();
        String Email = userRepository.findByUsername("test").get().getEmail();
        String admin = String.valueOf(userRepository.findByUsername("test").get().getRole());

        assertThat(Username).isNotEqualTo("notest");
        assertThat(Password).isNotEqualTo("notest");
        assertThat(Nickname).isNotEqualTo("notest");
        assertThat(Email).isNotEqualTo("notest");
        assertThat(admin).isEqualTo("ADMIN");
    }

    @Test
    @DisplayName("회원탈퇴 테스트")
    void userDelete() {
        //given
        UserRequestDto requestDto = new UserRequestDto();

        requestDto.setUsername("test");
        requestDto.setPassword("test");
        requestDto.setNickname("test");
        requestDto.setEmail("test@test.com");

        userService.signup(requestDto);

        User user = userRepository.findByUsername("test")
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));

        //when
        userService.userDelete(user);

        //then
        assertThat(userRepository.findByUsername("test")).isEmpty();
    }

    @Test
    @DisplayName("회원탈퇴실패 테스트")
    void userDeleteFail() {
        //given
        UserRequestDto requestDto = new UserRequestDto();

        //when
        userService.signup(requestDto);

        User user = new User();
        user.setUsername("test");

        ResponseEntity<RestApiResponseDto> response = userService.userDelete(user);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        RestApiResponseDto responseBody = response.getBody();
        assertThat(responseBody).isNotNull();
        assertThat(responseBody.getMessage()).contains("해당 아이디가 존재하지 않습니다.");
    }
}