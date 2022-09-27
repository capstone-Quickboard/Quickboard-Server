package com.newjeans.quickboard.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(
        name="user_department",
        uniqueConstraints={
                @UniqueConstraint(
                        columnNames = {"user_id","department_id"}
                )
        }
)
public class UserDepartment {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Builder
    public UserDepartment(User user, Department department){
        this.user=user;
        this.department =department;
    }
}
