package org.asuki.springboot.repository.es;

import org.asuki.springboot.model.es.Item;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {
    @Query("{\"bool\" : {\"should\" : [{\"term\" : {\"name\" : \"?0\"}}, {\"term\" : {\"description\" : \"?0\"}}]}}}")
    List<Item> findByNameOrDescription(String query);
}
