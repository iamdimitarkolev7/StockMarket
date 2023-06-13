package com.kolev.stock.app.myapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "portfolios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "portfolio_id")
    private Long portfolioId;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
    private List<PortfolioStock> portfolioStocks;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL)
    private List<Transaction> portfolioTransactions;
}
