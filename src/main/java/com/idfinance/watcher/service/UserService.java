package com.idfinance.watcher.service;

import com.idfinance.watcher.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> findUsersByCoinSymbol(String symbol);
    void priceChangeCheck(String symbol);
}
