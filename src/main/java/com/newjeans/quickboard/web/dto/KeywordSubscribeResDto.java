package com.newjeans.quickboard.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class KeywordSubscribeResDto {
    private Long keywordId;
    private String keyword;

    @Builder
    public KeywordSubscribeResDto(Long keywordId, String keyword){
        this.keywordId=keywordId;
        this.keyword=keyword;
    }
}
