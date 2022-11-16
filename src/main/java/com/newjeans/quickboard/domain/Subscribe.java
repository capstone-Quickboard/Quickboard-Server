package com.newjeans.quickboard.domain;

import com.newjeans.quickboard.domain.user.User;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(
        name="subscribe",
        uniqueConstraints={
                @UniqueConstraint(
                        columnNames = {"user_id","keyword_keyword"}
                )
        }
)
public class Subscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="subscribe_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "keyword_keyword", referencedColumnName="keyword_keyword")
    private Keyword keyword;
}
