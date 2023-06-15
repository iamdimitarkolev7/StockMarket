package com.kolev.stock.app.myapp.models.requests.portfolio;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kolev.stock.app.myapp.models.requests.portfolio.base.StockRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SellStockRequest extends StockRequest {
}
