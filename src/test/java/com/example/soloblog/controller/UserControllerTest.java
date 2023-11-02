package com.example.soloblog.controller;

import com.example.soloblog.dto.UserRequestDto;
import com.example.soloblog.entity.User;
import com.example.soloblog.repository.UserRepository;
import com.example.soloblog.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Rollback
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext wac;

    @BeforeEach
//    @WithMockUser
//    @Transactional
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
//                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    @DisplayName("회원가입")
    public void signup() throws Exception {
        //given
        UserRequestDto requestDto = new UserRequestDto();

        //when
        requestDto.setUsername("test");
        requestDto.setPassword("test");
        requestDto.setNickname("test");
        requestDto.setEmail("test@test.com");
        requestDto.setRole("USER");

        //then
        this.mockMvc
                .perform(post("/api/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @DisplayName("회원가입 실패")
    public void signupFail() throws Exception {
        //given
        UserRequestDto requestDto = new UserRequestDto();

        //when
        requestDto.setUsername("test");
        requestDto.setPassword("test");
        requestDto.setNickname("test");
        requestDto.setEmail("test@test.com");
        requestDto.setRole("USER");
        userService.signup(requestDto);

        //then
        this.mockMvc
                .perform(post("/api/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    @DisplayName("회원탈퇴")
    public void userDelete() throws Exception {
        //given
        UserRequestDto requestDto = new UserRequestDto();

        //when
        requestDto.setUsername("test");
        requestDto.setPassword("test");
        requestDto.setNickname("test");
        requestDto.setEmail("test@test.com");
        requestDto.setRole("USER");
        userService.signup(requestDto);

        //then
        this.mockMvc
                .perform(delete("/api/users/test"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("회원탈퇴 실패")
    public void userDeleteFail() throws Exception {
        //given
        UserRequestDto requestDto = new UserRequestDto();

        //when
        requestDto.setUsername("test");
        requestDto.setPassword("test");
        requestDto.setNickname("test");
        requestDto.setEmail("test@test.com");
        requestDto.setRole("USER");

        //then
        this.mockMvc
                .perform(delete("/api/users/testfail"))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }
}