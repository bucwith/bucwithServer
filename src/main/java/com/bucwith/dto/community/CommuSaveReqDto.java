package com.bucwith.dto.community;

import com.bucwith.domain.account.User;
import com.bucwith.domain.community.Category;
import com.bucwith.domain.community.CommuType;
import com.bucwith.domain.community.Community;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@NoArgsConstructor
public class CommuSaveReqDto {

    // 왜 UserId로 안받지?!
    // CommunityService.commuSave 에서 어차피 User 조회 함.
    private User user;
    @NotBlank(message = "내용을 입력해주세요")
    private String content;
    private CommuType type;
    private List<Category> category;
    private int party;

    @Builder
    public CommuSaveReqDto(User user, String content, CommuType type, List<Category> category, int party){
        this.user = user;
        this.content = content;
        this.type = type;
        this.category = category;
        this.party = party;
    }


    // 여기서 User Param으로 받아서 넣어주면 안되나?!
    public Community toEntity(){
        return Community.builder()
                .user(user)
                .content(content)
                .type(type)
                .party(party)
                .build();
    }

}
