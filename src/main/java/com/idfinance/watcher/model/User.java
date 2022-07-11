package com.idfinance.watcher.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "starting_price")
    private double startingPrice;
}
