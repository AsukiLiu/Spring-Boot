package org.asuki.springboot.service;

import org.asuki.springboot.model.Product;
import org.asuki.springboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> getSku(String sku) {
        return repository.findBySku(sku);
    }

    public List<Product> getAvailableSku(String sku) {
        return repository.findBySkuOnlyAvailables(sku);
    }

    public List<Product> getAvailableSkuCustom(String sku) {
        return repository.findBySkuOnlyAvailablesCustom(sku);
    }
}
