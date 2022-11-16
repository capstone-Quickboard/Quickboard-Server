package com.newjeans.quickboard.domain.alarmDepartment;

import com.newjeans.quickboard.domain.department.Department;
import com.newjeans.quickboard.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(
        uniqueConstraints={
                @UniqueConstraint(
                        columnNames = {"user_id","department_id"}
                )
        }
)
public class AlarmDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="alarm_department_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Builder
    public AlarmDepartment(User user, Department department){
        this.user=user;
        this.department =department;
    }
}
