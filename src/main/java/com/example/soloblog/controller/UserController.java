package com.example.soloblog.controller;

import com.example.soloblog.dto.RestApiResponseDto;
import com.example.soloblog.dto.UserRequestDto;
import com.example.soloblog.security.UserDetailsImpl;
import com.example.soloblog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<RestApiResponseDto> signup(@RequestBody UserRequestDto userDto) {
        log.info("Request to signup user: {}", userDto);
        return userService.signup(userDto);
    }

    @DeleteMapping("/userdelete")
    public ResponseEntity<RestApiResponseDto> userDelete(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        log.info("Request to delete user: {}", userDetails);
        return userService.userDelete(userDetails.getUser());
    }
}
