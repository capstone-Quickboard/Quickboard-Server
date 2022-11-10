package com.newjeans.quickboard.web.dto;

import com.newjeans.quickboard.domain.notice.Notice;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BookmarkedNoticeListResDto {
    private Long noticeId;
    private String title;
    private String uploadDate;
    private String department;
    private int dDay;


    public BookmarkedNoticeListResDto(Notice entity, int dDay) {
        this.noticeId = entity.getId();
        this.title = entity.getTitle();
        this.department = entity.getDepartment().getName();
        this.uploadDate = entity.getUploadDate();
        this.dDay=dDay;
    }
}
