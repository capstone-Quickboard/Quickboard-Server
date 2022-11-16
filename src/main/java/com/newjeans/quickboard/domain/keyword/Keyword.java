package com.newjeans.quickboard.domain.keyword;

import com.newjeans.quickboard.domain.Subscribe;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@Entity

public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "keyword_keyword", unique = true, nullable = false)
    private String keyword;

    private int subscribersCount;

//    @OneToMany(mappedBy = "keyword")
//    private List<Subscribe> subscribes=new ArrayList<Subscribe>();

    @Builder
    public Keyword(String keyword, int subscribersCount) {
        this.keyword = keyword;
        this.subscribersCount = subscribersCount;
    }
}
