package com.newjeans.quickboard.web.dto;

import com.newjeans.quickboard.domain.notice.Notice;
import lombok.Getter;

@Getter
public class NoticeResDto {
    private String title;
    private String content;
    private String uploadDate;
    private String deadline;
    private String department;

    public NoticeResDto(Notice entity){
        this.title = entity.getTitle();
        this.content=entity.getContent();
        this.deadline = entity.getDeadLine();
        this.uploadDate = entity.getUploadDate();
        this.department = entity.getDepartment().getName();
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
