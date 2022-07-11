package com.idfinance.watcher.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.idfinance.watcher.exception.CoinNotFoundException;
import com.idfinance.watcher.model.Coin;
import com.idfinance.watcher.service.implementation.CoinServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/crypto")
public class CryptocurrencyController {

    private final CoinServiceImpl coinService;

    @GetMapping()
    public ResponseEntity<List<Coin>> getAllCoins() throws CoinNotFoundException {
        List<Coin> coins = coinService.getAllCoins();
        if (coins.isEmpty()){
            throw new CoinNotFoundException();
        }
        return ResponseEntity.status(HttpStatus.OK).body(coins);
    }

    @Scheduled(fixedRate = 60000)
    public void updateCoins() throws JsonProcessingException {
        coinService.updateCoins();
    }

    @GetMapping("/{symbol}")
    public ResponseEntity<Coin> getCoinInfo(@PathVariable String symbol) throws CoinNotFoundException {
        Coin coin = coinService.getCoinBySymbol(symbol);
        if (coin == null){
            throw new CoinNotFoundException();
        }
        return ResponseEntity.status(HttpStatus.OK).body(coin);
    }

}
