package com.newjeans.quickboard.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BookmarkReqDto {
    private Long noticeId;

    @Builder
    public BookmarkReqDto(Long noticeId){
        this.noticeId = noticeId;
    }
}
