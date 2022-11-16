package com.newjeans.quickboard.domain.user;

import com.newjeans.quickboard.domain.Bookmark.Bookmark;
import com.newjeans.quickboard.domain.Subscribe;
import com.newjeans.quickboard.domain.department.Department;
import com.newjeans.quickboard.domain.userNoticeDeadline.UserNoticeDeadline;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Check(constraints = "department_id > 10") // 11부터 전공학과 코드
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column(unique = true)
    private String uuid;

    @Column(unique = true)
    private String fcmToken;

    @ManyToOne
    @JoinColumn(name="department_id")
    private Department department;

    @OneToMany(mappedBy = "user")
    private List<Subscribe> subscribes =new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Bookmark> bookmarks =new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserNoticeDeadline> userNoticeDeadlines =new ArrayList<>();

    @Builder
    public User(String uuid, String fcmToken, Department department){
        this.uuid = uuid;
        this.fcmToken = fcmToken;
        this.department = department;
    }

    public void departmentUpdate(Department department){
        this.department = department;
    }
}
