package org.asuki.springboot.controller.api.es;

import org.asuki.springboot.model.es.Item;
import org.asuki.springboot.repository.es.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// localhost:8082/item localhost:9200/item
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Item addItem(@RequestBody Item item) {
        return itemRepository.save(item);
    }

    @RequestMapping(value = "/{itemId}", method = RequestMethod.PUT)
    @ResponseBody
    public Item updateItem(@PathVariable("itemId") Long itemId, @RequestBody Item item) {
        item.setId(itemId);
        itemRepository.save(item);
        return item;
    }

    @RequestMapping(value = "/{itemId}", method = RequestMethod.DELETE)
    public void deleteItem(@PathVariable("itemId") Long itemId) {
        itemRepository.delete(itemId);
    }

    // localhost:8082/item?query=sample
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Item> searchItem(@RequestParam("query") String query) {
        return itemRepository.findByNameOrDescription(query);
    }
}
