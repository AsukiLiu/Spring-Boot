package org.asuki.springboot.repository.es;

import org.asuki.springboot.model.es.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
