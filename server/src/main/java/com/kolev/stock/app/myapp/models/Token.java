package com.kolev.stock.app.myapp.models;

import com.kolev.stock.app.myapp.enums.TokenType;
import com.kolev.stock.app.myapp.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {

    @Id
    @GeneratedValue
    @Column(name = "token_id")
    public Integer id;

    @Column(unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;

    public boolean expired;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    public User user;
}