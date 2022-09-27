package com.newjeans.quickboard.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String keyword;

    private int subscribersCount;

    @OneToMany(mappedBy = "keyword_keyword")
    private List<Subscribe> subscribes=new ArrayList<Subscribe>();

}
