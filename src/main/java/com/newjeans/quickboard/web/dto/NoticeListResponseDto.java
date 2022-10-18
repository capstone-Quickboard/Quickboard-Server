package com.newjeans.quickboard.web.dto;

import com.newjeans.quickboard.domain.notice.Notice;
import lombok.Getter;

@Getter
public class NoticeListResponseDto {
    private String title;
    private String deadline;
    private String department;
    private int dDay;

    public NoticeListResponseDto(Notice entity, int dDay) {
        this.title = entity.getTitle();
        this.deadline = entity.getDeadLine();
        this.department = entity.getDepartment().getName();
        this.dDay = dDay;
    }
}
