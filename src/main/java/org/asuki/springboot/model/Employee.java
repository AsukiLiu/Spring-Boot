package org.asuki.springboot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table
@Getter
@Setter
public class Employee extends BaseEntity {

    @Column(nullable = false, length = 20)
    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn
    private Department department;

    @ManyToOne(fetch = LAZY)
    @JoinColumn
    private Team team;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "id", referencedColumnName = "employee_profile_fk")
    private EmployeeProfile employeeProfile;

    @ManyToMany
    @JoinTable(name = "project_employee",
            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"))
    private List<Project> projectList;
}
