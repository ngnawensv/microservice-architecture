package com.belrose.docservice.model.drools;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    private String name;
    private String cardType;
    private  Integer discount;
    private Integer price;

}
