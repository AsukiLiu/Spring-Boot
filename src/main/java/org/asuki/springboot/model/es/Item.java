package org.asuki.springboot.model.es;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@Document(indexName = "item", type = "simple")
public class Item {

    private Long id;

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String sku;

    private String name;

    private String description;
}
