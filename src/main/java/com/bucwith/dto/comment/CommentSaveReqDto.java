package com.bucwith.dto.comment;

import com.bucwith.domain.user.User;
import com.bucwith.domain.comment.Comment;
import com.bucwith.domain.community.Community;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class CommentSaveReqDto {
    private Long commuId;
    private Long parentId;
    private Long userId;
    @NotBlank(message = "내용을 입력해주세요")
    private String content;
    private Boolean secret;

    @Builder
    public CommentSaveReqDto(Long commuId, Long parentId, Long userId, String content, Boolean secret){
        this.commuId = commuId;
        this.parentId = parentId;
        this.userId = userId;
        this.content = content;
        this.secret = secret;
    }

    public Comment toEntity(Community community, User user){
        return Comment.builder()
                .community(community)
                .parentId(parentId)
                .user(user)
                .content(content)
                .secret(secret)
                .build();
    }
}
