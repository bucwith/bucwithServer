package com.bucwith.dto.star;

import com.bucwith.domain.star.IconCode;
import com.bucwith.domain.star.Star;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class StarReqDto {
    @NotNull private Integer bucketId;
    @NotBlank private String nickname;
    @NotBlank private String contents;
    @NotNull private IconCode iconCode; // icon Table 조회를 위한 Code

    public Star toEntity() {
        return Star.builder()
                .bucketId(bucketId)
                .nickname(nickname)
                .contents(contents)
                .iconCode(iconCode)
                .build();
    }
}