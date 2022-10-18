package com.newjeans.quickboard.domain;

import com.newjeans.quickboard.domain.notice.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(
        name="bookmark",
        uniqueConstraints={
                @UniqueConstraint(
                        columnNames = {"user_id","notice_id"}
                )
        }
)
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="bookmark_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "notice_id")
    private Notice notice;

    @Builder
    public Bookmark(User user, Notice notice){
        this.user = user;
        this.notice=notice;
    }
}
