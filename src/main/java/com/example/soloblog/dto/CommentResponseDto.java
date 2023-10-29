package com.example.soloblog.dto;

import com.example.soloblog.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String username;
    private String nickname;
    private String content;
    private Long post_id;
    private Long user_id;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.post_id = comment.getPost().getId();
        this.user_id = comment.getUser().getId();
        this.username = comment.getUser().getUsername();
        this.nickname = comment.getUser().getNickname();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    } // CommentResponseDto() end
}
