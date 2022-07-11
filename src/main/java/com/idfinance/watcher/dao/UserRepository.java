package com.idfinance.watcher.dao;

import com.idfinance.watcher.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllBySymbol(String symbol);
}
