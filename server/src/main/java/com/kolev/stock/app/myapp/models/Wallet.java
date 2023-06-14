package com.kolev.stock.app.myapp.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "transactionId")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Transaction> walletTransactions;

    public void addTransaction(Transaction transaction) {
        walletTransactions.add(transaction);
    }
}
