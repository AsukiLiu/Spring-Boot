package org.asuki.springboot.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "products")
@Getter
@Setter
@ToString(exclude = "id")
public class Product {
    @Id
    private String id;

    private String sku;

    @Field(value = "material_name")
    private String materialName;

    private Double price;

    private Integer availability;
}
