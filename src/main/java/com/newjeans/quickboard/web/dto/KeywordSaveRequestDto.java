package com.newjeans.quickboard.web.dto;

import com.newjeans.quickboard.domain.keyword.Keyword;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KeywordSaveRequestDto {


    private String keyword;

    @Builder
    public KeywordSaveRequestDto(String keyword) {
        this.keyword = keyword;
    }

}
