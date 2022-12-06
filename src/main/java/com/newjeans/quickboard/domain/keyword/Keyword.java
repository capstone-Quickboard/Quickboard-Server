package com.newjeans.quickboard.domain.keyword;

import com.newjeans.quickboard.domain.subscribe.Subscribe;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="keyword_id")
    private Long id;

    @Column(name = "keyword_keyword", unique = true)
    private String keyword;

    private int subscribersCount;

    @OneToMany(mappedBy = "keyword")
    private List<Subscribe> subscribes=new ArrayList<Subscribe>();

    @Builder
    public Keyword(String keyword){
        this.keyword=keyword;
        this.subscribersCount = 0;
    }

    public int plusSubscriberCount(){
        this.subscribersCount++;
        return this.subscribersCount;
    }

    public int minusSubscriberCount(){
        this.subscribersCount--;
        return this.subscribersCount;
    }

}
