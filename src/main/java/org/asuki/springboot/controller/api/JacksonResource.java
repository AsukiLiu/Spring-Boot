package org.asuki.springboot.controller.api;

import com.fasterxml.jackson.annotation.JsonView;
import org.asuki.springboot.controller.api.jackson.Views;
import org.asuki.springboot.controller.api.model.Item;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("items")
public class JacksonResource {

    @JsonView(Views.Public.class)
    @RequestMapping("{id}")
    public Item getItemPublic(@PathVariable int id) {
        return getDummyItemById(id);
    }

    @JsonView(Views.Internal.class)
    @RequestMapping("internal/{id}")
    public Item getItemInternal(@PathVariable int id) {
        return getDummyItemById(id);
    }

    private Item getDummyItemById(int id) {
        return new Item(id, "book", "andy");
    }
}
