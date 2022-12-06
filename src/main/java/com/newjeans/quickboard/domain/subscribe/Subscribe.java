package com.newjeans.quickboard.domain.subscribe;

import com.newjeans.quickboard.domain.keyword.Keyword;
import com.newjeans.quickboard.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(
        name="subscribe",
        uniqueConstraints={
                @UniqueConstraint(
                        columnNames = {"user_id","keyword_id"}
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
    @JoinColumn(name = "keyword_id", referencedColumnName="keyword_id")
    private Keyword keyword;

    @Builder
    public Subscribe(User user, Keyword keyword){
        this.user= user;
        this.keyword=keyword;
    }
}
