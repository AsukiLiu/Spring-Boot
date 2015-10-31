package org.asuki.springboot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table
@Getter
@Setter
public class Department extends BaseEntity {

    @Column(nullable = false, length = 20)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    private boolean deleted;

    @OneToMany(cascade = ALL, mappedBy = "department")
    private List<Employee> employeeList;
}
