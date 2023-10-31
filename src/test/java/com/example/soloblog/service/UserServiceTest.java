package com.example.soloblog.service;

import com.example.soloblog.dto.UserRequestDto;
import com.example.soloblog.entity.User;
import com.example.soloblog.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Rollback
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void signup() {
        //given
        UserRequestDto requestDto = new UserRequestDto();

        requestDto.setUsername("test");
        requestDto.setPassword("test");
        requestDto.setNickname("test");
        requestDto.setEmail("test@test.com");
        requestDto.setRole("USER");

        //when
        userService.signup(requestDto);

        //then
        String Username = userRepository.findByUsername("test").get().getUsername();
        String Password = userRepository.findByUsername("test").get().getPassword();
        String Nickname = userRepository.findByUsername("test").get().getNickname();
        String Email = userRepository.findByUsername("test").get().getEmail();
        String Role = userRepository.findByUsername("test").get().getRole();

        assertThat(Username).isEqualTo(requestDto.getUsername());
        assertThat(Password).isEqualTo(requestDto.getPassword());
        assertThat(Nickname).isEqualTo(requestDto.getNickname());
        assertThat(Email).isEqualTo(requestDto.getEmail());
        assertThat(Role).isEqualTo(requestDto.getRole());
    }

    @Test
    void signupFail() {
        //given
        UserRequestDto requestDto = new UserRequestDto();

        requestDto.setUsername("test");
        requestDto.setPassword("test");
        requestDto.setNickname("test");
        requestDto.setEmail("test@test.com");
        requestDto.setRole("USER");

        //when
        userService.signup(requestDto);

        //then
        String Username = userRepository.findByUsername("test").get().getUsername();
        String Password = userRepository.findByUsername("test").get().getPassword();
        String Nickname = userRepository.findByUsername("test").get().getNickname();
        String Email = userRepository.findByUsername("test").get().getEmail();
        String Role = userRepository.findByUsername("test").get().getRole();

        assertThat(Username).isNotEqualTo("notest");
        assertThat(Password).isNotEqualTo("notest");
        assertThat(Nickname).isNotEqualTo("notest");
        assertThat(Email).isNotEqualTo("notest");
        assertThat(Role).isNotEqualTo("notest");
    }

    @Test
    void userDelete() {
        //given
        UserRequestDto requestDto = new UserRequestDto();

        requestDto.setUsername("test");
        requestDto.setPassword("test");
        requestDto.setNickname("test");
        requestDto.setEmail("test@test.com");
        requestDto.setRole("USER");

        userService.signup(requestDto);

        User user = userRepository.findByUsername("test")
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));

        //when
        userService.userDelete(user.getId());

        //then
        assertThat(userRepository.findById(1L)).isEmpty();
    }

    @Test
    void userDeleteFail() {
        //given
        UserRequestDto requestDto = new UserRequestDto();

        requestDto.setUsername("test");
        requestDto.setPassword("test");
        requestDto.setNickname("test");
        requestDto.setEmail("test@test.com");
        requestDto.setRole("USER");

        userService.signup(requestDto);

        User user = userRepository.findByUsername("test")
                .orElseThrow(() -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다."));

        //when
        userService.userDelete(200L);

        //then
        assertThat(userRepository.findById(1L)).isNotEmpty();
    }
}