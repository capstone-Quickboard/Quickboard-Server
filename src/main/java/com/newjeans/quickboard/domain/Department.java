package com.newjeans.quickboard.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "department_id")
    private List<Notice> notices = new ArrayList<Notice>();

    public Department(String name){
        this.name = name;
    }
}
