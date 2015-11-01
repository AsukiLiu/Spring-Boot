package org.asuki.springboot.repository;


import org.asuki.springboot.model.Product;

import java.util.List;

public interface ProductRepositoryCustom {
    List<Product> findBySkuOnlyAvailablesCustom(String sku);
}
