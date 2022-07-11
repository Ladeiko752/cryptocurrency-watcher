package com.idfinance.watcher.dao;

import com.idfinance.watcher.model.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinsRepository extends JpaRepository<Coin, Integer> {
    Coin findCoinBySymbol(String symbol);
}
