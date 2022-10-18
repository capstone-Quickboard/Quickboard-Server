package com.newjeans.quickboard.web.dto;

import com.newjeans.quickboard.domain.notice.Notice;
import lombok.Getter;

@Getter
public class NoticeResponseDto {
    private String title;
    private String content;
    private String deadline;
    private String department;
    private int dDay;

    public NoticeResponseDto(Notice entity, int dDay) {
        this.title = entity.getTitle();
        this.deadline = entity.getDeadLine();
        this.dDay = dDay;
    }
}
