package com.newjeans.quickboard.web.dto;

import com.newjeans.quickboard.domain.notice.Notice;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NoticeListResDto {
    private String title;
    private String uploadDate;
    private String department;
    private boolean isBookmarked;


    public NoticeListResDto(Notice entity, boolean isBookmarked) {
        this.title = entity.getTitle();
        this.department = entity.getDepartment().getName();
        this.uploadDate = entity.getUploadDate();
        this.isBookmarked = isBookmarked;
    }
}
