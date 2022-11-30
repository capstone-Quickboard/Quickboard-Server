package com.newjeans.quickboard.domain.keyword;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


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

    public void plusSubscribers() {
        this.subscribersCount++;
    }

    public void minusSubscribers() {this.subscribersCount--;}



}
