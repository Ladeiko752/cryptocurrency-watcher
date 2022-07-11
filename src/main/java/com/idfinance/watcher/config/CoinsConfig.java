package com.idfinance.watcher.config;

import com.idfinance.watcher.model.Coin;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "crypto")
@EnableConfigurationProperties
public class CoinsConfig {
    private List<Coin> coins;
}
