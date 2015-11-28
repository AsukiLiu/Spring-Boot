package org.asuki.springboot.model.es;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Document(indexName = "order")
public class Order {
    private Long id;
    private Date createdAt;
    private BigDecimal amount;
}
