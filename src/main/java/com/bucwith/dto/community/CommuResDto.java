package com.bucwith.dto.community;

import com.bucwith.domain.account.User;
import com.bucwith.domain.community.Category;
import com.bucwith.domain.community.CommuType;
import com.bucwith.domain.community.Community;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CommuResDto {
    private Long commuId;
    private String userName;
    private String content;
    private CommuType type;
    private List<Category> categoryList;
    private int party;
    private Long likeCnt;
    private Long commentCnt;
    private Boolean isLike;
    private LocalDateTime registDate;

    public CommuResDto(Community entity, List<Category> categoryList, Long likeCnt, Long commentCnt, Boolean isLike){
        this.commuId = entity.getCommuId();
        this.userName = entity.getUser().getName();
        this.content = entity.getContent();
        this.type = entity.getType();
        this.party = entity.getParty();
        this.registDate = entity.getRegistDate();
        this.categoryList = categoryList;
        this.likeCnt = likeCnt;
        this.commentCnt = commentCnt;
        this.isLike = isLike;

    }

}
