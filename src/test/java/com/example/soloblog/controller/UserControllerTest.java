package com.example.soloblog.controller;

import com.example.soloblog.dto.UserRequestDto;
import com.example.soloblog.entity.User;
import com.example.soloblog.repository.UserRepository;
import com.example.soloblog.security.UserDetailsServiceImpl;
import com.example.soloblog.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@ContextConfiguration
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
@Rollback
class UserControllerTest {

    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext wac;

    @BeforeEach
    @Transactional
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }


    @BeforeEach
    @Transactional
    public void login() {

        User user = new User();
        user.setUsername("test2");
        user.setPassword(passwordEncoder.encode("test2"));
        user.setNickname("test2");
        user.setEmail("test2@test.com");
        user.setRole("USER");
        User save = userRepository.save(user);
    }

    @AfterEach
    @Transactional
    public void cleanup() {
        userRepository.deleteAll();
    }

    @Order(1)
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

    @Order(2)
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

    @Order(4)
    @Test
    @WithUserDetails(value = "test2", userDetailsServiceBeanName = "userDetailsServiceImpl", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @DisplayName("회원탈퇴")
    public void userDelete() throws Exception {

        this.mockMvc
                .perform(delete("/api/users/userdelete"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Order(3)
    @Test
    @WithUserDetails(value = "test2", userDetailsServiceBeanName = "userDetailsServiceImpl", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @DisplayName("회원탈퇴 실패")
    public void userDeleteFail() throws Exception {

        //then
        this.mockMvc
                .perform(delete("/api/users/userdelete2"))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

}