package org.asuki.springboot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Entity
@Table(indexes = {@Index(name = "employee_profile_id_sex_idx", columnList = "id,sex")})
@Getter
@Setter
public class EmployeeProfile extends BaseEntity {

    public enum SEX {
        MALE, FEMALE
    }

    @Column(nullable = false, length = 5)
    @Enumerated(STRING)
    private SEX sex;
}
