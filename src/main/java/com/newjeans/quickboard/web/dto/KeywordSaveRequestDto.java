package com.newjeans.quickboard.web.dto;

import com.newjeans.quickboard.domain.keyword.Keyword;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KeywordSaveRequestDto {


    private String keyword;
    private int subscriberCount;

    @Builder
    public KeywordSaveRequestDto(String keyword) {
        this.keyword = keyword;
    }

    @Builder
    public void KeywordplusRequestDto(int subscriberCount) {
        this.subscriberCount = subscriberCount + 1;
    }

    public Keyword toEntity() {
        return Keyword.builder()
                .keyword(keyword)
                .subscribersCount(getSubscriberCount()+1)
                .build();
    }
}
