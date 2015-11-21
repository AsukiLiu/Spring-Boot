package org.asuki.springboot.controller.api.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.asuki.springboot.controller.api.jackson.Views;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @JsonView(Views.Public.class)
    public int id;

    @JsonView(Views.Public.class)
    public String itemName;

    @JsonView(Views.Internal.class)
    public String ownerName;

}
