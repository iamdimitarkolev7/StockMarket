package com.kolev.stock.app.myapp.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "wallets")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wallet_id")
    private Long walletId;

    @Builder.Default
    private Long availableBalance = 0L;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL)
    private List<Transaction> walletTransactions;
}
