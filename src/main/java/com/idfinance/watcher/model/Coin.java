package com.idfinance.watcher.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Data
@Entity
@Table(name = "coins")
public class Coin {
    @Id
    public int id;

    @Column(name = "symbol")
    public String symbol;

    @Column(name = "price_usd")
    public double priceUsd;
}
