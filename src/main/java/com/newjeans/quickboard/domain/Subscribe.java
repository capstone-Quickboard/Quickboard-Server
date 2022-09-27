package com.newjeans.quickboard.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(
        name="subscribe",
        uniqueConstraints={
                @UniqueConstraint(
                        columnNames = {"user","keyword"}
                )
        }
)
public class Subscribe {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "keyword_keyword")
    private Keyword keyword;
}
