package com.newjeans.quickboard.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BookmarkSaveReqDto {
    private Long noticeId;

    @Builder
    public BookmarkSaveReqDto(Long noticeId){
        this.noticeId = noticeId;
    }
}
