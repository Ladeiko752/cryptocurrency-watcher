package com.idfinance.watcher.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.idfinance.watcher.dto.CoinJsonResponseDto;
import com.idfinance.watcher.model.Coin;

import java.util.List;

public interface CoinService {
    List<CoinJsonResponseDto> getCoinById(int id) throws JsonProcessingException;
    void updateCoins() throws JsonProcessingException;
    List<Coin> getAllCoins();
    Coin getCoinBySymbol(String symbol);
}
