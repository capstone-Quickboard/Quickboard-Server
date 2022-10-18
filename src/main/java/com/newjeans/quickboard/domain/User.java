package com.newjeans.quickboard.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column(unique = true)
    private String uuid;

    @Column(unique = true)
    private String fcmToken;

    @OneToMany(mappedBy = "user")
    private List<Subscribe> subscribes =new ArrayList<Subscribe>();

    @OneToMany(mappedBy = "user")
    private List<Bookmark> bookmarks =new ArrayList<Bookmark>();

    @Builder
    public User(String uuid, String fcmToken){
        this.uuid = uuid;
        this.fcmToken = fcmToken;
    }
}
