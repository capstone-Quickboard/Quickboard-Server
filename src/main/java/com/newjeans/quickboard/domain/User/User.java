package com.newjeans.quickboard.domain.User;

import com.newjeans.quickboard.domain.Bookmark.Bookmark;
import com.newjeans.quickboard.domain.Subscribe;
import com.newjeans.quickboard.domain.userDepartment.UserDepartment;
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

    @OneToMany(mappedBy = "user")
    private List<UserDepartment> userDepartments = new ArrayList<>();

    @Builder
    public User(String uuid, String fcmToken){
        this.uuid = uuid;
        this.fcmToken = fcmToken;
    }
}
