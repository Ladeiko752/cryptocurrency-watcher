package com.idfinance.watcher.service.implementation;

import com.idfinance.watcher.dao.CoinsRepository;
import com.idfinance.watcher.dao.UserRepository;
import com.idfinance.watcher.model.Coin;
import com.idfinance.watcher.model.User;
import com.idfinance.watcher.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final double MAX_PERCENT_CHANGED = 1.0;
    private static final Logger LOGGER = LogManager.getLogger();
    private final CoinsRepository coinsRepository;
    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        Coin coin = coinsRepository.findCoinBySymbol(user.getSymbol());
        user.setStartingPrice(coin.getPriceUsd());
        return userRepository.save(user);
    }

    @Override
    public List<User> findUsersByCoinSymbol(String symbol){
        return userRepository.findAllBySymbol(symbol);
    }

    @Override
    public void priceChangeCheck(String symbol){
        List<User> users = userRepository.findAllBySymbol(symbol);

        for (User user: users){
            double priceChange = Math.abs(user.getStartingPrice() - coinsRepository.findCoinBySymbol(symbol).
                    getPriceUsd())/user.getStartingPrice() * 100;
            if (priceChange > MAX_PERCENT_CHANGED){
                LOGGER.warn(user.getUsername() + ", coin price: " + symbol + " has been changed by " + priceChange + "%");
            }
        }
    }
}
