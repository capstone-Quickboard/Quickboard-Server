package com.newjeans.quickboard.web.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class DeadlineReqDto {
    private String deadline;

    @Builder
    public DeadlineReqDto(String deadline){
        this.deadline = deadline;
    }

}
