package org.asuki.springboot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Team extends BaseEntity {

    @Column(nullable = false, length = 20)
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Employee> employeeList;
}
