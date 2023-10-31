package com.example.soloblog.controller;

import com.example.soloblog.dto.RestApiResponseDto;
import com.example.soloblog.dto.UserRequestDto;
import com.example.soloblog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<RestApiResponseDto> signup(@RequestBody UserRequestDto userDto) {
        log.info("Request to signup user: {}", userDto);
        return userService.signup(userDto);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<RestApiResponseDto> userDelete(@PathVariable Long id) {
        log.info("Request to delete user: {}", id);
        return userService.userDelete(id);
    }
}
