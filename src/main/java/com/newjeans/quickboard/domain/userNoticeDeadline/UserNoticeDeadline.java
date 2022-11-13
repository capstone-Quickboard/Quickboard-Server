package com.newjeans.quickboard.domain.userNoticeDeadline;

import com.newjeans.quickboard.domain.User.User;
import com.newjeans.quickboard.domain.notice.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Getter
@NoArgsConstructor
@Entity
@Table(
        name="user_notice_deadline",
        uniqueConstraints={
                @UniqueConstraint(
                        columnNames = {"user_id","notice_id"}
                )
        }
)
public class UserNoticeDeadline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "notice_id")
    private Notice notice;

    private String deadline;

    @Builder
    public UserNoticeDeadline(User user, Notice notice, String deadline){
        this.user=user;
        this.notice = notice;
        this.deadline = deadline;
    }

    public void update(String deadline){
        this.deadline=deadline;
    }
}
