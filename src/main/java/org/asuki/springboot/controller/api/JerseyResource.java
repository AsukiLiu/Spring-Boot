package org.asuki.springboot.controller.api;

import org.asuki.springboot.model.Product;
import org.asuki.springboot.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("demo")
public class JerseyResource {

    private static final Logger log = LoggerFactory.getLogger(JerseyResource.class);

    @Autowired
    private ProductService productService;

    @Path("{sku}")
    @GET
    @Produces(APPLICATION_JSON)
    public List<Product> get(@PathParam("sku") String sku) {

        List<Product> products = productService.getSku(sku);
        products.forEach(p -> log.info(p.toString()));

        products = productService.getAvailableSku(sku);
        products.forEach(p -> log.info(p.toString()));

        products = productService.getAvailableSkuCustom(sku);

        return products;
    }

}
