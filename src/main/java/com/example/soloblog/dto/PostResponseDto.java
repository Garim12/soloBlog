package com.example.soloblog.dto;

import com.example.soloblog.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String username;
    private String nickname;
    private Long user_id;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.user_id = post.getUser().getId();
        this.username = post.getUser().getUsername();
        this.nickname = post.getUser().getNickname();
    } // PostResponseDto() end
}
