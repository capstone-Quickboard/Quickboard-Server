package com.newjeans.quickboard.domain.department;

import com.newjeans.quickboard.domain.notice.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="department_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "department")
    private List<Notice> notices = new ArrayList<Notice>();

    @Builder
    public Department(String name){
        this.name = name;
    }
}
