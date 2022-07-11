package com.idfinance.watcher.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.idfinance.watcher.exception.CoinNotFound;
import com.idfinance.watcher.model.Coin;
import com.idfinance.watcher.service.implementation.CoinServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/crypto")
public class CryptocurrencyController {
    private static final Logger LOGGER = LogManager.getLogger();
    private final CoinServiceImpl coinService;

    @GetMapping()
    public ResponseEntity<List<Coin>> getAllCoins(){
        return ResponseEntity.ok(coinService.getAllCoins());
    }

    @Scheduled(fixedRate = 60000)
    public void updateCoins() throws JsonProcessingException {
        coinService.updateCoin();
        LOGGER.info("LAST DATABASE UPDATE: " + new Date());
    }

    @GetMapping("/{symbol}")
    public ResponseEntity<Coin> getCoinInfo(@PathVariable String symbol) throws CoinNotFound {
        if (coinService.findCoinBySymbol(symbol) == null){
            throw new CoinNotFound();
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(coinService.findCoinBySymbol(symbol));
    }

}
