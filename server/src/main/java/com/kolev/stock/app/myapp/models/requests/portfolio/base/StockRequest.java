package com.kolev.stock.app.myapp.models.requests.portfolio.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockRequest {

    private String symbol;
    private Long price;
    private Long amount;
}
