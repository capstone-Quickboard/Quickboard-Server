package com.newjeans.quickboard.web.dto;

import com.newjeans.quickboard.domain.notice.Notice;
import lombok.Getter;

@Getter
public class BookmarkedNoticeListResDto {
    private Long noticeId;
    private String title;
    private String uploadDate;
    private String department;
    private Integer dDay;


    public BookmarkedNoticeListResDto(Notice entity, Integer dDay) {
        this.noticeId = entity.getId();
        this.title = entity.getTitle();
        this.department = entity.getDepartment().getName();
        this.uploadDate = entity.getUploadDate();
        this.dDay=dDay;
    }
}
