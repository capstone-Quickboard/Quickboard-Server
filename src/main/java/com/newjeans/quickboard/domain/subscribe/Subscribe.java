package com.newjeans.quickboard.domain.subscribe;

import com.newjeans.quickboard.domain.User.User;
import com.newjeans.quickboard.domain.department.Department;
import com.newjeans.quickboard.domain.keyword.Keyword;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
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

    @Builder
    public Subscribe(User user, Keyword keyword){
        this.user = user;
        this.keyword = keyword;
    }
}
