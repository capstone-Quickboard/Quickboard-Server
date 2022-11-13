package com.newjeans.quickboard.domain.notice;

import com.newjeans.quickboard.domain.Bookmark.Bookmark;
import com.newjeans.quickboard.domain.department.Department;
import com.newjeans.quickboard.domain.userNoticeDeadline.UserNoticeDeadline;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="notice_id")
    private Long id;
    private String url;
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String uploadDate;
    private String deadLine;

    @ManyToOne
    @JoinColumn(name="department_id")
    private Department department;

    @OneToMany(mappedBy = "notice")
    private List<Bookmark> bookmarks=new ArrayList<>();

    @OneToMany(mappedBy = "notice")
    private List<UserNoticeDeadline> userNoticeDeadlines=new ArrayList<>();
    @Builder
    public Notice(String url, String title, String content, String uploadDate, String deadLine){
        this.url = url;
        this.title = title;
        this.content = content;
        this.uploadDate = uploadDate;
        this.deadLine = deadLine;
    }
}
