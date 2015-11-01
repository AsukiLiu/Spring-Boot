package org.asuki.springboot.repository;

import org.asuki.springboot.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String>, ProductRepositoryCustom {
    List<Product> findBySku(String sku);

    @Query(value = "{sku: ?0, availability : 1}")
    List<Product> findBySkuOnlyAvailables(String sku);
}
