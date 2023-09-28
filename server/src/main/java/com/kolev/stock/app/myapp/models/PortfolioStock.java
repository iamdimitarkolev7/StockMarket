package com.kolev.stock.app.myapp.models;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Table(name = "portfolio_stocks")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PortfolioStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "portfolio_stock_id")
    private Long portfolioStockId;

    private String symbol;
    private Long price;
    private String currency;
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;
}
