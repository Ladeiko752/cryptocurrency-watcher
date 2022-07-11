package com.idfinance.watcher.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idfinance.watcher.config.CoinsConfig;
import com.idfinance.watcher.dao.CoinsRepository;
import com.idfinance.watcher.dto.CoinJsonResponseDto;
import com.idfinance.watcher.model.Coin;
import com.idfinance.watcher.service.CoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoinServiceImpl implements CoinService {

    @Value("${crypto.api}")
    private String tickerApi;

    private final RestTemplate restTemplate;
    private final CoinsConfig coinsConfig;
    private final CoinsRepository coinsRepository;
    private final UserServiceImpl userService;

    @Override
    public Coin findCoinBySymbol(String symbol){
        return coinsRepository.findCoinBySymbol(symbol);
    }

    @Override
    public List<CoinJsonResponseDto> getCoinById(int id) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(restTemplate.getForObject(tickerApi + id, String.class),
                new TypeReference<>(){});
    }

    @Override
    public void updateCoin() throws JsonProcessingException {
        List<Coin> coins = coinsConfig.getCoins();
        for (Coin coin:coins){
            userService.priceChangeCheck(coin.getSymbol());
            coin.setId(coins.get(0).getId());
            coin.setSymbol(coins.get(0).getSymbol());
            coin.setPriceUsd(getCoinById(coin.getId()).get(0).getPriceUsd());
            coinsRepository.save(coin);
        }
    }

    @Override
    public List<Coin> getAllCoins() {
        return coinsRepository.findAll();
    }
}
