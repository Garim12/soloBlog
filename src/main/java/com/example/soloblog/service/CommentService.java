package com.example.soloblog.service;

import com.example.soloblog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final UserService userService;
    private final PostService postService;
    private final CommentRepository commentRepository;


}
