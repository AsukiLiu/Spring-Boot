package org.asuki.springboot.repository;

import org.asuki.springboot.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

public class ProductRepositoryImpl implements ProductRepositoryCustom {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Product> findBySkuOnlyAvailablesCustom(String sku) {
        Criteria criteria =
                where("sku").is(sku)
                        .andOperator(where("availability").is(1));

        return mongoTemplate.find(query(criteria), Product.class);
    }
}
