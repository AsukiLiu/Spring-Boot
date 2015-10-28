package org.asuki.springboot.controller.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class Hoge {
//    @NotEmpty
    private int id;
//    @NotEmpty
    @Size(max = 10)
    private String name;
    private String detail;

    public Hoge(int id, String name) {
        this.id = id;
        this.name = name;
    }
}