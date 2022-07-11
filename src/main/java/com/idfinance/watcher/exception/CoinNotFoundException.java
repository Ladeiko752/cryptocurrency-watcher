package com.idfinance.watcher.exception;

public class CoinNotFoundException extends Exception{
    public CoinNotFoundException() {
        super("Coin not found");
    }
}
